package programming.kg.myfriendchemistry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FailureNote extends AppCompatActivity {

    private Query query=null;
    private TextView mc=null;
    private TextView sq=null;
    private int MC_NUM;
    private int SQ_NUM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failure_note);
        query=new Query(this);
        mc=(TextView)findViewById(R.id.textView2);
        sq=(TextView)findViewById(R.id.textView3);
        MC_NUM=query.countWQMC();//현재 오답목록에 있는 객관식 문제 개수
        SQ_NUM=query.countWQSQ();//현재 오답목록에 있는 주관식 문제 개수
        mc.setText("객관식 "+String.valueOf(MC_NUM)+"문제");
        sq.setText("주관식 "+String.valueOf(SQ_NUM)+"문제");
    }

    /**
     * 시작 버튼을 눌렀을 때
     * @param v
     */
    public void Start(View v)
    {
        if(MC_NUM==0&&SQ_NUM==0)
        {
            Toast.makeText(getApplicationContext(),"더 이상 풀 문제가 없습니다! 틀려오세요?!",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(FailureNote.this, FailureNote2.class);
        intent.putExtra("MC_NUM", MC_NUM);
        intent.putExtra("SQ_NUM", SQ_NUM);
        startActivity(intent);
        this.finish();
    }
}
