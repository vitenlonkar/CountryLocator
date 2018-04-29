import {Injectable} from "@angular/core";
import {HttpClient, HttpClientModule, HttpHeaders} from "@angular/common/http";
import {Observable} from  "rxjs/Rx";
import {RequestOptions, Headers, HttpModule, Http} from "@angular/http";
import {ServiceUtils} from "../utils/service-utils";
import {environment} from "../../environments/environment";

@Injectable()
export class CountryfinderService {

  headers: Headers;
  options: RequestOptions;
  API_URL = environment.apiUrl;

  constructor(private http: Http) {
    let headers = new Headers({'Content-Type': 'application/json'})
    this.options = new RequestOptions({ headers : headers}) ;

  }

  getCountryList(term: string, inputData: any ): Observable<any> {
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http
      .post(this.API_URL+'/countryfinder/api/getCountryList',inputData, this.options)
      .map(ServiceUtils.extractJson);


  }

  getCountryReportData(): Observable<any> {
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http
      .get(this.API_URL+'/countryfinder/api/getReportsData', this.options)
      .map(ServiceUtils.extractJson);


  }


}
