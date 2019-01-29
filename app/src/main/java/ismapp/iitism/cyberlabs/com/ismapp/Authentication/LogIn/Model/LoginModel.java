package ismapp.iitism.cyberlabs.com.ismapp.Authentication.LogIn.Model;

public class LoginModel {
    private boolean success;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoginModel(boolean success, String message) {

        this.success = success;
        this.message = message;
    }
}