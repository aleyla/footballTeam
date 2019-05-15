package com.aleyla.footballTeam.service;

import com.aleyla.footballTeam.entity.Contract;
import com.aleyla.footballTeam.repository.ContractRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContractService {
    private ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public Contract save(Contract contract){
        //TODO Controller
        return contractRepository.save(contract);
    }

    public Contract findByid(Long id){
        return contractRepository.findById(id).orElse(null);
    }

    public List<Contract> findByTeamId(Long teamId){
        return contractRepository.findAllByTeamId(teamId);
    }

    public List<Contract> findByPlayerId(Long playerId){
        return  contractRepository.findAllByPlayerId(playerId);
    }


    public void delete(Long id) {
        contractRepository.deleteById(id);
    }
}
