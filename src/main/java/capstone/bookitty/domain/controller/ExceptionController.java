package capstone.bookitty.domain.controller;


import capstone.bookitty.domain.dto.ResponseType.BasicResponse;
import capstone.bookitty.domain.dto.ResponseType.ResponseError;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
@Slf4j
public class ExceptionController {
    @ExceptionHandler({Exception.class})
    public ResponseEntity<? extends BasicResponse> handleGeneralException(Exception e){
        log.error("Exception: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseError(e.getMessage(),"500"));
    }

    @ExceptionHandler({IllegalArgumentException.class}) //잘못된 인자 전달
    public ResponseEntity<? extends BasicResponse> IllegalArgumentHandler(IllegalArgumentException e){
        log.warn("IllegalArgumentException: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseError(e.getMessage(),"400"));
    }

    @ExceptionHandler({RuntimeException.class}) //서버 내부 오류
    public ResponseEntity<? extends BasicResponse> RunTimeHandler(RuntimeException e){
        log.warn("RuntimeException: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseError(e.getMessage(),"500"));
    }

    @ExceptionHandler({EntityNotFoundException.class}) //요청한 리소스나 엔티티 찾을 수 없음
    public ResponseEntity<? extends BasicResponse> handleEntityNotFoundException(EntityNotFoundException e){
        log.warn("EntityNotFoundException: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseError(e.getMessage(),"404"));
    }

    @ExceptionHandler({DataIntegrityViolationException.class}) //데이터 무결성 위반
    public ResponseEntity<? extends BasicResponse> handleDataIntegrityViolationException(DataIntegrityViolationException e){
        log.error("DataIntegrityViolationException: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ResponseError(e.getMessage(),"409"));
    }

    @ExceptionHandler({AccessDeniedException.class}) //권한 부족
    public ResponseEntity<? extends BasicResponse> handleAccessDeniedException(AccessDeniedException e){
        log.warn("AccessDeniedException: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ResponseError(e.getMessage(),"403"));
    }

    @ExceptionHandler(MultipartException.class) //multipartfile 처리
    public ResponseEntity<? extends BasicResponse> handleMultipartException(MultipartException e) {
        log.error("MultipartException: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseError(e.getMessage(),"400"));
    }

}
