import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class BoardService {
  private idSource = new BehaviorSubject<{
    issueId: number | null;
    projectId: number | null;
  }>({
    issueId: null,
    projectId: null,
  });
  currentId = this.idSource.asObservable();

  setId(issueId: number, projectId: number) {
    this.idSource.next({ issueId, projectId });
  }

  updateIssueId(issueId: number | null) {
    this.idSource.next({
      issueId,
      projectId: this.idSource.getValue().projectId,
    });
  }
}
