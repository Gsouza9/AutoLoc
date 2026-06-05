<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
String usuarioLogado = (String) session.getAttribute("usuarioLogado");
String tipoUsuario = (String) session.getAttribute("tipoUsuario");

if (usuarioLogado == null) {
	response.sendRedirect("login.jsp");
	return;
}

if (!"CLIENTE".equals(tipoUsuario)) {
	response.sendRedirect("vendedorpainel.jsp");
	return;
}

String nomeUsuario = (String) session.getAttribute("nomeUsuario");
String emailUsuario = (String) session.getAttribute("emailUsuario");
String telefoneUsuario = (String) session.getAttribute("telefoneUsuario");
String cpfUsuario = (String) session.getAttribute("cpfUsuario");

if (nomeUsuario == null) nomeUsuario = "";
if (emailUsuario == null) emailUsuario = "";
if (telefoneUsuario == null) telefoneUsuario = "";
if (cpfUsuario == null) cpfUsuario = "";

String iniciais = "US";

if (!nomeUsuario.trim().isEmpty()) {
	String[] partesNome = nomeUsuario.trim().split("\\s+");

	if (partesNome.length >= 2) {
		iniciais = partesNome[0].substring(0, 1).toUpperCase()
				+ partesNome[1].substring(0, 1).toUpperCase();
	} else {
		iniciais = partesNome[0].substring(0, 1).toUpperCase();
	}
}
%>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>AutoLoc | Perfil</title>

	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">
	
	<link rel="stylesheet" href="css/styles.css">

	<script src="https://unpkg.com/lucide@latest/dist/umd/lucide.min.js" defer></script>

	
</head>

<body data-page="profile">

<header class="site-header">

	<a class="brand" href="consumidor.jsp">
		<span class="brand-mark">AL</span>
		<span class="brand-name">Auto<span>Loc</span></span>
	</a>

	<nav class="desktop-nav" aria-label="Navegacao principal">
		<a href="consumidor.jsp">Inicio</a>
		<a href="veiculos.jsp">Veiculos</a>
		<a href="servicos.html">Servicos</a>
		<a href="favoritos.jsp">
			Favoritos <span data-favorite-count>0</span>
		</a>
	</nav>

	<div class="header-actions">
		<a class="icon-btn" href="favoritos.jsp" aria-label="Favoritos">
			<i data-lucide="heart"></i>
		</a>

		<a class="profile-link active" href="perfil.jsp" aria-label="Perfil">
			<i data-lucide="user"></i>
		</a>

		<a class="icon-btn" href="logout" aria-label="Sair">
			<i data-lucide="log-out"></i>
		</a>
	</div>

</header>

<main class="container">

	<section class="profile-hero">
		<div class="profile-avatar"><%= iniciais %></div>

		<div>
			<span class="eyebrow">Área do cliente</span>

			<h1><%= nomeUsuario %></h1>

			<p>
				<span><%= emailUsuario %></span>
				&middot;
				<span><%= telefoneUsuario %></span>
			</p>
		</div>
	</section>

	<section class="profile-grid">

		<form class="profile-card-large">

			<div class="section-title">
				<div>
					<span class="section-kicker">Dados pessoais</span>
					<h2>Meu perfil</h2>
				</div>
			</div>

			<label>
				Nome completo
				<input name="nome" type="text" value="<%= nomeUsuario %>" readonly>
			</label>

			<label>
				Email
				<input name="email" type="email" value="<%= emailUsuario %>" readonly>
			</label>

			<label>
				Telefone
				<input name="telefone" type="tel" value="<%= telefoneUsuario %>" readonly>
			</label>

			<label>
				CPF
				<input name="cpf" type="text" value="<%= cpfUsuario %>" readonly>
			</label>

			<label class="switch-row">
				<span>
					<strong>Notificações</strong>
					<small>Receber novidades e alertas dos favoritos.</small>
				</span>
				<input name="notifications" type="checkbox">
			</label>

			<label class="switch-row">
				<span>
					<strong>Financiamento</strong>
					<small>Permitir contato sobre simulações e condições.</small>
				</span>
				<input name="financing" type="checkbox">
			</label>

		</form>

		<aside class="profile-side">

			<article class="stat-card">
				<strong data-favorite-count>0</strong>
				<span>Favoritos salvos</span>
			</article>

			<article class="mini-card">
				<i data-lucide="shield-check"></i>
				<h3>Conta protegida</h3>
				<p>Seus dados de cadastro estão vinculados à sua conta AutoLoc.</p>
			</article>

			<article class="mini-card">
				<i data-lucide="headphones"></i>
				<h3>Consultoria ativa</h3>
				<p>Registre interesse em um veículo para iniciar o atendimento.</p>
			</article>

		</aside>

	</section>

</main>

<nav class="bottom-nav" aria-label="Navegacao inferior">

	<a class="nav-item" href="consumidor.jsp">
		<i data-lucide="home"></i>
		Inicio
	</a>

	<a class="nav-item" href="veiculos.jsp">
		<i data-lucide="car"></i>
		Veiculos
	</a>

	<a class="active" href="vendedorservicos">
		<i data-lucide="wrench"></i>
		Servicos
	</a>

	<a class="nav-item" href="favoritos.jsp">
		<i data-lucide="heart"></i>
		Favoritos
	</a>

	<a class="nav-item active" href="perfil.jsp">
		<i data-lucide="user"></i>
		Perfil
	</a>

</nav>

<script>
	document.addEventListener("DOMContentLoaded", function() {
		if (window.lucide) {
			lucide.createIcons();
		}
	});
</script>

</body>
</html>
