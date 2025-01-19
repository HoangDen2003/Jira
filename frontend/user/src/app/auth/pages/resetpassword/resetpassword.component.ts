import { Component } from '@angular/core';
import { AuthService } from 'src/app/core/apis/auth/auth.service';

@Component({
  selector: 'app-resetpassword',
  templateUrl: './resetpassword.component.html',
  styleUrls: ['./resetpassword.component.css'],
})
export class ResetpasswordComponent {
  email: string = '';

  constructor(private API: AuthService) {}

  onSubmit(data: any) {
    const { email } = data;
    this.API.resetPassword(email).subscribe({
      next: (response) => {
        console.log(response);
      },
      error: (error) => {
        console.log(error);
      },
    });
  }
}
