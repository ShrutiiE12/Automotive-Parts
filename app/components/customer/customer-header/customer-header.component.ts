import { Component,OnInit } from '@angular/core';
import { NavigationStart,Router } from '@angular/router';
import{filter} from 'rxjs';
import { ProductsService } from '../../service/products.service';


@Component({
  selector: 'app-customer-header',
  templateUrl: './customer-header.component.html',
  styleUrl: './customer-header.component.css'
})
export class CustomerHeaderComponent implements OnInit {

  url: string = "/customer/home";
  userName: string = '';

  constructor(
    private prService :ProductsService,
    private router:Router
  ) {
    if (this.prService.getCustomerName() !== null) {
      this.userName = this.prService.getCustomerName();
    }
  }

  ngOnInit(): void {
    this.router.events.pipe(
      filter(event => event instanceof NavigationStart)
    ).subscribe((event: any) => {
      this.url = event?.url;
    });
  }
  routerToLink(link: string): void {
    if (link === '/customer/logout') {
      this.prService.customerLogout();
      return;
    }
    this.router.navigate([link]);
  }

}


