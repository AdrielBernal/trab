package br.univel.orcamento;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.univel.produto.Produto;

public class OrcamentoModel extends AbstractTableModel {

	private List<Orcamento> lista;

	public OrcamentoModel(List<Orcamento> lista) {
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
		Orcamento c = lista.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return c.getId();
		case 1:
			return c.getDescricao();
		case 2:
			return c.getValorTotalDolar();
		}
		return "Azedou";
	}

	public Orcamento getOrcamento(int idx) {
		return lista.get(idx);
	}

	public void remover(Produto contatoSelecionado) {
		this.lista.remove(contatoSelecionado);
		super.fireTableDataChanged();
	}

	public void adicionar(Orcamento c) {
		OrcamentoDAO dao = new OrcamentoDAO();
		dao.insere(c);
		dao.getTodos();
		super.fireTableDataChanged();
	}

}
