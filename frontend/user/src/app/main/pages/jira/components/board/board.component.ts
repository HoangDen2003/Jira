import {
  Component,
  ElementRef,
  HostListener,
  QueryList,
  ViewChildren,
} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { finalize } from 'rxjs';
import { ProjectService } from 'src/app/core/apis/project/project.service';
import { BoardService } from './service/board.service';
import { IssueService } from 'src/app/core/apis/issue/issue.service';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css'],
})
export class BoardComponent {
  @ViewChildren('listIssues') listElements!: QueryList<
    ElementRef<HTMLUListElement>
  >; // Lấy danh sách các thẻ <ul>
  projectId!: number; // Biến để lưu trữ projectId
  activeDropdown: string | null = null;
  activeCreateButton: string | null = null;
  isHiddenCreateButton: boolean = true;
  isHiddenCreateButtonWorkflow: boolean = true;
  isShowIssueView: boolean = false;
  isShowIssueTypeDropdown: boolean = false;
  isVisibileSubmitButton: boolean = false;

  issues: any[] = [];
  issueSummary: string = '';

  constructor(
    private API: ProjectService,
    private API_ISSUE: IssueService,
    private activedRoute: ActivatedRoute,
    private boardService: BoardService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getIssues();
  }

  // Lấy projectId trên URL
  getParamMap() {
    this.activedRoute.paramMap.subscribe((params) => {
      this.projectId = Number(params.get('projectId')!);
    });
  }

  getIssues() {
    this.getParamMap();
    this.API.getIssuesByProjectId(this.projectId)
      .pipe(
        finalize(() => {
          this.adjustHeights();
        })
      )
      .subscribe({
        next: (res) => {
          this.issues = res['result'];
        },
        error: (err) => {
          console.log(err);
        },
      });
  }

  // creating issue
  onSubmit(data: any) {
    data.projectId = this.projectId;
    this.API_ISSUE.createIssue(data).subscribe({
      next: (res) => {
        this.getIssues();
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  onIssueSummaryChange(event: Event): void {
    const input = event.target as HTMLTextAreaElement;
    this.isVisibileSubmitButton = input.value.trim() !== '' ? true : false;
  }

  toggleDropdown(dropdown: string) {
    this.activeDropdown = this.activeDropdown === dropdown ? null : dropdown;
  }

  isDropdownActive(dropdown: string): boolean {
    return this.activeDropdown === dropdown; // Kiểm tra dropdown có đang hoạt động hay không
  }

  toggleCreateButton() {
    this.isHiddenCreateButton = this.isHiddenCreateButton ? false : true;
  }

  toggleCreateButtonWorkflow() {
    this.isHiddenCreateButtonWorkflow = this.isHiddenCreateButtonWorkflow
      ? false
      : true;
  }

  // show issue view
  showIssueView(issueId: any) {
    // Điều hướng tới Board Component với query parameters
    this.router.navigate([`/jira/software/projects/${this.projectId}/boards`], {
      queryParams: { selectedIssue: issueId },
    });
    this.boardService.setId(issueId, this.projectId);
  }

  // show issue type dropdown
  showIssueTypeDropdown() {
    this.isShowIssueTypeDropdown = !this.isShowIssueTypeDropdown ? true : false;
  }

  // Lắng nghe sự kiện click trên toàn bộ document
  @HostListener('document:click', ['$event'])
  onDocumentClick(event: MouseEvent) {
    const target = event.target as HTMLElement;
    // Kiểm tra nếu click vào button (hoặc nút mở dropdown)
    if (target.closest('.css-1l34k60')) {
      return; // Không đóng dropdown
    }
    // Kiểm tra nếu click bên ngoài dropdown
    if (!target.closest('.css-178kn0r')) {
      this.activeDropdown = null; // Đóng tất cả dropdown
    }
    if (target.closest('.createIssueForm')) {
      return;
    }
    if (target.closest('.createWorkflowForm')) {
      return;
    }
    if (!target.closest('.css-1i6zi3d')) {
      this.isHiddenCreateButton = true;
      this.isShowIssueTypeDropdown = false;
    }
    if (!target.closest('.css-dlnf2j')) {
      this.isHiddenCreateButtonWorkflow = true;
    }
  }

  // điều chỉnh height cho ul issues
  adjustHeights(): void {
    let maxTotalHeight = 0;

    // Bước 1: Tính chiều cao lớn nhất trong tất cả các thẻ <ul>
    this.listElements.toArray().forEach((ulElement) => {
      const ul = ulElement.nativeElement;
      const liElements = ul.querySelectorAll('li');
      let totalHeight = 0;

      // Tính tổng chiều cao của các thẻ <li>
      liElements.forEach((li) => {
        totalHeight += li.offsetHeight;
      });

      // Cập nhật chiều cao lớn nhất
      if (totalHeight > maxTotalHeight) {
        maxTotalHeight = totalHeight;
      }
    });

    // Bước 2: Áp dụng chiều cao lớn nhất cho tất cả các thẻ <ul>
    this.listElements.toArray().forEach((ulElement) => {
      const ul = ulElement.nativeElement;
      ul.style.minHeight = `${maxTotalHeight}px`; // Gán chiều cao lớn nhất
      ul.style.backgroundColor = 'var(--ds-surface-sunken, #F4F5F7)';
      ul.style.margin = 'var(--ds-space-050, 4px) 5px';
    });
  }
}
