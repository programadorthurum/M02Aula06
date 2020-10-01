<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro Cliente</title>
</head>
<body>
	<h1>Cadastro Cliente</h1>
	<form action="ClienteController" method="POST">
		<input type="hidden" name="action" value="salvar" /> 
		Nome: <input type="text" name="nome" required="required" /><br /> 
		CPF: <input type="text" name="cpf" required="required" /><br /> 
		E-Mail: <input type="text" name="email" required="required" /><br />
		Data de Nascimento: <input	type="text" name= "dataNascimento" required="required" /><br /> 
		Sexo: <input	type="text" name= "sexo" required="required" /><br /> 
		Nome Social: <input	type="text" name= "nomeSocial" required="required" /><br /> 
		Apelido: <input	type="text" name= "apelido" required="required" /><br /> 
		Telefone: <input	type="text" name= "telefone" required="required" /><br /> 
			<input type="submit" value="Salvar" />
	</form>

	<%
		out.print("<h2>Java</h2>");

	/*ACESSO AO BANCO DE DADOS*/
	String usu = "root";
	String sen = "root";
	String uri = "jdbc:mysql://localhost:3306/lojaunit?useTimezone=true&serverTimezone=UTC";
	String sql = "";
	Class.forName("com.mysql.jdbc.Driver");
	Connection c = DriverManager.getConnection(uri, usu, sen);


	/*ACESSA O BANCO DE DADOS E LISTA OS DADOS */
	sql = "SELECT id, email, nome, cpf, dataNascimento, sexo, nomeSocial, apelido, telefone FROM clientes";
	Statement stm = c.createStatement();
	ResultSet rs = stm.executeQuery(sql);
	%>

	<table border="1">
		<tr>
			<td>CPF</td>
			<td>Nome</td>
			<td>E-mail</td>
			<td></td>
		</tr>

		<%
			while (rs.next()) {
			out.print("<tr>");
			out.print("<td>" + rs.getString("cpf") + "</td>");
			out.print("<td>" + rs.getString("nome") + "</td>");
			out.print("<td>" + rs.getString("email") + "</td>");
			out.print("<td><a href='ClienteController.java?excluir=1&id=" + rs.getInt("id")
			+ "'>excluir</a> | <a>alterar</a> </td>");
			out.print("</tr>");
		}
		%>

	</table>
</body>
</html>