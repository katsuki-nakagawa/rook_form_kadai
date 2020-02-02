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

		String login_User="";
		String userPass ="";
		String userName ="";
		String userSeibetu ="";
		String userAge ="";
		String seibetuCustom ="";
		try {
			// MySQLドライバをロード
			Class.forName("com.mysql.jdbc.Driver");

			// MySQLデータベースに接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_study?user=root&password=");

			// ステートメントを作成
			stmt = conn.createStatement();

			// クエリ生成
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT *");
			sb.append("FROM m_user ");
			sb.append("WHERE id_user = '" + user_id + "'");
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

/*			login_User = rset.getString("ID_LOGIN_USER");
			userPass = rset.getString("PASSWORD");
			userName = rset.getString("MEI_USER");
			userSeibetu = rset.getString("SEIBETU");
			userAge = rset.getString("AGE");
			seibetuCustom = rset.getString("SEIBETU_CUSTOM");*/


			// 取得レコード数分、処理を繰り返しログインチェックを行う
			while (rset.next()) {
				if (user_id != null && user_id.equals(rset.getString(1))){
					is_User_id = true;
					login_User = rset.getString("ID_LOGIN_USER");
					userPass = rset.getString("PASSWORD");
					userName = rset.getString("MEI_USER");
					userSeibetu = rset.getString("SEIBETU");
					userAge = rset.getString("AGE");
					seibetuCustom = rset.getString("SEIBETU_CUSTOM");
				}
			}
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
			// Jspに渡すデータセット
			//request.setAttribute("fromServlet", "ユーザが存在しました。");
			request.setAttribute("id_user", login_User);
			request.setAttribute("user_pass", userPass);
			request.setAttribute("user_name", userName);
			request.setAttribute("user_seibetu", userSeibetu);
			request.setAttribute("user_age", userAge);
			request.setAttribute("seibetu_custom", seibetuCustom);

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
