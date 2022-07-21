package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.trivia.controller.AppController;
import com.example.trivia.data.AnswerAysncList;
import com.example.trivia.data.Repository;
import com.example.trivia.databinding.ActivityMainBinding;
import com.example.trivia.model.Question;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    int pos=0;
    int score=0;


    public void shakeAnimation()
    {
        Animation shakeit= AnimationUtils.loadAnimation(MainActivity.this,R.anim.shake);
        binding.questionDisplay.setAnimation(shakeit);
    }

    public void displayQuestions()
    {
        ArrayList questions= (ArrayList) new Repository().getQuestions(new AnswerAysncList() {
            @Override
            public void processFinished(ArrayList<Question> questionArrayList) {
                binding.textViewQuestionDisp.setText(String.valueOf(questionArrayList.get(0).getAnswer()));
                binding.buttonTrue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (questionArrayList.get(pos).isAnswerTrue()) {
                            Snackbar.make(v, "Correct", Snackbar.LENGTH_LONG).show();
                            score = score + 1;
                        } else {
                            Snackbar.make(v, "Incorrect", Snackbar.LENGTH_LONG).show();
                            shakeAnimation();
                        }
                        try {
                            if (pos < 10) {
                                pos = pos + 1;
                                if(pos+1<=10)
                                binding.questionTextView.setText("Question:" + (pos + 1) + "/10");
                                binding.textViewQuestionDisp.setText(String.valueOf(questionArrayList.get(pos).getAnswer()));
                            }
                        }
                        catch (Exception e)
                        {
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    // TODO: Your application init goes here.
                                    Intent intent = new Intent(MainActivity.this, Score.class);
                                    intent.putExtra("Score",Integer.toString(score));
                                    startActivity(intent);
                                }
                            }, 1000);

                        }

                    }
                });
                binding.button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (questionArrayList.get(pos).isAnswerTrue() == false) {
                            Snackbar.make(v, "Correct", Snackbar.LENGTH_LONG).show();
                            score = score + 1;
                        } else {
                            Snackbar.make(v, "Incorrect", Snackbar.LENGTH_LONG).show();
                            shakeAnimation();
                        }
                        try {
                            if (pos < 10) {
                                pos = pos + 1;
                                if(pos+1<=10)
                                binding.questionTextView.setText("Question:" + (pos + 1) + "/10");
                                binding.textViewQuestionDisp.setText(String.valueOf(questionArrayList.get(pos).getAnswer()));
                            }
                        }
                        catch (Exception e)
                        {
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    // TODO: Your application init goes here.
                                    Intent intent = new Intent(MainActivity.this, Score.class);
                                    intent.putExtra("Score",Integer.toString(score));
                                    startActivity(intent);
                                }
                            }, 1000);
                        }

                        }
                });
                binding.buttonNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            if (pos < 10) {
                                pos = pos + 1;
                                if(pos+1<=10)
                                binding.questionTextView.setText("Question:" + (pos + 1) + "/10");
                                binding.textViewQuestionDisp.setText(String.valueOf(questionArrayList.get(pos).getAnswer()));
                            }
                        }
                        catch (Exception e)
                        {
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    // TODO: Your application init goes here.
                                    Intent intent = new Intent(MainActivity.this, Score.class);
                                    intent.putExtra("Score",Integer.toString(score));
                                    startActivity(intent);
                                }
                            }, 1000);
                        }
                    }
                });
            }});
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        displayQuestions();

    }
}
