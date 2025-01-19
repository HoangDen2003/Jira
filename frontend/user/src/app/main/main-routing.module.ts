import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './main.component';
import { ProjectComponent } from './pages/project/project.component';
import { YourWorkComponent } from './pages/your-work/your-work.component';

const routes: Routes = [
  {
    path: '',
    component: MainComponent, // MainComponent chứa HeaderComponent
    children: [
      { path: '', redirectTo: 'your-work', pathMatch: 'full' },
      { path: 'projects', component: ProjectComponent },
      { path: 'your-work', component: YourWorkComponent },
      // jira là 1 module
      {
        path: 'software',
        loadChildren: () =>
          import('../main/pages/jira/jira.module').then((m) => m.JiraModule),
      },
      // { path: 'projects', component: ProjectComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MainRoutingModule {}
