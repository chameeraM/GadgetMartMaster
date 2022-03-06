

package lk.swlc.GadgetMartBackend.GadgetMartBackend.exception;

import ch.qos.logback.classic.Logger;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.model.ErrorResponse;

import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {



    @ExceptionHandler(value = MyRestTemplateException.class)
    ResponseEntity<ErrorResponse> handleMyRestTemplateException(MyRestTemplateException ex, HttpServletRequest request) {
//        Logger.error("An error happened while calling {} Downstream API: {}", ex.getApi(), ex.toString());
        return new ResponseEntity<>(new ErrorResponse(ex, request.getRequestURI()), ex.getStatusCode());
    }
}
