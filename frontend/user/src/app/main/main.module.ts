import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MainRoutingModule } from './main-routing.module';
import { MainComponent } from './main.component';
import { HeaderComponent } from '../shared/layout/header/header.component';
import { FormsModule } from '@angular/forms';
import { ProjectComponent } from './pages/project/project.component';
import { YourWorkComponent } from './pages/your-work/your-work.component';

@NgModule({
  declarations: [
    MainComponent,
    HeaderComponent,
    ProjectComponent,
    YourWorkComponent,
  ],
  imports: [CommonModule, MainRoutingModule, FormsModule],
  bootstrap: [MainComponent],
})
export class MainModule {}
