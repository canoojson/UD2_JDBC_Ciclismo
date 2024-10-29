package Ejemplos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class Ejemplo03_update {
	public static void main(String[] args) {
		PreparedStatement pstmt = null;
		Integer result;
		String sql;
		String vcod, vtipo, vcolor = null;
		Integer vprem = null;
		Connection con = null;
		Scanner tec = new Scanner(System.in);
		
		System.out.println("Nuevo premio: ");
		vprem = tec.nextInt();
		tec.nextLine();
		System.out.println("Tipo: ");
		vtipo = tec.nextLine();
		
		try {
			Properties propiedades = new Properties();
			propiedades.load(new FileInputStream("Configuracion\\propiedadesCiclismo"));
			DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
			con = ds.getConnection();
			System.out.println("Conectado");
			
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
