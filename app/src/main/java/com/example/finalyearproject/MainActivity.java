package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalyearproject.api_response.SignDataApiResponse;
import com.example.finalyearproject.models.SignData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {


    EditText editText;
    Button transBtn;
//    TextView testtext;
    ImageView imgView;

    String text,mainword;
    int j=0;
    int len;
    String[] words;
    int i=0;
    int k,p;

    Character[] singlechararray;

    Thread t1,t2;

    public static final String BASE_URL = "https://gub.project.mdimran.info/";
    List<SignData> signdataList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        testtext =findViewById(R.id.testtext);

        editText =findViewById(R.id.textView);
        transBtn=findViewById(R.id.transBtn);

        imgView=findViewById(R.id.imgView);


        //check internet connection
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if(isConnected) {
            getAllSignData();
        }
        else{
            finish();
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        //check internet connection



        transBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                text = editText.getText().toString();
                words = text.trim().split(" ");
                if (words.length > 1) {
                    len = words.length - 1;

                    //remove ? mark for processing - start
                    if(words[len].equals("?")) {
                        len = len - 1;
                    }
                    //remove ? mark for processing - end

                    i = 0;
                    //Thread start
                    t1 = new Thread() {
                        @Override
                        public void run() {


                            while (i != len) {


                                try {
                                    Thread.sleep(1000);  //1000ms = 1 sec


                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {

                                            mainword = words[i];

                                            for (j = 0; j < signdataList.size(); j++) {
                                                if (mainword.equals(signdataList.get(j).getKey())) {


                                                    imgView.setVisibility(View.VISIBLE);
                                                    String imglurl = BASE_URL + signdataList.get(j).getSignImages();
                                                    Picasso.get().load(imglurl).placeholder(R.drawable.load).into(imgView);

                                                    break;
                                                } else {

                                                               imgView.setVisibility(View.VISIBLE);
                                                               imgView.setImageResource(R.drawable.sorry);



                                                }
                                            }


                                            i++;


                                        }
                                    });


                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }


                            }


                        }


                    }; //Thread end
                    t1.start();

                }
                else {



                    mainword=words[0];

                    for(j=0; j<signdataList.size(); j++) {
                        if(mainword.equals(signdataList.get(j).getKey())) {

                            imgView.setVisibility(View.VISIBLE);
                            String imglurl = BASE_URL+signdataList.get(j).getSignImages();
                            Picasso.get().load(imglurl).placeholder(R.drawable.load).into(imgView);

                            break;
                        }else{

                            imgView.setVisibility(View.VISIBLE);
                            imgView.setImageResource(R.drawable.sorry);


                        }
                    }




                }
            }

        });









    }//main method




    //recognizer section start
    public void getSpeechInput(View view) {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "bn_IN");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);


//                    editText.setText(result.get(0));
//                    text= editText.getText().toString();
//                    words = text.trim().split(" ");



                    // ? mark add - start
                    text = result.get(0);
                    words = text.trim().split(" ");

                    int flag = 0;
                    for (int k = 0; k < words.length; k++) {
                        String w = words[k];
                        if ( w.equals("কি") || w.equals("কিনা") || w.equals("কিভাবে") || w.equals("কত") || w.equals("কেমন") || w.equals("কোথায়") || w.equals("কই") ) {
                            flag++;
                        }
                    }

                    if(flag>0) {
                        editText.setText(result.get(0)+" ?");
                    }
                    else{
                        editText.setText(result.get(0));
                    }
                    // ? mark add - end



                    if(words.length>1){
                        len=words.length-1;

                        i=0;
                        //Thread start
                        t1=new Thread(){
                            @Override
                            public void run(){



                                while(i!=len){



                                    try {
                                        Thread.sleep(1000);  //1000ms = 1 sec



                                        runOnUiThread(new Runnable() {

                                            @Override
                                            public void run() {

                                                mainword=words[i];

                                                for(j=0; j<signdataList.size(); j++) {
                                                    if(mainword.equals(signdataList.get(j).getKey())) {


                                                        imgView.setVisibility(View.VISIBLE);
                                                        String imglurl = BASE_URL+signdataList.get(j).getSignImages();
                                                        Picasso.get().load(imglurl).placeholder(R.drawable.load).into(imgView);

                                                        break;
                                                    }else{

                                                        imgView.setVisibility(View.VISIBLE);
                                                        imgView.setImageResource(R.drawable.sorry);


                                                    }
                                                }




                                                i++;


                                            }
                                        });



                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }





                                }



                            }


                        }; //Thread end
                        t1.start();

                    }
                    else {


                        mainword=words[0];

                        for(j=0; j<signdataList.size(); j++) {
                            if(mainword.equals(signdataList.get(j).getKey())) {

                                imgView.setVisibility(View.VISIBLE);
                                String imglurl = BASE_URL+signdataList.get(j).getSignImages();
                                Picasso.get().load(imglurl).placeholder(R.drawable.load).into(imgView);

                                break;
                            }else{

                                imgView.setVisibility(View.VISIBLE);
                                imgView.setImageResource(R.drawable.sorry);

                            }
                        }


                    }


                }

                break;
        }
    }

    //recognizer section end

    //sign data api section start
    private void getAllSignData() {
        try {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            SignDataApiResponse apiresponse = retrofit.create(SignDataApiResponse.class);

            apiresponse.getSignData().enqueue(new Callback<List<SignData>>() {
                @Override
                public void onResponse(Call<List<SignData>> call, Response<List<SignData>> response) {

                    if (response.isSuccessful()) {
                        signdataList = response.body();
                    }
                }

                @Override
                public void onFailure(Call<List<SignData>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Message="+t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });


        }catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Error : "+e, Toast.LENGTH_SHORT).show();
        }

    }

    //sign data api section end

    //back button
    public void backAction(View view) {
        onBackPressed();

    }


}
