package hyk.springframework.springtravellogrestapi.exception;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Htoo Yanant Khin
 */
@Data
public class ErrorResponse {
    private LocalDateTime timestamp;
    private String errorMessage;
    private String requestedUrl;
}
