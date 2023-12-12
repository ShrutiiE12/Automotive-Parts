import { Component,OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { take } from 'rxjs';
import { Product } from '../../model/product.model';
import { ProductsService } from '../../service/products.service';


@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css'
})
export class ProductListComponent  implements OnInit {


  productList: Array<Product> = [];
  getCategoryList: any[] = [];
  allProductList: Array<Product> = [];
  offset: number = 0;
  pageSize: number = 10; // How many item you want to display in your page.
  totalItem: number = 1;
  category:string='';
  constructor(

            private prService:ProductsService,
            private router :Router
  ){  
      this.prService.isAdminLoginPresent();
  }

      ngOnInit(): void {
        this.prService.getCategoryList().subscribe(
          (categories: any[]) => {
            // Handle the received categories here
            this.getCategoryList = categories;
           this.category = this.getCategoryList[0];
           this.getProductList();

          },
          (error) => {
            // Handle error, if any
            console.error('Error fetching categories:', error);
          }
        );
      }

      
      getProductList(): void {
       
        this.prService.getAllProductsByCategory(this.category).subscribe(x =>{
          this.productList =x;
          this.totalItem = x.length;
        },
        (err: any) => {
          console.log("Error fetching products:", err);
        })
        
      }      
    
      delProduct(product: Product): void {
        this.prService.deleteProduct(product?.pid).pipe(take(1)).subscribe(
          (res: any) => {
            alert("Product deleted sucessfully");
            this.getProductList();
          }, err => {
            console.log("Error");
          }
        )
      }
    
      editProduct(product: Product): void {
        this.router.navigate(['/admin/addproduct'], {
          queryParams: {
            id: product?.pid
          }
        });
    
      }
    
      getProductByCategory(ev:any): void {
        this.offset = 0;
        this.totalItem = 1;
        this.category = ev?.value;
        this.prService.getAllProductsByCategory(this.category).subscribe(x =>{
          console.log(x)
          this.productList =x;
        })
        
      }
    
      onNextPageClick(pageOffSet: any): void {
        this.offset = pageOffSet;
        this.getProductList();
      }
      
      onPreviousPageClick(pageOffSet: any): void {
        this.offset -= 1;
        this.getProductList();
      }
      
      onFirstPageClick(pageOffSet: any): void {
        this.offset = 0;
        this.getProductList();
      }
      
      onLastPageClick(pageOffSet: any): void {
        const lastPage = Math.ceil(this.totalItem / this.pageSize);
        this.offset = lastPage;
        this.getProductList();
      }
      
    


}
