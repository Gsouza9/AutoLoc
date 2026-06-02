<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-BR">

<head>

<meta charset="UTF-8" />

<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<meta name="description"
	content="Seus veículos favoritos salvos na AutoLoc." />

<title>AutoLoc | Favoritos</title>

<link rel="preconnect" href="https://fonts.googleapis.com" />

<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />

<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
	rel="stylesheet" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css" />

<script
	src="https://unpkg.com/lucide@latest/dist/umd/lucide.min.js"
	defer></script>

</head>

<body data-page="favorites">

	<header class="site-header">

		<a class="brand"
			href="${pageContext.request.contextPath}/consumidor.jsp"
			aria-label="AutoLoc, pagina inicial">

			<span class="brand-mark">AL</span>

			<span class="brand-name">
				Auto<span>Loc</span>
			</span>

		</a>

		<nav class="desktop-nav" aria-label="Navegacao principal">

			<a href="${pageContext.request.contextPath}/consumidor.jsp">
				Inicio
			</a>

			<a href="${pageContext.request.contextPath}/veiculos.jsp">
				Veiculos
			</a>

			<a href="${pageContext.request.contextPath}/servicos.jsp">
				Servicos
			</a>

			<a class="active"
				href="${pageContext.request.contextPath}/favoritos.jsp">

				Favoritos

				<span data-favorite-count>
					0
				</span>

			</a>

		</nav>

		<div class="header-actions">

			<button class="icon-btn"
				type="button"
				aria-label="Notificacoes">

				<i data-lucide="bell"></i>

			</button>

			<a class="profile-link"
				href="${pageContext.request.contextPath}/perfil.jsp"
				aria-label="Perfil">

				<i data-lucide="user"></i>

			</a>

		</div>

	</header>

	<main class="container">

		<section class="page-hero favorites-banner">

			<span class="eyebrow">
				Salvos por voce
			</span>

			<h1>
				Seus favoritos reunidos em um so lugar.
			</h1>

			<p>
				Acompanhe os modelos que voce mais gostou,
				compare opcoes e volte quando quiser.
			</p>

		</section>

		<section class="favorites-toolbar">

			<div class="search-box">

				<i data-lucide="search"></i>

				<input type="search"
					data-favorites-search
					placeholder="Buscar nos favoritos" />

			</div>

		</section>

		<section class="content-section">

			<div class="section-title">

				<div>

					<span class="section-kicker">
						Lista personalizada
					</span>

					<h2>
						Veiculos favoritos
					</h2>

				</div>

				<span class="favorites-count">
					<span data-favorite-count>0</span> itens
				</span>

			</div>

			<div class="cars"
				data-favorites-grid>

				<!-- CARDS VIA JAVASCRIPT -->

			</div>

			<div class="empty-state"
				data-empty-favorites
				style="display: none;">

				<i data-lucide="heart-off"></i>

				<h3>
					Nenhum favorito salvo
				</h3>

				<p>
					Adicione veiculos aos favoritos para visualizar aqui.
				</p>

				<a class="primary-btn"
					href="${pageContext.request.contextPath}/veiculos.jsp">

					Explorar veiculos

				</a>

			</div>

		</section>

	</main>

	<nav class="bottom-nav"
		aria-label="Navegacao inferior">

		<a class="nav-item"
			href="${pageContext.request.contextPath}/consumidor.jsp">

			<i data-lucide="home"></i>

			Inicio

		</a>

		<a class="nav-item"
			href="${pageContext.request.contextPath}/veiculos.jsp">

			<i data-lucide="car"></i>

			Veiculos

		</a>

		<a class="nav-item"
			href="${pageContext.request.contextPath}/servicos.jsp">

			<i data-lucide="wrench"></i>

			Servicos

		</a>

		<a class="nav-item active"
			href="${pageContext.request.contextPath}/favoritos.jsp">

			<i data-lucide="heart"></i>

			Favoritos

		</a>

		<a class="nav-item"
			href="${pageContext.request.contextPath}/perfil.jsp">

			<i data-lucide="user"></i>

			Perfil

		</a>

	</nav>

	<!-- MODAL -->

	<div class="modal"
		id="vehicle-modal"
		aria-hidden="true">

		<div class="modal-backdrop"
			data-close-modal></div>

		<section class="modal-card"
			aria-label="Detalhes do veiculo">

			<button class="modal-close"
				type="button"
				data-close-modal
				aria-label="Fechar">

				<i data-lucide="x"></i>

			</button>

			<img data-modal-image
				src=""
				alt="" />

			<div class="modal-body">

				<div class="modal-heading">

					<div>

						<span class="section-kicker">
							Detalhes do veiculo
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

	<script
		src="${pageContext.request.contextPath}/js/scripts.js"></script>

	<script>

		document.addEventListener("DOMContentLoaded", function () {

			if (window.lucide) {
				lucide.createIcons();
			}

			const emptyState = document.querySelector("[data-empty-favorites]");
			const grid = document.querySelector("[data-favorites-grid]");

			function checkFavorites() {

				if (!grid) return;

				const hasCards = grid.children.length > 0;

				if (!hasCards) {
					emptyState.style.display = "flex";
				} else {
					emptyState.style.display = "none";
				}
			}

			checkFavorites();

		});

	</script>

</body>

</html>
