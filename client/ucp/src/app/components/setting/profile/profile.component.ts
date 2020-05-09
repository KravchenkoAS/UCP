import { Component, OnInit } from '@angular/core';
import { User, ChangePassword } from 'src/app/services/user/user';
import { UserService } from 'src/app/services/user/user.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { TokenStorageService } from 'src/app/auth/token-storage.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user = new User();
  changePassword = new ChangePassword();
  submitted = false;
  submittedPass = false;
  errorPass = false;
  errorUser = false;
  errorMessageUser: string;
  messagePass: string;

  userForm: FormGroup;
  passForm: FormGroup;

  constructor (private tokenStorage: TokenStorageService,
                private userServise: UserService, private fb: FormBuilder) { }

  ngOnInit() {

    this.initForm(this.user);
    console.log(this.tokenStorage.getAuthorities());
    console.log(this.tokenStorage.getAuthorities()[0]);
    this.userServise.getUser(this.tokenStorage.getUsername(), this.tokenStorage.getAuthorities()[0])
    .subscribe(
      data => {
        this.user = data;
        this.initForm(data);
      }, error => {
        console.log(error);
        alert(error.error.message);
      }
    ); 

  }

  Save(){

    if (this.user.email == this.userForm.value.email &&
        this.user.name == this.userForm.value.name &&
        this.user.surname == this.userForm.value.surname) {
        
          this.errorUser = true;
          this.errorMessageUser = "Ошибка ввода! Необходимо внести изменения.";
    } else if (this.userForm.status == "VALID") {
      
      console.log("VALID");
      this.user.email = this.userForm.value.email;
      this.user.name = this.userForm.value.name;
      this.user.surname = this.userForm.value.surname;
      this.userServise.updateUser(this.user)
      .subscribe( data => {
          this.errorUser = false;
        }, error => {
          console.log(error);
          this.errorUser = true;
          if (error.status == 400) {
            this.errorMessageUser = error.error.message;
          } else {
            this.errorMessageUser = "Ошибка сервера.";
            alert(error.error);
          }
        })
    } else if (this.userForm.value.name == "" || this.userForm.value.name == null
                || this.userForm.value.surname == "" || this.userForm.value.surname == null) {
     
      this.user.email = this.userForm.value.email;
      this.user.name = this.userForm.value.name;
      this.user.surname = this.userForm.value.surname;

      this.userServise.updateUser(this.user)
      .subscribe( data => {
          this.errorUser = false;
        }, error => {
          this.errorUser = true;
          console.log(error);
          if (error.status == 400) {
            this.errorMessageUser = error.error.message;
          } else {
            this.errorMessageUser = "Ошибка сервера.";
            alert(error.error);
          }
        })
    } else {
      this.errorUser = true;
      this.errorMessageUser = "Ошибка ввода!";
    }
   
    this.submitted = true;

    setTimeout(() => {
      this.submitted = false;
    }, 10000);
  }

  SavePass(){
    this.submittedPass = false;
    if (this.passForm.value.oldPassword == null ||
      this.passForm.value.newPassword == null ||
      this.passForm.value.confirmNewPassword == null) {
        this.submittedPass = false;
        alert("Введите пароль.");
        this.passForm.reset();
    } else if (this.passForm.value.oldPassword ==
      this.passForm.value.newPassword) {
        this.submittedPass = false;
        alert("Новый и старый пароль совпадают.");
        this.passForm.reset();
    } else if (this.passForm.value.newPassword !=
      this.passForm.value.confirmNewPassword) {
        alert("Новые пароль не подтвержден.");
        this.passForm.reset();
    } else {
       this.changePassword.newPassword = this.passForm.value.newPassword;
       this.changePassword.oldPassword = this.passForm.value.oldPassword
       this.changePassword.roleUser = this.tokenStorage.getAuthorities()[0];
       this.userServise.updatePassword(this.user.id_user, this.changePassword)
       .subscribe( data => {
         this.submittedPass = true;
         this.errorPass = false;
         this.messagePass = "Пароль изменен.";
        }, error => {
          if (error.status == 400) {
            this.submittedPass = true;
            this.errorPass = true;
            this.messagePass = error.error;
            console.log(error);
          } else {
            this.submittedPass = false;
            alert(error);
          }
        })

        if (!this.errorPass) {
          setTimeout(() => {
            this.submittedPass = false;
          }, 10000);
        }

        this.passForm.reset();
       
    }
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

  initForm(user: User) {
    this.userForm = this.fb.group({
      username: this.user.username,
      email:  this.user.email,
      name:  this.user.name,
      surname: this.user.surname
    });

    this.passForm = this.fb.group({
      oldPassword: [null],
      confirmNewPassword: [null],
      newPassword:  [null]
    });

    this.userForm = this.fb.group({
      username: [user.username, [
        Validators.required,
        Validators.pattern(/^[\w_-\s]+$/ )
      ]],
      name: [user.name, [
        Validators.required,
        Validators.pattern(/^[A-zА-я-\s]+$/ )
      ]],
      surname: [user.surname, [
        Validators.required,
        Validators.pattern(/^[A-zА-я-\s]+$/ )
      ]],
      email: [user.email, [
        Validators.required,
        Validators.pattern(/^[0-9a-z-\.]+\@[0-9a-z-]{2,}\.[a-z]{2,}$/i)
      ]]
    });

    this.passForm = this.fb.group({
      oldPassword: ['', [
        Validators.pattern(/^[\w\s]+$/ )
      ]],
      confirmNewPassword: ['', [
        Validators.pattern(/^[\w\s]+$/ )
      ]],
      newPassword: ['', [
        Validators.pattern(/^[\w\s]+$/ )
      ]]
    });
  }

}
