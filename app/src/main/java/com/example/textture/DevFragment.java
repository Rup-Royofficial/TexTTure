package com.example.textture;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tomer.fadingtextview.FadingTextView;


public class DevFragment extends Fragment {


    public DevFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_dev, container, false);
        String[] texts = {"CSE undergrad","Android dev enthusiast"};
        FadingTextView FTV = (FadingTextView) view.findViewById(R.id.textViewWork);
        FTV.setTexts(texts); //You can use an array resource or a string array as the parameter
        FTV.setTimeout(0.15,FadingTextView.MINUTES);

        ImageView connectTwitch = view.findViewById(R.id.conectTwitch);
        ImageView connectLinkedIn = view.findViewById(R.id.connectLinkedIn);

        connectTwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.twitch.tv/the_forgotten_guy");
            }
        });

        connectLinkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.linkedin.com/in/rupayan-roy-7a787b226/");
            }
        });

        return view;
    }

    private void gotoUrl(String str) {
        Uri uri = Uri.parse(str);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}