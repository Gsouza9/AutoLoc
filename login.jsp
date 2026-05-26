<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login - AutoLoc</title>

<link rel="icon" type="image/png"
	href="${pageContext.request.contextPath}/img/logocerta.png">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
	rel="stylesheet">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">
</head>

<body>

	<div class="main-container">

		<!-- LOGIN -->
		<div class="login-side">

			<div class="login-box">

				<!-- LOGO MOBILE -->
				<div class="logo-mobile">
					<img
						src="${pageContext.request.contextPath}/img/logoautolocksemfundo.png">
				</div>

				<!-- TABS -->
				<div class="tabs">

					<button class="active" type="button">
						Entrar
					</button>

					<button type="button"
						onclick="window.location.href='cadastro.jsp'">
						Criar Conta
					</button>

				</div>

				<h1>Bem-vindo!</h1>

				<p class="subtitle">
					Entre na sua conta para continuar.
				</p>

				<!-- FORM -->
				<form action="loginu" method="post">

					<div class="mb-4">

						<label class="form-label">
							Email
						</label>

						<input type="email"
							name="email"
							class="form-control"
							placeholder="Digite seu email"
							required>

					</div>

					<div class="mb-4">

						<label class="form-label">
							Senha
						</label>

						<input type="password"
							name="senha"
							class="form-control"
							placeholder="Digite sua senha"
							required>

					</div>

					<div class="options">

						<label>
							<input type="checkbox">
							Lembrar de mim
						</label>

						<a href="#">
							Esqueceu a senha?
						</a>

					</div>

					<button type="submit" class="login-btn">
						Entrar
					</button>

				</form>

				<div class="divider">
					ou continue com
				</div>

				<button class="google-btn">

					<img
						src="${pageContext.request.contextPath}/img/google.png">

					<span>Google</span>

				</button>

				<p class="terms">
					Ao continuar, você concorda com nossos
					Termos de Uso e Política de Privacidade.
				</p>

			</div>

		</div>

		<!-- INFO -->
		<div class="info-side">

			<div class="logo">

				<img class="logo-img"
					src="${pageContext.request.contextPath}/img/logoautolocksemfundo.png">

			</div>

			<p class="top-text">
				Encontre oficinas e serviços automotivos com facilidade
			</p>

			<div class="feature">

				<div class="icon">
					🔍
				</div>

				<div>

					<h3>Busca Inteligente</h3>

					<p>
						Encontre oficinas e serviços próximos rapidamente.
					</p>

				</div>

			</div>

			<div class="feature">

				<div class="icon">
					⭐
				</div>

				<div>

					<h3>Avaliações Reais</h3>

					<p>
						Veja comentários de outros clientes.
					</p>

				</div>

			</div>

			<div class="feature">

				<div class="icon">
					⚡
				</div>

				<div>

					<h3>Atendimento Rápido</h3>

					<p>
						Entre em contato facilmente.
					</p>

				</div>

			</div>

			<div class="stats">

				<div class="stat-card">

					<h2>1.2K+</h2>

					<span>Oficinas</span>

				</div>

				<div class="stat-card">

					<h2>5K+</h2>

					<span>Usuários</span>

				</div>

				<div class="stat-card">

					<h2>4.9</h2>

					<span>Avaliação</span>

				</div>

			</div>

		</div>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>
