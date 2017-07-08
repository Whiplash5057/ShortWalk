package in.devdesk.shortwalk.view;

/**
 * Created by richardandrews on 28/06/17.
 */

public interface ShortWalkView {

/**
 * activity_main
 */
    public interface MainActivity{

    };

//----- xxx -----//



/**
 * activity_parent_tab
 */
    public interface ParentTab{

    };


//----- xxx -----//




/**
 * map_fragment
 */
    public interface MapFragment{

    };

//----- xxx -----//



/**
 * map_screen
 */

    public interface MapScreen{

    };
//----- xxx -----//



/**
 * top_five
 */
    public interface TopFive{

    };

//----- xxx -----//



/**
 * user_profile
 */
    public interface UserProfile{

    };
//----- xxx -----//

/**
 * login_page
 */
    public interface LoginFragment{
        public void receiveResponse(String successFailure);
    };

//----- xxx -----//

}
