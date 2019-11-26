import { Component, OnInit, Input } from '@angular/core';
import { Fuel } from 'src/app/services/fuel/fuel';
import { FuelService } from 'src/app/services/fuel/fuel.service';

@Component({
  selector: 'app-fuel',
  templateUrl: './fuel.component.html',
  styleUrls: ['./fuel.component.css']
})
export class FuelComponent implements OnInit {

  @Input() authority: any;

  fuels: Fuel[];
  isChange: boolean = false;
  fuelChange: Fuel;
  isAdmin: boolean = false;

  constructor(private fuelService: FuelService) { }

  ngOnInit() {
    let isAdmin = false;
    this.authority.forEach(function (value) {
      if (value == "ROLE_ADMIN") {
        isAdmin = true;
      }
    });
    this.isAdmin = isAdmin;

    this.fuelService.getAllFuels()
    .subscribe(data => {
      console.log(data.length);
      this.fuels = data;
    },
    error => {
      console.log(error);
      alert(error.error.message);
    })
  }

  changePrice(fuelChange: Fuel){
    console.log(fuelChange);
    this.fuelChange = fuelChange;
    this.isChange = true;
    console.log(this.isChange);
  }

  isControlInvalid(controlName: any): boolean {
    const control = /^[\d.]+$/.test(controlName);
    console.log(control);
    return control;
  }

  saveChange(fuelChange: Fuel) {
    if(this.isControlInvalid(fuelChange.price)){
      this.fuels.forEach(function (fuel) {
        if (fuel.id_fuel == fuelChange.id_fuel) {
          fuel.price = fuelChange.price;
        }
      });
      this.fuelService.updateFuel(fuelChange.id_fuel, fuelChange)
      .subscribe(data => {
        this.isChange = false;
      },
      error => {
        console.log(error);
        alert(error.error.message);
      });
    }
    }

}
