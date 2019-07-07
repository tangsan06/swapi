package com.example.swapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swapi.model.PessoaModel;

import java.util.ArrayList;
import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolderPessoa> {

    private List<PessoaModel> lstPessoa;



    public PeopleAdapter(List<PessoaModel> olstPessoa){
        this.lstPessoa = olstPessoa;
    }

    @Override
    public PeopleAdapter.ViewHolderPessoa onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.line_people, parent, false);
        ViewHolderPessoa holderPessoa = new ViewHolderPessoa(view);
        return holderPessoa;
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleAdapter.ViewHolderPessoa holder, int position) {

        if((lstPessoa) != null && (lstPessoa.size() > 0)) {
            PessoaModel pessoa = lstPessoa.get(position);
            holder.txtNome.setText(pessoa.getName());
            holder.txtAltura.setText("Altura: " + pessoa.getHeight() + "cm");
            holder.txtAniversario.setText("Ano de Nascimento: " + pessoa.getBirth());
        }
    }

    @Override
    public int getItemCount() {
        return this.lstPessoa.size();
    }

    public class ViewHolderPessoa extends RecyclerView.ViewHolder{
        public TextView txtNome;
        public TextView txtAltura;
        public TextView txtAniversario;

        public ViewHolderPessoa(@NonNull View itemView) {
            super(itemView);

            txtNome = (TextView) itemView.findViewById(R.id.txtNome);
            txtAltura = (TextView) itemView.findViewById(R.id.txtAltura);
            txtAniversario = (TextView) itemView.findViewById(R.id.txtAniversario);

        }
    }
}
