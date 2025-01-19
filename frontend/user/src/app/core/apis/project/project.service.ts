import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProjectService {
  constructor(private http: HttpClient) {}

  private host = 'http://localhost:8888/api/v1/project';

  getProjects(): Observable<any> {
    const api = `${this.host}/all`;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.get<any>(api, { headers });
  }

  getIssuesByProjectId(projectId: any): Observable<any> {
    const api = `${this.host}/${projectId}/boards`;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.get<any>(api, { headers });
  }

  getIssueByIdAndProjectId(projectId: any, issueId: any): Observable<any> {
    const api = `${this.host}/${projectId}/boards?selectedIssue=${issueId}`;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.get<any>(api, { headers });
  }
}
