<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des tâches</title>
</head>
<body>
	<a href="index.jsp">Retour au début</a><br>
	<h1><a href="TacheServlet">Liste des tâches</a></h1>
	
	<table border=1 cellpadding=5 cellspacing=0>
		<tr>
			<th>itTache</th>
			<th>
				<a href="TacheServlet?action=order&column=responsable">Responsable</a>
				<c:if test="${ pager.sortColumn == 'responsable' }">
					${ pager.descendingSort ? 'desc':'asc' }
				</c:if>
			</th>
			<th>
				<a href="TacheServlet?action=order&column=statut">Statut</a>
				<c:if test="${ pager.sortColumn == 'statut' }">
					${ pager.descendingSort ? 'desc':'asc' }
				</c:if>
			</th>
			<th>
				<a href="TacheServlet?action=order&column=type">Type</a>
				<c:if test="${ pager.sortColumn == 'type' }">
					${ pager.descendingSort ? 'desc':'asc' }
				</c:if>
			</th>
			<th>
				<a href="TacheServlet?action=order&column=pays">Pays</a>
				<c:if test="${ pager.sortColumn == 'pays' }">
					${ pager.descendingSort ? 'desc':'asc' }
				</c:if>
			</th>
		</tr>

		<c:forEach items="${ pager.data }" var="tache" varStatus="status">
			<tr>
				<td>${ tache.itTache }</td>
				<td>${ tache.responsable }</td>
				<td>${ tache.statut }</td>
				<td>${ tache.type }</td>
				<td>${ tache.pays }</td>
			</tr>
		</c:forEach>
	</table>
	
	<c:if test="${ pager.hasPrevious() }">
		<a href="TacheServlet?action=previous">Enregistrements précédents</a>
	</c:if>
	<c:if test="${ pager.hasPrevious() == pager.hasNext() }"> | </c:if>
	<c:if test="${ pager.hasNext() }">
		<a href="TacheServlet?action=next">Enregistrements suivants</a>
	</c:if>

</body>
</html>
