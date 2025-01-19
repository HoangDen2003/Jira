import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ProjectService } from 'src/app/core/apis/project/project.service';
import { BoardService } from 'src/app/main/pages/jira/components/board/service/board.service';

@Component({
  selector: 'app-issue-portal',
  templateUrl: './issue-portal.component.html',
  styleUrls: ['./issue-portal.component.css'],
})
export class IssuePortalComponent {
  issueId!: number | null;
  projectId!: number | null;
  issue: any = {};
  constructor(
    private boardService: BoardService,
    private API: ProjectService,
    private router: Router
  ) {}

  ngOnInit() {
    this.boardService.currentId.subscribe({
      next: ({ issueId, projectId }) => {
        this.issueId = issueId;
        this.projectId = projectId;
        if (this.issueId) {
          this.loadIssue();
        }
      },
      error: (err) => {},
    });
  }

  loadIssue() {
    this.API.getIssueByIdAndProjectId(this.projectId, this.issueId).subscribe({
      next: (res) => {
        if (res['result'].length == 1) {
          this.issue = res['result'][0];
        }
      },
      error: (err) => {},
    });
  }

  onClickClose() {
    this.issueId = null;
    this.projectId = null;
    this.boardService.updateIssueId(null);
    this.router.navigate([`/jira/software/projects/${this.projectId}/boards`]);
  }
}
