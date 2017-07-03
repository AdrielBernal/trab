package br.univel.orcamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.univel.db.ConexaoDB;
import br.univel.produto.Produto;

public class OrcamentoDAO {
	private static final String SQL_BUSCA_TODOS = "SELECT * FROM ORCAMENTO";

	private static final String SQL_INSERT_PRODUTO = "INSERT INTO PRODUTO_ORCAMENTO(id_orcamento,id_produto) VALUES(?,?)";

	private static final String SQL_INSERT_ORCAMENTO = "INSERT INTO ORCAMENTO(descricao, id_cliente, valor_dolar) VALUES(?,?,?)";

	private static final String SQL_DELETE = "DELETE FROM ORCAMENTO WHERE id=? CASCADE";

	private static final String SQL_PESQUISA = " SELECT * FROM PRODUTO WHERE descricao ILIKE ?";

	private void insereProdutos(List<Produto> lista) {
		try {
			for (Produto produto : lista) {
				insereProduto(produto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<Orcamento> getTodos() {

		Connection con = ConexaoDB.getInstance().getConnection();

		List<Orcamento> lista = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement(SQL_BUSCA_TODOS); ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Orcamento c = new Orcamento();
				c.setId(rs.getLong(1));
				c.setDescricao(rs.getString(2));
				c.setValorTotalDolar(rs.getBigDecimal(3));
				lista.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	public void insereProduto(Produto p) {
		Connection con = ConexaoDB.getInstance().getConnection();
		try (PreparedStatement ps = con.prepareStatement(SQL_INSERT_PRODUTO);) {
			ps.setLong(1, p.getId());
			ps.setString(2, p.getDescricao());
			ps.setBigDecimal(3, p.getValorDolar());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void exclui(int id) {
		Connection con = ConexaoDB.getInstance().getConnection();
		try (PreparedStatement ps = con.prepareStatement(SQL_DELETE);) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Produto> pesquisa(String filtro) {

		Connection con = ConexaoDB.getInstance().getConnection();

		List<Produto> lista = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement(SQL_PESQUISA)) {

			ps.setString(1, "%" + filtro + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Produto c = new Produto();
				c.setId(rs.getLong(1));
				c.setDescricao(rs.getString(2));
				c.setValorDolar(rs.getBigDecimal(3));
				lista.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	public void insere(Orcamento c) {
		Connection con = ConexaoDB.getInstance().getConnection();
		try (PreparedStatement ps = con.prepareStatement(SQL_INSERT_ORCAMENTO);) {
			ps.setLong(1, c.getId());
			ps.setString(2, c.getDescricao());
			ps.setBigDecimal(3, c.getValorTotalDolar());
			ps.executeUpdate();
			insereProdutos(c.getProdutos());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
