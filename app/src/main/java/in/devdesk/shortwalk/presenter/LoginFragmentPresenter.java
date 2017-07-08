package in.devdesk.shortwalk.presenter;

import android.util.Log;

import java.util.HashMap;
import java.util.List;

import in.devdesk.shortwalk.model.pojo.LoginRequest;
import in.devdesk.shortwalk.model.pojo.LoginResponse;
import in.devdesk.shortwalk.view.LoginFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by richardandrews on 07/07/17.
 */

public class LoginFragmentPresenter{

    public void loginTesting(String testString)
    {
//        return testString;
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.loginInnerFragTesting(testString);
    }

}
