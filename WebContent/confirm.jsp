<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

<%
	String disp_user_id = request.getAttribute("res_user_id").toString();
	String disp_user_pass = request.getAttribute("res_user_pass").toString();
	String disp_name = request.getAttribute("res_name").toString();
	String disp_age = request.getAttribute("res_age").toString();
	String disp_sex = request.getAttribute("res_sex").toString();
	String disp_seibetu_num = request.getAttribute("seibetu_num").toString();
%>

<title>Insert title here</title>
</head>
<body>
	<p>下記のユーザーを登録します</p>
	<p>
	<form action="./Comfirm" method="post">
		ユーザーID:<%=disp_user_id%>
		<input type="hidden" name="userid" value=<%=disp_user_id%>><br>

		ユーザーパス:<%=disp_user_pass%>
		<input type="hidden" name="pwd" value=<%=disp_user_pass%>><br>

		名前:<%=disp_name%>
		<input type="hidden" name="name" value=<%=disp_name%>><br>

		年齢:<%=disp_age%>
		<input type="hidden" name="age" value=<%=disp_age%>><br>

		性別:<%=disp_sex%>
		<input type="hidden" name="sex" value=<%=disp_sex%>><br>
		<input type="hidden" name="sex_num" value=<%=disp_seibetu_num%>>

		<input type ="submit" value='登録'>
			<a href="./member.jsp">戻る</a>

		</form>


</body>
</html>