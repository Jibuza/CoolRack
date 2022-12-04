package com.example.coolrack.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.coolrack.R;
import com.example.coolrack.generalClass.AdaptadorItemBook;
import com.example.coolrack.generalClass.GenerateBooks;
import com.example.coolrack.generalClass.Libro;
import com.example.coolrack.generalClass.XMLControll.XMLController;

import java.util.ArrayList;

import nl.siegmann.epublib.domain.Book;

public class Leyendo extends Fragment {
    AdaptadorItemBook adapterItem;
    RecyclerView recyclerView;
    ArrayList<Libro> listBook;


    public Leyendo() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_leyendo, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        listBook = new ArrayList<>();

        //cargar lista
        cargarLista();
        //mostrar data
        mostrarData();

        return view;
    }

    //Carga los datos de los libros a mostrar en la lista de libros
    public void cargarLista(){
        XMLController xmlController = new XMLController();
        this.listBook = xmlController.getBooks(this.getContext(),2);
    }

    //Muestra el contenido de los Libros y dicta su comportamiento al hacer click en el
    public void mostrarData(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterItem = new AdaptadorItemBook(getContext(),listBook);
        recyclerView.setAdapter(adapterItem);

        //Te redirecciona al perfil del usuario (Activity)
        adapterItem.setOnclickLister(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Libro libro = listBook.get(recyclerView.getChildAdapterPosition(view));
                // Le pasa a la actividad del perfil del libro el POJO con los datos del libro correspondiente
                startActivity(new Intent(getActivity(), com.example.coolrack.Activities.PerfilLibro.class).putExtra("objetoLibro",  libro));
            }
        });
    }
}