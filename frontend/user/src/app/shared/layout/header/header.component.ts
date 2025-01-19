import { Component, HostListener } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent {
  activeDropdown: string | null = null;

  constructor() {}

  toggleDropdown(dropdown: string) {
    if (this.activeDropdown === dropdown) {
      this.activeDropdown = null; // Nếu dropdown đang mở được click lại, đóng nó
    } else {
      this.activeDropdown = dropdown; // Mở dropdown được click và đóng các dropdown khác
    }
  }
  isDropdownActive(dropdown: string): boolean {
    return this.activeDropdown === dropdown; // Kiểm tra dropdown có đang hoạt động hay không
  }
  // Lắng nghe sự kiện click trên toàn bộ document
  @HostListener('document:click', ['$event'])
  onDocumentClick(event: MouseEvent) {
    const target = event.target as HTMLElement;
    // Kiểm tra nếu click vào button (hoặc nút mở dropdown)
    if (target.closest('.css-1rm27d1')) {
      return; // Không đóng dropdown
    }
    // Kiểm tra nếu click bên ngoài dropdown
    if (!target.closest('.css-2wv4z9')) {
      this.activeDropdown = null; // Đóng tất cả dropdown
    }
  }
}
