package com.example.textture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.textture.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {




    ActivityMainBinding binding;
    private static final String appUrl = "https://play.google.com/store/apps/details/?id=";
    private static final String smsMsg = "Introducing the perfect app for those who are too lazy to use their own imagination!\n" +
                                            "\nOur app uses OpenAI to generate images from your plain old text." +
                                            " Because why bother picturing things in your mind when you can just let a robot do it for you?\n" +
                                            "\nShare this app with your friends, because let's be honest, who needs creativity when you have technology?" +
                                            "\nHere's the link = https://play.google.com/store/apps/details/?id=com.example.textture";

    private static final String shareMsg = "Imagine the possibilities - you can write a boring grocery list and have it transformed into a vibrant image of fruits and vegetables. Or type out a mundane to-do list and watch it come to life in stunning visual detail. Don't bother using your own imagination, let us do the heavy lifting for you.\n" +
            "\nAnd now, with our new implicit intent feature, you can easily share this app with your friends and family. Because let's be real, who doesn't want to live in a world where technology does all the work for us?\n" +
            "\nSo go ahead, share this app and let the robots take over. After all, who needs creativity when you have OpenAI?";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setBackground(null);

        FloatingActionButton bottomSheetbtn = findViewById(R.id.fab);
        bottomSheetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog1();
            }
        });

        AtomicInteger itemId = new AtomicInteger(R.id.homeItem);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.homeItem:
                    if(itemId.get() != R.id.homeItem){
                        itemId.set(R.id.homeItem);
                        replaceFragment(new HomeFragment());
                        bottomSheetbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                showDialog1();
                            }
                        });
                        break;
                    }else {

                        bottomSheetbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                showDialog1();
                            }
                        });
                        break;
                    }


                case R.id.devItem:
                    if(itemId.get()!=R.id.devItem){
                        itemId.set(R.id.devItem);
                        replaceFragment(new DevFragment());
                        bottomSheetbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                showDialog2();
                            }
                        });
                        break;
                    }else{

                        bottomSheetbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                showDialog2();
                            }
                        });
                        break;
                    }

            }
            return true;
        });



    }



    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left,R.anim.enter_left_to_right,R.anim.exit_left_to_right);
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }

    private void showDialog1() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheet_layout);


        LinearLayout smsLayout = dialog.findViewById(R.id.secondLinearBS);
        LinearLayout shareLayout = dialog.findViewById(R.id.thirdLinearBS);

        ImageView smsImg = dialog.findViewById(R.id.shareTwitter);
        TextView smsTxt = dialog.findViewById(R.id.twitterBottomSheet);
        smsImg.setImageResource(R.drawable.baseline_sms_24);
        smsTxt.setText("Share this app via SMS");

        ImageView shareVia = dialog.findViewById(R.id.shareWp);
        TextView shareViaTxt = dialog.findViewById(R.id.wpBottomSheet);
        shareVia.setImageResource(R.drawable.baseline_share_24);
        shareViaTxt.setText("Share via Social Media");


        ImageView downSliderBtnBS = dialog.findViewById(R.id.sliderBtn_bottomSheet);
        downSliderBtnBS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        smsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:"));
                intent.putExtra("sms_body",smsMsg);
                startActivity(intent);
            }
        });

        shareLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Wp share", Toast.LENGTH_SHORT).show();
//                final Intent imgTxtIntent = new Intent(Intent.ACTION_SEND);
//                imgTxtIntent.setType("image/jpg");
//                final File photoFile = new File(getFilesDir(), "splashscreen.jpg");
//                imgTxtIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(photoFile));
//                startActivity(Intent.createChooser(imgTxtIntent,"Share img using"));
//                String _ImageFile = "android.resource://" + getResources().
//                        getResourceName(R.drawable.splashscreenbg).replace(":", "/");
                Uri imageUri = Uri.parse("android.resources://com.example.textture/" + R.drawable.splashscreenbg);
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT,shareMsg);
                startActivity(Intent.createChooser(shareIntent,"Share using"));
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }

    private void showDialog2() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheet_layout);


        LinearLayout smsLayout = dialog.findViewById(R.id.secondLinearBS);
        LinearLayout rateLayout = dialog.findViewById(R.id.thirdLinearBS);

        ImageView downSliderBtnBS = dialog.findViewById(R.id.sliderBtn_bottomSheet);
        ImageView rateAppImg = dialog.findViewById(R.id.shareWp);
        TextView rateTxt = dialog.findViewById(R.id.wpBottomSheet);


        rateAppImg.setImageResource(R.drawable.baseline_star_rate_24);
        rateTxt.setText("Rate this app");

        smsLayout.removeAllViews();

        downSliderBtnBS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        rateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //eta playstore a kholar jonno
                    Intent rateIntent = new Intent(Intent.ACTION_VIEW);
                    rateIntent.setData(Uri.parse(appUrl + getPackageName()));//package name  = application id
                    rateIntent.setPackage("com.android.textture");
                    startActivity(rateIntent);
                }catch (ActivityNotFoundException exception){//eta playstore na thakle emni web a kholar jonno
                    Intent rateIntent = new Intent(Intent.ACTION_VIEW);
                    rateIntent.setData(Uri.parse(appUrl + getPackageName()));//package name  = application id
                    startActivity(rateIntent);
                }
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }
}