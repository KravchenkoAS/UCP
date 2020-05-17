import { Component, OnInit, Input } from '@angular/core';
import { Fuel } from 'src/app/services/fuel/fuel';
import { FuelService } from 'src/app/services/fuel/fuel.service';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';

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
  isAdd:boolean = false;
  fuelForm: FormGroup;

  constructor(private fuelService: FuelService,
    private fb: FormBuilder) { }

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
    this.fuelChange = fuelChange;
    this.isChange = true;
  }

  saveChange(fuelChange: Fuel) {
    this.fuels.forEach(function (fuel) {
      if (fuel.id_fuel == fuelChange.id_fuel) {
        fuel.price = fuelChange.price;
      }
    });
    this.fuelService.updateFuel(fuelChange.id_fuel, fuelChange)
    .subscribe(data => {
      this.isChange = false;
    }, error => {
      console.log(error);
      alert(error.error.message);
    });
  }

  Save(){
    this.fuelService.createFuel(this.fuelForm.value)
    .subscribe(
      data => {
        this.fuels.push(data);
      }, error => {
        console.log(error);
        alert(error.error);
      }
    )
  }

  Add(){
    this.initForm();
    this.isAdd = !this.isAdd;
    this.initFuelForm();
  }

  isControlInvalid(controlName: string): boolean {
    const control = this.fuelForm.controls[controlName];
    const result = control.invalid && control.touched;
    return result;
  }

  onSubmit() {
    const controls = this.fuelForm.controls;

    /** Проверяем форму на валидность */
    if (this.fuelForm.invalid) {
      /** Если форма не валидна, то помечаем все контролы как touched*/
      Object.keys(controls)
        .forEach(controlName => controls[controlName].markAsTouched());

      /** Прерываем выполнение метода*/
      return;
    }

    /** TODO: Обработка данных формы */
    this.Save();

  }

  Delete(fuel: Fuel, i: number) {
    this.fuelService.deleteFuel(fuel.id_fuel)
    .subscribe(data => {
      this.fuels.splice(i, 1);
    }, error => {
      console.log(error);
      alert(error.error.message);
    })
  }

  initFuelForm(){
    this.fuelForm = this.fb.group({
      name: ['', [
        Validators.required
      ]],
      price: [0, [
        Validators.required,
        Validators.pattern(/^[\d.]+$/ )
      ]]
    });
  }

  /** Инициализация формы*/

  initForm() {
    this.fuelForm = this.fb.group({
      name: [null],
      price: [null]
    });
  }

}
