package dao.exceptions;

public class PasswordErrorException extends Exception {

    public PasswordErrorException(String errorMsg) {
        super(errorMsg);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}