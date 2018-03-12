package dunmow.meng.pechhulp;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class InfoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        TextView toolbarText = findViewById(R.id.toolbar_title);
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbarText != null && toolbar != null) {
            toolbarText.setText(getResources().getString(R.string.app_info));
            setSupportActionBar(toolbar);
        }

        ToolbarHelper helper = new ToolbarHelper();
        Button btnBack = helper.setup(this, 1);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}
