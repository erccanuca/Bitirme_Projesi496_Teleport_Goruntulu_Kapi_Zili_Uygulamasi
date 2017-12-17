package raspberrypi.example.com.raspberrypi_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button connect_btn;
    Button exit_btn;
    TextView response;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connect_btn = (Button) findViewById(R.id.connect);
        exit_btn = (Button) findViewById(R.id.exit);
        response = (TextView) findViewById(R.id.responseTextView);

        connect_btn.setOnClickListener(this);
        exit_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            // first screen pressed the connect button
            case R.id.connect:
                final AlertDialog.Builder builder_connection = new AlertDialog.Builder(MainActivity.this);
                View connectView = getLayoutInflater().inflate(R.layout.connect_dialog,null);
                final EditText ipEdit  = (EditText) connectView.findViewById(R.id.ip_edit);
                final EditText portEdit = (EditText) connectView.findViewById(R.id.port_edit);
                final Button connectButton = (Button) connectView.findViewById(R.id.connect_to_rpi);
                Button exitButton = (Button) connectView.findViewById(R.id.exit_to_connection_label);


                builder_connection.setView(connectView);
                builder_connection.setCancelable(false);
                final AlertDialog alertDialog_connection = builder_connection.create();
                alertDialog_connection.show();

                // Connection button pressed
                connectButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        alertDialog_connection.dismiss();

                        // connection successful
                        if(!ipEdit.getText().toString().isEmpty() && !portEdit.getText().toString().isEmpty())
                        {
                            Connection connection = new Connection(ipEdit.getText().toString(),
                                    Integer.parseInt(portEdit.getText().toString()),
                                    response);
                            connection.execute();

                            String isConnected = response.getText().toString();
                            showToast(isConnected);

                            final AlertDialog.Builder builder_connect_success = new AlertDialog.Builder(MainActivity.this);
                            final View connectSuccess = getLayoutInflater().inflate(R.layout.connect_success,null);
                            Button ok_btn = (Button) connectSuccess.findViewById(R.id.ok);

                            builder_connect_success.setView(connectSuccess);
                            builder_connect_success.setCancelable(false);
                            final AlertDialog alertDialog_connection_success = builder_connect_success.create();
                            alertDialog_connection_success.show();
                            // ok button pressed
                            ok_btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    // close success dialog and show next screen
                                    finish();
                                    alertDialog_connection_success.dismiss();
                                    Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                                    startActivity(intent);
                                }
                            });
                        }
                        // Connection Fail!
                        else
                        {
                            final AlertDialog.Builder builder_connect_fail = new AlertDialog.Builder(MainActivity.this);
                            View connectFail = getLayoutInflater().inflate(R.layout.connect_fail,null);
                            Button retry_btn = (Button) connectFail.findViewById(R.id.retry_connect);

                            builder_connect_fail.setView(connectFail);
                            builder_connect_fail.setCancelable(false);
                            final AlertDialog alertDialog_connection_fail = builder_connect_fail.create();
                            alertDialog_connection_fail.show();

                            // retry button pressed
                            retry_btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    // close fail dialog and show connection screen
                                    alertDialog_connection_fail.dismiss();
                                    //alertDialog_connection.show();
                                }
                            });
                        }
                    }
                });
                exitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog_connection.dismiss();
                    }
                });


                break;
            // first screes exit to app
            case R.id.exit:

                finish();
                System.exit(0);
                break;

        }

    }

    private void showToast(final String text)
    {
        Toast.makeText(MainActivity.this,
                text,
                Toast.LENGTH_SHORT).show();

    }


}
