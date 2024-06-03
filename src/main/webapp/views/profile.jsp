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
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<title>Perfil usuario</title>
</head>
<body>
	<jsp:include page="../component/navbar.jsp" />

	<erro:message attribute="semPermissao"></erro:message>
	<erro:message attribute="erroProfile"></erro:message>

	<div class="container mt-5">
		<div class="row d-flex justify-content-center align-items-center h-90">
			<div
				class="col-12 col-md-12 col-lg-10 col-xl-7 mt-2 container-custom">
				<div class="mb-3 text-center">
						<button id="excluir-conta" type="submit" data-mdb-ripple-init type="button"
							class="btn text-white" style="background-color: #d9534f;">
							Excluir conta</button>
				</div>
			</div>
		</div>
	</div>
	  <p id="resposta"></p>
	 <script>
        $('#excluir-conta').on('click', function() {
            $.ajax({
                url: '/vcRiquinho/pessoa',
                method: 'DELETE', // ou 'POST', 'PUT', 'DELETE', etc.
                contentType: 'application/json',
                success: function(data) {
                    $('#resposta').text(JSON.stringify(data));
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    $('#resposta').text('Erro: ' + textStatus + ' - ' + errorThrown);
                }
            });
        });
    </script>
</body>
</html>