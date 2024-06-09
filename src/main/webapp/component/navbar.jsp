<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-dark">
	<!-- Container wrapper -->
	<div class="container">
		<!-- Navbar brand -->
		<a class="navbar-brand me-2" href="home"> <img
			src="https://cdn-icons-png.flaticon.com/512/10384/10384161.png"
			height="30" alt="vcRiquinho Logo" style="margin-top: -1px;" />
		</a>

		<!-- Toggle button -->
		<button data-mdb-collapse-init class="navbar-toggler" type="button"
			data-mdb-target="#navbarButtonsExample"
			aria-controls="navbarButtonsExample" aria-expanded="false"
			aria-label="Toggle navigation">
			<i class="fas fa-bars"></i>
		</button>

		<!-- Collapsible wrapper -->
		<div class="collapse navbar-collapse" id="navbarButtonsExample">
			<!-- Left links -->
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link text-white" href="home">Home</a>
				</li>

				<%
				if (session.getAttribute("logado") != null) {
				%>
					<li class="nav-item"><a class="nav-link text-white"
						href="simulacao">Simulação</a></li>

				<%
				}
				%>

				<%
				Object isAdmin = session.getAttribute("isAdmin");
				if (isAdmin != null && (Boolean) isAdmin) {
				%>
					<li class="nav-item"><a class="nav-link text-white"
						href="simulacaoGeral">Simulação geral</a></li>
	
					<li class="nav-item"><a class="nav-link text-white"
						href="painelProduto">Painel de produtos</a></li>
				<%
				}
				%>

			</ul>

			<!-- Left links -->
			<%
			if (session.getAttribute("logado") != null) {
			%>
			<div class="d-flex align-items-center ms-auto"
				style="justify-content: flex-end;">
				<a href="profile">
					<button href="profile" data-mdb-ripple-init type="button"
						class="btn btn-link px-3 me-2"
						style="color: white; text-decoration-color: white">Profile
					</button>
				</a> <a href="logout">
					<button data-mdb-ripple-init type="button"
						class="btn  me-3 text-white" style="background-color: #454d6b;">
						Logout</button>
				</a>
			</div>
			<%
			} else {
			%>
			<div class="d-flex align-items-center ms-auto"
				style="justify-content: flex-end;">
				<a href="login">
					<button href="login" data-mdb-ripple-init type="button"
						class="btn btn-link px-3 me-2"
						style="color: white; text-decoration-color: white">Login
					</button>
				</a> <a href="register">
					<button data-mdb-ripple-init type="button"
						class="btn  me-3 text-white" style="background-color: #454d6b;">
						Cadastre-se</button>
				</a>
			</div>
			<%
			}
			%>
		</div>
	</div>
</nav>
