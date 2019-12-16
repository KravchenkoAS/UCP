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
        console.log(data);
        this.itemsInput = data;
        console.log(this.itemsInput);
      }, error => {
        console.log(error);
        alert(error.error);
      })

    this.transportService.getTransportsTransporter(this.route.snapshot.firstChild.paramMap.get('username'))
      .subscribe(data => {
        console.log(data.length);
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
    
    // let item1 = new Item();
    // let item2 = new Item();
    // let item3 = new Item();
    // let item4 = new Item();
    // let item5 = new Item();

    // item1.id_order = 111;
    // item1.name = "Книга";
    // item1.price = 600;
    // item1.weight = 200;
    
    // item2.id_order = 222;
    // item2.name = "Бинокль";
    // item2.price = 5000;
    // item2.weight = 300;
    
    // item3.id_order = 333;
    // item3.name = "Аптечка";
    // item3.price = 15000;
    // item3.weight = 500;

    // item4.id_order = 444;
    // item4.name = "Ноутбук";
    // item4.price = 40000;
    // item4.weight = 300;

    // item5.id_order = 555;
    // item5.name = "Котелок";
    // item5.price = 500;
    // item5.weight = 200;

    // this.itemsInput.push(item1);
    // this.itemsInput.push(item2);
    // this.itemsInput.push(item3);
    // this.itemsInput.push(item4);
    // this.itemsInput.push(item5);


    if (this.id_transport != null) {
      this.backpackService.calculateBackpack(this.id_transport, this.itemsInput)
        .subscribe( data => {
          this.backpack = data;
          this.submitted = true;
          this.isError = false;
          this.message = 'Рюкзак собран';
        }, error => {
          console.log(error);
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
            console.log(data)
          }, error => {
            console.log(error);
            this.submitted = true;
            this.isError = true;
            this.message = error.error;
          })
      }  else {
        this.submitted = true;
        this.isError = true;
        this.message = 'Рюкзак пуст';
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
