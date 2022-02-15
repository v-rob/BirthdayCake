package cs301.birthdaycake;

import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class CakeController implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener, View.OnTouchListener {
    private CakeView cakeView;
    private CakeModel cakeModel;

    public CakeController(CakeView cakeView) {
        this.cakeView = cakeView;
        this.cakeModel = cakeView.getCakeModel();
    }

    @Override
    public void onClick(View view) {
        this.cakeModel.candlesLit = false;
        this.cakeView.invalidate();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        this.cakeModel.hasCandles = b;
        this.cakeView.invalidate();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        this.cakeModel.numCandles = i;
        this.cakeView.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) { /* Unused */ }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) { /* Unused */ }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        cakeModel.touchX = motionEvent.getX();
        cakeModel.touchY = motionEvent.getY();
        cakeView.invalidate();
        return false;
    }
}
