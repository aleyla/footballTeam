package com.aleyla.footballTeam.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contract")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_contract")
    @SequenceGenerator(name = "seq_contract", sequenceName = "seq_contract", allocationSize = 1)
    private Long id;

    @Column(name = "CONTRACT_CODE")
    private String contractCode;

    @Column(name = "START_DATE", columnDefinition = "DATE")
    private LocalDate start;
    @Column(name = "END_DATE", columnDefinition = "DATE")
    private LocalDate end;

    @Column(name = "CURRENCY_CODE")
    private String currencyCode;

    @Column(name = "TEAM_COMMISSION")
    private BigDecimal teamCommission;

    @Column(name = "CONTRACT_PRICE")
    private BigDecimal contractPrice;

    @Column(name = "TRANSFER_FEE")
    private BigDecimal transferFee;

    @Column(name = "TEAM_ID")
    private Long teamId;

    @Column(name = "PLAYER_ID")
    private Long playerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getTeamCommission() {
        return teamCommission;
    }

    public void setTeamCommission(BigDecimal teamCommission) {
        this.teamCommission = teamCommission;
    }

    public BigDecimal getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(BigDecimal contractPrice) {
        this.contractPrice = contractPrice;
    }

    public BigDecimal getTransferFee() {
        return transferFee;
    }

    public void setTransferFee(BigDecimal transferFee) {
        this.transferFee = transferFee;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }
}
