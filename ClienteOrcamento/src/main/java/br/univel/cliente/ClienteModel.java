package br.univel.cliente;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ClienteModel extends AbstractTableModel {

	private List<Cliente> lista;

	public ClienteModel(List<Cliente> lista) {
		this.lista = lista != null ? lista : new ArrayList<>();
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Cliente c = lista.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return c.getId();
		case 1:
			return c.getNome();
		case 2:
			return c.getCpf();
		case 3:
			return c.getTelefone();
		}
		return "Azedou";
	}

	public Cliente getCliente(int idx) {
		return lista.get(idx);
	}

	public void remover(Cliente clienteSelecionado) {
		ClienteDAO dao = new ClienteDAO();
		dao.exclui(clienteSelecionado.getId());
		this.lista = dao.getTodos();
		super.fireTableDataChanged();
	}

	public void adicionar(Cliente c) {
		ClienteDAO dao = new ClienteDAO();
		this.lista = dao.getTodos();
		super.fireTableDataChanged();
	}

}
