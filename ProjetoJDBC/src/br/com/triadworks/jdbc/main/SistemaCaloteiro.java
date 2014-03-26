package br.com.triadworks.jdbc.main;

import java.util.Calendar;
import java.util.List;

import br.com.triadworks.jdbc.dao.CaloteiroDAO;
import br.com.triadworks.jdbc.modelo.Caloteiro;

public class SistemaCaloteiro {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Caloteiro caloteiro = new Caloteiro();
		caloteiro.setNome("Linux");
		caloteiro.setEmail("linux@gmail.com");
		caloteiro.setDevendo(100);
		caloteiro.setDataDivida(Calendar.getInstance());
		
		CaloteiroDAO dao = new CaloteiroDAO();
		dao.adicionar(caloteiro);
		
		System.out.println("Gravado!");
		
		List<Caloteiro> lista = dao.getLista();
		
		for(Caloteiro c: lista){
			System.out.println("Nome: " + c.getNome());
			System.out.println("Email: " + c.getEmail());
			System.out.println("Devendo: R$" + c.getDevendo());
			System.out.println("Data: " + c.getDataDivida().getTime() + "\n");
		}
	}
}
