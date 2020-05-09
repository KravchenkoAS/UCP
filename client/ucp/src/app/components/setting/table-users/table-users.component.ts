import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/services/user/user';
import { UserService } from 'src/app/services/user/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-table-users',
  templateUrl: './table-users.component.html',
  styleUrls: ['./table-users.component.css']
})
export class TableUsersComponent implements OnInit {

  users = new Array<User>();
  submitted = false;
  error = false;
  errorMessage: string;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {

    this.userService.getAllUser()
      .subscribe( data => {
        this.users = data;
        console.log(this.users);
      }, error => {
        this.submitted = this.error = true;
        this.errorMessage = error.error.message;
        console.log(error);
    })

    if (this.submitted) {
      setTimeout(() => {
        this.submitted = this.error = false;
      }, 10000);
    }

  }

  saveUsers() {
    console.log(this.users);
    this.userService.saveUsers(this.users)
      .subscribe(data => {
        this.submitted = true;
      }, error => {
        this.submitted = this.error = true;
        this.errorMessage = error.error.message;   
    })

    if (this.submitted) {
      setTimeout(() => {
        this.submitted = this.error = false;
      }, 10000);
    }

  }

  isCheckedBlock(index: number) {
    console.log(index);
    this.users[index].isActive = !this.users[index].isActive;
  }

}
