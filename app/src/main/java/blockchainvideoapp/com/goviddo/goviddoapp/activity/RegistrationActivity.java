package blockchainvideoapp.com.goviddo.goviddoapp.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Vibrator;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;

import blockchainvideoapp.com.goviddo.goviddoapp.R;
import blockchainvideoapp.com.goviddo.goviddoapp.coreclass.Config;
import blockchainvideoapp.com.goviddo.goviddoapp.coreclass.TLSSocketFactory;
import blockchainvideoapp.com.goviddo.goviddoapp.searchable_spinner.OnSpinerItemClick;
import blockchainvideoapp.com.goviddo.goviddoapp.searchable_spinner.SpinnerDialog;


public class RegistrationActivity extends AppCompatActivity {


    private EditText spinner;
    private ArrayList<String> students;

    private ArrayList<String> iso_code_list;

    JSONArray result;
    RadioGroup mRadioGroupGender;
    RadioButton mRadioButton;

    EditText mEditTextFirstName, mEditTextLastName, mEditTextEmail, mEditTextPassword, mEditTextConfirmPassword, mEditTextBirthDate;
    Button mBtnRegister;

    public static String mFirstName, mLastName, mEmailAddress, mPassword, mConfirmPassword, mCountryName;
    private static String mFinalEmail;
    public static String mGender = "";

    Vibrator mVibrator;

    RequestQueue requestQueueRegisterUser;
    RequestQueue requestQueueCountryDataGet;

    TextInputLayout mTextInputLayoutFirstName, mTextInputLayoutLastName, mTextInputLayoutEmail,
            mTextInputLayoutCountryName, mTextInputLayoutGender, mTextInputLayoutConfirmPassword,mTextInputLayoutPassword, mTextInputLayoutBirthDate;

    static String registerURL;
    int mGenderId;

   SpinnerDialog spinnerDialog;


    String selected_iso_code, user_birth_date;


    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;


    JSONObject mPasswordJ, mConfirmPasswordJ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        mCountryName = "";

        getData();

        mRadioGroupGender = (RadioGroup) findViewById(R.id.radioGender);


        mEditTextFirstName = (EditText) findViewById(R.id.editTextFName);
        mEditTextLastName = (EditText) findViewById(R.id.editTextLName);
        mEditTextEmail = (EditText) findViewById(R.id.editTextEmail);
        mEditTextPassword = (EditText) findViewById(R.id.editTextPassword);
        mEditTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        mBtnRegister = (Button) findViewById(R.id.btnRegister);

