package br.univel.orcamento;

import java.math.BigDecimal;
import java.util.List;

import br.univel.produto.Produto;

public class Orcamento {

	private Long id;

	private String descricao;

	private List<Produto> produtos;

	private BigDecimal valorTotalDolar;

	private int idCliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		for (Produto produto : produtos) {
			this.valorTotalDolar.add(this.valorTotalDolar.add(produto.getValorDolar()));
		}
		this.produtos = produtos;
	}

	public BigDecimal getValorTotalDolar() {
		return valorTotalDolar;
	}

	public void setValorTotalDolar(BigDecimal valorTotalDolar) {
		this.valorTotalDolar = valorTotalDolar;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

}
