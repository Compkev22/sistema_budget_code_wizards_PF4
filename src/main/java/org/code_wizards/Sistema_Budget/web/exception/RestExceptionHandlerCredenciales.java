package org.code_wizards.Sistema_Budget.web.exception;

import org.code_wizards.Sistema_Budget.dominio.exception.CredencialesNoExisteException;
import org.code_wizards.Sistema_Budget.dominio.exception.CredencialesYaExisteException;
import org.code_wizards.Sistema_Budget.dominio.exception.ErrorCredenciales;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandlerCredenciales {

    @ExceptionHandler(CredencialesYaExisteException.class)
    public ResponseEntity<ErrorCredenciales> handle(CredencialesYaExisteException ex) {
        ErrorCredenciales error = new ErrorCredenciales("credenciales-ya-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(CredencialesNoExisteException.class)
    public ResponseEntity<ErrorCredenciales> handleException(CredencialesNoExisteException ex) {
        ErrorCredenciales error = new ErrorCredenciales("credenciales-no-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorCredenciales>> handleException(MethodArgumentNotValidException ex) {
        List<ErrorCredenciales> errores = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldErrorCredenciales ->  {
            errores.add(new ErrorCredenciales(fieldErrorCredenciales.getField(), fieldErrorCredenciales.getDefaultMessage()));
        });
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorCredenciales> handleException(Exception ex) {
        ErrorCredenciales error = new ErrorCredenciales("-error desconocido-", ex.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }
}
