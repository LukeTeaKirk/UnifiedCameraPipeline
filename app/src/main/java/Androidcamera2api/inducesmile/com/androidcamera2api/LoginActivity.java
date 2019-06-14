package Androidcamera2api.inducesmile.com.androidcamera2api;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
public class LoginActivity extends AppCompatActivity {
    public Button Login;
    public Button Register;
    public EditText Username;
    public ImageView flash;
    public int x = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Login");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        flash = findViewById(R.id.imageView);



    }
        private void setFlash() throws InterruptedException {
            while (x > 0) {
                int n = x/2;
                if(n == 0) {
                    flash.setVisibility(View.VISIBLE);
                }
                else{
                    flash.setVisibility(View.INVISIBLE);

                }
                x++;
                wait(17);
            }
        }

        private void initViews(){
        Login = findViewById(R.id.loginButton);
        Register = findViewById(R.id.registerButton);
        Username = findViewById(R.id.usernameEdit);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Welcome " + Username.getText(), Toast.LENGTH_SHORT).show();
                //LoginActivity.this.wait(500);
                Intent myintent = new Intent(LoginActivity.this, AndroidCamera2API.class);
                LoginActivity.this.startActivity(myintent);
            }
        });    }
}
