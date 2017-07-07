package in.devdesk.shortwalk.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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

    @OnClick(R.id.buttonfornow)
    public void submit(View view) {
        // TODO submit data to server...
        Intent i = new Intent(this,ParentTab.class);
        i.putExtra(FinalStrings.MAINACTIVITY_TO_PARENTTAB, "TestFinals");
        startActivity(i);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                boolean isFirstStart = getPrefs.getBoolean("firstStart", true);
                if (isFirstStart)
                {
                    startActivity(new Intent(MainActivity.this, MyIntro.class));
                    SharedPreferences.Editor e = getPrefs.edit();
                    e.putBoolean("firstStart", false);
                    e.apply();
                }
            }
        });

        thread.start();
    }

}
