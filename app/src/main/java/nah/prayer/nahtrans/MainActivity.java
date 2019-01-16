package nah.prayer.nahtrans;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import nah.prayer.translib.NahButton;
import nah.prayer.translib.NahImageButton;
import nah.prayer.translib.NahImageView;
import nah.prayer.translib.NahLayout;
import nah.prayer.translib.NahTextView;
import nah.prayer.translib.TransTouchListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NahImageView iv = findViewById(R.id.iv);
        NahImageButton iv_btn = findViewById(R.id.iv_btn);
        NahButton btn = findViewById(R.id.btn);
        NahLayout layout = findViewById(R.id.layout);
        NahTextView tv = findViewById(R.id.tv);

        iv.setOnTouchListerer(new TransTouchListener() {
            @Override
            public void onTouch() {
                Toast.makeText(MainActivity.this, "iv click", Toast.LENGTH_SHORT).show();
            }
        });

        iv_btn.setOnTouchListerer(new TransTouchListener() {
            @Override
            public void onTouch() {
                Toast.makeText(MainActivity.this, "iv_btn click", Toast.LENGTH_SHORT).show();
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

        tv.setOnTouchListerer(new TransTouchListener() {
            @Override
            public void onTouch() {
                Toast.makeText(MainActivity.this, "tv click", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
