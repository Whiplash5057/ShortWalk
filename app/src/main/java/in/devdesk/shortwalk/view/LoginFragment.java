package in.devdesk.shortwalk.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import in.devdesk.shortwalk.R;
import in.devdesk.shortwalk.Utilities.FinalStrings;

/**
 * Created by richardandrews on 06/07/17.
 */

public class LoginFragment extends Fragment {


    @OnClick(R.id.btn_login_loginpage)
    public void submit(View view) {
        // TODO submit data to server...
        Intent i = new Intent(getActivity(),ParentTab.class);
        i.putExtra(FinalStrings.MAINACTIVITY_TO_PARENTTAB, "TestFinals");
        startActivity(i);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.login_page, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
