```jsp
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

		<!-- =========================
		     LOGIN SIDE
		========================= -->

		<section class="login-side">

			<div class="login-box">

				<!-- LOGO MOBILE -->
				<div class="logo-mobile">

					<img
						src="${pageContext.request.contextPath}/img/logoautolocksemfundo.png"
						alt="Logo AutoLoc">

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

				<!-- TITULO -->

				<h1>Bem-vindo!</h1>

				<p class="subtitle">
					Entre na sua conta para continuar na AutoLoc.
				</p>

				<!-- FORM -->

				<form action="loginu" method="post" class="login-form">

					<div class="mb-4">

						<label class="form-label">
							Email
						</label>

						<input type="email"
							name="email"
							class="form-control"
							placeholder="Digite seu email"
							autocomplete="email"
							required>

					</div>

					<div class="mb-3">

						<label class="form-label">
							Senha
						</label>

						<input type="password"
							name="senha"
							class="form-control"
							placeholder="Digite sua senha"
							autocomplete="current-password"
							required>

					</div>

					<!-- OPTIONS -->

					<div class="options">

						<label class="remember-me">

							<input type="checkbox">

							<span>
								Lembrar de mim
							</span>

						</label>

						<a href="#">
							Esqueceu a senha?
						</a>

					</div>

					<!-- BOTAO -->

					<button type="submit" class="login-btn">
						Entrar
					</button>

				</form>

				<!-- DIVIDER -->

				<div class="divider">
					<span>ou continue com</span>
				</div>

				<!-- GOOGLE -->

				<button class="google-btn" type="button">

					<img
						src="${pageContext.request.contextPath}/img/google.png"
						alt="Google">

					<span>
						Google
					</span>

				</button>

				<!-- TERMOS -->

				<p class="terms">

					Ao continuar, você concorda com nossos
					<strong>Termos de Uso</strong>
					e
					<strong>Política de Privacidade</strong>.

				</p>

			</div>

		</section>

		<!-- =========================
		     INFO SIDE
		========================= -->

		<section class="info-side">

			<div class="logo">

				<img class="logo-img"
					src="${pageContext.request.contextPath}/img/logoautolocksemfundo.png"
					alt="Logo AutoLoc">

			</div>

			<p class="top-text">
				Encontre oficinas, veículos e serviços automotivos com facilidade.
			</p>

			<!-- FEATURE -->

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

			<!-- FEATURE -->

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

			<!-- FEATURE -->

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

			<!-- STATS -->

			<div class="stats">

				<div class="stat-card">

					<h2>1.2K+</h2>

					<span>
						Oficinas
					</span>

				</div>

				<div class="stat-card">

					<h2>5K+</h2>

					<span>
						Usuários
					</span>

				</div>

				<div class="stat-card">

					<h2>4.9</h2>

					<span>
						Avaliação
					</span>

				</div>

			</div>

		</section>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>
```
