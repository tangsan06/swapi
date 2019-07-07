package com.example.swapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.swapi.model.PessoaModel;
import com.example.swapi.retrofitFactory.ApiUrl;
import com.example.swapi.retrofitFactory.PeopleService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    private PessoaModel oPessoa = new PessoaModel();
    private int quantidade;
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private List<PessoaModel> lstPessoa;
    private PeopleAdapter peopleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.lstDados);
        lstPessoa = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        listarPessoa();
    }

    private void listarPessoa(){
       // DaoPessoa daoPessoa = new DaoPessoa();
        PessoaModel oPessoa = new PessoaModel();
        buscarPessoa(10);
    }

    public void buscarPessoa(final int quantidade){
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PeopleService service = retrofit.create(PeopleService.class);
        for(int i = 1; i <= quantidade; i++) {
            final int value = i;
            Call<PessoaModel> call = service.getPessoa(i);

            String name = "";
            call.enqueue(new Callback<PessoaModel>() {
                @Override
                public void onResponse(Call<PessoaModel> call, Response<PessoaModel> response) {
                    if (response.isSuccessful()) {
                        oPessoa = response.body();
                        adicionarPessoa(oPessoa);

                        if(value == quantidade){
                            criarLayout();
                        }
                    }
                }

                @Override
                public void onFailure(Call<PessoaModel> call, Throwable t) {
                    System.out.println("Não foi possivel realizar essa operação");
                }
            });
        }
    }

    public void adicionarPessoa(PessoaModel pessoa){
        this.lstPessoa.add(pessoa);
    }

    public void criarLayout(){
        System.out.println(this.lstPessoa.size());
        peopleAdapter = new PeopleAdapter(this.lstPessoa);
        recyclerView.setAdapter(peopleAdapter);
    }

}
