<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.JavaBeans" %>

<!DOCTYPE html>
<html lang="pt-BR">

<head>

<meta charset="UTF-8" />

<meta name="viewport"
	content="width=device-width, initial-scale=1.0" />

<title>AutoLoc | Servicos do Vendedor</title>

<link rel="preconnect"
	href="https://fonts.googleapis.com" />

<link rel="preconnect"
	href="https://fonts.gstatic.com"
	crossorigin />

<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
	rel="stylesheet" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css?v=2" />

<script
	src="https://unpkg.com/lucide@latest/dist/umd/lucide.min.js"
	defer></script>

<style>

.service-grid{
	display:grid;
	grid-template-columns:1fr 1fr;
	gap:24px;
}

.service-card-list{
	display:grid;
	gap:16px;
}

.service-item{
	background:#fff;
	border:1px solid #e5e7eb;
	border-radius:18px;
	padding:18px;
	display:grid;
	gap:12px;
	transition:.2s ease;
	box-shadow:0 10px 25px rgba(0,0,0,.04);
}

.service-item:hover{
	transform:translateY(-2px);
}

.service-top{
	display:flex;
	align-items:flex-start;
	justify-content:space-between;
	gap:14px;
}

.service-top strong{
	font-size:18px;
	color:#0f172a;
}

.service-category{
	display:inline-flex;
	align-items:center;
	justify-content:center;
	padding:8px 14px;
	border-radius:999px;
	background:#f8fafc;
	border:1px solid #e2e8f0;
	font-size:12px;
	font-weight:700;
	color:#334155;
}

.service-meta{
	display:flex;
	flex-wrap:wrap;
	gap:10px;
}

.service-badge{
	padding:8px 14px;
	border-radius:999px;
	background:#f8fafc;
	border:1px solid #e2e8f0;
	font-size:13px;
	font-weight:600;
	color:#475569;
}

.service-description{
	font-size:14px;
	line-height:1.6;
	color:#64748b;
}

.preview-image{
	width:100%;
	height:200px;
	border-radius:18px;
	border:2px dashed #d4af37;
	display:flex;
	align-items:center;
	justify-content:center;
	background:#0f172a;
	color:#fff;
	font-size:15px;
	overflow:hidden;
}

.preview-image img{
	width:100%;
	height:100%;
	object-fit:cover;
}

@media(max-width:1000px){

	.service-grid{
		grid-template-columns:1fr;
	}

}

</style>

</head>

<body data-page="seller-services">

<header class="site-header seller-header">

	<a class="brand"
		href="${pageContext.request.contextPath}/vendedorinicio.jsp">

		<span class="brand-mark">AL</span>

		<span class="brand-name">
			Auto<span>Loc</span>
		</span>

	</a>

	<nav class="desktop-nav"
		aria-label="Navegacao do vendedor">

		<a href="${pageContext.request.contextPath}/vendedorinicio.jsp">
			Inicio
		</a>

		<a href="${pageContext.request.contextPath}/vendedorpainel">
			Loja
		</a>

		<a href="${pageContext.request.contextPath}/vendedorveiculos">
			Veiculos
		</a>

		<a class="active"
			href="${pageContext.request.contextPath}/vendedorservicos">

			Servicos

		</a>

		<a href="${pageContext.request.contextPath}/consumidor.jsp">
			Ver consumidor
		</a>

	</nav>

	<div class="header-actions">

		<a class="icon-btn"
			href="${pageContext.request.contextPath}/logout"
			aria-label="Sair">

			<i data-lucide="log-out"></i>

		</a>

	</div>

</header>

