package com.example.coolrack.generalClass.SQLiteControll;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.coolrack.generalClass.Libro;

import java.util.List;

@Dao
public interface LibroDao {
    @Insert
    void setNewBook(Libro libro);

    @Delete
    void deleteBook(Libro libro);

    @Update
    void updateBook(Libro libro);

    @Query("SELECT * FROM book")
    List<Libro> getAll();

    @Query("SELECT * FROM book WHERE leyendo LIKE 1")
    List<Libro> getLeyendo();

    @Query("SELECT * FROM book WHERE identifier LIKE :identifierBook")
    Libro getLibro(String identifierBook);

    @Query("SELECT * FROM book WHERE urlOriginal LIKE :path or urlCopy LIKE :path")
    Libro getLibroForPath(String path);

}
