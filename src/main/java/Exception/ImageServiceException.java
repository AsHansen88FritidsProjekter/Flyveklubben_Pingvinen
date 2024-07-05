package Exception;

import lombok.Getter;

@Getter
public class ImageServiceException extends RuntimeException {
    private final String context;

    public ImageServiceException(String message, Throwable cause, String context) {
        super(message, cause);
        this.context = context;
    }

}
