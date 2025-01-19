import { Component } from '@angular/core';
import { ProjectService } from 'src/app/core/apis/project/project.service';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css'],
})
export class ProjectComponent {
  projects: any[] = [];
  constructor(private API: ProjectService) {}

  ngOnInit(): void {
    this.getProjects();
  }

  getProjects() {
    this.API.getProjects().subscribe({
      next: (res) => {
        this.projects = res['result'];
        console.log(this.projects);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
