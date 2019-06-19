package Androidcamera2api.inducesmile.com.androidcamera2api;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;

public class Screenshot extends AppCompatActivity {
private Button Enable;
private Button Disable;
private Button ScreenshotButton;

public static String screenshotPath = Environment.getExternalStorageDirectory()+"/encrypt/screenshot.jpg";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(AndroidCamera2API.ScreenShotDisable == 1)
        {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

      }
        setContentView(R.layout.activity_screenshot);
        Enable = findViewById(R.id.Enable);
        ScreenshotButton = findViewById(R.id.Screenshot);
        Disable = findViewById(R.id.Disable);
        assert Enable != null;
        Enable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetEnable();
            }
        });
        assert Disable != null;
        Disable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDisable();
            }
        });
        assert ScreenshotButton != null;
        ScreenshotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Screenshot();
            }
        });


    }
    public void SetEnable(){
        AndroidCamera2API.ScreenShotDisable = 0;
    }
    public void SetDisable(){
        AndroidCamera2API.ScreenShotDisable = 1;
    }
    public void Screenshot(){
        try{View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(screenshotPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();}
        catch (Throwable e){
            e.printStackTrace();
        }
        Bitmap bitmap2= BitmapFactory.decodeFile(screenshotPath);
        ImageView starImage = (ImageView) this.findViewById(R.id.screenshotThumb);
        starImage.setImageBitmap(bitmap2);
    }
}
