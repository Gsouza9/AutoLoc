<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="pt-br">

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Cadastro - AutoLoc</title>

<link rel="icon" type="image/png"
	href="${pageContext.request.contextPath}/img/logocerta.png">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
	rel="stylesheet">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/cadastro.css">

</head>

<body>

```
<div class="main-container">

	<!-- ÁREA FORMULÁRIO -->
	<div class="login-side">

		<div class="login-box">

			<!-- LOGO MOBILE -->
			<div class="logo-mobile">

				<img
					src="${pageContext.request.contextPath}/img/logoautolocksemfundo.png">

			</div>

			<!-- TABS -->
			<div class="tabs">

				<button type="button"
					onclick="window.location.href='login.jsp'">

					Entrar

				</button>

				<button class="active" type="button">

					Criar Conta

				</button>

			</div>

			<h1>Crie sua conta</h1>

			<p class="subtitle">

				Cadastre-se para acessar a plataforma AutoLoc.

			</p>

			<!-- FORM -->
			<form action="cadastro" method="post">

				<div class="mb-4">

					<label class="form-label">

						Nome completo

					</label>

					<input type="text"
						name="nome"
						class="form-control"
						placeholder="Digite seu nome"
						required>

				</div>

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

				<div class="row-fields">

					<div class="field-group">

						<label>

							Telefone

						</label>

						<input type="text"
							name="telefone"
							class="form-control"
							placeholder="(11) 99999-9999"
							required>

					</div>

					<div class="field-group">

						<label>

							CPF

						</label>

						<input type="text"
							name="cpf"
							class="form-control"
							placeholder="000.000.000-00"
							required>

					</div>

				</div>

				<div class="row-fields">

					<div class="field-group">

						<label>

							CEP

						</label>

						<input type="text"
							name="cep"
							id="cep"
							class="form-control"
							placeholder="00000-000">

					</div>

					<div class="field-group">

						<label>

							Número

						</label>

						<input type="text"
							name="numero"
							class="form-control"
							placeholder="123">

					</div>

				</div>

				<div class="row-fields">

					<div class="field-group">

						<label>

							Cidade

						</label>

						<input type="text"
							name="cidade"
							class="form-control"
							placeholder="São Paulo">

					</div>

					<div class="field-group">

						<label>

							Estado

						</label>

						<input type="text"
							name="estado"
							maxlength="2"
							class="form-control"
							placeholder="SP">

					</div>

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

				<div class="mb-4">

					<label class="form-label">

						Tipo de conta

					</label>

					<select name="tipoUsuario"
						class="form-control"
						required>

						<option value="CLIENTE">

							Cliente

						</option>

						<option value="VENDEDOR">

							Vendedor

						</option>

					</select>

				</div>

				<button type="submit"
					class="login-btn">

					Criar conta

				</button>

			</form>

			<div class="divider">

				ou continue com

			</div>

			<button class="google-btn">

				<img
					src="${pageContext.request.contextPath}/img/google.png">

				<span>

					Google

				</span>

			</button>

			<p class="terms">

				Ao continuar, você concorda com nossos
				Termos de Uso e Política de Privacidade.

			</p>

		</div>

	</div>

	<!-- ÁREA INFORMATIVA -->
	<div class="info-side">

		<div class="logo">

			<img class="logo-img"
				src="${pageContext.request.contextPath}/img/logoautolocksemfundo.png">

		</div>

		<p class="top-text">

			Cadastre sua conta e tenha acesso ao ecossistema AutoLoc

		</p>

		<div class="feature">

			<div class="icon">

				🚗

			</div>

			<div>

				<h3>

					Veículos Premium

				</h3>

				<p>

					Encontre carros selecionados com segurança.

				</p>

			</div>

		</div>

		<div class="feature">

			<div class="icon">

				🛠️

			</div>

			<div>

				<h3>

					Serviços Automotivos

				</h3>

				<p>

					Oficinas, estética, revisão e muito mais.

				</p>

			</div>

		</div>

		<div class="feature">

			<div class="icon">

				📍

			</div>

			<div>

				<h3>

					Mapa Inteligente

				</h3>

				<p>

					Veja empresas próximas da sua região.

				</p>

			</div>

		</div>

		<div class="stats">

			<div class="stat-card">

				<h2>

					15K+

				</h2>

				<span>

					Veículos

				</span>

			</div>

			<div class="stat-card">

				<h2>

					8K+

				</h2>

				<span>

					Usuários

				</span>

			</div>

			<div class="stat-card">

				<h2>

					4.9

				</h2>

				<span>

					Avaliação

				</span>

			</div>

		</div>

	</div>

</div>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
```

</body>

</html>
