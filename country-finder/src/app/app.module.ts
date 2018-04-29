import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { AppComponent } from './app.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {HttpModule} from "@angular/http";
import {NgbTypeaheadModule} from "@ng-bootstrap/ng-bootstrap";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap"
import {AgGridModule} from "ag-grid-angular";


import {CountryFinderComponent} from "./countryfinder/countryfinder.component";
import {CountryfinderService} from "./countryfinder/countryfinder.service";

@NgModule({
  declarations: [
    AppComponent,
    CountryFinderComponent

  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    HttpModule,
    NgbTypeaheadModule.forRoot(),
    NgbModule.forRoot(),
    AgGridModule.withComponents([])

  ],
  providers: [
    CountryfinderService
  ],
  schemas:[
    CUSTOM_ELEMENTS_SCHEMA
  ],
  bootstrap: [CountryFinderComponent]
})
export class AppModule { }
