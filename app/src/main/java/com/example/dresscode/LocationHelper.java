package com.example.dresscode;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import java.io.IOException;
import java.util.List;

public class LocationHelper {

    private Context context;
    private FusedLocationProviderClient fusedLocationClient;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    public LocationHelper(Context context) {
        this.context = context;
        this.fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
    }

    // Request location permissions
    public void requestLocationPermission() {
        // Check if fine location permission is granted
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request only fine location
            ActivityCompat.requestPermissions((Home) context,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION
                    },
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // Permission already granted, fetch location
            fetchLocation();
        }
    }

    // Fetch the current location and return the city and state
    public void fetchLocation() {
        // Check for fine location permission
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        // Reverse geocode to get city and state
                        Geocoder geocoder = new Geocoder(context);
                        try {
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            if (addresses != null && !addresses.isEmpty()) {
                                Address address = addresses.get(0);
                                String city = address.getLocality();
                                String state = address.getAdminArea();

                                updateLocationText(city + ", " + state);
                                Toast.makeText(context, "Location Update!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Unable to fetch location", Toast.LENGTH_SHORT).show();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "Unable to fetch location", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "Unable to fetch location", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    // Get fine GPS location data directly
    public void getFineLocationData(FineLocationCallback callback) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        callback.onLocationRetrieved(latitude, longitude);
                    } else {
                        callback.onLocationError("Unable to fetch location");
                    }
                }
            }).addOnFailureListener(e -> callback.onLocationError(e.getMessage()));
        } else {
            callback.onLocationError("Location permission not granted");
        }
    }

    // Callback interface for fine location data
    public interface FineLocationCallback {
        void onLocationRetrieved(double latitude, double longitude);
        void onLocationError(String errorMessage);
    }

    // Update the location text in the activity (Home or Weather)
    private void updateLocationText(String location) {
        if (context instanceof LocationUpdateListener) {
            LocationUpdateListener listener = (LocationUpdateListener) context;
            listener.updateLocationText(location);
        }
    }

    public interface LocationUpdateListener {
        void updateLocationText(String location);
    }
}
