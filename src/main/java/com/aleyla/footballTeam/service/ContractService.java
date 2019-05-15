package com.aleyla.footballTeam.service;

import com.aleyla.footballTeam.repository.ContractRepository;
import org.springframework.stereotype.Component;

@Component
public class ContractService {
    private ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }


    
    
    
}
