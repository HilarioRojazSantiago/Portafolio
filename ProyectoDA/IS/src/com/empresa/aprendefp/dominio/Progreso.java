package com.empresa.aprendefp.dominio;

public class Progreso {
	
	private long id;
    private int NumEjer;
    private int NumTutos;
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public int getNumEjer() {
        return NumEjer;
    }
    
    public void setNumEjer(int ejer) {
    	this.NumEjer=ejer;
    }
    
    public long getNumTutos() {
        return NumTutos;
    }
    
    public void setNumTutos(int ejer) {
    	this.NumTutos=ejer;
    }
}
