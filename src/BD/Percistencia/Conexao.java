package BD.Percistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Conexao {

	private static String url = "jdbc:mysql://localhost/jogo";
	private static Connection conexao;
	
	private Conexao()	{ } // "esconde" construtor da classe
	
	// implementa um singleton para a conexao
	public static Connection getConexao() throws SQLException
	{
		if (conexao==null || conexao.isClosed()) // se conex�o fechada ou nula 
		{
			conexao = DriverManager.getConnection (url, "root", ""); // abre novamente			
		}
		return conexao;
	}
	
}
