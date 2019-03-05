package ismapp.iitism.cyberlabs.com.ismapp.managemember.memberlist.model;

public class RemoveMember {
    private boolean success;
    private String message;

    public RemoveMember(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
