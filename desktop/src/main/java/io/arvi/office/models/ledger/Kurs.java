package io.arvi.office.models.ledger;

import java.math.BigDecimal;

import io.arvi.office.models.Identity;

public class Kurs extends Identity {
    
    private String kursId;
    private String description;
    private String symbol;
    private BigDecimal value;
    private BigDecimal exchange;
    private boolean active;

    public String getKursId() {
        return kursId;
    }

    public void setKursId(String kursId) {
        this.kursId = kursId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getExchange() {
        return exchange;
    }

    public void setExchange(BigDecimal exchange) {
        this.exchange = exchange;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}