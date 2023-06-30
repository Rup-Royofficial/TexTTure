package com.example.textture;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class HomeFragment extends Fragment {


    View view;
    ImageView imageView;
    EditText editText;
    ProgressBar progressBar;

    ImageButton imageButton;
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();


    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_home, container, false);
        imageView =   view.findViewById(R.id.imageView);
        editText  = view.findViewById(R.id.editText2);
        progressBar = view.findViewById(R.id.progressBarID);
        imageButton = view.findViewById(R.id.generateBtn);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt = "";
                txt = editText.getText().toString().trim();
                if(txt.isEmpty()){
                    editText.setError("Text can't be empty");
                    return;
                }
                callApi(txt);
            }
        });
       return view;
    }

    public void callApi(String str){

        pictureProgress(true);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("prompt",str);
            jsonObject.put("size","256x256");
        }catch (Exception exception){
            exception.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(jsonObject.toString(),JSON);
        Request request  =  new Request.Builder()
                .url("https://api.openai.com/v1/images/generations")
                .header("Authorization","Bearer OpenAI txt2img API key") //use the key after Bearer
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast.makeText(getActivity().getApplicationContext(), "Failed to gen image", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                Log.i("Response : ",response.body().string());
                try{
                    JSONObject jsonObject1 = new JSONObject(response.body().string());
                    String imgUrl =  jsonObject1.getJSONArray("data").getJSONObject(0).getString("url");
                    imgLoader(imgUrl);
                    pictureProgress(false);

                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        });
    }

    public void imgLoader(String strImgUrl){
        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Picasso.get().load(strImgUrl).into(imageView);
            }
        });


    }

    public void pictureProgress(boolean picProgress){
        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(picProgress){
                    progressBar.setVisibility(View.VISIBLE);
                }else {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

}
