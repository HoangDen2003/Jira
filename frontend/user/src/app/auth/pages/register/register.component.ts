import { Component } from '@angular/core';
import { AuthService } from 'src/app/core/apis/auth/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  email: string = '';
  password: string = '';

  constructor(private API: AuthService) {}

  onSubmit(data: any): void {
    const { email, password } = data;
    this.API.signUp(email, password).subscribe({
      next: (res) => {
        console.log(res);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
