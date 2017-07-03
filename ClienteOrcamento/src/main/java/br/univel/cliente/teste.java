package br.univel.cliente;

import java.math.BigDecimal;
import java.util.List;

import br.univel.produto.Produto;
import br.univel.produto.ProdutoDAO;

public class teste {
	public static void main(String[] args) {
		// Cliente c = new Cliente();
		// c.setNome("teste");
		// c.setCpf("teste");
		// c.setTelefone("teste");
		// ClienteDAO cd = new ClienteDAO();
		// List<Cliente> lista = cd.pesquisa("teste");
		// for (Cliente cliente : lista) {
		// System.out.println(cliente.getNome());
		// }
		ProdutoDAO pd = new ProdutoDAO();
		// Produto p = new Produto();
		// p.setDescricao("teste");
		// p.setValorDolar(new BigDecimal("100"));
		// pd.insere(p);
		List<Produto> lista = pd.getTodos();
		for (Produto produto : lista) {
			System.out.println(produto.getDescricao());
		}
	}
}
