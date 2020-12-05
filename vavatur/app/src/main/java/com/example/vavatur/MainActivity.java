package com.example.vavatur;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import classes.Cliente;
import classes.ClienteJSON;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView resultado;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //RESULTADO É O TEXT AREA RECEBENDO TODOS OS EMAILS
        //resultado = findViewById(R.id.teste_teste);// teste da requisição
        email = (EditText) findViewById(R.id.edtEmail);
        Button btnEntrar = findViewById(R.id.btnEntrar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEntrar:
                Intent intent;
                Button btnEntrar = (Button) v;
                String email_digitado = email.getText().toString();
                String tag = btnEntrar.getTag().toString();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://elfcorreia.com.br/vavatur/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                VavaturAPI vavaturAPI = retrofit.create(VavaturAPI.class);

                Call<ClienteJSON> request = vavaturAPI.getClientes();
                request.enqueue(new Callback<ClienteJSON>() {
                    @Override
                    public void onResponse(Call<ClienteJSON> request, Response<ClienteJSON> response) {

                        if (!response.isSuccessful()) {
                            resultado.setText("Codigo: " + response.code() + "\nMensagem: Algo deu errado, tente novamente.");
                            return;
                        }
                        ClienteJSON clientes = response.body();

                        for (Cliente cliente : clientes.getClientes()) {
                            if (email_digitado.isEmpty() || !email_digitado.contains("@")) {
                                resultado.setText("Email inválido!");
                            }
                            String emaild = email_digitado;
                            if (null != cliente.getEmail() && cliente.getEmail().equals(email_digitado)) {
                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                intent.putExtra("id", cliente.getUserId());
                                intent.putExtra("nome", cliente.getNome());
                                startActivity(intent);
                            }
//                            String content = "";
//                            content += "Nome: " + cliente.getNome() + "\n";
//                            content += "Email: " + cliente.getEmail() + "\n\n";
//                            resultado.append(content);
                        }

                    }

                    @Override
                    public void onFailure(Call<ClienteJSON> request, Throwable t) {
                        resultado.setText(t.getMessage());
                    }
                });
                //abrindo atividade com a tag
                Bundle b = new Bundle();
                intent = new Intent(MainActivity.this, MainActivity.class);
                b.putString("tag", tag);
                intent.putExtras(b);
                resultado = findViewById(R.id.teste_teste);
//              startActivity(intent);
                break;
        }
    }
}