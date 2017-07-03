package br.univel.cliente.tela;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import br.univel.cliente.Cliente;
import br.univel.cliente.ClienteDAO;
import br.univel.cliente.ClienteModel;
import br.univel.report.ReportManager;

public class PainelCliente extends PainelClienteBase {
	private Cliente contatoSelecionado;

	private ClienteDAO dao;

	private ClienteModel modelo;

	public PainelCliente() {
		super();
		this.dao = new ClienteDAO();
		limparCampos();
		configurarBotoes();
		configuraTabela();
	}

	private void configuraTabela() {

		ClienteDAO dao = new ClienteDAO();
		List<Cliente> lista = dao.getTodos();

		this.modelo = new ClienteModel(lista);
		super.table.setModel(modelo);

		super.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int idx = table.getSelectedRow();
					if (idx < 0) {
						System.out.println("Não há¡ linha selecionada");
					} else {
						System.out.println("Linha " + idx);
						carregarLinha(idx);
					}
				}
			}
		});

	}

	protected void carregarLinha(int idx) {
		Cliente c = this.modelo.getCliente(idx);
		this.contatoSelecionado = c;
		this.labelAlerta.setText(CARREGADO_PARA_ALTERACAO);

		super.txfNome.setText(c.getNome());
		super.txfTelefone.setText(c.getTelefone());
		super.txfCpf.setText(c.getCpf());

		super.btnExcluir.setEnabled(true);

	}

	private void configurarBotoes() {
		super.btnNovo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou Novo");
				novo();
			}
		});
		super.btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou Salvar");
				salvar();
			}
		});
		super.btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou Excluir");
				excluir();
			}
		});
	}

	protected void excluir() {
		this.modelo.remover(this.contatoSelecionado);
		limparCampos();
	}

	protected void salvar() {
		if (contatoSelecionado == null) {
			Cliente c = new Cliente();

			String strNome = txfNome.getText().trim();
			String strTelefone = txfTelefone.getText().trim();
			String strCpf = txfCpf.getText().trim();

			c.setNome(strNome);
			c.setTelefone(strTelefone);
			c.setCpf(strCpf);

			dao.insere(c);
			this.modelo.adicionar(c);

			limparCampos();

		} else {

			String strNome = txfNome.getText().trim();
			String strTelefone = txfTelefone.getText().trim();
			String strCpf=txfCpf.getText().trim();

			this.contatoSelecionado.setNome(strNome);
			this.contatoSelecionado.setTelefone(strTelefone);
			this.contatoSelecionado.setCpf(strCpf);
			dao.atualiza(contatoSelecionado);

			limparCampos();
			this.modelo.fireTableDataChanged();
		}

	}

	protected void novo() {
		this.contatoSelecionado = null;

		limparCampos();
	}

	private void limparCampos() {
		super.labelAlerta.setText("");
		super.txfNome.setText("");
		super.txfTelefone.setText("");

		super.btnExcluir.setEnabled(false);
	}
}
