```jsp id="f4rjka"
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-BR">

<head>

	<meta charset="UTF-8" />

	<meta name="viewport"
		content="width=device-width, initial-scale=1.0" />

	<meta name="description"
		content="AutoLoc, plataforma premium para compra, financiamento e cuidado automotivo." />

	<title>AutoLoc | Veículos Premium</title>

	<!-- FONTS -->

	<link rel="preconnect"
		href="https://fonts.googleapis.com" />

	<link rel="preconnect"
		href="https://fonts.gstatic.com"
		crossorigin />

	<link
		href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
		rel="stylesheet" />

	<!-- CSS -->

	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/css/styles.css">

	<!-- ICONS -->

	<script
		src="https://unpkg.com/lucide@latest/dist/umd/lucide.min.js"
		defer></script>

</head>

<body data-page="home">

	<!-- =========================
	     HEADER
	========================= -->

	<header class="site-header">

		<a class="brand"
			href="consumidor.jsp"
			aria-label="AutoLoc página inicial">

			<span class="brand-mark">
				AL
			</span>

			<span class="brand-name">
				Auto<span>Loc</span>
			</span>

		</a>

		<!-- NAV DESKTOP -->

		<nav class="desktop-nav"
			aria-label="Navegação principal">

			<a class="active"
				href="consumidor.jsp">
				Início
			</a>

			<a href="veiculos.jsp">
				Veículos
			</a>

			<a href="servicos.jsp">
				Serviços
			</a>

			<a href="favoritos.jsp">
				Favoritos
				<span data-favorite-count>0</span>
			</a>

		</nav>

		<!-- ACTIONS -->

		<div class="header-actions">

			<button class="icon-btn"
				type="button"
				aria-label="Notificações">

				<i data-lucide="bell"></i>

			</button>

			<a class="profile-link"
				href="perfil.jsp"
				aria-label="Perfil do usuário">

				<i data-lucide="user"></i>

			</a>

		</div>

	</header>

	<!-- =========================
	     MAIN
	========================= -->

	<main class="container">

		<!-- HERO -->

		<section class="hero"
			aria-labelledby="hero-title">

			<div class="hero-content">

				<span class="eyebrow">
					Oferta especial por tempo limitado
				</span>

				<h1 id="hero-title">
					Seu próximo carro premium.
				</h1>

				<p>
					Compare modelos selecionados,
					simule financiamento e reserve seu veículo
					com atendimento consultivo do início ao fim.
				</p>

				<div class="hero-actions">

					<a class="primary-btn"
						href="veiculos.jsp">

						Ver ofertas

					</a>

					<a class="secondary-btn"
						href="#destaques">

						Explorar destaques

					</a>

				</div>

			</div>

			<!-- HERO SIDE -->

			<aside class="hero-panel"
				aria-label="Resumo da oferta">

				<span>
					Entrada a partir de
				</span>

				<strong>
					0%
				</strong>

				<p>
					Aprovação rápida,
					histórico verificado e garantia premium inclusa.
				</p>

			</aside>

		</section>

		<!-- STATS -->

		<section class="stats"
			aria-label="Indicadores AutoLoc">

			<article class="stat-card">

				<strong>+15 mil</strong>

				<span>
					Vendas realizadas
				</span>

			</article>

			<article class="stat-card">

				<strong>98%</strong>

				<span>
					Clientes satisfeitos
				</span>

			</article>

			<article class="stat-card">

				<strong>100%</strong>

				<span>
					Veículos revisados
				</span>

			</article>

		</section>

		<!-- SEARCH -->

		<section class="search-section"
			aria-label="Busca de veículos">

			<div class="search-box">

				<i data-lucide="search"></i>

				<input
					data-home-search
					type="search"
					placeholder="Buscar por marca, modelo ou categoria" />

			</div>

			<a class="filter-btn"
				href="veiculos.jsp">

				<i data-lucide="sliders-horizontal"></i>

				Filtros

			</a>

		</section>

		<!-- VEICULOS -->

		<section id="destaques"
			class="content-section">

			<div class="section-title">

				<div>

					<span class="section-kicker">
						Curadoria AutoLoc
					</span>

					<h2>
						Veículos em destaque
					</h2>

				</div>

				<a href="veiculos.jsp">
					Ver todos
				</a>

			</div>

			<div class="cars"
				data-home-vehicles>

			</div>

		</section>

		<!-- SERVICOS -->

		<section class="content-section">

			<div class="section-title">

				<div>

					<span class="section-kicker">
						Pós-venda completo
					</span>

					<h2>
						Serviços AutoLoc
					</h2>

				</div>

				<a href="servicos.jsp">
					Ver todos
				</a>

			</div>

			<div class="services"
				data-home-services>

			</div>

		</section>

		<!-- MAPA FUTURO -->

		<section class="content-section">

			<div class="section-title">

				<div>

					<span class="section-kicker">
						Localização
					</span>

					<h2>
						Empresas próximas
					</h2>

				</div>

			</div>

			<div id="map"
				class="map-container">

				<!-- MAPA GOOGLE / LEAFLET -->
				<!-- EMPRESAS CADASTRADAS APARECERÃO AQUI -->

			</div>

		</section>

	</main>

	<!-- =========================
	     BOTTOM NAV
	========================= -->

	<nav class="bottom-nav"
		aria-label="Navegação inferior">

		<a class="nav-item active"
			href="consumidor.jsp">

			<i data-lucide="home"></i>
			Início

		</a>

		<a class="nav-item"
			href="veiculos.jsp">

			<i data-lucide="car"></i>
			Veículos

		</a>

		<a class="nav-item"
			href="servicos.jsp">

			<i data-lucide="wrench"></i>
			Serviços

		</a>

		<a class="nav-item"
			href="favoritos.jsp">

			<i data-lucide="heart"></i>
			Favoritos

		</a>

		<a class="nav-item"
			href="perfil.jsp">

			<i data-lucide="user"></i>
			Perfil

		</a>

	</nav>

	<!-- =========================
	     MODAL
	========================= -->

	<div class="modal"
		id="vehicle-modal"
		aria-hidden="true">

		<div class="modal-backdrop"
			data-close-modal></div>

		<section class="modal-card"
			aria-label="Detalhes do veículo">

			<button class="modal-close"
				type="button"
				data-close-modal
				aria-label="Fechar">

				<i data-lucide="x"></i>

			</button>

			<img
				data-modal-image
				src=""
				alt="Imagem do veículo" />

			<div class="modal-body">

				<div class="modal-heading">

					<div>

						<span class="section-kicker">
							Detalhes do veículo
						</span>

						<h2 data-modal-title></h2>

					</div>

					<button class="favorite-btn modal-fav"
						type="button"
						data-modal-favorite>

						<i data-lucide="heart"></i>

					</button>

				</div>

				<p data-modal-description></p>

				<div class="car-info modal-meta"
					data-modal-meta></div>

				<ul class="spec-list"
					data-modal-specs></ul>

				<div class="modal-footer">

					<strong data-modal-price></strong>

					<button class="primary-btn"
						type="button"
						data-modal-interest>

						Tenho interesse

					</button>

				</div>

			</div>

		</section>

	</div>

	<!-- SCRIPTS -->

	<script src="${pageContext.request.contextPath}/js/scripts.js"></script>

	<script>

		document.addEventListener("DOMContentLoaded", function () {

			if (window.lucide) {
				lucide.createIcons();
			}

		});

	</script>

</body>

</html>
```
