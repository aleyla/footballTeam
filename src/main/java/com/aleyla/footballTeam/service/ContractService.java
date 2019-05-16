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
        //TODO Controller
        return contractRepository.save(contract);
    }

    public Contract update(Long id, Contract contract) {
        if (contract.getId() != null && !id.equals(contract.getId())) {
            throw new InvalidRequestException("id", id, "CONTRACT_ID_NOT_MATCH");
        }
        if (contract.getId() == null) {
            contract.setId(id);
        }

        //TODO Controller

        return contractRepository.save(contract);
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
        contractRepository.deleteById(id);
    }

    public List<Contract> findAll() {
        return contractRepository.findAll();
    }
}
