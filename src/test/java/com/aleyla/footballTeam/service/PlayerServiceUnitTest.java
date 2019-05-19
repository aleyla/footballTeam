package com.aleyla.footballTeam.service;

import com.aleyla.footballTeam.entity.Player;
import com.aleyla.footballTeam.exception.InvalidRequestException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PlayerServiceUnitTest {

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

    @Test(expected = InvalidRequestException.class)
    public void can_not_save_null_player() {
        PlayerService service = new PlayerService(null, null);
        service.save(null);
    }

    @Test(expected = InvalidRequestException.class)
    public void can_not_save_null_player_name() {
        PlayerService service = new PlayerService(null, null);
        testPlayer.setName(null);
        service.save(testPlayer);
    }

    @Test(expected = InvalidRequestException.class)
    public void can_not_save_null_player_surname() {
        PlayerService service = new PlayerService(null, null);
        testPlayer.setSurname(null);
        service.save(testPlayer);
    }

    @Test(expected = InvalidRequestException.class)
    public void can_not_save_null_player_birthday() {
        PlayerService service = new PlayerService(null, null);
        testPlayer.setBirthday(null);
        service.save(testPlayer);
    }

    @Test(expected = InvalidRequestException.class)
    public void can_not_update_player_id_and_id_not_match() {
        PlayerService service = new PlayerService(null, null);
        service.update(0L, testPlayer);
    }

}