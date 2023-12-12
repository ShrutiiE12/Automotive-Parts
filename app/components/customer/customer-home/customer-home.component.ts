import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { take } from 'rxjs';
import { Product } from '../../model/product.model';
import { ProductsService } from '../../service/products.service';

@Component({
  selector: 'app-customer-home',
  templateUrl: './customer-home.component.html',
  styleUrls: ['./customer-home.component.css']
})
export class CustomerHomeComponent implements OnInit {

  productList: Array<Product> = [];
  customer: any = {};
  getCategoryList: any;
  category: any = '';
  allProductList: Array<Product> = [];
  offset: number = 0;
  pageSize: number = 10;
  totalItem: number = 1;

  constructor(
    private prService: ProductsService,
    private router: Router,
    private snackbar: MatSnackBar
  ) {
    this.getCustomerDetail();
  }

  ngOnInit(): void {
    this.prService.getProductlist().subscribe((response: any) => {
      this.getCategoryList = response;
    });

    // Load all products initially
    this.getProductList(true);
  }

  getCustomerDetail(): void {
    const cid = this.prService.getCustomerAuthorization();
    this.prService.getCustomerById(cid).pipe(take(1)).subscribe(
      (res: any) => {
        if (!!res && res?.customerId) {
          this.customer = res;
        }
      },
      err => {
        console.log("Error fetching customer details");
      }
    );
  }

  getProductList(isAllProduct: boolean = false): void {
    let productObservable: any;

    if (isAllProduct) {
      productObservable = this.prService.getAllProducts(this.offset, this.pageSize);
    } else {
      productObservable = this.prService.getAllProductsByCategory(this.category);
    }

    productObservable.pipe(take(1)).subscribe(
      (res: any) => {
        if (res && res.product && Array.isArray(res.product)) {
          this.productList = res.product;
          this.allProductList = res.product;
          this.totalItem = res.totalProduct;
        }
      },
    );
  }

  addToCart(product: Product): void {
    const body: any = {
      quantity: 1, // You can adjust the quantity as needed
      price: product.price,
      product: product,
      customer: this.customer
    };

    this.prService.addToCart(body, product.pid, this.customer.customerId).pipe(take(1)).subscribe(
      (res: any) => {
        console.log(res);
        this.snackbar.open("Item added successfully", 'Close', {
          duration: 2000,
        });
        this.getProductList(true);
        this.router.navigate(['orderlist']);
      },
      err => {
        console.log("Error adding item to cart");
      }
    );
  }
}
