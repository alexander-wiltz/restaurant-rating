package dao.exceptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String errorMsg) {
        super(errorMsg);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
