package com.wprotheus.lista_tarefas.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.wprotheus.lista_tarefas.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnFazer).setOnClickListener(this::onClick);
        findViewById(R.id.btnFeito).setOnClickListener(this::onClick);
        findViewById(R.id.btnCadastrar).setOnClickListener(this::onClick);
        mostrarFragmento(new FragmentToDoList());

    }

    @Override
    public void onClick(View view) {
        Fragment mainFragment = null;

        if (view.getId() == R.id.btnCadastrar) {
            mainFragment = new FragmentAddTask();
        } else if (view.getId() == R.id.btnFeito) {
            mainFragment = new FragmentDoneList();
        } else if (view.getId() == R.id.btnFazer) {
            mainFragment = new FragmentToDoList();
        }
        mostrarFragmento(mainFragment);
    }

    private void mostrarFragmento(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fcvFragment, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}