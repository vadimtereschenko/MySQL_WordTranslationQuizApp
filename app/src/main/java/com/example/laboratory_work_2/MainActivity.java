package com.example.laboratory_work_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGoToQuiz = findViewById(R.id.btnGoToQuiz);
        Button btnWorkWithDatabase = findViewById(R.id.btnWorkWithDatabase);
        Button btnGoToArchive = findViewById(R.id.btnGoToArchive);

        btnGoToQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });

        btnWorkWithDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WorkWithDatabaseActivity.class);
                startActivity(intent);
            }
        });

        btnGoToArchive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ArchiveActivity.class);
                startActivity(intent);
            }
        });
    }
}