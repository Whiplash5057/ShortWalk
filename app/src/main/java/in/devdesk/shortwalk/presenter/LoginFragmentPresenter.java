package in.devdesk.shortwalk.presenter;

import android.util.Log;

import java.util.HashMap;
import java.util.List;

import in.devdesk.shortwalk.model.pojo.LoginRequest;
import in.devdesk.shortwalk.model.pojo.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by richardandrews on 07/07/17.
 */

public class LoginFragmentPresenter implements ShortWalkModelPresenter.LoginFragmentPresenter {

    private RestManager mManager;
    private LoginRequest loginRequest;
    private String responseMessage;
    @Override
    public String callLoginApi(HashMap<String, String> loginDetails) {

        loginRequest = new LoginRequest(loginDetails.get("username"), loginDetails.get("password"));
        mManager = new RestManager();

        Call<LoginResponse> loginCall = mManager.getmItemService().createNewAccount(loginRequest);
        loginCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful())
                {
                    LoginResponse successLogin = response.body();
                    Log.e("Success", successLogin.getMessage());

                    responseMessage = successLogin.getMessage();
                    Log.e("Success", responseMessage);
                }
                else {
                    LoginResponse errorMessage = response.body();
                    int sc = response.code();
                    switch (sc) {
                        case 400:
                            Log.e("Error 400", "Bad Request");
                            break;
                        case 404:
                            Log.e("Error 404", "Not Found");
                            break;
                        case 402:
                            Log.e("Error", "Enter your correct username and password");
                        default:
                            Log.e("Error", "Generic Error " + sc);
                    }
                    responseMessage = "error";
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });

        return responseMessage;
    }
}
