package ismapp.iitism.cyberlabs.com.ismapp.events.createevent.presenter;

import okhttp3.MultipartBody;

public interface CreateEventPresenterInterface {
    void getCreateEventRequest(String access_token, int club_id, String title,  String description,
                               String venue, String event_start_date, String event_end_date, MultipartBody.Part image,int event_id);
}
