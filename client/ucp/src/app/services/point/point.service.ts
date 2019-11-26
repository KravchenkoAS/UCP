import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';                    // <<<---
import { Observable } from 'rxjs';     

@Injectable({
  providedIn: 'root'
})
export class PointService {

  private baseUrl = 'http://localhost:8080/api/test/';   // <<<---

  constructor(private http: HttpClient) { }   
  
  getPointList(): Observable<any> {
    return this.http.get(`${this.baseUrl}client/delivery/getAllPoints`);
  }

  getPoint(id_point: string): Observable<any> {
    console.log(id_point);
    return this.http.get(`${this.baseUrl}getPoint/${id_point}`);
  }
  
}
