package br.univel.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.univel.db.ConexaoDB;

public class ClienteDAO {

	private static final String SQL_BUSCA_TODOS = "SELECT * FROM CLIENTE";

	private static final String SQL_INSERT = "INSERT INTO CLIENTE(nome, cpf, telefone) VALUES(?,?,?)";

	private static final String SQL_DELETE = "DELETE FROM CLIENTE WHERE id=?";

	private static final String SQL_PESQUISA = " SELECT * FROM CLIENTE WHERE nome ILIKE ?";

	public List<Cliente> getTodos() {

		Connection con = ConexaoDB.getInstance().getConnection();

		List<Cliente> lista = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement(SQL_BUSCA_TODOS); ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Cliente c = new Cliente();
				c.setId(rs.getInt(1));
				c.setNome(rs.getString(2));
				c.setCpf(rs.getString(3));
				c.setTelefone(rs.getString(4));
				lista.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	public void insere(Cliente c) {
		Connection con = ConexaoDB.getInstance().getConnection();
		try (PreparedStatement ps = con.prepareStatement(SQL_INSERT);) {
			ps.setString(1, c.getNome());
			ps.setString(2, c.getCpf());
			ps.setString(3, c.getTelefone());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void atualiza(int id, Cliente c) {

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

	public List<Cliente> pesquisa(String filtro) {

		Connection con = ConexaoDB.getInstance().getConnection();

		List<Cliente> lista = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement(SQL_PESQUISA)) {

			ps.setString(1, "%"+filtro+"%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Cliente c = new Cliente();
				c.setId(rs.getInt(1));
				c.setNome(rs.getString(2));
				c.setCpf(rs.getString(3));
				c.setTelefone(rs.getString(4));
				lista.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

}
