package com.aleyla.footballTeam.service;

import com.aleyla.footballTeam.entity.Contract;
import com.aleyla.footballTeam.exception.EntityNotFoundException;
import com.aleyla.footballTeam.exception.InvalidRequestException;
import com.aleyla.footballTeam.repository.ContractRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContractService {
    private ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public Contract save(Contract contract) {
        validate(contract);
        return contractRepository.save(contract);
    }

    public Contract update(Long id, Contract contract) {
        if (contract.getId() != null && !id.equals(contract.getId())) {
            throw new InvalidRequestException("id", id, "CONTRACT_ID_NOT_MATCH");
        }
        if (contract.getId() == null) {
            contract.setId(id);
        }
        validate(contract);
        return contractRepository.save(contract);
    }


    private void validate(Contract contract) {
        if (contract == null) {
            throw new InvalidRequestException("Contract", contract, "CONTRACT_COULD_NOT_BE_EMPTY");
        }
        if (contract.getContractCode() == null) {
            throw new InvalidRequestException("ContractCode", contract.getContractCode(), "CONTRACT_CODE_COULD_NOT_BE_EMPTY");
        }
        Contract sameCode = contractRepository.findByContractCode(contract.getContractCode());
        if (sameCode != null) {
            throw new InvalidRequestException("ContractCode", contract.getContractCode(), "CONTRACT_CODE_MUST_BE_UNIQUE");
        }

    }

    public Contract findByid(Long id) {
        Contract contract = contractRepository.findById(id).orElse(null);
        if(contract == null){
            throw new EntityNotFoundException();
        }
        return contract;
    }

    public List<Contract> findByTeamId(Long teamId) {
        return contractRepository.findAllByTeamId(teamId);
    }

    public List<Contract> findByPlayerId(Long playerId) {
        return contractRepository.findAllByPlayerId(playerId);
    }


    public void delete(Long id) {
        Contract contract = contractRepository.findById(id).orElse(null);
        if(contract == null){
            throw new EntityNotFoundException();
        }
        contractRepository.deleteById(id);
    }

    public List<Contract> findAll() {
        return contractRepository.findAll();
    }
}
