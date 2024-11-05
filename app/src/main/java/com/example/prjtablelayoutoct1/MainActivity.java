package com.example.prjtablelayoutoct1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import model.Schedule;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView[] listOfTV;
    int widgets[] = {R.id.tvMMorning, R.id.tvMAftEven,
                    R.id.tvTMonAft, R.id.tvTEven};
    Schedule[] scheduleList;

    //--1 declare activity result launcher
    ActivityResultLauncher<Intent> actResLauncher;
    //
    TextView clickedTV;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initialize();
    }

    private void initialize() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Schedule oneSchedule;
        scheduleList = getData(widgets.length);
        listOfTV = new TextView[widgets.length];
        for(int i=0;i<widgets.length;i++){
            listOfTV[i]=findViewById(widgets[i]);
            oneSchedule = scheduleList[i];
            listOfTV[i].setText(oneSchedule.getDescription());
            listOfTV[i].setTextColor(oneSchedule.getTextColor());
            listOfTV[i].setOnClickListener(this);
        }
        
        //
        actResLauncher = 
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(), //make sure to choose the 1st opt
                        new ActivityResultCallback<ActivityResult>() {
                            @Override
                            public void onActivityResult(ActivityResult o) {
                                if(o.getResultCode()==RESULT_OK){
                                    String newSchedule =
                                            o.getData().getStringExtra("new_schedule");
                                    clickedTV.setText(newSchedule);
                                }
                            }
                        }
                );


    }

    private Schedule[] getData(int len) {
        Schedule[] list;
        list = new Schedule[len];
        list[0] = new Schedule(0,"Android", Color.MAGENTA);
        list[1] = new Schedule(1, "SPORT", Color.BLUE);
        list[2] = new Schedule(2,"Java");
        list[3] = new Schedule(3,"SQL",Color.RED);

        return list;
    }

    @Override
    public void onClick(View view) {
       // Toast.makeText(this,"One Textview is clicked",Toast.LENGTH_LONG).show();


        //Launching the change schedule activity
        clickedTV = (TextView)view;//receive the content of tv
        String scheduleDesc = clickedTV.getText().toString(); //corresponds to text intv
        Intent intent= new Intent(this,ChangeSchedule.class);
        intent.putExtra("schedule",scheduleDesc);
        actResLauncher.launch(intent);

    }
}