import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-setting',
  templateUrl: './setting.component.html',
  styleUrls: ['./setting.component.css']
})
export class SettingComponent implements OnInit {

  roles: string[] = []; 

  constructor(private tokenStorage: TokenStorageService,
    private router: Router,
    private route: ActivatedRoute) { }                 

  ngOnInit() {
    if (this.tokenStorage.getToken()) {                                   
      this.roles = this.tokenStorage.getAuthorities();                    
    }   
    
    if (this.router.url.toString() == "/settings"){
      this.router.navigate(['./profile'], {relativeTo: this.route});
    }
  }

}
