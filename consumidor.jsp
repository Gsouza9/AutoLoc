<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.NumberFormat"%>
<%
ArrayList<JavaBeans> destaques = (ArrayList<JavaBeans>) request.getAttribute("destaques");
if (destaques == null)
	destaques = new ArrayList<JavaBeans>();
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description"
	content="AutoLoc, plataforma premium para compra, financiamento e cuidado automotivo." />
<title>AutoLoc | Veiculos Premium</title>

<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
	rel="stylesheet" />

<link rel="stylesheet" href="styles.css?v=20260519-theme" />
<script src="https://unpkg.com/lucide@latest/dist/umd/lucide.min.js"
	defer></script>

<link rel="stylesheet" href="css/styles.css">

</head>

<body data-page="home">

	<header class="site-header">

		<a class="brand" href="${pageContext.request.contextPath}/consumidor"
			aria-label="AutoLoc, página inicial"> <span class="brand-mark">AL</span>
			<span class="brand-name">Auto<span>Loc</span></span>
		</a>

		<nav class="desktop-nav" aria-label="Navegação principal">
			<a class="active"
				href="${pageContext.request.contextPath}/consumidor">Início</a> <a
				href="${pageContext.request.contextPath}/navegacao">Veículos</a> <a
				href="servicos.jsp">Serviços</a> <a href="favoritos.jsp">
				Favoritos <span data-favorite-count>0</span>
			</a>
		</nav>

		<div class="header-actions">
			<button class="icon-btn" type="button" aria-label="Notificações">
				<i data-lucide="bell"></i>
			</button>

			<a class="profile-link" href="perfil.jsp"
				aria-label="Perfil do usuário"> <i data-lucide="user"></i>
			</a>
		</div>

	</header>

	<main class="container">

		<section class="hero" aria-labelledby="hero-title">

			<div class="hero-content">
				<span class="eyebrow">Oferta especial por tempo limitado</span>
				<h1 id="hero-title">Seu próximo carro premium.</h1>
				<p>Compare modelos selecionados, simule financiamento e reserve
					seu veículo com atendimento consultivo do início ao fim.</p>

				<div class="hero-actions">
				<a class="primary-btn" href="${pageContext.request.contextPath}/navegacao">Ver ofertas</a> 
					<a class="secondary-btn" href="#destaques">Explorar destaques</a>
				</div>
			</div>

			<aside class="hero-panel" aria-label="Resumo da oferta">
				<span>Entrada a partir de</span> <strong>0%</strong>
				<p>Aprovação rápida, histórico verificado e garantia premium
					inclusa.</p>
			</aside>

		</section>

		<section class="stats" aria-label="Indicadores AutoLoc">
			<article class="stat-card">
				<strong>+15 mil</strong><span>Vendas realizadas</span>
			</article>
			<article class="stat-card">
				<strong>98%</strong><span>Clientes satisfeitos</span>
			</article>
			<article class="stat-card">
				<strong>100%</strong><span>Veículos revisados</span>
			</article>
		</section>

		<section class="search-section" aria-label="Busca de veículos">
			<div class="search-box">
				<i data-lucide="search"></i> <input data-home-search type="search"
					placeholder="Buscar por marca, modelo ou categoria" />
			</div>

			<a class="filter-btn" href="veiculos"> <i
				data-lucide="sliders-horizontal"></i> Filtros
			</a>
		</section>

		<section id="destaques" class="content-section">
			<div class="section-title">
				<div>
					<span class="section-kicker">Curadoria AutoLoc</span>
					<h2>Veículos em destaque</h2>
				</div>
				<a href="${pageContext.request.contextPath}/navegacao">Ver ofertas</a>
			</div>

			<div class="cars" data-home-vehicles>
				<%
				for (JavaBeans v : destaques) {
				%>
				<article class="car-card" data-id="<%=v.getIdVenda()%>"
					data-title="<%=v.getTituloAnuncio()%>"
					data-description="<%=v.getDescricao() != null ? v.getDescricao() : ""%>"
					data-price="<%=v.getPreco()%>" data-year="<%=v.getAno()%>"
					data-km="<%=v.getQuilometragem()%>"
					data-fuel="<%=v.getCombustivel()%>"
					data-image="/autoloc/uploads/<%=v.getImagem()%>"
					data-brand="<%=v.getMarca()%>">

					<div class="car-image">
						<button class="favorite-btn" type="button"
							data-favorite="<%=v.getIdVenda()%>"
							aria-label="Alternar favorito">
							<i data-lucide="heart"></i>
						</button>
						<img
							src="${pageContext.request.contextPath}/uploads/<%=v.getImagem()%>"
							alt="<%=v.getTituloAnuncio()%>" />
					</div>

					<div class="car-content">
						<div>
							<h3><%=v.getTituloAnuncio()%></h3>
							<p><%=v.getDescricao() != null ? v.getDescricao() : ""%></p>
						</div>
						<div class="car-info">
							<span><%=v.getAno()%></span> <span><%=v.getQuilometragem()%>
								km</span> <span><%=v.getCombustivel()%></span>
						</div>
						<div class="car-footer">
							<div>
								<span class="price-label">A partir de</span> <strong
									class="price">R$ <%=v.getPreco()%></strong>
							</div>
						</div>
					</div>
				</article>
				<%
				}
				%>
			</div>
		</section>

		<section class="content-section">
			<div class="section-title">
				<div>
					<span class="section-kicker">Pós-venda completo</span>
					<h2>Serviços AutoLoc</h2>
				</div>
				<a href="servicos.jsp">Ver todos</a>
			</div>

			<div class="services" data-home-services></div>
		</section>

	</main>

	<nav class="bottom-nav" aria-label="Navegacao inferior">
		<a class="nav-item active"
			href="${pageContext.request.contextPath}/consumidor"><i
			data-lucide="home"></i>Início</a> <a class="nav-item"
			href="${pageContext.request.contextPath}/navegacao"><i
			data-lucide="car"></i>Veículos</a> <a class="nav-item"
			href="servicos.jsp"><i data-lucide="wrench"></i>Serviços</a> <a
			class="nav-item" href="favoritos.jsp"><i data-lucide="heart"></i>Favoritos</a>
		<a class="nav-item" href="perfil.jsp"><i data-lucide="user"></i>Perfil</a>
	</nav>

	<!-- MODAL -->
	<div class="modal" id="vehicle-modal" aria-hidden="true">
		<div class="modal-backdrop" data-close-modal></div>

		<section class="modal-card" aria-label="Detalhes do veiculo">
			<button class="modal-close" type="button" data-close-modal
				aria-label="Fechar">
				<i data-lucide="x"></i>
			</button>

			<img data-modal-image src="" alt="" />

			<div class="modal-body">
				<div class="modal-heading">
					<div>
						<span class="section-kicker">Detalhes do veiculo</span>
						<h2 data-modal-title></h2>
					</div>

					<button class="favorite-btn modal-fav" type="button"
						data-modal-favorite>
						<i data-lucide="heart"></i>
					</button>
				</div>

				<p data-modal-description></p>

				<div class="car-info modal-meta" data-modal-meta></div>

				<ul class="spec-list" data-modal-specs></ul>

				<div class="modal-footer">
					<strong data-modal-price></strong>
					<button class="primary-btn" type="button" data-modal-interest>
						Tenho interesse</button>
				</div>
			</div>
		</section>
	</div>
	<script src="js/scripts.js"></script>
</body>
</html>
