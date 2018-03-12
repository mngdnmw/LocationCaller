package dunmow.meng.pechhulp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * Created by meng on 12/03/2018.
 */

public class ToolbarHelper {

    public Button setup(final Activity context, int type) {
        int tintColor = ContextCompat.getColor(context, R.color.colorAccent);
        Button button = new Button(context);
        Drawable drawable;

        //Type 1 has a back button and type 2 has an info button
        if (type == 1) {
            LinearLayout linBack = context.findViewById(R.id.linBack);
            drawable = DrawableCompat.wrap(ContextCompat.getDrawable(context, R.drawable.menu_arrow));
            DrawableCompat.setTint(drawable.mutate(), tintColor);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            button.setText(context.getResources().getString(R.string.back));
            button.setTextSize(18);
            button.setCompoundDrawables(drawable, null, null, null);
            button.setTextColor(context.getResources().getColor(R.color.colorAccent));
            button.setAllCaps(false);
            button.setBackgroundColor(Color.parseColor("#00FF0000"));
            linBack.addView(button);
        } else {
            LinearLayout linInfo = context.findViewById(R.id.linInfo);
//            drawable = DrawableCompat.wrap(ContextCompat.getDrawable(context, R.drawable.main_i));
//            DrawableCompat.setTint(drawable.mutate(), tintColor);
//            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//            button.setCompoundDrawables(drawable, null, null, null);
            drawable = context.getResources().getDrawable(R.drawable.main_i);
            drawable.setColorFilter(new
                    PorterDuffColorFilter(tintColor, PorterDuff.Mode.MULTIPLY));
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Drawable d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 50, 50, true));
            button.setBackground(d);
            button.setLayoutParams(new LinearLayout.LayoutParams(80, 80));
            linInfo.addView(button);

        }


        return button;

    }
}
