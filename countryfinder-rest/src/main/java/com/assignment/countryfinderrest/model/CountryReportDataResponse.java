package com.assignment.countryfinderrest.model;

import java.util.List;

public class CountryReportDataResponse {

    private List<CountryResponse> maxCountryPopulationList ;
    private List<CountryResponse> minCountryPopulationList ;
    private List<CountryResponse> currencyList ;

    public List<CountryResponse> getMaxCountryPopulationList() {
        return maxCountryPopulationList;
    }

    public void setMaxCountryPopulationList(List<CountryResponse> maxCountryPopulationList) {
        this.maxCountryPopulationList = maxCountryPopulationList;
    }

    public List<CountryResponse> getMinCountryPopulationList() {
        return minCountryPopulationList;
    }

    public void setMinCountryPopulationList(List<CountryResponse> minCountryPopulationList) {
        this.minCountryPopulationList = minCountryPopulationList;
    }

    public List<CountryResponse> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<CountryResponse> currencyList) {
        this.currencyList = currencyList;
    }
}
