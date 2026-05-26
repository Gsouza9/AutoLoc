<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Cadastro - AutoLoc</title>

<link rel="icon" type="image/png"
	href="${pageContext.request.contextPath}/img/logocerta.png">

<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
	rel="stylesheet">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/cadastro.css">
</head>

<body>

	<div class="container">

		<div class="lado-cadastro">

			<div class="caixa-cadastro">

				<div class="logo-mobile">
					<img src="${pageContext.request.contextPath}/img/logoautolocksemfundo.png" alt="">
				</div>

				<div class="botoes-topo">

					<button type="button"
						onclick="window.location.href='login.jsp'">
						Entrar
					</button>

					<button class="ativo" type="button">
						Criar Conta
					</button>

				</div>

				<h1>Criar Conta</h1>

				<p class="subtitulo">
					Preencha os dados para começar.
				</p>

				<form id="formCadastro" method="post" action="cadastrol">

					<div class="tipo-conta">

						<button type="button" class="tipo-btn ativo" id="btnVendedor">
							Vendedor
						</button>

						<button type="button" class="tipo-btn" id="btnUsuario">
							Usuário
						</button>

					</div>

					<div id="formVendedor">

						<div class="grupo-input full">
							<label>Nome da Empresa</label>
							<input type="text" name="nomeFantasia"
								placeholder="Digite o nome da empresa">
						</div>

						<div class="grupo-input full">
							<label>Razão Social</label>
							<input type="text" name="razaoSocial"
								placeholder="Digite a razão social">
						</div>

						<div class="grupo-input">
							<label>Nome</label>
							<input type="text" name="nome"
								placeholder="Digite seu nome">
						</div>

						<div class="grupo-input">
							<label>CPF</label>
							<input type="text" name="cpf"
								placeholder="Digite seu CPF">
						</div>

						<div class="grupo-input">
							<label>CNPJ</label>
							<input type="text" name="cnpj"
								placeholder="Digite o CNPJ">
						</div>

						<div class="grupo-input">
							<label>Telefone</label>
							<input type="text" name="telefone"
								placeholder="Digite o telefone">
						</div>

						<div class="grupo-input full">
							<label>Email</label>
							<input type="email" name="email"
								placeholder="Digite seu email">
						</div>

						<div class="grupo-input full">
							<label>Categoria</label>
							<select name="categoria">
								<option value="">Selecione</option>
								<option value="OFICINA">Oficina</option>
								<option value="LOCADORA">Locadora</option>
								<option value="POSTO">Posto</option>
								<option value="AUTOPECAS">Auto Peças</option>
								<option value="PNEUS">Pneus</option>
								<option value="ELETRICA">Elétrica</option>
								<option value="LAVAGEM">Lavagem</option>
							</select>
						</div>

						<div class="grupo-input full">
							<label>Descrição</label>
							<input type="text" name="descricao"
								placeholder="Descrição da empresa">
						</div>

						<div class="grupo-input full">
							<label>Senha</label>
							<input type="password" name="senha"
								placeholder="Digite sua senha">
						</div>

					</div>

					<div id="formUsuario" style="display: none;">

						<div class="grupo-input full">
							<label>Nome</label>
							<input type="text" name="nome"
								placeholder="Digite seu nome">
						</div>

						<div class="grupo-input">
							<label>CPF</label>
							<input type="text" name="cpf"
								placeholder="Digite seu CPF">
						</div>

						<div class="grupo-input">
							<label>Telefone</label>
							<input type="text" name="telefone"
								placeholder="Digite seu telefone">
						</div>

						<div class="grupo-input full">
							<label>Email</label>
							<input type="email" name="email"
								placeholder="Digite seu email">
						</div>

						<div class="grupo-input full">
							<label>Senha</label>
							<input type="password" name="senha"
								placeholder="Digite sua senha">
						</div>

					</div>

					<div class="opcoes">

						<label>
							<input type="checkbox" required>
							Aceito os termos
						</label>

					</div>

					<button class="botao-cadastro" type="submit">
						Criar Conta
					</button>

				</form>

			</div>

		</div>

		<div class="lado-info">

			<div class="logo">
				<img src="${pageContext.request.contextPath}/img/logoautolocksemfundo.png" alt="">
			</div>

			<h2 class="texto-principal">
				Crie sua conta e encontre oficinas com facilidade
			</h2>

			<div class="item-info">

				<div class="icone">🚗</div>

				<div>
					<h3>Cadastro Rápido</h3>
					<p>Crie sua conta em poucos segundos.</p>
				</div>

			</div>

			<div class="item-info">

				<div class="icone">⭐</div>

				<div>
					<h3>Oficinas Avaliadas</h3>
					<p>Veja avaliações reais de outros usuários.</p>
				</div>

			</div>

			<div class="item-info">

				<div class="icone">🔧</div>

				<div>
					<h3>Serviços Automotivos</h3>
					<p>Encontre serviços próximos.</p>
				</div>

			</div>

			<div class="cards">

				<div class="card">
					<h2>1.2K+</h2>
					<span>Oficinas</span>
				</div>

				<div class="card">
					<h2>5K+</h2>
					<span>Usuários</span>
				</div>

				<div class="card">
					<h2>4.9</h2>
					<span>Avaliação</span>
				</div>

			</div>

		</div>

	</div>

	<script>
		const btnVendedor = document.getElementById("btnVendedor");
		const btnUsuario = document.getElementById("btnUsuario");

		const formCadastro = document.getElementById("formCadastro");

		const formVendedor = document.getElementById("formVendedor");
		const formUsuario = document.getElementById("formUsuario");

		function setCamposAtivos(container, ativo) {
			const campos = container.querySelectorAll("input, select, textarea");

			campos.forEach(function(campo) {
				campo.disabled = !ativo;
			});
		}

		function mostrarVendedor() {
			formCadastro.action = "cadastrol";

			formVendedor.style.display = "grid";
			formUsuario.style.display = "none";

			setCamposAtivos(formVendedor, true);
			setCamposAtivos(formUsuario, false);

			btnVendedor.classList.add("ativo");
			btnUsuario.classList.remove("ativo");
		}

		function mostrarUsuario() {
			formCadastro.action = "cadastrou";

			formVendedor.style.display = "none";
			formUsuario.style.display = "grid";

			setCamposAtivos(formVendedor, false);
			setCamposAtivos(formUsuario, true);

			btnUsuario.classList.add("ativo");
			btnVendedor.classList.remove("ativo");
		}

		btnUsuario.addEventListener("click", mostrarUsuario);
		btnVendedor.addEventListener("click", mostrarVendedor);

		mostrarVendedor();
	</script>

</body>
</html>
