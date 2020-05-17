import { Component, OnInit, Input } from '@angular/core';
import { Order } from 'src/app/services/order/order';
import { BackpackService } from 'src/app/services/backpack/backpack.service';
import { Item, Backpack } from 'src/app/services/backpack/ItemAndBackpack';
import { TransportService } from 'src/app/services/transport/transport.service';
import { ActivatedRoute } from '@angular/router';
import { Transport } from 'src/app/services/transport/transport';

@Component({
  selector: 'app-backpack',
  templateUrl: './backpack.component.html',
  styleUrls: ['./backpack.component.css']
})
export class BackpackComponent implements OnInit {

  @Input() orders = new Array<Order>();
  itemsInput = new Array<Item>();
  transports: Transport[];
  id_transport: number = null;
  submitted: boolean = false;
  isError: boolean = false;
  message: string;
  backpack: Backpack;

  constructor(private backpackService: BackpackService,
    private transportService: TransportService,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.backpackService.getItems(this.orders)
      .subscribe( data => {
        this.itemsInput = data;
      }, error => {
        alert(error.error);
      })

    this.transportService.getTransportsTransporter(this.route.snapshot.firstChild.paramMap.get('username'))
      .subscribe(data => {
        this.transports = data;
      }, error => {
        console.log(error);
        alert(error.error.message);
      }) 
  }

  SubmitTrp(transport: Transport) {
    this.id_transport = transport.id_transport;
    this.transports.length = 0;
    this.transports.push(transport);
    this.submitted = true;
    this.message = "Транспорт выбран";
    
    setTimeout(() => {
      this.isError = false;
      this.submitted = false;
    }, 5000);
  }

  CalculateBackpack() {
 
    if (this.id_transport != null) {
      this.backpackService.calculateBackpack(this.id_transport, this.itemsInput)
        .subscribe( data => {
          this.backpack = data;
          this.submitted = true;
          this.isError = false;
          this.message = 'Набор грузов собран';
        }, error => {
          this.submitted = true;
          this.isError = true;
          this.message = error.error;
        })
    }  else {
      this.submitted = true;
      this.isError = true;
      this.message = 'Выберите ТС';
    }

    setTimeout(() => {
      this.isError = false;
      this.submitted = false;
    }, 20000);
  }

  Submit() {
      if (this.backpack != undefined && this.backpack.bestItems.length > 0) {
        this.backpackService.submit(this.backpack.bestItems)
          .subscribe( data => {
            this.backpack = data;
            this.submitted = true;
            this.isError = false;
            this.message = 'Заявки подтверждены';
          }, error => {
            this.submitted = true;
            this.isError = true;
            this.message = error.error;
          })
      }  else {
        this.submitted = true;
        this.isError = true;
        this.message = 'Список грузов пуст';
      }
    

    if (!this.isError) {
      setTimeout(() => {
        this.isError = false;
        this.submitted = false;
        window.location.reload();
      }, 3000);
    } else {
      setTimeout(() => {
        this.isError = false;
        this.submitted = false;
      }, 20000);
    }
  }
}
