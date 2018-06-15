package coolscan.dvsmart.com.dmdlocation;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import coolscan.dvsmart.com.dmdlocation.alert.AlertBottomMSG;
import coolscan.dvsmart.com.dmdlocation.kostr.Marker;
import coolscan.dvsmart.com.dmdlocation.kostr.MessagesApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback,OnMarkerClickListener {

     private GoogleMap mMap;
     private List<Marker> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.json-generator.com/api/json/get/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        MessagesApi messagesApi = retrofit.create(MessagesApi.class);
        Call<List<Marker>> messages = messagesApi.messages();

        messages.enqueue(new Callback<List<Marker>>() {
            @Override
            public void onResponse(Call<List<Marker>> call, Response<List<Marker>> response) {
                Log.i("response", "onResponse: "+response.body().size());
                list.clear();
                list = response.body();
                for (int i = 0; i < response.body().size(); i++) {
                    LatLng targetLatLng = new LatLng(response.body().get(i).getLat(), response.body().get(i).getLng());
                    MarkerOptions markerOptions =
                            new MarkerOptions().flat(true).position(targetLatLng).title(response.body().get(i).getTitle());
                    mMap.addMarker(markerOptions).setTag(response.body().get(i));
                }
                pDialog.dismiss();

            }

            @Override
            public void onFailure(Call<List<Marker>> call, Throwable t) {
                Log.i("onFailure", "onFailure: "+t);
                pDialog.dismiss();
            }
        });



    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);

    }

    @Override
    public boolean onMarkerClick(com.google.android.gms.maps.model.Marker marker) {

        Marker clickCount = (Marker) marker.getTag();
        Log.i( "onMarkerClick", clickCount.getDescription());


        AlertBottomMSG alertEnterWeight =   new AlertBottomMSG(MapsActivity2.this,clickCount);
        alertEnterWeight.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationBottom;
        alertEnterWeight.show();






        return false;
    }
}
