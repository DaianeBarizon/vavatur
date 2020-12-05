package com.example.vavatur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Dictionary;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class MarkSeatActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_seat);

        Button btnEntrar = findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(this);

        String resposta = null;

        /*try{
            //MarkSeatActivity = new HTTPService().execute().get();
            //tvResposta.setText(coins.toString()); //aqui chama a função to string
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/

        Log.d("[MarkSeatActivity] => ", String.valueOf(resposta));

    }

    protected String doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();

        try{
            URL url = new URL("http://elfcorreia.com.br/vavatur/assentos.php");

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setRequestProperty("Content-type","application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setDoOutput(true);
            conexao.setConnectTimeout(5000);
            conexao.connect();

            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()){
                resposta.append(scanner.next());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resposta.toString();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnEntrar:
                Intent it = new Intent(this, HomeActivity.class);
                startActivity(it);
                break;
        }
    }
}
