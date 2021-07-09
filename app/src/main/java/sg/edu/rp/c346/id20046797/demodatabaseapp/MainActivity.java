package sg.edu.rp.c346.id20046797.demodatabaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnInsert = findViewById(R.id.buttonInsert);
        Button btnGetTask = findViewById(R.id.buttonGetTask);
        TextView tvDisplay = findViewById(R.id.textViewDisplay);
        EditText etDescription = findViewById(R.id.editTextDescription);
        EditText etDate = findViewById(R.id.editTextDate);
        ListView lv = findViewById(R.id.lv);

        ArrayList<String> aaList = new ArrayList<>();
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, aaList);
        lv.setAdapter(aa);

//        DBHelper db = new DBHelper(MainActivity.this);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                if(!(etDescription.getText().toString().isEmpty()) && !(etDate.getText().toString().isEmpty()))
                    db.insertTask(etDescription.getText().toString(), etDate.getText().toString());
                else Toast.makeText(MainActivity.this, "is Empty", Toast.LENGTH_SHORT).show();
            }
        });

        btnGetTask.setOnClickListener(v -> {
            aa.clear();
            DBHelper db = new DBHelper(MainActivity.this);

            ArrayList<Task> data = db.getTasks();
            db.close();

            for (Task I : data) {
                aa.add(I.toString());
            }

            aa.notifyDataSetChanged();
        });
    }
}