package com.aleyla.footballTeam.service;

import com.aleyla.footballTeam.dto.PlayerContract;
import com.aleyla.footballTeam.dto.TransferSuggest;
import com.aleyla.footballTeam.entity.Contract;
import com.aleyla.footballTeam.entity.Player;
import com.aleyla.footballTeam.entity.Team;
import com.aleyla.footballTeam.exception.EntityNotFoundException;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContractServiceIntegrationTest {

    @Autowired
    public ContractService service;
    @Autowired
    public TeamService teamService;
    @Autowired
    public PlayerService playerService;

    private Player testPlayer = new Player();
    private Team testTeam = new Team();
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
        testContract.setStart(LocalDate.of(2010, 10, 10));
        testContract.setEnd(LocalDate.of(2020, 10, 10));

        testPlayer.setId(1L);
        testPlayer.setName("ali");
        testPlayer.setSurname("veli");
        testPlayer.setBirthday(LocalDate.of(1999, 4, 5));
        testPlayer.setExperienceDuration(BigDecimal.TEN);
        testPlayer.setIdentityNumber("1234");

        testTeam.setId(1L);
        testTeam.setCurrencyCode("eur");
        testTeam.setName("testTeamContract");

    }

    @Test
    public void can_save() {
        testContract.setId(null);
        testContract.setContractCode("saveTestContractCode");
        testTeam.setName("saveContractTeam");
        teamService.save(testTeam);
        playerService.save(testPlayer);
        Contract contract = service.save(testContract);
        assertThat(testContract.getContractCode(), equalTo(contract.getContractCode()));
    }

    @Test
    public void can_update_when_parameter_id_null() {
        teamService.save(testTeam);
        playerService.save(testPlayer);

        testContract.setId(null);
        service.update(1L, testContract);
        Contract contract = service.findById(1L);
        assertThat(contract.getContractCode(), equalTo(testContract.getContractCode()));
    }

    @Test(expected = EntityNotFoundException.class)
    public void can_not_find() {
        service.findById(0L);
    }


    @Test(expected = EntityNotFoundException.class)
    public void can_not_delete_entity_not_exist() {
        service.delete(0L);
    }

    @Test
    public void can_delete_exist_entity() {
        testTeam.setName("deleteContractTeam");
        teamService.save(testTeam);
        playerService.save(testPlayer);

        testContract.setId(null);
        testContract.setContractCode("silinecek");
        Contract contract = service.save(testContract);
        service.delete(contract.getId());
    }


    @Test
    public void can_calculate_transfer_amount_true() {
        testTeam.setName("calculate");
        Team team = teamService.save(testTeam);

        testPlayer.setBirthday(LocalDate.now().minusYears(10));
        testPlayer.setExperienceDuration(BigDecimal.TEN);
        Player player = playerService.save(testPlayer);

        testContract.setPlayerId(player.getId());
        testContract.setTeamId(team.getId());
        testContract.setStart(LocalDate.now().minusYears(1));
        testContract.setEnd(LocalDate.now().plusYears(1));
        testContract.setId(null);
        testContract.setContractCode("calculateContract");
        Contract contract = service.save(testContract);

        PlayerContract playerContract = service.calculateTransferAmount(player.getId());
        TransferSuggest first = playerContract.getSuggests().stream().filter(cont -> cont.getCurrentTeamName().equalsIgnoreCase(team.getName())).findAny().orElse(null);
        if (first != null) {
            //experienceDuration *100000 /age
            assertThat(first.getContractPrice(), Matchers.comparesEqualTo(new BigDecimal("100000")));
            assertThat(first.getCurrencyCode(), equalTo(contract.getCurrencyCode()));
        }
        assertThat(playerContract.getPlayer().getId(), equalTo(player.getId()));
    }
}