package dao.exceptions;

public class RatingNotFoundException extends Exception {

    public RatingNotFoundException(String errorMsg) {
        super(errorMsg);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}