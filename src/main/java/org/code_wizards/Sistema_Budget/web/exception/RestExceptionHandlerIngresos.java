package org.code_wizards.Sistema_Budget.web.exception;
import org.code_wizards.Sistema_Budget.dominio.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandlerIngresos {

    @ExceptionHandler(IngresosYaExisteException.class)
    public ResponseEntity<ErrorIngresos> handleIngresosYaExiste(IngresosYaExisteException ex) {
        ErrorIngresos error = new ErrorIngresos("ingresos-ya-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(IngresosNoExisteException.class)
    public ResponseEntity<ErrorIngresos> handleIngresosNoExiste(IngresosNoExisteException ex) {
        ErrorIngresos error = new ErrorIngresos("ingresos-no-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorIngresos>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<ErrorIngresos> errores = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldErrorIngresos -> {
            errores.add(new ErrorIngresos(fieldErrorIngresos.getField(), fieldErrorIngresos.getDefaultMessage()));
        });
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorIngresos> handleException(Exception ex) {
        ErrorIngresos error = new ErrorIngresos("- Error de Relacion con base de datos -", ex.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }

    //  @ExceptionHandler(PresupuestoNoExisteException.class)
    //  public ResponseEntity<ErrorIngresos> handlePresupuestoNoExiste(PresupuestoNoExisteException ex) {
    //     ErrorIngresos error = new ErrorIngresos("presupuesto-no-existe", ex.getMessage());
    //     return ResponseEntity.badRequest().body(error);
    // }

    // @ExceptionHandler(CategoriaNoExisteException.class)
    //public ResponseEntity<ErrorIngresos> handleCategoriaNoExiste(CategoriaNoExisteException ex) {
    //   ErrorIngresos error = new ErrorIngresos("categoria-no-existe", ex.getMessage());
    //    return ResponseEntity.badRequest().body(error);
    // }



}

