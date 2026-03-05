package org.example.expert.domain.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private final boolean success;

    @JsonInclude(JsonInclude.Include.NON_NULL) // null인 경우 JSON에 포함하지 않음
    private final T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String message;

    private ApiResponse(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    // 성공 시 호출
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null);
    }

    // 실패 시 호출
    public static <T> ApiResponse<Void> fail(String message) {
        return new ApiResponse<>(false, null, message);
    }
}