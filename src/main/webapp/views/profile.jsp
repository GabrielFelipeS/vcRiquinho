<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="../WEB-INF/errorTag.tld" prefix="erro"%>

<%@ taglib uri="../WEB-INF/errorTag.tld" prefix="erro"%>
<%@ page
	import="br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress"%>
<%@ page
	import="br.com.ifsp.vcRiquinho.produto.factory.concrate.FactoryProdutoCreator"%>
<%@ page import="br.com.ifsp.vcRiquinho.produto.dao.ProdutoDAO"%>
<%@ page
	import="br.com.ifsp.vcRiquinho.produto.repository.RepositoryProduto"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.util.List"%>
<%@ page import="br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa"%>

<%@ page
	import="br.com.ifsp.vcRiquinho.pessoa.factory.concrate.PessoaRepositoryFactory"%>

<%@ page
	import="br.com.ifsp.vcRiquinho.conta.factory.concrate.FactoryContaCreatorProvider"%>
<%@ page
	import="br.com.ifsp.vcRiquinho.conta.repository.RepositoryConta"%>
<%@ page import=" br.com.ifsp.vcRiquinho.conta.dao.ContaDAO"%>
<%@ page import=" br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="icon" type="image/png"
	href="https://cdn-icons-png.flaticon.com/512/10384/10384161.png">

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<title>Perfil usuario</title>
</head>
<body>
	<%
	Connection conn = new ConnectionPostgress().getConnection();
	RepositoryProduto repositoryProduto = new RepositoryProduto(new ProdutoDAO(conn), new FactoryProdutoCreator());
	RepositoryConta repository = new RepositoryConta(new ContaDAO(conn), repositoryProduto,
			new FactoryContaCreatorProvider());

	Pessoa pessoa = (Pessoa) session.getAttribute("conta");
	List<String> listMissingTypeAccounts = repository.findMissingTypeAccounts(pessoa.getDocumentoTitular());

	List<Conta> contas = pessoa.getContasListCopy();
	%>


	<jsp:include page="../component/navbar.jsp" />

	<erro:message attribute="semPermissao"></erro:message>
	<erro:message attribute="erroProfile"></erro:message>


	<div class="container">
		<div class="table-responsive">
			<div class="table-wrapper">
				<div class="table-title">
					<div class="row">
						<div class="col-xs-6">
							<h2>
								Gerenciar <b>Contas</b>
							</h2>
						</div>
					<%if(listMissingTypeAccounts.size() != 0){%>
						<div class="col-xs-6">
						<a href="#addEmployeeModal" class="btn btn-success"
							data-toggle="modal"><span>Adicionar novo conta</span></a>
					</div>
					<%}%>
					</div>
				</div>
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>Numero conta</th>
							<th>Documento titular</th>
							<th>Tipo Conta</th>
							<th>Montante financeiro</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<%
						for (Conta conta : contas) {
						%>
						<tr>
							<td><%=conta.getNumConta()%></td>
							<td><%=conta.getDocumentoTitular()%></td>
							<td><%=conta.tipoConta().replace("_", " ")%></td>
							<td><%=conta.getMontanteFinanceiro()%></td>
							<td><a href="#editEmployeeModal" class="edit"
								data-toggle="modal"><i class="material-icons"
									data-toggle="tooltip" title="Edit">&#xE254;</i></a> <a href="#"
								data-id="<%=conta.getNumConta()%>" class="delete"
								data-toggle="modal"><i class="material-icons"
									data-toggle="tooltip" title="Delete">&#xE872;</i></a></td>
						</tr>
						<%
						}
						%>


					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- Edit Modal HTML -->
	<div id="addEmployeeModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="produto" method="POST">
					<div class="modal-header">
						<h4 class="modal-title">Adicionar conta</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label>Nome do produto</label> <input type="text"
								class="form-control" name="nome" required>
						</div>
						<div class="form-group">
							<label>Descricao</label>
							<textarea class="form-control" name="descricao" required></textarea>

						</div>
						<div class="form-group">
							<label>Car�ncia</label> <input type="number" class="form-control"
								name="carencia" min="0" required>
						</div>
						<div class="form-group">
							<label>Tipo Produto</label> <select name="tipo_produto">
								<option value="renda_fixa">Renda Fixa</option>
								<option value="renda_variavel">Renda Vari�vel</option>
							</select>
						</div>
						<div class="form-group">
							<label>Rendimento Mensal</label> <input type="number"
								id="rendimento_mensal" name="rendimento_mensal" step="0.01"
								min="0" placeholder="0.00" required>
						</div>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal"
							value="Cancel"> <input type="submit"
							class="btn btn-success" value="Add">
					</div>
				</form>
			</div>
		</div>
	</div>


	<div class="container mt-5">
		<div class="row d-flex justify-content-center align-items-center h-90">
			<div
				class="col-12 col-md-12 col-lg-10 col-xl-7 mt-2 container-custom">
				<div class="mb-3 text-center">

					<button id="excluir-perfil" type="submit" data-mdb-ripple-init
						type="button" class="btn text-white"
						style="background-color: #d9534f;">Excluir perfil</button>

				</div>
			</div>
		</div>
	</div>

	<script>
        $('#excluir-perfil').on('click', function() {
        	 Swal.fire({
                 title: 'Voc� deseja realmente deletar seu perfil na vcRiquinho?',
                 text: "Voc� pode cancelar se quiser!",
                 icon: 'warning',
                 showCancelButton: true,
                 confirmButtonText: 'Sim, deletar!',
                 cancelButtonText: 'N�o, parar'
             }).then((result) => {
                 if (result.isConfirmed) {
                     Swal.fire(
                         'Deletando!',
                         'Voc� escolheu continuar.',
                         'success'
                     );
                     setTimeout(function() {
                    	 $.ajax({
                             url: '/vcRiquinho/pessoa',
                             method: 'DELETE',
                             contentType: 'application/json',
                             success: function(data) {
                             	window.location.assign('/vcRiquinho/logout');
                             },
                             error: function(jqXHR, textStatus, errorThrown) {
                                 $('#resposta').text('Erro: ' + textStatus + ' - ' + errorThrown);
                             }
                         });
                     }, 5000); // 5000 milissegundos = 5 segundos
                 } else if (result.dismiss === Swal.DismissReason.cancel) {
                     Swal.fire(
                         'Parando!',
                         'Voc� escolheu parar.',
                         'error'
                     );
                     // A��o para parar
                 }
             })
        	
           
        });
    </script>

	<script>
	        $('.delete').on('click', function() {
	        	 var contaId = $(this).data('id');
	        	 console.log(" a " + contaId)
	        	 Swal.fire({
	                 title: 'Voc� deseja realmente deletar essa conta do seu perfil na vcRiquinho?',
	                 text: "Voc� pode cancelar se quiser!",
	                 icon: 'warning',
	                 showCancelButton: true,
	                 confirmButtonText: 'Sim, deletar!',
	                 cancelButtonText: 'N�o, parar'
	             }).then((result) => {
	                 if (result.isConfirmed) {
	                     Swal.fire(
	                         'Deletando!',
	                         'Voc� escolheu continuar.',
	                         'success'
	                     );
	                     setTimeout(function() {
	                    	 $.ajax({
	                             url: '/vcRiquinho/conta?contaId='+ encodeURIComponent(contaId),
	                             method: 'DELETE',
	                             data: { contaId: contaId },
	                             contentType: 'application/json',
	                             success: function(data) {
	                             	window.location.assign('/vcRiquinho/perfil');
	                             },
	                             error: function(jqXHR, textStatus, errorThrown) {
	                                 $('#resposta').text('Erro: ' + textStatus + ' - ' + errorThrown);
	                             }
	                         });
	                     }, 5000); 
	                 } else if (result.dismiss === Swal.DismissReason.cancel) {
	                     Swal.fire(
	                         'Parando!',
	                         'Voc� escolheu parar.',
	                         'error'
	                     );
	                 }
	             })
	        	
	           
	        });
	
		
	</script>
	<!-- Edit Modal HTML -->
	<div id="editEmployeeModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form>
					<div class="modal-header">
						<h4 class="modal-title">Editanto Produto</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label>Name</label> <input type="text" class="form-control"
								required>
						</div>
						<div class="form-group">
							<label>Email</label> <input type="email" class="form-control"
								required>
						</div>
						<div class="form-group">
							<label>Address</label>
							<textarea class="form-control" required></textarea>
						</div>
						<div class="form-group">
							<label>Phone</label> <input type="text" class="form-control"
								required>
						</div>
						<div class="form-group">
							<label>Rendimento Mensal</label> <input type="number"
								id="rendimento_mensal" name="rendimento_mensal" step="0.01"
								min="0" placeholder="0.00" required>
						</div>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal"
							value="Cancel"> <input type="submit" class="btn btn-info"
							value="Save">
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Delete Modal HTML -->
	<div id="deleteEmployeeModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form>
					<div class="modal-header">
						<h4 class="modal-title">Delete Employee</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<p>Are you sure you want to delete these Records?</p>
						<p class="text-warning">
							<small>This action cannot be undone.</small>
						</p>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal"
							value="Cancel"> <input type="submit"
							class="btn btn-danger" value="Delete">
					</div>
				</form>
			</div>
		</div>
	</div>

	<script>
		$(document).on("click", ".edit", function () {
		    var myData = $(this).data('id');
		    $(".modal-content #data").val( myData );
		});
		
	      
	
		
	</script>
</body>
</html>