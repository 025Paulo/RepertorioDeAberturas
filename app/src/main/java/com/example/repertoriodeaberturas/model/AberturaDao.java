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
    void inserir(Abertura abertura);

    @Update
    void atualizar(Abertura abertura);

    @Delete
    void deletar(Abertura abertura);

    @Query("SELECT * FROM aberturas WHERE id = :id")
    Abertura buscarPorId(int id);

    @Query("SELECT * FROM aberturas ORDER BY nome ASC")
    List<Abertura> listarPorNome();

    @Query("SELECT * FROM aberturas ORDER BY categoria ASC")
    List<Abertura> listarPorCategoria();
}