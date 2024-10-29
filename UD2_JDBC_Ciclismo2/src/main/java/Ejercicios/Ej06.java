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

public class Ej06 {
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
			
			System.out.println("Etapa salida: ");
			String vsalida = tec.nextLine();
			System.out.println("Etapa llegada: ");
			String vllegada = tec.nextLine();
			
			sql = "Select netapa from etapa where salida=? and llegada=?";
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			pstmt.setString(1, vsalida);
			pstmt.setString(2, vllegada);
			
			rs = pstmt.executeQuery();
			
			if(rs.first()) {
				int vnetapa = rs.getInt(1);
				
				System.out.println("Nueva llegada: ");
				String nuevaLlegada = tec.nextLine();
				
				sql = "select * from etapa where llegada=?";
				pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				
				pstmt.setString(1, nuevaLlegada);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					System.out.println("Ya existe una etapa con esa llegada");
				}else {
					System.out.println("Km: ");
					int km = tec.nextInt();
					
					sql = "update etapa set llegada=?, km=? where netapa=?";
					pstmt= con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					pstmt.setString(1, nuevaLlegada);
					pstmt.setInt(2, km);
					pstmt.setInt(3, vnetapa);
					pstmt.executeUpdate();
					System.out.println("-------------------------ETAPA MODIFICADA-----------------------");
				}
			} else {
				System.out.println("No hay etapa con esa salida/llegada");
			}
			
			
		}catch (SQLException e) {
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
