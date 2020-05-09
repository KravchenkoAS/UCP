import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { OrderCreate, Order } from 'src/app/services/order/order';
import { Point } from 'src/app/services/point/point';
import { OrderService } from 'src/app/services/order/order.service';
import { PointService } from 'src/app/services/point/point.service';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';

@Component({
  selector: 'app-bid-client-details',
  templateUrl: './bid-client-details.component.html',
  styleUrls: ['./bid-client-details.component.css']
})
export class BidClientDetailsComponent implements OnInit {

  info: any;    

  @Input() order = new Order();
  @Output() status = new EventEmitter<string>();
  orderDetails = new OrderCreate();
  startPoint = new Point();
  endPoint = new Point();

  constructor(private orderService: OrderService, 
    private pointService: PointService,
    private token: TokenStorageService) { }

  ngOnInit() {
    this.info = {                                                     // <<<---
      token: this.token.getToken(),                                   // <<<---
      username: this.token.getUsername(),                             // <<<---
      authorities: this.token.getAuthorities()                        // <<<---
    };   

    console.log(this.info.authorities);
    this.orderService.getOrder(this.order.id_order)
      .subscribe(
        data => {
          this.orderDetails = data;
          this.getPoint(this.orderDetails.startPoint, this.startPoint);
          this.getPoint(this.orderDetails.endPoint, this.endPoint);
        },
        error => {
          alert(error.error.message);
        })
  }

  getPoint(id_point: string, point: Point) {
    this.pointService.getPoint(id_point)
      .subscribe(
        data => {
          point.id_point = data.id_point;
          point.city = data.city;
          point.country = data.country;
        },
        error => {
          alert(error.error.message);
        })
  }

  SubmitStatus(){
    this.orderService.updateOrderStatus(this.order)
    .subscribe(
      data => {
        this.status.emit(this.order.status);
      }, error => {
        alert(error.error.message);
      }
    )
  }

}
