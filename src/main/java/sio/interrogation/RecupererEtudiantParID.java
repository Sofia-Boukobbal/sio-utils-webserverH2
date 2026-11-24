package sio.interrogation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecupererEtudiantParID {

	public static void main(String[] args) {
		Connection connection = null;
		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:file:./db/testdb");
			// ID de l'étudiant que vous souhaitez récupérer ici le n°1
			int studentId = 1;
			// Requête SQL pour récupérer un étudiant par ID
			String selectQuery = "SELECT * FROM etudiants WHERE id = ?";
			PreparedStatement preparedStatement =
					connection.prepareStatement(selectQuery);
			preparedStatement.setInt(1, studentId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nom = resultSet.getString("nom");
				System.out.println("ID de l'étudiant : " + id);
				System.out.println("Nom de l'étudiant : " + nom);
			} else {
				System.out.println("Aucun étudiant trouvé avec l'ID " + studentId);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
