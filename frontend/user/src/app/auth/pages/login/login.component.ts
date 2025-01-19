import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/apis/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  email: string = '';
  constructor(private API: AuthService, private router: Router) {}

  onSubmit(data: any): void {
    const { email, password } = data;
    this.API.login(email, '123456').subscribe({
      next: (response) => {
        this.router.navigate(['/jira/your-work']);
      },
      error: (error) => {
        console.log(error);
      },
    });
  }
}
