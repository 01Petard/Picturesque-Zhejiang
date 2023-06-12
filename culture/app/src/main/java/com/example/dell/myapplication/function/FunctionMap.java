package com.example.dell.myapplication.function;

import android.content.Context;
import android.graphics.Color;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.example.dell.myapplication.BaseFragment;
import com.example.dell.myapplication.R;


public class FunctionMap extends BaseFragment implements   AMap.OnMarkerClickListener,LocationSource, AMapLocationListener, AMap.OnMapTouchListener{

    View view;
    private AMap aMap;
    private MapView mapView;
    private int GPS_REQUEST_CODE = 1;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    // private RadioGroup mGPSModeGroup;
    private RelativeLayout tab_back_button;
    MyLocationStyle myLocationStyle;
    OnLocationChangedListener mListeners;
    private boolean canMove = true;//变量控制拖动不返回中心点

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_function_map, container, false);

        mapView = (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        //openGPSSEtting();
        aMap.setLocationSource(this);
        myLocationStyle = new MyLocationStyle();
        // myLocationStyle.interval(2000);
        myLocationStyle.myLocationIcon(null);
        //不显示Marker外的圆圈
        myLocationStyle.strokeColor(Color.argb(00, 255, 255, 255));
        myLocationStyle.radiusFillColor(Color.argb(00, 255, 255, 255));
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE);
        aMap.getUiSettings().setMyLocationButtonEnabled(false);
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
        aMap.setOnMapTouchListener(this);
        //     aMap.setMyLocationEnabled(true);
        mapView.getMap().moveCamera(CameraUpdateFactory.zoomTo(14));
        aMap.setOnMarkerClickListener(this);
        LatLng latLng3 = new LatLng(29.87109, 121.539599);//构造一个位置
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng3,15));
        LatLng latLng = new LatLng(29.87109, 121.539599);
        MarkerOptions markerOption = new MarkerOptions();
        markerOption.title("天一阁").snippet("宁波市海曙区天一街10号 ");
        markerOption.draggable(true);//设置Marker可拖动
        markerOption.position(latLng);
        markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.timg));
        //设置覆盖物比例
        markerOption.anchor(0.5f, 0.5f);
        Marker marker = mapView.getMap().addMarker(markerOption);
        LatLng latLng1 = new LatLng(29.875317, 121.54591);
        MarkerOptions markerOption1 = new MarkerOptions();
        markerOption1.title("鼓楼").snippet("宁波市海曙区公园路2号 ");
        markerOption1.draggable(true);//设置Marker可拖动
        markerOption1.position(latLng1);
        markerOption1.icon(BitmapDescriptorFactory.fromResource(R.drawable.gl1));
        //设置覆盖物比例
        markerOption1.anchor(0.5f, 0.5f);
        Marker marker1 = mapView.getMap().addMarker(markerOption1);
        marker1.showInfoWindow();
        marker.showInfoWindow();
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListeners != null && aMapLocation != null) {
            if (aMapLocation != null
                    && aMapLocation.getErrorCode() == 0) {
                if (canMove) {
                    mListeners.onLocationChanged(aMapLocation);
                    Log.d("activateactivate", aMapLocation.getPoiName());
                }
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.e("activateactivate", errText);
            }
        }
    }
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        canMove = true;
        mListeners = onLocationChangedListener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(view.getContext());
            mLocationOption = new AMapLocationClientOption();
            mlocationClient.setLocationListener(this);
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            mlocationClient.setLocationOption(mLocationOption);
            mlocationClient.stopLocation();
            mlocationClient.startLocation();
        }
    }
    @Override
    public void deactivate() {
        mListeners = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }
    @Override
    public void onTouch(MotionEvent motionEvent) {
        if (canMove) {
            //用户拖动地图后，不再跟随移动，直到用户点击定位按钮
            canMove = false;
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker){
        if(aMap !=null)
        {
            marker.showInfoWindow();
            return true;
        }
        return false;
    }

}
