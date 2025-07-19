package com.example;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class LibroDatabase {
	
	public void insert(Libro libro) {
		try {
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","Mike261214!");
			String queryInsert= "INSERT INTO `libro`(`titolo`,`autore`,`data_pubblicazione`) VALUES (?,?,?)";
			PreparedStatement ps = c.prepareStatement(queryInsert);
			
			ps.setString(1,libro.getTitolo());
			ps.setString(2,libro.getAutore());
			ps.setDate(3,libro.getData_pubblicazione() );
			
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Libro> readAll() {
		List<Libro> listaLibri = new ArrayList<>();
		try {
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","Mike261214!");
			String querySelect = "SELECT * FROM `libro`";
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(querySelect);
			while(rs.next()) {
				int id = rs.getInt("id_libro");
				String titolo = rs.getString("titolo");
				String autore = rs.getString("autore");
				Date data = rs.getDate("data_pubblicazione");
				Libro l = new Libro(id,titolo,autore,data);
				listaLibri.add(l);
			}
			
			
		}catch (SQLException e ) {
			e.printStackTrace();
		}
		return listaLibri;
		
	}
	
	public Libro read(int id_libro) {
		try {
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","Mike261214!");
			String querySelect = "SELECT * FROM `libro` WHERE `id_libro`=?";
			PreparedStatement ps = c.prepareStatement(querySelect);
			ps.setInt(1,id_libro);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String titolo = rs.getString("titolo");
				String autore = rs.getString("autore");
				Date data = rs.getDate("data_pubblicazione");
				Libro l = new Libro(id_libro,titolo,autore,data);
				return l;
			}
			return null;
		}catch(SQLException e ) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public void update(Libro libro, int id) {
		try {
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","Mike261214!");
			String queryUpdate = " UPDATE `libro` SET `titolo`=?,`autore`=?,`data_pubblicazione`=? WHERE `id_libro`=?";
			PreparedStatement ps = c.prepareStatement(queryUpdate);
			ps.setString(1, libro.getTitolo());
			ps.setString(2, libro.getAutore());
			ps.setDate(3, libro.getData_pubblicazione());
            ps.setInt(4, id);

			
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id) {
		try {
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","Mike261214!");
			String queryDelete = "DELETE FROM `libro` WHERE `id_libro`=?";
			PreparedStatement ps = c.prepareStatement(queryDelete);
			ps.setInt(1, id);
			
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}

