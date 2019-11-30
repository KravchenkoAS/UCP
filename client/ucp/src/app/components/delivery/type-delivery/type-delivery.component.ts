import { Component, OnInit, Input } from '@angular/core';
import { TypeDelivery } from 'src/app/services/typeDelivery/typeDelivery';
import { TypeDeliveryService } from 'src/app/services/typeDelivery/type-delivery.service';

@Component({
  selector: 'app-type-delivery',
  templateUrl: './type-delivery.component.html',
  styleUrls: ['./type-delivery.component.css']
})
export class TypeDeliveryComponent implements OnInit {

  @Input() authority: any;

  typeDelivery: TypeDelivery[];
  isAdmin: boolean = false;
  isAdd:boolean = false;


  constructor(private typeDeliveryService: TypeDeliveryService) { }

  ngOnInit() {
    let isAdmin: boolean = false;
    this.authority.forEach(function (value) {
      if (value == "ROLE_ADMIN") {
        isAdmin = true;
      }
    });
    this.isAdmin = isAdmin;

    this.typeDeliveryService.getAllTypeDeliveries()
    .subscribe(
      data => {
        this.typeDelivery = data;
      }, error => {
        console.log(error);
        alert(error.error);
      }
    )
  }

  Add(type: TypeDelivery){
    this.isAdd = !this.isAdd;
  }

  isControlInvalid(name: any): boolean {
    const control = /^[А-я]+$/.test(name);
    return control;
  }

  Save(name: string) {
    if(this.isControlInvalid(name)){
     
      let typeDelivery = new TypeDelivery();
      typeDelivery.name = name;
      this.typeDeliveryService.createTypeDelivery(typeDelivery)
      .subscribe(
        data => {
          this.typeDelivery.push(data);
        }, error => {
          console.log(error);
          alert(error.error);
        })
      this.isAdd = !this.isAdd;
    }
    
  }

  Delete(type: TypeDelivery, i: number) {
    this.typeDeliveryService.deleteTypeDelivery(type)
    .subscribe(data => {
      this.typeDelivery.splice(i, 1);
    }, error => {
      console.log(error);
      alert(error.error.message);
    })
  }

}
