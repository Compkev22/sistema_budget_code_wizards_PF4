package org.code_wizards.Sistema_Budget.web.exception;

import org.code_wizards.Sistema_Budget.dominio.exception.Error;
import org.code_wizards.Sistema_Budget.dominio.exception.UsuarioNoExisteException;
import org.code_wizards.Sistema_Budget.dominio.exception.UsuarioYaExisteException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandlerUsuarios {
    @ExceptionHandler(UsuarioYaExisteException.class)
    public ResponseEntity<Error> handle(UsuarioYaExisteException ex) {
        Error error = new Error("usuario-ya-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(UsuarioNoExisteException.class)
    public ResponseEntity<Error> handleException(UsuarioNoExisteException ex) {
        Error error = new Error("usuario-no-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Error>> handleException(MethodArgumentNotValidException ex) {
        List<Error> errores = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError ->  {
            errores.add(new Error(fieldError.getField(), fieldError.getDefaultMessage()));
        });
        return ResponseEntity.badRequest().body(errores);
    }

    //Manejador de error generico y desconocidos
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception ex) {
        Error error = new Error("-error desconocido-", ex.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }
}
