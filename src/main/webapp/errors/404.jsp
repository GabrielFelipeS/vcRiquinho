<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="../WEB-INF/errorTag.tld" prefix="erro"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="icon" type="image/png"
	href="https://cdn-icons-png.flaticon.com/512/10384/10384161.png">

<title>Error page</title>
</head>
<body>
	<erro:forceLogout></erro:forceLogout>

	<jsp:include page="../component/navbar.jsp" />

	<div class="container mt-5">
		<div class="row d-flex justify-content-center align-items-center h-90">
			<div
				class="col-12 col-md-12 col-lg-10 col-xl-7 mt-2 container-custom">
				<h1 class="text-center">Erro 404: Página ou recurso não
					encontrado</h1>
				<p class="text-center">A vcRiquinho pede desculpas pelo
					imprevisto e solicita uma nova tentativa.</p>
				<div class="mb-3 text-center">
					<a href="home">
						<button type="submit" data-mdb-ripple-init type="button"
							class="btn text-white" style="background-color: #454d6b;">
							Voltar para a página inicial</button>
					</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>