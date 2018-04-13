package dunmow.meng.pechhulp.controllers;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import dunmow.meng.pechhulp.R;
import dunmow.meng.pechhulp.helpers.ScreenSizeHelper;

public class SplashActivity extends AppCompatActivity {

    private static int sSPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //Set up layout differently if is a tablet
        ScreenSizeHelper screenSizeHelper = new ScreenSizeHelper(this);
        if (screenSizeHelper.isTabletScreen()) {
            setupTabletLayout();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, sSPLASH_TIME_OUT);

    }

    /**
     * Set up the specific features for tablet layouts.
     */
    private void setupTabletLayout() {
        ImageView imgVwSml = findViewById(R.id.logoSml);
        LinearLayout.LayoutParams linLayParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linLayParams.setMargins(0,0,0,200);
        imgVwSml.setLayoutParams(linLayParams);

    }
}
