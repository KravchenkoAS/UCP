import { Component, OnInit, Input } from '@angular/core';
import { Order } from 'src/app/services/order/order';
import { OrderService } from 'src/app/services/order/order.service';
import { BidClientComponent } from '../bid-client.component';
import { ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-bid-client-list',
  templateUrl: './bid-client-list.component.html',
  styleUrls: ['./bid-client-list.component.css']
})
export class BidClientListComponent implements OnInit {
  
  isDetails: boolean;

  @Input() order: Order;
 
  constructor(private orderService: OrderService, private bidClientComponent: BidClientComponent,
    private route: ActivatedRoute) { 
    }
 

  ngOnInit() {
    if (this.order.name.length > 40) {
      this.order.name = this.order.name.substr(0, 40);
      this.order.name += "...";
      console.log(document.documentElement.clientWidth);
      this.isDetails = false;
    }
  }

   onClick(id_order: number){
      if(this.isDetails) {
        this.isDetails = false
      } else {
        this.isDetails = true;
      }
   }

  status(status:any){
    this.order.status = status;
  }

}
