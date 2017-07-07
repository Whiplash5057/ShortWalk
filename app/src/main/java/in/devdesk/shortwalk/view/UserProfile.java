package in.devdesk.shortwalk.view;

import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.devdesk.shortwalk.R;
import in.devdesk.shortwalk.presenter.UserProfilePresenter;

/**
 * Created by richardandrews on 24/06/17.
 */

public class UserProfile extends Fragment {

    ArrayList<String> xAXIS = new ArrayList<>();
    ArrayList<Entry> yAXIS = new ArrayList<>();
    ArrayList<Entry> yAXISavg = new ArrayList<>();
    int x = 0;
    int numDataPoints = 4;

    //always leave fragment constructor empty
    public UserProfile() {}


    @BindView(R.id.chart)
    LineChart chart;


    @BindView(R.id.calview_profile)
    MaterialCalendarView materialCalendarView;


    @OnClick(R.id.btn_hideshow)
    public void submit(View view) {
        // TODO submit data to server...
        Log.i("TEST", "TEST");
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_profile, container, false);
        ButterKnife.bind(this, view);

        //---- Graph

        for (int i = 0; i < numDataPoints; i++)
        {
            float yValue = (float) x;
            x += 1;
            yAXIS.add(new Entry(yValue, i));
            yAXISavg.add(new Entry(yValue - 1, i));
            xAXIS.add(i, String.valueOf(x));
        }
        String[] xaxis = new String[xAXIS.size()];
        for (int i = 0; i < xAXIS.size(); i++)
        {
            xaxis[i] = xAXIS.get(i).toString();
        }

        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

        LineDataSet lineDataSet1 = new LineDataSet(yAXIS, "You");
        lineDataSet1.setDrawCircles(false);
        lineDataSet1.setColor(Color.BLUE);

        LineDataSet lineDataSet2 = new LineDataSet(yAXISavg, "average user");
        lineDataSet2.setDrawCircles(false);
        lineDataSet2.setColor(Color.RED);

        lineDataSets.add(lineDataSet1);
        lineDataSets.add(lineDataSet2);

        chart.setData(new LineData(lineDataSets));
        chart.setVisibleXRangeMaximum(5f);


        //---- Graph End


        //---- Calendar
//        materialCalendarView.state().edit()
//                .setFirstDayOfWeek(Calendar.WEDNESDAY)
//                .setMinimumDate(CalendarDay.from(2016, 4, 3))
//                .setMaximumDate(CalendarDay.from(2016, 5, 12))
//                .setCalendarDisplayMode(CalendarMode.MONTHS)
//                .commit();


        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                UserProfilePresenter uPPInstance = new UserProfilePresenter();
                String logValue = uPPInstance.logDateSuccessful();
//                Log.i("Date", logValue + date);
                Toast.makeText(getActivity(), logValue + date, Toast.LENGTH_SHORT).show();
            }
        });
        //---- Calendar End

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
