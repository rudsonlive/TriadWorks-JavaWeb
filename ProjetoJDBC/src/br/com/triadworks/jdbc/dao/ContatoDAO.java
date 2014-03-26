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
import br.com.triadworks.jdbc.modelo.Contato;

public class ContatoDAO {
private Connection conexao;
	
	public ContatoDAO(){
		this.conexao = new ConnectionFactory().getConnection();
	}
	
	public void adicionar(Contato contato){
		
		String sql = "insert into contato " +
		             "(nome, email, endereco, dataNascimento) " +
	                 "values(?,?,?,?)";
		
		try {
			
			PreparedStatement prep = conexao.prepareStatement(sql);
			
			prep.setString(1, contato.getNome());
			prep.setString(2, contato.getEmail());
			prep.setString(3, contato.getEndereco());
			prep.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			
			prep.execute();
			prep.close();
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
	}
	
	public void altera(Contato contato){
		
		String sql = "update contato set nome = ?, email = ?," +
                     "endereco = ?, dataNascimento = ? where id = ?";
		
		try {
			PreparedStatement prep = this.conexao.prepareStatement(sql);
			prep.setString(1, contato.getNome());
			prep.setString(2, contato.getEmail());
			prep.setString(3, contato.getEndereco());
			prep.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			prep.setLong(5, contato.getId());
			
			prep.execute();
			prep.close();
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	public void deleta(Contato contato){
		
		String sql = "delete from contato where id = ?";
		
		try {
			PreparedStatement prep = this.conexao.prepareStatement(sql);
			prep.setInt(1, contato.getId());
			prep.execute();
			prep.close();
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		
	}
	public List<Contato> getLista(){
		
		try {
			
			PreparedStatement prep = this.conexao.prepareStatement("select * from contato");
			List<Contato> contatos = new ArrayList<Contato>();
			Contato contato = null;
			
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()){
			
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String endereco = rs.getString("endereco");
				
				Calendar dataDivida = Calendar.getInstance();
				dataDivida.setTime(rs.getDate("dataNascimento"));
				
				contato = new Contato();
				
				contato.setId(id);
				contato.setNome(nome);
				contato.setEmail(email);
				contato.setEndereco(endereco);
				contato.setDataNascimento(dataDivida);
				
				contatos.add(contato);
			}
			
			rs.close();
			prep.close();
			return contatos;
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
}