import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Employee } from './employee';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private url = 'http://localhost:8080/api/test/admin';
 
  constructor(private http: HttpClient) { }

  createEmployee(employee: Employee): Observable<any> {          
    return this.http.put(`${this.url}/createEmployee`, employee);               
  }

}
