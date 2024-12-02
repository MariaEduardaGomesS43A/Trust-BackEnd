package com.ibeus.Comanda.Digital.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorMessage {
    private int code;
    private HttpStatus status;
    private String message;
    private LocalDateTime timestamp;
    private String path;
}
