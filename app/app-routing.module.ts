import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutusComponent } from './components/aboutus/aboutus.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { ContactusComponent } from './components/contactus/contactus.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { HomePageComponent } from './components/home-page/home-page.component';import { HeaderComponent } from './components/admin/header/header.component';
import { HomeComponent } from './components/admin/home/home.component';
import { LogingPageComponent } from './components/admin/loging-page/loging-page.component';
import { OrderListComponent } from './components/admin/order-list/order-list.component';
import { ProductListComponent } from './components/admin/product-list/product-list.component';
import { CustomerCartComponent } from './components/customer/customer-cart/customer-cart.component';
import { CustomerHomeComponent } from './components/customer/customer-home/customer-home.component';
import { CustomerLoginPageComponent } from './components/customer/customer-login-page/customer-login-page.component';
import { CustomerOrderComponent } from './components/customer/customer-order/customer-order.component';
import { CustomerSiginUpComponent } from './components/customer/customer-sigin-up/customer-sigin-up.component';
import { AddproductComponent } from './components/admin/addproduct/addproduct.component';
import { AppHeaderComponent } from './components/app-header/app-header.component';

const routes: Routes = [
  {path:'home',component:HomePageComponent},

{path:'about-us',component:AboutusComponent},
{path:'change-password',component:ChangePasswordComponent},
{path:'contact-us',component:ContactusComponent},
{path:'forgot-password',component:ForgotPasswordComponent},
{path:'change-password',component:ChangePasswordComponent},
{path:'customer-register',component:CustomerSiginUpComponent},
{path:'customer-login',component:CustomerLoginPageComponent},
{path:'admin-login',component:LogingPageComponent},
{path:'', redirectTo:'/home', pathMatch:'full'},

{path:'admin',children:[
  {path:'home',component:HomeComponent},
  {path:'orderlist',component:OrderListComponent},
  {path:'listproduct',component:ProductListComponent},
  {path:'addproduct',component:AddproductComponent}
]},

{path:'customer',children:[  
  {path:'home',component:CustomerHomeComponent},
  {path:'cart',component:CustomerCartComponent},
  {path:'order',component:CustomerOrderComponent}
]}


  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
