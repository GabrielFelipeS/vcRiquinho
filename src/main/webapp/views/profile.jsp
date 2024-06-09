<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="../WEB-INF/errorTag.tld" prefix="erro"%>
<%@ taglib uri="../WEB-INF/errorTag.tld" prefix="erro"%>
<%@ page
	import="br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress"%>
<%@ page import="br.com.ifsp.vcRiquinho.base.db.interfaces.IDBConnector"%>
<%@ page
	import="br.com.ifsp.vcRiquinho.produto.factory.concrate.FactoryProdutoCreator"%>
<%@ page
	import="br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto"%>
<%@ page
	import="br.com.ifsp.vcRiquinho.produto.repository.RepositoryProduto"%>
<%@ page import="br.com.ifsp.vcRiquinho.produto.dao.ProdutoDAO"%>
<%@ page import="java.util.List"%>

<%@ page import="br.com.ifsp.vcRiquinho.pessoa.factory.concrate.PessoaRepositoryFactory"%>
<%@ page import="br.com.ifsp.vcRiquinho.pessoa.repository.RepositoryPessoa"%>

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
   <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
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
							Excluir perfil</button>
				</div>
			</div>
		</div>
	</div>
	  <p id="resposta"></p>
	 <script>
        $('#excluir-conta').on('click', function() {
        	 Swal.fire({
                 title: 'Você deseja realmente deletar a sua conta na vcRiquinho?',
                 text: "Você pode cancelar se quiser!",
                 icon: 'warning',
                 showCancelButton: true,
                 confirmButtonText: 'Sim, deletar!',
                 cancelButtonText: 'Não, parar'
             }).then((result) => {
                 if (result.isConfirmed) {
                     Swal.fire(
                         'Deletando!',
                         'Você escolheu continuar.',
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
                         'Você escolheu parar.',
                         'error'
                     );
                     // Ação para parar
                 }
             })
        	
           
        });
    </script>
    
    
    
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
						<div class="col-xs-6">
							<a href="#addEmployeeModal" class="btn btn-success"
								data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Adicionar
									novo produto</span></a>
						</div>
					</div>
				</div>
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>Nome</th>
							<th>Descricao</th>
							<th>Carência</th>
							<th>Tipo Produto</th>
							<th>Rendimento mensal</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						
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
						<h4 class="modal-title">Adicionar produto</h4>
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
							<label>Carência</label> <input type="number" class="form-control"
								name="carencia" min="0" required>
						</div>
						<div class="form-group">
							<label>Tipo Produto</label> <select name="tipo_produto">
								<option value="renda_fixa">Renda Fixa</option>
								<option value="renda_variavel">Renda Variável</option>
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
		
	        $('.delete').on('click', function() {
	        	 var productId = $(this).data('id');
	        	 
	        	 Swal.fire({
	                 title: 'Você deseja realmente deletar esse produto da vcRiquinho?',
	                 text: "Você pode cancelar se quiser!",
	                 icon: 'warning',
	                 showCancelButton: true,
	                 confirmButtonText: 'Sim, deletar!',
	                 cancelButtonText: 'Não, parar'
	             }).then((result) => {
	                 if (result.isConfirmed) {
	                     Swal.fire(
	                         'Deletando!',
	                         'Você escolheu continuar.',
	                         'success'
	                     );
	                     setTimeout(function() {
	                    	 $.ajax({
	                             url: '/vcRiquinho/produto?idProduto='+productId,
	                             method: 'DELETE',
	                             contentType: 'application/json',
	                             success: function(data) {
	                             	window.location.assign('/vcRiquinho/painelProduto');
	                             },
	                             error: function(jqXHR, textStatus, errorThrown) {
	                                 $('#resposta').text('Erro: ' + textStatus + ' - ' + errorThrown);
	                             }
	                         });
	                     }, 5000); 
	                 } else if (result.dismiss === Swal.DismissReason.cancel) {
	                     Swal.fire(
	                         'Parando!',
	                         'Você escolheu parar.',
	                         'error'
	                     );
	                 }
	             })
	        	
	           
	        });
	
		
	</script>
</body>
</html>