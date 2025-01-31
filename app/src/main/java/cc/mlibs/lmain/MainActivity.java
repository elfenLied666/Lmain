package cc.mlibs.lmain;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import cc.mlibs.l.L;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        L.i("Hello World!!!");
        L.toastL(this, "Hello World!!!!");
        testTime();
    }

    private void testTime() {
        Integer keyTime = L.startMeasureTime();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Integer keyTime2 = L.startMeasureTime();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                L.endMeasureTimeLog(keyTime2);
            }
        }).start();
        L.toastL(this, "Start measuring");
        L.toastSh(this, "Time measuring " + L.endMeasureTime(keyTime));

    }
}