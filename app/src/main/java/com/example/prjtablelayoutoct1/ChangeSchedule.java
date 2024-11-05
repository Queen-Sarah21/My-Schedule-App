package com.example.prjtablelayoutoct1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChangeSchedule extends AppCompatActivity implements View.OnClickListener{

    EditText edNewSchedule;
    Button btnReturn;

    String oldScheduleDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_change_schedule);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initialize();
    }

    private void initialize() {
        edNewSchedule = findViewById(R.id.edNewSchedule);
        btnReturn = findViewById(R.id.btnReturn);

        btnReturn.setOnClickListener(this);
        oldScheduleDesc = getIntent().getStringExtra("schedule");
        edNewSchedule.setText(oldScheduleDesc);

    }

    @Override
    public void onClick(View view) {

        String newScheduleDesc = edNewSchedule.getText().toString();
        Intent intent = new Intent();
        if(oldScheduleDesc.equalsIgnoreCase(newScheduleDesc))
            setResult(RESULT_CANCELED,intent);
        else {
            intent.putExtra("new_schedule", newScheduleDesc);
            setResult(RESULT_OK, intent);
        }
        finish();
    }
}