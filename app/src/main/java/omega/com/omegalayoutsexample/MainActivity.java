package omega.com.omegalayoutsexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_percents).setOnClickListener(this);
        findViewById(R.id.button_shadows).setOnClickListener(this);
        findViewById(R.id.button_auto_orientation).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_percents:
                startActivity(PercentsExampleActivity.createIntent(this));
                break;
            case R.id.button_shadows:
                startActivity(ShadowsExampleActivity.createIntent(this));
                break;
            case R.id.button_auto_orientation:
                startActivity(AutoOrientationExampleActivity.createIntent(this));
                break;
        }
    }
}
