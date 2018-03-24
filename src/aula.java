
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ManageUser;
import dao.ProductDAO;
import entidade.Product;

@WebServlet("/aula")
public class aula extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public aula() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter resposta = response.getWriter();
		
		ArrayList<Product> productList = new ProductDAO().selectAll();
		request.setAttribute("productList", productList);
		
		
		String htmlFormName = request.getParameter("htmlFormName");
		
		switch (htmlFormName) {
        case "register":
        	String reg_firstname = request.getParameter("firstname");
        	String reg_lastname = request.getParameter("lastname");
        	String reg_email = request.getParameter("email");
        	String reg_passwd = request.getParameter("passwd");
        	
        	new ManageUser().insertNew(reg_firstname, reg_lastname, reg_email, reg_passwd);
            break;      
        case "login":
        	String login_email = request.getParameter("email");
        	String login_passwd = request.getParameter("password");
        	
        	resposta.print(new ManageUser().authUser(login_email, login_passwd));
        	break;      
        default: 
        	resposta.println("Another form");
        	break;
		}
		
		
		
//		Connection conn = ConexaoMySQL.getConexaoMySQL();
//		String sql = "SELECT '1' as teste;";
//
//		try {
//			PreparedStatement comando;
//			comando = conn.prepareStatement(sql.toString());
//			ResultSet resultado = comando.executeQuery();
//			if(resultado != null) {
//				while(resultado.next()) {
//					resposta.println(resultado.getString("teste"));
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
