import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { OrderCreate, Order } from 'src/app/services/order/order';
import { Router } from '@angular/router';
import { OrderService } from 'src/app/services/order/order.service';
import { PointService } from 'src/app/services/point/point.service';
import { Point } from 'src/app/services/point/point';
import { DeliveryComponent } from '../delivery.component';

@Component({
  selector: 'app-delivery-details',
  templateUrl: './delivery-details.component.html',
  styleUrls: ['./delivery-details.component.css']
})
export class DeliveryDetailsComponent implements OnInit {

  @Input() id_order: number;
  @Output() openDetails = new EventEmitter<boolean>();
  order = new OrderCreate();
  startPoint = new Point();
  endPoint = new Point();
  submitted = false;

  constructor(private _router: Router, private orderService: OrderService, private pointService: PointService,
    deliveryComponent: DeliveryComponent) { }

  ngOnInit() {
    this.navigateToFoo();
    this.orderService.getOrder(this.id_order)
      .subscribe(
        data => {
          console.log(data),
            this.order = data;
          this.getPoint(this.order.startPoint, this.startPoint);
          this.getPoint(this.order.endPoint, this.endPoint);
        },
        error => {
          console.log(error);
          alert(error.error.message);
        })
  }

  // clickBack(){
  //   this.openDetails.emit(false);
  // }

  navigateToFoo() {
    this._router.navigate([], { queryParams: { order: this.id_order } });
  }

  isControlInvalid(controlName: string): boolean {
    const control = /^[\wА-я-\s]+$/.test(controlName);
    return control;
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

  Save(){
    console.log(this.order);
    this.orderService.updateOrder(this.order.id_order, this.order)
      .subscribe(
        data => {
          console.log(data);
          this.submitted = true;
          this.order = data as OrderCreate;
        },
        error => {
          this.submitted = false;
          console.log(error);
          alert(error.error.message);
        })
  }

  Delete(){
    console.log(this.order);
    this.orderService.deleteOrder(this.order.id_order)
      .subscribe(
        data => {
          console.log(data);
          alert(data);
          window.location.reload();
        },
        error => {
          this.submitted = false;
          console.log(error);
          alert(error.error.message);
        })
  }

}
