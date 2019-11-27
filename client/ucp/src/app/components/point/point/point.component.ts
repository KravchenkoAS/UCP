import { Component, OnInit, Input } from '@angular/core';
import { Point } from 'src/app/services/point/point';
import { PointService } from 'src/app/services/point/point.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-point',
  templateUrl: './point.component.html',
  styleUrls: ['./point.component.css']
})
export class PointComponent implements OnInit {

  @Input() authority: any;

  points: Point[];
  isAdmin: boolean = false;
  isAdd:boolean = false;
  pointForm: FormGroup;

  constructor(private pointService: PointService,
    private fb: FormBuilder) { }

  ngOnInit() {
    let isAdmin: boolean = false;
    this.authority.forEach(function (value) {
      if (value == "ROLE_ADMIN") {
        isAdmin = true;
      }
    });
    this.isAdmin = isAdmin;

    this.pointService.getPointList()
    .subscribe(
      data => {
        this.points = data;
      }, error => {
        console.log(error);
        alert(error.error);
      }
    )
  }

  Add(){
    this.initForm();
    this.isAdd = !this.isAdd;
  }

  isControlInvalid(name: any): boolean {
    const control = /^[А-я]+$/.test(name);
    return control;
  }

  Save() {
    if(this.isControlInvalid(this.pointForm.value.city) 
    && this.isControlInvalid(this.pointForm.value.country)){
      let point = new Point();
      point.city = this.pointForm.value.city;
      point.country = this.pointForm.value.country;
      this.pointService.createPoint(point)
      .subscribe(
        data => {
          this.points.push(data);
        }, error => {
          console.log(error);
          alert(error.error);
        })
      this.isAdd = !this.isAdd;
    } 
  }

  onSubmit() {
    const controls = this.pointForm.controls;

    /** Проверяем форму на валидность */
    if (this.pointForm.invalid) {
      /** Если форма не валидна, то помечаем все контролы как touched*/
      Object.keys(controls)
        .forEach(controlName => controls[controlName].markAsTouched());

      /** Прерываем выполнение метода*/
      return;
    }

    this.Save();
    /** TODO: Обработка данных формы */
  }

  Delete(point: Point, i: number) {
    this.pointService.deletePoint(point)
    .subscribe(data => {
      this.points.splice(i, 1);
    }, error => {
      console.log(error);
      alert(error.error.message);
    })
  }

   /** Инициализация формы*/

   initForm() {
    this.pointForm = this.fb.group({
      city: [null],
      country: [null]
    });

    this.pointForm = this.fb.group({
      city: ['', [
        Validators.required,
        Validators.pattern(/^[А-я-\s]+$/ )
      ]],
      country: ['', [
        Validators.required,
        Validators.pattern(/^[А-я-\s]+$/ )
      ]]
    });
  }

}
