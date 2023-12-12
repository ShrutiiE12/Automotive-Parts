import { Component,OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { take } from 'rxjs';
import { Product } from '../../model/product.model';
import { ProductsService } from '../../service/products.service';

@Component({
  selector: 'app-addproduct',
  templateUrl: './addproduct.component.html',
  styleUrl: './addproduct.component.css'
})
export class AddproductComponent implements OnInit {

name: string = '';
price: number = 0;
description: string = '';
brand: string = '';
model: string = '';
image:string ='';

isEdit: boolean = false;
pid:any;
getCategoryList: any[] = [];
categories:any='';

constructor(
    private prService:ProductsService,
    private router:Router,
    private activateRouter:ActivatedRoute
) {

  this.activateRouter.queryParams.subscribe((params: any) => {
    if (params?.id) {
      this.isEdit = true;
      this.prService.getProductById(params?.id).pipe(take(1)).subscribe((res:any)=> {
        if(!!res && res?.pid){
         

          // Assign values to the component properties
          const product :Product=res;
          console.log('>>>>', product);
          this.name = product?.name;
          this.price = product?.price;
          this.description = product?.description;
          this.brand = product?.brand;
          this.model = product?.model;
          this.image =product?.image;
          this.pid=product?.pid;
          // const categoryName = this.getCategoryList.find((cate: any) => cate?.name.toString() === product?.category)?.value;
          this.categories = product?.category;
          
        }
        console.log(res);

      });
    }
  });
}

ngOnInit(): void {
  this.prService.isAdminLoginPresent();
  // this.getCategoryList

  // Subscribe to the getCategoryList observable
  this.prService.getCategoryList().subscribe(
    (categories: any[]) => {
      // Handle the received categories here
      this.getCategoryList = categories;
      if(!this.categories){
        this.categories = categories[0]
      }
      
    },
    (error) => {
      // Handle error, if any
      console.error('Error fetching categories:', error);
    }
  );
}


onAddProduct(): void {
  // Assuming this block is within your Angular component class

if (this.name === '') {
  alert("Product name is required");
  return;
}
if (this.description === '') {
  alert("Description is required");
  return;
}

console.log("******MRP price", this.price);
if (this.price === 0 || this.price === null) {
  alert("Price should not be zero/blank");
  return;
}
if (this.brand === '') {
  alert("Brand is required");
  return;
}
if (this.model === '') {
  alert("Model is required");
  return;
}
if (this.image === '') {
  alert("Image is required");
  return;
}
const body: any = {
  name: this.name,
  image: this.image,
  description: this.description,
  price: this.price,
  brand:this.brand,
  model:this.model,
  category: this.categories,

}
if(this.isEdit){
  console.log("=======>", body);
this.prService.editProduct(body,this.pid).pipe(take(1)).subscribe((res: any) => {
  console.log("*****", res);
  if (res && res?.pid) {
    alert("Product updated sucessfully");
   this.router.navigate(['/admin/listproduct'])
  }
}, err => {
  console.log("Error  ", err);
  alert("Something going wrong!!pl try again");
})
}else{
  console.log("=======>", body);
  this.prService.addProduct(body).pipe(take(1)).subscribe((res: any) => {
    console.log("*****", res);
    if (res && res?.pid) {
      alert("Product added sucessfully");
      this.router.navigate(["/admin/listproduct"]);
    }
  }, err => {
    console.log("Error  ", err);
    alert("Something going wrong!!pl try again");
  })
}



}
getSelectedCategory(e:any){
  this.categories = this.getCategoryList.find((el:any) => el.name === e.target.value )
  console.log(this.categories);

}
}