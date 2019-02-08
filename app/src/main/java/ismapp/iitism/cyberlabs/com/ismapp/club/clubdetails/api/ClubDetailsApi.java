package ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.api;

import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model.ClubDetails;
import ismapp.iitism.cyberlabs.com.ismapp.helper.Urls;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ClubDetailsApi {

    @GET(Urls.clubdetails)

    Call<ClubDetails> getdetails(@Header("Authorization") String Token_access_token, @Query("club_id") int id);
}