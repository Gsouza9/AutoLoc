<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.JavaBeans" %>

<%
JavaBeans loja = (JavaBeans) request.getAttribute("loja");

if (loja == null) {
	loja = new JavaBeans();
}
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>AutoLoc | Loja do Vendedor</title>

<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
<script src="https://unpkg.com/lucide@latest/dist/umd/lucide.min.js" defer></script>
</head>

<body data-page="seller-panel">

<header class="site-header seller-header">
	<a class="brand" href="${pageContext.request.contextPath}/vendedorinicio">
		<span class="brand-mark">AL</span>
		<span class="brand-name">Auto<span>Loc</span></span>
	</a>

	<nav class="desktop-nav" aria-label="Navegacao do vendedor">
		<a href="${pageContext.request.contextPath}/vendedorinicio">Inicio</a>
		<a class="active" href="${pageContext.request.contextPath}/vendedorpainel">Loja</a>
		<a href="${pageContext.request.contextPath}/vendedorveiculos">Veiculos</a>
		<a href="${pageContext.request.contextPath}/vendedorservicos">Servicos</a>
		<a href="${pageContext.request.contextPath}/consumidor">Ver consumidor</a>
	</nav>

	<div class="header-actions">
		<a class="icon-btn" href="${pageContext.request.contextPath}/login.jsp" aria-label="Sair" data-clear-role>
			<i data-lucide="log-out"></i>
		</a>
	</div>
</header>

<main class="seller-shell">

	<section class="seller-page-heading">
		<div>
			<span class="section-kicker">Loja</span>
			<h1>Cadastro e previa publica.</h1>
			<p>Mantenha telefone, endereco e horario atualizados para seus clientes.</p>
		</div>

		<a class="secondary-btn" href="${pageContext.request.contextPath}/consumidor">Ver como consumidor</a>
	</section>

	<section class="seller-grid">

		<form class="seller-card seller-form"
			action="${pageContext.request.contextPath}/atualizarLoja"
			method="post">

			<div class="seller-card-title">
				<span><i data-lucide="store"></i></span>
				<div>
					<h2>Dados da loja</h2>
					<p>Apenas telefone, endereco e horario podem ser editados.</p>
				</div>
			</div>

			<label>Nome da loja
				<input type="text" value="<%=loja.getNomeEmpresa() != null ? loja.getNomeEmpresa() : ""%>" readonly />
			</label>

			<label>CNPJ ou documento
				<input type="text" value="<%=loja.getCnpj() != null ? loja.getCnpj() : ""%>" readonly />
			</label>

			<label>Categoria principal
				<input type="text" value="<%=loja.getCategoria() != null ? loja.getCategoria() : ""%>" readonly />
			</label>

			<label>Email
				<input type="email" value="<%=loja.getEmail() != null ? loja.getEmail() : ""%>" readonly />
			</label>

			<label>Telefone
				<input name="telefone" type="tel"
					value="<%=loja.getNumeroTelefone() != null ? loja.getNumeroTelefone() : ""%>"
					placeholder="(11) 99999-0000" />
			</label>

			<label>Endereco
				<input name="endereco" type="text"
					value="<%=loja.getEndereco() != null ? loja.getEndereco() : ""%>"
					placeholder="Rua, numero, bairro" />
			</label>

			<label>Horario
				<input name="horario" type="text"
					value="<%=loja.getHorario() != null ? loja.getHorario() : ""%>"
					placeholder="Segunda a Sexta, 08:00 - 18:00" />
			</label>

			<label class="full-field">Descricao
				<textarea rows="4" readonly><%=loja.getDescricao() != null ? loja.getDescricao() : ""%></textarea>
			</label>

			<button class="primary-btn" type="submit">Salvar alteracoes</button>
		</form>

		<aside class="seller-card seller-preview">
			<div class="seller-card-title">
				<span><i data-lucide="badge-check"></i></span>
				<div>
					<h2>Previa publica</h2>
					<p>Resumo atual do cadastro.</p>
				</div>
			</div>

			<div class="seller-list">
				<div class="seller-list-item">
					<strong><%=loja.getNomeEmpresa() != null ? loja.getNomeEmpresa() : "Nome da loja"%></strong>
					<span>CNPJ: <%=loja.getCnpj() != null ? loja.getCnpj() : ""%></span>
					<span>Categoria: <%=loja.getCategoria() != null ? loja.getCategoria() : ""%></span>
					<span>Telefone: <%=loja.getNumeroTelefone() != null ? loja.getNumeroTelefone() : ""%></span>
					<span>Endereco: <%=loja.getEndereco() != null ? loja.getEndereco() : ""%></span>
					<span>Horario: <%=loja.getHorario() != null ? loja.getHorario() : ""%></span>
					<span><%=loja.getDescricao() != null ? loja.getDescricao() : ""%></span>
				</div>
			</div>
		</aside>

	</section>
</main>

<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
</body>
</html>
