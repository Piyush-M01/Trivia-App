package com.example.trivia.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.trivia.controller.AppController;
import com.example.trivia.model.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    // class to store all the questions
    private String url="https://opentdb.com/api.php?amount=10&difficulty=medium&type=boolean";
    ArrayList<Question> questions=new ArrayList<>();

    public List<Question> getQuestions( final AnswerAysncList callBack)
    {
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        //Log.d("category", jsonObject.getString("question"));
                        //Log.d("category", jsonObject.getString("correct_answer"));
                        Question question=new Question(jsonObject.getString("question"),jsonObject.getBoolean("correct_answer"));

                        questions.add(question);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(null != callBack) callBack.processFinished(questions);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    return questions;
    }

}
