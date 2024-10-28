package br.com.balduino.peoplepro.user.core.application.rest.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationError {
    private String status;
    private int code;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();
    private String message;
    private String detailMessage;

    public ApplicationError(HttpStatus status, String message) {
        this(status, message, null);
    }

    public ApplicationError(HttpStatus status, String message, Throwable t) {
        this.status = status.name();
        this.code = status.value();

        if (t != null) {
            this.message = String.format("%s - %s", message, t.getMessage());
            this.detailMessage = ExceptionUtils.getStackTrace(t);
        } else {
            this.message = message;
        }
    }
}