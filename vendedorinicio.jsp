<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-BR">

<head>

<meta charset="UTF-8" />

<meta name="viewport"
	content="width=device-width, initial-scale=1.0" />

<meta name="description"
	content="Painel inicial do vendedor AutoLoc." />

<title>AutoLoc | Inicio do Vendedor</title>

<link rel="preconnect"
	href="https://fonts.googleapis.com" />

<link rel="preconnect"
	href="https://fonts.gstatic.com"
	crossorigin />

<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
	rel="stylesheet" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css" />

<script
	src="https://unpkg.com/lucide@latest/dist/umd/lucide.min.js"
	defer></script>

</head>

<body data-page="seller-home">

<header class="site-header seller-header">

	<a class="brand"
		href="${pageContext.request.contextPath}/vendedorinicio.jsp"
		aria-label="Inicio do vendedor AutoLoc">

		<span class="brand-mark">
			AL
		</span>

		<span class="brand-name">
			Auto<span>Loc</span>
		</span>

	</a>

	<nav class="desktop-nav"
		aria-label="Navegacao do vendedor">

		<a class="active"
			href="${pageContext.request.contextPath}/vendedorinicio.jsp">

			Inicio

		</a>

		<a href="${pageContext.request.contextPath}/vendedorpainel">

			Loja

		</a>

		<a href="${pageContext.request.contextPath}/vendedorveiculos">

			Veiculos

		</a>

		<a href="${pageContext.request.contextPath}/vendedorservicos">

			Servicos

		</a>

		<a href="${pageContext.request.contextPath}/consumidor.jsp">

			Ver consumidor

		</a>

	</nav>

	<div class="header-actions">

		<a class="icon-btn"
			href="${pageContext.request.contextPath}/logout"
			aria-label="Sair"
			data-clear-role>

			<i data-lucide="log-out"></i>

		</a>

	</div>

</header>

<main class="seller-shell"
	data-seller-dashboard>

	<section class="seller-hero seller-hero-dashboard">

		<div>

			<span class="section-kicker">
				Painel do vendedor
			</span>

			<h1>
				Veja seu negocio como o consumidor ve.
			</h1>

			<p>
				Controle sua loja, estoque, servicos e produtos
				com acesso rapido as telas que impactam sua
				presenca no AutoLoc.
			</p>

			<div class="hero-actions">

				<a class="primary-btn"
					href="${pageContext.request.contextPath}/vendedorpainel">

					Gerenciar loja

				</a>

				<a class="secondary-btn"
					href="${pageContext.request.contextPath}/consumidor.jsp">

					Visualizar consumidor

				</a>

			</div>

		</div>

		<div class="seller-status"
			data-seller-status>

			Perfil em configuracao

		</div>

	</section>

	<section class="seller-stats"
		aria-label="Resumo do vendedor">

		<article class="stat-card">

			<strong data-seller-store-name>
				Loja sem nome
			</strong>

			<span>
				Loja cadastrada
			</span>

		</article>

		<article class="stat-card">

			<strong data-seller-vehicle-count>
				0
			</strong>

			<span>
				Veiculos ativos
			</span>

		</article>

		<article class="stat-card">

			<strong data-seller-service-count>
				0
			</strong>

			<span>
				Servicos ativos
			</span>

		</article>

		<article class="stat-card">

			<strong data-seller-product-count>
				0
			</strong>

			<span>
				Produtos ativos
			</span>

		</article>

	</section>

	<section class="seller-action-grid">

		<a class="seller-action-card"
			href="${pageContext.request.contextPath}/vendedorpainel">

			<i data-lucide="store"></i>

			<h2>
				Cadastro da loja
			</h2>

			<p>
				Atualize nome, telefone, endereco,
				horario, fotos e apresentacao publica.
			</p>

		</a>

		<a class="seller-action-card"
			href="${pageContext.request.contextPath}/vendedorveiculos">

			<i data-lucide="car"></i>

			<h2>
				Veiculos a venda
			</h2>

			<p>
				Cadastre o estoque que o consumidor
				podera encontrar e comparar.
			</p>

		</a>

		<a class="seller-action-card"
			href="${pageContext.request.contextPath}/vendedorservicos">

			<i data-lucide="wrench"></i>

			<h2>
				Servicos e produtos
			</h2>

			<p>
				Gerencie manutencoes, pecas,
				acessorios e disponibilidade.
			</p>

		</a>

		<a class="seller-action-card"
			href="${pageContext.request.contextPath}/consumidor.jsp">

			<i data-lucide="eye"></i>

			<h2>
				Visualizar consumidor
			</h2>

			<p>
				Abra a experiencia publica e veja
				como os clientes enxergam sua loja.
			</p>

		</a>

	</section>

	<section class="content-section seller-info-section">

		<div class="section-title">

			<div>

				<span class="section-kicker">
					Dicas AutoLoc
				</span>

				<h2>
					Melhore a visibilidade da sua loja
				</h2>

			</div>

		</div>

		<div class="seller-tips-grid">

			<article class="mini-card">

				<i data-lucide="image"></i>

				<h3>
					Adicione fotos
				</h3>

				<p>
					Lojas e veiculos com imagens recebem mais visualizacoes.
				</p>

			</article>

			<article class="mini-card">

				<i data-lucide="map-pin"></i>

				<h3>
					Mantenha o endereco atualizado
				</h3>

				<p>
					O endereco sera utilizado para exibir sua empresa no mapa.
				</p>

			</article>

			<article class="mini-card">

				<i data-lucide="badge-check"></i>

				<h3>
					Perfil completo
				</h3>

				<p>
					Preencha telefone, horario e descricao para gerar mais confianca.
				</p>

			</article>

		</div>

	</section>

</main>

<script
	src="${pageContext.request.contextPath}/js/scripts.js"></script>

<script>

	document.addEventListener("DOMContentLoaded", function () {

		if (window.lucide) {
			lucide.createIcons();
		}

	});

</script>

</body>

</html>
