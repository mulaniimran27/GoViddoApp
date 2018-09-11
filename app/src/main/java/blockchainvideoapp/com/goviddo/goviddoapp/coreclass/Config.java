package blockchainvideoapp.com.goviddo.goviddoapp.coreclass;


public class Config {

    String replaceMainPartURL = "https://www.indianmoviefriend.com/api/"+
            "Replace this http://simimf.xyz/"+
            "from all the below URL's with The above URL";

    public static final String DATA_URL = "https://www.goviddo.com/api/contrylist.php?cmd=contrylist";

    public static String WebEpisodeListURL = "https://www.goviddo.com/api/webEpisodes.php?cmd=webEpisode&ws_id=";

    public static String OriginalsURL = "https://www.goviddo.com/api/orignal1.php?cmd=imforiginals";

    public static String MoviesURL = "https://www.goviddo.com/api/orignal.php?cmd=imforiginals";

    public static String ShortFilmsURL = "https://www.goviddo.com/api/orignal.php?cmd=imforiginals";

    public static String WebSeriesURL = "https://www.goviddo.com/api/webSeriesList1.php?cmd=webSeries";

    public static String TrendingsURL = "https://www.goviddo.com/api/trendings.php?cmd=trendings";






    public static String NotificationRecievedURL = "https://www.goviddo.com/api/notification_recieved.php?cmd=imforiginals&slug=";



    public static String NotificationTokenUpdateURL = "https://www.goviddo.com/api/notificationTokenUpdate.php?cmd=notificationUpdate&email=";



    public static String GetTokenURL = "https://www.goviddo.com/api/getData.php?cmd=gettoken&email=";


    public static String NotificationRecivedWebEpisode = "https://www.goviddo.com/api/notificationWebseries.php?cmd=webEpisode&slug=";




    public static String SliderImageClickURL = "https://www.goviddo.com/api/imgSliderClick.php?cmd=sliderClick&slug=";


    public static String SelectedImageURLWebseries = "https://www.goviddo.com/api/webSeriesList1.php?cmd=webSeriesSelectedImage&name=";



    public static String VideoShareURL = "https://www.goviddo.com/api/shareURL.php?cmd=shareURL&slug=";


    public static String FetchUserDataURL = "https://www.goviddo.com/api/fetchUserData.php?cmd=getUserData&email=";



    public static String UpdatePasswordURL = "https://www.goviddo.com/api/UpdatePassword.php?cmd=updatePassword&email=";



    public static String UpdateProfileURL = "https://www.goviddo.com/api/ProfielUpdate.php?cmd=updateProfile&fname=";




    public static String VideoPlayerPlayURL = "https://www.goviddo.com/api/example.php?video_id=";



    public static String RegistrationURL = "https://www.goviddo.com/api/registerApp.php?cmd=register&fname=";



    public static String IMFAccountLoginURL = "https://www.goviddo.com/api/login1App.php?cmd=login&email=";



    public static String FBLoginURL = "https://www.goviddo.com/api/FBregisterApp.php?cmd=FBregister&fname=";



    public static String GoogleLoginURL = "https://www.goviddo.com/api/GoogleRegisterApp.php?cmd=GoogleRegister&fname=";



    public static String ForgetPasswordURL = "https://www.goviddo.com/api/ForgetPasswordApp.php?cmd=forgetPassword&email=";



    public static String AboutUsURl = "https://www.goviddo.com/about_us";

    public static String MediaURL = "https://www.indianmoviefriend.com/media";

    public static String  CareerURL = "https://www.indianmoviefriend.com/careers";

    public static String ContactUsURL = "https://www.goviddo.com/contact_us";


    public static String TermsURL = "https://www.goviddo.com/terms";

    public static String PrivacyURL = "https://www.goviddo.com/privacy_cookies";

    public static String HelpURl = "https://www.goviddo.com/help";

    public static String FAQsURL = "https://www.goviddo.com/faqs";

    public static String FeddBackURL = "https://www.goviddo.com/feedback";

    public static String GetCountryNameFromCountryCodeURL = "https://www.goviddo.com/api/getPresentCountry.php?cmd=contrylist&isoCode=";



    public static String PlayBackInfoURL = "https://www.goviddo.com/api/PlayBackInfo.php?cmd=playBackInfo&userEmail=";


    public static String PlayBackCompleteURL = "https://www.goviddo.com/api/completePlayBack.php?cmd=playBackInfoEnd&playbackId=";



    public static String ShareVideoPlayURL = "https://www.w.com/api/shareURLData.php?cmd=getshareURLdata&shorturl=";



    public static String SubmitRatingsURL = "https://www.goviddo.com/api/insetRatings.php?cmd=ratings&user_email=";


    //Tags used in the JSON String
    public static final String TAG_ID = "id";
    public static final String TAG_ISOCODE = "iso_code";
    public static final String TAG_COUNTRY = "country_name";


    //JSON array name
    public static final String JSON_ARRAY = "result";


    // global topic to receive app wide push notifications
    public static final String TOPIC_GLOBAL = "global";

    // broadcast receiver intent filters
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";

    // id to handle the notification in the notification tray
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;

    public static final String SHARED_PREF = "ah_firebase";

}
