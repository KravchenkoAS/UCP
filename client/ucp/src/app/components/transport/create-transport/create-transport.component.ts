import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { TypeDelivery } from 'src/app/services/typeDelivery/typeDelivery';
import { TypeDeliveryService } from 'src/app/services/typeDelivery/type-delivery.service';
import { Transport } from 'src/app/services/transport/transport';
import { FuelService } from 'src/app/services/fuel/fuel.service';
import { Fuel } from 'src/app/services/fuel/fuel';
import { TransportService } from 'src/app/services/transport/transport.service';

@Component({
  selector: 'app-create-transport',
  templateUrl: './create-transport.component.html',
  styleUrls: ['./create-transport.component.css']
})
export class CreateTransportComponent implements OnInit {

  @Input() transportChange: Transport;
  @Output() transportUpdate = new EventEmitter<Transport>();

  transportForm: FormGroup;
  typeDelivery: TypeDelivery[];
  fuel: Fuel[];

  constructor(private typeDeliveryService: TypeDeliveryService,
    private fb: FormBuilder,
    private fuelService: FuelService,
    private transportService: TransportService) { }

  ngOnInit() {
    this.typeDeliveryService.getAllTypeDeliveries()
    .subscribe(
      data => {
        this.typeDelivery = data;
      }, error => {
        console.log(error);
        alert(error.error);
      }
    )

    this.fuelService.getAllFuels()
    .subscribe(
      data => {
        this.fuel = data;
      }, error => {
        console.log(error);
        alert(error.error);
      }
    )

    this.initForm();


    if (this.transportChange) {
      this.initTransportFormForChange(this.transportChange);
    } else {
      this.initTransportForm();
    }
  }

  Save(){
    
      this.transportService.updateTransport(this.transportForm.value)
      .subscribe(
        data => {
          this.transportUpdate.emit(data);
        }, error => {
          console.log(error);
          alert(error.error);
        })
    
    
  }

  isControlInvalid(controlName: string): boolean {
    const control = this.transportForm.controls[controlName];
    const result = control.invalid && control.touched;
    return result;
  }

  onSubmit() {
    const controls = this.transportForm.controls;

    /** Проверяем форму на валидность */
    if (this.transportForm.invalid) {
      /** Если форма не валидна, то помечаем все контролы как touched*/
      Object.keys(controls)
        .forEach(controlName => controls[controlName].markAsTouched());

      /** Прерываем выполнение метода*/
      return;
    }

    /** TODO: Обработка данных формы */
    this.Save();

  }

  initTransportFormForChange(transportChange: Transport) {
    this.transportForm = this.fb.group({
      fuel: [transportChange.fuel, [
        Validators.required
      ]],
      type_delivery: [transportChange.type_delivery, [
        Validators.required
      ]],
      name: [transportChange.name, [
        Validators.required,
        Validators.pattern(/^[\wА-я-\s.]+$/ )
      ]],
      speed: [transportChange.speed, [
        Validators.required,
        Validators.pattern(/^[\d]+$/ )
      ]],
      max_volume: [transportChange.max_volume, [
        Validators.required,
        Validators.pattern(/^[\d.]+$/ )
      ]],
      max_weight: [transportChange.max_weight, [
        Validators.required,
        Validators.pattern(/^[\d.]+$/ )
      ]],
      price: [transportChange.price, [
        Validators.required,
        Validators.pattern(/^[\d.]+$/ )
      ]],
      coefficient: [transportChange.coefficient, [
        Validators.required,
        Validators.pattern(/^[\d.]+$/ )
      ]],
      fuel_consumption: [transportChange.fuel_consumption, [
        Validators.required,
        Validators.pattern(/^[\d.]+$/ )
      ]],
      max_width: [transportChange.max_width, [
        Validators.required,
        Validators.pattern(/^[\d.]+$/ )
      ]],
      crewCost: [transportChange.crewCost, [
        Validators.required,
        Validators.pattern(/^[\d.]+$/ )
      ]],
    });
  }

  initTransportForm(){
    this.transportForm = this.fb.group({
      fuel: ['', [
        Validators.required
      ]],
      type_delivery: ['', [
        Validators.required
      ]],
      name: ['', [
        Validators.required,
        Validators.pattern(/^[\wА-я-\s.]+$/ )
      ]],
      speed: [0, [
        Validators.required,
        Validators.pattern(/^[\d]+$/ )
      ]],
      max_volume: [0, [
        Validators.required,
        Validators.pattern(/^[\d.]+$/ )
      ]],
      max_weight: [0, [
        Validators.required,
        Validators.pattern(/^[\d.]+$/ )
      ]],
      price: [0, [
        Validators.required,
        Validators.pattern(/^[\d.]+$/ )
      ]],
      coefficient: [0, [
        Validators.required,
        Validators.pattern(/^[\d.]+$/ )
      ]],
      fuel_consumption: [0, [
        Validators.required,
        Validators.pattern(/^[\d.]+$/ )
      ]],
      max_width: [0,  [
        Validators.required,
        Validators.pattern(/^[\d.]+$/ )
      ]],
      crewCost: [0,  [
        Validators.required,
        Validators.pattern(/^[\d.]+$/ )
      ]]
    });
  }


   /** Инициализация формы*/

   initForm() {
    this.transportForm = this.fb.group({
      fuel: [null],
      type_delivery: [null],
      name: [null],
      speed: [null],
      max_volume: [null],
      max_weight: [null],
      price: [null],
      coefficient: [null],
      fuel_consumption: [null],
      max_width: [null],
      crewCost: [null]
    });
  }

}
