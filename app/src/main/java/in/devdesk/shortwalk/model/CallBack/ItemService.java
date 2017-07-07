package in.devdesk.shortwalk.model.CallBack;

import java.util.List;

import in.devdesk.shortwalk.model.pojo.Login.LoginRequest;
import in.devdesk.shortwalk.model.pojo.Login.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by richardandrews on 06/07/17.
 */

public interface ItemService {

    @POST("api/newuser")
    Call<List<LoginResponse>> createNewAccount(@Body LoginRequest loginRequest);
}
