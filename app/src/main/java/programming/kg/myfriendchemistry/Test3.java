package programming.kg.myfriendchemistry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Test3 extends AppCompatActivity {

    private TYPE.QMC[] qmc = new TYPE.QMC[20];//객관식 문제 배열
    private TYPE.QSQ[] qsq = new TYPE.QSQ[5];//주관식 문제 배열
    private String[] choices = new String[25];//사용자가 선택한 답 배열
    private Query query = null;
    private int wqmc_num;
    private int wqsq_num;
    private int mcscore=0;
    private int sqscore=0;
    private TextView correctans=null;
    private TextView userans=null;
    private LinearLayout score1=null;
    private LinearLayout score2=null;
    private TextView mc_score=null;
    private TextView sq_score=null;
    private TextView finalscore=null;
    private int pg=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);

        query=new Query(this);
        wqmc_num=query.countWQMC();
        wqsq_num=query.countWQSQ();
        correctans=(TextView) findViewById(R.id.correctans);
        userans=(TextView) findViewById(R.id.userans);
        score1=(LinearLayout) findViewById(R.id.score1);
        score2=(LinearLayout) findViewById(R.id.score2);
        mc_score=(TextView) findViewById(R.id.mcscore);
        sq_score=(TextView) findViewById(R.id.sqscore);
        finalscore=(TextView) findViewById(R.id.finalscore);

        Intent intent = getIntent();
        ArrayList<TYPE.QMC> qmcList = (ArrayList<TYPE.QMC>)intent.getSerializableExtra("QMC");
        for (int i=0; i<qmcList.size(); i++) {
            TYPE.QMC q = qmcList.get(i);
            Log.d("Test3",q.question);
            qmc[i] = q;
        }//이전 액티비티에서 전달된 객관식 문제 배열로 현재 객관식 문제 배열을 초기화한다.

        ArrayList<TYPE.QSQ> qsqList = (ArrayList<TYPE.QSQ>)intent.getSerializableExtra("QSQ");
        for (int i=0; i<qsqList.size(); i++) {
            TYPE.QSQ q = qsqList.get(i);
            Log.d("Test3",q.question);
            qsq[i] = q;
        }//이전 액티비티에서 전달된 주관식 문제 배열로 현재 주관식 문제 배열을 초기화한다.

        ArrayList<String> choicesList = intent.getStringArrayListExtra("CHOICES");
        for (int i=0; i<choicesList.size(); i++) {
            String s = choicesList.get(i);
            if(s==null) s="";
            Log.d("Test3",s);
            choices[i] = s;
        }//이전 액티비티에서 전달된 사용자 답 배열로 현재 사용자 답 배열을 초기화한다.

        mcscore=MC_Score();//객관식 채점
        correctans.setText(qsq[0].answer);
        userans.setText(choices[20]);
    }

    private int MC_Score()
    {
        int count=0;
        int score=0;
        for(int i=0;i<20;i++)
        {
            if(!(qmc[i].answer.equals(choices[i])))//사용자가 쓴 답이 정답이 아닐 경우
            {
                count++;
                query.InsertQMC(wqmc_num+count, qmc[i].question, qmc[i].a1, qmc[i].a2, qmc[i].a3, qmc[i].a4, qmc[i].a5, qmc[i].answer);
            }
            else score+=4;
        }
        return score;
    }

    /**
     * 정답 버튼을 눌렀을 때
     * @param v
     */
    public void Correct_Click(View v)
    {
        sqscore+=4;
        pg++;
        if(pg>4)
        {
            score1.setVisibility(View.GONE);
            score2.setVisibility(View.VISIBLE);
            mc_score.setText(String.valueOf(mcscore)+"점");
            sq_score.setText(String.valueOf(sqscore)+"점");
            finalscore.setText(String.valueOf(mcscore+sqscore)+"점");
            return;
        }
        correctans.setText(qsq[pg].answer);
        userans.setText(choices[20+pg]);
    }

    private int count=0;

    /**
     * 오답 버튼을 눌렀을 때
     * @param v
     */
    public void Wrong_Click(View v)
    {
        count++;
        query.InsertQSQ(wqsq_num+count, qsq[pg].question, qsq[pg].answer);
        pg++;
        if(pg>4)
        {
            score1.setVisibility(View.GONE);
            score2.setVisibility(View.VISIBLE);
            mc_score.setText(String.valueOf(mcscore)+"점");
            sq_score.setText(String.valueOf(sqscore)+"점");
            finalscore.setText(String.valueOf(mcscore+sqscore)+"점");
            return;
        }
        correctans.setText(qsq[pg].answer);
        userans.setText(choices[20+pg]);
    }

    public void End_Test(View v)
    {
        this.finish();
    }
}
