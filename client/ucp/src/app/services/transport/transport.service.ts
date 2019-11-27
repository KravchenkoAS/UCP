import { Injectable } from '@angular/core';
import { AuthService } from 'src/app/auth/auth.service';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Transport } from './transport';

@Injectable({
  providedIn: 'root'
})
export class TransportService {
  
  private baseUrl = 'http://localhost:8080/api/test/transport';

  constructor(private http: HttpClient, private authService: AuthService,                           // <<<---
        private tokenStorage: TokenStorageService) { }

  updateTransport(transportForm: any): Observable<any> {                // <<<---
    let transport = new Transport();
    transport.init(transportForm);
    console.log(transport);
    return this.http.post(`${this.baseUrl}/updateTransport`, transport);     // <<<---
  }      

  getAllTransports(): Observable<any> {
    return this.http.get(`${this.baseUrl}/getAllTransports`);
  }

  deleteTransport(id_transport: number): Observable<any> {                         // <<<---
    return this.http.delete(`${this.baseUrl}/deleteTransport/${id_transport}`, { responseType: 'text' });                  // <<<---
  }  

}
