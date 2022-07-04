package dao.exceptions;

public class GeoPointNotFoundException extends Exception {

    public GeoPointNotFoundException(String errorMsg) {
        super(errorMsg);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
