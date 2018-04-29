export class CountryDetails {
   alpha_2: string
   alpha_3: string
   area: string
   capital: string
   continent: string
   currency_code: string
   currency_name: string
   eqivalent_fips_code: string
   fips: string
   geoname_id: string
   languages: string
   name: string
   neighbours: string
   numeric: string
   phone: string
   population: string
   postal_code_format: string
   postal_code_regex: string
   tld: string

  constructor(alpha_2: string,alpha_3: string,area: string,capital: string,
  continent: string,currency_code: string,currency_name: string,eqivalent_fips_code: string,fips: string,geoname_id: string,languages: string
,name: string,neighbours:string,numeric: string,phone:string,population: string,postal_code_format: string,postal_code_regex: string,tld: string){
    this.alpha_2= alpha_2;
    this.alpha_3= alpha_3 ;
    this.area = area;
    this.capital= capital;
    this.continent= continent;
    this.currency_code= currency_code;
    this.currency_name= currency_name;
    this.eqivalent_fips_code= eqivalent_fips_code;
    this.fips= fips;
    this.geoname_id= geoname_id;
    this.languages= languages;
    this.name= name;
    this.neighbours= neighbours;
    this.numeric= numeric;
    this.phone= phone;
    this.population= population;
    this.postal_code_format= postal_code_format;
    this.postal_code_regex= postal_code_regex;
    this.tld= tld;
  }
}
