package nah.prayer.nahtrans;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import nah.prayer.translib.NahButton;
import nah.prayer.translib.NahImageView;
import nah.prayer.translib.NahLayout;
import nah.prayer.translib.TransTouchListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NahImageView iv = findViewById(R.id.iv);
        NahButton btn = findViewById(R.id.btn);
        NahLayout layout = findViewById(R.id.layout);

        iv.setOnTouchListerer(new TransTouchListener() {
            @Override
            public void onTouch() {
                Toast.makeText(MainActivity.this, "iv click", Toast.LENGTH_SHORT).show();
            }
        });

        btn.setOnTouchListerer(new TransTouchListener() {
            @Override
            public void onTouch() {
                Toast.makeText(MainActivity.this, "btn click", Toast.LENGTH_SHORT).show();
            }
        });

        layout.setOnTouchListerer(new TransTouchListener() {
            @Override
            public void onTouch() {
                Toast.makeText(MainActivity.this, "layout click", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
