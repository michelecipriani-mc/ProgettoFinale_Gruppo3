package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDatabase {
    public void insert(Utente utente) {
        try {
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","Mike261214!");
        String queryInsert= "INSERT INTO `utente`(`id_utente`,`nome_utente`,`email`) VALUES (?,?,?)";
        PreparedStatement ps = c.prepareStatement(queryInsert);

        ps.setInt(1, utente.getId_utente());
        ps.setString(2, utente.getNome_utente());
        ps.setString(3, utente.getEmail());

        ps.executeUpdate();

        }catch(SQLException e) {
            e.printStackTrace();
        }
	}
	
	public List<Utente> readAll() {
		List<Utente> utenti = new ArrayList<>();
		try {
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","Mike261214!");
			String querySelect = "SELECT * FROM `utente`";
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(querySelect);
			while(rs.next()) {
				int id = rs.getInt("id_utente");
				String nome = rs.getString("nome_utente");
				String email = rs.getString("email");
                Utente u = new Utente(id, nome, email);
				utenti.add(u);
			}
			
			
		}catch (SQLException e ) {
			e.printStackTrace();
		}
		return utenti;
		
	}
	
	public Utente read(int id_utente) {
		try {
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","Mike261214!");
			String querySelect = "SELECT * FROM `utente` WHERE `id_utente`=?";
			PreparedStatement ps = c.prepareStatement(querySelect);
			ps.setInt(1,id_utente);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String nomeUtente = rs.getString("nome_utente");
				String emailUtente = rs.getString("email");
				Utente u = new Utente(id_utente, nomeUtente, emailUtente);
				return u;
			}
			return null;
		}catch(SQLException e ) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public void update(Utente utente) {
		try {
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","Mike261214!");
			String queryUpdate = " UPDATE `utente` SET `nome_utente`=?,`email`=? WHERE `id_utente`=?";
			PreparedStatement ps = c.prepareStatement(queryUpdate);
			ps.setString(1, utente.getNome_utente());
			ps.setString(2, utente.getEmail());
            ps.setInt(3, utente.getId_utente());
			
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id) {
		try {
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","Mike261214!");
			String queryDelete = "DELETE FROM `utente` WHERE `id_utente`=?";
			PreparedStatement ps = c.prepareStatement(queryDelete);
			ps.setInt(1, id);
			
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
