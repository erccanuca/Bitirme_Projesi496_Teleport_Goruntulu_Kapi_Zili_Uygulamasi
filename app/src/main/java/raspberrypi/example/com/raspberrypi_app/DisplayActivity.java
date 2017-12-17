package raspberrypi.example.com.raspberrypi_app;


import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


public class DisplayActivity extends AppCompatActivity implements OnClickListener{

    TextView response;
    Button  buttonLock,
            buttonUnLock,
            buttonVideoView,
            buttonBack,
            buttonExit;
    FrameLayout cameraPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display);


        buttonLock =  (Button)findViewById(R.id.lockButton);
        buttonUnLock = (Button)findViewById(R.id.unlockButton);
        buttonVideoView =  (Button)findViewById(R.id.video_viewButton);
        buttonBack =  (Button)findViewById(R.id.backButton);
        buttonExit =  (Button)findViewById(R.id.exitButton);
        cameraPreview = (FrameLayout)findViewById(R.id.camera_preview);

        response =   (TextView)findViewById(R.id.responseTextView);

        buttonLock.setOnClickListener(this);
        buttonUnLock.setOnClickListener(this);
        buttonBack.setOnClickListener(this);
        buttonExit.setOnClickListener(this);
        buttonVideoView.setOnClickListener(this);

        Toast.makeText(DisplayActivity.this,
                response.getText().toString(),
                Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.video_viewButton:
                setDefaultColors();
                buttonVideoView.setBackgroundColor(getResources().getColor(R.color.clicked_color));;
                String hit = getString(R.string.view_camera);
               // cameraPreview.setBackground();
                response.setText(hit);

                break;

            case R.id.lockButton:  // click lock button
                setDefaultColors();
                hit = getString(R.string.lock_hit);
                response.setText(hit);
                buttonLock.setBackgroundColor(getResources().getColor(R.color.clicked_color));



                break;

            case R.id.unlockButton: // click unlock button
                setDefaultColors();
                hit = getString(R.string.unlock_hit);
                response.setText(hit);
                buttonUnLock.setBackgroundColor(getResources().getColor(R.color.clicked_color));

                break;

            case R.id.backButton:  // click back button
                setDefaultColors();
                hit = getString(R.string.back_hit);
                response.setText(hit);
                buttonBack.setBackgroundColor(getResources().getColor(R.color.clicked_color));
                finish();
                startActivity(new Intent(DisplayActivity.this,MainActivity.class));

                break;

            case R.id.exitButton:  // click exit button
                setDefaultColors();
                hit = getString(R.string.exit_hit);
                response.setText(hit);
                buttonExit.setBackgroundColor(getResources().getColor(R.color.clicked_color));
                finish();
                System.exit(0);

                break;
        }


    }
    public void setDefaultColors()
    {
        buttonVideoView.setBackgroundColor(Color.WHITE);
        buttonLock.setBackgroundColor(Color.WHITE);
        buttonUnLock.setBackgroundColor(Color.WHITE);
        buttonExit.setBackgroundColor(Color.WHITE);
        buttonBack.setBackgroundColor(Color.WHITE);
    }
}

