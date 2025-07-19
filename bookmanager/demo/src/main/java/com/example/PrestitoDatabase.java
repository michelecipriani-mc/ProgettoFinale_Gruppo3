package com.example;

import java.util.ArrayList;
import java.sql.*;

public class PrestitoDatabase {
    public void insert(Prestito prestito) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "Mike261214!");
            String sql = "INSERT INTO prestito (data_prestito, data_restituzione, id_libro, id_utente) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, prestito.getData_prestito());
            ps.setDate(2, prestito.getData_restituzione());
            ps.setInt(3, prestito.getId_libro().getId_libro());
            ps.setInt(4, prestito.getId_utente().getId_utente());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println();
        }
    }

    public ArrayList<Prestito> readAll() {
        ArrayList<Prestito> listaPrestiti = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "Mike261214!");
            String sql = "SELECT * FROM prestito " +
                    " JOIN libro ON prestito.id_libro = libro.id_libro " +
                    " JOIN utente ON libro.id_utente = utente.id_utente ";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int id_prestito = rs.getInt("id_prestito");
                Date data_prestito = rs.getDate("data_prestito");
                Date data_restituzione = rs.getDate("data_restituzione");

                // Libro
                int id_libro = rs.getInt("id_libro");
                String titolo = rs.getString("titolo");
                String autore = rs.getString("autore");
                Date data_pubblicazione = rs.getDate("data_pubblicazione");
                Libro libro = new Libro(id_libro, titolo, autore, data_pubblicazione);

                // Utente
                int id_utente = rs.getInt("id_utente");
                String nome_utente = rs.getString("nome_utente");
                String email = rs.getString("email");
                Utente utente = new Utente(id_utente, nome_utente, email);

                Prestito prestito2 = new Prestito(id_prestito, libro, utente, data_prestito, data_restituzione);
                listaPrestiti.add(prestito2);
            }

        } catch (Exception e) {
            System.out.println();
        }
        return listaPrestiti;

    }

    public Prestito read(int id_prestito) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "Mike261214!");
            String sql = "SELECT * FROM prestito " +
                    "JOIN libro ON prestito.id_libro = libro.id_libro " +
                    "JOIN utente ON prestito.id_utente = utente.id_utente " +
                    "WHERE prestito.id_prestito = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id_prestito);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Date data_prestito = rs.getDate("data_prestito");
                Date data_restituzione = rs.getDate("data_restituzione");

                // Libro
                int id_libro = rs.getInt("id_libro");
                String titolo = rs.getString("titolo");
                String autore = rs.getString("autore");
                Date data_pubblicazione = rs.getDate("data_pubblicazione");
                Libro libro = new Libro(id_libro, titolo, autore, data_pubblicazione);

                // Utente
                int id_utente = rs.getInt("id_utente");
                String nome_utente = rs.getString("nome_utente");
                String email = rs.getString("email");
                Utente utente = new Utente(id_utente, nome_utente, email);

                rs.close();
                ps.close();
                conn.close();

                return new Prestito(id_prestito, libro, utente, data_prestito, data_restituzione);
            } else {
                rs.close();
                ps.close();
                conn.close();
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update( Prestito p) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "Mike261214!");
            String sql = "UPDATE prestito SET data_prestito = ?, data_restituzione = ?, id_libro = ?, id_utente = ? WHERE id_prestito = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, p.getData_prestito());
            ps.setDate(2, p.getData_restituzione());
            ps.setInt(3, p.getId_libro().getId_libro());
            ps.setInt(4, p.getId_utente().getId_utente());
            ps.setInt(5,p.getId_prestito());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Errore durante l'aggiornamento: " + e.getMessage());
        }
    }

    public void delete(int id) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "Mike261214!");
            String sql = "DELETE FROM prestito WHERE id_prestito = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Errore durante l'aggiornamento: " + e.getMessage());
        }

    }

}

