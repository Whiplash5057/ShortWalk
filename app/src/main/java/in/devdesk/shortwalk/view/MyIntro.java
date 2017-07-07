package in.devdesk.shortwalk.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;

import in.devdesk.shortwalk.R;

public class MyIntro extends AppIntro2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance(
                getResources().getString(R.string.slide_1_title),
                getResources().getString(R.string.slide_1_description),
                R.drawable.ic_map_profile,
                Color.parseColor("#b4a7d6")));

        addSlide(AppIntroFragment.newInstance(
                getResources().getString(R.string.slide_2_title),
                getResources().getString(R.string.slide_2_description),
                R.drawable.ic_dummy_profile,
                Color.parseColor("#8e7cc3")));

        addSlide(AppIntroFragment.newInstance(
                getResources().getString(R.string.slide_3_title),
                getResources().getString(R.string.slide_3_description),
                R.drawable.ic_aim,
                Color.parseColor("#b6d7a8")));

        addSlide(AppIntroFragment.newInstance(
                getResources().getString(R.string.slide_4_title),
                getResources().getString(R.string.slide_4_description),
                R.drawable.ic_plus,
                Color.parseColor("#93c47d")));

        addSlide(AppIntroFragment.newInstance(
                getResources().getString(R.string.slide_5_title),
                getResources().getString(R.string.slide_5_description),
                R.drawable.ic_phone,
                Color.parseColor("#6aa84f")));

        showStatusBar(false);
    }

    @Override
    public void onDonePressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        Toast.makeText(this, "Skip was pressed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        Toast.makeText(this, "Slide changed", Toast.LENGTH_SHORT).show();
    }
}
