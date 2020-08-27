package io.arvi.office.models.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import io.arvi.office.models.Identity;

public class Company extends Identity {

    private String name;
    private String description;
    private int taxYear;
    private String taxId;
    private String taxResponsibleId;
    private LocalDate taxResponsibleDate;

    List<Branch> branches = new ArrayList<>();

    public String getCompanyName() {
        return name;
    }

    public void setCompanyName(String companyName) {
        this.name = companyName;
    }

    public String getCompanyDescription() {
        return description;
    }

    public void setCompanyDescription(String companyDescription) {
        this.description = companyDescription;
    }

    public int getTaxYear() {
        return taxYear;
    }

    public void setTaxYear(int taxYear) {
        this.taxYear = taxYear;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getTaxResponsibleId() {
        return taxResponsibleId;
    }

    public void setTaxResponsibleId(String taxResponsibleId) {
        this.taxResponsibleId = taxResponsibleId;
    }

    public LocalDate getTaxResponsibleDate() {
        return taxResponsibleDate;
    }

    public void setTaxResponsibleDate(LocalDate taxResponsibleDate) {
        this.taxResponsibleDate = taxResponsibleDate;
    }

    public List<Branch> getBranches() {
        return branches;
    }
}