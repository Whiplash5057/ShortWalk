package in.devdesk.shortwalk.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.devdesk.shortwalk.R;
import in.devdesk.shortwalk.Utilities.FinalStrings;
import in.devdesk.shortwalk.Utilities.Utils;
import in.devdesk.shortwalk.presenter.LoginFragmentPresenter;
import in.devdesk.shortwalk.presenter.ShortWalkModelPresenter;

/**
 * Created by richardandrews on 06/07/17.
 */

public class LoginFragment extends Fragment {


    String username, password;
    ShortWalkModelPresenter.LoginFragmentPresenter loginFragmentPresenter;
    HashMap<String, String> loginDetails = new HashMap<>();

    @BindView(R.id.et_login_loginpage)
    EditText usernameView;

    @BindView(R.id.et_password_loginpage)
    EditText passwordView;

    @OnClick(R.id.btn_login_loginpage)
    public void submit(View view) {
        // TODO submit data to server...

        //== To enter the map fragment
//        Intent i = new Intent(getActivity(),ParentTab.class);
//        i.putExtra(FinalStrings.MAINACTIVITY_TO_PARENTTAB, "TestFinals");
//        startActivity(i);

        username = usernameView.getText().toString();
        password = passwordView.getText().toString();


        loginDetails.put("username", username);
        loginDetails.put("password", password);
        loginFragmentPresenter = new LoginFragmentPresenter();
        if(Utils.isNetworkAvailable(getActivity()))
        {
            loginFragmentPresenter.callLoginApi(loginDetails);
        }
        else{
            Toast.makeText(getActivity(), "No Internet", Toast.LENGTH_SHORT).show();
        }


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_page, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
