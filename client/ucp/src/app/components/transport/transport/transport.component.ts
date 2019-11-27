import { Component, OnInit, Input } from '@angular/core';
import { TransportService } from 'src/app/services/transport/transport.service';
import { TypeDelivery } from 'src/app/services/typeDelivery/typeDelivery';
import { TypeDeliveryService } from 'src/app/services/typeDelivery/type-delivery.service';
import { Transport } from 'src/app/services/transport/transport';

@Component({
  selector: 'app-transport',
  templateUrl: './transport.component.html',
  styleUrls: ['./transport.component.css']
})
export class TransportComponent implements OnInit {

  @Input() authority: any;

  transports: Transport[];
  isChange: boolean = false;
  isAdd: boolean = false;
  transportChange: Transport;
  isAdmin: boolean = false;

  constructor(private transportService: TransportService) { }

  ngOnInit() {
    let isAdmin = false;
    this.authority.forEach(function (value) {
      if (value == "ROLE_ADMIN") {
        isAdmin = true;
      }
    });
    this.isAdmin = isAdmin;

    this.transportService.getAllTransports()
    .subscribe(data => {
      console.log(data.length);
      this.transports = data;
    },
    error => {
      console.log(error);
      alert(error.error.message);
    })
  }

  Add(transportChange: Transport){
    this.isAdd = !this.isAdd;
  }

  Change(transportChange: Transport){
    this.transportChange = transportChange;
    this.isChange = !this.isChange;
  }

  TransportChange(transportChange: any){
    if (this.isChange) {
      this.isChange = false;
      this.transports.forEach(function (transport: Transport) {
        console.log(transport.id_transport + " - " + transportChange.id_transport);
        if(transport.id_transport == transportChange.id_transport) {
          transport = transportChange;
          console.log(transport);
        }
      })
      console.log(this.transports);
    } else if (this.isAdd) {
      this.isAdd = false;
      this.transports.push(transportChange);
    }
  }

  Delete(id_transport: number) {
    let i = -1;
    let index = 0;
    this.transports.forEach(function (transport: Transport) {
      if(transport.id_transport == id_transport) {
        i = index;
      }
      index++;
    })

    if (i >= 0) {
      this.transportService.deleteTransport(id_transport)
      .subscribe(data => {
        console.log(data);
        this.transports.splice(i, 1);
      },
      error => {
        console.log(error);
        alert(error.error.message);
      })
    }
  }
 
}