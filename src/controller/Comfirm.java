package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Comfirm
 */
@WebServlet("/Comfirm")
public class Comfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Comfirm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF8");
		// 呼び出し元Jspからデータ受け取り
		String user_id= request.getParameter("userid");
		String user_pwd = request.getParameter("pwd");
		String user_name = request.getParameter("name");
		String user_age = request.getParameter("age");
		String user_sex = request.getParameter("sex");
		String seibetu_num = request.getParameter("sex_num");


		/**
		 * SQL
		 */
		Connection conn = null;
		Statement stmt = null;
		//ResultSet rset = null;
		try {
			//MySQLドライバをロード
			Class.forName("com.mysql.jdbc.Driver");
			// MySQLデータベースに接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_study?user=root&password=&useUnicode=true&characterEncoding=utf8");
			// ステートメントを作成
			stmt = conn.createStatement();

			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO `m_user`(`ID_LOGIN_USER`, `PASSWORD`, `MEI_USER`, `SEIBETU`, `AGE`, `SEIBETU_CUSTOM`)");
			sb.append("VALUES ('"+user_id+"','"+user_pwd+"','"+user_name+"','"+seibetu_num+"','"+user_age+"','"+user_sex+"')");


			String sql = sb.toString();
			System.out.println("SQL:" + sql);

			// SQL発行
			int count = stmt.executeUpdate(sql);

			if (count >= 1) {

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 結果セットをクローズ
				//if (!rset.isClosed()) {
					//rset.close();
				//}

				// ステートメントをクローズ
				if (!stmt.isClosed()) {
					stmt.close();
				}

				// 接続をクローズ
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		// confirm.jsp にページ遷移
		RequestDispatcher dispatch = request.getRequestDispatcher("complete.jsp");
		dispatch.forward(request, response);
	}
}
