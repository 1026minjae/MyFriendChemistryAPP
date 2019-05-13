package programming.kg.myfriendchemistry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

public class School extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);

        findViewById(R.id.S1).setOnClickListener(onClickListener);
        findViewById(R.id.S2).setOnClickListener(onClickListener);
        findViewById(R.id.S3).setOnClickListener(onClickListener);
        findViewById(R.id.S4).setOnClickListener(onClickListener);
        findViewById(R.id.S5).setOnClickListener(onClickListener);
        findViewById(R.id.S6).setOnClickListener(onClickListener);
        findViewById(R.id.S7).setOnClickListener(onClickListener);
        findViewById(R.id.S8).setOnClickListener(onClickListener);
        findViewById(R.id.S9).setOnClickListener(onClickListener);
        findViewById(R.id.S10).setOnClickListener(onClickListener);
        findViewById(R.id.S11).setOnClickListener(onClickListener);
        findViewById(R.id.S12).setOnClickListener(onClickListener);
        findViewById(R.id.S13).setOnClickListener(onClickListener);
        findViewById(R.id.S14).setOnClickListener(onClickListener);
        findViewById(R.id.S15).setOnClickListener(onClickListener);
        findViewById(R.id.S16).setOnClickListener(onClickListener);
        findViewById(R.id.S17).setOnClickListener(onClickListener);
    }

    /**
     * 버튼이 클릭될 경우 그 버튼이 몇 번째 주제인지를 감지하여 그 주제의 번호를 다음 액티비티에 전달한다.
     */
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(School.this, School2.class);
            intent.putExtra("NO",Integer.valueOf(v.getTag().toString()));
            startActivity(intent);
        }
    };
}
