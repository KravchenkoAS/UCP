import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';                    // <<<---
import { Observable } from 'rxjs';  
import { OrderCreate } from './order';
import { TokenStorageService } from "../../auth/token-storage.service";
import { AuthService } from "../../auth/auth.service";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private baseUrl = 'http://localhost:8080/api/test/client/delivery';
  private baseUrl_2 = 'http://localhost:8080/api/test';

  constructor(private http: HttpClient,private authService: AuthService,                           // <<<---
        private tokenStorage: TokenStorageService) { }

  createOrder(formCreateOrder: any): Observable<Object> {                // <<<---
    let orderCreate: OrderCreate = new OrderCreate()
    orderCreate.type = formCreateOrder.type;
    orderCreate.name = formCreateOrder.name;
    orderCreate.length = formCreateOrder.length;
    orderCreate.width = formCreateOrder.width;
    orderCreate.height = formCreateOrder.height;
    orderCreate.weight = formCreateOrder.weight;
    orderCreate.amount = formCreateOrder.amount;
    orderCreate.startPoint = formCreateOrder.startPoint;
    orderCreate.endPoint = formCreateOrder.endPoint;
    orderCreate.date = formCreateOrder.date;
    orderCreate.isContainer = formCreateOrder.isContainer;
    orderCreate.isContainer = formCreateOrder.isContainer;
    console.log(this.tokenStorage.getUsername());
    return this.http.post(`${this.baseUrl}` + `/` + this.tokenStorage.getUsername() + `/createOrder`, orderCreate);     // <<<---
  }      
  
  getAllOrdersUser(): Observable<any> {
    return this.http.get(`${this.baseUrl}/${this.tokenStorage.getUsername()}`);
  }

  getAllOrders(): Observable<any> {
    return this.http.get(`${this.baseUrl_2}/analyst/getAllOrders`);
  }

  getOrder(id_order: number): Observable<any> {
   return this.http.get(`${this.baseUrl}/delivery-details/${id_order}`);
  }

  getAloneOrder(id_order: number): Observable<any> {
    return this.http.get(`${this.baseUrl_2}/getAloneOrder/${id_order}`);
   }

  updateOrder(id_order: number, orderCreate: OrderCreate): Observable<any> {          // <<<---
    return this.http.put(`${this.baseUrl}/updateOrder/${this.tokenStorage.getUsername()}/${id_order}`, orderCreate);               // <<<---
  }                                                                     // <<<---
 
  deleteOrder(id_order: number): Observable<any> {                         // <<<---
    return this.http.delete(`${this.baseUrl}/delete/${id_order}`,                    // <<<--- 
                            { responseType: 'text' });                  // <<<---
  }  

}
