package Ejemplos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class Ejemplo07_transaccion {
	public static void main(String[] args) {
		PreparedStatement pstmt = null;
		Integer result;
		String sql = null;
		Connection con = null;
		Scanner tec = new Scanner(System.in);
		ResultSet rs = null;
		
		//Lectura de director
		System.out.println("Nombre del director: ");
		String vnombre = tec.nextLine();
		System.out.println("Telefono del director: ");
		String vtelef = tec.nextLine();
		System.out.println("DNI del director: ");
		String vdni = tec.nextLine();
		
		//Lectura de equipo
		System.out.println("Nombre del equipo: ");
		String vnomeq = tec.nextLine();
		
		try {
			Properties propiedades = new Properties();
			propiedades.load(new FileInputStream("Configuracion\\propiedadesCiclismo"));
			DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
			con = ds.getConnection();
			con.setAutoCommit(false);
			System.out.println("Conectado");
			sql = "INSERT INTO director values(?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vnombre);
			pstmt.setString(2, vdni);
			pstmt.setString(3, vtelef);
			pstmt.executeUpdate();
			sql = "INSERT INTO equipo values(?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vnomeq);
			pstmt.setString(2, vnombre);
			pstmt.executeUpdate();
			
			con.commit();
			
			con.setAutoCommit(true);
			
		}catch(SQLException e) {
			System.out.println("Code: " + e.getErrorCode());
			System.out.println("sqlState: " + e.getSQLState());
			System.out.println("Error Message: " + e.getMessage());
			e.printStackTrace();
			try {
				con.rollback();
				con.setAutoCommit(true);
			}catch(SQLException el) {
				el.printStackTrace();
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(con!= null && !con.isClosed()) {
					con.close();
				}
				System.out.println("Desconectado");
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
}
