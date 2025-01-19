package com.jira.identity_service.exception;
// Định nghĩa package chứa các class liên quan đến xử lý ngoại lệ trong ứng dụng.

import java.util.Map;
import java.util.Objects;

import jakarta.validation.ConstraintViolation;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jira.identity_service.dto.response.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
// Annotation đánh dấu class này là nơi xử lý ngoại lệ toàn cục trong ứng dụng Spring.

@Slf4j
// Annotation của Lombok, tự động thêm một logger vào class này.

public class GlobalExceptionHandler {
    // Class này định nghĩa các phương thức xử lý ngoại lệ toàn cục trong ứng dụng.

    private static final String MIN_ATTRIBUTE = "min";
    // Hằng số để xác định tên thuộc tính "min" trong các ràng buộc validation.

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(Exception exception) {
        // Phương thức xử lý các ngoại lệ chưa được phân loại.

        log.error("Exception: ", exception);
        // Ghi log chi tiết lỗi xảy ra.

        ApiResponse apiResponse = new ApiResponse();
        // Tạo một đối tượng ApiResponse để chuẩn hóa phản hồi.

        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        // Gán mã lỗi chung cho các ngoại lệ không xác định.

        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
        // Gán thông báo lỗi chung.

        return ResponseEntity.badRequest().body(apiResponse);
        // Trả về phản hồi HTTP 400 (Bad Request) với nội dung lỗi.
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception) {
        // Phương thức xử lý ngoại lệ do ứng dụng định nghĩa (AppException).

        ErrorCode errorCode = exception.getErrorCode();
        // Lấy mã lỗi từ ngoại lệ AppException.

        ApiResponse apiResponse = new ApiResponse();
        // Tạo đối tượng ApiResponse để chuẩn hóa phản hồi.

        apiResponse.setCode(errorCode.getCode());
        // Gán mã lỗi từ ngoại lệ AppException.

        apiResponse.setMessage(errorCode.getMessage());
        // Gán thông báo lỗi từ ngoại lệ AppException.

        return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponse);
        // Trả về phản hồi HTTP với mã trạng thái và nội dung lỗi.
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse> handlingAccessDeniedException(AccessDeniedException exception) {
        // Phương thức xử lý ngoại lệ khi người dùng bị từ chối quyền truy cập.

        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        // Gán mã lỗi "Unauthorized" (401).

        return ResponseEntity.status(errorCode.getStatusCode())
                .body(ApiResponse.builder()
                        .code(errorCode.getCode())
                        // Gán mã lỗi vào phản hồi.
                        .message(errorCode.getMessage())
                        // Gán thông báo lỗi vào phản hồi.
                        .build());
        // Xây dựng đối tượng ApiResponse và trả về.
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException exception) {
        // Phương thức xử lý lỗi validation khi tham số không hợp lệ.

        String enumKey = exception.getFieldError().getDefaultMessage();
        // Lấy thông báo lỗi mặc định từ trường không hợp lệ.

        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        // Gán mã lỗi mặc định cho lỗi validation.

        Map<String, Object> attributes = null;
        // Khởi tạo biến để lưu trữ các thuộc tính của ràng buộc validation.

        try {
            errorCode = ErrorCode.valueOf(enumKey);
            // Thử chuyển đổi thông báo lỗi mặc định thành ErrorCode nếu tồn tại.

            var constraintViolation =
                    exception.getBindingResult().getAllErrors().getFirst().unwrap(ConstraintViolation.class);
            // Lấy lỗi validation đầu tiên và chuyển thành ConstraintViolation.

            attributes = constraintViolation.getConstraintDescriptor().getAttributes();
            // Lấy các thuộc tính liên quan đến ràng buộc validation.

            log.info(attributes.toString());
            // Ghi log thông tin các thuộc tính của ràng buộc.
        } catch (IllegalArgumentException e) {
            // Bắt ngoại lệ nếu không thể chuyển enumKey thành ErrorCode.
        }

        ApiResponse apiResponse = new ApiResponse();
        // Tạo đối tượng ApiResponse để chuẩn hóa phản hồi.

        apiResponse.setCode(errorCode.getCode());
        // Gán mã lỗi.

        apiResponse.setMessage(
                Objects.nonNull(attributes)
                        ? mapAttribute(errorCode.getMessage(), attributes)
                        // Nếu có thuộc tính validation, thay thế giá trị "min" trong thông báo lỗi.
                        : errorCode.getMessage());
        // Nếu không, sử dụng thông báo lỗi mặc định.

        return ResponseEntity.badRequest().body(apiResponse);
        // Trả về phản hồi HTTP 400 (Bad Request) với nội dung lỗi.
    }

    private String mapAttribute(String message, Map<String, Object> attributes) {
        // Phương thức thay thế giá trị `{min}` trong thông báo lỗi bằng giá trị thực tế.

        String minValue = String.valueOf(attributes.get(MIN_ATTRIBUTE));
        // Lấy giá trị thuộc tính "min" từ attributes.

        return message.replace("{" + MIN_ATTRIBUTE + "}", minValue);
        // Thay thế `{min}` trong thông báo lỗi bằng giá trị thực tế.
    }
}
