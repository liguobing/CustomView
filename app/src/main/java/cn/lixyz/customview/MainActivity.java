package cn.lixyz.customview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {

    private MyCustomView customView;
    private Button bt;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        customView = (MyCustomView) findViewById(R.id.custom_view);
        bt = (Button) findViewById(R.id.bt);
        et = (EditText) findViewById(R.id.et);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customView.setSocre(Integer.valueOf(et.getText().toString()));
            }
        });

    }
}

