package br.univel.orcamento.tela;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.List;

import br.univel.orcamento.Orcamento;
import br.univel.orcamento.OrcamentoModel;
import br.univel.produto.Produto;
import br.univel.produto.ProdutoDAO;
import br.univel.produto.ProdutoModel;
import br.univel.report.ReportManager;

public class PainelOrcamento extends PainelOrcamentoBase {

	private Orcamento contatoSelecionado;

	private OrcamentoModel modelo;

	private ProdutoModel modeloProd;

	public PainelOrcamento() {
		super();
		limparCampos();
		configurarBotoes();
		configuraTabela();
	}

	private void configuraTabela() {

		ProdutoDAO dao = new ProdutoDAO();
		List<Produto> lista = dao.getTodos();

		this.modeloProd = new ProdutoModel(lista);
		super.table.setModel(modeloProd);

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
//				excluir();
			}
		});
		super.btnExportarEmPdf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exportarPDF();
			}
		});
	}

	protected void exportarPDF() {
		ReportManager rm = new ReportManager();
		rm.exportarCustom(contatoSelecionado);
	}

	protected void salvar() {
		if (contatoSelecionado == null) {
			Orcamento c = new Orcamento();

			String strId = txfId.getText().trim();
			String strDesc = txfDescricao.getText().trim();
			String stridCliente = txfIdCliente.getText().trim();

			c.setId(Long.parseLong(strId));
			c.setDescricao(strDesc);
			c.setIdCliente(Integer.parseInt(stridCliente));
			c.setProdutos(modeloProd.getProdutos());

			this.modelo.adicionar(c);

			limparCampos();

		}
	}

	protected void novo() {
		this.contatoSelecionado = null;

		limparCampos();
	}

	private void limparCampos() {
		super.labelAlerta.setText("");
		super.txfId.setText("");
		super.txfDescricao.setText("");
		super.txfIdCliente.setText("");

		super.btnExcluir.setEnabled(false);
	}
}
