package Androidcamera2api.inducesmile.com.androidcamera2api;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.OpenableColumns;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static Androidcamera2api.inducesmile.com.androidcamera2api.AndroidCamera2API.verifyPath;

public class verify extends AppCompatActivity {
    private Button selectbtn;
    public String RetrievedData;
    public Intent Data;

    private static final int CHOOSE_FILE_REQUESTCODE = 8777;
    private static final int PICKFILE_REQUEST_CODE = 8778;
    public int storedhashvalue;
    public ImageView thumbnail2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Verification Page");

        setContentView(R.layout.activity_verify);
        t1.start();

    }
    Thread t1 = new Thread(new Runnable() {
        public void run() {
            selectbtn =  findViewById(R.id.btn_select);
            assert selectbtn != null;
            selectbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    startActivityForResult(intent, PICKFILE_REQUEST_CODE);



                }


            });
        }

    });
    public void setFields(Intent data){
        Uri fileuri = data.getData();
        String filename = getFileName(fileuri);
        TextView Filename = findViewById(R.id.FileName);
        TextView DateTaken = findViewById(R.id.DateTaken);
        TextView username = findViewById(R.id.username);
        TextView UserID = findViewById(R.id.USERID);
        TextView TamperStatus = findViewById(R.id.TamperStatus);
        Filename.setText(filename);
        username.setText(UserID.getText());
        DateTaken.setText(RetrieveData("data"));
        TamperStatus.setText(RetrieveData("tamper"));
        //String Retrievedhash = RetrieveData("hash");
        //String outputColorString = AndroidCamera2API.resultValue;
        Bitmap bitmap = BitmapFactory.decodeFile(verifyPath);
        thumbnail2 = findViewById(R.id.verifyView);
        thumbnail2.setImageBitmap(bitmap);
    }
    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
    @Override
    protected void onActivityResult(int request, int result, Intent data) {
        super.onActivityResult(request, result, data);
        setFields(data);


    }
    protected String RetrieveData(String type){
        RetrievedData = "";
        switch (type){
            case "data":
                RetrievedData = "02/22/2018";
                break;
            case "tamper":
                //if(parseInt(AndroidCamera2API.resultValue) == storedhashvalue) {
                //  RetrievedData = "Genuine";
                // }
                // else {
                RetrievedData = "Tampered";
                //}
                break;
            case "hash":

                RetrievedData = output.outputColorString;
                break;
        }
    return RetrievedData;
    }

}
