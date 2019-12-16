import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Order } from '../order/order';
import { Observable } from 'rxjs';
import { Item } from './ItemAndBackpack';

@Injectable({
  providedIn: 'root'
})
export class BackpackService {
  
  private baseUrl = 'http://localhost:8080/api/test/backpack';
  
    constructor(private http: HttpClient) { }
  
    getItems(orders: Array<Order>): Observable<any> {                // <<<--
      return this.http.post(`${this.baseUrl}/getItems`, orders);     // <<<---
    } 
    
    calculateBackpack(id_transport: number, items: Array<Item>): Observable<any> {
      return this.http.post(`${this.baseUrl}/calculateBackpack/${id_transport}`, items);     // <<<---
    }

    submit(items: Array<Item>): Observable<any> {
      return this.http.post(`${this.baseUrl}/submit`, items);
    }
}
