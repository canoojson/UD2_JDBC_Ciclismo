package Ejercicios;

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

public class Ej04 {
	public static void main(String[] args) {
		PreparedStatement pstmt = null;
		Integer result;
		String sql = null;
		Connection con = null;
		Scanner tec = new Scanner(System.in);

		ResultSet rs = null;

		try {
			Properties propiedades = new Properties();
			propiedades.load(new FileInputStream("Configuracion\\propiedadesCiclismo"));
			DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
			con = ds.getConnection();
			int premio = 0;
			System.out.println("-----------------------CONEXION REALIZADA-----------------------");
			
						
			sql = "Select premio from maillot where tipo=?";
			
			pstmt = con.prepareStatement(sql);
			
			System.out.println("Tipo maillot: ");
			
			String tipo = tec.nextLine();
			
			pstmt.setString(1,tipo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				premio = rs.getInt(1);
			}
			
			

			sql = "UPDATE maillot set premio=? where tipo=?";

			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, premio + 50);
			
			pstmt.setString(2, tipo);
			
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(con!=null && !con.isClosed()) {
					con.close();
				}
				System.out.println("---------------------FIN DE LA CONEXION----------------------");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
}
