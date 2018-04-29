package com.assignment.countryfinderrest.config;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

import com.assignment.countryfinderrest.model.CountryResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsxBuilder {

    private Workbook workbook;
    private Sheet sheet = null;
    private Row row = null;
    private int nextRowIdx = 0;
    private Set<StyleAttribute> rowStyleAttributes;
    private int nextColumnIdx = 0;
    private Map<Set<StyleAttribute>, CellStyle> styleBank = new HashMap<Set<StyleAttribute>, CellStyle>();
    public XlsxBuilder() {
        workbook = new XSSFWorkbook();
    }
    public XlsxBuilder startSheet(String name) {
        sheet = workbook.createSheet(name);
        nextRowIdx = 0;
        nextColumnIdx = 0;
        rowStyleAttributes = new HashSet<StyleAttribute>();
        return this;
    }
    public XlsxBuilder setAutoSizeColumn(int idx) {
        sheet.autoSizeColumn(idx);
        return this;
    }
    public XlsxBuilder setColumnSize(int idx, int m) {
        sheet.setColumnWidth(idx, (m + 1) * 256);
        return this;
    }
    public XlsxBuilder startRow() {
        row = sheet.createRow(nextRowIdx);
        nextRowIdx = nextRowIdx + 1;
        nextColumnIdx = 0;
        rowStyleAttributes = new HashSet<StyleAttribute>();
        return this;
    }
    public XlsxBuilder setRowThinTopBorder() {
      //  ValidationUtils.guardEquals(0, nextColumnIdx, "must be called before inserting columns");
        row.setRowStyle(getCellStyle(StyleAttribute.THIN_TOP_BORDER));
        rowStyleAttributes.add(StyleAttribute.THIN_TOP_BORDER);
        return this;
    }

    public XlsxBuilder setRowThickTopBorder() {
      //  ValidationUtils.guardEquals(0, nextColumnIdx, "must be called before inserting columns");
        row.setRowStyle(getCellStyle(StyleAttribute.THICK_TOP_BORDER));
        rowStyleAttributes.add(StyleAttribute.THICK_TOP_BORDER);
        return this;
    }

    public XlsxBuilder setRowThinBottomBorder() {
      //  ValidationUtils.guardEquals(0, nextColumnIdx, "must be called before inserting columns");
        row.setRowStyle(getCellStyle(StyleAttribute.THIN_BOTTOM_BORDER));
        rowStyleAttributes.add(StyleAttribute.THIN_BOTTOM_BORDER);
        return this;
    }

    public XlsxBuilder setRowThickBottomBorder() {
        //ValidationUtils.guardEquals(0, nextColumnIdx, "must be called before inserting columns");
        row.setRowStyle(getCellStyle(StyleAttribute.THICK_BOTTOM_BORDER));
        rowStyleAttributes.add(StyleAttribute.THICK_BOTTOM_BORDER);
        return this;
    }

    public XlsxBuilder setRowTitleHeight() {
       // ValidationUtils.guardEquals(0, nextColumnIdx, "must be called before inserting columns");
        row.setHeightInPoints(30);
        return this;
    }

    public XlsxBuilder addTitleTextColumn(String text) {
        Cell cell = row.createCell(nextColumnIdx);
        CellStyle style = getCellStyle(StyleAttribute.TITLE_SIZE, StyleAttribute.BOLD);
        cell.setCellStyle(style);
        cell.setCellValue(StringUtils.stripToEmpty(text));
        nextColumnIdx = nextColumnIdx + 1;
        return this;
    }

    public XlsxBuilder addTextLeftAlignedColumn(String text) {
        Cell cell = row.createCell(nextColumnIdx);
        CellStyle style = getCellStyle(StyleAttribute.ALIGN_LEFT);
        cell.setCellStyle(style);
        cell.setCellValue(StringUtils.stripToEmpty(text));
        nextColumnIdx = nextColumnIdx + 1;
        return this;
    }

    public XlsxBuilder addTextCenterAlignedColumn(String text) {
        Cell cell = row.createCell(nextColumnIdx);
        CellStyle style = getCellStyle(StyleAttribute.ALIGN_CENTER);
        cell.setCellStyle(style);
        cell.setCellValue(StringUtils.stripToEmpty(text));
        nextColumnIdx = nextColumnIdx + 1;
        return this;
    }
    public XlsxBuilder addDoubleCenterAlignedColumn(double val) {
        Cell cell = row.createCell(nextColumnIdx);
        CellStyle style = getCellStyle(StyleAttribute.ALIGN_CENTER);
        cell.setCellStyle(style);
        cell.setCellValue(val);
        nextColumnIdx = nextColumnIdx + 1;
        return this;
    }

    public XlsxBuilder addBoldTextLeftAlignedColumn(String text) {
        Cell cell = row.createCell(nextColumnIdx);
        CellStyle style = getCellStyle(StyleAttribute.ALIGN_LEFT, StyleAttribute.BOLD);
        cell.setCellStyle(style);
        cell.setCellValue(StringUtils.stripToEmpty(text));
        nextColumnIdx = nextColumnIdx + 1;
        return this;
    }
    public XlsxBuilder addBoldTextCenterAlignedColumn(String text) {
        Cell cell = row.createCell(nextColumnIdx);
        CellStyle style = getCellStyle(StyleAttribute.ALIGN_CENTER, StyleAttribute.BOLD);
        cell.setCellStyle(style);
        cell.setCellValue(StringUtils.stripToEmpty(text));
        nextColumnIdx = nextColumnIdx + 1;
        return this;
    }
    public byte[] build() {
        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            workbook.write(bos);
            bos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(bos);
        }
        return bos.toByteArray();
    }

    private CellStyle getCellStyle(StyleAttribute... attrs) {
        Set<StyleAttribute> allattrs = new HashSet<StyleAttribute>();
        allattrs.addAll(rowStyleAttributes);
        allattrs.addAll(Arrays.asList(attrs));
        if (styleBank.containsKey(allattrs)) {
            return styleBank.get(allattrs);
        }
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        style.setFont(font);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        for (StyleAttribute attr : allattrs) {
            if (attr.equals(StyleAttribute.TITLE_SIZE)) {
                font.setFontHeightInPoints((short) 18);
            }
            else if (attr.equals(StyleAttribute.BOLD)) {
                font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            }
            else if (attr.equals(StyleAttribute.THIN_TOP_BORDER)) {
                style.setBorderTop(CellStyle.BORDER_THIN);
            }
            else if (attr.equals(StyleAttribute.THIN_BOTTOM_BORDER)) {
                style.setBorderBottom(CellStyle.BORDER_THIN);
            }
            else if (attr.equals(StyleAttribute.THICK_TOP_BORDER)) {
                style.setBorderTop(CellStyle.BORDER_THICK);
            }
            else if (attr.equals(StyleAttribute.THICK_BOTTOM_BORDER)) {
                style.setBorderBottom(CellStyle.BORDER_THICK);
            }
            else if (attr.equals(StyleAttribute.ALIGN_LEFT)) {
                style.setAlignment(CellStyle.ALIGN_LEFT);
            }
            else if (attr.equals(StyleAttribute.ALIGN_CENTER)) {
                style.setAlignment(CellStyle.ALIGN_CENTER);
            }
            else {
                throw new RuntimeException("unknown cell style attribute: " + attr);
            }
        }
        styleBank.put(allattrs, style);
        return style;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public XlsxBuilder callAddTextLeftAlignedColumn(List<CountryResponse> countryResponseList){
        for(CountryResponse countryResponse : countryResponseList) {
            addTextLeftAlignedColumn(countryResponse.getName());
            addTextLeftAlignedColumn(countryResponse.getPopulation().toString());
            startRow();
        }
        return this;
    }
    public XlsxBuilder callAddTextLeftAlignedColumnForCurrency(List<CountryResponse> currencyList) {
        for(CountryResponse countryResponse : currencyList) {
            addTextLeftAlignedColumn(countryResponse.getCurrency_code());
            addTextLeftAlignedColumn(countryResponse.getCount().toString());
            startRow();
        }
        return this;
    }
    private static enum StyleAttribute {
        THIN_TOP_BORDER,
        THIN_BOTTOM_BORDER,
        THICK_TOP_BORDER,
        THICK_BOTTOM_BORDER,
        TITLE_SIZE,
        BOLD,
        ALIGN_LEFT,
        ALIGN_CENTER
    };
}
