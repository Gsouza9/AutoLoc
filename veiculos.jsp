<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="java.util.Locale"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("anuncios");
Integer paginaAtual = (Integer) request.getAttribute("paginaAtual");
Integer totalPaginas = (Integer) request.getAttribute("totalPaginas");
if (paginaAtual == null) paginaAtual = 1;
if (totalPaginas == null) totalPaginas = 1;

NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>AutoLoc | Veiculos</title>

<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />

<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
	rel="stylesheet" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/styles.css?v=20260519-theme" />

<script src="https://unpkg.com/lucide@latest/dist/umd/lucide.min.js"
	defer></script>
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet" href="css/veiculos.css">
</head>

<body data-page="vehicles">

	<header class="site-header">

		<a class="brand" href="${pageContext.request.contextPath}/consumidor">
			<span class="brand-mark">AL</span>
			<span class="brand-name">Auto<span>Loc</span></span>
		</a>

		<nav class="desktop-nav" aria-label="Navegacao principal">
			<a href="${pageContext.request.contextPath}/consumidor">Inicio</a>
			<a class="active" href="${pageContext.request.contextPath}/navegacao">Veiculos</a>
			<a href="servicos.jsp">Serviços</a>
			<a href="favoritos.jsp">Favoritos <span data-favorite-count>0</span></a>
		</nav>

		<div class="header-actions">
			<a class="icon-btn" href="favoritos.jsp" aria-label="Favoritos">
				<i data-lucide="heart"></i>
			</a>
			<a class="profile-link" href="perfil.jsp" aria-label="Perfil">
				<i data-lucide="user"></i>
			</a>
		</div>

	</header>

	<main class="container">

		<section class="page-hero vehicles-banner">
			<span class="eyebrow">Catalogo completo</span>
			<h1>Escolha com dados claros, fotos fortes e curadoria premium.</h1>
			<p>Filtre por marca, categoria, ano ou preco. Todos os veiculos
				passam por vistoria e validacao de procedencia.</p>
		</section>

		<section class="toolbar" aria-label="Busca e organizacao">
			<div class="search-box">
				<i data-lucide="search"></i>
				<input data-search type="search"
					placeholder="Buscar veiculo, marca, categoria ou combustivel" />
			</div>

			<select class="select" data-sort aria-label="Ordenar veiculos">
				<option value="recommended">Recomendados</option>
				<option value="price-asc">Menor preco</option>
				<option value="price-desc">Maior preco</option>
				<option value="year-desc">Mais novos</option>
			</select>
		</section>

		<section class="filters" aria-label="Filtros rapidos">
			<button class="filter active" type="button" data-filter-chip="Todos">Todos</button>
			<button class="filter" type="button" data-filter-chip="Esportivo">Esportivos</button>
			<button class="filter" type="button" data-filter-chip="SUV">SUV</button>
			<button class="filter" type="button" data-filter-chip="Porsche">Porsche</button>
			<button class="filter" type="button" data-filter-chip="BMW">BMW</button>
			<button class="filter" type="button" data-filter-chip="Audi">Audi</button>
		</section>

		<section class="content-section">

			<div class="section-title">
				<div>
					<span class="section-kicker">Disponiveis agora</span>
					<h2>Veiculos selecionados</h2>
				</div>
			</div>

			<div class="cars" data-vehicles-grid>
				<% if (lista != null && !lista.isEmpty()) {
					for (JavaBeans v : lista) { %>
				<article class="car-card"
					data-id="<%=v.getIdVenda()%>"
					data-title="<%=v.getTituloAnuncio()%>"
					data-description="<%=v.getDescricao() != null ? v.getDescricao() : ""%>"
					data-price="<%=v.getPreco()%>"
					data-year="<%=v.getAno()%>"
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
						<img src="${pageContext.request.contextPath}/uploads/<%=v.getImagem()%>"
							alt="<%=v.getTituloAnuncio()%>" />
					</div>

					<div class="car-content">
						<div>
							<h3><%=v.getTituloAnuncio()%></h3>
							<p><%=v.getDescricao() != null ? v.getDescricao() : ""%></p>
						</div>
						<div class="car-info">
							<span><%=v.getAno()%></span>
							<span><%=v.getQuilometragem()%> km</span>
							<span><%=v.getCombustivel()%></span>
						</div>
						<div class="car-footer">
							<div>
								<span class="price-label">A partir de</span>
								<strong class="price"><%=nf.format(v.getPreco())%></strong>
							</div>
						</div>
					</div>
				</article>
				<% } } else { %>
				<p>Nenhum veiculo cadastrado ainda.</p>
				<% } %>
			</div>

			<% if (totalPaginas > 1) { %>
			<div class="pagination">
				<% if (paginaAtual > 1) { %>
					<a href="${pageContext.request.contextPath}/navegacao?pagina=<%=paginaAtual - 1%>" class="page-btn">← Anterior</a>
				<% } %>

				<% for (int i = 1; i <= totalPaginas; i++) { %>
					<a href="${pageContext.request.contextPath}/navegacao?pagina=<%=i%>"
					   class="page-btn <%=i == paginaAtual ? "active" : ""%>"><%=i%></a>
				<% } %>

				<% if (paginaAtual < totalPaginas) { %>
					<a href="${pageContext.request.contextPath}/navegacao?pagina=<%=paginaAtual + 1%>" class="page-btn">Proxima →</a>
				<% } %>
			</div>
			<% } %>

		</section>

	</main>

	<nav class="bottom-nav" aria-label="Navegacao inferior">
		<a class="nav-item" href="${pageContext.request.contextPath}/consumidor"><i data-lucide="home"></i>Inicio</a>
		<a class="nav-item active" href="${pageContext.request.contextPath}/navegacao"><i data-lucide="car"></i>Veiculos</a>
		<a class="nav-item" href="servicos.jsp"><i data-lucide="wrench"></i>Serviços</a>
		<a class="nav-item" href="favoritos.jsp"><i data-lucide="heart"></i>Favoritos</a>
		<a class="nav-item" href="perfil.jsp"><i data-lucide="user"></i>Perfil</a>
	</nav>

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
					<button class="primary-btn" type="button" data-modal-interest
						data-interest="">Tenho interesse</button>
				</div>
			</div>
		</section>
	</div>
	<script src="js/scripts.js"></script>
</body>

</html>
