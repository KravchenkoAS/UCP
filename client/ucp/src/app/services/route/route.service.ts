import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RouteService {

  private baseUrl = 'http://localhost:8080/api/test/';   

  constructor(private http: HttpClient) { }   
  
  getRoute(id_order: number): Observable<any> {
    return this.http.get(`${this.baseUrl}route/getRoute/${id_order}`);
  }

  getDictionaryList(id_route: number): Observable<any> {
    return this.http.get(`${this.baseUrl}route/getDictionaryList/${id_route}`)
  }

  deleteWay(id_route: number, way: number): Observable<any> {                         
    return this.http.delete(`${this.baseUrl}route/deleteWay/${id_route}/${way}`,                     
                            { responseType: 'text' });                  
  }
  
  submit(id_order: number, id_route: number, way: number): Observable<any> {                         
    return this.http.get(`${this.baseUrl}route/submit/${id_order}/${id_route}/${way}`);                  
  }

}
