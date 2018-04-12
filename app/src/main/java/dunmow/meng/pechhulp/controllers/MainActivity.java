package dunmow.meng.pechhulp.controllers;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import dunmow.meng.pechhulp.R;
import dunmow.meng.pechhulp.helpers.ScreenSizeHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupCommonLayout();
    }

    private boolean isTabletScreen() {
        ScreenSizeHelper screenSizeHelper = new ScreenSizeHelper(this);
        return screenSizeHelper.isTabletScreen();
    }


    private void setupCommonLayout() {

        //Setting up of the button that goes to the map activity
        Button btnGoToMap = new Button(this);
        btnGoToMap.setBackground(getResources().getDrawable(R.drawable.btn_general));
        btnGoToMap.setText(getResources().getString(R.string.activity_maps));
        Drawable btnDrawableWarning = getResources().getDrawable(R.drawable.main_btn_warning);
        LinearLayout relaLayBtnGrp = findViewById(R.id.linLayBtnGrp);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        if (!isTabletScreen()) {
            setupPhoneLayout(btnGoToMap, btnDrawableWarning, relaLayBtnGrp, layoutParams);
        } else {
            setupTabletLayout(btnGoToMap, btnDrawableWarning, relaLayBtnGrp, layoutParams);
        }

    }

    private void setupTabletLayout(Button btnGoToMap, Drawable drawableIcon, LinearLayout linLayBtnGrp, LinearLayout.LayoutParams linParams) {

        //Adding more padding to the linear layout that holds the two buttons
        FrameLayout.LayoutParams fraLayParams = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        fraLayParams.setMargins(400,0, 400, 50);
        linLayBtnGrp.setLayoutParams(fraLayParams);

        //Changing params for button that goes to map activity
        drawableIcon.setBounds(0, 0, 60, 60);
        btnGoToMap.setCompoundDrawables(drawableIcon, null, null, null);
        btnGoToMap.setPadding(100, 0, 100, 0);
        btnGoToMap.setTextSize(15);
        linParams.setMargins(0,0,0,20);
        linLayBtnGrp.addView(btnGoToMap, linParams);

        //Setting up of the button that goes to the information activity
        Button btnGoToInfo = new Button(this);
        btnGoToInfo.setBackground(getResources().getDrawable(R.drawable.btn_general));
        btnGoToInfo.setText(getResources().getString(R.string.activity_info));
        Drawable btnDrawableInfo = getResources().getDrawable(R.drawable.main_btn_info);
        btnDrawableInfo.setBounds(0, 0, 60, 60);
        btnGoToInfo.setCompoundDrawables(btnDrawableInfo, null, null, null);
        btnGoToInfo.setPadding(100, 0, 100, 0);
        btnGoToInfo.setTextSize(15);
        linLayBtnGrp.addView(btnGoToInfo);

    }

    private void setupPhoneLayout(Button btnGoToMap, Drawable drawableIcon, LinearLayout relaLayBtnGrp, LinearLayout.LayoutParams linParams) {
        //Changing params for button that goes to map activity
        drawableIcon.setBounds(0, 0, 60, 60);
        btnGoToMap.setCompoundDrawables(drawableIcon, null, null, null);
        btnGoToMap.setPadding(100, 0, 100, 0);
        btnGoToMap.setTextSize(15);
        relaLayBtnGrp.addView(btnGoToMap, linParams);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();
        switch (menuId) {
            case R.id.menuItem_info:
                openInfo();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openInfo() {
        Intent addFriendIntent = new Intent(this, InfoActivity.class);
        startActivityForResult(addFriendIntent, 1);
    }


}
