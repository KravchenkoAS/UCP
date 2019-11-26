import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/auth/token-storage.service';

@Component({
  selector: 'app-setting',
  templateUrl: './setting.component.html',
  styleUrls: ['./setting.component.css']
})
export class SettingComponent implements OnInit {

  info: any;                                                          // <<<---

  constructor(private token: TokenStorageService) { }                 // <<<---

  ngOnInit() {
                                 
  }

}
