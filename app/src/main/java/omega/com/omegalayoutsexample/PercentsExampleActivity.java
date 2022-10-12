package omega.com.omegalayoutsexample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class PercentsExampleActivity extends AppCompatActivity {

    public static Intent createIntent(Context context) {
        return new Intent(context, PercentsExampleActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percents_example);
    }
}
