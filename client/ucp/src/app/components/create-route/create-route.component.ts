import { Component, OnInit, ViewChild, ComponentRef, ComponentFactoryResolver, ViewContainerRef } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Order, OrderCreate } from 'src/app/services/order/order';
import { switchMap } from 'rxjs/operators';
import { OrderService } from "../../services/order/order.service";
import { PointService } from 'src/app/services/point/point.service';
import { Point } from 'src/app/services/point/point';
import { Button } from 'protractor';
import { HtmlTagDefinition } from '@angular/compiler';
import { SegmentComponent } from './segment/segment.component';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { SegmentCreate } from 'src/app/services/segment/segment';
import { SegmentService } from 'src/app/services/segment/segment.service';

@Component({
  selector: 'app-create-route',
  templateUrl: './create-route.component.html',
  styleUrls: ['./create-route.component.css']
})
export class CreateRouteComponent implements OnInit {

  info: any;   
  order = new Order();
  orderDetails = new OrderCreate();
  startPoint = new Point();
  endPoint = new Point();
  time: number = 0;
  distance: number = 0;
  isSaveAll: boolean = false;

  @ViewChild('viewContainerRef', { read: ViewContainerRef }) VCR: ViewContainerRef;

  index: number = 0;
  segments = new Map<number, SegmentCreate>();
  componentsReferences = [];

  constructor( private route: ActivatedRoute, private orderService: OrderService,
    private pointService: PointService, private CFR: ComponentFactoryResolver,
    private token: TokenStorageService,
    private segmentService: SegmentService ) { }

  ngOnInit() {
    this.info = {                                                     // <<<---
      token: this.token.getToken(),                                   // <<<---
      username: this.token.getUsername(),                             // <<<---
      authorities: this.token.getAuthorities()                        // <<<---
    };  

    this.orderService.getAloneOrder(Number(this.route.snapshot.paramMap.get('id')))
    .subscribe(
      data => {
        this.order = data;
        this.getOrderDetails(this.order.id_order, this.orderDetails);
      },
      error => {
        alert(error.error.message);
      })
    }

  getPoint(id_point: string, point: Point) {
    this.pointService.getPoint(id_point)
      .subscribe(
        data => {
          console.log(data);

          point.id_point = data.id_point;
          point.city = data.city;
          point.country = data.country;
        },
        error => {
          console.log(error);

          alert(error.error.message);
        })
  }

  getOrderDetails(id_order: number, orderDetails: OrderCreate) {
    this.orderService.getOrder(id_order)
      .subscribe(
        data => {
          orderDetails.init(data);
          this.getPoint(this.orderDetails.startPoint, this.startPoint);
          this.getPoint(this.orderDetails.endPoint, this.endPoint);
        },
        error => {
          alert(error.error.message);
        })
  }

  addSegment() {

    let componentFactory = this.CFR.resolveComponentFactory(SegmentComponent);
    let componentRef: ComponentRef<SegmentComponent> = this.VCR.createComponent(componentFactory);
    let currentComponent = componentRef.instance;

    currentComponent.selfRef = currentComponent;
    currentComponent.index = ++this.index;

    // prividing parent Component reference to get access to parent class methods
    currentComponent.compInteraction = this;

    // add reference for newly created component
    this.componentsReferences.push(componentRef);
  }

  removeSegment(index: number) {

    if (this.VCR.length < 1)
      return;

    let componentRef = this.componentsReferences.filter(x => x.instance.index == index)[0];
    let component: SegmentComponent = <SegmentComponent>componentRef.instance;

    let vcrIndex: number = this.VCR.indexOf(componentRef)

    this.segments.delete(index);
    // removing component from container
    this.VCR.remove(vcrIndex);

    this.componentsReferences = this.componentsReferences.filter(x => x.instance.index !== index);
  }

  save(index: number, segment: SegmentCreate){
    if (this.VCR.length < 1)
      return;

    this.segments.set(index, segment);
  }

  calculateAll() {
    this.isSaveAll = false;
    let isPointStart: boolean = false;
    let isPointEnd: boolean = false;
    let idStartPoint = this.orderDetails.startPoint;
    let idEndPoint = this.orderDetails.endPoint;
    this.segments.forEach(function (segment: SegmentCreate) {
      if (segment.startPoint === idStartPoint.toString()) {
        isPointStart = true;
      }
      if (segment.endPoint === idEndPoint.toString()) {
        isPointEnd = true;
      }
    });

    let price: number = 0;
    let time: number = 0;
    let distance: number = 0;
    if (!isPointEnd || !isPointStart) {
      alert("Маршрут не построен!!!");
    } else {
      price = this.segmentService.calculateAllPrice(this.segments);
      time = this.segmentService.calculateAllTime(this.segments);
      distance = this.segmentService.calculateAllDistance(this.segments);
    }
    this.order.price = 0;
    this.order.price += price;
    this.time = time;
    this.distance = distance;
  }

  saveAll(){
  if(this.segments.size < 1) {
      alert("Маршрут не построен!!!");
  } else if (this.order.price <= 8 ) {
    this.calculateAll();
  }

  this.segmentService.saveAllSegments(this.order.id_order, this.segments)
  .subscribe(
    data => {
      this.isSaveAll = true;
    },
    error => {
      alert(error.error);
    })
    
  }

}
