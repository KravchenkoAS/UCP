import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { Order } from 'src/app/services/order/order';
import { OrderService } from 'src/app/services/order/order.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-delivery',
  templateUrl: './delivery.component.html',
  styleUrls: ['./delivery.component.css']
})
export class DeliveryComponent implements OnInit {

  info: any;    
  orders:  Array<Order> = [];
  openDetails: boolean;
  id_order: number;

  constructor(private token: TokenStorageService, private orderService: OrderService,
    private _router: Router) { 
    }                 // <<<---

  ngOnInit() {                                                        // <<<---
    this.info = {                                                     // <<<---
      token: this.token.getToken(),                                   // <<<---
      username: this.token.getUsername(),                             // <<<---
      authorities: this.token.getAuthorities()                        // <<<---
    };   
    this.orderService.getAllOrdersUser()
      .subscribe(orders => this.orders = orders); 
    this._router.navigate([]);
    this.openDetails = false;                                                            // <<<---
  }                                                                   // <<<---
 
  logout() {  
    this.token.signOut();                                             // <<<---
    window.location.reload();                                         // <<<---
  }     
  
  onId_order(id_order: number) {
    if(id_order > 0){
      this.openDetails = true;
      this.id_order = id_order;
    }
  } 

  // onOpenDetails(openDetails: boolean) {
  //     this.openDetails = openDetails;
  //     this._router.navigate([]);
  // }
}
