package com.wprotheus.lista_tarefas.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wprotheus.lista_tarefas.R;
import com.wprotheus.lista_tarefas.model.DbSqlite;
import com.wprotheus.lista_tarefas.utils.TarefaAdapter;

import java.util.Objects;

public class FragmentDoneList extends Fragment {
    private DbSqlite dbSqlite;
    private RecyclerView rvConcluido;

    public FragmentDoneList() {
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_done_list, container, false);
        CheckBox cbStatus = view.findViewById(R.id.cbStatus);
        if (cbStatus != null)
            cbStatus.setVisibility(View.INVISIBLE);

        dbSqlite = new DbSqlite(getContext());

        rvConcluido = view.findViewById(R.id.rvConcluido);
        rvConcluido.setHasFixedSize(true);
        rvConcluido.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rvConcluido.setAdapter(new TarefaAdapter(getContext(), dbSqlite));
        ((TarefaAdapter) Objects.requireNonNull(rvConcluido.getAdapter())).updateTarefaList(true);

        return view;
    }
}