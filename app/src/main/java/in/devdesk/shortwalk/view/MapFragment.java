package in.devdesk.shortwalk.view;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.devdesk.shortwalk.R;
import in.devdesk.shortwalk.Utilities.AnimationClass;
import in.devdesk.shortwalk.Utilities.GeocodingLocation;
import in.devdesk.shortwalk.Utilities.MapHelperClasses;

import static in.devdesk.shortwalk.Utilities.FinalStrings.HOMEBASEVALUE;
import static in.devdesk.shortwalk.Utilities.FinalStrings.MY_PERMISSIONS_REQUEST_LOCATION;

/**
 * Created by richardandrews on 24/06/17.
 */

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private View mView;
    Button homeCurrentBtn;
    Button setHomeBtn;
    private String address;
    private String homeLocn;
    EditText locationEditText;
    LinearLayout setHomeValue;
    SharedPreferences.Editor ed;
    TextView bouncingTextMessage;
    EditText homeLocationEditText;
    SharedPreferences sharedPrefs;
    private GoogleMap mGoogleMaps;
    MapHelperClasses mapHelperClasses;
    private LocationManager locationManager;
    private LocationListener locationListener;
    HashMap<String, Double> latLong = new HashMap<>();
    AnimationClass animaClass = new AnimationClass();
    private String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.mMapIntoFrag_inner)
    MapView mMapView;

    public MapFragment() {}  //always leave fragment constructor empty


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.map_fragment, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initializeViews();
        inefficientCode();
        //==   try to fix later
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());


        mGoogleMaps = googleMap;
        mGoogleMaps.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        mGoogleMaps.setBuildingsEnabled(true);
        mGoogleMaps.setIndoorEnabled(true);

        locationListenerAndManager();

        mGoogleMaps.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                setUpMapLocation(latLng.latitude, latLng.longitude, 5);
            }
        });

        mGoogleMaps.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                setUpMapLocation(latLng.latitude, latLng.longitude, 3);
            }
        });

    }

    private void initializeViews() {
        mapHelperClasses = new MapHelperClasses();

        if (mMapView != null)
        {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
        //== try to fix later
        bouncingTextMessage = (TextView) getActivity().findViewById(R.id.lb_mainmap_bounceback);
        homeCurrentBtn = (Button) getActivity().findViewById(R.id.btn_map_shuffle);
        locationEditText = (EditText) getActivity().findViewById(R.id.et_map_address);
        setHomeValue = (LinearLayout) getActivity().findViewById(R.id.setHomeValue);
        setHomeBtn = (Button) getActivity().findViewById(R.id.btn_map_homeloc);
        homeLocationEditText = (EditText) getActivity().findViewById(R.id.et_map_homeloc);
        sharedPrefs = getActivity().getPreferences(Context.MODE_PRIVATE);

        if(!sharedPrefs.contains(HOMEBASEVALUE)){
            //== animatino
            animaClass.doBounceAnimation(setHomeValue, "translationY", +600, 0);
            ed = sharedPrefs.edit();
        }
        else
        {
            setHomeValue.setTranslationY(600f);
            Toast.makeText(getActivity(), sharedPrefs.getString(HOMEBASEVALUE, "No string available"), Toast.LENGTH_SHORT).show();
        }

    }

    private void locationListenerAndManager() {
        //== start

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latLong.put("latitude", location.getLatitude());
                latLong.put("longitude", location.getLongitude());
                setUpMapLocation(latLong.get("latitude"), latLong.get("longitude"), 2);

            }
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {}
            @Override
            public void onProviderEnabled(String provider) {}
            @Override
            public void onProviderDisabled(String provider) {}

        };

        // If device is less than SDK < 23
        if (Build.VERSION.SDK_INT < 23)
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
        else
        {
            if(ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                //we don't have permission
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            else
            {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                setUpMapLocation(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude(), 2);
            }
        }
        //== end
    }

    private void setUpMapLocation(Double latitude, Double longitude, int locationType) {
        // 1 - home / base
        // 2. - current location
        // 3. - incomplete walk
        // 4. - complete walk
        MarkerOptions markerOptionsHome;
        MarkerOptions markerOptionsCurrent;
        MarkerOptions markerOptionsIncomplete;
        MarkerOptions markerOptionsComplete;
        CameraPosition Liberty;

//        mGoogleMaps.clear();
//        mapHelperClasses.purpleRedYellowGreen(mGoogleMaps, latitude, longitude);

        switch (locationType) {
            case 1:  // home/base

                mapHelperClasses.purpleRedYellowGreen(mGoogleMaps, latitude, longitude);
                markerOptionsHome = new MarkerOptions()
                        .position(new LatLng(latitude, longitude))
                        .title(getActivity().getString(R.string.user_home_locn))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_home));
                mGoogleMaps = mapHelperClasses.userMarker(mGoogleMaps, getActivity(), latitude, longitude , 2, markerOptionsHome);
                Liberty = CameraPosition.builder().target(new LatLng(latitude, longitude)).zoom(17).bearing(0).tilt(85).build();

            break;
            case 2:  // current location

                markerOptionsCurrent = new MarkerOptions()
                        .position(new LatLng(latitude, longitude))
                        .title(getActivity().getString(R.string.user_locn))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_profile));


                mGoogleMaps = mapHelperClasses.userMarker(mGoogleMaps, getActivity(), latitude, longitude , 2, markerOptionsCurrent);
                Liberty = CameraPosition.builder().target(new LatLng(latitude, longitude)).zoom(17).bearing(0).tilt(85).build();

            break;
            case 3:  // incomplete walk
                markerOptionsIncomplete = new MarkerOptions()
                        .position(new LatLng(latitude, longitude))
                        .title(getActivity().getString(R.string.incomplete_locn))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_incomplete_walk));
                mGoogleMaps = mapHelperClasses.userMarker(mGoogleMaps, getActivity(), latitude, longitude , 2, markerOptionsIncomplete);
                Liberty = CameraPosition.builder().target(new LatLng(latitude, longitude)).zoom(19).bearing(0).tilt(85).build();
            break;
            case 4:  // complete walk
                markerOptionsComplete = new MarkerOptions()
                        .position(new LatLng(latitude, longitude))
                        .title(getActivity().getString(R.string.complete_locn))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_complete_walk));
                mGoogleMaps = mapHelperClasses.userMarker(mGoogleMaps, getActivity(), latitude, longitude , 2, markerOptionsComplete);
                Liberty = CameraPosition.builder().target(new LatLng(latitude, longitude)).zoom(17).bearing(0).tilt(85).build();
            break;
            case 5:  // only zoom
                address = mapHelperClasses.getGeoLocation(getActivity(), latitude, longitude);
                bouncingTextMessage.setText(address);
                Liberty = CameraPosition.builder().target(new LatLng(latitude, longitude)).zoom(19).bearing(0).tilt(85).build();

            break;
            default:
                bouncingTextMessage.setText(getResources().getString(R.string.map_msg));
                Liberty = CameraPosition.builder().target(new LatLng(latitude, longitude)).zoom(17).bearing(0).tilt(85).build();
            break;
        }


        mGoogleMaps.moveCamera(CameraUpdateFactory.newCameraPosition(Liberty));
    }

    public void inefficientCode() {
        //==improve later


        homeCurrentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                String buttonState = (String) homeCurrentBtn.getTag();


                if(buttonState == "1") // home
                {
                    Toast.makeText(getActivity(), "Home / Base", Toast.LENGTH_SHORT).show();
                    homeCurrentBtn.setText("Current");
                    homeCurrentBtn.setTag("2");
                }
                else if(buttonState == "2") // current
                {
                    Toast.makeText(getActivity(), "Current", Toast.LENGTH_SHORT).show();
                    locationListenerAndManager();
                    homeCurrentBtn.setText("Home / Base");
                    homeCurrentBtn.setTag("1");
                }
                else
                {
                    Toast.makeText(getActivity(), "Home / Base", Toast.LENGTH_SHORT).show();
                    homeCurrentBtn.setText("Current");
                    homeCurrentBtn.setTag("2");
                }

            }
        });

        setHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeLocn = homeLocationEditText.getText().toString();

                if(homeLocn.length() > 0)
                {
                    GeocodingLocation locationAddress = new GeocodingLocation();
                    locationAddress.getAddressFromLocation(homeLocn,
                            getActivity(), new GeocoderHandler());
                    animaClass.doBounceAnimation(setHomeValue, "translationY", 0, +600);
                    //Set some default shared pref
                    ed.putString(HOMEBASEVALUE, "wowsaBowsa");

                    ed.commit();
                    Toast.makeText(getActivity(), "Is not initialized", Toast.LENGTH_SHORT).show();
                }

            }
        });

        locationEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() > 0)
                {
                    String address = locationEditText.getText().toString();
                    GeocodingLocation locationAddress = new GeocodingLocation();
                    locationAddress.getAddressFromLocation(address,
                            getActivity(), new GeocoderHandler());
                }
                else
                {
                    bouncingTextMessage.setText(getResources().getString(R.string.map_msg));
                }
            }
        });

        //==improve later
    }

    private class GeocoderHandler extends Handler {

        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            Double editTextLat, editTextLng;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    editTextLat = bundle.getDouble("latitude");
                    editTextLng = bundle.getDouble("longitude");
                    if(editTextLat != null && editTextLng != null)
                    {
                        Toast.makeText(getActivity(), editTextLat.toString() + " " + editTextLng.toString(), Toast.LENGTH_SHORT).show();
                        setUpMapLocation(editTextLat, editTextLng, 0);

                    }

                    break;
                default:
                    locationAddress = null;
            }
//            bouncingTextMessage.setText(locationAddress);

        }
    }

    //==remove later

}
