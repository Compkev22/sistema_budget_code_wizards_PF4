package org.code_wizards.Sistema_Budget.web.exception;

import org.code_wizards.Sistema_Budget.dominio.exception.ErrorMetaAhorro;
import org.code_wizards.Sistema_Budget.dominio.exception.MetaAhorroExisteException;
import org.code_wizards.Sistema_Budget.dominio.exception.MetaAhorroNoExisteException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandlerMetaAhorro {

    @ExceptionHandler(MetaAhorroExisteException.class)
    public ResponseEntity<ErrorMetaAhorro> handleMetaAhorroYaExiste(MetaAhorroExisteException ex) {
        ErrorMetaAhorro error = new ErrorMetaAhorro("metaahorro-ya-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MetaAhorroNoExisteException.class)
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
    public ResponseEntity<ErrorMetaAhorro> handleException(Exception ex) {
        ErrorMetaAhorro error = new ErrorMetaAhorro("- Error de Relacion con base de datos -", ex.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }

}