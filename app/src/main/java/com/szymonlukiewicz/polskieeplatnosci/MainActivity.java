package com.szymonlukiewicz.polskieeplatnosci;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements StationListAdapter.StationClickListener {


    Messenger sendMessenger = null;
    Messenger replyMessenger = null;

    ArrayList<Station> stations;
    Station chosenStation;
    boolean bound;

    RecyclerView recyclerView;
    ProgressBar progressBar;

    StationListAdapter adapter;

    private static String ACTION_GET_LIST = "get_station_list";
    private static String ACTION_GET_STATION_DATA = "get_station_data";


    Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message message) {
            Bundle bundle = message.getData();
            try {
                if(bundle.getString("executedAction").equals(ACTION_GET_LIST)){
                    JSONArray jsonArray = new JSONArray(bundle.getString("response"));
                    stations = new ArrayList<>();
                    for(int i = 0; i < jsonArray.length();i++){
                        stations.add(new Station(jsonArray.getJSONObject(i)));
                    }
                    adapter.loadData(stations);
                    hideProgressbar();
                }else{
                    JSONObject airPollutionJSONObject = new JSONObject(message.getData().getString("response"));
                    chosenStation.setIndexLevels(airPollutionJSONObject);
                    adapter.loadData(stations);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {

            sendMessenger = new Messenger(service);
            bound = true;
            sendMessage();
        }

        public void onServiceDisconnected(ComponentName className) {
            sendMessenger = null;
            bound = false;
        }
    };

    public void sendMessage() {
        if (!bound) return;
        Message msg = Message.obtain(handler);
        Bundle bundle = new Bundle();
        bundle.putString("action", ACTION_GET_LIST);
        msg.setData(bundle);
        msg.replyTo = replyMessenger;
        try {
            sendMessenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    private void hideProgressbar(){
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        adapter = new StationListAdapter(this, this);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        replyMessenger = new Messenger(handler);

        Intent intent = new Intent("air_data");
        intent.setPackage("com.szymonlukiewicz.polskieepatnociserver");
        bindService(intent, mConnection,
                Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (bound) {
            unbindService(mConnection);
            bound = false;
        }
    }

    @Override
    public void onStationItemClick(int position) {

        if(chosenStation!= null){
            chosenStation.isExpanded = false;
        }

        chosenStation = stations.get(position);
        chosenStation.isExpanded = true;

        if (!bound) return;
        Message message = Message.obtain(handler);
        Bundle bundle = new Bundle();
        bundle.putString("action", ACTION_GET_STATION_DATA);
        bundle.putInt("stationID", chosenStation.id);
        message.setData(bundle);
        message.replyTo = replyMessenger;
        try {
            sendMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}