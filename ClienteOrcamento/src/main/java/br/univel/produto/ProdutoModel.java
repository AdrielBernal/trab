package br.univel.produto;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ProdutoModel extends AbstractTableModel {

	private List<Produto> lista;

	public ProdutoModel(List<Produto> lista) {
		this.lista = lista != null ? lista : new ArrayList<>();
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Produto c = lista.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return c.getId();
		case 1:
			return c.getDescricao();
		case 2:
			return c.getValorDolar();
		}
		return "Azedou";
	}

	public Produto getProduto(int idx) {
		return lista.get(idx);
	}

	public void remover(Produto contatoSelecionado) {
		this.lista.remove(contatoSelecionado);
		super.fireTableDataChanged();
	}

	public void adicionar(Produto c) {
		this.lista.add(c);
		super.fireTableDataChanged();
	}

	public List<Produto> getProdutos() {
		return this.lista;
	}

}
