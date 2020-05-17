import { Component, OnInit } from '@angular/core';
import { Route } from 'src/app/services/route/route';
import { RouteService } from 'src/app/services/route/route.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Point } from 'src/app/services/point/point';
import { PointService } from 'src/app/services/point/point.service';
import { WayDTO } from 'src/app/services/route/Dictionary';
import { TokenStorageService } from 'src/app/auth/token-storage.service';

@Component({
  selector: 'app-route-list',
  templateUrl: './route-list.component.html',
  styleUrls: ['./route-list.component.css']
})
export class RouteListComponent implements OnInit {

  info: any; 
  authority: string;
  isActive: boolean = false;
  route = new Route();
  startPoint = new Point();
  endPoint = new Point();
  wayDTO: WayDTO[];
  id_segment: Array<number>;
  numberWayDTO: number = 0;
  id_order: number;

  constructor(private routeService: RouteService,
    private ActivatedRouter: ActivatedRoute,
    private router: Router,
    private pointService: PointService,
    private token: TokenStorageService) {
      
     }

  ngOnInit() {

    this.info = {                                                     
      token: this.token.getToken(),                                   
      username: this.token.getUsername(),                             
      authorities: this.token.getAuthorities()                        
    };
    
    this.controlAuthority();

    this.id_order = +this.ActivatedRouter.snapshot.paramMap.get('id');

    this.routeService.getRoute(this.id_order)
      .subscribe(
        data => {
          this.route = data;
          this.getPoint(this.route.startPoint, this.startPoint);
          this.getPoint(this.route.endPoint, this.endPoint);
          this.getDictionaryList(this.route.id_route);
        }, error => {
          console.log(error);
          alert(error.error.message);
        })
  }

  active(way: WayDTO, index: number) {
    this.isActive = !this.isActive;
    if(this.isActive) {
      this.numberWayDTO = index;
      this.id_segment = way.id_segment;
      this.router.navigate([], {
        queryParams:{
          'way': way.way
        }
      })
    } else {
      this.router.navigateByUrl(
        this.router.url.substring(0, this.router.url.indexOf("?"))
        ); 
        this.numberWayDTO = 0;
    }
  }

  getPoint(id_point: number, point: Point) {
    this.pointService.getPoint(String(id_point))
      .subscribe(
        data => {
          point.id_point = data.id_point;
          point.city = data.city;
          point.country = data.country;
        },
        error => {
          console.log(error);
          alert(error.error.message);
        })
  }

  getDictionaryList(id_route: number) {
    this.routeService.getDictionaryList(id_route)
       .subscribe(
         data => {
          this.wayDTO = data;
        }, error => {
          console.log(error);
          alert(error.error.message);
        })
  }
  

  controlAuthority() {
    this.info.authorities.every(role => {                                        
      if (role === 'ROLE_ADMIN') {                                    
        this.authority = 'admin';                                                 
      } else if (role === 'ROLE_ANALYST') {
        this.authority = 'analyst';
      } else if (role === 'ROLE_CLIENT') {
        this.authority = 'client'; 
      } else {
        alert('Ошибка авторизации');
        console.log('Ошибка авторизации' + role);
        this.authority = 'client'
      }                                            
    });                                                               
  }

  deleteWay(numberWayDTO: number){
    let i = -1;
    let index = 0;
    this.wayDTO.forEach(function (way: WayDTO) {
      if(way.way == numberWayDTO) {
        i = index;
      }
      index++;
    })

    if (i >= 0) {
      this.routeService.deleteWay(this.route.id_route, numberWayDTO)
        .subscribe(
          data => {
            alert(data);
            this.wayDTO.splice(i, 1);
            this.isActive = false;
          }, error => {
            console.log(error);
            alert(error.error.message);
            i = -1;
          })

          console.log(this.wayDTO)
    }  
  }

  submitOn(numberWayDTO: number){
    this.routeService.submit(this.id_order, this.route.id_route, numberWayDTO)
    .subscribe(
      data => {
        this.wayDTO.length = 0;
        this.wayDTO.push(data);
        this.isActive = false;
      }, error => {
        console.log(error);
        alert(error.error.message);
      })

    this.route.distance = this.wayDTO[0].distance;
    this.route.time = this.wayDTO[0].time;
    this.route.price = this.wayDTO[0].price;
  }
}