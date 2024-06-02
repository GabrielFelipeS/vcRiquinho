
<form id="formCadastro" action="pessoa" method="post" class="row g-3"
	onsubmit="return validarDocumento() && validarSenhas();">
	<h2 class="text-center">Dados Pessoais</h2>

	<div class="col-md-6">
		<label for="tipo_pessoa" class="form-label">Tipo pessoa:</label> <select
			id="tipo_pessoa" name="tipo_pessoa"
			onchange="alterarPlaceholderEValidar()" class="form-select mb-3">
			<option value="juridica">Pessoa juridica</option>
			<option value="fisica">Pessoa fisica</option>
		</select>
	</div>

	<div class="col-md-6">
		<label for="documento_titular" class="form-label">Documento
			Titular:</label> <input type="text" id="documento_titular"
			name="documento_titular" class="form-control mb-3" placeholder=""
			required>
	</div>

	<div class="col-md-6">
		<label for="nome" class="form-label">Nome Completo:</label> <input
			type="text" id="nome" name="nome" class="form-control mb-3" required>
	</div>

	<div class="col-md-12" id="accountContainer">
		<h2 class="text-center">Contas</h2>
		<label for="documentoTitular_conta" class="form-label">Documento
			Titular:</label> <input type="text" id="documentoTitular_conta"
			name="documentoTitular_conta" class="form-control mb-3" readonly>
		<input type="hidden" id="tipo_conta" name="tipo_conta"
			value="corrente">
	</div>

	<div class="col-md-6">
		<select id="accountType" class="form-select mb-3">
			<option value="Conta Corrente">Conta Corrente</option>
			<option value="Conta CDI">Conta CDI</option>
			<option value="Conta Investimento Automático">Conta
				Investimento Automático</option>
		</select>
	</div>
	<div class="col-md-6">
		<input type="button" value="Mudar primeira conta"
			onclick="addAccount()" class="btn btn-primary mb-3">
	</div>

	<div class="col-md-12">
		<h2 class="text-center">Dados de acesso</h2>
		<label for="email" class="form-label">Email:</label> <input
			type="email" id="email" name="email" class="form-control mb-3"
			required> <label for="password" class="form-label">Senha:</label>
		<input type="password" id="password" name="password"
			class="form-control mb-3"> <label for="password_confirm"
			class="form-label">Confirme a senha:</label> <input type="password"
			id="password_confirm" name="password_confirm"
			class="form-control mb-3">
	</div>

	<div class="col-md-12 text-center">
		<input type="submit" value="Enviar" class="btn btn-primary btn-custom">
	</div>
</form>




<script type="text/javascript" id="copyDocumentoTitular">
   
		const sourceField = document.getElementById('documento_titular');
	
	    sourceField.placeholder = "XX.XXX.XXX/YYYY-ZZ ";
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
    </script>

<script type="text/javascript" id="addConta">   
		function clearContaForm() {
    		 let accountDiv = document.getElementById("accountContainer");
             accountDiv.innerHTML = `<h2 class="text-center">Contas</h2>`;  
    	 }
    	 
        function addAccount() {
            let accountType = document.getElementById('accountType').value;
            var container = document.getElementById('accountContainer');
            var accountDiv = document.createElement('div');
 
            clearContaForm()
            accountDiv.innerHTML = `
                <label for="documentoTitular_conta" class="form-label">Documento Titular:</label>
                <input type="text" id="documentoTitular_conta" class="form-control mb-3" name="documentoTitular_conta" readonly>
               `;
            if (accountType === 'Conta CDI') {
                accountDiv.innerHTML += `
                    <label for="cdiAtual" class="form-label">CDI Atual:</label>
                    <input type="text" id="cdiAtual" class="form-control mb-3" name="cdiAtual" value="0.12" readonly>
                    <input type="hidden" id="tipo_conta" class="form-control mb-3" name="tipo_conta" value="cdi">
                  `;
            } else if (accountType === 'Conta Investimento Automático') {
                accountDiv.innerHTML += `
                    <label for="idProduto" class="form-label">ID do Produto:</label><br>
                    <input type="number" id="idProduto" class="form-control mb-3" name="idProduto" value="2" readonly>
                    <input type="hidden" id="tipo_conta" class="form-control mb-3"  name="tipo_conta" value="investimento_automatico">
                `;
            } else if (accountType === 'Conta Corrente') {
            	 accountDiv.innerHTML += `
            		<input type="hidden" id="tipo_conta" class="form-control mb-3" name="tipo_conta" value="corrente">
            	`;
            }
            container.appendChild(accountDiv);
            copyDocumentoTitular();
        }
        

    </script>

<script type="text/javascript" id="placeholder_e_validacao">
	document.getElementById('tipo_pessoa').addEventListener('change', alterarPlaceholderEValidar);
	document.getElementById('accountType').addEventListener('change', addAccount);

		function validarSenhas() {
			  var senha1 = document.getElementById('password').value;
			  var senha2 = document.getElementById('password_confirm').value;
	
			  console.log('Senha1:', senha1);
              console.log('Senha2:', senha2);
              
			  if (senha1 !== senha2) {
			    alert('As senhas não são iguais!');
			    //event.preventDefault(); // impede o envio do formulário
			    return false;
			  }
			  
			  return true;
			} 
		
       function alterarPlaceholderEValidar() {
           var tipoPessoa = document.getElementById("tipo_pessoa").value;
           var documentoTitular = document.getElementById("documento_titular");

           if (tipoPessoa == "fisica") {
               documentoTitular.placeholder = "XXX.XXX.XXX-XX";
               documentoTitular.onchange = function() {
               	var regex = /^(\d{3}\.\d{3}\.\d{3}-\d{2})$/;
                   if (!regex.test(this.value)) {
                       alert("CPF inválido!");
                   }
               };
           } else if (tipoPessoa == "juridica") {
               documentoTitular.placeholder = "XX.XXX.XXX/YYYY-ZZ ";
               documentoTitular.onchange = function() {
               	var regex = /^(\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2})$/;
                   if (!regex.test(this.value)) {
                       alert("CNPJ inválido!");
                   }
               };
           }
       }
       
       function validarDocumento() {
           var tipoPessoa = document.getElementById("tipo_pessoa").value;
           var documentoTitular = document.getElementById("documento_titular").value;

           if ((tipoPessoa == "fisica" && !/^(\d{3}\.\d{3}\.\d{3}-\d{2})$/.test(documentoTitular)) ||
               (tipoPessoa == "juridica" && !/^(\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2})$/.test(documentoTitular))) {
               alert("Documento inválido!");
			    return false;
			  }
			  
			  return true;
       }
       
   </script>