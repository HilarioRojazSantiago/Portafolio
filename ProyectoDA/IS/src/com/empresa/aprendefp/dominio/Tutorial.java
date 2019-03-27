package com.empresa.aprendefp.dominio;

public class Tutorial {
	
	private long id;
    private int NumTema;
    private int NumTuto;
    private String Titulo;
    private String Tuto;
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public int getNumTema() {
        return NumTema;
    }

    public void setNumTema(int tema) {
        this.NumTema = tema;
    }

    public int getNumTuto() {
        return NumTuto;
    }

    public void setNumTuto(int ntuto) {
        this.NumTuto = ntuto;
    }
    
    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        this.Titulo = titulo;
    }
    
    public String getTuto() {
        return Tuto;
    }

    public void setTuto(String text) {
        this.Tuto = text;
    }
}
