package com.aleyla.footballTeam.service;

import com.aleyla.footballTeam.entity.Team;
import com.aleyla.footballTeam.exception.InvalidRequestException;
import org.junit.Before;
import org.junit.Test;

public class TeamServiceUnitTest {

    private Team testTeam = new Team();


    @Before
    public void setUp() {
        testTeam.setId(1L);
        testTeam.setCurrencyCode("eur");
        testTeam.setName("testTeam");
    }

    @Test(expected = InvalidRequestException.class)
    public void can_not_save_null_team() {
        TeamService service = new TeamService(null, null);
        service.save(null);
    }

    @Test(expected = InvalidRequestException.class)
    public void can_not_save_null_team_name() {
        TeamService service = new TeamService(null, null);
        testTeam.setName(null);
        service.save(testTeam);
    }

    @Test(expected = InvalidRequestException.class)
    public void can_not_save_null_team_currency_code() {
        TeamService service = new TeamService(null, null);
        testTeam.setCurrencyCode(null);
        service.save(testTeam);
    }

    @Test(expected = InvalidRequestException.class)
    public void can_not_update_team_id_and_id_not_match() {
        TeamService service = new TeamService(null, null);
        service.update(0L, testTeam);
    }

}