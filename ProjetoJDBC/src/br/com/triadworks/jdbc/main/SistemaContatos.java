package br.com.triadworks.jdbc.main;

import java.util.Calendar;
import java.util.List;

import br.com.triadworks.jdbc.dao.ContatoDAO;
import br.com.triadworks.jdbc.modelo.Contato;

public class SistemaContatos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Contato contato = new Contato();
		contato.setNome("Mac");
		contato.setEmail("mac@gmail.com");
		contato.setEndereco("Av. IOS");
		contato.setDataNascimento(Calendar.getInstance());
		
		ContatoDAO dao = new ContatoDAO();
		dao.adicionar(contato);
		
		System.out.println("Gravado!");
		
		List<Contato> lista = dao.getLista();
		
		for(Contato c: lista){
			System.out.println("Nome: " + c.getNome());
			System.out.println("Email: " + c.getEmail());
			System.out.println("Endereço: " + c.getEndereco());
			System.out.println("Data Nascimento: " + c.getDataNascimento().getTime() + "\n");
		}
	}
}
