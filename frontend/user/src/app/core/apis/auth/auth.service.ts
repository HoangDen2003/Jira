import { Injectable } from '@angular/core';
import {
  HttpClient,
  HttpHeaders,
  HttpParams,
  HttpRequest,
} from '@angular/common/http';
import { catchError, Observable, tap } from 'rxjs';
import { Token } from '@angular/compiler';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}
  private url = 'http://localhost:8888/api/v1/identity/auth';

  login(email: string, password: string): Observable<any> {
    const api = `${this.url}/sign-in`;
    const body = {
      email: email,
      password: password,
    };
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.post<any>(api, body, { headers }).pipe(
      tap((response) => {
        if (response && response.result) {
          const token = response['result']['invalidatedToken']['id'];

          if (token) {
            const [header, payload, signature] = token.split('.');
            // Lưu payload vào LocalStorage
            if (payload) {
              localStorage.setItem('headerPayload', `${header}.${payload}`);
            }
            // Lưu signature vào Cookie
            if (signature) {
              document.cookie = `signature=${signature}; path=/; secure; samesite=strict`;
            }
          }
        }
      }),
      catchError((error) => {
        throw error;
      })
    );
  }

  signUp(email: string, password: string): Observable<any> {
    const api = `${this.url}/sign-up`;
    const body = {
      email: email,
      password: password,
    };
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.post<any>(api, body, { headers });
  }

  logout() {}

  resetPassword(email: string): Observable<any> {
    const api = `${this.url}/refresh-token`;
    const body = {
      email: email,
    };
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.post<any>(api, body, { headers });
  }
}
