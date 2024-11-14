package SOLID_4;

public class UnknownException extends RuntimeException{
    @Override
    public String getMessage() {
        return "What am I?";
    }
}
