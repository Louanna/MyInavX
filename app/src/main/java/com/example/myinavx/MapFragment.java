package com.example.myinavx;

import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private MapView mapView;
    private GoogleMap map;

    private static final int REQUEST_PERMISSION_CODE = 10000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        mapView = (MapView) v.findViewById(R.id.mapFrag);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
        return v;
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.setOnMapLongClickListener(this);
        map.getUiSettings().setAllGesturesEnabled(true);

        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
        MapsInitializer.initialize(this.getActivity());

        LocationManager service = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = service.getBestProvider(criteria, false);

        if (ActivityCompat.checkSelfPermission(this.getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            String[] permissions = new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION};

            requestPermissions(permissions, REQUEST_PERMISSION_CODE);
            return;
        } else {
            //IF we can't get location, use SLC as the default
            LatLng userLocation;
            Location location = service.getLastKnownLocation(provider);
            if (location != null) {
                map.setMyLocationEnabled(true);
                userLocation = new LatLng(location.getLatitude(), location.getLongitude());
            } else {
                userLocation = new LatLng(40.76, -111.89);
            }

            // Updates the location and zoom of the MapView
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(userLocation, 12);
            map.animateCamera(cameraUpdate);
        }
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
//        map.clear();
        map.addMarker(new MarkerOptions()
                .position(latLng)
                .draggable(true));
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

}