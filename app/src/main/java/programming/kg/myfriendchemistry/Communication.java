package programming.kg.myfriendchemistry;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Communication extends AppCompatActivity {
    public static final int MY_PERMISSION_REQUEST_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication);
        Button button = (Button)findViewById(R.id.button);
        final EditText title = (EditText)findViewById(R.id.mailTitle);
        final EditText body = (EditText)findViewById(R.id.mailBody);
        final EditText addr = (EditText)findViewById(R.id.mailAddr);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent send = new Intent(Intent.ACTION_SENDTO);
                String uriText = "mailto:" + Uri.encode("myfriendchemistry@gmail.com") +
                        "?subject=" + Uri.encode(title.getText().toString()) +
                        "&body=" + Uri.encode(body.getText().toString()+"\n"+addr.getText().toString());//사용자가 입력했던 내용을 그대로 붙여넣어준다.
                Uri uri = Uri.parse(uriText);

                send.setData(uri);

                startActivity(Intent.createChooser(send, "Send Email..."));

            }
        });

    }

}
