package com.aleyla.footballTeam.repository;

import com.aleyla.footballTeam.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    List<Contract> findAllByTeamId(Long teamId);

    List<Contract> findAllByPlayerId(Long playerId);

}
