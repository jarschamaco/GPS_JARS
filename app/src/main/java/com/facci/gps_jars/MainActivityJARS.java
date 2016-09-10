package com.facci.gps_jars;

import  android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.List;

public class MainActivityJARS extends AppCompatActivity {

    LocationManager locManager;
    private Double latitud;
    private Double longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_jars);
        //inicializar el locationManager
        locManager=(LocationManager)getSystemService(LOCATION_SERVICE);
        //Lista proceedores
        List<String> ListaProvedors = locManager.getAllProviders();
        //Obtener el primer proveedor
        LocationProvider provider = locManager.getProvider(ListaProvedors.get(0));

    }
    public void ActualizarCoordenadas(View v){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){

        }
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2*60*100,10,locationListener);
    }
    private final LocationListener locationListener= new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            longitud = location.getLongitude();
            latitud=location.getLatitude();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    EditText txtLongitud=(EditText)findViewById(R.id.txtLongitud);
                    EditText txtLatitud=(EditText)findViewById(R.id.txtLatitud);
                    txtLatitud.setText(longitud+"");
                    txtLongitud.setText(String.valueOf(latitud));


                }
            });

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };
}
