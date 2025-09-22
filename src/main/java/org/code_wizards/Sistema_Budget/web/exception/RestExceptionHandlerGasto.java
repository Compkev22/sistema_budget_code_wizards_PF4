package org.code_wizards.Sistema_Budget.web.exception;

import org.code_wizards.Sistema_Budget.dominio.exception.ErrorGastos;
import org.code_wizards.Sistema_Budget.dominio.exception.GastoNoExisteException;
import org.code_wizards.Sistema_Budget.dominio.exception.GastoYaExisteException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandlerGasto {

    @ExceptionHandler(GastoYaExisteException.class)
    public ResponseEntity<ErrorGastos> handleGastoYaExiste(GastoYaExisteException ex) {
        ErrorGastos error = new ErrorGastos("gasto-ya-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(GastoNoExisteException.class)
    public ResponseEntity<ErrorGastos> handleGastoNoExiste(GastoNoExisteException ex) {
        ErrorGastos error = new ErrorGastos("gasto-no-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorGastos>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<ErrorGastos> errores = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errores.add(new ErrorGastos(fieldError.getField(), fieldError.getDefaultMessage()));
        });
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorGastos> handleUnknownException(Exception ex) {
        ErrorGastos error = new ErrorGastos("-error-desconocido-", ex.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }
}