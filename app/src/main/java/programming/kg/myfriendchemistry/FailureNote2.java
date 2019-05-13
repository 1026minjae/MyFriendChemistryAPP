package programming.kg.myfriendchemistry;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class FailureNote2 extends AppCompatActivity {
    CheckBox check1 = null;
    CheckBox check2 = null;
    CheckBox check3 = null;
    CheckBox check4 = null;
    CheckBox check5 = null;
    Button page = null;
    TextView num =null;
    TextView board = null;
    EditText edit = null;
    LinearLayout mc = null;
    LinearLayout sq = null;

    private int MC_MAX;//오답목록에 있는 객관식 문제 개수
    private int SQ_MAX;//오답목록에 있는 주관식 문제 개수
    private int QMC_MAX;//오답노트에 출제할 객관식 문제 개수
    private int QSQ_MAX;//오답노트에 출제할 주관식 문제 개수
    private TYPE.QMC[] qmc;//객관식 문제 배열
    private TYPE.QSQ[] qsq;//주관식 문제 배열
    private String[] choices;//사용자가 선택한 답 배열
    private int count=0;
    private boolean changing=false;

    private Query query = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failure_note2);
        check1 = (CheckBox) findViewById(R.id.checkBox);
        check2 = (CheckBox) findViewById(R.id.checkBox2);
        check3 = (CheckBox) findViewById(R.id.checkBox3);
        check4 = (CheckBox) findViewById(R.id.checkBox4);
        check5 = (CheckBox) findViewById(R.id.checkBox5);
        page = (Button) findViewById(R.id.Page);
        num = (TextView) findViewById(R.id.Question_Num);
        board = (TextView) findViewById(R.id.Question_Board);
        edit = (EditText) findViewById(R.id.Edit);
        mc = (LinearLayout) findViewById(R.id.layout_mc);
        sq = (LinearLayout) findViewById(R.id.layout_sq);

        query = new Query(this);

        Intent intent = getIntent();
        MC_MAX=intent.getIntExtra("MC_NUM",0);
        SQ_MAX=intent.getIntExtra("SQ_NUM",0);
        if(MC_MAX<20) QMC_MAX=MC_MAX;
        else QMC_MAX=20;
        if(SQ_MAX<5) QSQ_MAX=SQ_MAX;
        else QSQ_MAX=5;

        qmc=new TYPE.QMC[QMC_MAX];
        qsq=new TYPE.QSQ[QSQ_MAX];
        choices=new String[QMC_MAX+QSQ_MAX];

        //사용자가 체크박스에 체크를 할 경우 그것을 감지하여 해당 체크박스의 번호를 사용자 답 배열에 넣는다.
        check1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check1.isChecked() == false) return;
                choices[count] = "1";
                check2.setChecked(false);
                check3.setChecked(false);
                check4.setChecked(false);
                check5.setChecked(false);
            }
        });
        check2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check2.isChecked() == false) return;
                choices[count] = "2";
                check1.setChecked(false);
                check3.setChecked(false);
                check4.setChecked(false);
                check5.setChecked(false);
            }
        });
        check3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check3.isChecked() == false) return;
                choices[count] = "3";
                check1.setChecked(false);
                check2.setChecked(false);
                check4.setChecked(false);
                check5.setChecked(false);
            }
        });
        check4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check4.isChecked() == false) return;
                choices[count] = "4";
                check1.setChecked(false);
                check2.setChecked(false);
                check3.setChecked(false);
                check5.setChecked(false);
            }
        });
        check5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check5.isChecked() == false) return;
                choices[count] = "5";
                check1.setChecked(false);
                check2.setChecked(false);
                check3.setChecked(false);
                check4.setChecked(false);
            }
        });

        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(changing==true)
                {
                    changing=false;
                    return;
                }//다음 문제로 갔다가 다시 이전 문제로 돌아올 때 입력했던 답이 공백으로 바뀌는 것을 막기 위한 장치.
                choices[count] = String.valueOf(edit.getText());
            }
        });

        qmc = Make_QMC();
        qsq = Make_QSQ();

        page.setText("1/"+String.valueOf(QMC_MAX+QSQ_MAX));

        if(QMC_MAX==0)
        {
            board.setText(qsq[0].question);
            return;
        }
        board.setText(qmc[0].question);
        check1.setText(qmc[0].a1);
        check2.setText(qmc[0].a2);
        check3.setText(qmc[0].a3);
        check4.setText(qmc[0].a4);
        check5.setText(qmc[0].a5);
    }

    @Override
    public void onBackPressed(){
        Toast.makeText(getApplicationContext(), "뒤로가기는 안됩니다. 차라리 홈버튼을 누르세요.", Toast.LENGTH_LONG).show();
    }//뒤로가기 버튼을 누를 경우 화면이 꺼지는 것을 막기 위한 함수.

    /**
     * 객관식 문제 배열에 객관식 문제를 채운다
     * @return
     */
    private TYPE.QMC[] Make_QMC()
    {
        TYPE.QMC[] qmc = new TYPE.QMC[QMC_MAX];

        for(int i=0;i<QMC_MAX;i++)
        {
            TYPE.QMC qm = new TYPE.QMC();
            Cursor cursor = query.searchWQMC(MC_MAX-i);
            if (cursor != null) {
                cursor.moveToFirst();
                qm.question = cursor.getString(cursor.getColumnIndex("QUES"));
                qm.a1 = cursor.getString(cursor.getColumnIndex("A1"));
                qm.a2 = cursor.getString(cursor.getColumnIndex("A2"));
                qm.a3 = cursor.getString(cursor.getColumnIndex("A3"));
                qm.a4 = cursor.getString(cursor.getColumnIndex("A4"));
                qm.a5 = cursor.getString(cursor.getColumnIndex("A5"));
                qm.answer = cursor.getString(cursor.getColumnIndex("ANS"));
                cursor.close();
            }
            query.Delete_WQMC(MC_MAX-i);//출제한 문제는 일단 오답목록에서 지운다.
            qmc[i] = qm;
        }
        return qmc;     //만든 객관식 문제 배열을 리턴
    }

    /**
     * 주관식 문제 배열에 주관식 문제를 채운다.
     * @return
     */
    private TYPE.QSQ[] Make_QSQ()
    {
        TYPE.QSQ[] qsq = new TYPE.QSQ[QSQ_MAX];
        for (int i = 0; i < QSQ_MAX; i++)
        {
            TYPE.QSQ qs =new TYPE.QSQ();
            Cursor cursor = query.searchWQSQ(SQ_MAX-i);
            if (cursor != null) {
                cursor.moveToFirst();
                qs.question = cursor.getString(cursor.getColumnIndex("QUES"));
                qs.answer = cursor.getString(cursor.getColumnIndex("ANS"));
                cursor.close();
            }
            query.Delete_WQSQ(SQ_MAX-i);//출제한 문제는 일단 오답목록에서 지운다.
            qsq[i] = qs;
        }
        return qsq;     //만든 주관식 문제 배열을 리턴
    }

    /**
     * 이전 문제로 돌아갈 때 세팅해주는 함수.
     * @param v
     */
    public void Previous_Click(View v)
    {
        if(count==0)return;

        count--;

        page.setText(String.valueOf(count+1)+"/"+String.valueOf(QMC_MAX+QSQ_MAX));
        num.setText(String.valueOf(count+1));
        if(count<QMC_MAX-1)
        {
            check1.setChecked(false);
            check2.setChecked(false);
            check3.setChecked(false);
            check4.setChecked(false);
            check5.setChecked(false);
            board.setText(qmc[count].question);
            check1.setText(qmc[count].a1);
            check2.setText(qmc[count].a2);
            check3.setText(qmc[count].a3);
            check4.setText(qmc[count].a4);
            check5.setText(qmc[count].a5);
        }
        else if(count==QMC_MAX-1)
        {
            mc.setVisibility(View.VISIBLE);
            sq.setVisibility(View.GONE);
            board.setText(qmc[count].question);
            check1.setText(qmc[count].a1);
            check2.setText(qmc[count].a2);
            check3.setText(qmc[count].a3);
            check4.setText(qmc[count].a4);
            check5.setText(qmc[count].a5);
        }
        else if(count>QMC_MAX-1)
        {
            changing=true;
            edit.setText("");
            board.setText(qsq[count-QMC_MAX].question);
            if(ready==true)ready=false;
        }

        ReAnswer();
    }

    private boolean ready = false;

    /**
     * 다음 문제로 넘어갈 때 세팅해주는 함수.
     * @param v
     */
    public void Next_Click(View v)
    {
        if(count==QMC_MAX+QSQ_MAX-1)
        {
            if(ready==true)
            {
                Intent intent = new Intent(FailureNote2.this, FailureNote3.class);
                intent.putExtra("QMC",new ArrayList<TYPE.QMC>(Arrays.asList(qmc)));//객관식 문제 배열
                intent.putExtra("QSQ",new ArrayList<TYPE.QSQ>(Arrays.asList(qsq)));//주관식 문제 배열
                intent.putExtra("CHOICES",new ArrayList<String>(Arrays.asList(choices)));//사용자가 선택한 답 배열
                intent.putExtra("QMC_NUM", QMC_MAX);
                intent.putExtra("QSQ_NUM", QSQ_MAX);
                startActivity(intent);
                this.finish();
            }
            ready=true;
            Toast.makeText(getApplicationContext(), "한번 더 누르면 제출합니다.", Toast.LENGTH_SHORT).show();
            return;
        }


        count++;

        page.setText(String.valueOf(count+1)+"/"+String.valueOf(QMC_MAX+QSQ_MAX));
        num.setText(String.valueOf(count+1));
        if(count<QMC_MAX)
        {
            check1.setChecked(false);
            check2.setChecked(false);
            check3.setChecked(false);
            check4.setChecked(false);
            check5.setChecked(false);
            board.setText(qmc[count].question);
            check1.setText(qmc[count].a1);
            check2.setText(qmc[count].a2);
            check3.setText(qmc[count].a3);
            check4.setText(qmc[count].a4);
            check5.setText(qmc[count].a5);
        }
        else if(count==QMC_MAX)
        {
            mc.setVisibility(View.GONE);
            sq.setVisibility(View.VISIBLE);
            board.setText(qsq[count-QMC_MAX].question);
        }
        else if(count>QMC_MAX)
        {
            changing=true;
            edit.setText("");
            board.setText(qsq[count-QMC_MAX].question);
        }

        ReAnswer();
    }

    /**
     * 사용자가 체크한 답안이 있다면 그것을 다시 표시해준다.
     */
    private void ReAnswer(){
        if(choices[count]==null)return;

        if(count<=QMC_MAX-1)
        {
            switch(choices[count])
            {
                case "1":
                    check1.setChecked(true);
                    break;
                case "2":
                    check2.setChecked(true);
                    break;
                case "3":
                    check3.setChecked(true);
                    break;
                case "4":
                    check4.setChecked(true);
                    break;
                case "5":
                    check5.setChecked(true);
                    break;
            }
        }
        else if(count>QMC_MAX-1)
        {
            edit.setText(choices[count]);
        }
    }
}
