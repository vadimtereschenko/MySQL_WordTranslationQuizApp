package com.example.laboratory_work_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WorkWithDatabaseActivity extends AppCompatActivity {
    String insertUrl = "http://10.96.250.88/insertCoupleWords.php";
    String showUrl = "http://10.96.250.88/showCoupleWords.php";
    String updateUrl = "http://10.96.250.88/updateCoupleWords.php";
    EditText etWord, etTranslation;
    Button btnAdd, btnShow;
    TextView result;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_with_database);

        etWord = (EditText) findViewById(R.id.etWord);
        etTranslation = (EditText) findViewById(R.id.etTranslation);
        btnAdd = (Button) findViewById(R.id.btnAdd);
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
                        JSONArray jsonWords = null;
                        JSONObject jsonObject = null;
                        CoupleWords couple_words_obj = new CoupleWords();
                        ArrayList<CoupleWords> wordsArrayList = new ArrayList<CoupleWords>();
                        try {
                            JSONArray students = response.getJSONArray("couplewords");
                            for (int i = 0; i < students.length(); i++) {
                                JSONObject student = students.getJSONObject(i);
                                int id = student.getInt("id");
                                String word  = student.getString("word");
                                String translation  = student.getString("translation");
                                boolean passed = "1".equals(student.getString("passed").trim());
                                couple_words_obj = new CoupleWords(id, word, translation, passed);
                                result.append(word+" "+translation+" "+passed+"\n");
                                wordsArrayList.add(couple_words_obj);
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

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> parameters = new HashMap<String, String>();
                        parameters.put("word", etWord.getText().toString());
                        parameters.put("translation", etTranslation.getText().toString());
                        parameters.put("passed", "0");
                        return parameters;
                    }
                };
                requestQueue.add(request);
            }
        });
    }
}