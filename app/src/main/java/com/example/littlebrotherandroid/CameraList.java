package com.example.littlebrotherandroid;

import android.location.LocationManager;
import android.util.Log;

import com.example.littlebrotherandroid.model.CameraModel;
import com.example.littlebrotherandroid.rest.Rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CameraList {
    public List<CameraModel> bigBro = new ArrayList<>();
    public List<CameraModel> litBro = new ArrayList<>();

    private static CameraList instance = new CameraList();
    private CameraList(){
       refreshBig(null);
       refreshLittle(null);
    }
    public void refreshBig(Action action) {
        Call<Map<String, CameraModel>> callBig = Rest.getInstance().cameraRest.getBig("Bearer " + Auth.getInstance().firebaseKey);

        callBig.enqueue(new Callback<Map<String, CameraModel>>() {
            @Override
            public void onResponse(Call<Map<String, CameraModel>> call, Response<Map<String, CameraModel>> response) {
                Map<String, CameraModel> bigMap = response.body();
                if (bigMap != null) {
                    List<CameraModel> list = new ArrayList<CameraModel>(bigMap.values());
                    if (!list.equals(bigBro)) {
                        bigBro.clear();
                        bigBro.addAll(list);
                        if (action != null)
                            action.action();
                    }
                }
            }

            @Override
            public void onFailure(Call<Map<String, CameraModel>> call, Throwable t) {
                Log.i("bigbro", "error" + t.getMessage());
            }
        });
    }
    public void refreshLittle(Action action){
        Call<Map<String, CameraModel>> callLittle = Rest.getInstance().cameraRest.getLittle("Bearer " + Auth.getInstance().firebaseKey);

        callLittle.enqueue(new Callback<Map<String, CameraModel>>() {
            @Override
            public void onResponse(Call<Map<String, CameraModel>> call, Response<Map<String, CameraModel>> response) {
                Map<String, CameraModel> litMap = response.body();
                if (litMap != null) {
                    List<CameraModel> list = new ArrayList<CameraModel>(litMap.values());
                    if (compareList(litBro, list)){
                        litBro.clear();
                        litBro.addAll(list);
                         if ( action != null) {
                             Log.i("refresh", "ref");
                             action.action();
                         }
                    }
                }
            }
            @Override
            public void onFailure(Call<Map<String, CameraModel>> call, Throwable t) {
            }
        });
    }
    public static CameraList getInstance(){
        return instance;
    }
    public interface Action{
        void action();
    }

    private boolean compareList(List<CameraModel> cam1, List<CameraModel> cam2){
        boolean changed = false;
        if (cam1 == null || cam1.isEmpty())
            return true;
        if (cam2 == null || cam2.isEmpty())
            return true;
        for (CameraModel item1 : cam1){
            boolean exist = false;
            for (CameraModel item2 : cam2){
                if (item1.getId().equals(item2.getId())){
                    exist = true;
                    if (compareItem(item1, item2)){
                        changed = true;
                    }
                }
            }
            if (!exist){
                changed = true;
            }
        }
        return changed;
    }

    private boolean compareItem(CameraModel cam1, CameraModel cam2){
        boolean result = false;
        if (cam1.getAccept().equals(cam2.getAccept())) {
            result = true;
        }
        if (!cam1.getBigBrother().equals(cam2.getBigBrother())){
            result = true;
        }
        if (!cam1.getBigBrotherId().equals(cam2.getBigBrotherId())){
            result = true;
        }
        if (cam1.getLittleBrother().equals(cam2.getLittleBrother())){
            result = true;
        }
        if (cam1.getLittleBrotherId().equals(cam2.getLittleBrotherId())){
            result = true;
        }
        if (cam1.getLatitude().equals(cam2.getLatitude())){
            result = true;
        }
        if (cam1.getLongitude().equals(cam2.getLongitude())){
            result = true;
        }
        return result;
    }
}
