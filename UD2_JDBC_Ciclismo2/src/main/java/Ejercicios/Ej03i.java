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

public class Ej03i {
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
			

			sql = "SELECT e.nomeq, count(c.dorsal) from equipo e left join ciclista c on e.nomeq=c.nomeq group by e.nomeq";

			pstmt = con.prepareStatement(sql);
			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println("Equipo: " + rs.getString(1) + "\t" + rs.getInt(2));
				
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
