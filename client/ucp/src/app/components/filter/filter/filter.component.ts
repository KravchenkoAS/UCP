import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Point } from 'src/app/services/point/point';
import { PointService } from 'src/app/services/point/point.service';
import { Filter } from 'src/app/services/filter/filter';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css']
})
export class FilterComponent implements OnInit {

  @Output() filter = new EventEmitter<Filter>();

  formFilter: FormGroup;
  points: Point[];

  constructor(private fb: FormBuilder,
    private pointService: PointService) { }

  ngOnInit() {
    this.reloadPoint();
    this.initForm();

    this.formFilter = this.fb.group({
      startPoint: ["null", [
        Validators
      ]],
      endPoint: ["null", [
        Validators
      ]],
      status:  ["null", [
        Validators
      ]],
    });
  }

  isControlInvalid(controlName: string): boolean {
    const control = this.formFilter.controls[controlName];
    const result = control.invalid && control.touched;
    return result;
  }

  onSubmit() {
    console.log("onSubmit");

    const controls = this.formFilter.controls;

    /** Проверяем форму на валидность */
    if (this.formFilter.invalid) {
      /** Если форма не валидна, то помечаем все контролы как touched*/
      Object.keys(controls)
        .forEach(controlName => controls[controlName].markAsTouched());

      /** Прерываем выполнение метода*/
      return;
    }

    /** TODO: Обработка данных формы */
    console.log(this.formFilter.value);
    let filter = new Filter();
    filter.startPoint = this.formFilter.value.startPoint;
    filter.endPoint = this.formFilter.value.endPoint;
    filter.status = this.formFilter.value.status;
    this.filter.emit(filter);

  }

  /** Инициализация формы*/

  initForm() {
    this.formFilter = this.fb.group({
      startPoint: [null],
      endPoint: [null],
      status: [null]
    });
  }

  reloadPoint() {
    this.pointService.getPointList()
    .subscribe(points => this.points = points);
  }

}
