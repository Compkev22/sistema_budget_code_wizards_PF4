package org.code_wizards.Sistema_Budget.web.exception;
import org.code_wizards.Sistema_Budget.dominio.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandlerCategoria {

    @ExceptionHandler(CategoriaExisteException.class)
    public ResponseEntity<ErrorCategoria> handleCategoriaExiste (CategoriaExisteException ex) {
        ErrorCategoria error = new ErrorCategoria("categoria-ya-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(CategoriaNoExisteException.class)
    public ResponseEntity<ErrorCategoria> handleCategoriaNoExiste(CategoriaNoExisteException ex) {
        ErrorCategoria error = new ErrorCategoria("categoria-no-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorCategoria>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<ErrorCategoria> errores = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errores.add(new ErrorCategoria(fieldError.getField(), fieldError.getDefaultMessage()));
        });
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorCategoria> handleException(Exception ex) {
        ErrorCategoria error = new ErrorCategoria("- Error de Relacion con base de datos -", ex.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }

}

