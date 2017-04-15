package utils.location;//package utils.location;
//
//import android.app.AlertDialog;
//import android.app.Service;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.location.Address;
//import android.location.Criteria;
//import android.location.Geocoder;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.os.Bundle;
//import android.os.IBinder;
//import android.provider.Settings;
//import android.util.Log;
//
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GooglePlayServicesUtil;
//import com.vis.saleskit.R;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Locale;
//
//public class GPSTracker extends Service implements LocationListener {
//    private final Context mContext;
//
//    // flag for gps Status
//    boolean isGPSEnabled = false;
//
//    // flag for network status
//    boolean isNetworkEnabled = false;
//
//    boolean canGetLocation = false;
//
//    Location location;
//    double latitude;
//    double longitude;
//    String provider;
//    private LocationChangeListener locationChangeListener;
//
//    // The minimum distance to change updates in metters
//    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 100; // 10
//    // metters
//
//    // The minimum time beetwen updates in milliseconds
//    private static final long MIN_TIME_BW_UPDATES = /*1000 * 60 * 1*/0; // 1 minute
//
//    // Declaring a Location Manager
//    protected LocationManager locationManager;
//
//    public GPSTracker(Context context, LocationChangeListener listener) {
//        this.mContext = context;
//        this.locationChangeListener = listener;
//        getLocation();
//
//
//    }
//
//    public void getLocation() {
//
//        try {
//
//            // Getting Google Play availability status
//            int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(mContext);
//
//            if (status == ConnectionResult.SUCCESS) {
//
//                locationManager = (LocationManager) mContext
//                        .getSystemService(LOCATION_SERVICE);
//
//                Criteria criteria = new Criteria();
//
//                // Getting the name of the best provider
//                provider = locationManager.getBestProvider(criteria, true);
//                if (provider != null && !provider.equals("")
//                        && !provider.equals(" ")) {
//
//                    // Getting Current Location From gps
//                    location = locationManager.getLastKnownLocation(provider);
//                    locationManager.requestLocationUpdates(provider,
//                            MIN_TIME_BW_UPDATES,
//                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
//                    if (location != null && location.getLongitude() != 0.0
//                            && location.getLatitude() != 0.0) {
//
//                        updateGPSCoordinates();
//                        this.locationChangeListener.onLocationSucess();
//                    } else {
//                        this.locationChangeListener.onLocationFailure(mContext
//                                .getString(R.string.enable_gps_network));
//                    }
//
//                } else {
//                    this.locationChangeListener.onLocationFailure(mContext
//                            .getString(R.string.enable_gps_network));
//                }
//
//            } else {
//                this.locationChangeListener.onLocationFailure(mContext
//                        .getString(R.string.google_service_not_available));
//            }
//
//        } catch (Exception e) {
//            // e.printStackTrace();
//            Log.e("Error : Location",
//                    "Impossible to connect to LocationManager", e);
//            this.locationChangeListener.onLocationFailure(mContext
//                    .getString(R.string.google_service_not_available));
//
//        }
//
//    }
//
//    public void updateGPSCoordinates() {
//        if (location != null) {
//            latitude = location.getLatitude();
//            longitude = location.getLongitude();
//        }
//    }
//
//    /**
//     * Stop using gps listener Calling this function will stop using gps in your
//     * app
//     */
//
//    public void stopUsingGPS() {
//        if (locationManager != null) {
//            locationManager.removeUpdates(GPSTracker.this);
//        }
//    }
//
//    /**
//     * Function to get latitude
//     */
//    public double getLatitude() {
//        if (location != null) {
//            latitude = location.getLatitude();
//        }
//
//        return latitude;
//    }
//
//    /**
//     * Function to get longitude
//     */
//    public double getLongitude() {
//        if (location != null) {
//            longitude = location.getLongitude();
//        }
//
//        return longitude;
//    }
//
//    /**
//     * Function to get longitude
//     */
//    public double getAltitude() {
//        if (location != null) {
//            longitude = location.getAltitude();
//        }
//
//        return longitude;
//    }
//
//    /**
//     * Function to check gps/wifi enabled
//     */
//    public boolean canGetLocation() {
//        return this.canGetLocation;
//    }
//
//    /**
//     * Function to show settings alert dialog
//     */
//    public void showSettingsAlert() {
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
//
//        // Setting Dialog Title
//        alertDialog.setTitle(R.string.GPSAlertDialogTitle);
//
//        // Setting Dialog Message
//        alertDialog.setMessage(R.string.GPSAlertDialogMessage);
//
//        // On Pressing Setting button
//        alertDialog.setPositiveButton(R.string.settings,
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent intent = new Intent(
//                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                        mContext.startActivity(intent);
//                    }
//                });
//
//        // On pressing cancel button
//        alertDialog.setNegativeButton("cancel",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//
//        alertDialog.show();
//    }
//
//    /**
//     * Get list of address by latitude and longitude
//     *
//     * @return null or List<Address>
//     */
//    public List<Address> getGeocoderAddress(Context context) {
//        if (location != null) {
//            Geocoder geocoder = new Geocoder(context, Locale.ENGLISH);
//            try {
//                List<Address> addresses = geocoder.getFromLocation(latitude,
//                        longitude, 1);
//                return addresses;
//            } catch (IOException e) {
//                // e.printStackTrace();
//                Log.e("Error : Geocoder", "Impossible to connect to Geocoder",
//                        e);
//            }
//        }
//
//        return null;
//    }
//
//    /**
//     * Try to get AddressLine
//     *
//     * @return null or addressLine
//     */
//    public String getAddressLine(Context context) {
//        List<Address> addresses = getGeocoderAddress(context);
//        if (addresses != null && addresses.size() > 0) {
//            Address address = addresses.get(0);
//            String addressLine = address.getAddressLine(0);
//
//            return addressLine;
//        } else {
//            return null;
//        }
//    }
//
//    /**
//     * Try to get Locality
//     *
//     * @return null or locality
//     */
//    public String getLocality(Context context) {
//        List<Address> addresses = getGeocoderAddress(context);
//        if (addresses != null && addresses.size() > 0) {
//            Address address = addresses.get(0);
//            String locality = address.getLocality();
//
//            return locality;
//        } else {
//            return null;
//        }
//    }
//
//    /**
//     * Try to get Postal Code
//     *
//     * @return null or postalCode
//     */
//    public String getPostalCode(Context context) {
//        List<Address> addresses = getGeocoderAddress(context);
//        if (addresses != null && addresses.size() > 0) {
//            Address address = addresses.get(0);
//            String postalCode = address.getPostalCode();
//
//            return postalCode;
//        } else {
//            return null;
//        }
//    }
//
//    /**
//     * Try to get CountryName
//     *
//     * @return null or postalCode
//     */
//    public String getCountryName(Context context) {
//        List<Address> addresses = getGeocoderAddress(context);
//        if (addresses != null && addresses.size() > 0) {
//            Address address = addresses.get(0);
//            String countryName = address.getCountryName();
//
//            return countryName;
//        } else {
//            return null;
//        }
//    }
//
//    /**
//     * Try to get CityName
//     *
//     * @return null or postalCode
//     */
//    public String getCityName(Context context) {
//        List<Address> addresses = getGeocoderAddress(context);
//        if (addresses != null && addresses.size() > 0) {
//            Address address = addresses.get(0);
//            String countryName = address.getLocality();
//
//            return countryName;
//        } else {
//            return null;
//        }
//    }
//
//    /**
//     * Try to get CountryCode
//     *
//     * @return null or postalCode
//     */
//    public String getCountryCode(Context context) {
//        List<Address> addresses = getGeocoderAddress(context);
//        if (addresses != null && addresses.size() > 0) {
//            Address address = addresses.get(0);
//            String countryName = address.getCountryCode();
//
//            return countryName;
//        } else {
//            return null;
//        }
//    }
//
//    /**
//     * Try to get Address
//     *
//     * @return null or Address
//     */
//    public Address getFullAddress(Context context) {
//        List<Address> addresses = getGeocoderAddress(context);
//
//        if (addresses != null && addresses.size() > 0) {
//            Address address = addresses.get(0);
//
//            return address;
//        } else {
//            return null;
//        }
//    }
//
//    public Address geocodeAddress(String addressInput) {
//        Address address = null;
//        Geocoder geocoder = new Geocoder(getApplicationContext(),
//                Locale.getDefault());
//        List<Address> addresses = null;
//        try {
//            addresses = geocoder.getFromLocationName(addressInput, 1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if (addresses != null && addresses.size() > 0) {
//            address = addresses.get(0);
//
//        }
//        return address;
//    }
//
//    @Override
//    public void onLocationChanged(Location location) {
//
//        this.location = location;
//        updateGPSCoordinates();
//        this.locationChangeListener.onLocationChange();
//
//    }
//
//    @Override
//    public void onProviderDisabled(String provider) {
//        this.locationChangeListener.onLocationFailure(getString(R.string.enable_gps_network));
//    }
//
//    @Override
//    public void onProviderEnabled(String provider) {
//        this.locationChangeListener.onProviderEnable();
//    }
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//    }
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//}
