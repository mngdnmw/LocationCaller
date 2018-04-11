package dunmow.meng.pechhulp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    //Member variables
    private GoogleMap mMap;
    private Location mCurrentLocation;
    private Button mBtnRing;
    private FusedLocationProviderClient mFusedLocationClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getCurrentLocation();

        //Sets up ringing button
        mBtnRing = findViewById(R.id.btnRing);
        mBtnRing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(MapsActivity.this);
            }
        });
    }

    /**
     * Checks permission and tries to get current location if received. Sets mCurrentLocation from the result.
     */
    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // GPS location can be null if GPS is switched off
                        if (location != null) {
                            mCurrentLocation = location;
                            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
                            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                                    .findFragmentById(R.id.map);
                            mapFragment.getMapAsync(MapsActivity.this);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                    }
                });
    }


    /**
     * Sets a marker to the location of the person if there is one.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //Place current location marker
        final LatLng currentLocationLatLng = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        BitmapDescriptor markerPopup = BitmapDescriptorFactory.fromResource(R.drawable.map_marker);

        markerOptions.position(currentLocationLatLng).icon(markerPopup);
        Marker currentLocationMarker = mMap.addMarker(markerOptions);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocationLatLng, 13));

        // Setting a custom info window adapter for the google map
        mMap.setInfoWindowAdapter(new InfoWindowAdapter() {

            // Use default InfoWindow frame
            @Override
            public View getInfoWindow(Marker marker) {

                View popupView = getLayoutInflater().inflate(R.layout.location_window_popup, null);
                TextView address = popupView.findViewById(R.id.txtVwAddress);
                String addressString = currentLocationLatLng.latitude + " , " + currentLocationLatLng.longitude;

                //Setting marker popup to display the address, if none is found will set it to gps location
                if (getAddressFromLatLng(currentLocationLatLng) != null) {
                    addressString = getAddressFromLatLng(currentLocationLatLng);
                }
                address.setText(addressString);

                // Returning the view containing InfoWindow contents
                return popupView;

            }

            // Defines the contents of the InfoWindow
            @Override
            public View getInfoContents(Marker arg0) {
                return null;
            }

        });
        currentLocationMarker.showInfoWindow();


    }

    /**
     * Method to get nearest address using gps location given.
     *
     * @param latLng
     * @return string of the address.
     */
    public String getAddressFromLatLng(LatLng latLng) {

        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

        try {
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);

            if (addresses != null && !addresses.isEmpty()) {
                return (addresses.get(0).getAddressLine(0) + ", " +
                        addresses.get(0).getLocality() + ", " +
                        addresses.get(0).getAdminArea() + ", " +
                        addresses.get(0).getCountryName() + ", " +
                        addresses.get(0).getPostalCode());
            } else {
                return null;
            }
        } catch (IllegalArgumentException iae) {
            Log.e("GC IllegalArgument: ", iae.getMessage());
        } catch (IOException ioe) {
            Log.e("GC IOException: ", ioe.getMessage());
        }
        return null;
    }

    /**
     * Method for confirming call popup dialogue.
     */
    private void showPopup(final Activity context) {

        //Hide the call button
        mBtnRing.setVisibility(View.GONE);

        // Inflate the popup_layout.xml
        LinearLayout linLayViewGroup = context.findViewById(R.id.linLayPopup);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutVw = layoutInflater.inflate(R.layout.call_confirm_window, linLayViewGroup);

        // Creating the Popup Window
        final PopupWindow confirmCallPopup = new PopupWindow(context);
        confirmCallPopup.setContentView(layoutVw);
        confirmCallPopup.setFocusable(true);
        confirmCallPopup.setHeight(650);
        confirmCallPopup.setWidth(750);

        // Clear the default translucent background
        confirmCallPopup.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        confirmCallPopup.showAtLocation(layoutVw, Gravity.BOTTOM, 0, 30);

        // Getting a reference to Close button, and close the popup when clicked.
        Button btnCancel = layoutVw.findViewById(R.id.btnCancel);
        ImageView imgVwCancel = layoutVw.findViewById(R.id.imgVwCancel);
        imgVwCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                confirmCallPopup.dismiss();
                mBtnRing.setVisibility(View.VISIBLE);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                confirmCallPopup.dismiss();
                mBtnRing.setVisibility(View.VISIBLE);
            }
        });

        Button bntCallConfirm = layoutVw.findViewById(R.id.btnRingConfirm);
        bntCallConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "+319007788990"));
                startActivity(intent);
                confirmCallPopup.dismiss();
            }
        });
    }
}
