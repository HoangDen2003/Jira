import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { JiraRoutingModule } from './jira-routing.module';
import { BoardComponent } from './components/board/board.component';
import { JiraComponent } from './jira.component';
import { SidebarComponent } from 'src/app/shared/layout/sidebar/sidebar.component';
import { IssuePortalComponent } from 'src/app/shared/components/issue-portal/issue-portal.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    JiraComponent,
    BoardComponent,
    SidebarComponent,
    IssuePortalComponent,
  ],
  imports: [CommonModule, JiraRoutingModule, FormsModule],
  bootstrap: [JiraComponent],
})
export class JiraModule {}
