package dunmow.meng.pechhulp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mBtnRSR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnRSR = findViewById(R.id.btnPechhulp);
        mBtnRSR.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

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
