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

public class Ejemplo04_Select {
	public static void main(String[] args) {
		PreparedStatement pstmt = null;
		Integer result;
		String sql = null;
		Connection con = null;
		Scanner tec = new Scanner(System.in);
		int vdorsal = 22;
		
		ResultSet rs = null;
		
		try {
			Properties propiedades = new Properties();
			propiedades.load(new FileInputStream("Configuracion\\propiedadesCiclismo"));
			DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
			con = ds.getConnection();
			System.out.println("Conectado");
			
			sql = "select netapa, nombre " + "from ciclista c join etapa e on c.dorsal=e.dorsal " + "where c.dorsal=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vdorsal);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println("Numero etapa: " + rs.getString(1));
				System.out.println("Nombre: " + rs.getString(2));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
