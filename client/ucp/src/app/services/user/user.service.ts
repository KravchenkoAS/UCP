import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User, ChangePassword } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userUrl = 'http://localhost:8080/api/test/client';
  private adminUrl = 'http://localhost:8080/api/test/admin';
  private baseUrl = 'http://localhost:8080/api/test/user';
 
  constructor(private http: HttpClient) { }
 
  public getUserBoard(): Observable<string> {
    return this.http.get(this.userUrl, { responseType: 'text' });
  }
 
  getAdminBoard(): Observable<string> {
    return this.http.get(this.adminUrl, { responseType: 'text' });
  }

  getUser(userName: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/getUser/${userName}`);
  }

  // updateLoginData(id_user: number, user: User): Observable<any> {          // <<<---
  //   return this.http.put(`${this.baseUrl}/updateLoginData/${id_user}`, user);               // <<<---
  // }  

  updatePassword(id_user: number, changePassword: ChangePassword): Observable<any> {          // <<<---
    return this.http.put(`${this.baseUrl}/updatePassword/${id_user}`, changePassword);               // <<<---
  }
  
  updateUser(user: User): Observable<any> {          // <<<---
    return this.http.put(`${this.baseUrl}/updateUser/${user.id_user}`, user);               // <<<---
  } 
  
  blockUser(id_user: number): Observable<any> {          // <<<---
    return this.http.get(`${this.baseUrl}/blockUser/${id_user}`);               // <<<---
  } 

  getAllUser(): Observable<any> {
    return this.http.get(`${this.baseUrl}/getAllUser/`);
  }  

}
