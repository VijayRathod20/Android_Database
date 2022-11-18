package com.example.anddatabase;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText PID,NAME,STREAM;
        Button INSERT,UPDATE,VIEW,DELETE;

        PID = findViewById(R.id.pid);
        NAME = findViewById(R.id.name);
        STREAM = findViewById(R.id.stream);

        INSERT = findViewById(R.id.insert);
        VIEW = findViewById(R.id.view);
        UPDATE = findViewById(R.id.update);
        DELETE = findViewById(R.id.delete);

        DBHelper dbHelper = new DBHelper(this);

        INSERT.setOnClickListener(view -> {
            boolean temp=dbHelper.insert(Integer.parseInt(PID.getText().toString()),NAME.getText().toString(),STREAM.getText().toString());
            if(temp == true){
                Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Error While Inserting Data!", Toast.LENGTH_SHORT).show();
            }
        });

        UPDATE.setOnClickListener(view -> {
            boolean temp=dbHelper.update(Integer.parseInt(PID.getText().toString()),NAME.getText().toString(),STREAM.getText().toString());
            if(temp == true){
                Toast.makeText(this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Error While Updating Data!", Toast.LENGTH_SHORT).show();
            }

        });

        DELETE.setOnClickListener(view -> {
            boolean temp=dbHelper.delete(Integer.parseInt(PID.getText().toString()));
            if(temp == true){
                Toast.makeText(this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Error While Deleting Data!", Toast.LENGTH_SHORT).show();
            }
        });

        VIEW.setOnClickListener(view -> {
            Cursor cursor = dbHelper.view();
            if(cursor.getCount() == 0){
                Toast.makeText(this, "No Data Available", Toast.LENGTH_SHORT).show();
            }
            StringBuffer buffer = new StringBuffer();
            while (cursor.moveToNext()){
                buffer.append("Pid :- "+cursor.getString(0)+"\n");
                buffer.append("Name :- "+cursor.getString(1)+"\n");
                buffer.append("Stream :- "+cursor.getString(2)+"\n\n");
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this).
                    setTitle("Student Details")
                    .setCancelable(true)
                    .setMessage(buffer);
            builder.create();
            builder.show();
        });
    }
}