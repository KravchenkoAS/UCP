import { Component, OnInit } from '@angular/core';
import { Order } from 'src/app/services/order/order';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { OrderService } from 'src/app/services/order/order.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-bid-client',
  templateUrl: './bid-client.component.html',
  styleUrls: ['./bid-client.component.css']
})
export class BidClientComponent implements OnInit {

  info: any;    
  orders = new Array<Order>();
  id_order: number;

  constructor(private token: TokenStorageService, private orderService: OrderService) { 
    }

    ngOnInit() {                                                        // <<<---
      this.info = {                                                     // <<<---
        token: this.token.getToken(),                                   // <<<---
        username: this.token.getUsername(),                             // <<<---
        authorities: this.token.getAuthorities()                        // <<<---
      };   
      this.orderService.getAllOrders()
      .subscribe(orders => this.orders = orders); 
      console.log(this.info);
    }                                                                   // <<<---
   
    logout() {  
      console.log("/bid-client");                                                          // <<<---
      this.token.signOut();                                             // <<<---
      window.location.reload();                                         // <<<---
    }       

}
