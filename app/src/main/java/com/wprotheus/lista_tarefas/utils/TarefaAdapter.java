package com.wprotheus.lista_tarefas.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wprotheus.lista_tarefas.R;
import com.wprotheus.lista_tarefas.model.Tarefa;

import java.util.List;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder> {
    private List<Tarefa> listaTarefas;

    public TarefaAdapter(List<Tarefa> listaTarefas) {
        this.listaTarefas = listaTarefas;
    }

    @NonNull
    @Override
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tarefa, parent, false);
        return new TarefaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {
        Tarefa tarefa = listaTarefas.get(position);
        holder.tvTitulo.setText(tarefa.getTitulo());

        holder.btnEditar.setOnClickListener(v -> Toast.makeText(v.getContext(), "Editar: " + tarefa.getTitulo(), Toast.LENGTH_SHORT).show());

        holder.btnExcluir.setOnClickListener(v -> {


            Toast.makeText(v.getContext(), "Excluir: " + tarefa.getTitulo(), Toast.LENGTH_SHORT).show();
            listaTarefas.remove(position);
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return listaTarefas.size();
    }

    public static class TarefaViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo;
        Button btnEditar;
        Button btnExcluir;

        public TarefaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnExcluir = itemView.findViewById(R.id.btnExcluir);
        }
    }
}
