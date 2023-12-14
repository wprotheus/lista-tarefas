package com.wprotheus.lista_tarefas.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wprotheus.lista_tarefas.R;
import com.wprotheus.lista_tarefas.model.DbSqlite;
import com.wprotheus.lista_tarefas.model.Tarefa;
import com.wprotheus.lista_tarefas.utils.TarefaAdapter;

import java.util.Objects;

public class FragmentToDoList extends Fragment {
    private DbSqlite dbSqlite;
    private Tarefa tarefa = new Tarefa();
    private RecyclerView rvAndamento;

    public FragmentToDoList() {
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_do_list, container, false);
        CheckBox cbStatus = view.findViewById(R.id.cbStatus);
        dbSqlite = new DbSqlite(getContext());
        if (cbStatus != null) {
            cbStatus.setOnClickListener(v -> {
                tarefa.setStatus(true);
                dbSqlite.editarTarefa(tarefa);
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            });
        }

        rvAndamento = view.findViewById(R.id.rvAndamento);
        rvAndamento.setHasFixedSize(true);
        rvAndamento.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rvAndamento.setAdapter(new TarefaAdapter(getContext(), dbSqlite));
        ((TarefaAdapter) Objects.requireNonNull(rvAndamento.getAdapter())).updateTarefaList(false);

        return view;
    }
}