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
 * Servlet implementation class LoginSample
 */
@WebServlet("/LoginSample")
public class LoginSample extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginSample() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF8");

		// 呼び出し元Jspからデータ受け取り
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");

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
			sb.append("WHERE id_login_user = '" + id + "'");
			String sql = sb.toString();
			System.out.println("SQL:" + sql);

			// SQL発行
			rset = stmt.executeQuery(sql);

			// 取得レコード数分、処理を繰り返しログインチェックを行う
			while (rset.next()) {
				if (id != null && pass != null
						&& id.equals(rset.getString(1)) && pass.equals(rset.getString(2))) {
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

		// ログインチェック
		if (isLogin) {
			// Jspに渡すデータセット
			request.setAttribute("fromServlet", "ログインに成功しました。");
		} else {
			// Jspに渡すデータセット
			request.setAttribute("fromServlet", "ログインに失敗しました。");
		}

		// result.jsp にページ遷移
		RequestDispatcher dispatch = request.getRequestDispatcher("result.jsp");
		dispatch.forward(request, response);
	}
}
