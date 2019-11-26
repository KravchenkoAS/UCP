import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Point } from 'src/app/services/point/point';
import { PointService } from 'src/app/services/point/point.service';
import { SegmentService } from 'src/app/services/segment/segment.service';
import { TypeDelivery } from 'src/app/services/typeDelivery/typeDelivery';
import { TypeDeliveryService } from 'src/app/services/typeDelivery/type-delivery.service';
import { Transport } from 'src/app/services/transport/transport';
import { TransportService } from 'src/app/services/transport/transport.service';
import { SegmentCreate } from 'src/app/services/segment/segment';

export interface myinterface {
  removeSegment(index: number);
  save(index: number, segment: SegmentCreate);
}

@Component({
  selector: 'app-segment',
  templateUrl: './segment.component.html',
  styleUrls: ['./segment.component.css']
})
export class SegmentComponent implements OnInit {

  segmentForm: FormGroup;
  submitted = false;
  points: Point[];
  segment = new SegmentCreate();
  type_deliveres: TypeDelivery[];
  transports: Transport[];

  public index: number;
  public selfRef: SegmentComponent;

  //interface for Parent-Child interaction
  public compInteraction: myinterface;

  constructor(private fb: FormBuilder,
              private pointService: PointService,
              private segmentService: SegmentService,
              private typeDeliveryService: TypeDeliveryService,
              private transportService: TransportService) {
  }

  ngOnInit(){
    this.reloadPoint();
    this.initForm();
    this.reloadTypeDeliveres();
    this.reloadTransports();

    this.segmentForm = this.fb.group({
      type_delivery: ['', [Validators.required]],
      distance: ['', [ Validators.required, 
                      Validators.pattern(/^[\d.]+$/ ) ]],
      time: [0, [ Validators.required,
                  Validators.pattern(/^[\d]+$/ )  ]],
      price: [0, [  Validators.required,
                    Validators.pattern(/^[\d.]+$/ )  ]],
      amount_transport: ['', [ Validators.required,
                                Validators.pattern(/^[\d]+$/ )  ]],
      startPoint: ['', [Validators.required ]],
      endPoint: ['', [Validators.required ]],
      id_transport: ['', [Validators.required ]]
    });

  }

  isControlInvalid(controlName: string): boolean {
    const control = this.segmentForm.controls[controlName];
    const result = control.invalid && control.touched;
    return result;
  }

  onSubmit() {
    const controls = this.segmentForm.controls;

    if (this.segmentForm.invalid) {
      Object.keys(controls)
        .forEach(controlName => controls[controlName].markAsTouched());

        return;
    }

    console.log(this.segmentForm.value);
    this.save();

  }

  calculate() {
    const controls = this.segmentForm.controls;

    if (this.segmentForm.invalid) {
      Object.keys(controls)
        .forEach(controlName => controls[controlName].markAsTouched());

        return;
    }

    this.segmentService.calculate(this.segmentForm.value)
      .subscribe(
        data => {
          this.segmentForm.value.price = data.price;
          this.segmentForm.value.time = data.time;
        },
        error => {
          console.log(error);
          alert(error.error.message);
        })

        console.log(this.segmentForm);

  }

  save(){
    console.log("save");
    if (this.segmentForm.value.price <= 0) {
      this.calculate();
    } 
    console.log(this.segmentForm.value);

    this.segment = this.segmentForm.value;
    // this.segmentService.createSegment(this.segmentForm.value)
    //   .subscribe(
    //     data => {
    //       console.log(data); 
    //       this.submitted = true;
    //       this.segment = data;
    //     },
    //     error => {
    //       this.submitted = false;
    //       console.log(error);
    //       alert(error.error.message);
    //     })

    this.compInteraction.save(this.index, this.segment);

  }

  removeSegment(index) {
    // if (this.segment.id_segment === undefined) {
      this.compInteraction.removeSegment(index)
    // } else {
    //   this.segmentService.removeSegment(this.segment.id_segment)
    //   .subscribe(
    //     data => {
    //       console.log("delete segment " + data.id_segment);
    //     }, error => {
    //       console.log(error);
    //       alert(error.error.message);
    //     }
    //   )
    // }
  }

  /** Инициализация формы*/

  initForm() {
    this.segmentForm = this.fb.group({
      type_delivery: [null],
      time: [null],
      startPoint: [null],
      endPoint: [null],
      id_transport: [null],
      amount_transport: [null],
      distance: [null],
      price: [null]
    });
  }

  reloadPoint() {
    this.pointService.getPointList()
    .subscribe(points => this.points = points);
    console.log("getPointList: " + this.points);
  }

  reloadTypeDeliveres() {
    this.typeDeliveryService.getAllTypeDeliveries()
      .subscribe(
        data => {
          console.log("getAllTypeDeliveres");
          this.type_deliveres = data;
        }, error => {
          console.log(error);
          alert(error.error.message);
        }
      )
  }

  reloadTransports() {
    this.transportService.getAllTransports()
      .subscribe(
        data => {
          console.log(data);
          this.transports = data;
        }, error => {
          console.log(error);
          alert(error.error.message);
        }
      )
  }

}
