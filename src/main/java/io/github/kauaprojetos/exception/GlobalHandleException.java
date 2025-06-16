package io.github.kauaprojetos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandleException {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<String> handleConflict(Exception ex){
        ex.printStackTrace(); // LOGA o erro no console
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<String> handleInternal(Exception ex){
        ex.printStackTrace(); // LOGA o erro no console
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Captura qualquer outro erro desconhecido
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneric(Exception ex) {
        ex.printStackTrace();
        return new ResponseEntity<>("Erro interno no servidor.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
