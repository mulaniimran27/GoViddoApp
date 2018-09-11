package blockchainvideoapp.com.goviddo.goviddoapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import blockchainvideoapp.com.goviddo.goviddoapp.R;
import blockchainvideoapp.com.goviddo.goviddoapp.coreclass.LoginUserDetails;

public class LoginActivity extends AppCompatActivity {

    ImageView mImageViewLoginLogo;
    android.support.design.widget.TextInputLayout mTextInputLayoutUserName,mTextInputLayoutUserPassword;
    android.support.design.widget.TextInputEditText mEdtUserName,mEdtUserPassword;
    Button mBtnLogin;
    String mUserName, mPassword;
    Vibrator mVibrator;
    LoginUserDetails mLoginUserDetails;
    TabLayout myCustomTabsPromotion;
    TextView mTextViewMyTabsPromotionTitle1, mTextViewMyTabsPromotionTitle2,mTextViewAboutUs, mTextViewMedia, mTextViewContactUs, mTextViewTermsAndConition, mTextViewPrivacyAndCoockies, mTextViewHelp, mTextViewFAQs, mTxtViewFeedBack;


    TextView mTextViewNewUserRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mLoginUserDetails = new LoginUserDetails(this);


        if (mLoginUserDetails.getEmail() != "")
        {
            Intent loginIntent = new Intent(LoginActivity.this,MainActivity.class);
            loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(loginIntent);
            finish();
        }


        mImageViewLoginLogo = findViewById(R.id.imageViewLoginLogo);

        mTextInputLayoutUserName = findViewById(R.id.textInputLayoutUserName);
        mTextInputLayoutUserPassword = findViewById(R.id.textInputLayoutUserPassword);

        mEdtUserName = findViewById(R.id.edtUserName);
        mEdtUserPassword = findViewById(R.id.edtUserPassword);

        mBtnLogin = findViewById(R.id.btnLogin);

        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        mTextViewNewUserRegistration = findViewById(R.id.textRegisterNewUser);



        mTextViewNewUserRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent registrationIntent = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(registrationIntent);

            }
        });




        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mUserName = mEdtUserName.getText().toString();
                mPassword = mEdtUserPassword.getText().toString();

                submitForm(mUserName,mPassword);


            }
        });





        mTextViewMyTabsPromotionTitle1 = (TextView) findViewById(R.id.textViewPromotionCustomTabsTitle1);
        mTextViewMyTabsPromotionTitle2 = (TextView) findViewById(R.id.textViewPromotionCustomTabsTitle2);
        mTextViewMyTabsPromotionTitle1.setText("Watch for Free");
        mTextViewMyTabsPromotionTitle2.setText("Watch all movies and series for free");


        myCustomTabsPromotion = (TabLayout) findViewById(R.id.tabs);


        myCustomTabsPromotion.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == 0){

                    mTextViewMyTabsPromotionTitle1.setText("Watch for Free");
                    mTextViewMyTabsPromotionTitle2.setText("Watch all movies and series for free.");

                }
                else {

                    mTextViewMyTabsPromotionTitle1.setText("Enjoy on a range of devices");
                    mTextViewMyTabsPromotionTitle2.setText("IMF is available on PC, MAC, Mobiles and Tablets.");

                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                if (tab.getPosition() == 0){
                    //    Toast.makeText(LoginActivity.this, "position"+tab.getPosition(), Toast.LENGTH_SHORT).show();

                    mTextViewMyTabsPromotionTitle1.setText("Enjoy on a range of devices");
                    mTextViewMyTabsPromotionTitle2.setText("GoViddo is available on PC, MAC, Mobiles and Tablets.");

                }
                else {
                    //   Toast.makeText(LoginActivity.this, "position"+tab.getPosition(), Toast.LENGTH_SHORT).show();

                    mTextViewMyTabsPromotionTitle1.setText("Watch for Free");
                    mTextViewMyTabsPromotionTitle2.setText("Watch all movies and series for free.");

                }

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {


            }
        });





        mTextViewAboutUs = (TextView) findViewById(R.id.textViewAboutUs);
        mTextViewContactUs = (TextView) findViewById(R.id.textViewContactUs);
        mTextViewMedia = (TextView) findViewById(R.id.textViewMedia);

        mTextViewTermsAndConition = (TextView) findViewById(R.id.textViewTermsAndCondition);
        mTextViewPrivacyAndCoockies = (TextView) findViewById(R.id.textViewPrivacyAndCoockies);
        mTextViewHelp = (TextView) findViewById(R.id.textViewHelp);
        mTextViewFAQs = (TextView) findViewById(R.id.textViewFAQs);
        mTxtViewFeedBack = (TextView) findViewById(R.id.textViewFeedBack);








    }

    public void submitForm(String userName, String password)
    {

        if (!checkUserName(userName)){
            mVibrator.vibrate(1000);
            return;
        }
        else if (!checkPassword(password)){
            mVibrator.vibrate(1000);
            return;

        }
        else {
            mTextInputLayoutUserName.setErrorEnabled(false);
            mTextInputLayoutUserPassword.setErrorEnabled(false);

            mLoginUserDetails.setEmail(userName);
            mLoginUserDetails.setPassword(password);

            Toast.makeText(LoginActivity.this, "Registration Successfull Please Verify Email", Toast.LENGTH_LONG).show();
            Intent loginIntent = new Intent(LoginActivity.this,MainActivity.class);
            loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(loginIntent);
            finish();
        }


    }



    private boolean checkUserName(String userName) {

        userName = userName.trim();

        if (userName.trim().isEmpty() || !isValidEmail(userName)){
            mTextInputLayoutUserName.setErrorEnabled(true);
            mTextInputLayoutUserName.setError("Please Enter Valid Email ID");
            return false;
        }
        mTextInputLayoutUserName.setErrorEnabled(false);
        return true;
    }

    private boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    private boolean checkPassword(String password) {
        if (password.trim().isEmpty() || (password.length() < 7)){
            mTextInputLayoutUserPassword.setErrorEnabled(true);
            mTextInputLayoutUserPassword.setError("Please Enter Password length greater than 6");
            return false;
        }
        mTextInputLayoutUserPassword.setErrorEnabled(false);
        return true;

    }



}
