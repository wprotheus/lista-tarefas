package com.wprotheus.lista_tarefas.view;

import static com.google.android.material.internal.ViewUtils.hideKeyboard;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.wprotheus.lista_tarefas.R;
import com.wprotheus.lista_tarefas.model.DbSqlite;
import com.wprotheus.lista_tarefas.model.Tarefa;

public class FragmentAddTask extends Fragment {
    private DbSqlite dbSqlite;
    private TextInputEditText tietTitulo;
    private TextInputEditText tietDescricao;
    private TextInputEditText tietData;
    private Button btnAdicionar;

    public FragmentAddTask() {
    }

    @SuppressLint("RestrictedApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        tietTitulo = view.findViewById(R.id.tietTitulo);
        tietDescricao = view.findViewById(R.id.tietDescricao);
        tietData = view.findViewById(R.id.tietDataFim);
        btnAdicionar = view.findViewById(R.id.btnEditar);

        tietTitulo.setOnFocusChangeListener((v, hasFocus) -> { if(!hasFocus) hideKeyboard(v); });
        tietDescricao.setOnFocusChangeListener((v, hasFocus) -> { if(!hasFocus) hideKeyboard(v); });
        tietData.setOnFocusChangeListener((v, hasFocus) -> { if(!hasFocus) hideKeyboard(v); });

        btnAdicionar.setOnClickListener(v -> adicionarTarefa());
        return view;
    }

    private void adicionarTarefa() {
        String titulo = tietTitulo.getText().toString();
        String descricao = tietDescricao.getText().toString();
        String data = tietData.getText().toString();

        if (TextUtils.isEmpty(titulo) || TextUtils.isEmpty(descricao) || TextUtils.isEmpty(data)) {
            Toast.makeText(getActivity(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        dbSqlite = new DbSqlite(getActivity());
        long tarefaID = dbSqlite.adicionarTarefa(new Tarefa(titulo, descricao, data));

        if (tarefaID != -1) {
            Toast.makeText(getActivity(), "Tarefa adicionada com sucesso!", Toast.LENGTH_SHORT).show();
            requireActivity().onBackPressed();
        } else {
            Toast.makeText(getActivity(), "Erro ao cadastrar a tarefa", Toast.LENGTH_SHORT).show();
        }
    }
}