package cs301.birthdaycake;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;

public class CrazyTouchListener implements View.OnTouchListener {
    CakeView cakeView;

    CrazyTouchListener(CakeView cakeView) {
        this.cakeView = cakeView;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == MotionEvent.ACTION_UP) {
            cakeView.setBackgroundColor(0xFFFFFFFF);
        } else {
            cakeView.setBackgroundColor(Color.rgb(
                    (int) (Math.random() * 256),
                    (int) (Math.random() * 256),
                    (int) (Math.random() * 256)
            ));
        }
        return true;
    }
}
