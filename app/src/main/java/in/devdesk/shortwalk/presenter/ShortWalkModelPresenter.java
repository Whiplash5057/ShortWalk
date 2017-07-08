package in.devdesk.shortwalk.presenter;

import java.util.HashMap;

/**
 * Created by richardandrews on 28/06/17.
 */

public interface ShortWalkModelPresenter {

/**
 * activity_main
 */
    public interface MainActivityPresenter{

    };

//----- xxx -----//



/**
 * activity_parent_tab
 */
    public interface ParentTabPresenter{

    };


//----- xxx -----//




/**
 * map_fragment
 */
    public interface MapFragmentPresenter{

    };

//----- xxx -----//



/**
 * map_screen
 */

    public interface MapScreenPresenter{

    };
//----- xxx -----//



/**
 * top_five
 */
    public interface TopFivePresenter{

    };

//----- xxx -----//



/**
 * user_profile
 */
    public interface UserProfilePresenter{

    };
//----- xxx -----//

/**
 * map_fragment
 */
    public interface LoginFragmentPresenter{

        void callLoginApiInPresenter(HashMap<String, String> loginDetails);
    };

//----- xxx -----//


}
