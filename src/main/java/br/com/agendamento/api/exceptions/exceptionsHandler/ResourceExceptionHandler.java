package br.com.agendamento.api.exceptions.exceptionsHandler;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.BadRequestException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;


@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<StandardError> noSuchElementException(NoSuchElementException e, HttpServletRequest request){

        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), "Objeto não encontrado.", System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StandardError> runtimeException(RuntimeException e, HttpServletRequest request){

        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<StandardError> handleBadRequestException(BadRequestException e) {

        HttpStatus httpStatus = HttpStatus.OK;

        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(err, httpStatus);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        // Aqui você pode personalizar a mensagem com base na exceção
        String errorMessage = "Erro ao processar a requisição: um registro com este valor já existe.";

        // Verifique se a causa raiz é uma ConstraintViolationException
        if (ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException constraintViolationException) {

            // Obtenha o nome da restrição violada (opcional)
            String constraintName = constraintViolationException.getConstraintName();

            // Você pode personalizar a mensagem com base na restrição específica
            errorMessage = "Erro: O campo '" + constraintName + "' deve ser único.";
        }

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

}
