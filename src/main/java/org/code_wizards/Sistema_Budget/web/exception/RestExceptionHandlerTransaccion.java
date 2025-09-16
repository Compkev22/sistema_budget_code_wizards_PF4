package org.code_wizards.Sistema_Budget.web.exception;

import org.code_wizards.Sistema_Budget.dominio.exception.ErrorTransaccion;
import org.code_wizards.Sistema_Budget.dominio.exception.TransaccionNoExisteException;
import org.code_wizards.Sistema_Budget.dominio.exception.TransaccionYaExisteException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandlerTransaccion {

    @ExceptionHandler(TransaccionYaExisteException.class)
    public ResponseEntity<ErrorTransaccion> handleTransaccionYaExiste(TransaccionYaExisteException ex) {
        ErrorTransaccion error = new ErrorTransaccion("transaccion-ya-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorTransaccion> handleTransaccionNoExiste(TransaccionNoExisteException ex) {
        ErrorTransaccion error = new ErrorTransaccion("transaccion-no-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorTransaccion>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<ErrorTransaccion> errores = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldErrorTransaccion -> {
            errores.add(new ErrorTransaccion(fieldErrorTransaccion.getField(), fieldErrorTransaccion.getDefaultMessage()));
        });
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorTransaccion> handleUnknownException(Exception ex) {
        ErrorTransaccion error = new ErrorTransaccion("-error desconocido-", ex.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }
}
