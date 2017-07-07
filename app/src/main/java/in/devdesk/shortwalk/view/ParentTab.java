package in.devdesk.shortwalk.view;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.devdesk.shortwalk.R;
import in.devdesk.shortwalk.presenter.MyPagerAdapter;

public class ParentTab extends AppCompatActivity {


//  CODING REFERENCES

/**
 * reference 1: https://stackoverflow.com/questions/3282390/add-floating-point-value-to-android-resources-values
 * reference 2: https://stackoverflow.com/questions/19297522/android-integer-from-xml-resource
 * reference 3: https://stackoverflow.com/questions/4253328/getstring-outside-of-a-context-or-activity
 * reference 4: https://www.youtube.com/watch?v=ZQSu48J9TBg
 * reference 5: https://www.youtube.com/watch?v=0dToEEuPL9Y
 * reference 6: https://stackoverflow.com/questions/28351539/circle-button-with-shadow
 * reference 7: https://medium.com/@adinugroho/upload-image-from-android-app-using-retrofit-2-ae6f922b184c
 * reference 8: https://github.com/PhilJay/MPAndroidChart
 * reference 9: https://www.youtube.com/watch?v=RN4Zmxlah_I
 * reference 10: https://www.youtube.com/watch?v=a_Ap6T4RlYU
 * reference 11: http://javapapers.com/android/android-geocoding-to-get-latitude-longitude-for-an-address/
 * reference 12: https://github.com/MasayukiSuda/EasingInterpolator
 */

/**
 * 1. Add drawable resource to activity or fragment
 * 2. Storing integers from resource integers
 * 3. Add resource outside Activity Context
 * 4. Add all required configurations to Map
 * 5. Add Maps to a fragment
 * 6. Add Shapes in different states of user interraction
 * 7. Uploading Images with retrofit
 * 8. Add Line Graph
 * 9. Calendar View
 * 10. Date and Time Picker
 * 11. Get LatLng from Location
 * 12. Easy Animation
 */

    private MyPagerAdapter mAdapter;


    @BindView(R.id.viewpager_main)
    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_tab);
        ButterKnife.bind(this);

        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);
        mPager.setCurrentItem(1);
    }
}
