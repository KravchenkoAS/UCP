import { Component } from '@angular/core';

import { TokenStorageService } from './auth/token-storage.service';     // <<<---
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
 
  private roles: string[];                                              // <<<---
  private authority: string; 

  constructor(private tokenStorage: TokenStorageService,
    private router: Router) { }            // <<<---
 
  ngOnInit() {                                                          // <<<---
    if (this.tokenStorage.getToken()) {                                 // <<<---
      this.roles = this.tokenStorage.getAuthorities();                  // <<<---
      this.roles.every(role => {                                        // <<<---
        if (role === 'ROLE_ADMIN') {                                    // <<<---
          this.authority = 'admin';                                     // <<<---
          return false;                                                 // <<<---
        } else if (role === 'ROLE_ANALYST') {
          this.authority = 'analyst';                                     // <<<---
          return false;
        }                                                            // <<<---
        this.authority = 'client';                                      // <<<---
        return true;                                                    // <<<---
      });                                                               // <<<---
    }

  }   
  
  logout() {    
    console.log("/app");                                                        // <<<---
    this.router.navigate(['/']); 
    this.tokenStorage.signOut();
    this.authority = null;
  }                                                                   // <<<---// <<<---
  
}
