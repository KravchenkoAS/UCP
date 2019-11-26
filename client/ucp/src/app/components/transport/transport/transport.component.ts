import { Component, OnInit, Input } from '@angular/core';
import { TransportService } from 'src/app/services/transport/transport.service';
import { TypeDelivery } from 'src/app/services/typeDelivery/typeDelivery';
import { TypeDeliveryService } from 'src/app/services/typeDelivery/type-delivery.service';

@Component({
  selector: 'app-transport',
  templateUrl: './transport.component.html',
  styleUrls: ['./transport.component.css']
})
export class TransportComponent implements OnInit {

  @Input() authority: any;

  transports: Transport[];
  isChange: boolean = false;
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

  changeOrAdd(transportChange: Transport){
    console.log(transportChange);
  }

 
}