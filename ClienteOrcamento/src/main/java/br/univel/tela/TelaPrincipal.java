package br.univel.tela;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import br.univel.cliente.tela.PainelCliente;
import br.univel.orcamento.tela.PainelOrcamento;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JButton btnOrcamento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));

		JButton btnCliente = new JButton("Cliente");
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				adicionarAbaCliente();
			}
		});
		panel.add(btnCliente);
		
		btnOrcamento = new JButton("Orcamento");
		btnOrcamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adcionarAbaOrcamento();
			}
		});
		panel.add(btnOrcamento);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
	}

	protected void adcionarAbaOrcamento() {
		JPanel painelCliente = new PainelOrcamento();

		PainelWrapper wrapper = new PainelWrapper();
		wrapper.setConteudo(painelCliente);
		wrapper.setTitulo("Cadastro de Produtos");

		wrapper.setAcaoFechar(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.remove(wrapper);
			}
		});

		tabbedPane.addTab("Produto", wrapper);
		
	}

	protected void adicionarAbaCliente() {
		JPanel painelCliente = new PainelCliente();

		PainelWrapper wrapper = new PainelWrapper();
		wrapper.setConteudo(painelCliente);
		wrapper.setTitulo("Cadastro de Clientes");

		wrapper.setAcaoFechar(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.remove(wrapper);
			}
		});

		tabbedPane.addTab("Cliente", wrapper);

	}

}
