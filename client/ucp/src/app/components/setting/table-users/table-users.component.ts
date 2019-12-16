import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/services/user/user';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-table-users',
  templateUrl: './table-users.component.html',
  styleUrls: ['./table-users.component.css']
})
export class TableUsersComponent implements OnInit {

  users = new Array<User>();

  constructor(private userServise: UserService) { }

  ngOnInit() {
    this.userServise.getAllUser()
    .subscribe( data => {
      this.users = data;
      console.log(this.users);
    }, error => {
      console.log(error);
      alert(error.error.message);
    })
  }


  isChecked(role: string, user: User) {
    if (user.role == role){
      return true;
    } else return false
  }

  CheckedRole(role: string, id_user: number) {
    console.log(id_user + " " + role);
    // this.userCount = 0;

    // this.users.forEach(user => {
    //   if (user.user) {
    //     this.userCount++;
    //   }
    // });

    // return false;
  }

  isCheckedBlock(id_user: number) {
    console.log(id_user);
    // if (isBlock == "unblock") {
    //   this.users[index].active = true;
    //   this.blockCount--;
    //   this.userCount++;

    //   this.userService.editUser(this.users[index])
    //   .subscribe((response) => {
    //      console.log(response);
    //   }, (error) => {
    //     console.log(error);
    //   });

    // } else if (isBlock == "block") {
    //   this.users[index].active = false;
    //   this.blockCount++;
    //   this.userCount--;

    //   this.userService.editUser(this.users[index])
    //   .subscribe((response) => {
    //      console.log(response);
    //   }, (error) => {
    //     console.log(error);
    //   });
    // }
    // console.log(this.users[index].admin);

    // return false;
  }

}
