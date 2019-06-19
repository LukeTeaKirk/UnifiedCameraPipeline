package Androidcamera2api.inducesmile.com.androidcamera2api;
//import android.accessibilityservice.AccessibilityService;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
//import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
//import android.drm.DrmInfoRequest;
//import android.drm.DrmManagerClient;
//import android.drm.DrmRights;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
//import android.graphics.Camera;
import android.graphics.Color;
//import android.graphics.Paint;
import android.hardware.camera2.CameraManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.*;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.media.MediaDrm;
import android.media.MediaPlayer;
import android.media.UnsupportedSchemeException;
//import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
//import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
//import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraAccessException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import android.hardware.camera2.CameraMetadata;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
//import static android.hardware.camera2.CameraCharacteristics.LENS_INFO_MINIMUM_FOCUS_DISTANCE;
//import static android.hardware.camera2.CaptureRequest.CONTROL_AF_MODE;
//import static android.hardware.camera2.CaptureRequest.LENS_FOCUS_DISTANCE;
//import static android.hardware.camera2.CaptureRequest.Builder;

import static android.hardware.camera2.CameraMetadata.FLASH_MODE_OFF;
import static android.hardware.camera2.CameraMetadata.FLASH_MODE_TORCH;
import static android.hardware.camera2.CaptureRequest.FLASH_MODE;
import static android.hardware.camera2.CaptureRequest.LENS_FOCUS_DISTANCE;
import static android.widget.Toast.makeText;


