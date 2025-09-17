package org.code_wizards.Sistema_Budget.web.exception;
import org.code_wizards.Sistema_Budget.dominio.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandlerMetaAhorro {

    @ExceptionHandler(CategoriaExisteException.class)
    public ResponseEntity<ErrorMetaAhorro> handleMetaAhorroYaExiste(MetaAhorroExisteException ex) {
        ErrorMetaAhorro error = new ErrorMetaAhorro("metaahorro-ya-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(CategoriaNoExisteException.class)
    public ResponseEntity<ErrorMetaAhorro> handleMetaAhorroNoExiste(MetaAhorroNoExisteException ex) {
        ErrorMetaAhorro error = new ErrorMetaAhorro("metaahorro-no-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMetaAhorro>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<ErrorMetaAhorro> errores = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errores.add(new ErrorMetaAhorro(fieldError.getField(), fieldError.getDefaultMessage()));
        });
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMetaAhorro> handleUnknownException(Exception ex) {
        ErrorMetaAhorro error = new ErrorMetaAhorro("-error desconocido-", ex.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }

}

