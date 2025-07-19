package com.example;

public class Utente {
	private int id_utente;
    private String nome_utente;
    private String email;

    public Utente(int id_utente, String nome_utente, String email) {
        this.id_utente = id_utente;
        this.nome_utente = nome_utente;
        this.email = email;
    }

    public int getId_utente() {
        return id_utente;
    }

    public void setId_utente(int id_utente) {
        this.id_utente = id_utente;
    }

    public String getNome_utente() {
        return nome_utente;
    }

    public void setNome_utente(String nome_utente) {
        this.nome_utente = nome_utente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Utente [id_utente=" + id_utente + ", nome_utente=" + nome_utente + ", email=" + email + "]";
    }
}