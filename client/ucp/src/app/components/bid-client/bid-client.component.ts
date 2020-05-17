import { Component, OnInit } from '@angular/core';
import { Order, OrderCreate } from 'src/app/services/order/order';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { OrderService } from 'src/app/services/order/order.service';
import { Filter } from 'src/app/services/filter/filter';

@Component({
  selector: 'app-bid-client',
  templateUrl: './bid-client.component.html',
  styleUrls: ['./bid-client.component.css']
})
export class BidClientComponent implements OnInit {

  info: any;    
  orders = new Array<Order>();
  ordersList = new Array<Order>();
  id_order: number;

  backpack: boolean = false;

  ordersFilter = new Array<OrderCreate>();

  constructor(private token: TokenStorageService, private orderService: OrderService) { }

    ngOnInit() {                                                        
      this.info = {                                                     
        token: this.token.getToken(),                                   
        username: this.token.getUsername(),                             
        authorities: this.token.getAuthorities()                        
      };   
      this.reloadAllOrders();
      this.orderService.getAllOrCaRoDTO()
      .subscribe(data => this.ordersFilter = data);
    }                                                                   
   
    logout() {  
      this.token.signOut();                                             
      window.location.reload();                                         
    }       

    filter(filter: Filter) {
      let ordersNew = new Array<Order>();
      let ordersFilterNew = new Array<OrderCreate>();
      if (filter.startPoint != "null" || filter.endPoint != "null") {
        this.ordersFilter.forEach(function (orderFilter: OrderCreate) {
        if (orderFilter.startPoint == filter.startPoint
          && orderFilter.endPoint == filter.endPoint) {
            ordersFilterNew.push(orderFilter);
          } 
        })
        this.orders.forEach(function (order: Order) {
          ordersFilterNew.forEach(function (orderFilterNew: OrderCreate) {
            if (order.id_order === orderFilterNew.id_order) {
              ordersNew.push(order);
            }
          })
        })

        if (filter.status != "null") {
          let array = new Array<Order>(); 
          for (let i = 0; i < ordersNew.length; i++) {
            if (ordersNew[i].status === filter.status) {
              array.push(ordersNew[i]);
            }  
          }
          ordersNew = array.slice();
        }
        
        this.orders = ordersNew;
      } else if (filter.status != "null") {
         
          this.orders.forEach(function (order: Order) {
            if (order.status == filter.status) {
              ordersNew.push(order);
            }
          })
          this.orders = ordersNew;
      } 
      
    }

    cancel(){
      this.reloadAllOrders();
    }

    reloadAllOrders(){
      this.orderService.getAllOrders()
      .subscribe(orders => this.orders = orders);
    }

    IsBackpack() {
       if (this.orders.length === 0) {
        alert("По этому маршруту нет заявок");
      }  else {
        this.backpack = !this.backpack;
      }
  
    }
}
