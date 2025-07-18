import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
// Import aggiuntivi necessari per LocalDate e DateTimeParseException
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class FacadeBiblioteca {
    LibroDatabase libroRepoService = new LibroDatabase();
    UtenteDatabase UtenteRepoService = new UtenteDatabase();
    PrestitoDatabase PrestitoRepoService = new LibroDatabase();

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
                        Utente nuovoUtente = new Utente(nome, email);

                        // Verifica se l'utente esiste già
                        if (UtenteDatabase.getUtenti().contains(nuovoUtente)) {
                            System.out.println("Errore: Utente con lo stesso nome e email già presente.");
                        } else {
                            UtenteDatabase.insert(nuovoUtente);
                            System.out.println("Utente inserito con successo!");
                        }
                        break;
                    case 2:
                        // Modifica di un utente esistente
                        System.out.print("Inserisci ID utente da modificare: ");
                        int id_utente = intScanner.nextInt();
                        Utente utenteEsistente = UtenteDatabase.getUtenteById(id_utente);
                        if (utenteEsistente == null) {
                            System.out.println("Utente con ID " + id_utente + " non trovato.");
                        } else {
                            System.out.println("Stai modificando l'utente: " + utenteEsistente);
                            System.out.print("Inserisci nuovo nome (lascia vuoto per non modificare): ");
                            stringScanner.nextLine();
                            String nomeAggiornato = stringScanner.nextLine();
                            if (nomeAggiornato.isEmpty()) {
                                nomeAggiornato = utenteEsistente.getNome();
                            }

                            System.out.print("Inserisci nuova email (lascia vuoto per non modificare): ");
                            String emailAggiornato = stringScanner.nextLine();
                            if (emailAggiornato.isEmpty()) {
                                emailAggiornato = utenteEsistente.getEmail();
                            }

                            Utente utenteAggiornato = new Utente(id_utente, nomeAggiornato, emailAggiornato);
                            UtenteDatabase.update(id_utente, utenteAggiornato);
                            System.out.println("Utente aggiornato con successo!");
                        }
                        break;
                    case 3:
                        // Cancellazione di un utente
                        System.out.print("Inserisci ID utente da cancellare: ");
                        id_utente = intScanner.nextInt();
                        utenteEsistente = UtenteDatabase.getUtenteById(id_utente);
                        if (utenteEsistente == null) {
                            System.out.println("Utente con ID " + id_utente + " non trovato.");
                        } else {
                            System.out.println("Confermi la cancellazione dell'utente: " + utenteEsistente + " (s/n)?");
                            stringScanner.nextLine();
                            String conferma = stringScanner.nextLine();
                            if (conferma.equalsIgnoreCase("s")) {
                                // Controllo: non si può cancellare l'utente se ha prestiti attivi
                                if (!PrestitiDatabase.getPrestitiByUtente(utenteEsistente).isEmpty()) {
                                    System.out.println(
                                            "Errore: L'utente ha prestiti attivi e non può essere cancellato.");
                                } else {
                                    UtenteDatabase.delete(id_utente);
                                    System.out.println("Utente cancellato con successo!");
                                }
                            } else {
                                System.out.println("Cancellazione annullata.");
                            }
                        }
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

        } catch (Exception e) {
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
                        Libro l = new Libro(titolo, autore, d1);
                        LibriDatabase.insert(l);
                        System.out.println("Libro inserito con successo!");
                        break;
                    case 2:
                        // Modifica di un libro esistente
                        System.out.print("Inserisci ID libro da modificare: ");
                        int id_libro = intScanner.nextInt();
                        Libro libroEsistente = LibriDatabase.getLibroById(id_libro);
                        if (libroEsistente == null) {
                            System.out.println("Libro con ID " + id_libro + " non trovato.");
                        } else {
                            System.out.println("Stai modificando il libro: " + libroEsistente);
                            System.out.print("Inserisci nuovo titolo (lascia vuoto per non modificare): ");
                            stringScanner.nextLine();
                            String titoloAggiornato = stringScanner.nextLine();
                            if (titoloAggiornato.isEmpty()) {
                                titoloAggiornato = libroEsistente.getTitolo();
                            }

                            System.out.print("Inserisci nuovo autore (lascia vuoto per non modificare): ");
                            String autoreAggiornato = stringScanner.nextLine();
                            if (autoreAggiornato.isEmpty()) {
                                autoreAggiornato = libroEsistente.getAutore();
                            }

                            System.out.print(
                                    "Inserisci nuova data di pubblicazione (dd/MM/yyyy, lascia vuoto per non modificare): ");
                            String dataAggiornataStr = stringScanner.nextLine();
                            Date dataPubblicazioneAggiornata = libroEsistente.getDataPubblicazione();
                            if (!dataAggiornataStr.isEmpty()) {
                                try {
                                    dataPubblicazioneAggiornata = sdf.parse(dataAggiornataStr);
                                } catch (ParseException e) {
                                    System.out.println("Formato data non valido. Lasciando la data originale.");
                                }
                            }

                            Libro libroAggiornato = new Libro(id_libro, titoloAggiornato, autoreAggiornato,
                                    new java.sql.Date(dataPubblicazioneAggiornata.getTime()));
                            LibriDatabase.update(id_libro, libroAggiornato);
                            System.out.println("Libro aggiornato con successo!");
                        }
                        break;
                    case 3:
                        // Cancellazione di un libro
                        System.out.print("Inserisci ID libro da cancellare: ");
                        id_libro = intScanner.nextInt();
                        libroEsistente = LibriDatabase.getLibroById(id_libro);
                        if (libroEsistente == null) {
                            System.out.println("Libro con ID " + id_libro + " non trovato.");
                        } else {
                            // Controllo: non si può cancellare il libro se è in prestito
                            if (PrestitiDatabase.isLibroInPrestito(libroEsistente)) {
                                System.out.println(
                                        "Errore: Il libro è attualmente in prestito e non può essere cancellato.");
                            } else {
                                System.out
                                        .println("Confermi la cancellazione del libro: " + libroEsistente + " (s/n)?");
                                stringScanner.nextLine();
                                String conferma = stringScanner.nextLine();
                                if (conferma.equalsIgnoreCase("s")) {
                                    LibriDatabase.delete(id_libro);
                                    System.out.println("Libro cancellato con successo!");
                                } else {
                                    System.out.println("Cancellazione annullata.");
                                }
                            }
                        }
                        break;
                    case 4:
                        // Visualizzazione di tutti i libri
                        LibriDatabase.readAll();
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

        } catch (Exception e) {
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
                        Libro libro = LibriDatabase.getLibroById(idLibro);
                        if (libro == null) {
                            System.out.println("Libro con ID " + idLibro + " non trovato.");
                            break;
                        }

                        // Controllo: il libro non deve essere già in prestito
                        if (PrestitiDatabase.isLibroInPrestito(libro)) {
                            System.out.println("Errore: Il libro '" + libro.getTitolo() + "' è già in prestito.");
                            break;
                        }

                        // Data del prestito impostata a oggi
                        LocalDate dataPrestito = LocalDate.now();

                        // Creazione e inserimento del nuovo prestito
                        Prestiti nuovoPrestito = new Prestiti(0, libro, utente, dataPrestito, null);
                        PrestitiDatabase.insert(nuovoPrestito);
                        System.out.println("Prestito registrato con successo per il libro '" + libro.getTitolo()
                                + "' a " + utente.getNome() + ".");
                        break;
                    case 2:
                        // Modifica di un prestito (es. data restituzione)
                        System.out.print("Inserisci ID Prestito da modificare: ");
                        int idPrestito = intScanner.nextInt();
                        Prestiti prestitoEsistente = PrestitiDatabase.getPrestitoById(idPrestito);

                        if (prestitoEsistente == null) {
                            System.out.println("Prestito con ID " + idPrestito + " non trovato.");
                            break;
                        }

                        System.out.println("Stai modificando il prestito: " + prestitoEsistente);
                        System.out.print("Vuoi impostare la data di restituzione? (s/n): ");
                        stringScanner.nextLine();
                        String sceltaRestituzione = stringScanner.nextLine();

                        LocalDate dataRestituzioneAggiornata = prestitoEsistente.getData_restituzione();

                        if (sceltaRestituzione.equalsIgnoreCase("s")) {
                            System.out.print("Inserisci la nuova data di restituzione (YYYY-MM-DD): ");
                            String dataRestituzioneStr = stringScanner.nextLine();
                            try {
                                dataRestituzioneAggiornata = LocalDate.parse(dataRestituzioneStr);
                                if (dataRestituzioneAggiornata.isBefore(prestitoEsistente.getData_prestito())) {
                                    System.out.println(
                                            "La data di restituzione non può essere precedente alla data di prestito. Lasciando la data originale.");
                                    dataRestituzioneAggiornata = prestitoEsistente.getData_restituzione();
                                }
                            } catch (DateTimeParseException e) {
                                System.out.println(
                                        "Formato data non valido. Lasciando la data originale. (Es: 2023-10-26)");
                            }
                        }

                        // Aggiornamento del prestito
                        Prestiti prestitoAggiornato = new Prestiti(
                                prestitoEsistente.getId_prestito(),
                                prestitoEsistente.getId_libro(),
                                prestitoEsistente.getId_utente(),
                                prestitoEsistente.getData_prestito(),
                                dataRestituzioneAggiornata);
                        PrestitiDatabase.update(idPrestito, prestitoAggiornato);
                        System.out.println("Prestito aggiornato con successo!");
                        break;
                    case 3:
                        // Eliminazione di un prestito
                        System.out.print("Inserisci ID Prestito da eliminare: ");
                        idPrestito = intScanner.nextInt();
                        prestitoEsistente = PrestitiDatabase.getPrestitoById(idPrestito);

                        if (prestitoEsistente == null) {
                            System.out.println("Prestito con ID " + idPrestito + " non trovato.");
                            break;
                        }

                        System.out.println("Confermi l'eliminazione del prestito: " + prestitoEsistente + " (s/n)?");
                        stringScanner.nextLine();
                        String confermaEliminazione = stringScanner.nextLine();
                        if (confermaEliminazione.equalsIgnoreCase("s")) {
                            PrestitiDatabase.delete(idPrestito);
                            System.out.println("Prestito eliminato con successo!");
                        } else {
                            System.out.println("Eliminazione annullata.");
                        }
                        break;
                    case 4:
                        // Visualizzazione di tutti i prestiti
                        PrestitiDatabase.readAll();
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

        } catch (Exception e) {
            System.err.println("Errore nel menu prestiti: " + e.getMessage());
        }
    }
}