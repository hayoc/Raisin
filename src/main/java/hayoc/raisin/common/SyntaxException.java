package hayoc.raisin.common;

/**
 * Created by Hayo on 17/08/2016.
 */
public class SyntaxException extends Exception {

    public SyntaxException() {}

    public SyntaxException(String message) {
        super(message);
    }

    public SyntaxException(String message, Throwable cause) {
        super(message, cause);
    }


}
