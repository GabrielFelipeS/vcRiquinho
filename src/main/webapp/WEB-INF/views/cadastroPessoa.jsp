<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Formulário de Cadastro</title>

<style>
input[type="text"], input[type="email"] {
	width: 260px;
}
</style>
</head>
<body>
	<%
	String mensagem = (String) session.getAttribute("mensagemErro");
	session.removeAttribute("mensagemErro");

	if (mensagem != null) {
	%>
	<p><%=mensagem%></p>
	<%
	}
	%>

	<form id="formCadastro" action="pessoa" method="post">
		<h2>Dados Pessoais</h2>
		<select id="tipo_pessoa" name="tipo_pessoa"
			onchange="alterarPlaceholderEValidar()">
			<option value="juridica">Pessoa juridica</option>
			<option value="fisica">Pessoa fisica</option>
		</select> <br> <label for="documento_titular">Documento Titular:</label><br>
		<input type="text" id="documento_titular" name="documento_titular"
			placeholder="" required><br> <label for="nome">Nome
			Completo:</label><br> <input type="text" id="nome" name="nome" required><br>
		<h2>Contas</h2>
		<div id="accountContainer">
			<label for="documentoTitular_conta">Documento Titular:</label><br>
			<input type="text" id="documentoTitular_conta"
				name="documentoTitular_conta" readonly><br> <input
				type="hidden" id="tipo_conta" name="tipo_conta" value="corrente">
		</div>
		<select id="accountType">
			<option value="Conta Corrente">Conta Corrente</option>
			<option value="Conta CDI">Conta CDI</option>
			<option value="Conta Investimento Automático">Conta
				Investimento Automático</option>
		</select> <input type="button" value="Adicionar Conta" onclick="addAccount()">
		<h2>Dados de acesso</h2>
		<label for="email">Email:</label><br> <input type="email"
			id="email" name="email" required><br> <label
			for="password">Senha:</label><br> <input type="password"
			id="password" name="password"><br> <label
			for="password_confirm"> Confirme a senha::</label><br> <input
			type="password" id="password_confirm" name="password_confirm"><br>
		<input type="submit" value="Enviar">
	</form>


	<script type="text/javascript">
		document.getElementById('tipo_pessoa').addEventListener('change', alterarPlaceholderEValidar);
		document.getElementById('accountType').addEventListener('change', addAccount);
		
		document.getElementById('formCadastro').addEventListener('submit', function(event) {
			  var senha1 = document.getElementById('password').value;
			  var senha2 = document.getElementById('password_confirm').value;
	
			  if (senha1 !== senha2) {
			    alert('As senhas não são iguais!');
			    event.preventDefault(); // impede o envio do formulário
			  }
			});
		const sourceField = document.getElementById('documento_titular');
	
	    sourceField.placeholder = "Insira o CNPJ no formato: XX.XXX.XXX/YYYY-ZZ ";
	    sourceField.onchange = function() {
	    	  var regex = /^(\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2})$/;
              if (!regex.test(this.value)) {
                  alert("CNPJ inválido!");
              }
          };
	      
	      // Adiciona um evento 'input' ao sourceField
	    sourceField.addEventListener('input', function() {
	    	 copyDocumentoTitular();
	      });
	    
          
       	function copyDocumentoTitular() {
        	  const targetField = document.getElementById('documentoTitular_conta');
              if(targetField != null) {
            	  targetField.value = sourceField.value;
              }
     	 }
	      
		function clearContaForm() {
    		 let accountDiv = document.getElementById("accountContainer");
             accountDiv.innerHTML = ``;  
    	 }
    	 
        function addAccount() {
            let accountType = document.getElementById('accountType').value;
            var container = document.getElementById('accountContainer');
            var accountDiv = document.createElement('div');
 
            clearContaForm()
            accountDiv.innerHTML = `
                <label for="documentoTitular_conta">Documento Titular:</label><br>
                <input type="text" id="documentoTitular_conta" name="documentoTitular_conta" readonly><br>
               `;
            if (accountType === 'Conta CDI') {
                accountDiv.innerHTML += `
                    <label for="cdiAtual">CDI Atual:</label><br>
                    <input type="number" id="cdiAtual" name="cdiAtual" readonly><br>
                    <input type="hidden" id="tipo_conta" name="tipo_conta" value="cdi">
                  `;
            } else if (accountType === 'Conta Investimento Automático') {
                accountDiv.innerHTML += `
                    <label for="idProduto">ID do Produto:</label><br>
                    <input type="number" id="idProduto" name="idProduto" required><br>
                    <input type="hidden" id="tipo_conta" name="tipo_conta" value="investimento_automatico">
                `;
            } else if (accountType === 'Conta Corrente') {
            	 accountDiv.innerHTML += `
            		<input type="hidden" id="tipo_conta" name="tipo_conta" value="corrente">
            	`;
            }
            container.appendChild(accountDiv);
            copyDocumentoTitular();
        }
        
        function alterarPlaceholderEValidar() {
            var tipoPessoa = document.getElementById("tipo_pessoa").value;
            var documentoTitular = document.getElementById("documento_titular");

            if (tipoPessoa == "fisica") {
                documentoTitular.placeholder = "Insira o CPF no formato XXX.XXX.XXX-XX";
                documentoTitular.onchange = function() {
                	var regex = /^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})$/;
                    if (!regex.test(this.value)) {
                        alert("CPF inválido!");
                    }
                };
            } else if (tipoPessoa == "juridica") {
                documentoTitular.placeholder = "Insira o CNPJ no formato: XX.XXX.XXX/YYYY-ZZ ";
                documentoTitular.onchange = function() {
                	var regex = /^(\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2})$/;
                    if (!regex.test(this.value)) {
                        alert("CNPJ inválido!");
                    }
                };
            }
        }
        
        document.getElementById("formCadastro").addEventListener("submit", function(event) {
            var tipoPessoa = document.getElementById("tipo_pessoa").value;
            var documentoTitular = document.getElementById("documento_titular").value;

            if ((tipoPessoa == "fisica" && !/^(\d{3}\.\d{3}\.\d{3}-\d{2})$/.test(documentoTitular)) ||
                (tipoPessoa == "juridica" && !/^(\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2})$/.test(documentoTitular))) {
                event.preventDefault();
                alert("Documento inválido!");
            }
        })
    </script>
</body>
</html>