package blockchainvideoapp.com.goviddo.goviddoapp.coreclass;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginUserDetails {

    SharedPreferences mSharedPreferences;
    String mUserName,mUserPassword;
    Context mContext;



    public LoginUserDetails(Context context) {
        this.mContext = context;
        mSharedPreferences = mContext.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
    }


    public void removeUserInfo(){
        mSharedPreferences.edit().clear().commit();
    }


    public String getEmail() {
        mUserName = mSharedPreferences.getString("email","");
        return mUserName;
    }

    public void setEmail(String email) {
        this.mUserName = email;
        mSharedPreferences.edit().putString("email",mUserName).commit();
    }

    public String getPassword() {
        mUserPassword = mSharedPreferences.getString("password","");
        return mUserPassword;
    }

    public void setPassword(String password) {
        this.mUserPassword = password;
        mSharedPreferences.edit().putString("password",mUserName).commit();
    }


}
