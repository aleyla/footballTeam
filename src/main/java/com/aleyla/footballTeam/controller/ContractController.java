package com.aleyla.footballTeam.controller;

import com.aleyla.footballTeam.entity.Contract;
import com.aleyla.footballTeam.service.ContractService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping(path = "contract/v1")
public class ContractController {

    private ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }


    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Contract contract) {
        Contract saved = contractService.save(contract);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().pathSegment("contract", "v1", String.valueOf(saved.getId())).build().toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(path = "{id}")
    public @ResponseBody
    Contract getById(@PathVariable("id") Long id) {
        return contractService.findByid(id);
    }

    @DeleteMapping(path = "{id}")
    public @ResponseBody
    void delete(@PathVariable("id") Long id) {
        contractService.delete(id);
    }

    @PutMapping(path = "{id}")
    public @ResponseBody
    void update(@PathVariable("id") Long id, @RequestBody Contract contract) {
        if (contract.getId() != null && !id.equals(contract.getId())) {
            //TODO Exception
        }
        if (contract.getId() == null) {
            contract.setId(id);
        }
        contractService.save(contract);
    }
}