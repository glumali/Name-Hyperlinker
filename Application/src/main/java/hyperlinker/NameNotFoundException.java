package hyperlinker;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Name not found")
public class NameNotFoundException extends RuntimeException {
}
