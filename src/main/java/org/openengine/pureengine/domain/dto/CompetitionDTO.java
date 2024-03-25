package org.openengine.pureengine.domain.dto;

public class CompetitionDTO {

    private String name;
    private String country;
    private String countryDemonym;

    public CompetitionDTO(String name, String country, String countryDemonym) {
        this.name = name;
        this.country = country;
        this.countryDemonym = countryDemonym;
    }

    public String getName() {
        return name;
    }

    public String getCountryDemonym() {
        return countryDemonym;
    }
}
