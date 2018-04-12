package dunmow.meng.pechhulp.controllers;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import dunmow.meng.pechhulp.R;
import dunmow.meng.pechhulp.helpers.ScreenSizeHelper;

public class InfoActivity extends AppCompatActivity {

    private static final int sPADDING_TXTVW_TABLET = 100;
    private static final int sPADDING_TXTVW_MOBILE = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        setupDifferentLayout();
    }

    /**
     * Checks if the screen is more than 600dp.
     *
     * @return boolean
     */
    private boolean isTabletScreen() {
        ScreenSizeHelper screenSizeHelper = new ScreenSizeHelper(this);
        return screenSizeHelper.isTabletScreen();
    }

    /**
     * Set up the different features of both tablet and mobile layouts.
     */
    private void setupDifferentLayout() {

        //Padding of the text
        TextView txtVwInfo = findViewById(R.id.txtViewInfo);

        if (!isTabletScreen()) {
            txtVwInfo.setPadding(sPADDING_TXTVW_MOBILE, sPADDING_TXTVW_MOBILE, sPADDING_TXTVW_MOBILE, sPADDING_TXTVW_MOBILE);

        } else {
            txtVwInfo.setPadding(sPADDING_TXTVW_TABLET, sPADDING_TXTVW_TABLET, sPADDING_TXTVW_TABLET, sPADDING_TXTVW_TABLET);
            LinearLayout linLayInfo = findViewById(R.id.linLayInfo);
            ImageView imgVwLogo = new ImageView(this);
            imgVwLogo.setImageDrawable(getResources().getDrawable(R.drawable.splash_logo_rsr));
            linLayInfo.addView(imgVwLogo);
        }

    }


}
