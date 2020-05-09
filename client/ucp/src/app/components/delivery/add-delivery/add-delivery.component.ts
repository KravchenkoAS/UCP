import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { OrderService } from "../../../services/order/order.service";
import { Point } from 'src/app/services/point/point';
import { PointService } from 'src/app/services/point/point.service';

@Component({
  selector: 'app-add-delivery',
  templateUrl: './add-delivery.component.html',
  styleUrls: ['./add-delivery.component.css']
})

export class AddDeliveryComponent implements OnInit {

  myFirstReactiveForm: FormGroup;
  submitted = false;
  points:Point[];

  constructor(private fb: FormBuilder, private orderService: OrderService,
    private pointService: PointService) { }

  ngOnInit() {
    this.reloadPoint();
    this.initForm();

    this.myFirstReactiveForm = this.fb.group({
      type: ['', [
        Validators.required
      ]],
      name: ['', [
        Validators.required,
        Validators.pattern(/^[\wА-я-\s]+$/ )
      ]],
      length: [0, [
        Validators.required,
        Validators.pattern(/^[\d.]+$/ )
      ]],
      width: [0, [
        Validators.required,
        Validators.pattern(/^[\d.]+$/ )
      ]],
      height: [0, [
        Validators.required,
        Validators.pattern(/^[\d.]+$/ )
      ]],
      weight: ['', [
        Validators.required,
        Validators.pattern(/^[\d.]+$/ )
      ]],
      amount: ['', [
        Validators.required,
        Validators.pattern(/^[\d]+$/ )
      ]],
      startPoint: ['', [
        Validators.required
      ]],
      endPoint: ['', [
        Validators.required
      ]],
      date: [null],
      isContainer: [false],
      isDocuments: [false],
      stack: [false],
      express: [false]
    });

  }

  isControlInvalid(controlName: string): boolean {
    const control = this.myFirstReactiveForm.controls[controlName];
    const result = control.invalid && control.touched;
    return result;
  }

  onSubmit() {
    const controls = this.myFirstReactiveForm.controls;

    /** Проверяем форму на валидность */
    if (this.myFirstReactiveForm.invalid) {
      /** Если форма не валидна, то помечаем все контролы как touched*/
      Object.keys(controls)
        .forEach(controlName => controls[controlName].markAsTouched());

      /** Прерываем выполнение метода*/
      return;
    }

    /** TODO: Обработка данных формы */
    this.save();

  }

  save(){

    if(this.myFirstReactiveForm.value.type === 'Наливной'){
      this.myFirstReactiveForm.value.length = null;
      this.myFirstReactiveForm.value.width = null;
      this.myFirstReactiveForm.value.height = null;
    } else if (this.myFirstReactiveForm.value.length <= 0 ||
                this.myFirstReactiveForm.value.width <= 0 ||
                this.myFirstReactiveForm.value.height <= 0){
      alert("Ошибка! Данные о габаритах груза некорректны.");
      return false;
    } 

    this.orderService.createOrder(this.myFirstReactiveForm.value)
      .subscribe(
        data => {
          this.submitted = true;
        },
        error => {
          this.submitted = false;
          alert(error.error.message);
        })
  }

  /** Инициализация формы*/

  initForm() {
    this.myFirstReactiveForm = this.fb.group({
      type: [null],
      name: [null],
      length: [null],
      width: [null],
      height: [null],
      weight: [null],
      amount: [null],
      startPoint: [null],
      endPoint: [null],
      date: [null],
      isContainer: [null],
      isDocuments: [null],
      stack: [null],
      express: [null]
    });
  }

  reloadPoint() {
    this.pointService.getPointList()
    .subscribe(points => this.points = points);
  }

}