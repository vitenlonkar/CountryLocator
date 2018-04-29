import {Response} from "@angular/http";

export class ServiceUtils {
  static extractJson(res: Response) {
    let body = {};
    if (res.text()) {
      body = res.json();
    }

    return body ;
  }
}
