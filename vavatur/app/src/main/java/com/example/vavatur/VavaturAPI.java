package com.example.vavatur;

import classes.ClienteJSON;
import retrofit2.Call;
import retrofit2.http.GET;

public interface VavaturAPI {

    @GET("clientes.php")
    Call <ClienteJSON> getClientes();

}