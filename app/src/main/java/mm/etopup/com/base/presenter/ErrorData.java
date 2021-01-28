package mm.etopup.com.base.presenter;

public class ErrorData {
    private String errorType;
    private String errorString;

    public ErrorData(String errorType, String errorString) {
        this.errorType = errorType;
        this.errorString = errorString;
    }

    public String getErrorType() {
        return errorType;
    }

    public String getErrorString() {
        return errorString;
    }
}
