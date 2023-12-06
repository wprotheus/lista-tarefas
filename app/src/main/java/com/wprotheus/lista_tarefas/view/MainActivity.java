package com.wprotheus.lista_tarefas.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;

import com.wprotheus.lista_tarefas.R;

public class MainActivity extends AppCompatActivity {
    private Button btnFazer;
    private Button btnFeito;
    private Button btnCadastrar;
    private FazerFragment fazerFragment;
    private FeitoFragment feitoFragment;
    private CadastrarFragment cadastrarFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnFazer = findViewById(R.id.btnFazer);
        btnFeito = findViewById(R.id.btnFeito);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        fazerFragment = new FazerFragment();
        feitoFragment = new FeitoFragment();
        cadastrarFragment = new CadastrarFragment();

        btnFazer.setOnClickListener(v -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frmTarefa, fazerFragment);
            transaction.commit();
        });
        btnFeito.setOnClickListener(v -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frmTarefa, feitoFragment);
            transaction.commit();
        });
        btnCadastrar.setOnClickListener(v -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frmTarefa, cadastrarFragment);
            transaction.commit();
        });
    }
}