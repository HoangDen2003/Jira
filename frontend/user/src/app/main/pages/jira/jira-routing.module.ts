import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BoardComponent } from './components/board/board.component';
import { ListComponent } from './components/list/list.component';
import { JiraComponent } from './jira.component';
import { SummaryComponent } from './components/summary/summary.component';

const routes: Routes = [
  {
    path: '',
    component: JiraComponent, // JiraComponent chứa SidebarComponent
    children: [
      // { path: '', redirectTo: 'projects/boards', pathMatch: 'full' },
      { path: 'projects/:projectId/boards', component: BoardComponent },
      { path: 'projects/lists', component: ListComponent },
      { path: 'projects/summary', component: SummaryComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class JiraRoutingModule {}
