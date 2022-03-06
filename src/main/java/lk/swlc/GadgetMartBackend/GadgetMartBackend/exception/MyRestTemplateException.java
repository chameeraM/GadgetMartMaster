

package lk.swlc.GadgetMartBackend.GadgetMartBackend.exception;

import lombok.*;
import org.springframework.http.HttpStatus;


@NoArgsConstructor
@Getter
@Setter
@ToString
public class MyRestTemplateException extends RuntimeException {
    private DownstreamApi api;
    private HttpStatus statusCode;
    private String error;

    public MyRestTemplateException(DownstreamApi api, HttpStatus statusCode, String error) {
        super(error);
        this.api = api;
        this.statusCode = statusCode;
        this.error = error;
    }
}
