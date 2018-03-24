package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexao.ConexaoMySQL;
import entidade.Product;
import entidade.User;

public class ProductDAO {

	public void inserir(Product product) {
		String inserirSQL = "INSERT INTO product(name, description, category, price, image)"
				+ "VALUES (?, ?, ?, ?, ?)";

		new ConexaoMySQL();
		try (Connection conn = ConexaoMySQL.getConexaoMySQL(); 
				PreparedStatement pst = conn.prepareStatement(inserirSQL);) {

			pst.setString(1, product.getName());
			pst.setString(2, product.getDescription());
			pst.setString(3, product.getCategory());
			pst.setFloat(4, product.getPrice());
			pst.setBytes(5, product.getImage());
			
			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o Statment " + e.toString());
		}

	}

	public ArrayList<Product> selectAll() {
		String SQL_SELECIONA_TUDO = "SELECT id, name, description, category, price, image, status FROM user";
		ArrayList<Product> productList = new ArrayList<>();

		new ConexaoMySQL();
		try (Connection conn = ConexaoMySQL.getConexaoMySQL(); 
				PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_TUDO);
				ResultSet rs = pst.executeQuery();) {

			while (rs.next()) {
				Product product = new Product();
				
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setCategory(rs.getString("category"));
				product.setPrice(rs.getFloat("price"));
				product.setImage(rs.getBytes("image"));
				product.setStatus(rs.getBoolean("status"));
				
				productList.add(product);
				
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		}

		return productList;
	}


//	public void alterar(Pessoa pessoa) {
//		String alterarSQL = "UPDATE PESSOA SET NOME=?, SOBRENOME=?, EMAIL=?, CPF_CNPJ=?, PJ_PF=? "
//				+ "WHERE ID = ?"
//				+ "";
//		
//		try (Connection conn = new CNXHSQLDB().conectar(); 
//				PreparedStatement pst = conn.prepareStatement(alterarSQL);) {
//
//			pst.setString(1, pessoa.getNome());
//			pst.setString(2, pessoa.getSobrenome());
//			pst.setString(3, pessoa.getEmail());
//			if (pessoa instanceof PessoaFisica) {
//				PessoaFisica pf = (PessoaFisica) pessoa;
//				pst.setString(4, pf.getCfp());
//				pst.setString(5, "PF");
//			} else if (pessoa instanceof PessoaJuridica) {
//				PessoaJuridica pj = (PessoaJuridica) pessoa;
//				pst.setString(4, pj.getCnpj());
//				pst.setString(5, "PJ");
//			}
//			pst.setInt(6, pessoa.getID());
//			pst.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("Erro ao executar o Statment " + e.toString());
//		}
//		
//	}

}
