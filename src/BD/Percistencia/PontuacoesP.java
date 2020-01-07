package BD.Percistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import BD.Entidade.PontuacoesE;

public class PontuacoesP {
	public int Incluir(PontuacoesE pontuacao) throws SQLException
	{
		String sql;
		PreparedStatement comando;
		
		Connection conexao = Conexao.getConexao();
		
		// obtendo o pr�ximo ID
		int proximoID = 0;
		sql = "SELECT MAX(id+1) AS proximo FROM pontuacoes";
		comando = conexao.prepareStatement(sql);
		ResultSet dados = comando.executeQuery();
		if (dados.next()) proximoID = dados.getInt("proximo");
		dados.close();
		
		
		sql = "INSERT INTO pontuacoes (id, nome, pontuacao, data) "
				+ "VALUES (?, ?, ?, ?)";
		comando =  conexao.prepareStatement(sql);
		comando.setInt(1,proximoID);
		comando.setString(2,pontuacao.nome);
		comando.setInt(3,pontuacao.pontuacao);
                comando.setDate(4,new java.sql.Date(pontuacao.data.getTime()));
		comando.executeUpdate();
		conexao.close();

		return proximoID;
	}
		
	public ArrayList<PontuacoesE> Listar() throws SQLException{
            ArrayList<PontuacoesE> lista = new ArrayList<PontuacoesE>();
            Connection conexao = Conexao.getConexao();
            String sql = "SELECT id, nome, pontuacao, data FROM pontuacoes ORDER BY pontuacao DESC ";
            ResultSet dados = conexao.createStatement().executeQuery(sql);
            int i = 0;
            while(dados.next() && i<10){
                    lista.add(Instanciar(dados));
                    i++;
            }
            conexao.close();
            return lista;
	}
	
		
	// mapeamento Objeto-Relacional
	private PontuacoesE Instanciar(ResultSet dados) throws SQLException
	{
		PontuacoesE pontuacao = null;
		pontuacao = new PontuacoesE();
		pontuacao.id = dados.getInt("id");
		pontuacao.nome = dados.getString("nome");
		pontuacao.pontuacao = dados.getInt("pontuacao");
                pontuacao.data = dados.getDate("data");
		return pontuacao;
	}

}
