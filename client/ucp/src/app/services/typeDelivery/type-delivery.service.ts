import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { TypeDelivery } from './typeDelivery';

@Injectable({
  providedIn: 'root'
})
export class TypeDeliveryService {

  private baseUrl = 'http://localhost:8080/api/test/type_delivery';

  constructor(private http: HttpClient) { }

  createTypeDelivery(type_delivery: TypeDelivery): Observable<any> {                
    return this.http.post(`${this.baseUrl}/createTypeDelivery`, type_delivery);   
  }      

  getAllTypeDeliveries(): Observable<any> {
    return this.http.get(`${this.baseUrl}/getAllTypeDeliveries`);
  }

  deleteTypeDelivery(type: TypeDelivery): Observable<any> {                         
    return this.http.delete(`${this.baseUrl}/deleteTypeDelivery/${type.id_type_delivery}`, { responseType: 'text' });     
  }  
}
