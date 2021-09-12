package br.edu.ifpb.dac.atividade.jsf.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	public Conexao() {

	}

	public static Connection abrirConexao() {
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection("jdbc:postgresql://db/docker",
                               "postgres",
                              "123456");
//	return DriverManager.getConnection("jdbc:postgresql://localhost:5434/dac",
//		"jose","12345");

		} catch (SQLException | ClassNotFoundException e) {
			System.out.print(e);
			return null;
		}
	}

	public static void CloseConecxao(Connection con) {
		try {
			con.close();
		} catch (Exception e) {
			System.out.print(e);
			// TODO: handle exception
		}
	}
}
