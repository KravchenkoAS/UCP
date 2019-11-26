import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { SegmentService } from 'src/app/services/segment/segment.service';
import { SegmentCreate, SegmentDetailsDTO } from 'src/app/services/segment/segment';

@Component({
  selector: 'app-route-ditails',
  templateUrl: './route-ditails.component.html',
  styleUrls: ['./route-ditails.component.css']
})
export class RouteDitailsComponent implements OnInit {

  @Input() id_segment: Array<number>
  
  segments: SegmentDetailsDTO[];
  segmentsLength: number = 0;

  constructor(private segmentService: SegmentService) { }

  ngOnInit() {
    this.segmentService.getSegments(this.id_segment)
    .subscribe(
      data => {
        this.segments = data;
        this.segmentsLength = this.segments.length;
      }, error => {
        console.log(error);
        alert(error.error.message);
      }
    )
  }

}
