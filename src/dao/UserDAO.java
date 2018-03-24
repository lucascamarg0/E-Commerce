package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexao.ConexaoMySQL;
import entidade.User;

public class UserDAO {

	public void inserir(User user) {
		String inserirSQL = "INSERT INTO user" + "(nome, sobrenome, email, password)"
				+ "VALUES (?, ?, ?, ?)";

		new ConexaoMySQL();
		try (Connection conn = ConexaoMySQL.getConexaoMySQL(); 
				PreparedStatement pst = conn.prepareStatement(inserirSQL);) {

			pst.setString(1, user.getNome());
			pst.setString(2, user.getSobrenome());
			pst.setString(3, user.getEmail());
			pst.setString(4, user.getPassword());
			
			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o Statment " + e.toString());
		}

	}

	public ArrayList<User> listaTudo() {
		String SQL_SELECIONA_TUDO = "SELECT * FROM user";
		ArrayList<User> listaDePessoas = new ArrayList<>();

		new ConexaoMySQL();
		try (Connection conn = ConexaoMySQL.getConexaoMySQL(); 
				PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_TUDO);
				ResultSet rs = pst.executeQuery();) {

			while (rs.next()) {
				// , , ,
				
				User u = new User();
				
				u.setID(rs.getInt("ID"));
				u.setNome(rs.getString("nome"));
				u.setSobrenome(rs.getString("sobrenome"));
				u.setEmail(rs.getString("email"));
				u.setAdmin(rs.getBoolean("admin"));
				
				listaDePessoas.add(u);
				
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		}

		return listaDePessoas;
	}

	public boolean authUser(User user) {
		String select = "SELECT IF(1=1, true, false) AS exist FROM user WHERE email = ? AND password = ?;";
		
		new ConexaoMySQL();
		boolean exist = false;

		try (Connection conn = ConexaoMySQL.getConexaoMySQL(); 
				PreparedStatement pst = conn.prepareStatement(select);) {
			
			pst.setString(1, user.getEmail());
			pst.setString(2, user.getPassword());
			
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				exist = rs.getBoolean("exist");
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		}
		
		return exist;
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
