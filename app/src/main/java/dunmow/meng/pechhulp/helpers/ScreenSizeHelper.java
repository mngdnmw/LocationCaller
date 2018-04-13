package dunmow.meng.pechhulp.helpers;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

public class ScreenSizeHelper {
    private boolean mTabletScreen;
    private int mScreenSizeDp;

    public ScreenSizeHelper(Context context) {

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        mScreenSizeDp = Math.round(dpWidth);
        mTabletScreen = (dpWidth >= 600);

    }

    public boolean isTabletScreen() {
        return mTabletScreen;
    }

    public int getScreenSizeDp() {
        return mScreenSizeDp;
    }
}
