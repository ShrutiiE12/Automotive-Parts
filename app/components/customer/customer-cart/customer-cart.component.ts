import { Component,OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { forkJoin, take } from 'rxjs';
import { DatePipe } from '@angular/common';
import { Product } from '../../model/product.model';
import { Cart } from '../../model/cart';
import { Order } from '../../model/order.model';
import { ProductsService } from '../../service/products.service';
//import * as _ from "lodash";

@Component({
  selector: 'app-customer-cart',
  templateUrl: './customer-cart.component.html',
  styleUrl: './customer-cart.component.css'
})
export class CustomerCartComponent implements OnInit {
  cartList: any[] = [];
  cartListBackup: Cart[] = [];
  grandTotal: number = 0;
  customer: any = {};
  caddress  ='';
 

  constructor(
    private prService:ProductsService,
    private router: Router,
    private datePipe: DatePipe
  ){

    this.prService.isCustomerLoginPresent();
    this.getCartList();
    this.getCustomerDetail();
  }

    ngOnInit(): void {

    }

    getCartList(): void {
      this.prService.cartList().pipe(take(1)).subscribe(
        (res: any) => {
          console.log("********", res);
          if (!!res && Array.isArray(res)) {
            const customerFilter = res.filter((item: Cart)=> item?.customer?.customerId === parseInt(this.prService.getCustomerAuthorization()));
            console.log("customer filter::::::",customerFilter);
           // this.cartListBackup =  _.cloneDeep(customerFilter);
            this.cartList = customerFilter;
            if (this.cartList.length > 0) {
              this.cartList.map((item: Cart) => {
                this.grandTotal += (item?.total_price * item?.quantity);
              })
            }
          }
        }, err => {
          console.log("error");
        }
  
      );
    }

    getTotal(quantity: number = 0, mrpPrice: number = 0): number {
      return quantity * mrpPrice;
    }
    placeOrder(): void {
      let totalPrice: number = 0;
      const deleteCartReq:any[]=[];
      const productItems: Array<Product> = [];
      this.cartList.forEach((item: any) => {
        productItems.push(item?.product);
        totalPrice += (item?.total_price* item?.quantity);
        console.log(item, 'ite')
        deleteCartReq.push(this.prService.deleteCart(item?.id));
      });
      console.log('>>>>>>>>', totalPrice)
      const body: any = {
        orderPrice: totalPrice,
        orderStatus: "success",
        paymentStatus: "success",
        orderedDate: this.datePipe.transform(new Date(), 'yyyy-MM-dd'),
        customer: this.customer,
        address:this.prService.getCustomerAddress(),
        productname: 'xxxxx',
        image: 'xxxxx',
        product: productItems
      };
      this.prService.placeOrderItem(this.customer?.customerId, body).pipe(take(1)).subscribe((res: any) => {
        console.log('>>>>>>>', res);
        forkJoin(deleteCartReq).pipe(take(1)).subscribe();
        alert("Place order Sucessfully");
        this.router.navigate(["/customer/order"]);
      
      
      })
      
  
  
    }
    
    getCustomerDetail(): void {
      const cid = this.prService.getCustomerAuthorization();
      this.prService.getCustomerById(cid).pipe(take(1)).subscribe(
        (res: any) => {
          console.log("Customer*****", res);
          if (!!res && res?.customerId) {
            this.customer = res;
          }
        }, err => {
          console.log("Err");
        }
      )
    }
  
    deleteCart(cart_id: number, showAlert: boolean = true): void {
      console.log(cart_id);
      this.prService.deleteCart(cart_id).subscribe(
        () => {
          if (showAlert) {
            alert('Product deleted successfully');
          }
          this.getCartList();
        },
        (error) => {
          console.error('Error deleting cart:', error);
          // Handle the error as needed
        }
      );
    }
    
    onIncreaseQunatity(cart: any): void {
      cart.quantity = cart.quantity + 1;
      this.cartList.forEach(el =>{
        if(cart?.id === el?.id){
          el.quantity = cart.quantity;
        }
      })
      
      this.updateGrantTotal();
    }
  
    onDecreaseQunatity(cart: any): void {
      cart.quantity = cart.quantity - 1;
      this.cartList.forEach(el =>{
        if(cart?.id === el?.id){
          el.quantity = cart.quantity;
        }
      })
      this.updateGrantTotal();
    }
  
    updateGrantTotal(): void {
      let total = 0;
      this.cartList.map((item: Cart) => {
        total+= (item?.total_price * item?.quantity);
      })
      this.grandTotal = total;
    }
  
  }
  




