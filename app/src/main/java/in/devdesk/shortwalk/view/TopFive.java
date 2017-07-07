package in.devdesk.shortwalk.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.devdesk.shortwalk.R;

/**
 * Created by richardandrews on 24/06/17.
 */

public class TopFive extends Fragment {
    public TopFive() {}  //always leave fragment constructor empty

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.top_five, container, false);
    }
}
