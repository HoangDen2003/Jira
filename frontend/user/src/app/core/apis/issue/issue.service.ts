import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class IssueService {
  constructor(private http: HttpClient) {}
  private host = 'http://localhost:8888';
  private url = `${this.host}/api/v1/issue`;

  getIssues(projectId: any): Observable<any> {
    const api = `${this.url}/${projectId}`;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.get<any>(api, { headers });
  }

  getIssueById(issueId: any): Observable<any> {
    const api = `${this.url}/${issueId}`;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.get<any>(api, { headers });
  }

  createIssue(body: any): Observable<any> {
    const api = `${this.url}/create`;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.post<any>(api, body, { headers });
  }

  deleteIssue() {}

  updateIssue() {}
}
