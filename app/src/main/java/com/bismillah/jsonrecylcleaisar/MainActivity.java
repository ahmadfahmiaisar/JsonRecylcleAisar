package com.bismillah.jsonrecylcleaisar;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    private String url = "https://script.googleusercontent.com/macros/echo?user_content_key=DoSfeiW5v6S6RE4jDCbtF7Rm7pfV0lFGHGrMeVSsGJI8BjRZ_xkc5N-86uN07lYpjoJtIglGp6BsL6g7ByHBN4-I9yqrbeQzOJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMWojr9NvTBuB6lHT6qnqYcmFWggwoSVQQXTsQ1HqKa1CgDXQROm1OeNR5ibYVAaRxAeOtzLmbRZcVjrce7Uveb8iU1s-L39A_CLDTUaq6azCNVhRMhi1rsPEMUK-CH6pys1RvMr_dgaGkoVsMt9XllB7kFByHUCzY&lib=M-tpZm-Fm1QX9Yr80nZn_p-WXe3zpGnIr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.list);
        rv.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);

        final ProgressDialog pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("wkwkwk ....");
        pd.show();

        OkHttpClient okhttp = new OkHttpClient();
        final Request minta = new Request.Builder()
                                        .url(url)
                                        .build();
        okhttp.newCall(minta).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "gagal wkwk", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final ArrayList<MixData> itemData = new ArrayList<>();
                String dataJson = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(dataJson);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String valuesatu = object.get("instansi").toString();
                        String valuedua = object.get("alamat").toString();
                        String valuetiga = object.get("jam").toString();
                        String valueempat = object.get("rencana_donor").toString();

                        MixData dataobject = new MixData(valuesatu,valuedua,valuetiga,valueempat);
                        itemData.add(dataobject);
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pd.dismiss();
                        AdapterData adapterData = new AdapterData(getApplicationContext(), itemData);
                        rv.setAdapter(adapterData);
                    }
                });
            }
        });
    }
}
