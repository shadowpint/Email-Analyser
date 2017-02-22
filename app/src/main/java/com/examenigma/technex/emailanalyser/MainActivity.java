package com.examenigma.technex.emailanalyser;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private static Response response;


    static final String MAIN_URL = "http://api.examenigma.in/tokenspost/";


    Button predict;
    EditText name;
    EditText word;
    public String sword;
    ProgressBar progressBar;


    TextView text_From;
    TextView text_To;
    TextView text_ToDate;
    TextView text_FromDate;
    TextView text_HasAttachments;
    TextView text_AttachmentType;
    TextView text_AttachmentSize;
    TextView text_AttachmentName;
    TextView text_Subject;
    TextView text_CC;


    private String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private String responsview;
    private String ressponsview;
    private String jsonString;
    private String resp;

    JSONObject Inputs = new JSONObject();
    private String rs;
    private JSONObject jsonobject;
    private String From;
    private String To;
    private String ToDate;
    private String FromDate;
    private String HasAttachments;
    private String AttachmentType;
    private String AttachmentSize;
    private String AttachmentName;
    private String Subject;
    private String CC;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.name);

        word = (EditText) findViewById(R.id.word);

        predict = (Button) findViewById(R.id.Predict);


        text_From = (TextView)findViewById(R.id.From);
        text_To = (TextView)findViewById(R.id.To);
        text_ToDate = (TextView)findViewById(R.id.ToDate);
        text_FromDate = (TextView)findViewById(R.id.FromDate);
        text_HasAttachments = (TextView)findViewById(R.id.HasAttachments);
        text_AttachmentType = (TextView)findViewById(R.id.AttachmentType);
        text_AttachmentSize = (TextView)findViewById(R.id.AttachmentSize);
        text_AttachmentName = (TextView)findViewById(R.id.AttachmentName);
        text_Subject = (TextView)findViewById(R.id.Subject);
        text_CC = (TextView)findViewById(R.id.CC);




               predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull View view) {
                sword=word.getText().toString();











                try {
                Inputs.put("code", sword);
                }


                catch (JSONException e) {
                    e.printStackTrace();
                }
                    new GetDataTask().execute();



            }
        });



    }

    class GetDataTask extends AsyncTask<Void, Void, String> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /**
             * Progress Dialog for User Interaction
             */
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setTitle("Please wait...");
            dialog.setMessage("Searching");
            dialog.show();
        }

        @Nullable
        @Override
        protected String doInBackground(Void... params) {

            try {
                OkHttpClient client = new OkHttpClient();
                MediaType JSON
                        = MediaType.parse("application/json; charset=utf-8");


                RequestBody body = RequestBody.create(JSON, Inputs.toString());
                Request request = new Request.Builder()
                        .addHeader("Content-Type", "application/json")
                        .url(MAIN_URL)
                        .post(body)
                        .build();
                response = client.newCall(request).execute();

                return response.body().string();
            } catch (@NonNull IOException e) {
                Log.e(TAG, "" + e.getLocalizedMessage());
            }


            return null;
        }

        @Override
        protected void onPostExecute(String Response) {
            super.onPostExecute(Response);
            dialog.dismiss();
            JSONObject json = null;
            try {
                json = new JSONObject(Response);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            try {
                rs=json.getString("code");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                 jsonobject = new JSONObject(rs);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            try {
            From =jsonobject.getString("From");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                To =jsonobject.getString("To");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                ToDate =jsonobject.getString("ToDate");
            } catch (JSONException e) {
                e.printStackTrace();
            } try {
                FromDate =jsonobject.getString("FromDate");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                HasAttachments =jsonobject.getString("HasAttachments");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                AttachmentType=jsonobject.getString("AttachmentType");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                AttachmentSize=jsonobject.getString("AttachmentSize");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                AttachmentName=jsonobject.getString("AttachmentName");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                Subject=jsonobject.getString("Subject");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                CC=jsonobject.getString("CC");
            } catch (JSONException e) {
                e.printStackTrace();
            }


           text_From.setText("From"+" "+From);
            text_To.setText("To"+" "+To);
            text_ToDate.setText("ToDate"+" "+ToDate);
            text_FromDate.setText("FromDate"+" "+FromDate);
            text_HasAttachments.setText("HasAttachments"+" "+HasAttachments);
            text_AttachmentType.setText("AttachmentType"+" "+AttachmentType);
            text_AttachmentSize.setText("AttachmentSize"+" "+AttachmentSize);
            text_AttachmentName.setText("AttachmentName"+" "+AttachmentName);
            text_Subject.setText("Subject"+" "+Subject);
            text_CC.setText("CC"+" "+CC);



        }
    }
}