package br.univel.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.univel.db.ConexaoDB;

public class ProdutoDAO {

	private static final String SQL_BUSCA_TODOS = "SELECT * FROM PRODUTO";

	private static final String SQL_INSERT = "INSERT INTO PRODUTO(id,descricao, valor_dolar) VALUES(?,?,?)";

	private static final String SQL_DELETE = "DELETE FROM PRODUTO WHERE id=?";

	private static final String SQL_PESQUISA = " SELECT * FROM PRODUTO WHERE descricao ILIKE ?";

	public ProdutoDAO() {
		LeitorProdutoURL lp = new LeitorProdutoURL();
		List<Produto> listaB = getTodos();
		if (!(listaB.size() == 0)) {
			try {
				List<Produto> listaUrl = lp.lerProdutos();
				for (Produto produto : listaUrl) {
					for (Produto prod : listaB) {
						if (produto.getId() == prod.getId()) {
							if (!produto.getDescricao().equals(prod.getDescricao())
									|| produto.getValorDolar() != prod.getValorDolar()) {
								exclui(prod.getId().intValue());
								insere(produto);
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else
			insereTodos(lp);
	}

	private void insereTodos(LeitorProdutoURL lp) {
		try {
			List<Produto> lista = lp.lerProdutos();
			for (Produto produto : lista) {
				insere(produto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<Produto> getTodos() {

		Connection con = ConexaoDB.getInstance().getConnection();

		List<Produto> lista = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement(SQL_BUSCA_TODOS); ResultSet rs = ps.executeQuery()) {

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

	public void insere(Produto p) {
		Connection con = ConexaoDB.getInstance().getConnection();
		try (PreparedStatement ps = con.prepareStatement(SQL_INSERT);) {
			ps.setLong(1, p.getId());
			ps.setString(2, p.getDescricao());
			ps.setBigDecimal(3, p.getValorDolar());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void atualiza(int id, Produto c) {

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

}
