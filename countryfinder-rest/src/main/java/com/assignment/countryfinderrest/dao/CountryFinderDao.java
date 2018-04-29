package com.assignment.countryfinderrest.dao;

import com.assignment.countryfinderrest.config.Constants;
import com.assignment.countryfinderrest.config.CountryFinderException;
import com.assignment.countryfinderrest.config.ResponseCreator;
import com.assignment.countryfinderrest.model.CountryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CountryFinderDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private static Logger log = LoggerFactory.getLogger(CountryFinderDao.class);

    public void createTable() throws CountryFinderException {
        log.info("table creation started");
        try {
            jdbcTemplate.execute("CREATE SCHEMA IF Not EXISTS assignment;");
            jdbcTemplate.execute(Constants.CREATE_TABLE_QUERY);
        } catch (Exception e) {
            throw new CountryFinderException(e.getMessage(), "Error Occurred in table creation", e) ;
        }

        log.info("table created");

    }

    public List<CountryResponse> getCountryList(String searchText) throws CountryFinderException{
        log.info("Inside getCountryList Dao");
        List<CountryResponse> countryResponseList = new ArrayList<>();
        String query = "SELECT * FROM assignment.country1 where NAME like '%" + searchText + "%'" +
                "UNION\n" +
                "SELECT * FROM assignment.country1 where numeric = '" + searchText+"'" ;
        try {
            List<Map<String, Object>> retList = jdbcTemplate.queryForList(query);
            if (retList == null) {
                return countryResponseList;
            } else {
                for (Map<String, Object> rowMap : retList) {
                    countryResponseList.add(ResponseCreator.createResponseObject(rowMap));
                }
            }
        } catch (Exception e) {
            throw new CountryFinderException(e.getMessage(), "Error Occurred while fetching Country List", e);
        }

        return countryResponseList;

    }

    public List<CountryResponse> getCountryWithMaxPopulation() throws CountryFinderException{
        {
            log.info("Inside getCountryWithMaxPopulation Dao");
            List<CountryResponse> countryResponseList = new ArrayList<>();
            String query = "select * from ASSIGNMENT.COUNTRY1 order by population desc limit 10 ;";
            try {
                List<Map<String, Object>> retList = jdbcTemplate.queryForList(query);
                if (retList == null) {
                    return countryResponseList;
                } else {
                    for (Map<String, Object> rowMap : retList) {
                        countryResponseList.add(ResponseCreator.createResponseObject(rowMap));
                    }
                }
            } catch (Exception e) {
                throw new CountryFinderException(e.getMessage(), "Error Occurred while fetching Max population List", e);
            }

            return countryResponseList;
        }
    }


    public List<CountryResponse> getCountryWithMinPopulation() throws CountryFinderException{
        {
            log.info("Inside getCountryWithMinPopulation Dao");
            List<CountryResponse> countryResponseList = new ArrayList<>();
            String query = "select * from ASSIGNMENT.COUNTRY1 order by population  limit 10 ;";
            try {
                List<Map<String, Object>> retList = jdbcTemplate.queryForList(query);
                if (retList == null) {
                    return countryResponseList;
                } else {
                    for (Map<String, Object> rowMap : retList) {
                        countryResponseList.add(ResponseCreator.createResponseObject(rowMap));
                    }
                }
            } catch (Exception e) {
                throw new CountryFinderException(e.getMessage(), "Error Occurred while fetching Min population List", e) ;
            }

            return countryResponseList;
        }
    }

    public List<CountryResponse> getMaxCurrenciesUsed() throws CountryFinderException{
        {
            log.info("Inside getMaxCurrenciesUsed Dao");
            List<CountryResponse> countryResponseList = new ArrayList<>();
            String query = "SELECT CURRENCY_CODE, COUNT(*) As count " +
                    "FROM ASSIGNMENT.COUNTRY1 " +
                    "GROUP BY CURRENCY_CODE " +
                    "ORDER BY 2 DESC limit 10;";
            try {
                List<Map<String, Object>> retList = jdbcTemplate.queryForList(query);
                if (retList == null) {
                    return countryResponseList;
                } else {
                    for (Map<String, Object> rowMap : retList) {
                        countryResponseList.add(ResponseCreator.createResponseObjectForCurrencyList(rowMap));
                    }
                }
            } catch (Exception e) {
                throw new CountryFinderException(e.getMessage(), "Error Occurred while fetching Currencies List", e) ;
            }

            return countryResponseList;
        }
    }

}
