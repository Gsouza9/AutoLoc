<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>AutoLoc</title>

<link rel="icon" type="image/png"
	href="${pageContext.request.contextPath}/img/logoautolocksemfundo.png">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">

<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
	rel="stylesheet">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet">

<style>
body {
	background-color: rgb(0, 0, 0);
	background: linear-gradient(28deg, #D4AF37, black, #D4AF37);
}
</style>
</head>

<body>

	<div class="blur blur1"></div>
	<div class="blur blur2"></div>

	<div class="main-container">

		<!-- LOGIN -->
		<div class="right-side">

			<div class="login-box">

				<div class="logo-mobile">
					<img
						src="${pageContext.request.contextPath}/imagem/logoautolocksemfundo.png"
						alt="Logo">
				</div>

				<div class="tabs">
					<button class="active">Entrar</button>

					<button type="button"
						onclick="window.location.href='${pageContext.request.contextPath}/cadastro.jsp'">
						Criar Conta</button>
				</div>

				<h1>Bem-vindo!</h1>

				<p class="subtitle">Entre na sua conta para continuar.</p>

				<form action="${pageContext.request.contextPath}/loginu"
					method="post">

					<div class="input-group">
						<label>Email</label> <input type="email" name="email"
							placeholder="Digite seu email" required>
					</div>

					<div class="input-group">
						<label>Senha</label> <input type="password" id="senha"
							name="senha" placeholder="Digite sua senha" required>
					</div>

					<label class="mostrar-senha"> <input type="checkbox"
						onclick="mostrarSenha()"> Mostrar senha
					</label>

					<div class="options">
						<label> <input type="checkbox" name="lembrar">
							Lembrar de mim
						</label> <a href="#">Esqueceu a senha?</a>
					</div>

					<button type="submit" class="login-btn">Entrar</button>

				</form>

				<div class="divider">ou continue com</div>

				<div class="socials">

					<button class="social-btn google-btn" type="button">
						<img src="${pageContext.request.contextPath}/imagem/google.png"
							alt="Google"> <span>Google</span>
					</button>

				</div>

				<p class="terms">
					Ao continuar, você concorda com nossos <span>Termos de Uso</span> e
					<span>Política de Privacidade</span>.
				</p>

			</div>

		</div>

		<!-- LADO ESQUERDO -->
		<div class="left-side">

			<div class="logo">
				<img
					src="${pageContext.request.contextPath}/imagem/logoautolocksemfundo.png"
					class="logo-img" alt="Logo">
			</div>

			<p class="top-text">Encontre oficinas e serviços automotivos com
				facilidade</p>

			<div class="feature">
				<div class="icon">🔍</div>
				<div>
					<h3>Busca Inteligente</h3>
					<p>Encontre oficinas e serviços próximos rapidamente.</p>
				</div>
			</div>

			<div class="feature">
				<div class="icon">⭐</div>
				<div>
					<h3>Avaliações Reais</h3>
					<p>Veja comentários de outros clientes.</p>
				</div>
			</div>

			<div class="feature">
				<div class="icon">⚡</div>
				<div>
					<h3>Atendimento Rápido</h3>
					<p>Entre em contato facilmente.</p>
				</div>
			</div>

			<div class="stats">

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

	<script src="${pageContext.request.contextPath}/js/login.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
