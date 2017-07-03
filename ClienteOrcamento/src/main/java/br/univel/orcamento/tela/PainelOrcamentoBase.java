package br.univel.orcamento.tela;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PainelOrcamentoBase extends JPanel {

	protected static final String CARREGADO_PARA_ALTERACAO = "Carregado para alteração";
	// private JPanel contentPane;
	protected JTextField txfId;
	protected JTextField txfDescricao;
	protected JTextField txfIdCliente;
	protected JTable table;
	protected JButton btnNovo;
	protected JButton btnSalvar;
	protected JButton btnExcluir;
	protected JLabel labelAlerta;
	private JMenuBar menuBar;
	private JMenu mnArquivo;
	protected JMenuItem mntmImprimir;
	protected JMenuItem mntmExportarPdf;
	protected JButton btnExportarEmPdf;

	/**
	 * Create the panel.
	 */
	public PainelOrcamentoBase() {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		menuBar = new JMenuBar();
		// setJMenuBar(menuBar);

		mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);

		mntmImprimir = new JMenuItem("Imprimir");
		mnArquivo.add(mntmImprimir);

		mntmExportarPdf = new JMenuItem("Exportar PDF");
		mnArquivo.add(mntmExportarPdf);
		// contentPane = new JPanel();
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		// setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		this.setLayout(gbl_contentPane);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		this.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblIdOrcamento = new JLabel("ID Or\u00E7amento");
		GridBagConstraints gbc_lblIdOrcamento = new GridBagConstraints();
		gbc_lblIdOrcamento.anchor = GridBagConstraints.EAST;
		gbc_lblIdOrcamento.insets = new Insets(0, 0, 5, 5);
		gbc_lblIdOrcamento.gridx = 0;
		gbc_lblIdOrcamento.gridy = 0;
		panel.add(lblIdOrcamento, gbc_lblIdOrcamento);

		txfId = new JTextField();
		GridBagConstraints gbc_txfId = new GridBagConstraints();
		gbc_txfId.anchor = GridBagConstraints.WEST;
		gbc_txfId.insets = new Insets(0, 0, 5, 5);
		gbc_txfId.gridx = 1;
		gbc_txfId.gridy = 0;
		panel.add(txfId, gbc_txfId);
		txfId.setColumns(5);

		labelAlerta = new JLabel(CARREGADO_PARA_ALTERACAO);
		labelAlerta.setForeground(Color.RED);
		GridBagConstraints gbc_labelAlerta = new GridBagConstraints();
		gbc_labelAlerta.anchor = GridBagConstraints.WEST;
		gbc_labelAlerta.insets = new Insets(0, 0, 5, 0);
		gbc_labelAlerta.gridx = 2;
		gbc_labelAlerta.gridy = 0;
		panel.add(labelAlerta, gbc_labelAlerta);

		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o");
		GridBagConstraints gbc_lblDescricao = new GridBagConstraints();
		gbc_lblDescricao.anchor = GridBagConstraints.EAST;
		gbc_lblDescricao.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescricao.gridx = 0;
		gbc_lblDescricao.gridy = 1;
		panel.add(lblDescricao, gbc_lblDescricao);

		txfDescricao = new JTextField();
		GridBagConstraints gbc_txfDescricao = new GridBagConstraints();
		gbc_txfDescricao.gridwidth = 2;
		gbc_txfDescricao.insets = new Insets(0, 0, 5, 5);
		gbc_txfDescricao.fill = GridBagConstraints.HORIZONTAL;
		gbc_txfDescricao.gridx = 1;
		gbc_txfDescricao.gridy = 1;
		panel.add(txfDescricao, gbc_txfDescricao);
		txfDescricao.setColumns(10);

		JLabel lblIdCliente = new JLabel("ID Cliente");
		GridBagConstraints gbc_lblIdCliente = new GridBagConstraints();
		gbc_lblIdCliente.anchor = GridBagConstraints.EAST;
		gbc_lblIdCliente.insets = new Insets(0, 0, 0, 5);
		gbc_lblIdCliente.gridx = 0;
		gbc_lblIdCliente.gridy = 2;
		panel.add(lblIdCliente, gbc_lblIdCliente);

		txfIdCliente = new JTextField();
		GridBagConstraints gbc_txfIdCliente = new GridBagConstraints();
		gbc_txfIdCliente.gridwidth = 2;
		gbc_txfIdCliente.insets = new Insets(0, 0, 0, 5);
		gbc_txfIdCliente.anchor = GridBagConstraints.WEST;
		gbc_txfIdCliente.gridx = 1;
		gbc_txfIdCliente.gridy = 2;
		panel.add(txfIdCliente, gbc_txfIdCliente);
		txfIdCliente.setColumns(5);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		this.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		btnNovo = new JButton("Novo");
		GridBagConstraints gbc_btnNovo = new GridBagConstraints();
		gbc_btnNovo.anchor = GridBagConstraints.EAST;
		gbc_btnNovo.insets = new Insets(0, 0, 0, 5);
		gbc_btnNovo.gridx = 0;
		gbc_btnNovo.gridy = 0;
		panel_1.add(btnNovo, gbc_btnNovo);

		btnSalvar = new JButton("Salvar");
		GridBagConstraints gbc_btnSalvar = new GridBagConstraints();
		gbc_btnSalvar.insets = new Insets(0, 0, 0, 5);
		gbc_btnSalvar.gridx = 1;
		gbc_btnSalvar.gridy = 0;
		panel_1.add(btnSalvar, gbc_btnSalvar);

		btnExcluir = new JButton("Excluir");
		GridBagConstraints gbc_btnExcluir = new GridBagConstraints();
		gbc_btnExcluir.insets = new Insets(0, 0, 0, 5);
		gbc_btnExcluir.anchor = GridBagConstraints.WEST;
		gbc_btnExcluir.gridx = 2;
		gbc_btnExcluir.gridy = 0;
		panel_1.add(btnExcluir, gbc_btnExcluir);

		btnExportarEmPdf = new JButton("Exportar em PDF");
		GridBagConstraints gbc_btnExportarEmPdf = new GridBagConstraints();
		gbc_btnExportarEmPdf.anchor = GridBagConstraints.EAST;
		gbc_btnExportarEmPdf.gridx = 3;
		gbc_btnExportarEmPdf.gridy = 0;
		panel_1.add(btnExportarEmPdf, gbc_btnExportarEmPdf);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		this.add(scrollPane, gbc_scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

	}

}
