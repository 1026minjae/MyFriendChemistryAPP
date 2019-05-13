package programming.kg.myfriendchemistry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    /**
     * 시작 버튼을 눌렀을 때
     * @param v
     */
    public void Start(View v)
    {
        Intent intent=new Intent(Test.this, Test2.class);
        startActivity(intent);
        this.finish();
    }
}
