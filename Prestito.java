

import java.time.LocalDate;
import java.sql.Date;

public class Prestito {
	private int id_prestito;
    private Libro id_libro;
    private Utenti id_utente;
    private Date data_prestito;
    private Date data_restituzione;

    public Prestito(int id_prestito, Libro id_libro, Utenti id_utente, Date data_prestito,
            Date data_restituzione) {
        this.id_prestito = id_prestito;
        this.id_libro = id_libro;
        this.id_utente = id_utente;
        this.data_prestito = data_prestito;
        this.data_restituzione = data_restituzione;
    }

    public int getId_prestito() {
        return id_prestito;
    }

    public void setId_prestito(int id_prestito) {
        this.id_prestito = id_prestito;
    }

    public Libro getId_libro() {
        return id_libro;
    }

    public void setId_libro(Libro id_libro) {
        this.id_libro = id_libro;
    }

    public Utenti getId_utente() {
        return id_utente;
    }

    public void setId_utente(Utenti id_utente) {
        this.id_utente = id_utente;
    }

    public Date getData_prestito() {
        return data_prestito;
    }

    public void setData_prestito(Date data_prestito) {
        this.data_prestito = data_prestito;
    }

    public Date getData_restituzione() {
        return data_restituzione;
    }

    public void setData_restituzione(Date data_restituzione) {
        this.data_restituzione = data_restituzione;
    }

    @Override
    public String toString() {
        return "Prestito [id_prestito=" + id_prestito + ", id_libro=" + id_libro + ", id_utente=" + id_utente
                + ", data_prestito=" + data_prestito + ", data_restituzione=" + data_restituzione + "]";
    }
}
