package com.aleyla.footballTeam.repository;

import com.aleyla.footballTeam.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    Contract findByContractCode(String contractCode);

    @Transactional
    @Query(value = "select distinct cont.teamId from Contract cont where cont.playerId=:playerId")
    List<Long> findAllTeamIdsByPlayerId(@Param("playerId")Long playerId);

    @Transactional
    @Query(value = "select distinct cont.playerId from Contract cont where cont.teamId=:teamId and cont.start>=:startDate and cont.end<=:endDate")
    List<Long> findAllPlayerIdsByTeamIdAndContractDate(@Param("teamId")Long teamId, @Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate);
}
