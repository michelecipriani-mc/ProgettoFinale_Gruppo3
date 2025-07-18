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
    UtenteDatabase UtenteDatabase = new UtenteDatabase();
    PrestitoDatabase PrestitoDatabase = new PrestitoDatabase();

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
                        stringScanner.nextLine(); // Consuma newline
                        String nome = stringScanner.nextLine();
                        System.out.print("Inserisci email: ");
                        String email = stringScanner.nextLine();
                        Utente nuovoUtente = new Utente(0, nome, email);
                        UtenteDatabase.insert(nuovoUtente);
                    case 2:
                        // Modifica di un utente esistente
                        Scanner s2 = new Scanner(System.in);
                        Scanner s = new Scanner(System.in);
                        System.out.println("Inserisci il nome utente: ");
                        nome = s2.nextLine();
                        System.out.println("Inserisci email: ");
                        email = s2.nextLine();

                        System.out.println("Inseriscila l id dell attore da aggiornare: ");
                        int id_utente = s.nextInt();

                        Utente u = new Utente(id_utente, nome, email);
                        UtenteDatabase.update(u);

                        break;
                    case 3:
                        // Cancellazione di un utente
                        System.out.print("Inserisci ID utente da cancellare: ");
                        id_utente = intScanner.nextInt();
                        UtenteDatabase.delete(id_utente);

                        break;
                    case 4:
                        // Visualizzazione di tutti gli utenti
                        UtenteDatabase.readAll();
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
                        System.out.println("Inserisci il titolo: ");
                        String titolo1 = s2.nextLine();
                        System.out.println("Inserisci il autore: ");
                        String autore1 = s2.nextLine();
                        System.out.println("Inserisci la data pub: ");
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
                        // Registrazione di un nuovo prestito
                        System.out.print("Inserisci ID Utente: ");
                        int idUtente = intScanner.nextInt();
                        Utente utente = UtenteDatabase.getUtenteById(idUtente);
                        if (utente == null) {
                            System.out.println("Utente con ID " + idUtente + " non trovato.");
                            break;
                        }

                        System.out.print("Inserisci ID Libro: ");
                        int idLibro = intScanner.nextInt();
                        Libro libro = LibroDatabase.getLibroById(idLibro);
                        if (libro == null) {
                            System.out.println("Libro con ID " + idLibro + " non trovato.");
                            break;
                        }

                        // Controllo: il libro non deve essere già in prestito
                        if (PrestitoDatabase.isLibroInPrestito(libro)) {
                            System.out.println("Errore: Il libro '" + libro.getTitolo() + "' è già in prestito.");
                            break;
                        }

                        // Data del prestito impostata a oggi
                        LocalDate dataPrestito = LocalDate.now();

                        // Creazione e inserimento del nuovo prestito
                        Prestito nuovoPrestito = new Prestito(0, libro, utente, dataPrestito, null);
                        PrestitoDatabase.insert(nuovoPrestito);
                        System.out.println("Prestito registrato con successo per il libro '" + libro.getTitolo()
                                + "' a " + utente.getNome_utente() + ".");
                        break;
                    case 2:
                        // Modifica di un prestito (es. data restituzione)
                        System.out.print("Inserisci ID Prestito da modificare: ");
                        int idPrestito = intScanner.nextInt();
                        
                        Prestito nuovoPrestito = new Prestito( libro, utente, dataPrestito, null);
                        PrestitoDatabase.update(idPrestito, nuovoPrestito);
                        System.out.println("Prestito aggiornato con successo!");
                        break;
                    case 3:
                        // Eliminazione di un prestito
                        System.out.println("Inserisci id prestito da eliminare");
                        int prestitoDaEliminare = intScanner.nextInt();
                        PrestitoDatabase.delete(idPrestito);

                        break;
                    case 4:
                        // Visualizzazione di tutti i prestiti
                        titoDatabase.readAll();

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