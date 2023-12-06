package com.wprotheus.lista_tarefas.model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DbSqlite extends SQLiteOpenHelper {
    private static final String DB_NAME = "dbsqlite";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "tb_tarefa";
    private static final String ID = "ID";
    private static final String TITULO = "TITULO";
    private static final String DESCRICAO = "DESCRICAO";
    private static final String DATA = "DATA";
    private static final String STATUS = "STATUS";

    public DbSqlite(@Nullable Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE tb_tarefa (")
                .append("ID INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append("TITULO TEXT NOT NULL, ")
                .append("DESCRICAO TEXT NOT NULL, ")
                .append("DATA TEXT NOT NULL, ")
                .append("STATUS TEXT NOT NULL");
        db.execSQL(query.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void adicionarTarefa(Tarefa tarefa)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.beginTransaction();
            ContentValues values = new ContentValues();
            values.put(TITULO, tarefa.getTitulo());
            values.put(DESCRICAO, tarefa.getDescricao());
            values.put(DATA, String.valueOf(tarefa.getDataFim()));
            db.insert(TABLE_NAME, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            db.endTransaction();
            db.close();
        }
    }


    @SuppressLint("Range")
    public List<Tarefa> tarefaList()
    {
        SQLiteDatabase database = getReadableDatabase();
        String[] colunas = {ID, TITULO, DESCRICAO, DATA};
        List<Tarefa> tarefaList = new ArrayList<>();

        try (Cursor cursor = database.query(TABLE_NAME, colunas, null, null, null, null, null))
        {
            while (cursor.moveToNext())
            {
                Tarefa t = new Tarefa();
                t.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                t.setTitulo(cursor.getString(cursor.getColumnIndex(TITULO)));
                t.setDescricao(cursor.getString(cursor.getColumnIndex(DESCRICAO)));
                t.setDataFim(LocalDateTime.parse(cursor.getString(cursor.getColumnIndex(DATA))));
                tarefaList.add(t);
            }
        } catch (Exception e)
        {
            throw new RuntimeException();
        } finally {
            database.close();
        }
        return tarefaList;
    }

    public void apagarTarefa(int id)
    {
        SQLiteDatabase database = getReadableDatabase();
        String tarefaID = "ID LIKE ?";
        String[] IDSelecionado = {String.valueOf(id)};
        database.delete(TABLE_NAME, tarefaID, IDSelecionado);
    }

    public void editarTarefa(Tarefa tarefa)
    {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITULO, tarefa.getTitulo());
        values.put(DESCRICAO, tarefa.getDescricao());
        values.put(DATA, String.valueOf(tarefa.getDataFim()));

        String selection = "ID LIKE ?";
        String[] selectionId = {String.valueOf(tarefa.getId())};

        int count = database.update(TABLE_NAME, values, selection, selectionId);
    }
}