public class AndroidCamera2API extends AppCompatActivity {
    private static final UUID WIDEVINE_UUID = new UUID(0xEDEF8BA979D64ACEL, 0xA3C827DCD51D21EDL);
    public  String securityLevel;
    public boolean xvar;
    public boolean CameraNumber;
    static public int blockCount;
    static public int blockCount2;
    static public int ScreenShotDisable;
    //private Bitmap bitmap;
    private static final String TAG = "AndroidCameraApi";
    private Button takePictureButton;
    private Button outputButton;
    private Button verifyButton;
    private FloatingActionButton flashButton;
    private Button switchButton;
    public Button screenshotButton;
    private TextureView textureView;
    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();
    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 90);
        ORIENTATIONS.append(Surface.ROTATION_90, 0);
        ORIENTATIONS.append(Surface.ROTATION_180, 270);
        ORIENTATIONS.append(Surface.ROTATION_270, 180);
    }
    private String cameraId;
    public static String verifyPath = Environment.getExternalStorageDirectory()+"/encrypt/verify.jpg";
    public static String audioPath = Environment.getExternalStorageDirectory()+"/encrypt/flash.mp3";
    //public static String audioPath = "/assets/flash.mp3";
    protected CameraDevice cameraDevice;
    protected CameraCaptureSession cameraCaptureSessions;
    //protected CaptureRequest captureRequest;
    protected CaptureRequest.Builder captureRequestBuilder;
    private Size imageDimension;
    //public int imageID;
    private ImageReader imageReader;
    public float mininumLens;
    CharSequence title;
    public File file;
    static public int type;
    private static final int REQUEST_CAMERA_PERMISSION = 200;
    //private boolean mFlashSupported;
    private Handler mBackgroundHandler;
    private HandlerThread mBackgroundThread;
    public Thread backgroundThread;
    //static public String resultValue;
   //static public long ElapsedTime;
   // static public int[] rgb = new int[6400];
    static public String ReferenceID;
    private boolean CTS;
    public boolean Flashset;
    private boolean basicintegrity;
    public CameraManager mCameraManager;
   // private String mCameraId;
    //public int x1;
   // private Boolean isTorchOn;
    SharedPreferences data;
    public static int n;
    public int d2;
    private int safetynetavailble;
    private SeekBar focusControl = null;
    public static String picPath = Environment.getExternalStorageDirectory()+"/encrypt/pic" + n + ".jpg";

    final File tempFile = File.createTempFile("tmp", ".tmp");
    public String tempPath = tempFile.getAbsolutePath();

    public AndroidCamera2API() throws IOException {
    }

    public class JWS {
        private String nonce;
        private long timestampMs;
        private String apkPackageName;
        private String apkDigestSha256;
        private boolean ctsProfileMatch;
        private String extension;
        private List<String> apkCertificateDigestSha256;
        private boolean basicIntegrity;

        public String getNonce() {
            return nonce;
        }

        public long getTimestampMs() {
            return timestampMs;
        }

        public String getApkPackageName() {
            return apkPackageName;
        }

        public String getApkDigestSha256() {
            return apkDigestSha256;
        }

        public boolean isCtsProfileMatch() {
            return ctsProfileMatch;
        }

        public String getExtension() {
            return extension;
        }

        public List<String> getApkCertificateDigestSha256() {
            return apkCertificateDigestSha256;
        }

        public boolean isBasicIntegrity() {
            return basicIntegrity;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        CameraNumber = false;
        setTitle("Validating Device");
        //setTitle("Validation Disabled");
        detectScreenShotService(AndroidCamera2API.this);
        data = getSharedPreferences(String.valueOf(n), 0);
        try {
            Process su = Runtime.getRuntime().exec("su");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //attest(); //disable attestation
        drmoutput();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_camera2_api);
        View root = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        root.setBackgroundColor(Color.parseColor("#299DB2"));
        ScreenShotDisable = 1;
        //final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(AndroidCamera2API.this, R.array.select_array, android.R.layout.simple_spinner_item);
        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setAdapter(adapter);
        onUserLeaveHint();
        initViews(1);
        //onPause();
    }
    @SuppressLint("WrongConstant")
    private boolean getWVDrmInfo() {

        MediaDrm mediaDrm = null;
        try {
            mediaDrm = new MediaDrm(WIDEVINE_UUID);

            String vendor = mediaDrm.getPropertyString(MediaDrm.PROPERTY_VENDOR);
            String version = mediaDrm.getPropertyString(MediaDrm.PROPERTY_VERSION);
            String description = mediaDrm.getPropertyString(MediaDrm.PROPERTY_DESCRIPTION);
            String algorithms = mediaDrm.getPropertyString(MediaDrm.PROPERTY_ALGORITHMS);
            securityLevel = mediaDrm.getPropertyString("securityLevel");
            @SuppressLint("WrongConstant") String systemId = mediaDrm.getPropertyString("systemId");
            @SuppressLint("WrongConstant") String hdcpLevel = mediaDrm.getPropertyString("hdcpLevel");
            @SuppressLint("WrongConstant") String maxHdcpLevel = mediaDrm.getPropertyString("maxHdcpLevel");
            @SuppressLint("WrongConstant") String usageReportingSupport = mediaDrm.getPropertyString("usageReportingSupport");
            @SuppressLint("WrongConstant") String maxNumberOfSessions = mediaDrm.getPropertyString("maxNumberOfSessions");
            @SuppressLint("WrongConstant") String numberOfOpenSessions = mediaDrm.getPropertyString("numberOfOpenSessions");
            mediaDrm.release();
        } catch (UnsupportedSchemeException e) {
            e.printStackTrace();
        }
        Log.d("security level", securityLevel);
        if (securityLevel.equals("L3")) {
            Toast.makeText(AndroidCamera2API.this, securityLevel , Toast.LENGTH_SHORT).show();
            xvar = true; //disable drm testing
            Log.d("security level", "success");
            return xvar;
        }
        return xvar;

    }

    private  void drmoutput(){
       // Toast.makeText(AndroidCamera2API.this, String.valueOf(getWVDrmInfo()), Toast.LENGTH_SHORT).show();
        if (getWVDrmInfo()) {
            Toast.makeText(AndroidCamera2API.this, "DRM passed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(AndroidCamera2API.this, "DRM failed, your device is unsupported", Toast.LENGTH_SHORT).show();
            Log.d("security level","failure");
            finish();
        }
    }
    private void attest(){
        //if (getWVDrmInfo() == "L1"){

            if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(AndroidCamera2API.this)
                == ConnectionResult.SUCCESS) {
            safetynetavailble = 1;
        }
        else{
            finish();
        }
        byte[] bytes = ByteBuffer.allocate(4).putInt(generateNonce()).array();

        for (byte b : bytes) {
            System.out.format("0x%x ", b);
        }
        SafetyNet.getClient(this).attest(bytes, "AIzaSyDYHv1ElVPe7XzYA34HlR7-rySKG11-iXs" )
                .addOnSuccessListener(this,
                        new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                            @Override
                            public void onSuccess(SafetyNetApi.AttestationResponse response) {
                                String[] jwtParts = response.getJwsResult().split("\\.");
                                if(jwtParts.length == 3) {
                                    byte[] sharedpreferences = Base64.decode(jwtParts[1], 0);
                                    /*SharedPreferences editor = getSharedPreferences("DecodedPayload", 0);
                                    SharedPreferences.Editor editor1 = editor.edit();
                                    editor1.putString("decodedPayload", sharedpreferences);
                                    editor1.commit();*/
                                    decodeJWS(sharedpreferences);
                                    if(basicintegrity){
                                        setTitle("Home Page (Basic Passed)");
                                       //onResume();
                                    }
                                    if(CTS){
                                        //onResume();
                                        setTitle("Home Page (CTS Passed)");
                                        initViews(1);

                                    }
                                    if (basicintegrity == false){
                                        setTitle("Home Page (basic failed)");
                                        finish();


                                    }
                                    if ((CTS == false)){
                                        setTitle("Home Page (CTS failed)");
                                        finish();
                                    }
                                } else {
                                    SharedPreferences sharedpreferences1 = getSharedPreferences("DecodedPayload", 0);
                                    SharedPreferences.Editor editor2 = sharedpreferences1.edit();
                                    editor2.putString("decodedPayload", "CND");
                                    editor2.commit();
                                    Log.d("ContentValues", "The safety net response could not be decoded");
                                }

                                response.getJwsResult();
                                //Toast.makeText(AndroidCamera2API.this, "Root detected", Toast.LENGTH_SHORT).show();
                                //setTitle("Home Page (Basic Passed)");

                            }
                        })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        finish();
                        // An error occurred while communicating with the service.
                        if (e instanceof ApiException) {
                            // An error with the Google Play services API contains some
                            // additional details.
                            ApiException apiException = (ApiException) e;
                            // You can retrieve the status code using the
                            // apiException.getStatusCode() method.
                        } else {
                            // A different, unknown type of error occurred.
                            Log.d(TAG, "Error: " + e.getMessage());
                        }
                    }
                });}
    //}
    private void decodeJWS(byte[] jwsString) {

       // byte[] json = Base64.decode(jwsString.split("\\.")[1],Base64.DEFAULT);
        String text = new String(jwsString, StandardCharsets.UTF_8);

        Gson gson = new Gson();
        JWS jws = gson.fromJson(text, JWS.class);
        basicintegrity = jws.isBasicIntegrity();
        CTS = jws.isCtsProfileMatch();
    }
    private void integrityCheck(){

    }
    private void initViews(int d) {
        assert outputButton != null;
        assert switchButton != null;
        assert flashButton != null;
        assert verifyButton != null;
        assert takePictureButton != null;
        assert screenshotButton != null;
        assert textureView != null;
        final Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setDuration(1000);

        final Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setStartOffset(1000);
        fadeOut.setDuration(1000);
        TextView screenshotValtext = findViewById(R.id.screenshotval);
        screenshotValtext.setText(Integer.toString(ScreenShotDisable));
        textureView = (TextureView) findViewById(R.id.texture);
        textureView.setSurfaceTextureListener(textureListener);
        takePictureButton = (Button) findViewById(R.id.btn_takepicture);
        switchButton = (Button) findViewById(R.id.switchcamera);
        flashButton = findViewById(R.id.fab);
        screenshotButton = findViewById(R.id.screenshot);
        outputButton = (Button) findViewById(R.id.button);
        verifyButton = findViewById(R.id.btn_verify);
        focusControl = (SeekBar) findViewById(R.id.seekBar);
        //takePictureButton.setBackgroundColor(Color.rgb(211,211,0));
        focusControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                progressChanged = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(AndroidCamera2API.this,"Focal distance:"+progressChanged,
                        Toast.LENGTH_SHORT).show();
                try {
                    setFocus(progressChanged);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }

                // Ca
                //  CaptureRequest.LENS_FOCUS_DISTANCE, progressChanged;
            }
        });
        final ImageView flashImage = (ImageView) this.findViewById(R.id.imageView5);

        if (d == 1) {
            Flashset = false;
            verifyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
/*                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                       @Override
                        public void run() {*/
                            // Do something after 5s = 5000ms
                            Intent myintent = new Intent(AndroidCamera2API.this, verify.class);
                            AndroidCamera2API.this.startActivity(myintent);
 /*                       }
                    }, 0);*/


                }
            });
            flashButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Flashset = !Flashset;
                    createCameraPreview3(Flashset);
                }
            });
            switchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    closeCamera();

                    CameraNumber = !CameraNumber;
                    openCamera(CameraNumber);
                }
            });
            takePictureButton.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {
                    d2 = 1;
                    //new BackgroundThreader().execute();
                    final CharSequence title = getTitle();
                    setTitle("Processing");
                    audioPlayer();
                    takePicture();
                    FadeIn(flashImage, 1, 256, 300, true);
                    FadeIn(flashImage, 256, 1, 300, true);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 5s = 5000ms
                            setTitle(title);
                            generateThumb();
                        }
                    }, 600);
                }
            });
            screenshotButton.setOnClickListener(new View.OnClickListener() {
                //@RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v) {
                    textureView.setAnimation(fadeOut);
                    onPause();
                    Intent myIntent2 = new Intent(AndroidCamera2API.this, LoginActivity.class);
                    AndroidCamera2API.this.startActivity(myIntent2);
                }
            });
            outputButton.setOnClickListener(new View.OnClickListener() {
                //@RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v) {
                    //int position = spinner.getSelectedItemPosition();
                    //accuracyString = spinner.getItemAtPosition(position).toString();
                    //StartTime = System.nanoTime();
                    type = 1;
                         /* if (accuracyString.equals("Fast")){
                           type = 16;

                          }
                          else {
                             type = 1;
                          }*/
                    //ElapsedTime = System.nanoTime() - StartTime;
                    if (d2 == 1){
                        textureView.setAnimation(fadeOut);
                        onPause();
                        Intent myIntent = new Intent(AndroidCamera2API.this, output.class);
                        AndroidCamera2API.this.startActivity(myIntent);}
                    else {
                        Toast.makeText(AndroidCamera2API.this, "Please take a picture first", Toast.LENGTH_SHORT).show();
                    }
                    //resultValue = AverageColor(type);

                    //Toast.makeText(AndroidCamera2API.this, "Block 2: " + rgb[5] +  "; type: " + type, Toast.LENGTH_SHORT);
                }
            });
        }

    }
    TextureView.SurfaceTextureListener textureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            //open your camera here
            openCamera(CameraNumber);
        }
        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
            // Transform you image captured size according to the surface width and height
        }
        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            return false;
        }
        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        }
    };
    private static int generateNonce() {
       int x2 = 0;
        try {
            // Create a secure random number generator
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");

            // Get 1024 random bits
            byte[] bytes = new byte[1024/8];
            sr.nextBytes(bytes);

            // Create two secure number generators with the same seed
            int seedByteCount = 10;
            byte[] seed = sr.generateSeed(seedByteCount);

            sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed(seed);
            SecureRandom sr2 = SecureRandom.getInstance("SHA1PRNG");
            sr2.setSeed(seed);
            x2 = sr.nextInt(32);

        } catch (NoSuchAlgorithmException e) {
        }
        return x2;
    }
    protected void onUserLeaveHint(){
        closeCamera();

    }
    public void detectScreenShotService(final Activity activity){

        final Handler h = new Handler();
        final int delay = 3000; //milliseconds
        final ActivityManager am=(ActivityManager)activity.getSystemService(Context.ACTIVITY_SERVICE);
        //Toast.makeText(activity,"func running!!",Toast.LENGTH_LONG).show();

        h.postDelayed(new Runnable(){
            public void run(){

                List<ActivityManager.RunningServiceInfo> rs=am.getRunningServices(200);

                for(ActivityManager.RunningServiceInfo ar:rs){
                    if(ar.process.equals("com.snapchat.android")){
                        Toast.makeText(activity,"Screenshot captured!!",Toast.LENGTH_LONG).show();
                    }
                }
                h.postDelayed(this, delay);
            }
        }, delay);

    }
    private final CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(CameraDevice camera) {
            Log.e(TAG, "onOpened");
            cameraDevice = camera;
            createCameraPreview();
        }
        @Override
        public void onDisconnected(CameraDevice camera) {
            cameraDevice.close();
        }
        @Override
        public void onError(CameraDevice camera, int error) {
            cameraDevice.close();
            cameraDevice = null;
        }
    };
    final CameraCaptureSession.CaptureCallback captureCallbackListener = new CameraCaptureSession.CaptureCallback() {
        @Override
        public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
            super.onCaptureCompleted(session, request, result);
            makeText(AndroidCamera2API.this, "Saved:" + file, Toast.LENGTH_SHORT).show();
            createCameraPreview();
        }
    };

    private void setFocus(int x) throws CameraAccessException {
        //not working
        float num = x; //(x);
        //closeCamera();
        createCameraPreview2(num);
        /*
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        CameraDevice mCamera = cameraDevice;
        SurfaceTexture texture = textureView.getSurfaceTexture();
        assert texture != null;
        texture.setDefaultBufferSize(imageDimension.getWidth(), imageDimension.getHeight());
        Surface surface = new Surface(texture);
        CaptureRequest.Builder captureBuilder = null;   // = mCamera.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
        captureBuilder.set(CaptureRequest.CONTROL_MODE, CaptureRequest.CONTROL_MODE_OFF);
        captureBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, CaptureRequest.CONTROL_AF_TRIGGER_CANCEL);
        captureBuilder.set(CaptureRequest.CONTROL_AF_MODE, CameraMetadata.CONTROL_AF_MODE_OFF);
        captureBuilder.set(LENS_FOCUS_DISTANCE, num);
        captureBuilder = mCamera.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
        captureBuilder.addTarget(surface);/*
        /*     try {
            CameraCharacteristics characteristics = manager.getCameraCharacteristics(String.valueOf(LENS_FOCUS_DISTANCE));
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        try {
            CameraCharacteristics characteristics2 = manager.getCameraCharacteristics(String.valueOf(LENS_INFO_MINIMUM_FOCUS_DISTANCE));
            mininumLens = characteristics2.get(LENS_INFO_MINIMUM_FOCUS_DISTANCE);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        //CONTROL_AF_MODE off;
        CameraCharacteristics mCameraCharacteristics = null;
        try {
            mCameraCharacteristics = manager.getCameraCharacteristics(cameraId);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }*/

         /* float yourMinFocus = mCameraCharacteristics.get(CameraCharacteristics.LENS_INFO_MINIMUM_FOCUS_DISTANCE);
          float yourMaxFocus = mCameraCharacteristics.get(CameraCharacteristics.LENS_INFO_HYPERFOCAL_DISTANCE);
          float median = yourMinFocus/yourMaxFocus;
          float perecnt = median*x;*/
          //onPause();
        /*

          cameraCaptureSessions.setRepeatingRequest(captureBuilder.build(), null, mBackgroundHandler);*/
          //onResume();
          //openCamera(CameraNumber);
        }

    protected void startBackgroundThread() {
        mBackgroundThread = new HandlerThread("Camera Background");
        mBackgroundThread.start();
        mBackgroundHandler = new Handler(mBackgroundThread.getLooper());
    }
    protected int GenerateUUID(){
        int x = 10;
        return x;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void audioPlayer(){
        //set up MediaPlayer
        MediaPlayer mp = new MediaPlayer();

        try {
            AssetFileDescriptor afd = getAssets().openFd("flash.mp3");
            mp.setDataSource(afd); //afd
            mp.prepare();
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void stopBackgroundThread() {
        mBackgroundThread.quitSafely();
      /*  try {
            mBackgroundThread.join();
            mBackgroundThread = null;
            mBackgroundHandler = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    protected void takePicture(){
        int x = data.getInt("xda", n);
        n = x + 1;
        SharedPreferences.Editor editor = data.edit();
        editor.putInt("xda", n);
        editor.commit();
        if(null == cameraDevice) {
            Log.e(TAG, "cameraDevice is null");
            return;
        }
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraDevice.getId());
            Size[] jpegSizes = null;
            if (characteristics != null) {
                jpegSizes = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP).getOutputSizes(ImageFormat.JPEG);
            }
            int width = 1080;
            int height = 1920;
            if (jpegSizes != null && 0 < jpegSizes.length) {
                width = jpegSizes[0].getWidth();
                height = jpegSizes[0].getHeight();
            }
            ImageReader reader = ImageReader.newInstance(width, height, ImageFormat.JPEG, 1);
            List<Surface> outputSurfaces = new ArrayList<Surface>(2);
            outputSurfaces.add(reader.getSurface());
            outputSurfaces.add(new Surface(textureView.getSurfaceTexture()));
            final CaptureRequest.Builder captureBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
            captureBuilder.addTarget(reader.getSurface());
            captureBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);
            // Orientation
            int rotation = getWindowManager().getDefaultDisplay().getRotation();
            captureBuilder.set(CaptureRequest.JPEG_ORIENTATION, ORIENTATIONS.get(rotation));

            final File file2 = new File(Environment.getExternalStorageDirectory()+"/encrypt");
            file2.mkdirs();
            final File file = new File(Environment.getExternalStorageDirectory()+"/encrypt/pic" + n + ".jpg");

            picPath = Environment.getExternalStorageDirectory()+"/encrypt/pic" + n + ".jpg";
            ImageReader.OnImageAvailableListener readerListener = new ImageReader.OnImageAvailableListener() {
                @Override
                public void onImageAvailable(ImageReader reader) {
                    Image image = null;
                    try {
                        image = reader.acquireLatestImage();
                        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                        byte[] bytes = new byte[buffer.capacity()];
                        buffer.get(bytes);
                        save(bytes);
                        bytes = null;
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (image != null) {
                            image.close();
                        }
                    }
                }
                private void save(byte[] bytes) throws IOException {
                    OutputStream output = null;
                    try {
                        output = new FileOutputStream(file);
                        output.write(bytes);
                        output = new FileOutputStream(tempFile);
                        output.write(bytes);
                        bytes = null;
                    } finally {
                        if (null != output) {
                            output.close();
                        }
                    }
                }

            };
            reader.setOnImageAvailableListener(readerListener, mBackgroundHandler);
            final CameraCaptureSession.CaptureCallback captureListener = new CameraCaptureSession.CaptureCallback() {
                @Override
                public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
                    super.onCaptureCompleted(session, request, result);
                    //Toast.makeText(AndroidCamera2API.this, "Saved:" + file, Toast.LENGTH_SHORT).show();
                    createCameraPreview();
                }
            };
            cameraDevice.createCaptureSession(outputSurfaces, new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(CameraCaptureSession session) {
                    try {
                        session.capture(captureBuilder.build(), captureListener, mBackgroundHandler);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onConfigureFailed(CameraCaptureSession session) {
                }
            }, mBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }
    protected void generateThumb(){
        Bitmap bitmap = BitmapFactory.decodeFile(tempPath);
        ImageView starImage = (ImageView) this.findViewById(R.id.imageView2);
        starImage.setImageBitmap(bitmap);
        bitmap = null;
    }
    protected void createCameraPreview() {
        try {
            SurfaceTexture texture = textureView.getSurfaceTexture();
            assert texture != null;
            texture.setDefaultBufferSize(imageDimension.getWidth(), imageDimension.getHeight());
            Surface surface = new Surface(texture);
            captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            captureRequestBuilder.addTarget(surface);
            cameraDevice.createCaptureSession(Arrays.asList(surface), new CameraCaptureSession.StateCallback(){
                @Override
                public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
                    //The camera is already closed
                    if (null == cameraDevice) {
                        return;
                    }
                    // When the session is ready, we start displaying the preview.
                    cameraCaptureSessions = cameraCaptureSession;
                    updatePreview();
                }
                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {
                    makeText(AndroidCamera2API.this, "Configuration change", Toast.LENGTH_SHORT).show();
                }
            }, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
    protected void createCameraPreview2(float x) {
        try {
            SurfaceTexture texture = textureView.getSurfaceTexture();
            assert texture != null;
            texture.setDefaultBufferSize(imageDimension.getWidth(), imageDimension.getHeight());
            Surface surface = new Surface(texture);
            captureRequestBuilder.set(CaptureRequest.CONTROL_MODE, CaptureRequest.CONTROL_MODE_OFF);
            captureRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, CaptureRequest.CONTROL_AF_TRIGGER_CANCEL);
            captureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CameraMetadata.CONTROL_AF_MODE_OFF);
            captureRequestBuilder.set(LENS_FOCUS_DISTANCE, x/15);
            //captureRequestBuilder.set(FLASH_MODE, FLASH_MODE_TORCH);

            //captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_MANUAL);
            captureRequestBuilder.addTarget(surface);
            cameraDevice.createCaptureSession(Arrays.asList(surface), new CameraCaptureSession.StateCallback(){
                @Override
                public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
                    //The camera is already closed
                    if (null == cameraDevice) {
                        return;
                    }
                    // When the session is ready, we start displaying the preview.
                    cameraCaptureSessions = cameraCaptureSession;
                    updatePreview();
                }
                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {
                    makeText(AndroidCamera2API.this, "Configuration change", Toast.LENGTH_SHORT).show();
                }
            }, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
    protected void createCameraPreview3(boolean flash) {
        try {
            SurfaceTexture texture = textureView.getSurfaceTexture();
            assert texture != null;
            texture.setDefaultBufferSize(imageDimension.getWidth(), imageDimension.getHeight());
            Surface surface = new Surface(texture);
           // captureRequestBuilder.set(CaptureRequest.CONTROL_MODE, CaptureRequest.CONTROL_MODE_OFF);
           // captureRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, CaptureRequest.CONTROL_AF_TRIGGER_CANCEL);
           // captureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CameraMetadata.CONTROL_AF_MODE_OFF);
            captureRequestBuilder.set(FLASH_MODE, FLASH_MODE_OFF);
            if(flash) {
                captureRequestBuilder.set(FLASH_MODE, FLASH_MODE_TORCH);
            }


            //captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_MANUAL);
            captureRequestBuilder.addTarget(surface);
            cameraDevice.createCaptureSession(Arrays.asList(surface), new CameraCaptureSession.StateCallback(){
                @Override
                public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
                    //The camera is already closed
                    if (null == cameraDevice) {
                        return;
                    }
                    // When the session is ready, we start displaying the preview.
                    cameraCaptureSessions = cameraCaptureSession;
                    updatePreview();
                }
                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {
                    makeText(AndroidCamera2API.this, "Configuration change", Toast.LENGTH_SHORT).show();
                }
            }, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
    private void openCamera(boolean xd) {
        int cameranumber = xd ? 1 : 0;

        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        Log.e(TAG, "is camera open");
        try {
            cameraId = manager.getCameraIdList()[cameranumber]; //set front/back camera here(the integer)
            CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
            StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            assert map != null;
            imageDimension = map.getOutputSizes(SurfaceTexture.class)[0];
            // Add permission for camera and let user grant the permission
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(AndroidCamera2API.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CAMERA_PERMISSION);
                return;
            }
            manager.openCamera(cameraId, stateCallback, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "openCamera X");
    }
    protected void updatePreview() {
        if(null == cameraDevice) {
            Log.e(TAG, "updatePreview error, return");
        }
        captureRequestBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO); //SET APERTURE AND STUFF
        try {
            cameraCaptureSessions.setRepeatingRequest(captureRequestBuilder.build(), null, mBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
    private void closeCamera() {
        if (null != cameraDevice) {
            cameraDevice.close();
            cameraDevice = null;
        }
        if (null != imageReader) {
            imageReader.close();
            imageReader = null;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                // close the app
                makeText(AndroidCamera2API.this, "Sorry, you can't use this app without granting permission", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
        startBackgroundThread();
       /* threadName = Thread.currentThread().getName();
        if(Looper.myLooper() == Looper.getMainLooper())
        {
            Toast.makeText(AndroidCamera2API.this, "ui", Toast.LENGTH_SHORT).show();

        } */
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setDuration(1200);

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setStartOffset(1000);
        fadeOut.setDuration(1000);

        if (textureView.isAvailable()) {
            openCamera(CameraNumber);
            //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            textureView.setAnimation(fadeIn);

        } else {
            textureView.setSurfaceTextureListener(textureListener);
        }
    }
    @Override
    public void onPause() {
        Log.e(TAG, "onPause");
        closeCamera();
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setDuration(1200);

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setStartOffset(1000);
        fadeOut.setDuration(1000);
        textureView.setAnimation(fadeOut);

        stopBackgroundThread();
        super.onPause();
    }

    //public int GenerateSignature(int seed){

    //}
   // @RequiresApi(api = Build.VERSION_CODES.O)
    public static String md5(byte[] s) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(s);
            byte[] magnitude = digest.digest();
            BigInteger bi = new BigInteger(1, magnitude);
            String hash = String.format("%0" + (magnitude.length << 1) + "x", bi);
            magnitude = null;
            return hash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    protected String AverageColor(int pixelSpacing) {
        System.gc();
        Bitmap bitmap = BitmapFactory.decodeFile(picPath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos); //bm is the bitmap object
        byte[] bitmapBytes = baos.toByteArray();
        String Hash = md5(bitmapBytes);
        int redBucket;
        int greenBucket;
        int blueBucket;
        int pixelCount;
        int blocks = 80;
        blockCount = 1;
        blockCount2 = 1;

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

        //return rgb[7];
        ReferenceID = "x102342410";
        return Hash;
    }

    public void turnOnFlashLight() {

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mCameraManager.setTorchMode(cameraId, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void FadeIn(final ImageView v,
                              final int begin_alpha, final int end_alpha, int time,
                              final boolean toggleVisibility) {


        startBackgroundThread();


        v.setImageAlpha(begin_alpha);


        if (toggleVisibility) {
            if (v.getVisibility() == View.GONE)
                v.setVisibility(View.VISIBLE);
            else
                v.setVisibility(View.GONE);
        }

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime,
                                               Transformation t) {
                if (interpolatedTime == 1) {

                        v.setImageAlpha(end_alpha);


                    if (toggleVisibility) {
                        if (v.getVisibility() == View.GONE)
                            v.setVisibility(View.VISIBLE);
                        else
                            v.setVisibility(View.GONE);
                    }
                } else {
                    int new_alpha = (int) (begin_alpha + (interpolatedTime * (end_alpha - begin_alpha)));

                        v.setImageAlpha(new_alpha);


                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration(time);
        v.startAnimation(a);
    }
  /*  public class BackgroundThreader extends AsyncTask<String,String,String> {


        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }*/
   /* public class MyAccessibilityService extends AccessibilityService{
        @Override
        public void onAccessibilityEvent(AccessibilityEvent event) {
            if (event.getEventType() != AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
                Log.d("ACCESS", "1");
                return; // event is not a notification
            }
            String sourcePackageName = (String) event.getPackageName();

            Parcelable parcelable = event.getParcelableData();
            if (parcelable instanceof Notification) {
                Log.d("ACCESS", "2");

                // Statusbar Notification
            }
            else {
                Log.d("ACCESS", "3");

                Toast.makeText(AndroidCamera2API.this, "toast detected", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onInterrupt() {

        }

    }
   // public abstract class camera {
   //     public abstract CaptureRequest.Builder createCaptureRequest(int template);
   // }*/

}