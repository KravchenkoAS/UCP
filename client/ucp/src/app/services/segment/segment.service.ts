import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SegmentCreate } from './segment';

@Injectable({
  providedIn: 'root'
})
export class SegmentService {

  private baseUrl = 'http://localhost:8080/api/test/analyst';
  private baseUrl_2 = 'http://localhost:8080/api/test';

  constructor(private http: HttpClient) { }

  createSegment(segmentForm: any): Observable<any> {                
    let segmentCreate: SegmentCreate = new SegmentCreate();
    segmentCreate.init(segmentForm);
        return this.http.post(`${this.baseUrl}/createSegment`, segmentCreate);     
  }  
  
  removeSegment(id_segment: number): Observable<any> {                         
    return this.http.delete(`${this.baseUrl}/removeSegment/${id_segment}`,                     
                            { responseType: 'text' });                  
  }  

  calculate(segmentForm: any): Observable<any> { 
    let segmentCreate: SegmentCreate = new SegmentCreate();
    segmentCreate.init(segmentForm);
        return this.http.post(`${this.baseUrl}/calculate`, segmentCreate);     
  } 

  saveAllSegments(id_order: number, segments: Map<number, SegmentCreate>){
    let array: Array<SegmentCreate> = new Array();
    segments.forEach(function (segment) {
      array.push(segment);
    })
    console.log(array);
    return this.http.post(`${this.baseUrl}/saveAllSegments/${id_order}`, array
    , { headers: { 'Content-Type': 'application/json' } });
  }

  calculateAllPrice(segments: Map<number, SegmentCreate>): number{
    let price: number = 0;
    segments.forEach(function (segment: SegmentCreate) {
      price += segment.price;
    });
    return price;
  }

  calculateAllTime(segments: Map<number, SegmentCreate>): number{
    let time: number = 0;
    segments.forEach(function (segment: SegmentCreate) {
      time += segment.time;
    });
    return time;
  }

  calculateAllDistance(segments: Map<number, SegmentCreate>): number{
    let distance: number = 0;
    segments.forEach(function (segment: SegmentCreate) {
      distance += +segment.distance;
    });
    return distance;
  }

  getSegments(id_segments: Array<number>): Observable<any>{
    return this.http.post(`${this.baseUrl_2}/segment/getSegments`, id_segments); 
  }
}
