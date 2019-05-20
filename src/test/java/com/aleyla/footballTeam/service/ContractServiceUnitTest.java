package com.aleyla.footballTeam.service;

import com.aleyla.footballTeam.entity.Contract;
import com.aleyla.footballTeam.exception.InvalidRequestException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContractServiceUnitTest {

    private Contract testContract = new Contract();
    @Before
    public void setUp() {
        testContract.setId(1L);
        testContract.setContractCode("abc");
        testContract.setCurrencyCode("eur");
        testContract.setTeamCommission(BigDecimal.ONE);
        testContract.setContractPrice(BigDecimal.TEN);
        testContract.setTransferFee(new BigDecimal(11));
        testContract.setPlayerId(1L);
        testContract.setTeamId(1L);
        testContract.setStart(LocalDate.of(2010,10,10));
        testContract.setEnd(LocalDate.of(2020,10,10));
    }

    @Test(expected = InvalidRequestException.class)
    public void can_not_save_null_contract() {
        ContractService service = new ContractService(null, null, null);
        service.save(null);
    }
    @Test(expected = InvalidRequestException.class)
    public void can_not_save_null_contract_code() {
        ContractService service = new ContractService(null, null, null);
        testContract.setContractCode(null);
        service.save(testContract);
    }

}