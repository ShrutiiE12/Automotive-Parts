import { Component,OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { take } from 'rxjs';
import { Order } from '../../model/order.model';
import { MatDialog } from '@angular/material/dialog';
import { ProductsService } from '../../service/products.service';
import { DatePipe } from '@angular/common';


@Component({
  selector: 'app-customer-order',
  templateUrl: './customer-order.component.html',
  styleUrl: './customer-order.component.css'
})
export class CustomerOrderComponent implements OnInit {

  orderList: Order[]=[];
  constructor(
    private prService: ProductsService,
    private router: Router,
    private datePipe : DatePipe,
    private dialog: MatDialog
  ) { 
    this.prService.isCustomerLoginPresent();
  }

  ngOnInit(): void {
    this.getOrderList();
  }
  getOrderList():void{
    this.prService.orderListByCustomer(this.prService.getCustomerAuthorization()).pipe(take(1)).subscribe(
      (res: any) => {
        console.log("************",res);
        if(!!res && Array.isArray(res)){
          this.orderList=res;
        }
        
      }, err => {
        console.log("Error");
      }
    )
  }
  getDate(d:string|undefined):any{
    //return  !!d ? this.datePipe.transform(new Date(d),"" )?.toString(): "";
    //return this.datePipe.transform(d,"").toString();
    let ans :any;
    console.log("DDDDDD",d);
    if(!!d && d!== null){
      ans=this.datePipe.transform(d,"shortDate")||null;
      console.log("@@@@@@@@",ans);
    }
    return ans;
  }
  
  
}
