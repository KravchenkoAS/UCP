import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userUrl = 'http://localhost:8080/api/test/client';
  private adminUrl = 'http://localhost:8080/api/test/admin';
 
  constructor(private http: HttpClient) { }
 
  public getUserBoard(): Observable<string> {
    return this.http.get(this.userUrl, { responseType: 'text' });
  }
 
  getAdminBoard(): Observable<string> {
    return this.http.get(this.adminUrl, { responseType: 'text' });
  }

}
