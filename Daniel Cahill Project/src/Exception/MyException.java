package Exception;

@SuppressWarnings("serial")
public class MyException extends Exception {
    public MyException(String details) {
        super(details);
    }
}