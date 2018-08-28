package cm.supptic.lidms;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cm.supptic.lidms.adapters.ErrorAdapter;
import cm.supptic.lidms.adapters.ListAdapter;
import cm.supptic.lidms.data.GetRequestManager;
import cm.supptic.lidms.data.PostRequestManager;
import cm.supptic.lidms.objects.Demand;

public class MainActivity extends AppCompatActivity {
    private IntentIntegrator qrScan;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        qrScan = new IntentIntegrator(this);
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        if(IDStore.getIds(this)==IDStore.DEFAULT_VALUE){
            rv.setAdapter(new ErrorAdapter());
        }else{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final String resp  = GetRequestManager.getResponse("http://192.168.137.83/lidms/api/demand-data/?dta="+IDStore.getIds(getApplicationContext()));
//                    Log.i("LiDMS",resp);
//                    Log.i("LiDMS",IDStore.getIds(getApplicationContext()));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            List<Demand> demandList = new ArrayList<>();
                            String[] splitt = resp.split("====");
                            for (int i=0;i<splitt.length;i++) {
                                String sp = splitt[i];
                                Log.i("LiDMS",sp+" is sp");
                                if (sp != ""){
                                    String[] splitted = sp.split("12oiuwtweo");

                                    /*this.name = name;
        this.percentage = percentage;
        this.title = title;
        this.day = day;
        this.due = due;
        this.status = status;*/
                                    Log.i("LiDMS",splitted[5]);
                                    Log.i("LiDMS",splitted[2]);
                                    Demand demand =  new Demand(splitted[0],splitted[7],splitted[1],Integer.parseInt(splitted[5]),splitted[6],Integer.parseInt(splitted[2]),splitted[9]);
                                    demandList.add(demand);
                                    rv.setAdapter(new ListAdapter(demandList,MainActivity.this));

                                }
                            }
//                            Toast.makeText(MainActivity.this, resp, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).start();

            SwipeRefreshLayout  swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.sr);
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {

                }
            });
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                String contents = result.getContents();
                IDStore.addId(contents,this);
                Toast.makeText(this, contents, Toast.LENGTH_LONG).show();
                if(IDStore.getIds(this)==IDStore.DEFAULT_VALUE){
                    rv.setAdapter(new ErrorAdapter());
                }else{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            final String resp  = GetRequestManager.getResponse("http://192.168.137.83/lidms/api/demand-data/?dta="+IDStore.getIds(getApplicationContext()));
//                            Log.i("LiDMS",resp);
//                            Log.i("LiDMS",IDStore.getIds(getApplicationContext()));
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    List<Demand> demandList = new ArrayList<>();
                                    String[] splitt = resp.split("====");
                                    for (int i=0;i<splitt.length;i++) {
                                        String sp = splitt[i];
                                        Log.i("LiDMS",sp+" is sp");
                                        if (sp != ""){
                                            String[] splitted = sp.split("12oiuwtweo");

                                    /*this.name = name;
        this.percentage = percentage;
        this.title = title;
        this.day = day;
        this.due = due;
        this.status = status;*/
                                            Log.i("LiDMS",splitted[5]);
                                            Log.i("LiDMS",splitted[2]);
                                            Demand demand =  new Demand(splitted[0],splitted[7],splitted[1],Integer.parseInt(splitted[5]),splitted[6],Integer.parseInt(splitted[2]),splitted[9]);
                                            demandList.add(demand);
                                            rv.setAdapter(new ListAdapter(demandList,MainActivity.this));

                                        }
                                    }
//                                    Toast.makeText(MainActivity.this, resp, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).start();

                    SwipeRefreshLayout  swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.sr);
                    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {

                        }
                    });
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    public void qrScan(View view){
        qrScan.initiateScan();
    }
}
