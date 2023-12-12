import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { take } from 'rxjs';
import { ProductsService } from '../../service/products.service';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-loging-page',
  templateUrl: './loging-page.component.html',
  styleUrl: './loging-page.component.css'
})
export class LogingPageComponent implements OnInit {


  email: string = "";
  password: string = "";


  constructor(
    private router:Router,
    private prservice:ProductsService,
    private snackBar: MatSnackBar
  ) { }
  ngOnInit(): void {
  }
  signIn(): void {
    const pattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/; 
    if (!pattern.test(this.email)) {
      alert("Email is not valid.");
      return;
    }
    if (this.password === '') {
      alert("Password should not be blank");
      return;
    }
    //alert("sucess")
    const body = {
      "adminEmailId": this.email,
      "adminPassword": this.password
    }
    
    this.prservice.adminSignIn(body).pipe(take(1)).subscribe((res :any) => {
      console.log("*****",res);
      if(res && res?.adminId){
        let userName = '';
        if (res?.firstName) {
          userName+=res?.firstName;
        }
        if (res?.lastName){
          userName+=' ' + res?.lastName;
        }
        this.prservice.storeAdminUserName(userName);
        this.prservice.storeAdminEmail(res.adminEmailId);
        this.prservice.storeAdminAuthorization(res?.adminId);
        this.router.navigate(['/admin/home']);
       
      }
    }, err =>{
      console.log("Error: ", err);
this.snackBar.open("Something went wrong in login! Please try again.", "OK", {
  duration: 8000, // Duration in milliseconds
  verticalPosition: 'bottom', // Position: 'top', 'bottom'
  horizontalPosition: 'center', // Position: 'start', 'center', 'end', 'left', 'right'
});
    })
  }




}
