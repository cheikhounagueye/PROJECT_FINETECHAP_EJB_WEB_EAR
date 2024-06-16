<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
<style>
body {
	font-family: 'Arial', sans-serif;
	background: #f7f7f7;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

.container {
	background: #fff;
	padding: 2rem;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	max-width: 400px;
	width: 100%;
	text-align: center;
}

h1 {
	color: #333;
}

p {
	margin: 1rem 0;
	color: #666;
}

table {
	margin-top: 2rem;
	width: 100%;
	border-collapse: collapse;
}

table th, table td {
	padding: 0.5rem;
	border: 1px solid #ccc;
}

form {
	margin-top: 2rem;
	display: flex;
	flex-direction: column;
	align-items: center;
}

label {
	margin-bottom: 0.5rem;
}

input[type="text"], input[type="submit"], input[type="number"], input[type="date"]
	{
	padding: 0.5rem;
	margin-bottom: 1rem;
	width: 100%;
	max-width: 300px;
	box-sizing: border-box;
}
</style>
</head>
<body>
	<div class="container">
		<h1>Welcome</h1>
		<p>Login successful! Welcome, ${sessionScope.user}.</p>

		<table>
			<tr>
				<th>Date</th>
				<th>Opération</th>
				<th>Montant</th>
				<th>Code</th>
			</tr>
			<c:forEach var="Lcompte" items="${listCompte}">
				<tr>
					<td>${Lcompte.dateCreation}</td>
					<td>Ajout de compte</td>
					<td>${Lcompte.montant}</td>
					<td>${Lcompte.code}</td>
				</tr>
			</c:forEach>
			<c:forEach var="compte" items="${comptes}">
				<tr>
					<td>${compte.dateCreation}</td>
					<td>Ajout de compte</td>
					<td>${compte.montant}</td>
					<td>${compte.code}</td>
				</tr>
			</c:forEach>
		</table>


		<form action="addAccount" method="post">
			<label for="montant">Montant:</label> <input type="number"
				id="montant" name="montant" required><br> <label
				for="datecreation">Date de création:</label> <input type="date"
				id="datecreation" name="datecreation" required><br> <input
				type="hidden" name="actionAddCompte" value="ajouterCompte">
			<input type="submit" value="Ajouter Compte">
		</form>

	</div>
</body>
</html>
