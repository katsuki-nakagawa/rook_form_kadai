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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Postでリクエストされた");
        request.setCharacterEncoding("UTF8");

        // 呼び出し元Jspからデータ受け取り
		String in_ID = request.getParameter("id");
		String in_PASS = request.getParameter("pass");
		//String error_msg = request.getParameter("エラー");

		boolean isLogin = false;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			// MySQLドライバをロード
			Class.forName("com.mysql.jdbc.Driver");

			// MySQLデータベースに接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_study?user=root&password=");

			// ステートメントを作成
			stmt = conn.createStatement();

			// クエリ生成
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT id_login_user, password ");
			sb.append("FROM m_user ");
			sb.append("WHERE id_login_user = '" + in_ID + "'");
			String sql = sb.toString();
			System.out.println("SQL:" + sql);

			// SQL発行
			rset = stmt.executeQuery(sql);

			// 取得レコード数分、処理を繰り返しログインチェックを行う
			while (rset.next()) {
				if (in_ID != null && in_PASS != null
						&& in_ID.equals(rset.getString(1)) && in_PASS.equals(rset.getString(2))) {
					isLogin = true;
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

		/**
		if(in_ID.equals("rook") &&in_PASS.equals("6009") ) {
        // result.jsp にページ遷移
        RequestDispatcher dispatch = request.getRequestDispatcher("member.jsp");
        dispatch.forward(request, response);
		}else {
			System.out.println("エラー");
		}
		**/

		// ログインチェック
		if (isLogin) {
			// Jspに渡すデータセット
			request.setAttribute("fromServlet", "ログインに成功しました。");
			System.out.println("ログインに成功しました");
			// result.jsp にページ遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("menu.jsp");
			dispatch.forward(request, response);

		} else {
			// Jspに渡すデータセット
			request.setAttribute("fromServlet", "ログインに失敗しました。");
			System.out.println("ログインに失敗しました");
			//request.setAttribute("login_error", error_msg);
			RequestDispatcher dispatch = request.getRequestDispatcher("login.jsp");
			dispatch.forward(request, response);
		}


	}

}
