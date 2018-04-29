package com.assignment.countryfinderrest.utility;


import com.assignment.countryfinderrest.config.CountryFinderException;
import com.assignment.countryfinderrest.dao.CountryFinderDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableCreationUtility {
    private static final Logger log = LoggerFactory.getLogger(TableCreationUtility.class);

    @Autowired
    CountryFinderDao countryFinderDao;

    public void createTable() throws CountryFinderException {
        log.info("started country table creation in H2");
        countryFinderDao.createTable();


    }
}