        mEditTextBirthDate = (EditText) findViewById(R.id.editTextBirthDate);


        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        mTextInputLayoutFirstName = (TextInputLayout) findViewById(R.id.textInputLayoutFirstName);
        mTextInputLayoutLastName = (TextInputLayout) findViewById(R.id.textInputLayoutLastName);
        mTextInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);

        mTextInputLayoutGender = (TextInputLayout) findViewById(R.id.textInputLayoutGender);
        mTextInputLayoutCountryName = (TextInputLayout) findViewById(R.id.textInputLayoutCountryName);

        mTextInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);
        mTextInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        mTextInputLayoutBirthDate = (TextInputLayout) findViewById(R.id.textInputLayoutBirthDate);



        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mFirstName = mEditTextFirstName.getText().toString();
                mLastName = mEditTextLastName.getText().toString();
                mEmailAddress = mEditTextEmail.getText().toString();
                mPassword = mEditTextPassword.getText().toString();
                mConfirmPassword = mEditTextConfirmPassword.getText().toString();

                submitForm();


            }
        });

        students = new ArrayList<String>();
        iso_code_list = new ArrayList<String>();
        spinner = (EditText) findViewById(R.id.spinnerCountry);

        spinner.setFocusable(false);
        spinner.setClickable(true);

        spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog.showSpinerDialog();
            }
        });



        mEditTextBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setDate(v);

                calendar = Calendar.getInstance();

                year = calendar.get(Calendar.YEAR);

                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                showDate(year, month+1, day);

            }
        });



    }


    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {

            Calendar then = Calendar.getInstance();

            then.add(Calendar.YEAR, -15);

            Calendar thenMin = Calendar.getInstance();
            thenMin.add(Calendar.YEAR, -115);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_DARK,myDateListener,year,month,day);

            datePickerDialog.getDatePicker().setMaxDate(then.getTimeInMillis());
            datePickerDialog.getDatePicker().setMinDate(thenMin.getTimeInMillis());

            return datePickerDialog;
        }
        return null;
    }




    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            showDate(year, month+1, dayOfMonth);
        }


    };

    private void showDate(int year, int month, int day) {
        String Displayday;

        if (day<=9){
            Displayday = "0"+day;
        }
        else {
            Displayday = ""+day;
        }
        mEditTextBirthDate.setText(new StringBuilder().append(year).append("-")
                .append(month).append("-").append(Displayday));
    }





    private void submitForm() {

        if (!checkFirstName()){
            mVibrator.vibrate(1000);
            return;
        }
        if (!checkLastName()){
            mVibrator.vibrate(1000);
            return;

        }
        if (!checkGender()){
            mVibrator.vibrate(1000);
            return;

        }
        if (!checkEmail()){
            mVibrator.vibrate(1000);
            return;

        }
        if (!checkPassword()){
            mVibrator.vibrate(1000);
            return;

        }
        if (!checkConfirmPassword()){
            mVibrator.vibrate(1000);
            return;

        }
        if (!samePassword()){
            mVibrator.vibrate(1000);
            return;

        }
        if (!checkCountryName()){
            mVibrator.vibrate(1000);
            return;

        }
        if (!checkBirthDate()){
            mVibrator.vibrate(1000);
            return;
        }
        mTextInputLayoutFirstName.setErrorEnabled(false);
        mTextInputLayoutFirstName.setErrorEnabled(false);
        mTextInputLayoutEmail.setErrorEnabled(false);
        mTextInputLayoutGender.setErrorEnabled(false);
        mTextInputLayoutCountryName.setErrorEnabled(false);
        mTextInputLayoutConfirmPassword.setErrorEnabled(false);
        mTextInputLayoutPassword.setErrorEnabled(false);
        mTextInputLayoutBirthDate.setErrorEnabled(false);

        if (mGender.equals("Male"))
        {
            mGenderId = 1;
        }
        else if (mGender.equals("Female")){
            mGenderId = 2;
        }

        user_birth_date = String.valueOf(mEditTextBirthDate.getText());


        final String mPasswordAppend = Base64.encodeToString(mPassword.getBytes(), Base64.DEFAULT);

        JSONObject j = new JSONObject();
        try {
            mPasswordJ = j.put("pass", mPasswordAppend);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        registerURL = Config.RegistrationURL+mFirstName+"&lname="+mLastName+"&gender="+mGenderId+"&email="+mFinalEmail+"&password="+mPasswordJ+"&country="+mCountryName+"&birthDate="+user_birth_date+"&mobRegType=1";

        // Toast.makeText(this, registerURL, Toast.LENGTH_LONG).show();
        // System.out.println(registerURL);
        //Toast.makeText(this, registerURL, Toast.LENGTH_SHORT).show();

        final ProgressDialog loading = ProgressDialog.show(this, "Please wait...","Fetching data...",false,false);


        JsonObjectRequest jsonObjectRequestRegister = new JsonObjectRequest(Request.Method.GET, registerURL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    int status = response.getInt("status");

                    if (status==1){

                        if (response.getString("msg").equals("This Email ID Already Register")){

                            loading.dismiss();
                            Toast.makeText(RegistrationActivity.this, "This Email ID Already Register", Toast.LENGTH_LONG).show();

                        }
                        else {
                            loading.dismiss();
                            // Toast.makeText(RegistrationActivity.this, "Registration Successfull Please Verify Email", Toast.LENGTH_LONG).show();


                            Intent registerSuccess = new Intent(RegistrationActivity.this, LoginActivity.class);
                            registerSuccess.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            ActivityCompat.finishAffinity(RegistrationActivity.this);
                            startActivity(registerSuccess);
                            finish();
                        }

                    }
                    else {
                        loading.dismiss();
                        Toast.makeText(RegistrationActivity.this, "Registration Failed Please check Data", Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                loading.dismiss();
                System.out.println("error");

            }
        });

        //   requestQueueRegisterUser = Volley.newRequestQueue(RegistrationActivity.this);

        //  requestQueueRegisterUser.add(jsonObjectRequestRegister);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                && Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            HttpStack stack = null;
            try {
                stack = new HurlStack(null, new TLSSocketFactory());
            } catch (KeyManagementException e) {
                e.printStackTrace();
                Log.d("Your Wrapper Class", "Could not create new stack for TLS v1.2");
                stack = new HurlStack();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                Log.d("Your Wrapper Class", "Could not create new stack for TLS v1.2");
                stack = new HurlStack();
            }
            requestQueueRegisterUser = Volley.newRequestQueue(RegistrationActivity.this, stack);
            requestQueueRegisterUser.add(jsonObjectRequestRegister);
        } else {
            requestQueueRegisterUser = Volley.newRequestQueue(RegistrationActivity.this);
            requestQueueRegisterUser.add(jsonObjectRequestRegister);
        }

    }




    private boolean samePassword() {

        if (mPassword.trim().equals(mConfirmPassword.trim())){
            mTextInputLayoutConfirmPassword.setErrorEnabled(false);
            return true;
        }else{
            mTextInputLayoutConfirmPassword.setErrorEnabled(true);
            mTextInputLayoutConfirmPassword.setError("Password Enter Same Password");
            return false;
        }
    }

    private boolean checkEmail() {

        mFinalEmail = mEmailAddress.trim();

        if (mFinalEmail.trim().isEmpty() || !isValidEmail(mFinalEmail)){
            mTextInputLayoutEmail.setErrorEnabled(true);
            mTextInputLayoutEmail.setError("Please Enter Valid Email ID");
            return false;
        }
        mTextInputLayoutEmail.setErrorEnabled(false);
        return true;

    }


    private boolean checkBirthDate() {

        String birthDate = String.valueOf(mEditTextBirthDate.getText());

        // Toast.makeText(this, birthDate, Toast.LENGTH_SHORT).show();

        if (birthDate.isEmpty()){
            mTextInputLayoutBirthDate.setErrorEnabled(true);
            mTextInputLayoutBirthDate.setError("Please Select Birth Date");
            return false;
        }
        mTextInputLayoutBirthDate.setErrorEnabled(false);
        return true;

    }


    private boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean checkCountryName() {

        if (mCountryName.isEmpty()){
            mTextInputLayoutCountryName.setErrorEnabled(true);
            mTextInputLayoutCountryName.setError("Please Select Country");
            return false;
        }
        mTextInputLayoutCountryName.setErrorEnabled(false);
        return true;

    }

    private boolean checkGender() {

        if (mGender.trim().isEmpty()){
            mTextInputLayoutGender.setErrorEnabled(true);
            mTextInputLayoutGender.setError("Please Select Gender");
            return false;
        }
        mTextInputLayoutGender.setErrorEnabled(false);
        return true;

    }

    private boolean checkConfirmPassword() {
        if (mConfirmPassword.trim().isEmpty()){
            mTextInputLayoutConfirmPassword.setErrorEnabled(true);
            mTextInputLayoutConfirmPassword.setError("Please Confirm Your Password");
            return false;
        }
        mTextInputLayoutConfirmPassword.setErrorEnabled(false);
        return true;

    }

    private boolean checkPassword() {
        if (mPassword.trim().isEmpty()){
            mTextInputLayoutPassword.setErrorEnabled(true);
            mTextInputLayoutPassword.setError("Please Enter Password");
            return false;
        }
        mTextInputLayoutPassword.setErrorEnabled(false);
        return true;

    }

    private boolean checkLastName() {
        if (mLastName.trim().isEmpty() || !isValidName(mLastName.trim())){
            mTextInputLayoutLastName.setErrorEnabled(true);
            mTextInputLayoutLastName.setError("Please Enter Valid Last Name");
            return false;
        }
        mTextInputLayoutLastName.setErrorEnabled(false);
        return true;

    }

    private boolean checkFirstName() {

        if (mFirstName.trim().isEmpty() || !isValidName(mFirstName.trim())){
            mTextInputLayoutFirstName.setErrorEnabled(true);
            mTextInputLayoutFirstName.setError("Please Enter Valid First Name");
            return false;
        }

        mTextInputLayoutFirstName.setErrorEnabled(false);
        return true;
    }


    private boolean isValidName(String name){

        return name.matches( "[a-zA-Z]*" );
    }




    public void rgClick(View v){

        int radioButtonId = mRadioGroupGender.getCheckedRadioButtonId();

        mRadioButton = (RadioButton) findViewById(radioButtonId);

        mGender = mRadioButton.getText().toString();

    }




    private void getData() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Config.DATA_URL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    result = response.getJSONArray("result");

                    getStudents(result);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //  requestQueueCountryDataGet = Volley.newRequestQueue(this);

        // requestQueueCountryDataGet.add(jsonObjectRequest);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                && Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            HttpStack stack = null;
            try {
                stack = new HurlStack(null, new TLSSocketFactory());
            } catch (KeyManagementException e) {
                e.printStackTrace();
                Log.d("Your Wrapper Class", "Could not create new stack for TLS v1.2");
                stack = new HurlStack();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                Log.d("Your Wrapper Class", "Could not create new stack for TLS v1.2");
                stack = new HurlStack();
            }
            requestQueueCountryDataGet = Volley.newRequestQueue(RegistrationActivity.this, stack);
            requestQueueCountryDataGet.add(jsonObjectRequest);
        } else {
            requestQueueCountryDataGet = Volley.newRequestQueue(RegistrationActivity.this);
            requestQueueCountryDataGet.add(jsonObjectRequest);
        }

    }


    private void getStudents(JSONArray result) {

        for (int i = 0; i < result.length(); i++) {
            try {
                JSONObject json = result.getJSONObject(i);

                students.add(json.getString(Config.TAG_COUNTRY));
                iso_code_list.add(json.getString("iso_code"));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        spinnerDialog = new SpinnerDialog(RegistrationActivity.this, students, "Select Country Name", R.style.DialogAnimations_SmileWindow);


        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                //  Toast.makeText(RegistrationActivity.this, item + "  " + position + "", Toast.LENGTH_SHORT).show();
                spinner.setText(item);

                selected_iso_code =  getIsoCode(position);

                mCountryName = selected_iso_code;


            }
        });

    }


    private String getIsoCode(int position){

        String iso_code = iso_code_list.get(position);

        return iso_code;
    }



}
