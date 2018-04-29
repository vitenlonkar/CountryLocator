package com.assignment.countryfinderrest.config;

import com.assignment.countryfinderrest.model.CountryResponse;

import java.util.Map;

public class ResponseCreator {

    public static CountryResponse createResponseObject(Map<String, Object> rowMap) {
        CountryResponse countryResponse = new CountryResponse();
        countryResponse.setAlpha_2((String) rowMap.get("alpha_2"));
        countryResponse.setAlpha_3((String) rowMap.get("alpha_3"));
        countryResponse.setArea((Double) rowMap.get("area"));
        countryResponse.setCapital((String) rowMap.get("capital"));
        countryResponse.setContinent((String) rowMap.get("continent"));
        countryResponse.setCurrency_code((String) rowMap.get("currency_code"));
        countryResponse.setCurrency_name((String) rowMap.get("currency_name"));
        countryResponse.setEqivalent_fips_code((String) rowMap.get("eqivalent_fips_code"));
        countryResponse.setFips((String) rowMap.get("fips"));
        countryResponse.setGeoname_id((String) rowMap.get("geoname_id"));
        countryResponse.setLanguages((String) rowMap.get("languages"));
        countryResponse.setName((String) rowMap.get("name"));
        countryResponse.setNeighbours((String) rowMap.get("neighbours"));
        countryResponse.setNumeric((String) rowMap.get("numeric"));
        countryResponse.setPhone((String) rowMap.get("phone"));
        countryResponse.setPopulation((Integer) rowMap.get("population"));
        countryResponse.setTld((String) rowMap.get("tld"));
        countryResponse.setPostal_code_format((String) rowMap.get("postal_code_format"));
        countryResponse.setPostal_code_regex((String) rowMap.get("postal_code_regex"));
        return countryResponse;
    }

    public static CountryResponse createResponseObjectForCurrencyList(Map<String,Object> rowMap) {
        CountryResponse countryResponse = new CountryResponse();
        countryResponse.setCount((Long) rowMap.get("count"));
        countryResponse.setCurrency_code((String) rowMap.get("currency_code"));

        return countryResponse;
    }
}
