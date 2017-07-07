package in.devdesk.shortwalk.presenter;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import in.devdesk.shortwalk.R;
import in.devdesk.shortwalk.view.MapScreen;
import in.devdesk.shortwalk.view.TopFive;
import in.devdesk.shortwalk.view.UserProfile;


/**
 * Created by richardandrews on 24/06/17.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {


//    private int tabNumbers = (int) Resources.getSystem().getInteger(R.integer.numberOfTabs);
    private int tabNumbers = 3;

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        UserProfile one = new UserProfile();
        MapScreen two = new MapScreen();
        TopFive three = new TopFive();

        switch(position)
        {
            case 0:  return one;
            case 1:  return two;
            case 2:  return three;
            default: return null;
        }

    }

    @Override
    public int getCount() {

        return tabNumbers;
    }
}
