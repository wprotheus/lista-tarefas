package com.wprotheus.lista_tarefas.utils;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wprotheus.lista_tarefas.R;
import com.wprotheus.lista_tarefas.model.DbSqlite;
import com.wprotheus.lista_tarefas.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaHolder> {
    private Context contexto;
    private DbSqlite dbSqlite;
    private List<Tarefa> tarefaList;

    public TarefaAdapter(Context contexto, DbSqlite dbSqlite) {
        this.contexto = contexto;
        this.dbSqlite = dbSqlite;
        this.tarefaList = new ArrayList<>();
    }

    public class TarefaHolder extends RecyclerView.ViewHolder {
        public CheckBox cbSatus;
        private TextView tietTitulo;
        private TextView tietDescricao;
        private TextView tietData;

        public TarefaHolder(@NonNull View itemView) {
            super(itemView);
            tietTitulo = itemView.findViewById(R.id.tietTitulo);
            tietDescricao = itemView.findViewById(R.id.tietDescricao);
            tietData = itemView.findViewById(R.id.tietDataFim);
            cbSatus = itemView.findViewById(R.id.cbStatus);
            dbSqlite.tarefaList(false);
        }
    }

    public void updateTarefaList(boolean status) {
        tarefaList = dbSqlite.tarefaList(status);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TarefaAdapter.TarefaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TarefaHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_itens_lista, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaHolder holder, @SuppressLint("RecyclerView") int position) {
        if (position != RecyclerView.NO_POSITION) {
            Tarefa tarefa = tarefaList.get(position);
            holder.tietTitulo.setText(tarefa.getTitulo());
            holder.tietDescricao.setText(tarefa.getDescricao());
            holder.tietData.setText(tarefa.getDataFim());
            holder.cbSatus.setChecked(tarefa.isStatus());

            holder.cbSatus.setOnCheckedChangeListener((buttonView, isChecked) -> {
                tarefa.setStatus(isChecked);
                dbSqlite.editarTarefa(tarefa);
            });
        }
    }

    @Override
    public int getItemCount() {
        return tarefaList.size();
    }
}