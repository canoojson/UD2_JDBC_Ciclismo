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

public class Ejem01 {
	public static void main(String[] args) {
		PreparedStatement pstmt = null;
		Integer result;
		String sql;
		String vcod, vtipo, vcolor = null;
		Integer vprem = null;
		Connection con = null;
		/**
		 * Insertar un nuevo maillot que nos de el usuario
		 * Codigo: RE
		 * Tipo: Regularidad
		 * Color: Azul
		 * Premio: 2000
		 */
		
		Scanner tec = new Scanner(System.in);
		System.out.println("codigo: ");
		vcod = tec.nextLine();
		System.out.println("tipo: ");
		vtipo = tec.nextLine();
		System.out.println("color: ");
		vcolor = tec.nextLine();
		System.out.println("premio: ");
		vprem = tec.nextInt();
		tec.nextLine();
		try {
			Properties propiedades = new Properties();
			propiedades.load(new FileInputStream("Configuracion\\propiedadesCiclismo"));
			DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
			con = ds.getConnection();
			System.out.println("Conectado");
			sql = "insert into maillot values(?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setObject(1, vcod);
			pstmt.setObject(2, vtipo);
			pstmt.setObject(3, vcolor);
			pstmt.setObject(4, vprem);
			
			pstmt.executeUpdate();
			
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
