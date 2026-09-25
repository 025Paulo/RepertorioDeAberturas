package com.example.repertoriodeaberturas.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AberturaDao {

    @Insert
    long inserir(Abertura abertura);

    @Update
    void atualizar(Abertura abertura);

    @Delete
    void excluir(Abertura abertura);

    @Query("SELECT * FROM aberturas ORDER BY nome ASC")
    List<Abertura> listarTodas();

    @Query("SELECT * FROM aberturas WHERE id = :id LIMIT 1")
    Abertura buscarPorId(int id);
}