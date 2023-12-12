import { Component,OnInit } from '@angular/core';
import { ProductsService } from '../../service/products.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  userName: string = '';
  email: string = '';
  
  constructor(
    private prService: ProductsService
  ) {
    if (this.prService.getAdminName() !== null) {
      this.userName = this.prService.getAdminName();
      this.email = this.prService.getAdminEmail();
     
    }
    this.prService.isAdminLoginPresent();
  }

  ngOnInit(): void {
  }


}
