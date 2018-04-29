package com.assignment.countryfinderrest.controller;

import com.assignment.countryfinderrest.config.CountryFinderException;
import com.assignment.countryfinderrest.model.CountryReportDataResponse;
import com.assignment.countryfinderrest.model.CountryRequest;
import com.assignment.countryfinderrest.model.CountryResponse;
import com.assignment.countryfinderrest.service.CountryFinderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryFinderController {

    @Autowired
    CountryFinderService countryFinderService;


    @RequestMapping(value = "/api/getCountryList", method = RequestMethod.POST)
    @CrossOrigin(value = "*")
    public List<CountryResponse> getCountryList(@RequestBody CountryRequest countryRequest) throws CountryFinderException {
        List<CountryResponse> responseCountryList = new ArrayList<>();
        if (StringUtils.isNotBlank(countryRequest.getSearchText()) && StringUtils.isNotEmpty(countryRequest.getSearchText())) {
            responseCountryList = countryFinderService.getCountryList(countryRequest);
        }
        return responseCountryList;
    }

    @RequestMapping(value = "/api/getReports", method = RequestMethod.GET)
    @CrossOrigin(value = "*")
    public void getReports(ServletResponse response) throws CountryFinderException{
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        List<CountryResponse> highPopulationList = countryFinderService.getCountryWithMaxPopulation();
        List<CountryResponse> lowPopulationList = countryFinderService.getCountryWithMinPopulation();
        List<CountryResponse> curencyList = countryFinderService.getMaxCurrenciesUsed();
        countryFinderService.generateReport(httpServletResponse, highPopulationList, lowPopulationList, curencyList);
    }

    @RequestMapping(value = "/api/getReportsData", method = RequestMethod.GET)
    @CrossOrigin(value = "*")
    public CountryReportDataResponse getReportsData() throws CountryFinderException{
        CountryReportDataResponse countryReportDataResponse = new CountryReportDataResponse();
        countryReportDataResponse.setMaxCountryPopulationList(countryFinderService.getCountryWithMaxPopulation());
        countryReportDataResponse.setMinCountryPopulationList(countryFinderService.getCountryWithMinPopulation());
        countryReportDataResponse.setCurrencyList(countryFinderService.getMaxCurrenciesUsed());
        return countryReportDataResponse ;

    }


}
