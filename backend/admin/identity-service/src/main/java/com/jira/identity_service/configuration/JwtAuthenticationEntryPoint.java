package com.jira.identity_service.configuration;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jira.identity_service.dto.response.ApiResponse;
import com.jira.identity_service.exception.ErrorCode;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    // Class này triển khai interface AuthenticationEntryPoint để xử lý lỗi xác thực.
    // Được sử dụng khi người dùng truy cập tài nguyên yêu cầu xác thực mà không cung cấp thông tin hợp lệ.

    @Override
    public void commence(
            HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        // Phương thức commence() được gọi khi xảy ra lỗi xác thực.
        // Các tham số:
        // - HttpServletRequest: đại diện cho yêu cầu từ client.
        // - HttpServletResponse: đại diện cho phản hồi gửi về client.
        // - AuthenticationException: chứa thông tin về lỗi xác thực.

        ErrorCode errorCode = ErrorCode.UNAUTHENTICATED;
        // Lấy mã lỗi xác định rằng người dùng chưa xác thực (mã này được định nghĩa trong class ErrorCode).

        response.setStatus(errorCode.getStatusCode().value());
        // Thiết lập mã trạng thái HTTP (ví dụ: 401 Unauthorized) cho phản hồi.

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        // Thiết lập kiểu nội dung của phản hồi là JSON để client biết cách xử lý.

        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(errorCode.getCode()) // Thiết lập mã lỗi từ đối tượng ErrorCode.
                .message(errorCode.getMessage()) // Thiết lập thông báo lỗi từ ErrorCode.
                .build();
        // Xây dựng đối tượng ApiResponse chứa thông tin mã lỗi và thông báo lỗi.

        ObjectMapper objectMapper = new ObjectMapper();
        // Tạo một đối tượng ObjectMapper để chuyển đổi đối tượng Java thành chuỗi JSON.

        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
        // Ghi dữ liệu JSON (được tạo từ đối tượng ApiResponse) vào luồng phản hồi HTTP.

        response.flushBuffer();
        // Đảm bảo tất cả dữ liệu trong buffer được gửi ngay lập tức về phía client.
    }
}
