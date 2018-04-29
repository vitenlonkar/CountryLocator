package com.assignment.countryfinderrest.model;

public class CountryRequest {




    private String searchText ;

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public CountryRequest(String searchText) {
        super();
        this.searchText = searchText ;
    }

    public CountryRequest() {

    }
}
