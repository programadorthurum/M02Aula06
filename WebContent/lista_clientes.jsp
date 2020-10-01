<%@page import="java.util.List"%>
<%@page import="m02aula06.basica.Cliente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<%
	List<Cliente> lista = (List<Cliente>) request.getAttribute("clientes");
	for(int i=0; i<lista.size(); i++){
		out.print("<tr>");
		out.print("<tr>" + "Nome: " + lista.get(i).getNome() + "<br/>" + "</tr>");  
		out.print("<tr>" + "CPF: " + lista.get(i).getCpf() + "<br/>"  + "</tr>") ;
		out.print("<tr>" + "E-mail" + lista.get(i).getEmail() + "<br/>" + "</tr>");
		out.print("<tr>" + "Data de Nascimento" + lista.get(i).getDataNascimento() + "<br/>" + "</tr>");
		out.print("<tr>" + "Sexo" + lista.get(i).getSexo() + "<br/>" + "</tr>");
		out.print("<tr>" + "Nome Social" + lista.get(i).getNomeSocial() + "<br/>" + "</tr>");
		out.print("<tr>" + "Apelido" + lista.get(i).getApelido() + "<br/>" + "</tr>");
		out.print("<tr>" + "Telefone" + lista.get(i).getTelefone() + "<br/>" + "</tr>");
		out.print("<tr>" + "<a href='ClienteController?excluir=1&id=" + lista.get(i).getId()
		+ "'>excluir</a> | <a>alterar</a>");
//		out.println("<input type=\"button\"value=\"Excluir\"> <br/>" + "</tr>");

		out.print("</tr>");
	}
	%>
	
	
</body>
</html>