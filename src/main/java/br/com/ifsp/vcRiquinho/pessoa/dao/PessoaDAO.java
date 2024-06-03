package br.com.ifsp.vcRiquinho.pessoa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.com.ifsp.vcRiquinho.pessoa.dto.DTOPessoa;

public class PessoaDAO implements IPessoaDAO{
	private Connection conn;

	public PessoaDAO(Connection conn) {
		this.conn = conn;
	}

	private DTOPessoa createDTOPessoa(ResultSet rs) throws SQLException {
		Integer id = rs.getInt("id");
		String documentoTitular = rs.getString("documento_titular");
		String nome = rs.getString("nome");
		String email = rs.getString("email");
		String tipo_pessoa = rs.getString("tipo_pessoa");

		return new DTOPessoa(id, documentoTitular, nome, email, tipo_pessoa);
	}

	@Override
	public List<DTOPessoa> findAll() {
		List<DTOPessoa> list = new LinkedList<DTOPessoa>();
		try (Statement st = conn.createStatement()) {
			st.execute("SELECT * FROM pessoa");
			try (ResultSet rs = st.getResultSet()) {

				while (rs.next()) {
					DTOPessoa dto = createDTOPessoa(rs);
					list.add(dto);
				}
			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public DTOPessoa insert(DTOPessoa dto) {
		try (PreparedStatement pst = conn
				.prepareStatement("INSERT INTO pessoa (documento_titular, nome, email, tipo_pessoa)"
						+ "	VALUES  (?, ?, ?,  CAST(? as TIPO_PESSOA))", Statement.RETURN_GENERATED_KEYS)) {
			pst.setString(1, dto.documento_titular());
			pst.setString(2, dto.nome());
			pst.setString(3, dto.email());
			pst.setObject(4, dto.tipo_pessoa(), java.sql.Types.OTHER);

			int affectedRows = pst.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Falha na criação da entidade pessoa, nenhuma linha afetada.");
			}

			try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					return findBy(dto.documento_titular());
				} else {
					throw new SQLException("Falha na criação da entidade pessoa, nenhum ID obtido.");
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	@Override
	public Boolean deleteBy(String id) {
		try (PreparedStatement pst = conn
				.prepareStatement("UPDATE pessoa SET ativo = false WHERE documento_titular = ? ")) {
			pst.setString(1, id);
			int affectedRows = pst.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Falha ao deletar pessoa, nenhuma linha afetada.");
			}

			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public DTOPessoa findBy(String id) {
		try (PreparedStatement pst = conn.prepareStatement("SELECT * FROM pessoa WHERE documento_titular = ?")) {
			pst.setString(1, id);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					DTOPessoa dto = createDTOPessoa(rs);
					return dto;
				}

				return null;
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public DTOPessoa update(DTOPessoa dto) {
		try (PreparedStatement pst = conn
				.prepareStatement("UPDATE pessoa SET nome = ?, email = ? WHERE documento_titular = ? ")) {

			pst.setString(1, dto.nome());
			pst.setString(2, dto.email());
			pst.setString(3, dto.documento_titular());

			pst.executeUpdate();

			return findBy(dto.documento_titular());
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
