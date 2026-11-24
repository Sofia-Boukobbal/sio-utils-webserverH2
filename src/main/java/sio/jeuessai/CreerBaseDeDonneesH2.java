package sio.jeuessai;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class CreerBaseDeDonneesH2 {
	
	public static void main(String[] args) {
		
		Connection connection = null;
		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:file:./db/testdb");
			Statement statement = connection.createStatement();
			statement.execute("CREATE TABLE etudiants(id INT AUTO_INCREMENT, nom VARCHAR(255), PRIMARY KEY (id))"
					);
			// Insérer des données dans la table.
			String[] noms = {"Einstein","Newton","Voyce"};
			// Parcours du tableau et insertions des données dans la table.
			PreparedStatement preparedStatement ;
			for(int i=0;i<noms.length;i++){
				String insertionSql="insert into etudiants (nom) VALUES (?)" ;
				preparedStatement = connection.prepareStatement(insertionSql);
				// Remplacement du premier paramètre ? avec la valeur lu dans le tableau
				preparedStatement.setString(1, noms[i]);
				// Exécutez la requête d'insertion
				preparedStatement.executeUpdate();
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
