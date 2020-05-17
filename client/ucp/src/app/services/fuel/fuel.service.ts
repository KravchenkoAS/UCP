import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from 'src/app/auth/auth.service';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { Observable } from 'rxjs';
import { Fuel } from './fuel';

@Injectable({
  providedIn: 'root'
})
export class FuelService {
  private baseUrl = 'http://localhost:8080/api/test/fuel';
;

  constructor(private http: HttpClient,private authService: AuthService,                           
        private tokenStorage: TokenStorageService) { }

  createFuel(fuel: Fuel): Observable<any> {                
    return this.http.post(`${this.baseUrl}/createFuel`, fuel);     
  }      
  
  getAllFuels(): Observable<any> {
    return this.http.get(`${this.baseUrl}/getAllFuels`);
  }

  getFuelId(id_fuel: number): Observable<any> {
   return this.http.get(`${this.baseUrl}/getFuelId/${id_fuel}`);
  }

  getFuelName(name: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/getFuelName/${name}`);
   }

  updateFuel(id_fuel: number, fuel: Fuel): Observable<any> {          
    return this.http.put(`${this.baseUrl}/updateFuel/${id_fuel}`, fuel);               
  }                                                                     
 
  deleteFuel(id_fuel: number): Observable<any> {                         
    return this.http.delete(`${this.baseUrl}/deleteFuel/${id_fuel}`,                     
                            { responseType: 'text' });                  
  }  
}
