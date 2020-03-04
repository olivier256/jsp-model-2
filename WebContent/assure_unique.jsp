<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Récupérer les Apache Taglibs Impl, Spec et EL, et les coller dans WebContent/WEB-INF/lib -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des assurés uniques</title>
</head>
<body>
	<a href="index.jsp">Retour au début</a>
	<br>
	<h1>
		<a href="AssureUniqueServlet">Liste des assurés uniques</a>
	</h1>

	<table border=1 cellpadding=5 cellspacing=0>
		<tr>
			<th>itAssureUnique</th>
			<th><a href="AssureUniqueServlet?action=order&column=nomNaissance">Nom de naissance</a> <c:if test="${ pager.sortColumn == 'nomNaissance' }">
					${ pager.descendingSort ? 'desc':'asc' }
				</c:if></th>
			<th><a href="AssureUniqueServlet?action=order&column=prenomNaissance">Prénom de naissance</a> <c:if
					test="${ pager.sortColumn == 'prenomNaissance' }">
					${ pager.descendingSort ? 'desc':'asc' }
				</c:if></th>
			<th>Sexe</th>
			<th>NIR</th>
			<th><a href="AssureUniqueServlet?action=order&column=dateDeNaissance">Date de naissance</a> <c:if
					test="${ pager.sortColumn == 'dateDeNaissance' }">
					${ pager.descendingSort ? 'desc':'asc' }
				</c:if></th>
		</tr>
		<c:forEach items="${ pager.data }" var="assureUnique" varStatus="status">
			<tr>
				<td>${ assureUnique.itAssureUnique }</td>
				<td>${ assureUnique.nomNaissance }</td>
				<td>${ assureUnique.prenomNaissance }</td>
				<td>${ assureUnique.sexe }</td>
				<td>${ assureUnique.nir }</td>
				<td>${ assureUnique.jourNaissance}/${ assureUnique.moisNaissance }/${ assureUnique.anneeNaissance }</td>
			</tr>
		</c:forEach>
	</table>

	<c:if test="${ pager.hasPrevious() }">
		<a href="AssureUniqueServlet?action=previous">Enregistrements précédents</a>
	</c:if>
	<c:if test="${ pager.hasPrevious() == pager.hasNext() }"> | </c:if>
	<c:if test="${ pager.hasNext() }">
		<a href="AssureUniqueServlet?action=next">Enregistrements suivants</a>
	</c:if>

</body>
</html>
