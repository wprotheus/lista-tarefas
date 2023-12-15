package com.wprotheus.lista_tarefas.model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
    private static final String ADICIONADA = "ADICIONADA";

    public DbSqlite(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE ")
                .append(TABLE_NAME).append(" (")
                .append("ID INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append("TITULO TEXT NOT NULL, ")
                .append("DESCRICAO TEXT NOT NULL, ")
                .append("DATA TEXT NOT NULL, ")
                .append("STATUS BOOLEAN NOT NULL)");
        db.execSQL(query.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long adicionarTarefa(Tarefa tarefa) {
        SQLiteDatabase db = this.getWritableDatabase();
        long tarefaID = -1;
        try {
            db.beginTransaction();
            ContentValues values = new ContentValues();
            values.put(TITULO, tarefa.getTitulo());
            values.put(DESCRICAO, tarefa.getDescricao());
            values.put(DATA, tarefa.getDataFim());
            values.put(STATUS, tarefa.isStatus());
            tarefaID = db.insert(TABLE_NAME, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            db.endTransaction();
            db.close();
        }
        return tarefaID;
    }


    @SuppressLint("Range")
    public List<Tarefa> tarefaList(boolean status) {
        SQLiteDatabase database = getReadableDatabase();
        String[] colunas = {ID, TITULO, DESCRICAO, DATA, STATUS};
        List<Tarefa> tarefaList = new ArrayList<>();

        String situacaoStatus = STATUS + " = ?";
        String[] condicaoStatus = {String.valueOf(status ? 1 : 0)};

        try (Cursor cursor = database.query(TABLE_NAME, colunas, situacaoStatus, condicaoStatus, null, null, null)) {
            while (cursor.moveToNext()) {
                Tarefa t = new Tarefa();
                t.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                t.setTitulo(cursor.getString(cursor.getColumnIndex(TITULO)));
                t.setDescricao(cursor.getString(cursor.getColumnIndex(DESCRICAO)));
                t.setDataFim(cursor.getString(cursor.getColumnIndex(DATA)));
                t.setStatus(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(STATUS))));
                tarefaList.add(t);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            database.close();
        }
        return tarefaList;
    }


    public void apagarTarefa(int id) {
        SQLiteDatabase database = getReadableDatabase();
        String tarefaID = "ID LIKE ?";
        String[] IDSelecionado = {String.valueOf(id)};
        database.delete(TABLE_NAME, tarefaID, IDSelecionado);
    }

    public void editarTarefa(Tarefa tarefa) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITULO, tarefa.getTitulo());
        values.put(DESCRICAO, tarefa.getDescricao());
        values.put(DATA, tarefa.getDataFim());
        values.put(STATUS, tarefa.isStatus());

        String selection = "ID LIKE ?";
        String[] selectionId = {String.valueOf(tarefa.getId())};

        int count = database.update(TABLE_NAME, values, selection, selectionId);
    }
}