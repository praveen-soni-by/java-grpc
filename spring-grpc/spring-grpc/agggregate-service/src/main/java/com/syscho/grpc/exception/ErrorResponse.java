package com.syscho.grpc.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ErrorResponse {
    private String msg;
    private LocalDateTime createdTime;
    private String status;
}
