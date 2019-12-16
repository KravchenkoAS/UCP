import { Component, OnInit, Input } from '@angular/core';
import { TransportService } from 'src/app/services/transport/transport.service';

@Component({
  selector: 'app-my-trp',
  templateUrl: './my-trp.component.html',
  styleUrls: ['./my-trp.component.css']
})
export class MyTrpComponent implements OnInit {

  @Input() info: any;

  transportsTransporter: Transport[];
  isDelete: boolean = false;

  constructor(private transportService: TransportService) { }

  ngOnInit() {
    this.transportService.getTransportsTransporter(this.info.username)
    .subscribe(data => {
      console.log(data.length);
      this.transportsTransporter = data;
    }, error => {
      console.log(error);
      alert(error.error.message);
    }) 
  }

  Delete(id_transport: number, index: number) {
    this.transportService.deleteTransportTransporter(this.info.username, id_transport)
    .subscribe(data => {
      console.log(data);
      this.isDelete = true;
      this.transportsTransporter.splice(index, 1);
    }, error => {
      console.log(error);
      alert(error.error.message);
    })

    setTimeout(() => {
      this.isDelete = false;
    }, 10000);

  }


}
