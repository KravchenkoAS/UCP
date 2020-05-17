import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Employee } from 'src/app/services/employee/employee';
import { EmployeeService } from 'src/app/services/employee/employee.service';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  userForm: FormGroup;
  employee: Employee = new Employee();

  submitted = false;
  error = false;
  message: string;

  constructor(private employeeService: EmployeeService, private fb: FormBuilder) { }

  ngOnInit() {
    this.initForm();
  }

  isControlInvalidUserForm(controlName: string): boolean {
    const control = this.userForm.controls[controlName];
    const result = control.invalid && control.touched;
    return result;
  }

  onSubmit() {
    const controls = this.userForm.controls;

    if (this.userForm.invalid) {
      Object.keys(controls)
        .forEach(controlName => controls[controlName].markAsTouched());

      return;
    }

    this.save();

  }

  save() {
    this.error = false;

    if (this.userForm.value.password != this.userForm.value.confirmPassword) {
      this.message = "Пароли не совпадают";
      this.error = true;
    } else {
      this.employee.init(this.userForm.getRawValue());
    
      this.employeeService.createEmployee(this.employee)
        .subscribe( data => { this.message = "Новый сотрудник зарегистрирован.";  }, 
          error => {
            this.error = true;
            console.log(error);
            if (error.status >= 400 && error.status < 500) {
              this.message = error.error;
            } else {
              this.message = "Ошибка сервера.";
              alert(error.error);
            }
        })

    }

    this.submitted = true;

    setTimeout(() => {
      this.submitted = false;
    }, 10000);
  }

  /** Инициализация формы*/

  initForm() {
    this.userForm = this.fb.group({
      role: [null],
      username: [null],
      email:  [null],
      name:  [null],
      surname: [null],
      password:  [null],
      confirmPassword: [null]
    });

    this.userForm = this.fb.group({
      role: ['', [
        Validators.required,
        // Validators.pattern(/^[\w_-\s]+$/ )
      ]],
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
      ]],
      password: ['', [
        Validators.required,
        Validators.pattern(/^[\w\s]+$/ )
      ]],
      confirmPassword: ['', [
        Validators.required,
        Validators.pattern(/^[\w\s]+$/ )
      ]]
    });
  }

}
