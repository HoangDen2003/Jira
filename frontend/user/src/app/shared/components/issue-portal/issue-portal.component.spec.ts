import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IssuePortalComponent } from './issue-portal.component';

describe('IssuePortalComponent', () => {
  let component: IssuePortalComponent;
  let fixture: ComponentFixture<IssuePortalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [IssuePortalComponent]
    });
    fixture = TestBed.createComponent(IssuePortalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
