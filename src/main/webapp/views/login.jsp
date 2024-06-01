<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="icon" type="image/png"
	href="https://cdn-icons-png.flaticon.com/512/10384/10384161.png">
<style>
body {
	background-color: #454d6b;
}

.input-custom {
	border: 1px solid #ced4da; /* Cor da borda padrão */
	padding: .375rem .75rem; /* Espaçamento interno */
	transition: border-color 0.3s ease; /* Transição suave */
}

/* Efeito de hover */
.input-custom:hover {
	border-color: #007bff; /* Cor da borda quando passa o mouse */
	backgroud-color: red;
}
</style>
<title>Login</title>
</head>
<body>

	<jsp:include page="../component/navbar.jsp" />
	
	<div class="container mt-5">
		<div
			class="row d-flex justify-content-center align-items-center h-100">
			<div class="col-12 col-md-10 col-lg-8 col-x1-7">
				<div class="card bg-dark text-white" style="border-radius: 1rem;">
					<div class="card-body p-5 text-center">
						<form action="login" method="post" class="row g-3">
						<h2><strong>Login</strong></h2>
							<label class=form-label for="email">Email:</label> <input
								type="text" class="form-control" name="email" required>
							<label class=form-label for="password"> Password:</label> <input
								type="password" class="form-control" name="password" required>
							<input type="submit" value="login" id="logar"
								class="form-control input-custom">
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>