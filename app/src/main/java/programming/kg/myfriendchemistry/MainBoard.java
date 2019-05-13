package programming.kg.myfriendchemistry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainboard);

    }

    public void B1_Click(View v){
        Intent intent = new Intent(MainBoard.this, Atomboard.class);
        startActivity(intent);
    }

    public void B2_Click(View v){
        Intent intent = new Intent(MainBoard.this, School.class);
        startActivity(intent);
    }

    public void B3_Click(View v){
        Intent intent = new Intent(MainBoard.this, Test.class);
        startActivity(intent);
    }

    public void B4_Click(View v){
        Intent intent = new Intent(MainBoard.this, FailureNote.class);
        startActivity(intent);
    }

    public void B5_Click(View v){
        Intent intent = new Intent(MainBoard.this, Communication.class);
        startActivity(intent);
    }

    public void B6_Click(View v){
        Intent intent = new Intent(MainBoard.this, Information.class);
        startActivity(intent);
    }

}
