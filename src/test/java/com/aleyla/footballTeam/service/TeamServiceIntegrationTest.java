package com.aleyla.footballTeam.service;

import com.aleyla.footballTeam.entity.Team;
import com.aleyla.footballTeam.exception.EntityNotFoundException;
import com.aleyla.footballTeam.exception.InvalidRequestException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamServiceIntegrationTest {

    public Team testTeam = new Team();

    @Autowired
    public TeamService service;

    @Before
    public void setUp() {
        testTeam.setId(1L);
        testTeam.setCurrencyCode("eur");
        testTeam.setName("testTeam");
    }

    @Test
    public void can_save(){
        testTeam.setId(null);
        testTeam.setName("saveTestName");
        Team team = service.save(testTeam);
        assertThat(team.getName(), equalTo(testTeam.getName()));
    }

    @Test
    public void can_update_when_parametter_id_null(){
        testTeam.setId(null);
        service.update(1L, testTeam);
        Team team = service.findByid(1L);
        assertThat(team.getName(), equalTo(testTeam.getName()));
    }

    @Test(expected = EntityNotFoundException.class)
    public void can_not_find(){
        service.findByid(0L);
    }


    @Test(expected = EntityNotFoundException.class)
    public void can_not_delete_entity_not_exist(){
        service.delete(0L);
    }

    @Test
    public void can_delete_exist_entity(){
        testTeam.setId(null);
        testTeam.setName("silinecek");
        Team team = service.save(testTeam);
        service.delete(team.getId());
    }

    @Test(expected = InvalidRequestException.class)
    public void team_name_must_be_unique(){
        service.save(testTeam);
        testTeam.setId(null);
        service.save(testTeam);
    }

}