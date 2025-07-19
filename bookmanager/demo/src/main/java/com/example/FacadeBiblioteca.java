package com.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

// Import aggiuntivi necessari per LocalDate e DateTimeParseException
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class FacadeBiblioteca {
    LibroDatabase libroDatabase = new LibroDatabase();
    UtenteDatabase utenteDatabase = new UtenteDatabase();
    PrestitoDatabase prestitoDatabase = new PrestitoDatabase();

    // Metodo principale che mostra il menu generale della biblioteca
    public void menu() {
        try (Scanner intScanner = new Scanner(System.in);
            Scanner stringScanner = new Scanner(System.in)) {

            int sceltaPrincipale;
            do {
                System.out.println("\n--- Menu Principale Biblioteca ---");
                System.out.println("'1' Gestione Utenti");
                System.out.println("'2' Gestione Libri");
                System.out.println("'3' Gestione Prestiti");
                System.out.println("'4' Esci");
                System.out.print("Scegli un'opzione: ");
                
                sceltaPrincipale = intScanner.nextInt();

                switch (sceltaPrincipale) {
                    case 1:
                        // Gestione utenti
                        menuUtente();
                        break;
                    case 2:
                        // Gestione libri
                        menuLibro();
                        break;
                    case 3:
                        // Gestione prestiti
                        menuPrestiti();
                        break;
                    case 4:
                        // Uscita dal programma
                        System.out.println("Uscita dal programma. Arrivederci!");
                        break;
                    default:
                        System.out.println("Scelta non valida. Riprova.");
                        break;
                }
            } while (sceltaPrincipale != 4);

        } catch (Exception e) {
            System.err.println("Errore nel menu principale: " + e.getMessage());
        }
    }

    // Menu per la gestione degli utenti
    private void menuUtente() {
        try (Scanner intScanner = new Scanner(System.in);
                Scanner stringScanner = new Scanner(System.in)) {

            int scelta;
            do {
                System.out.println("\n--- Menu Gestione Utenti ---");
                System.out.println("'1' Inserisci Utente");
                System.out.println("'2' Modifica Utente");
                System.out.println("'3' Cancella Utente");
                System.out.println("'4' Stampa Utenti");
                System.out.println("'5' Torna al Menu Principale");
                System.out.print("Scegli l'operazione che vuoi effettuare: ");
                scelta = intScanner.nextInt();

                switch (scelta) {
                    case 1:
                        // Inserimento di un nuovo utente
                        System.out.print("Inserisci nome utente: ");
                        String nome = stringScanner.nextLine();
                        System.out.print("Inserisci email: ");
                        String email = stringScanner.nextLine();
                        Utente nuovoUtente = new Utente(0, nome, email);
                        utenteDatabase.insert(nuovoUtente);
                        break;
                    case 2:
                        // Modifica di un utente esistente
                        Scanner s2 = new Scanner(System.in);
                        Scanner s = new Scanner(System.in);
                        System.out.println("Inserisci il nome utente: ");
                        nome = s2.nextLine();
                        System.out.println("Inserisci email: ");
                        email = s2.nextLine();

                        System.out.println("Inseriscila ID utente da aggiornare: ");
                        int id_utente = s.nextInt();

                        Utente u = new Utente(id_utente, nome, email);
                        utenteDatabase.update(u);
                        break;
                    case 3:
                        // Cancellazione di un utente
                        System.out.print("Inserisci ID utente da cancellare: ");
                        id_utente = intScanner.nextInt();
                        utenteDatabase.delete(id_utente);

                        break;
                    case 4:
                        // Visualizzazione di tutti gli utenti
                        List<Utente> utenti = utenteDatabase.readAll();
                        if (utenti.isEmpty()) {
                            System.out.println("Nessun utente trovato.");
                        } else {
                            System.out.println("Lista Utenti: ");
                            for (Utente utente : utenti) {
                                System.out.println("ID: " + utente.getId_utente());
                                System.out.println("Nome: " + utente.getNome_utente());
                                System.out.println("Email: " + utente.getEmail());
                                System.out.println("------------------------");
                            }
                        }
                        break;
                    case 5:
                        // Torna al menu principale
                        System.out.println("Tornando al menu principale...");
                        this.menu();
                        break;
                    default:
                        System.out.println("Scelta non valida. Riprova.");
                        break;
                }
            } while (scelta != 5);

        } catch (

        Exception e) {
            System.err.println("Errore nel menu utente: " + e.getMessage());
        }
    }

    // Menu per la gestione dei libri
    private void menuLibro() {
        try (Scanner intScanner = new Scanner(System.in);
                Scanner stringScanner = new Scanner(System.in)) {

            int scelta;
            do {
                System.out.println("\n--- Menu Gestione Libri ---");
                System.out.println("'1' Inserisci Libro");
                System.out.println("'2' Modifica Libro");
                System.out.println("'3' Cancella Libro");
                System.out.println("'4' Stampa Libri");
                System.out.println("'5' Torna al Menu Principale");
                System.out.print("Scegli l'operazione che vuoi effettuare: ");
                scelta = intScanner.nextInt();

                switch (scelta) {
                    case 1:
                        // Inserimento di un nuovo libro
                        System.out.print("Inserisci il titolo del libro: ");
                        stringScanner.nextLine();
                        String titolo = stringScanner.nextLine();

                        System.out.print("Inserisci l'autore del libro: ");
                        String autore = stringScanner.nextLine();

                        System.out.print("Inserisci la data di pubblicazione (dd/MM/yyyy): ");
                        String dataStr = stringScanner.nextLine();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Date d = null;
                        try {
                            d = sdf.parse(dataStr);
                        } catch (ParseException e) {
                            System.out.println("Formato data non valido. Riprova. (Es: 01/01/2023)");
                            break;
                        }
                        java.sql.Date d1 = new java.sql.Date(d.getTime());
                        int id_libro = intScanner.nextInt();
                        Libro l = new Libro(id_libro, titolo, autore, d1);
                        libroDatabase.insert(l);
                        System.out.println("Libro inserito con successo!");
                        break;
                    case 2:
                        // Modifica di un libro esistente
                        Scanner s2 = new Scanner(System.in);
                        Scanner s = new Scanner(System.in);
                        System.out.println("Inserisci il nuovo titolo: ");
                        String titolo1 = s2.nextLine();
                        System.out.println("Inserisci il nuovo autore: ");
                        String autore1 = s2.nextLine();
                        System.out.println("Inserisci la nuova data pub: ");
                        String data_publicazione = s2.nextLine();
                        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
                        Date d12 = null;
                        try {
                            d12 = sdf1.parse(data_publicazione);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        java.sql.Date d123 = new java.sql.Date(d12.getTime());

                        System.out.println("Inseriscila l id dell libro da aggiornare: ");
                        int cod_l = s.nextInt();
                        

                        Libro libro = new Libro(cod_l, titolo1, autore1, d123);
                        libroDatabase.update(libro, cod_l);

                        break;
                    case 3:
                        // Cancellazione di un libro
                        System.out.print("Inserisci ID libro da cancellare: ");
                        id_libro = intScanner.nextInt();
                        // Cancellazione di un utente
                        System.out.print("Inserisci ID utente da cancellare: ");
                        id_libro = intScanner.nextInt();
                        libroDatabase.delete(id_libro);

                        break;
                    case 4:
                        // Visualizzazione di tutti i libri
                        libroDatabase.readAll();
                        break;
                    case 5:
                        // Torna al menu principale
                        System.out.println("Tornando al menu principale...");
                        break;
                    default:
                        System.out.println("Scelta non valida. Riprova.");
                        break;
                }
            } while (scelta != 5);

        } catch (

        Exception e) {
            System.err.println("Errore nel menu libro: " + e.getMessage());
        }
    }

    // Menu per la gestione dei prestiti
    private void menuPrestiti() {
        try (Scanner intScanner = new Scanner(System.in);
                Scanner stringScanner = new Scanner(System.in)) {

            int scelta;
            do {
                System.out.println("\n--- Menu Gestione Prestiti ---");
                System.out.println("'1' Registra Nuovo Prestito");
                System.out.println("'2' Modifica Prestito (es. data restituzione)");
                System.out.println("'3' Elimina Prestito");
                System.out.println("'4' Visualizza Tutti i Prestiti");
                System.out.println("'5' Torna al Menu Principale");
                System.out.print("Scegli l'operazione che vuoi effettuare: ");
                scelta = intScanner.nextInt();

                switch (scelta) {
                    case 1:
                           System.out.println("Inserisci id prestito: ");
                           int id_prestito = intScanner.nextInt();
                           System.out.println("Inserisci data prestito: ");
                           String dataPrestito = stringScanner.nextLine();
                           SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
                           Date data = null;
                           try {
                           data = sdf1.parse(dataPrestito);
                           } catch (ParseException e) {
                           e.printStackTrace();
                           }
                           java.sql.Date d123 = new java.sql.Date(data.getTime());

                           System.out.println("Inserisci data restituzione: ");
                           String dataRestituzione = stringScanner.nextLine();
                           SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
                           Date data2 = null;
                           try {
                           data2 = sdf2.parse(dataRestituzione);
                           } catch (ParseException e) {
                           e.printStackTrace();
                           }
                           java.sql.Date d124 = new java.sql.Date(data2.getTime());

                           System.out.println("Inserisci l id dell libro che vuoi prendere in prestito: ");
                           int id_libro = intScanner.nextInt();

                           System.out.println("Inserisci l id utente da assegnare: ");
                           int id_utente = intScanner.nextInt();
                           
                           Prestito prestito = new Prestito(id_prestito, libroDatabase.read(id_libro),utenteDatabase.read(id_utente), d123, d124);
                           prestitoDatabase.insert(prestito);
                           break;

                    case 2:
                          
                           System.out.println("Inserisci data prestito: ");
                           String dataPrestito2 = stringScanner.nextLine();
                           SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy");
                           Date data3 = null;
                           try {
                           data3 = sdf3.parse(dataPrestito2);
                           } catch (ParseException e) {
                           e.printStackTrace();
                           }
                           java.sql.Date d1234 = new java.sql.Date(data3.getTime());

                           System.out.println("Inserisci data restituzione: ");
                           String dataRestituzione2 = stringScanner.nextLine();
                           SimpleDateFormat sdf4 = new SimpleDateFormat("dd/MM/yyyy");
                           Date data4 = null;
                           try {
                           data4 = sdf4.parse(dataRestituzione2);
                           } catch (ParseException e) {
                           e.printStackTrace();
                           }
                           java.sql.Date nuovaData = new java.sql.Date(data4.getTime());

                           System.out.println("Inserisci l id dell libro che vuoi prendere in prestito: ");
                           int idLibro = intScanner.nextInt();

                           System.out.println("Inserisci l id utente da assegnare: ");
                           int idUtente = intScanner.nextInt();

                           System.out.println("Inserisci il nuovo id Prestito: ");
                           int idPrestitoNuovo = intScanner.nextInt();
                           
                           Prestito prestitoAggiornato = new Prestito(idPrestitoNuovo, libroDatabase.read(idLibro),utenteDatabase.read(idUtente), d1234, nuovaData);
                           prestitoDatabase.update(prestitoAggiornato);
                        break;
                    case 3:
                        // Eliminazione di un prestito
                        System.out.println("Inserisci id prestito da eliminare");
                        int prestitoDaEliminare = intScanner.nextInt();
                        prestitoDatabase.delete(prestitoDaEliminare);

                        break;
                    case 4:
                        // Visualizzazione di tutti i prestiti
                        prestitoDatabase.readAll();

                        // Torna al menu principale
                        System.out.println("Tornando al menu principale...");
                        break;
                    default:
                        System.out.println("Scelta non valida. Riprova.");
                        break;
                }
            } while (scelta != 5);

        } catch (Exception e) {
            System.err.println("Errore nel menu prestiti: " + e.getMessage());
        }
    }
}