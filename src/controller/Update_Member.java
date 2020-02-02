package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Update_Member
 */
@WebServlet("/Update_Member")
public class Update_Member extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Update_Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());


		String user_id = request.getParameter("user_id");
		String mode = request.getParameter("proc");

		if(mode.equals("update")) {

			request.setCharacterEncoding("UTF8");

		boolean is_User_id = false;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		String login_User=request.getParameter("login_user");
		String userPass =request.getParameter("login_pass");
		String userName =request.getParameter("name");
		String userSeibetu =request.getParameter("age");
		String userAge =request.getParameter("sex");
		String seibetuCustom =request.getParameter("gender");


		try {
			// MySQLドライバをロード
			Class.forName("com.mysql.jdbc.Driver");

			// MySQLデータベースに接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_study?user=root&password=");

			// ステートメントを作成
			stmt = conn.createStatement();

			// クエリ生成
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE `m_user`");
			sb.append("SET `ID_LOGIN_USER`="+login_User+",`PASSWORD`="+userPass+",`MEI_USER`="+userName+", `SEIBETU="+userSeibetu+", `AGE="+userAge+", `SEIBETU_CUSTOM`="+seibetuCustom+"");
			sb.append("WHERE = "+user_id+"");

			String sql = sb.toString();
			System.out.println("SQL:" + sql);

			/**
			ID_USER(1)
			ID_LOGIN_USER(2)
			PASSWORD(3)
			MEI_USER (4)
			SEIBETU (5)
			AGE (6)
			SEIBETU_CUSTOM(7)
			**/

			// SQL発行
			rset = stmt.executeQuery(sql);


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 結果セットをクローズ
				if (!rset.isClosed()) {
					rset.close();
				}

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
		//
		if (is_User_id) {


			System.out.println("ユーザが存在しました。");
			// result.jsp にページ遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("menu.jsp");
			dispatch.forward(request, response);

		} else {
			// Jspに渡すデータセット
			request.setAttribute("fromServlet", "ユーザーが存在しません。");
			System.out.println("ユーザーが存在しません。");
			RequestDispatcher dispatch = request.getRequestDispatcher("update_member.jsp");
			dispatch.forward(request, response);
		}
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
