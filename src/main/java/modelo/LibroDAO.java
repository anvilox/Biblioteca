package modelo;

import java.sql.*;
import java.util.ArrayList;

public class LibroDAO {

	private String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private String DB_URL = "jdbc:mysql://localhost/biblioteca";
	private String DB_USER = "bibliotecario";
	private String DB_PASS = "bibliotecario";
	private Connection conn = null;
	private Statement stm = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public LibroDAO() throws RuntimeException {
		super();
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("ERROR: failed to load MySQL JBDC driver.",e);
		} catch (SQLException e) {
			throw new RuntimeException("ERROR: failed to access database.",e);
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException("Error cerrando la conexion",e);
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new RuntimeException("Error cerrando el resultset",e);
			}
		}
	}
	
	public boolean insertar(Libro libro) throws RuntimeException {
		System.out.println("insertando");
		try {
			ps = conn.prepareStatement("insert into libros values(?,?,?)");
			ps.setInt(1,  libro.getIsbn());
			ps.setString(2, libro.getTitulo());
			ps.setString(3, libro.getAutor());
			int i = ps.executeUpdate();
			
			if (i==1) {
				return true;
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error: failed to insert libro", e);
		}
		return false;
	}
	
	public ArrayList<Libro> getLibros() throws RuntimeException {
		try {
			stm = conn.createStatement();
			String consulta = "select * from Libros";
			rs = stm.executeQuery(consulta);
			ArrayList<Libro> libros = new ArrayList<Libro>();
			while (rs.next()) {
				Libro libro = new Libro(rs.getInt("isbn"),rs.getString("titulo"),rs.getString("autor"));
				libros.add(libro);
			}
			
			return libros;
		} catch (SQLException e) {
			throw new RuntimeException("failed to create statement",e);
		}
	}
}
