package com.aleyla.footballTeam.repository;

import com.aleyla.footballTeam.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    Contract findByContractCode(String contractCode);

    @Query(value = "select distinct cont from Contract cont where cont.playerId=:playerId and :now between cont.start and cont.end")
    List<Contract> findByPlayerId(Long playerId, LocalDate now);

    @Query(value = "select distinct cont.teamId from Contract cont where cont.playerId=:playerId")
    Iterable<Long> findAllTeamIdsByPlayerId(@Param("playerId") Long playerId);

    @Query(value = "select distinct cont.playerId from Contract cont where cont.teamId=:teamId and ((cont.start between :startDate and :endDate) or (cont.end between :startDate and :endDate))")
    Iterable<Long> findAllPlayerIdsByTeamIdAndContractDate(@Param("teamId") Long teamId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
