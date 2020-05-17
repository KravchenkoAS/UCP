import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

import { OrderService } from 'src/app/services/order/order.service';
import { Order } from 'src/app/services/order/order';
import { DeliveryComponent } from '../delivery.component';

@Component({
  selector: 'app-delivery-list',
  templateUrl: './delivery-list.component.html',
  styleUrls: ['./delivery-list.component.css']
})
export class DeliveryListComponent implements OnInit {

  @Input() order: Order;
  @Output() id_order = new EventEmitter<number>();
  image: string;
 
  constructor() { }
 
  ngOnInit() {
    if (this.order.name.length > 15) {
      this.order.name = this.order.name.substr(0, 15);
      this.order.name += "...";
    }

    if(this.order.status == "Выполняется") {
      this.image = "delivery-truck"
    } else if(this.order.status == "Выполнен") {
      this.image = "tick"
    } else if(this.order.status == "Отменен") {
      this.image = "letter-x"
    } else if(this.order.status == "Договор") {
      this.image = "contract"
    } else {
      this.image = "hourglass";
    } 
   }

   onClick(id_order: number){
     this.id_order.emit(id_order);
   }

}
