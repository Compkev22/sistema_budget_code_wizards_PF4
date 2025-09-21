package org.code_wizards.Sistema_Budget.web.exception;

import org.code_wizards.Sistema_Budget.dominio.exception.ErrorPresupuestos;
import org.code_wizards.Sistema_Budget.dominio.exception.PresupuestoNoExisteException;
import org.code_wizards.Sistema_Budget.dominio.exception.PresupuestoYaExisteException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandlerPresupuesto {

    @ExceptionHandler(PresupuestoYaExisteException.class)
    public ResponseEntity<ErrorPresupuestos> handlePresupuestoYaExiste(PresupuestoYaExisteException ex) {
        ErrorPresupuestos error = new ErrorPresupuestos("presupuesto-ya-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(PresupuestoNoExisteException.class)
    public ResponseEntity<ErrorPresupuestos> handlePresupuestoNoExiste(PresupuestoNoExisteException ex) {
        ErrorPresupuestos error = new ErrorPresupuestos("presupuesto-no-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorPresupuestos>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<ErrorPresupuestos> errores = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errores.add(new ErrorPresupuestos(fieldError.getField(), fieldError.getDefaultMessage()));
        });
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorPresupuestos> handleUnknownException(Exception ex) {
        ErrorPresupuestos error = new ErrorPresupuestos("-error-desconocido-", ex.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }
}