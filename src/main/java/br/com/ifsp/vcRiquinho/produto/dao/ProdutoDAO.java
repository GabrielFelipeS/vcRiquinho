package br.com.ifsp.vcRiquinho.produto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.ifsp.vcRiquinho.base.interfaces.DAO;
import br.com.ifsp.vcRiquinho.produto.dto.DTOProduto;

public class ProdutoDAO implements IProdutoDAO {
	private Connection conn;

	public ProdutoDAO(Connection conn) {
		this.conn = conn;
	}

	private DTOProduto createDTOProduto(ResultSet rs) throws SQLException {
		Integer id = rs.getInt("id_produto");
		Integer carencia = rs.getInt("carencia");
		String tipo_produto = rs.getString("tipo_produto");
		String nome = rs.getString("nome");
		String descricao = rs.getString("descricao");
		Double rendimentoMensal = rs.getDouble("rendimento_mensal");

		return new DTOProduto(id, carencia, tipo_produto, nome, descricao, rendimentoMensal);
	}

	private void setIntOrNull(PreparedStatement pst, int index, Integer integer) throws SQLException {
		if(integer == null) {
			pst.setNull(index, java.sql.Types.INTEGER);
		} else {				
			pst.setInt(index, integer);
		}
	}
	
	@Override
	public List<DTOProduto> findAll() {
		return findWhere("1=1");
	}

	@Override
	public List<DTOProduto> findWhere(String where) {
		List<DTOProduto> list = new ArrayList<DTOProduto>();
		try (Statement st = conn.createStatement()) {
			st.execute("SELECT id_produto, carencia, tipo_produto, nome, descricao, rendimento_mensal FROM produto "
						+ "WHERE " + where);
			try (ResultSet rs = st.getResultSet()) {

				while (rs.next()) {
					DTOProduto dto = createDTOProduto(rs);
					list.add(dto);
				}
			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public DTOProduto insert(DTOProduto dto) {
		try (PreparedStatement pst = conn.prepareStatement(
				"INSERT INTO produto (nome, descricao, rendimento_mensal, carencia, tipo_produto)" + "	VALUES  (?, ?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS)) {
			pst.setString(1, dto.nome());
			pst.setString(2, dto.descricao());
			pst.setDouble(3, dto.rendimentoMensal());
			this.setIntOrNull(pst, 4, dto.carencia());
			pst.setObject(5, dto.tipo_produto(), java.sql.Types.OTHER);
			
			int affectedRows = pst.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("Falha na criação do produto, nenhuma linha afetada.");
			}

			try (ResultSet generatedKeysProduto = pst.getGeneratedKeys()) {
				if (generatedKeysProduto.next()) {
					Integer id_produto = generatedKeysProduto.getInt("id_produto");
					
					return findBy(id_produto);
				} else {
					throw new SQLException("Falha na criação do produto, nenhum ID obtido.");
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}


	public DTOProduto insertInProdutoConta(Integer id_produto, DTOProduto dto) throws SQLException {
		try (PreparedStatement pst2 = conn
				.prepareStatement("INSERT INTO produto_conta (id_produto, carencia, tipo_produto)"
						+ "	VALUES  (?, ?, CAST(? AS TIPO_PRODUTO))", Statement.RETURN_GENERATED_KEYS)) {

			pst2.setInt(1, id_produto);
			pst2.setInt(2, dto.carencia());
			pst2.setObject(3, dto.tipo_produto(), java.sql.Types.OTHER);

			int affectedRows = pst2.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("Falha na criação do produto, nenhuma linha afetada.");
			}

			try (ResultSet generatedKeysProdutoConta = pst2.getGeneratedKeys()) {
				if (generatedKeysProdutoConta.next()) {
					Integer idProdutoConta = generatedKeysProdutoConta.getInt("id_produto");

					return findBy(idProdutoConta);
				} else {
					throw new SQLException("Falha na criação do usuário, nenhum ID obtido.");
				}
			}
		}
	}

	@Override
	public Boolean deleteBy(Integer id) {
		try (PreparedStatement pst = conn.prepareStatement("UPDATE produto SET ativo = false WHERE id_produto = ? ")) {
			pst.setInt(1, id);
			int affectedRows = pst.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Falha na criação do usuário, nenhuma linha afetada.");
			}

			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public DTOProduto findBy(Integer id) {
		try (PreparedStatement pst = conn.prepareStatement("SELECT id_produto, carencia, tipo_produto, nome, "
				+ "descricao, rendimento_mensal "
				+ "FROM produto " 
				+ "WHERE id_produto = ?")) {

			pst.setInt(1, id);

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					DTOProduto dto = createDTOProduto(rs);
					return dto;
				}

				return null;
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public DTOProduto updateBy(DTOProduto dto) {
		updateProduto(dto);
		return findBy(dto.id());
	}

	private void updateProduto(DTOProduto dto) {
		try (PreparedStatement pst = conn.prepareStatement(
				"UPDATE produto SET nome = ?, descricao = ?, rendimento_mensal = ?, carencia = ? WHERE id_produto = ? ")) {

			pst.setString(1, dto.nome());
			pst.setString(2, dto.descricao());
			pst.setDouble(3, dto.rendimentoMensal());
			pst.setInt(4, dto.carencia());
			pst.setInt(5, dto.id());

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private void updateProdutoConta(DTOProduto dto) {
		try (PreparedStatement pst = conn.prepareStatement("UPDATE produto_conta SET carencia = ? WHERE id = ? ")) {

			pst.setInt(1, dto.carencia());
			pst.setInt(2, dto.id());

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public DTOProduto insert(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DTOProduto updateBy(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
