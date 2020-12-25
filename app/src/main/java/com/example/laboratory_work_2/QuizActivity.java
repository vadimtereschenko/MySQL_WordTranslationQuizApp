package com.example.laboratory_work_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizActivity extends AppCompatActivity {
    String showUrl = "http://10.96.250.88/showCoupleWords.php";
    String updateUrl = "http://10.96.250.88/updateCoupleWords.php";
    public ArrayList<CoupleWords> wordsArrayList = new ArrayList<CoupleWords>();
    Button btnTakeWords, btnCloseTest, firstButton, secondButton, thirthButton;
    TextView currentQuizWord;
    RequestQueue requestQueue;
    int buttonWin = 0;
    List<CoupleWords> quizList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        btnTakeWords = (Button) findViewById(R.id.btnTakeWords);
        btnCloseTest = (Button) findViewById(R.id.btnCloseTest);
        currentQuizWord = (TextView)findViewById(R.id.currentQuizWord);
        firstButton = (Button)findViewById(R.id.firstButton);
        secondButton = (Button)findViewById(R.id.secondButton);
        thirthButton = (Button)findViewById(R.id.thirthButton);
        firstButton.setOnClickListener(buttonsListener);
        secondButton.setOnClickListener(buttonsListener);
        thirthButton.setOnClickListener(buttonsListener);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        btnTakeWords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, showUrl, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("VOLLEY", String.valueOf(response));
                        JSONArray jsonWords = null;
                        JSONObject jsonObject = null;
                        CoupleWords couple_words_obj = new CoupleWords();
                        try {
                            JSONArray students = response.getJSONArray("couplewords");
                            for (int i = 0; i < students.length(); i++) {
                                JSONObject student = students.getJSONObject(i);
                                int id = student.getInt("id");
                                String word  = student.getString("word");
                                String translation  = student.getString("translation");
                                boolean passed = "1".equals(student.getString("passed").trim());
                                couple_words_obj = new CoupleWords(id, word, translation, passed);
                                wordsArrayList.add(couple_words_obj);
                            }
                            Log.i(null, "Words is taked");
                            startQuiz();
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

        btnCloseTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(QuizActivity.this, wordsArrayList.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    View.OnClickListener buttonsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast toast;
            String checkMessage = "-";
            Button b = (Button)v;
            switch (b.getId()) {
                case R.id.firstButton:
                    if (buttonWin == 1) {
                        checkMessage = "+";
                        StringRequest request = new StringRequest(Request.Method.POST, updateUrl, new Response.Listener<String>() {
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
                                parameters.put("id", Integer.toString(wordsArrayList.get(buttonWin-1).getWordID()));
                                parameters.put("word", wordsArrayList.get(buttonWin-1).getWord());
                                parameters.put("translation", wordsArrayList.get(buttonWin-1).getTranslation());
                                parameters.put("passed", "1");
                                return parameters;
                            }
                        };
                        requestQueue.add(request);
                    }
                    break;
                case R.id.secondButton:
                    if (buttonWin == 2) {
                        checkMessage = "+";
                        StringRequest request = new StringRequest(Request.Method.POST, updateUrl, new Response.Listener<String>() {
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
                                parameters.put("id", Integer.toString(wordsArrayList.get(buttonWin-1).getWordID()));
                                parameters.put("word", wordsArrayList.get(buttonWin-1).getWord());
                                parameters.put("translation", wordsArrayList.get(buttonWin-1).getTranslation());
                                parameters.put("passed", "1");
                                return parameters;
                            }
                        };
                        requestQueue.add(request);
                    }
                    break;
                case R.id.thirthButton:
                    if (buttonWin == 3) {
                        checkMessage = "+";
                        StringRequest request = new StringRequest(Request.Method.POST, updateUrl, new Response.Listener<String>() {
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
                                parameters.put("id", Integer.toString(wordsArrayList.get(buttonWin-1).getWordID()));
                                parameters.put("word", wordsArrayList.get(buttonWin-1).getWord());
                                parameters.put("translation", wordsArrayList.get(buttonWin-1).getTranslation());
                                parameters.put("passed", "1");
                                return parameters;
                            }
                        };
                        requestQueue.add(request);
                    }
                    break;
            }
            toast = Toast.makeText(QuizActivity.this, checkMessage, Toast.LENGTH_SHORT);
            toast.show();
            if(wordsArrayList.size() > 3) {
                startQuiz();
            } else {
                toast = Toast.makeText(QuizActivity.this, "Конец", Toast.LENGTH_SHORT);
                toast.show();
                firstButton.setEnabled(false);
                secondButton.setEnabled(false);
                thirthButton.setEnabled(false);
            }
        }
    };

    public void startQuiz() {
        Collections.shuffle(wordsArrayList);
        quizList = wordsArrayList.subList(0, 3);
        int buttonRandom = (int)(Math.random() * 3);
        currentQuizWord.setText(wordsArrayList.get(buttonRandom).getWord());
        buttonWin = buttonRandom + 1;
        firstButton.setText(quizList.get(0).getTranslation());
        secondButton.setText(quizList.get(1).getTranslation());
        thirthButton.setText(quizList.get(2).getTranslation());
    }
}