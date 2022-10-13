package uk.gov.companieshouse.bankruptofficersearch.api.model.response;

import org.springframework.stereotype.Component;

/**
 * Filters to apply when searching for bankrupt officers
 */
@Component
public class ScottishBankruptOfficerSearchFiltersEntity {

    private String forename1;
    private String surname;
    private String alias;
    private String dateOfBirth;
    private String postcode;


    public String getForename1() {
        return forename1;
    }

    public void setForename1(String forename1) {
        this.forename1 = forename1;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}

