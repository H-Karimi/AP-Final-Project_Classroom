package com.classroom.zed.classroom;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class JoinClassActivity extends AppCompatActivity {

    private String input = "";

    private EditText join_class_et;
    private TextView join_class_error_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Join class");
        setContentView(R.layout.activity_join_class);

        join_class_et = findViewById(R.id.join_class_et);
        join_class_error_tv = findViewById(R.id.join_class_error_tv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_join_class, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.aboutus_join_class_menu:
                intent = new Intent(JoinClassActivity.this, AboutUsActivity.class);
                startActivity(intent);
                break;
            case R.id.join_submenu:
                if (!join_class_et.getText().toString().trim().isEmpty()) {
                    Communicator communicator = new Communicator();
                    communicator.execute("J#" + join_class_et.getText().toString().trim());
                    try {
                        input = communicator.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (input.equals("C#0")) {
                        Toast.makeText(JoinClassActivity.this, "You joined the class!", Toast.LENGTH_LONG).show();
                        intent = new Intent(JoinClassActivity.this, ClassPageActivity.class);
                        intent.putExtra("ClassCode", join_class_et.getText().toString().trim());
                        intent.putExtra("ClassState", "S");
                        startActivity(intent);

                    } else if (input.equals("C#1")) {
                        join_class_error_tv.setText("No class found.");

                    } else if (input.equals("C#2")) {
                        join_class_error_tv.setText("You are already teacher in this class!");

                    } else if (input.equals("C#3")) {
                        join_class_error_tv.setText("You are already student in this class!");

                    }
                } else
                    join_class_error_tv.setText("Class code field cannot be empty.");

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
