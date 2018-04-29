package com.assignment.countryfinderrest.service;

import com.assignment.countryfinderrest.config.Constants;
import com.assignment.countryfinderrest.config.CountryFinderException;
import com.assignment.countryfinderrest.config.XlsxBuilder;
import com.assignment.countryfinderrest.dao.CountryFinderDao;
import com.assignment.countryfinderrest.model.CountryRequest;
import com.assignment.countryfinderrest.model.CountryResponse;
import com.assignment.countryfinderrest.utility.CaseConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class CountryFinderService {

    @Autowired
    CountryFinderDao countryFinderDao ;


    public List<CountryResponse> getCountryList(CountryRequest countryRequest) throws CountryFinderException {

        return countryFinderDao.getCountryList(CaseConverter.camelCase(countryRequest.getSearchText()));
    }

    public List<CountryResponse> getCountryWithMaxPopulation() throws CountryFinderException{
        return countryFinderDao.getCountryWithMaxPopulation();
    }

    public List<CountryResponse> getCountryWithMinPopulation() throws CountryFinderException {
        return countryFinderDao.getCountryWithMinPopulation();
    }

    public List<CountryResponse> getMaxCurrenciesUsed() throws CountryFinderException{
        return countryFinderDao.getMaxCurrenciesUsed();
    }

    public void generateReport(HttpServletResponse httpServletResponse, List<CountryResponse> highPopulationList,
                               List<CountryResponse> lowPopulationList,  List<CountryResponse> cuurencyList) {
        byte[] report = new XlsxBuilder().
                startSheet(Constants.reportHeader).
                startRow().
                setRowTitleHeight().
                addTitleTextColumn(Constants.reportHeader).
                startRow().
                setRowTitleHeight().
                setRowThinBottomBorder().
                setRowThinTopBorder().
                addBoldTextLeftAlignedColumn(Constants.COUNTRIES_WITH_MAX_POP).
                startRow().
                startRow().
                setRowTitleHeight().
                setRowThickTopBorder().
                setRowThickBottomBorder().
                addBoldTextCenterAlignedColumn("Country Name").
                addBoldTextCenterAlignedColumn("Population").
                startRow().
                callAddTextLeftAlignedColumn(highPopulationList).
                setRowThinTopBorder().
                startRow().
                setRowTitleHeight().
                setRowThinBottomBorder().
                setRowThinTopBorder().
                addBoldTextLeftAlignedColumn(Constants.COUNTRIES_WITH_MIN_POP).
                startRow().
                callAddTextLeftAlignedColumn(lowPopulationList).
                setRowThinTopBorder().
                startRow().
                startRow().
                setRowTitleHeight().
                setRowThinBottomBorder().
                setRowThinTopBorder().
                addBoldTextLeftAlignedColumn(Constants.TOP_CURRENCIES).
                startRow().
                startRow().
                setRowTitleHeight().
                setRowThickTopBorder().
                setRowThickBottomBorder().
                addBoldTextCenterAlignedColumn("Currency").
                addBoldTextCenterAlignedColumn("Count").
                startRow().
                callAddTextLeftAlignedColumnForCurrency(cuurencyList).
                setRowThinTopBorder().
                /* setColumnSize(0, 13).
                 setAutoSizeColumn(1).
                 setAutoSizeColumn(2).*/
                        build();


        try {
            ServletOutputStream op = httpServletResponse.getOutputStream();
            httpServletResponse.setContentType("application/vnd.msexcel");
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"" + "Report.xlsx");
            op.write(report);
            op.flush();
            op.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
