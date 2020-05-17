import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Transport } from './transport';

@Injectable({
  providedIn: 'root'
})
export class TransportService {
  
  private baseUrl = 'http://localhost:8080/api/test';

  constructor(private http: HttpClient) { }

  updateTransport(transportForm: any): Observable<any> {                
    let transport = new Transport();
    transport.init(transportForm);
    console.log(transport);
    return this.http.post(`${this.baseUrl}/transport/updateTransport`, transport);    
  }      

  getAllTransports(): Observable<any> {
    return this.http.get(`${this.baseUrl}/transport/getAllTransports`);
  }

  deleteTransport(id_transport: number): Observable<any> {                         
    return this.http.delete(`${this.baseUrl}/transport/deleteTransport/${id_transport}`, { responseType: 'text' });                  
  }  

  getTransportsTransporter(username: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/transporter/getTransportsTransporter/${username}`);
  }

  deleteTransportTransporter(username: string, id_transport: number): Observable<any> {                         
    return this.http.delete(`${this.baseUrl}/transporter/deleteTransportTransporter/${username}/${id_transport}`, { responseType: 'text' });             
  }  

  addTransportTransporter(username: string, id_transport: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/transporter/addTransportTransporter/${username}/${id_transport}`);
  }
}
