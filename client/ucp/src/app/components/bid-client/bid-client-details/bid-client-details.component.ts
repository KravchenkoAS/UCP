import { Component, OnInit, Input } from '@angular/core';
import { OrderCreate, Order } from 'src/app/services/order/order';
import { Point } from 'src/app/services/point/point';
import { OrderService } from 'src/app/services/order/order.service';
import { PointService } from 'src/app/services/point/point.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-bid-client-details',
  templateUrl: './bid-client-details.component.html',
  styleUrls: ['./bid-client-details.component.css']
})
export class BidClientDetailsComponent implements OnInit {

  @Input() order = new Order();
  orderDetails = new OrderCreate();
  startPoint = new Point();
  endPoint = new Point();

  constructor(private orderService: OrderService, private pointService: PointService,
    private router: Router) { }

  ngOnInit() {
    console.log(this.order);
    this.orderService.getOrder(this.order.id_order)
      .subscribe(
        data => {
          console.log(data),
            this.orderDetails = data;
          this.getPoint(this.orderDetails.startPoint, this.startPoint);
          this.getPoint(this.orderDetails.endPoint, this.endPoint);
        },
        error => {
          console.log(error);
          alert(error.error.message);
        })
  }

  getPoint(id_point: string, point: Point) {
    this.pointService.getPoint(id_point)
      .subscribe(
        data => {
          console.log(data),
          point.id_point = data.id_point;
          point.city = data.city;
          point.country = data.country;
        },
        error => {
          console.log(error);
          alert(error.error.message);
        })
  }

}
