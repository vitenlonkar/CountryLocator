import { Component, OnInit } from '@angular/core';
import {CountryfinderService} from "./countryfinder.service";
import {Observable} from  "rxjs/Rx";
import {TypeaheadMatch} from "ngx-bootstrap";
import {of} from "rxjs/observable/of";
import {CountryDropDownInput} from "../model/countrydropdowninput";
import {GridOptions} from "ag-grid";
import {CountryDetails} from "../model/countrydetails";
import {environment} from "../../environments/environment";





@Component({
  selector: 'app-root',
  templateUrl: './countryfinder.component.html',
  styleUrls: ['./countryfinder.component.css']
})
export class CountryFinderComponent implements OnInit{
  countryList: any ;
  formatter = (x:{name: string}) => x.name ;
  searching: boolean ;
  searchFailed: boolean;
  hideSearchingWhenUnsubscribed = new Observable(() => () => this.searching = false);
  gridOptions: GridOptions ;
  gridOptionsForMaxPop: GridOptions ;
  gridOptionsForMinPop: GridOptions ;
  gridOptionsForCurrencies: GridOptions ;
  colDefs;
  colDefsForPop;
  colDefForCurrency;
  rowData;
  rowDataForPop;
  rowDataCurrency;
  apiURL = environment.apiUrl;
  idx: number;
  showQueryMode: boolean;
  showReportMode: boolean;
  formData: CountryDetails



  constructor(private countryFinderService: CountryfinderService){

  }

  ngOnInit(){
    this.initializeGrid() ;
    var countryDetails = new CountryDetails('','','','','','',
      '','','','','','','','','',
      '','','','');
    this.formData = countryDetails ;
  }

  initializeGrid() {
    this.colDefs = [
      {headerName: '2DigitCode' ,field:'alpha_2' },
      {headerName: '3DigitCode' ,field:'alpha_3' },
      {headerName: 'Country Name' ,field:'name' },
      {headerName: 'Capital' ,field:'capital' },
      {headerName: 'Population' ,field:'population' },
      {headerName: 'Continent' ,field:'continent' },
      {headerName: 'Currency' ,field:'currency_name' },
      {headerName: 'Phone' ,field:'phone' },
      {headerName: 'Spoken Language Codes' ,field:'languages' },
      {headerName: 'Fips Code' ,field:'eqivalent_fips_code' },
      {headerName: 'Fips' ,field:'fips' },
      {headerName: 'Geo Name' ,field:'geoname_id' },
      {headerName: 'Neighbour Country Codes' ,field:'neighbours' },
      /*{headerName: 'Country Name' ,field:'postal_code_format' },
      {headerName: 'Country Name' ,field:'postal_code_regex' },*/
      {headerName: 'TLD' ,field:'tld' }
    ];
    this.rowData=[];
    this.gridOptions = {
      rowData:this.rowData,
      columnDefs:this.colDefs,
      rowHeight:30,
      suppressRowClickSelection: true,
      overlayLoadingTemplate:'<span class = "ag-overlay-loading-center"> Loading</span>'

    }

    this.colDefsForPop = [
      {headerName: 'Country Name' ,field:'name' },
      {headerName: 'Population' ,field:'population' },
    ];

    this.rowDataForPop=[];
    this.gridOptionsForMaxPop = {
      rowData:this.rowDataForPop,
      columnDefs:this.colDefsForPop,
      rowHeight:30,
      suppressRowClickSelection: true,
      overlayLoadingTemplate:'<span class = "ag-overlay-loading-center"> Loading</span>'

    }

    this.gridOptionsForMinPop = {
      rowData:this.rowDataForPop,
      columnDefs:this.colDefsForPop,
      rowHeight:30,
      suppressRowClickSelection: true,
      overlayLoadingTemplate:'<span class = "ag-overlay-loading-center"> Loading</span>'

    }

    this.colDefForCurrency = [
      {headerName: 'Currency' ,field:'currency_code' },
      {headerName: 'Count' ,field:'count' },
    ];
    this.rowDataCurrency = [];
    this.gridOptionsForCurrencies = {
      rowData:this.rowDataCurrency,
      columnDefs:this.colDefForCurrency,
      rowHeight:30,
      suppressRowClickSelection: true,
      overlayLoadingTemplate:'<span class = "ag-overlay-loading-center"> Loading</span>'

    }

  }

  searchOnTypeAhead = (text$: Observable<string>) =>
    text$
      .debounceTime(300)
      .distinctUntilChanged()
      .do(() => this.searching = true)
      .switchMap(term =>
        this.countryFinderService.getCountryList(term, new CountryDropDownInput(term))
          .do(() => this.searchFailed = false)
          .catch(() => {
            this.searchFailed = true;
            return of([]);
          }))
      .do(() => this.searching = false)
      .merge(this.hideSearchingWhenUnsubscribed);

  OnCountrySelect = (e: TypeaheadMatch) =>{
    var countryDetails = new CountryDetails(e.item.alpha_2,e.item.alpha_3,e.item.area,e.item.capital,e.item.continent,e.item.currency_code,
    e.item.currency_name,e.item.eqivalent_fips_code,e.item.fips,e.item.geoname_id,e.item.languages,e.item.name,e.item.neighbours,e.item.numeric,e.item.phone,
    e.item.population,e.item.postal_code_format,e.item.postal_code_regex,e.item.tld);
    this.formData = countryDetails ;
    var countryDetailsGridObject = [];
    debugger;
    countryDetailsGridObject.push(countryDetails);
    //this.gridOptions.api.setRowData(countryDetailsGridObject);
  }

  onSelectionChange(mode: string): void {
    if(mode === 'queryMode'){
       this.showQueryMode = true ;
       this.showReportMode = false ;
      var countryDetails = new CountryDetails('','','','','','',
        '','','','','','','','','',
        '','','','');
      this.formData = countryDetails ;
    } else {
      this.showQueryMode = false ;
      this.showReportMode = true ;
      var countryDetails = new CountryDetails('','','','','','',
        '','','','','','','','','',
        '','','','');
      this.formData = countryDetails ;
      this.countryFinderService.getCountryReportData()
        .subscribe((data) => {
          debugger;
            var maxPopObject = data.maxCountryPopulationList;
            this.gridOptionsForMaxPop.api.setRowData(maxPopObject);

          var minPopObject = data.minCountryPopulationList;
          this.gridOptionsForMinPop.api.setRowData(minPopObject);

          var currencyObject = data.currencyList;
          this.gridOptionsForCurrencies.api.setRowData(currencyObject);
          });

    }
  }

}


