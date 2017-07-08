package in.devdesk.shortwalk.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.devdesk.shortwalk.R;
import in.devdesk.shortwalk.Utilities.FinalStrings;

public class MainActivity extends AppCompatActivity {


//  DESIGNING REFERENCES

/**
 * reference 1: https://www.youtube.com/watch?v=eciDWS2Nu0M
 * reference 1: https://www.youtube.com/watch?v=XhFUqdO0jz8
 * reference 1: https://www.youtube.com/watch?v=Y__CPZX97xE
 * reference 1: https://www.youtube.com/watch?v=ottYIDyE--I
 * reference 1: https://www.youtube.com/watch?v=SCBeXrAYwKk
 */

    /**
     * 1. ICON
     */


    //THIS IS WHERE THE LOGIN AND DEMO OF THE APP WILL BE PLACED...
    //TO FIND OUT WHERE THE ACTUAL APP BEGINS CHECK OUT PARENTTAB ACTIVITY


    private boolean mShowingNext;
    Thread thread;

    @BindString(R.string.loginSignup_btn_txt) String signUpTag;

    @BindString(R.string.login_btn_hint) String logInTag;

    @BindView(R.id.lbl_signup_gotosinuplogin)
    Button lbl_signup_gotosinuplogin;

    @OnClick(R.id.lbl_signup_gotosinuplogin)
    public void submit(View view) {

        if(lbl_signup_gotosinuplogin.getText() == signUpTag)
        {
            setLoginFragment("signup");
            lbl_signup_gotosinuplogin.setText(logInTag);
        }
        else if(lbl_signup_gotosinuplogin.getText() == logInTag)
        {
            setLoginFragment("login");
            lbl_signup_gotosinuplogin.setText(signUpTag);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        initializeElements();

        thread.start();

        setLoginFragment("login");

    }

    private void initializeElements() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                boolean isFirstStart = getPrefs.getBoolean("firstStart", true);
                if (isFirstStart) {
                    startActivity(new Intent(MainActivity.this, MyIntro.class));
                    SharedPreferences.Editor e = getPrefs.edit();
                    e.putBoolean("firstStart", false);
                    e.apply();
                }
            }
        });
    }

    private void setLoginFragment(String fragSelection) {

//        Intent i = new Intent(this,ParentTab.class);
//        i.putExtra(FinalStrings.MAINACTIVITY_TO_PARENTTAB, "TestFinals");
//        startActivity(i);



        LoginFragment loginFragment = new LoginFragment();
        SignUpFragment signUpFragment = new SignUpFragment();
        SplashScreen splashScreen = new SplashScreen();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_animation, R.anim.exit_animation);

        switch (fragSelection) {
            case "login":
                transaction.replace(R.id.frag_container_main, loginFragment, "LoginFragment");
                transaction.commit();
                break;
            case "signup":
                transaction.replace(R.id.frag_container_main, signUpFragment, "SignUpFragment");
                transaction.commit();
                break;
            case "splashscreen":
                transaction.replace(R.id.frag_container_main, splashScreen, "SplashScreenFragment");
                transaction.commit();
                break;
            default:
                break;
        }


    }

}
