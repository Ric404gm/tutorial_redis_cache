package com.example.tutoral.dto;

import lombok.Data;

@Data
public class Tutorial {
    private int id;
    private String descripcion;
    private boolean isPublished;
    public Tutorial (){

    }    

    public  Tutorial id (int id){
        this.id = id;
        return this;
    }
    public  Tutorial desc (  String descripcion){
        this.descripcion = descripcion;
        return this;
    }
     public  Tutorial isPublished (  boolean  isPublished){
        this.isPublished = isPublished;
        return this;
    }
}
