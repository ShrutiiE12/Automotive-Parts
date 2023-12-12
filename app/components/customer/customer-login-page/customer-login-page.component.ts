// customer-login-page.component.ts
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { take } from 'rxjs';
import { ProductsService } from '../../service/products.service';

@Component({
  selector: 'app-customer-login-page',
  templateUrl: './customer-login-page.component.html',
  styleUrls: ['./customer-login-page.component.css']
})
export class CustomerLoginPageComponent implements OnInit {
  email: string = "";
  password: string = "";
  customerLoginForm: FormGroup;

  constructor(
    private router: Router,
    private prservice: ProductsService,
    private fb: FormBuilder
  ) {
    const pattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    this.customerLoginForm = this.fb.group({
      email: ['', [Validators.required, Validators.pattern(pattern)]],
      password: [null, Validators.compose([Validators.required, Validators.minLength(8)])]
    });
  }

  ngOnInit(): void {
  }

  signIn(): void {
    const body = {
      "emailID": this.customerLoginForm.controls['email'].value,
      "password": this.customerLoginForm.controls['password'].value
    };

    this.prservice.customerSignIn(body).pipe(take(1)).subscribe((res: any) => {
      console.log("*****", res);
      if (res && res?.customerId) {
        this.prservice.storeCustomerAuthorization(res?.customerId);
        let userName = '';
        if (res?.firstName) {
          userName += res?.firstName;
        }
        if (res?.lastName) {
          userName += ' ' + res?.lastName;
        }
        this.prservice.storeCustomerUserName(res.username);
        this.prservice.storeCustomerUserAddress(res.address);
        this.router.navigate(['/customer/home']);
      }
    }, err => {
      console.log("Error  ", err);
      alert("Something went wrong in login! Please try again.");
    });
  }

  routeToNewUser(): void {
    this.router.navigate(['/customer-register']);
  }

  routeToForgotPassword(): void {
    this.router.navigate(['/forgot-password']);
  }
}
