package com.example.asynctask_mm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Declare variables for the layout components
    private Button button;
    private EditText time;
    private TextView finalResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize our layout components
        time = (EditText) findViewById(R.id.in_time);
        button = (Button) findViewById(R.id.btn_run);
        finalResult = (TextView) findViewById(R.id.tv_result);

        //OnClick listener for the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create AsyncTask
                AsyncTaskRunner runner = new AsyncTaskRunner();

                //Variable for the value entered by the user
                String sleepTime = time.getText().toString();

                //Checks if a value has been entered and shows a message if not
                if(sleepTime.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter a Number", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Run the task
                runner.execute(sleepTime);
            }
        });
    }

    //Class for AsyncTask to process the operation in the background
    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        //Method runs in background in separate from the main UI thread
        @Override
        protected String doInBackground(String... params) {
            //Updates the progress
            publishProgress("Sleeping...");
            try {
                //Converts to milliseconds
                int time = Integer.parseInt(params[0])*1000;

                //Thread sleeps for the time entered
                Thread.sleep(time);
                //Set response message with the time slept in seconds
                resp = "Slept for " + params[0] + " seconds";
            } catch (InterruptedException e) {
                e.printStackTrace();

                //Sets error message if interrupted
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();

                //Sets error message ir there is an exception
                resp = e.getMessage();
            }
            //Returns response to onPostExecute
            return resp;
        }

        //Method runs after doInBackground() with response for parameter
        @Override
        protected void onPostExecute(String result) {
            //Dismisses progress dialog and shows the result in the textview
            progressDialog.dismiss();
            finalResult.setText(result);
        }

        //Method is ran before doInBackground() starts and shows a progress dialog
        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this,
                    "ProgressDialog",
                    "Wait for "+time.getText().toString()+ " seconds");
        }

        //Method sets progress update from doInBackground()
        @Override
        protected void onProgressUpdate(String... text) {
            finalResult.setText(text[0]);

        }
    }
}