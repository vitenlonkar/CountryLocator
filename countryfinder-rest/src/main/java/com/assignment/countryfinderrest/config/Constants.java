package com.assignment.countryfinderrest.config;

public interface Constants {

  String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS assignment.COUNTRY1 " +
            "( alpha_2 VARCHAR(40), alpha_3 VARCHAR(40), area  FLOAT, capital VARCHAR(40), continent VARCHAR(40), " +
            "currency_code VARCHAR(40), currency_name VARCHAR(40), eqivalent_fips_code VARCHAR(40), fips VARCHAR(40), " +
            "geoname_id VARCHAR(40), languages VARCHAR(100), name VARCHAR(100), neighbours VARCHAR(100),numeric VARCHAR(40) ," +
            "phone VARCHAR(40),population INT,postal_code_format VARCHAR(100),postal_code_regex VARCHAR(150),tld VARCHAR(40)" +
            ")AS SELECT alpha_2 , alpha_3, area  , capital , continent , currency_code , currency_name , " +
            "eqivalent_fips_code , fips , geoname_id , languages , name , neighbours ,numeric ,phone ,population ," +
            "postal_code_format ,postal_code_regex ,tld " +
            "FROM CSVREAD('classpath:/templates/countries.csv');";

  String reportHeader = "Country Details";
  String COUNTRIES_WITH_MAX_POP = "Top 10 countries having High population";
  String COUNTRIES_WITH_MIN_POP = "Top 10 countries having Lowest population";
  String TOP_CURRENCIES ="Top 10 used currencies";



}
