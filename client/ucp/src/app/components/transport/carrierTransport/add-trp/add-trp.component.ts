import { Component, OnInit, Input } from '@angular/core';
import { TransportService } from 'src/app/services/transport/transport.service';
import { Transport } from 'src/app/services/transport/transport';

@Component({
  selector: 'app-add-trp',
  templateUrl: './add-trp.component.html',
  styleUrls: ['./add-trp.component.css']
})
export class AddTrpComponent implements OnInit {

  @Input() info: any;

  transports: Transport[];
  isAdd: boolean = false;
  submitted: boolean = false;
  errorUser: boolean = false;
  errorMessageUser: string;

  constructor(private transportService: TransportService) { }

  ngOnInit() {
    this.transportService.getAllTransports()
    .subscribe(data => {
      this.transports = data;
    }, error => {
      console.log(error);
      alert(error.error.message);
    }) 
  }

  Add(transport: Transport, index: number){
    
    console.log(transport);
    this.transportService.addTransportTransporter(this.info.username, transport.id_transport)
    .subscribe(data => {
      this.transports.splice(index, 1);
      this.submitted = true;
      this.errorUser = false;
    }, error => {
      this.submitted = true;
      this.errorUser = true;
      console.log(error);
      this.errorMessageUser = error.error.message;
    })

    setTimeout(() => {
      this.submitted = false;
    }, 10000);
  }

  AddNew(){
    this.isAdd = !this.isAdd;
  }

  transportUpdate(newTransport: any){
      this.isAdd = false;
      console.log(newTransport);
  }

}
