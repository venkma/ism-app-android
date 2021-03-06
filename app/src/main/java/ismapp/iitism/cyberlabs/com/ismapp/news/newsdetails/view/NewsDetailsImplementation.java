package ismapp.iitism.cyberlabs.com.ismapp.news.newsdetails.view;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.fragment.app.Fragment;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.helper.ViewUtils;
import ismapp.iitism.cyberlabs.com.ismapp.news.newsdetails.model.DeleteNewsResponseModel;
import ismapp.iitism.cyberlabs.com.ismapp.news.newsdetails.model.NewsDetailsModel;


public class NewsDetailsImplementation extends Fragment implements NewsDetailsInterface {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView clubName;
    private TextView Description;
    private TextView createdDate;
    private TextView createdTime;
    private ImageView clubPic;
    private ProgressDialog progressDialog;
    private boolean is_admin;
    private MenuItem item;


    public NewsDetailsImplementation() {
        // Required empty public constructor
    }


    public static NewsDetailsImplementation newInstance(int param1, boolean param2) {
        NewsDetailsImplementation fragment = new NewsDetailsImplementation();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putBoolean(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // TODO: Rename and change types of parameters
            int newsId = getArguments().getInt(ARG_PARAM1);
            is_admin = getArguments().getBoolean(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_details, container, false);
        clubName = view.findViewById(R.id.news_details_club_name);
        clubPic = view.findViewById(R.id.news_details_club_pic);
        Description = view.findViewById(R.id.news_details_description);
        createdDate = view.findViewById(R.id.news_details_created_date);
        createdTime = view.findViewById(R.id.news_details_created_time);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        if (is_admin) {
            //show settings button;
            item.setVisible(true);
            //call delete api-- put in onOptionsItemSelected - Taz
        }
        return view;
    }

    @Override
    public void showProgressBar(boolean show) {
        if (show) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.remove_action_menu, menu);
        item = menu.findItem(R.id.action_remove);
        item.setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                //Call delete api here

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void getResponse(NewsDetailsModel newsDetailsModel) {
        if (newsDetailsModel.isSuccess()) {
            clubName.setText(newsDetailsModel.getClub_name());
            Picasso.get().load(newsDetailsModel.getNews_pic_url()).into(clubPic);
            Description.setText(newsDetailsModel.getDescription());
            createdDate.setText(newsDetailsModel.getCreated_date());
            createdTime.setText(newsDetailsModel.getCreated_time());
        } else {
            ViewUtils.showToast(getContext(), newsDetailsModel.getMessage());
        }


    }

    @Override
    public void showMessage(String message) {
        ViewUtils.showToast(getContext(), message);
    }

    @Override
    public void getDeleteResponse(DeleteNewsResponseModel deleteNewsResponseModel) {

    }
}
