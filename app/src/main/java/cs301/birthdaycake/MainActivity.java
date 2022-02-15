package cs301.birthdaycake;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        CakeView cakeView = findViewById(R.id.cakeview);
        CakeController cakeController = new CakeController(cakeView);

        Button blowOut = findViewById(R.id.blowOut);
        blowOut.setOnClickListener(cakeController);

        Switch candleSwitch = findViewById(R.id.candleSwitch);
        candleSwitch.setOnCheckedChangeListener(cakeController);

        SeekBar numCandles = findViewById(R.id.numCandles);
        numCandles.setOnSeekBarChangeListener(cakeController);

        cakeView.setOnTouchListener(cakeController);

        Button ohNo = findViewById(R.id.ohNo);
        View.OnTouchListener epilepsyTouchListener = new CrazyTouchListener(cakeView);
        ohNo.setOnTouchListener(epilepsyTouchListener);
    }

    public void goodbye(View button) {
        finishAffinity();
    }
}