<main class="seller-shell">

	<section class="seller-page-heading">

		<div>

			<span class="section-kicker">
				Servicos e produtos
			</span>

			<h1>
				Organize os servicos da sua empresa
			</h1>

			<p>
				Cadastre manutencoes, revisoes,
				produtos automotivos e deixe
				sua loja mais completa.
			</p>

		</div>

	</section>

	<section class="service-grid">

		<!-- FORM SERVICO -->
		<form class="seller-card seller-form"
			action="${pageContext.request.contextPath}/cadservico"
			method="post"
			enctype="multipart/form-data">

			<div class="seller-card-title">

				<span>
					<i data-lucide="wrench"></i>
				</span>

				<div>

					<h2>Cadastrar servico</h2>

					<p>
						Adicione os servicos oferecidos.
					</p>

				</div>

			</div>

			<label>

				Servico

				<input name="nomeServico"
					type="text"
					placeholder="Ex: Revisao completa"
					required />

			</label>

			<label>

				Categoria

				<select name="categoria">

					<option value="Mecanica">Mecanica</option>

					<option value="Eletrica">Eletrica</option>

					<option value="Borracharia">Borracharia</option>

					<option value="Funilaria">Funilaria</option>

					<option value="Estetica">Estetica</option>

					<option value="Vistoria">Vistoria</option>

				</select>

			</label>

			<label>

				Preco

				<input name="preco"
					type="text"
					placeholder="250,00" />

			</label>

			<label>

				Prazo medio

				<input name="tempoEstimado"
					type="text"
					placeholder="2 horas" />

			</label>

			<label>

				Status

				<select name="statusServico">

					<option value="ATIVO">Ativo</option>

					<option value="INATIVO">Inativo</option>

				</select>

			</label>

			<label class="full-field">

				Descricao

				<textarea name="descricao"
					rows="4"
					placeholder="Detalhe o servico"></textarea>

			</label>

			<label class="full-field">

				Foto do servico

				<input id="fotoServico"
					name="fotoServico"
					type="file"
					accept="image/*" />

			</label>

			<div class="preview-image full-field"
				id="previewServico">

				Preview da imagem

			</div>

			<button class="primary-btn"
				type="submit">

				Adicionar servico

			</button>

		</form>

		<!-- FORM PRODUTO -->
		<form class="seller-card seller-form"
			action="${pageContext.request.contextPath}/cadproduto"
			method="post"
			enctype="multipart/form-data">

			<div class="seller-card-title">

				<span>
					<i data-lucide="package"></i>
				</span>

				<div>

					<h2>Cadastrar produto</h2>

					<p>
						Adicione pecas e acessorios.
					</p>

				</div>

			</div>

			<label>

				Produto

				<input name="nomeProduto"
					type="text"
					placeholder="Ex: Kit de freio"
					required />

			</label>

			<label>

				Categoria

				<select name="categoriaProduto">

					<option value="Autopecas">Autopecas</option>

					<option value="Acessorios">Acessorios</option>

					<option value="Borracharia">Borracharia</option>

					<option value="Oleo e fluidos">Oleo e fluidos</option>

				</select>

			</label>

			<label>

				Preco

				<input name="precoProduto"
					type="text"
					placeholder="390,00" />

			</label>

			<label>

				Estoque

				<input name="estoqueProduto"
					type="number"
					min="0"
					step="1" />

			</label>

			<label>

				Status

				<select name="statusProduto">

					<option value="ATIVO">Ativo</option>

					<option value="ESGOTADO">Esgotado</option>

					<option value="INATIVO">Inativo</option>

				</select>

			</label>

			<label class="full-field">

				Descricao

				<textarea name="descricaoProduto"
					rows="4"
					placeholder="Descricao do produto"></textarea>

			</label>

			<label class="full-field">

				Foto do produto

				<input id="fotoProduto"
					name="fotoProduto"
					type="file"
					accept="image/*" />

			</label>

			<div class="preview-image full-field"
				id="previewProduto">

				Preview da imagem

			</div>

			<button class="primary-btn"
				type="submit">

				Adicionar produto

			</button>

		</form>

	</section>

	<section class="service-grid">

		<!-- SERVICOS -->
		<div class="seller-card">

			<div class="seller-card-title">

				<span>
					<i data-lucide="clipboard-list"></i>
				</span>

				<div>

					<h2>Servicos cadastrados</h2>

					<%
					ArrayList<JavaBeans> servicos =
						(ArrayList<JavaBeans>) request.getAttribute("servicos");

					int totalServicos =
						servicos == null ? 0 : servicos.size();
					%>

					<p>
						<%= totalServicos %> itens
					</p>

				</div>

			</div>

			<div class="service-card-list">

				<%
				if (servicos == null || servicos.isEmpty()) {
				%>

					<p>
						Nenhum servico cadastrado.
					</p>

				<%
				} else {

					for (JavaBeans s : servicos) {
				%>

					<div class="service-item">

						<div class="service-top">

							<div>

								<strong>
									<%= s.getServico() %>
								</strong>

							</div>

							<div class="service-category">

								<%= s.getCategoria() %>

							</div>

						</div>

						<div class="service-meta">

							<span class="service-badge">
								R$ <%= s.getPreco() %>
							</span>

							<span class="service-badge">
								<%= s.getPrazo() %>
							</span>

							<span class="service-badge">
								<%= s.getStatus() %>
							</span>

						</div>

						<div class="service-description">

							<%= s.getDescricao() %>

						</div>

					</div>

				<%
					}
				}
				%>

			</div>

		</div>

		<!-- PRODUTOS -->
		<div class="seller-card">

			<div class="seller-card-title">

				<span>
					<i data-lucide="boxes"></i>
				</span>

				<div>

					<h2>Produtos cadastrados</h2>

					<%
					ArrayList<JavaBeans> produtos =
						(ArrayList<JavaBeans>) request.getAttribute("produtos");

					int totalProdutos =
						produtos == null ? 0 : produtos.size();
					%>

					<p>
						<%= totalProdutos %> itens
					</p>

				</div>

			</div>

			<div class="service-card-list">

				<%
				if (produtos == null || produtos.isEmpty()) {
				%>

					<p>
						Nenhum produto cadastrado.
					</p>

				<%
				} else {

					for (JavaBeans p : produtos) {
				%>

					<div class="service-item">

						<div class="service-top">

							<div>

								<strong>
									<%= p.getNomeProduto() %>
								</strong>

							</div>

							<div class="service-category">

								<%= p.getCategoria() %>

							</div>

						</div>

						<div class="service-meta">

							<span class="service-badge">
								R$ <%= p.getPreco() %>
							</span>

							<span class="service-badge">
								Estoque:
								<%= p.getEstoque() %>
							</span>

							<span class="service-badge">
								<%= p.getStatus() %>
							</span>

						</div>

						<div class="service-description">

							<%= p.getDescricao() %>

						</div>

					</div>

				<%
					}
				}
				%>

			</div>

		</div>

	</section>

</main>

<script>

	document.addEventListener("DOMContentLoaded", function() {

		if (window.lucide) {
			lucide.createIcons();
		}

		function configurarPreview(inputId, previewId){

			const input =
				document.getElementById(inputId);

			const preview =
				document.getElementById(previewId);

			if(!input || !preview) return;

			input.addEventListener("change", function(e){

				const file = e.target.files[0];

				if(!file) return;

				const reader = new FileReader();

				reader.onload = function(event){

					preview.innerHTML =
						'<img src="' +
						event.target.result +
						'" alt="Preview">';

				};

				reader.readAsDataURL(file);

			});

		}

		configurarPreview(
			"fotoServico",
			"previewServico"
		);

		configurarPreview(
			"fotoProduto",
			"previewProduto"
		);

	});

</script>

<script
	src="${pageContext.request.contextPath}/js/scripts.js"></script>

</body>
</html>
