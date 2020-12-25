package com.example.laboratory_work_2;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class ArchiveActivity extends AppCompatActivity {
    String showUrl = "http://10.96.250.88/showArchiveCoupleWords.php";
    RequestQueue requestQueue;
    Button btnShow;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);

        btnShow = (Button) findViewById(R.id.btnShow);
        result = (TextView) findViewById(R.id.result);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, showUrl, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("VOLLEY", String.valueOf(response));
                        try {
                            JSONArray students = response.getJSONArray("couplewords");
                            for (int i = 0; i < students.length(); i++) {
                                JSONObject student = students.getJSONObject(i);
                                String word  = student.getString("word");
                                String translation  = student.getString("translation");
                                String passed  = student.getString("passed");

                                result.append(word+" "+translation+" "+passed+"\n");
                            }
                            result.append("===\n");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}