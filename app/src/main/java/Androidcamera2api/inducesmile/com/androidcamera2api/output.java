package Androidcamera2api.inducesmile.com.androidcamera2api;

import android.content.ClipData;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.ClipboardManager;
import android.widget.Toast;

//import com.androidcamera2api.authenticator.R;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
public class output extends AppCompatActivity {
    private Button uploadButton;
    private Button CopyButton;
    static public String outputColorString;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);
        initViews();
        setTitle("Output");
        new HashThread().execute();
}
    public void initViews(){
        TextView accuracyText = findViewById(R.id.textView4);
        TextView ElapsedTimeView = findViewById(R.id.ElapsedTimeView);
        //TextView outputColor = findViewById(R.id.OutputColor);
        uploadButton = findViewById(R.id.uploadButton);
        CopyButton = findViewById(R.id.CopyRIDButton);
        assert CopyButton != null;
        assert uploadButton != null;
        final TextView outputColor = findViewById(R.id.OutputColor);

        CopyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", String.valueOf(outputColor.getText()));
                clipboard.setPrimaryClip(clip);
                Toast.makeText(output.this,"Selected data has been copied to the clipboard",
                        Toast.LENGTH_SHORT).show();

            }
        });
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Upload();
            }
        });
        TextView RefID2 = this.findViewById(R.id.RefId);
        ImageView thumbnail = this.findViewById(R.id.thumbnail);
        Bitmap bitmap = BitmapFactory.decodeFile(AndroidCamera2API.picPath);
        thumbnail.setImageBitmap(bitmap);
        bitmap = null;
        //String ElapsedTimeString = Long.toString(AndroidCamera2API.ElapsedTime);
        //String outputColorString = AndroidCamera2API.resultValue;
        //outputColor.setText(outputColorString);
        accuracyText.setText("Sha-256");
        ElapsedTimeView.setText("Dummy Text");
        RefID2.setText("@" + generateRandom());

    }
    public static long generateRandom() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // first not 0 digit
        sb.append(random.nextInt(9) + 1);

        // rest of 11 digits
        for (int i = 0; i < 11; i++) {
            sb.append(random.nextInt(10));
        }

        return Long.valueOf(sb.toString()).longValue();
    }
    public void UpdateHash(){
        // try {
        //     Thread.sleep(1000);
        // } catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        //String ElapsedTimeString = Long.toString(AndroidCamera2API.ElapsedTime);

        TextView outputColor = findViewById(R.id.OutputColor);
        outputColorString = AverageColor(1);
        outputColor.setText(String.valueOf(outputColorString.length()));

        //setTitle("Output");
    }
    public static String md5(byte[] s) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(s);
            byte[] magnitude = digest.digest();
            BigInteger bi = new BigInteger(1, magnitude);
            String hash = String.format("%0" + (magnitude.length << 1) + "x", bi);
            return hash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    public String AverageColor(int pixelSpacing) {
        Bitmap bitmap = BitmapFactory.decodeFile(AndroidCamera2API.picPath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos); //bm is the bitmap object
        byte[] bitmapBytes = baos.toByteArray();
        String Hash = md5(bitmapBytes);
        bitmapBytes = null;
        bitmap = null;
        int redBucket;
        int greenBucket;
        int blueBucket;
        int pixelCount;
        int blocks = 80;
       // blockCount = 1;
       // blockCount2 = 1;

        /*for (int count = 1; count < 80*80; count += 1) {
            redBucket = 0;
            greenBucket = 10;
            blueBucket = 10;
            pixelCount = 1;
            for (int y = (blockCount - 1) * 16; y < (bitmap.getHeight() / blocks) * blockCount; y += 1) {
                for (int x = (blockCount2 - 1) * 9; x < bitmap.getWidth() / blocks * blockCount2; x += 1) {

                    int c = bitmap.getPixel(x, y);

                    pixelCount = pixelCount + 1;
                    redBucket += Color.red(c);
                    greenBucket += Color.green(c);
                    blueBucket += Color.blue(c);

                }
                if (blockCount2 < blocks) {
                    blockCount2 = blockCount2 + 1;
                }
            }
            if (blockCount2 == blocks) {
                blockCount = blockCount + 1;
                blockCount2 = 1;
            }
            rgb[count] = ((redBucket / pixelCount) + (greenBucket / pixelCount) + (blueBucket / pixelCount))/3;

        }*/

        // stopbitmapThread();
        //return rgb[7];
        //ReferenceID = "x102342410";
        return Hash;
    }

    public void Upload() {
            //AndroidCamera2API.resultValue;
                 Toast.makeText(output.this,"Selected data is uploading in the background", Toast.LENGTH_SHORT).show();

    }
    public class HashThread extends AsyncTask<String,String,String>{


        @Override
        protected String doInBackground(String... strings) {
            //initViews();
            setTitle("Processing");
            UpdateHash();
            return null;
        }
    }



}
