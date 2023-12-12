import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { Order } from '../../model/order.model';
import { take } from 'rxjs';
import { ProductsService } from '../../service/products.service';


@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrl: './order-list.component.css'
})
export class OrderListComponent {
  orderList: Order[] = [];
  tempOrderList: Order[] = [];
  today = new Date();

  constructor(
    private prservice:ProductsService,
    private router: Router,
    private datePipe: DatePipe
  ) {
    this.prservice.isAdminLoginPresent();
  }
  ngOnInit(): void {
    this.getOrderList();
  }
  getOrderList(): void {
    this.prservice.getAllorderList().pipe(take(1)).subscribe(
      (res: any) => {
        if (!!res && Array.isArray(res)) {
          this.orderList = res;
          this.tempOrderList = res;
        }
      }, err => {
        console.log("Error");
      }
    )
  }
  getDate(d: string | undefined): any {
    let ans: any;
    if (!!d && d !== null) {
      ans = this.datePipe.transform(d, "shortDate") || null;
    }
    return ans;
  }

 



}
