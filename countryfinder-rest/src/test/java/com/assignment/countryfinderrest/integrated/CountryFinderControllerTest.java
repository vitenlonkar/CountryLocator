package com.assignment.countryfinderrest.integrated;

import com.assignment.countryfinderrest.config.CountryFinderException;
import com.assignment.countryfinderrest.controller.CountryFinderController;
import com.assignment.countryfinderrest.model.CountryReportDataResponse;
import com.assignment.countryfinderrest.model.CountryRequest;
import com.assignment.countryfinderrest.utility.TableCreationUtility;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.ServletResponse;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryFinderControllerTest {

    @Autowired
    CountryFinderController countryFinderController ;

    @Autowired
    TableCreationUtility tableCreationUtility;

    @MockBean
    ServletResponse servletResponse ;

    @Before
    public void testCreateTable() throws CountryFinderException {
        tableCreationUtility.createTable();
    }

    @Test
    public void testGetCountryListUsingCountryName() throws CountryFinderException {
        CountryRequest countryRequest =  new CountryRequest();
        countryRequest.setSearchText("Ind");
        Assert.assertThat(countryFinderController.getCountryList(countryRequest).size(),is(3) );

    }

    @Test
    public void testGetCountryListUsingCountryCode() throws CountryFinderException{
        CountryRequest countryRequest =  new CountryRequest();
        countryRequest.setSearchText("356");
        Assert.assertThat(countryFinderController.getCountryList(countryRequest).size(),is(1) );
    }

    @Test
    public void testGetReportData() throws CountryFinderException{
        assertNotNull(countryFinderController.getReportsData());
    }
}
