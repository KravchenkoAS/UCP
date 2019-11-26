import { Injectable } from '@angular/core';
import { AuthService } from 'src/app/auth/auth.service';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { TypeDelivery } from './typeDelivery';

@Injectable({
  providedIn: 'root'
})
export class TypeDeliveryService {

  private baseUrl = 'http://localhost:8080/api/test/type_delivery';

  constructor(private http: HttpClient, private authService: AuthService,                           // <<<---
        private tokenStorage: TokenStorageService) { }

  createTypeDelivery(type_delivery: TypeDelivery): Observable<Object> {                // <<<---
    return this.http.post(`${this.baseUrl}/createTypeDelivery`, type_delivery);     // <<<---
  }      

  getAllTypeDeliveries(): Observable<any> {
    return this.http.get(`${this.baseUrl}/getAllTypeDeliveries`);
  }
}
