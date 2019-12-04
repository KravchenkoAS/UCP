import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { User, ChangePassword } from 'src/app/services/user/user';
import { UserService } from 'src/app/services/user/user.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-setting',
  templateUrl: './setting.component.html',
  styleUrls: ['./setting.component.css']
})
export class SettingComponent implements OnInit {

  roles: string[] = []; 
  user: User;
  changePassword: ChangePassword;
  submitted = false;
  submittedPass = false;

  userForm: FormGroup;
  passForm: FormGroup;


  constructor(private tokenStorage: TokenStorageService,
    private userServise: UserService,
    private fb: FormBuilder) { }                 // <<<---

  ngOnInit() {
    if (this.tokenStorage.getToken()) {                                   // <<<---
      this.roles = this.tokenStorage.getAuthorities();                    // <<<---
    }      
    this.userServise.getUser(this.tokenStorage.getUsername())
    .subscribe(
      data => {
        this.user = data;
      }, error => {
        console.log(error);
        alert(error.error.message);
      }
    )  
    this.initForm();
  }

  Save(){
    console.log(this.user);
    this.userServise.updateUser(this.user)
      .subscribe(
        data => {
          console.log(data);
          this.submitted = true;
          this.user = data as User;
        },
        error => {
          this.submitted = false;
          console.log(error);
          alert(error.error.message);
        })

        setTimeout(() => {
          this.submitted = false;
        }, 10000);
  }

  SavePass(){
    console.log(this.user);
    this.userServise.updatePassword(this.user.id_user, this.changePassword)
      .subscribe( data => {
          this.submittedPass = true;
        }, error => {
          this.submittedPass = false;
          alert(error.error.message);
        })
        setTimeout(() => {
          this.submittedPass = false;
        }, 10000);
  }

  isControlInvalidPassForm(controlName: string): boolean {
    const control = this.passForm.controls[controlName];
    const result = control.invalid && control.touched;
    return result;
  }

  isControlInvalidUserForm(controlName: string): boolean {
    const control = this.userForm.controls[controlName];
    const result = control.invalid && control.touched;
    return result;
  }

  /** Инициализация формы*/

  initForm() {
    this.userForm = this.fb.group({
      username: [null],
      email:  [null],
      name:  [null],
      surname: [null]
    });

    this.passForm = this.fb.group({
      oldPassword: [null],
      newPassword:  [null]
    });

    this.userForm = this.fb.group({
      username: ['', [
        Validators.required,
        Validators.pattern(/^[\w_-\s]+$/ )
      ]],
      name: ['', [
        Validators.required,
        Validators.pattern(/^[A-zА-я-\s]+$/ )
      ]],
      surname: ['', [
        Validators.required,
        Validators.pattern(/^[A-zА-я-\s]+$/ )
      ]],
      email: ['', [
        Validators.required,
        Validators.pattern(/^[0-9a-z-\.]+\@[0-9a-z-]{2,}\.[a-z]{2,}$/i)
      ]]
    });

    this.passForm = this.fb.group({
      oldPassword: ['', [
        Validators.pattern(/^[\w\s]+$/ )
      ]],
      newPassword: ['', [
        Validators.pattern(/^[\w\s]+$/ )
      ]]
    });
  }

}
