package ismapp.iitism.cyberlabs.com.ismapp.createevent.view;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;

import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.createevent.model.CreateEventModel;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;


public class CreateEvent extends Fragment implements CreateEventFragmentInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ImageView selectImage;
    private EditText title;
    private EditText description;
    private EditText shortDescription;
    private EditText Description;
    private EditText startEndDate;
    private EditText startStartDate;
    private EditText venue;
    private Button submit;
    public static final int PICK_IMAGE = 1;
    private MultipartBody.Part image;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public CreateEvent() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateEvent.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateEvent newInstance(String param1, String param2) {
        CreateEvent fragment = new CreateEvent();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_event, container, false);
        selectImage = (ImageView)view.findViewById(R.id.iv_add_event_pic);
        title = (EditText) view.findViewById(R.id.tv_add_event_title);
        description = (EditText)view.findViewById(R.id.tv_add_event_desc);
        shortDescription = (EditText)view.findViewById(R.id.tv_add_event_short_desc);
        venue = (EditText)view.findViewById(R.id.tv_add_event_venue);
        startStartDate = (EditText)view.findViewById(R.id.add_event_start_date);
        startEndDate = (EditText)view.findViewById(R.id.add_event_end_date);
        submit = (Button)view.findViewById(R.id.create_event_submit);
        selectimage();

        return   view;

    }

    private void selectimage() {
        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE  && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            selectImage.setImageBitmap(photo);



            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
            Uri tempUri = getImageUri(getContext(), photo);

            // CALL THIS METHOD TO GET THE ACTUAL PATH
            File finalFile = new File(getRealPathFromURI(tempUri));
           // File file = new File("/storage/emulated/0/Download/Corrections 6.jpg");
            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), finalFile);

// MultipartBody.Part is used to send also the actual file name
             image =
                    MultipartBody.Part.createFormData("image", finalFile.getName(), requestFile);



        }
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }


    @Override
    public void showProgressBar(Boolean show) {

    }

    @Override
    public void showResponse(CreateEventModel createEventModel) {
      //response;
    }

    @Override
    public void showButtonClickable(Boolean showButton) {
     if(showButton){
         submit.setEnabled(true);
              }
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void getUserResponse() {
        String Title = title.getText().toString();
        String Description = description.getText().toString();
        String ShortDescription = shortDescription.getText().toString();
        String StartDate = startStartDate.getText().toString();
        String EndDate = startEndDate.getText().toString();
        String Venue = venue.getText().toString();


    }

}