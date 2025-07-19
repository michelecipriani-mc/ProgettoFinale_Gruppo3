package com.example;

import java.sql.Date;

public class Libro {
	private int id_libro;
	private String titolo;
	private String autore;
	private Date data_pubblicazione;

	public Libro(int id_libro, String titolo, String autore, Date data_pubblicazione) {
		this.id_libro = id_libro;
		this.titolo = titolo;
		this.autore = autore;
		this.data_pubblicazione = data_pubblicazione;
	}

	public int getId_libro() {
		return id_libro;
	}

	public void setId_libro(int id_libro) {
		this.id_libro = id_libro;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public Date getData_pubblicazione() {
		return data_pubblicazione;
	}

	public void setData_pubblicazione(Date data_pubblicazione) {
		this.data_pubblicazione = data_pubblicazione;
	}

	@Override
	public String toString() {
		return "Libro [id_libro=" + id_libro + ", titolo=" + titolo + ", autore=" + autore + ", data_pubblicazione="
				+ data_pubblicazione + "]";
	}
}