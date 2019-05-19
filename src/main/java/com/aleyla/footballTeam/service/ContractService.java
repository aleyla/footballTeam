package com.aleyla.footballTeam.service;

import com.aleyla.footballTeam.dto.PlayerContract;
import com.aleyla.footballTeam.dto.TransferSuggest;
import com.aleyla.footballTeam.entity.Contract;
import com.aleyla.footballTeam.entity.Player;
import com.aleyla.footballTeam.entity.Team;
import com.aleyla.footballTeam.exception.EntityNotFoundException;
import com.aleyla.footballTeam.exception.InvalidRequestException;
import com.aleyla.footballTeam.repository.ContractRepository;
import com.aleyla.footballTeam.repository.PlayerRepository;
import com.aleyla.footballTeam.repository.TeamRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Component
public class ContractService {
    private ContractRepository contractRepository;
    private PlayerRepository playerRepository;
    private TeamRepository teamRepository;

    public ContractService(ContractRepository contractRepository, PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.contractRepository = contractRepository;
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    public Contract save(Contract contract) {
        validate(contract);
        return contractRepository.save(contract);
    }

    public void update(Long id, Contract contract) {
        if (contract.getId() != null && !id.equals(contract.getId())) {
            throw new InvalidRequestException("id", id, "CONTRACT_ID_NOT_MATCH");
        }
        if (contract.getId() == null) {
            contract.setId(id);
        }
        validate(contract);
        contractRepository.save(contract);
    }


    private void validate(Contract contract) {
        if (contract == null) {
            throw new InvalidRequestException("Contract", contract, "CONTRACT_COULD_NOT_BE_EMPTY");
        }
        if (contract.getContractCode() == null) {
            throw new InvalidRequestException("ContractCode", contract.getContractCode(), "CONTRACT_CODE_COULD_NOT_BE_EMPTY");
        }
        if(teamRepository.findById(contract.getTeamId()).orElse(null) == null){
            throw new InvalidRequestException("Team", contract.getTeamId(), " EXIST");
        }
        if(playerRepository.findById(contract.getPlayerId()).orElse(null) == null){
            throw new InvalidRequestException("Player", contract.getPlayerId(), " EXIST");
        }
        //Update edebilsin ama aynı isimli kaydedemesin
        Contract sameCode = contractRepository.findByContractCode(contract.getContractCode());
        if (sameCode != null && !sameCode.getContractCode().equalsIgnoreCase(contract.getContractCode())) {
            throw new InvalidRequestException("ContractCode", contract.getContractCode(), "CONTRACT_CODE_MUST_BE_UNIQUE");
        } else if (sameCode != null && sameCode.getId() != contract.getId() && sameCode.getContractCode().equalsIgnoreCase(contract.getContractCode())) {
            throw new InvalidRequestException("ContractCode", contract.getContractCode(), "CONTRACT_CODE_MUST_BE_UNIQUE");
        }

    }

    public Contract findByid(Long id) {
        Contract contract = contractRepository.findById(id).orElse(null);
        if (contract == null) {
            throw new EntityNotFoundException();
        }
        return contract;
    }

    public void delete(Long id) {
        Contract contract = contractRepository.findById(id).orElse(null);
        if (contract == null) {
            throw new EntityNotFoundException();
        }
        contractRepository.deleteById(id);
    }

    public List<Contract> findAll() {
        return contractRepository.findAll();
    }

    public PlayerContract calculateTransferAmout(Long playerId) {
        Player player = playerRepository.findById(playerId).orElse(null);
        if (player == null) {
            throw new InvalidRequestException("Player", playerId, " EXIST");
        }
        List<Contract> contract = contractRepository.findByPlayerId(playerId, LocalDate.now());
        if (contract == null || contract.isEmpty()) {
            throw new InvalidRequestException("Player", playerId, " HAVE_CONTRACT");
        }

        PlayerContract playerContract = new PlayerContract();
        playerContract.setPlayer(player);
        playerContract.setSuggests(new ArrayList<>());

        contract.stream().forEach(cont -> {
            TransferSuggest transferSuggest = new TransferSuggest();
            BigDecimal contractPrice = calculateContractPrice(player.getExperienceDuration(), calculateAge(player.getBirthday()));
            BigDecimal teamCommission = contractPrice.divide(new BigDecimal(10), 2, BigDecimal.ROUND_HALF_UP);
            transferSuggest.setContractPrice(contractPrice);
            transferSuggest.setTeamCommission(teamCommission);
            transferSuggest.setTransferFee(contractPrice.add(teamCommission));

            Team team = teamRepository.findById(cont.getTeamId()).orElse(null);
            if (team == null) {
                throw new InvalidRequestException("Team", cont.getId(), " EXIST");
            }
            transferSuggest.setCurrencyCode(team.getCurrencyCode());
            transferSuggest.setCurrentTeamName(team.getName());
            playerContract.getSuggests().add(transferSuggest);

        });

        return playerContract;
    }

    private BigDecimal calculateContractPrice(BigDecimal experienceDuration, BigDecimal age) {
        return experienceDuration.multiply(new BigDecimal(100000)).divide(age, 2, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal calculateAge(LocalDate birthday) {
        return new BigDecimal(Period.between(birthday, LocalDate.now()).getYears());
    }


}