import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LogingPageComponent } from './loging-page.component';

describe('LogingPageComponent', () => {
  let component: LogingPageComponent;
  let fixture: ComponentFixture<LogingPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LogingPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LogingPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
