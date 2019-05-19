package com.aleyla.footballTeam.service;

import com.aleyla.footballTeam.entity.Player;
import com.aleyla.footballTeam.entity.Team;
import com.aleyla.footballTeam.exception.EntityNotFoundException;
import com.aleyla.footballTeam.exception.InvalidRequestException;
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
public class PlayerServiceIntegrationTest {

    @Autowired
    public PlayerService service;

    public Player testPlayer = new Player();

    @Before
    public void setUp() {
        testPlayer.setId(1L);
        testPlayer.setName("ali");
        testPlayer.setSurname("veli");
        testPlayer.setBirthday(LocalDate.of(1988, 4, 5));
        testPlayer.setExperienceDuration(BigDecimal.TEN);
        testPlayer.setIdentityNumber("1234");
    }


    @Test
    public void can_save(){
        testPlayer.setId(null);
        testPlayer.setName("saveTestName");
        Player player = service.save(testPlayer);
        assertThat(player.getName(), equalTo(player.getName()));
    }

    @Test
    public void can_update_when_parametter_id_null(){
        testPlayer.setId(null);
        service.update(1L, testPlayer);
        Player player = service.findByid(1L);
        assertThat(player.getName(), equalTo(testPlayer.getName()));
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
        testPlayer.setId(null);
        testPlayer.setName("silinecek");
        Player player = service.save(testPlayer);
        service.delete(player.getId());
    }


}