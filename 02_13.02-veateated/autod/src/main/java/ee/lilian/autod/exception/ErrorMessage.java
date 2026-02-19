package ee.lilian.autod.exception;
import lombok.Data;
import java.util.Date;

@Data //sama mis @getter, setter jne, aga ei sobi entity jaoks!

public class ErrorMessage {
    private String message;
    private Date timestamp;
    private int status;
}
