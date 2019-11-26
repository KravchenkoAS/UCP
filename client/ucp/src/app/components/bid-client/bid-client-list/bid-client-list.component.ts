import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { Order } from 'src/app/services/order/order';
import { OrderService } from 'src/app/services/order/order.service';
import { BidClientComponent } from '../bid-client.component';
import { switchMap } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { ActivatedRoute} from '@angular/router';
import {Subscription} from 'rxjs';
import { BidClientDetailsComponent } from '../bid-client-details/bid-client-details.component';

@Component({
  selector: 'app-bid-client-list',
  templateUrl: './bid-client-list.component.html',
  styleUrls: ['./bid-client-list.component.css']
})
export class BidClientListComponent implements OnInit {
  
  isDetails: boolean;

  @Input() order: Order;
  // @Output() id_order = new EventEmitter<number>();
 
  constructor(private orderService: OrderService, private bidClientComponent: BidClientComponent,
    private route: ActivatedRoute) { 
      // console.log(this.route.children);
      // this.subscription = route.params.subscribe(params=>this.id_order = params['id_order']);
      // this.routeSubscription = route.params.subscribe(params=>this.order.id_order=params['id']);
    }
 

  ngOnInit() {
    if (this.order.name.length > 40) {
      this.order.name = this.order.name.substr(0, 40);
      this.order.name += "...";
      console.log(document.documentElement.clientWidth);
      this.isDetails = false;
    }
  //   console.log(this.route);
  //   this.route.paramMap.pipe(
  //     switchMap(params => params.get('id_order')) 
  // )
  // .subscribe(data=> {
  //   console.log(data);
  //   this.order.id_order = +data});
    
  }

   onClick(id_order: number){
      if(this.isDetails) {
        this.isDetails = false
      } else {
        this.isDetails = true;
      }
   }

}
