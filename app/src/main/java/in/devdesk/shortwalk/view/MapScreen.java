package in.devdesk.shortwalk.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;
import in.devdesk.shortwalk.R;

/**
 * Created by richardandrews on 24/06/17.
 */

public class MapScreen extends Fragment {

    String[] listItems;
    String alertLocationTypeTitle;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();

    @BindView(R.id.lbl_map_loctype)
    TextView locationType;

    @OnClick(R.id.lbl_map_loctype)
    public void submit(View view) {
        // TODO submit data to server...
//        Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();
//        locationType.setText("Clicked");
        callAlertBuilder();
    }

    public MapScreen() {} //always leave fragment constructor empty

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_screen, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listItems = getResources().getStringArray(R.array.locationTypes);
        alertLocationTypeTitle = getResources().getString(R.string.alertLocationTypeTitle);
        checkedItems = new boolean[listItems.length];
    }


    public void callAlertBuilder()
    {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        mBuilder.setTitle(alertLocationTypeTitle);
        mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                if (isChecked)
                {
                    if(! mUserItems.contains(position))
                    {
                        mUserItems.add(position);
                    }

                }else if(mUserItems.contains(position))
                {
                        mUserItems.remove((Integer) position);
                }
            }
        });

        mBuilder.setCancelable(false);
        mBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String item = "";
                for(int i = 0; i < mUserItems.size(); i++)
                {
                    item += listItems[mUserItems.get(i)];
                    if(i != mUserItems.size() - 1 )
                    {
                        item += ", ";
                    }
                }
                locationType.setText(item);
                dialog.dismiss();
            }
        });
        mBuilder.setNegativeButton(R.string.dismiss, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        mBuilder.setNeutralButton(R.string.clear_all, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i = 0; i < checkedItems.length; i++)
                {
                    checkedItems[i] = false;
                    mUserItems.clear();
                }
                locationType.setText("");
                dialog.dismiss();
            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

}
