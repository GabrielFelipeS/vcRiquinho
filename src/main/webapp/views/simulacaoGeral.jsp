<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="../WEB-INF/tableTags.tld" prefix="table"%>
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
.container-custom {
	margin-left: 70px;
}
</style>

<title>Simulação</title>
</head>
<body>
	<jsp:include page="../component/navbar.jsp" />

	<div class="container mt-5">
		<div class="row d-flex justify-content-center align-items-center h-90">
			<div
				class="col-12 col-md-12 col-lg-10 col-xl-7 mt-2 container-custom">
				<h1 class="text-center">Faça a simulação agora mesmo</h1>
				<p class="text-center">A simulação será feita baseando-se nos
					perfis dos usuários, usando suas contas e tipo de pessoa.</p>

				<div class="d-flex justify-content-center mt-5">
					<form method="post" action="simulacaoGeral">
						<div class="mb-3">
							<label for="selectDays" class="form-label">Dias</label> <select
								class="form-select" id="selectDays" name="days">
								<option value="30">30 dias</option>
								<option value="60">60 dias</option>
								<option value="90">90 dias</option>
								<option value="180">180 dias</option>
							</select>
						</div>
						<div class="mb-3 text-center">
							<button type="submit" data-mdb-ripple-init type="button"
								class="btn text-white" style="background-color: #454d6b;">
								Simular</button>
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>

	<%
	Object obj = session.getAttribute("simulacoesGeral");
	if (obj != null) {
	%>
		<div class="container mt-5">
				<div class="table-wrapper">
					<div class="table-title">
						<div class="row">
							<div class="col-sm-8 mb-2">
								<h2>
									Simulações <b>Detalhes</b>
								</h2>
							</div>
						</div>
	
						<table:simulacao attribute="simulacoesGeral"/>
	
					</div>
				</div>
		</div>
	<%
	}
	%>
</body>
</html>