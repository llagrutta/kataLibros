package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import play.Logger;
import play.db.DB;

public class LibroDao {
	public Integer discount(int quantity){
		Integer result = null;
		Connection connection = DB.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select discount from Libros where quantity = ?");
			stmt.setInt(0, quantity);
			stmt.execute();
			ResultSet resultSet = stmt.getResultSet();
			resultSet.next();
			result = resultSet.getInt(0);
		} catch (SQLException e) {
			Logger.error("Getting discount",e);
		}
		return result;
	}
}
