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

public class Ej03j {
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
			System.out.println("-----------------------CONEXION REALIZADA-----------------------");
			
			System.out.println("Categoria del puerto: ");
			String catPuerto = tec.nextLine();
			

			sql = "SELECT distinct c.nombre from ciclista c join puerto p on c.dorsal=p.dorsal where p.categoria=?";
			
			

			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1,catPuerto);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println("Nombre: " + rs.getString(1));
				
			}

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
