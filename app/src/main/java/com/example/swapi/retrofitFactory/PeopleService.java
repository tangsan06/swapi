package com.example.swapi.retrofitFactory;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import com.example.swapi.model.PessoaModel;

public interface PeopleService {
        @GET("{people}")
        Call<PessoaModel> getPessoa(@Path("people") int idPessoa);
}
