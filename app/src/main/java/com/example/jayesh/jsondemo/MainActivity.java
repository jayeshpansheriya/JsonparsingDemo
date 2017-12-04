package com.example.jayesh.jsondemo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public static String DATA_URL="http://api.androidhive.info/contacts/";
    ListView lv1;
    ProgressDialog pd;

    public static String TAG_ID="id";
    public static String TAG_NAME="name";
    public static String TAG_EMAIL="email";
    public static String TAG_ADDRESS="address";
    public static String TAG_GENDER="gender";
    public static String TAG_MOBILE="mobile";
    public static String TAG_HOME="home";
    public static String TAG_OFFICE="office";

    ArrayList<HashMap<String,String>> arrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv1=(ListView)findViewById(R.id.list);
        new MyTask().execute();
    }

    class MyTask extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPreExecute() {
            pd=new ProgressDialog(MainActivity.this);
            pd.setMessage("Loading");
            pd.setTitle("Connecting");
            pd.show();super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Parser parser=new Parser();
            String result=parser.getDataJson(DATA_URL);
            Log.e("hiii",result);

            try {
                JSONObject object=new JSONObject(result);
                JSONArray array=object.getJSONArray("contacts");
                for (int i=0;i<array.length();i++)
                {
                    JSONObject object1=array.getJSONObject(i);
                    String id=object1.getString("id");
                    String name=object1.getString("name");
                    String email=object1.getString("email");
                    String address=object1.getString("address");
                    String gender=object1.getString("gender");

                    JSONObject object2=object1.getJSONObject("phone");
                    String mobile=object2.getString("mobile");
                    String home=object2.getString("home");
                    String office=object2.getString("office");

                    HashMap map=new HashMap();
                    map.put(TAG_ID,id);
                    map.put(TAG_NAME,name);
                    map.put(TAG_EMAIL,email);
                    map.put(TAG_ADDRESS,address);
                    map.put(TAG_GENDER,gender);
                    map.put(TAG_MOBILE,mobile);

                    arrayList.add(map);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pd.dismiss();
            ListAdapter adapter;
            adapter=new SimpleAdapter(MainActivity.this,arrayList,R.layout.layout,new String[]{TAG_ID,TAG_NAME,TAG_EMAIL,TAG_ADDRESS,TAG_GENDER,TAG_MOBILE},new int[]{R.id.textView8,R.id.textView9,R.id.textView10,R.id.textView11,R.id.textView12,R.id.textView13});
           lv1.setAdapter(adapter);
        }
    }

}
