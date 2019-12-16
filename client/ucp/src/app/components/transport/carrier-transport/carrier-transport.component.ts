import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-carrier-transport',
  templateUrl: './carrier-transport.component.html',
  styleUrls: ['./carrier-transport.component.css']
})
export class CarrierTransportComponent implements OnInit {

  info: any;   
  isList: boolean = false;
  isAdd: boolean = false;
  isMy: boolean = false;                                                       // <<<---

  constructor(private token: TokenStorageService,
    private router: Router,) { }                 // <<<---

  ngOnInit() {                                                        // <<<---
    this.info = {                                                     // <<<---
      token: this.token.getToken(),                                   // <<<---
      username: this.token.getUsername(),                             // <<<---
      authorities: this.token.getAuthorities()                        // <<<---
    };                                                                // <<<---
  }   

  IsList() {
    if (this.isList) {
      this.isList = false;
      this.router.navigateByUrl("/carrierTransport"); 
    } else {
      this.isList = true;
      this.isAdd = false;
      this.isMy = false;
    }
  }

  IsAdd() {
    if (this.isAdd) {
      this.isAdd = false;
      this.router.navigateByUrl("/carrierTransport"); 
    } else {
      this.isAdd = true;
      this.isMy = false;
      this.isList = false;
    }
  }

  IsMy() {
    if (this.isMy) {
      this.isMy = false;
      this.router.navigateByUrl("/carrierTransport"); 
    } else {
      this.isMy = true;
      this.isAdd = false;
      this.isList = false;
    }
  }
}
