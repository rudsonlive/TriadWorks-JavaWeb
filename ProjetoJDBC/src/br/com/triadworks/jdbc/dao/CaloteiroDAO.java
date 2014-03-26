package br.com.triadworks.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.triadworks.jdbc.ConnectionFactory;
import br.com.triadworks.jdbc.modelo.Caloteiro;

public class CaloteiroDAO {

	private Connection conexao;
	
	public CaloteiroDAO(){
		this.conexao = new ConnectionFactory().getConnection();
	}
	
	public void adicionar(Caloteiro Caloteiro){
		
		String sql = "insert into caloteiro " +
		             "(nome, email, devendo, dataDivida) " +
	                 "values(?,?,?,?)";
		
		try {
			
			PreparedStatement prep = conexao.prepareStatement(sql);
			
			prep.setString(1, Caloteiro.getNome());
			prep.setString(2, Caloteiro.getEmail());
			prep.setInt(3, Caloteiro.getDevendo());
			prep.setDate(4, new Date(Caloteiro.getDataDivida().getTimeInMillis()));
			
			prep.execute();
			prep.close();
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
	}
	
	public void altera(Caloteiro caloteiro){
		
		String sql = "update caloteiro set nome = ?, email = ?," +
                     "devendo = ?, dataDivida = ? where id = ?";
		
		try {
			PreparedStatement prep = this.conexao.prepareStatement(sql);
			prep.setString(1, caloteiro.getNome());
			prep.setString(2, caloteiro.getEmail());
			prep.setInt(3, caloteiro.getDevendo());
			prep.setDate(4, new Date(caloteiro.getDataDivida().getTimeInMillis()));
			prep.setLong(5, caloteiro.getId());
			
			prep.execute();
			prep.close();
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	public void deleta(Caloteiro caloteiro){
		
		String sql = "delete from caloteiro where id = ?";
		
		try {
			PreparedStatement prep = this.conexao.prepareStatement(sql);
			prep.setLong(1, caloteiro.getId());
			prep.execute();
			prep.close();
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		
	}
	public List<Caloteiro> getLista(){
		
		try {
			
			PreparedStatement prep = this.conexao.prepareStatement("select * from caloteiro");
			List<Caloteiro> caloteiros = new ArrayList<Caloteiro>();
			Caloteiro caloteiro = null;
			
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()){
			
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				int devendo = rs.getInt("devendo");
				
				Calendar dataDivida = Calendar.getInstance();
				dataDivida.setTime(rs.getDate("dataDivida"));
				
				caloteiro = new Caloteiro();
				
				caloteiro.setId(id);
				caloteiro.setNome(nome);
				caloteiro.setEmail(email);
				caloteiro.setDevendo(new Integer(devendo));
				caloteiro.setDataDivida(dataDivida);
				
				caloteiros.add(caloteiro);
			}
			
			rs.close();
			prep.close();
			return caloteiros;
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
}
