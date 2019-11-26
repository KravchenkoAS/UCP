import { Injectable } from '@angular/core';
import { AuthService } from 'src/app/auth/auth.service';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TransportService {
  
  private baseUrl = 'http://localhost:8080/api/test/transport';

  constructor(private http: HttpClient, private authService: AuthService,                           // <<<---
        private tokenStorage: TokenStorageService) { }

  createTransport(transport: Transport): Observable<Object> {                // <<<---
    return this.http.post(`${this.baseUrl}/createTransport`, transport);     // <<<---
  }      

  getAllTransports(): Observable<any> {
    return this.http.get(`${this.baseUrl}/getAllTransports`);
  }
}
