package ismapp.iitism.cyberlabs.com.ismapp.events.createevent.provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import okhttp3.MultipartBody;

interface CreateEventProviderInterface {
    void getCreateEventResponse(String access_token, int club_id, String title, String description,
                                String venue, String event_start_date, String event_end_date, MultipartBody.Part image, PresenterCallback presenterCallback,int event_id);
}
