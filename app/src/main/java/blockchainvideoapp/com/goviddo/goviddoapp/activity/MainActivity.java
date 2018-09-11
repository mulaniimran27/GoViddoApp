package blockchainvideoapp.com.goviddo.goviddoapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import blockchainvideoapp.com.goviddo.goviddoapp.R;
import blockchainvideoapp.com.goviddo.goviddoapp.coreclass.LoginUserDetails;

public class MainActivity extends AppCompatActivity {

    LoginUserDetails mLoginUserDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mLoginUserDetails = new LoginUserDetails(this);

        if (mLoginUserDetails.getEmail()==""){

            Intent sessioChkIntent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(sessioChkIntent);
            finish();

        }
        else {

            Toast.makeText(this, "Email - " + mLoginUserDetails.getEmail(), Toast.LENGTH_LONG).show();

        }

    }
}
