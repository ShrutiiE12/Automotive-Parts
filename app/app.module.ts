import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule, MatRippleModule } from '@angular/material/core';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';


import { DatePipe } from '@angular/common';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AppHeaderComponent } from './components/app-header/app-header.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { ContactusComponent } from './components/contactus/contactus.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { PagingComponent } from './components/paging/paging.component';
import { HeaderComponent } from './components/admin/header/header.component';
import { HomeComponent } from './components/admin/home/home.component';
import { LogingPageComponent } from './components/admin/loging-page/loging-page.component';
import { OrderListComponent } from './components/admin/order-list/order-list.component';
import { ProductListComponent } from './components/admin/product-list/product-list.component';
import { CustomerCartComponent } from './components/customer/customer-cart/customer-cart.component';
import { CustomerHeaderComponent } from './components/customer/customer-header/customer-header.component';
import { CustomerHomeComponent } from './components/customer/customer-home/customer-home.component';
import { CustomerLoginPageComponent } from './components/customer/customer-login-page/customer-login-page.component';
import { CustomerOrderComponent } from './components/customer/customer-order/customer-order.component';
import { CustomerSiginUpComponent } from './components/customer/customer-sigin-up/customer-sigin-up.component';
import { AddproductComponent } from './components/admin/addproduct/addproduct.component';

import { FormsModule,ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AppComponent,
    AppHeaderComponent,
    ChangePasswordComponent,
    ContactusComponent,
    ForgotPasswordComponent,
    HomePageComponent,
    PagingComponent,
    HeaderComponent,
    HomeComponent,
    LogingPageComponent,
    OrderListComponent,
    ProductListComponent,
    CustomerCartComponent,
    CustomerHeaderComponent,
    CustomerHomeComponent,
    CustomerLoginPageComponent,
    CustomerOrderComponent,
    CustomerSiginUpComponent,
    AddproductComponent,
  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatRippleModule,
    MatDatepickerModule,
    MatNativeDateModule,
    HttpClientModule,
    MatMenuModule,
    MatIconModule,
    MatSnackBarModule,
    MatButtonModule,
    NgbModule,
    MatDialogModule,
    MatSidenavModule,
    MatButtonToggleModule
  ],
  providers: [
    DatePipe
  ],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ],

  bootstrap: [AppComponent]
})
export class AppModule { }
