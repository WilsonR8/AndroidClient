package com.will.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {


    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private EditText usertxt,passwordtxt;
    private Button signinbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usertxt = findViewById(R.id.usertxt);
        passwordtxt = findViewById(R.id.passwordtxt);
        signinbtn = findViewById(R.id.signinbtn);

        Init();

        signinbtn.setOnClickListener(
                (v) -> {
                    String x = usertxt.getText().toString();
                    String y = passwordtxt.getText().toString();
                    sendMessage(x + ":"+y);
                }
        );
    }



      public void Init(){
          new Thread(
                    ()->{
                        try {
                            //2.enviar solicitud de conexion
                            socket = new Socket("192.168.1.33", 5000);
                            //3.Cliente y server conect

                            InputStream is = socket.getInputStream();
                            InputStreamReader isr = new InputStreamReader(is);
                            reader = new BufferedReader(isr);

                            OutputStream os =socket.getOutputStream();
                            OutputStreamWriter osw = new OutputStreamWriter(os);
                            writer = new BufferedWriter(osw);

                            while(true) {
                                System.out.println("perate pue..");
                                String line=reader.readLine();
                                System.out.println("Recibido...");
                                System.out.println("Recibido:"+line);
                            }

                        } catch (UnknownHostException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
            ).start();
        }


    public void sendMessage(String msg){
        new Thread(
                ()->{
                    try {

                     writer.write(msg+"\n");
                     writer.flush();

                } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
                }
    }
        ).start();
    }





}