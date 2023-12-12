import { ChangeDetectorRef,Component, OnInit } from '@angular/core';
import { NavigationStart, Router } from '@angular/router';
import { filter } from 'rxjs';
import { ProductsService } from '../../service/products.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit {
  url: string = '';
  userName: string = '';
  constructor(
    private prService :ProductsService,
    private router:Router,
    private changeDetector: ChangeDetectorRef
  ) {
    if (this.prService.getAdminName() !== null) {
      this.userName = this.prService.getAdminName();
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
    if (link === '/admin/logout') {
      this.prService.customerLogout();
      return;
    }
    this.router.navigate([link]);
  }

}
