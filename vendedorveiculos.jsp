<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.JavaBeans"%>

<%
ArrayList<JavaBeans> veiculos = (ArrayList<JavaBeans>) request.getAttribute("veiculos");

if (veiculos == null) {
	veiculos = new ArrayList<JavaBeans>();
}
%>

<!DOCTYPE html>
<html lang="pt-BR">

<head>

<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<title>AutoLoc | Veículos do Vendedor</title>

<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />

<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
	rel="stylesheet" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css?v=final-v2" />

<script
	src="https://unpkg.com/lucide@latest/dist/umd/lucide.min.js"
	defer></script>

</head>

<body data-page="seller-vehicles">

	<!-- HEADER -->
	<header class="site-header seller-header">

		<a class="brand"
			href="${pageContext.request.contextPath}/vendedorinicio.jsp">

			<span class="brand-mark">AL</span>

			<span class="brand-name">
				Auto<span>Loc</span>
			</span>

		</a>

		<nav class="desktop-nav" aria-label="Navegação do vendedor">

			<a href="${pageContext.request.contextPath}/vendedorinicio.jsp">
				Início
			</a>

			<a href="${pageContext.request.contextPath}/vendedorpainel">
				Loja
			</a>

			<a class="active"
				href="${pageContext.request.contextPath}/vendedorveiculos">
				Veículos
			</a>

			<a href="${pageContext.request.contextPath}/vendedorservicos">
				Serviços
			</a>

			<a href="${pageContext.request.contextPath}/consumidor.jsp">
				Ver consumidor
			</a>

		</nav>

		<div class="header-actions">

			<button class="theme-toggle"
				type="button"
				data-theme-toggle
				aria-label="Alternar tema">

				<i data-lucide="moon"></i>

			</button>

			<a class="icon-btn"
				href="${pageContext.request.contextPath}/logout"
				aria-label="Sair">

				<i data-lucide="log-out"></i>

			</a>

		</div>

	</header>

	<!-- MAIN -->
	<main class="seller-shell">

		<!-- HERO -->
		<section class="seller-page-heading seller-heading-glass">

			<div>

				<span class="section-kicker">
					Estoque da loja
				</span>

				<h1>
					Gerencie seus veículos cadastrados.
				</h1>

				<p>
					Atualize status, cidade, quilometragem, fotos e informações
					do anúncio em tempo real.
				</p>

			</div>

			<div class="seller-heading-actions">

				<div class="seller-mini-stat">

					<strong><%= veiculos.size() %></strong>

					<span>Veículos ativos</span>

				</div>

				<a class="secondary-btn"
					href="${pageContext.request.contextPath}/consumidor.jsp">

					Visualizar vitrine

				</a>

			</div>

		</section>

		<!-- GRID -->
		<section class="seller-grid seller-grid-vehicles">

			<!-- FORM -->
			<form class="seller-card seller-form seller-form-vehicle"
				action="${pageContext.request.contextPath}/cadveiculo"
				method="post">

				<div class="seller-card-title">

					<span class="seller-card-icon">
						<i data-lucide="car-front"></i>
					</span>

					<div>

						<h2>
							Cadastrar veículo
						</h2>

						<p>
							Adicione novos veículos ao catálogo AutoLoc.
						</p>

					</div>

				</div>

				<div class="form-grid">

					<label>
						Título do anúncio

						<input name="tituloAnuncio"
							type="text"
							maxlength="150"
							placeholder="Ex: BMW M4 Competition 2023"
							required />
					</label>

					<label>
						Marca

						<input name="marca"
							type="text"
							maxlength="100"
							placeholder="BMW"
							required />
					</label>

					<label>
						Modelo

						<input name="modelo"
							type="text"
							maxlength="100"
							placeholder="M4 Competition"
							required />
					</label>

					<label>
						Ano

						<input name="ano"
							type="number"
							min="1980"
							max="2035"
							placeholder="2024"
							required />
					</label>

					<label>
						Cor

						<input name="cor"
							type="text"
							placeholder="Preto"
							maxlength="50" />
					</label>

					<label>
						Placa

						<input name="placa"
							type="text"
							maxlength="10"
							placeholder="ABC1D23"
							required />
					</label>

					<label>
						Chassi

						<input name="chassi"
							type="text"
							maxlength="50"
							placeholder="9BWZZZ377VT004251" />
					</label>

					<label>
						Combustível

						<select name="combustivel" required>

							<option value="">
								Selecione
							</option>

							<option value="GASOLINA">
								Gasolina
							</option>

							<option value="ETANOL">
								Etanol
							</option>

							<option value="FLEX">
								Flex
							</option>

							<option value="DIESEL">
								Diesel
							</option>

							<option value="ELETRICO">
								Elétrico
							</option>

							<option value="HIBRIDO">
								Híbrido
							</option>

						</select>
					</label>

					<label>
						Preço

						<input name="preco"
							type="number"
							min="0"
							step="0.01"
							placeholder="250000"
							required />
					</label>

					<label>
						Status

						<select name="statusVenda">

							<option value="DISPONIVEL">
								Disponível
							</option>

							<option value="RESERVADO">
								Reservado
							</option>

							<option value="VENDIDO">
								Vendido
							</option>

						</select>
					</label>

					<label>
						KM rodados

						<input name="kmRodados"
							type="number"
							min="0"
							placeholder="12000" />
					</label>

					<label>
						Câmbio

						<select name="cambio">

							<option value="">
								Selecione
							</option>

							<option value="MANUAL">
								Manual
							</option>

							<option value="AUTOMATICO">
								Automático
							</option>

							<option value="CVT">
								CVT
							</option>

						</select>
					</label>

					<label>
						Portas

						<input name="portas"
							type="number"
							min="2"
							max="6"
							placeholder="4" />
					</label>

					<label>
						Final da placa

						<input name="finalPlaca"
							type="text"
							maxlength="1"
							placeholder="8" />
					</label>

					<label>
						Cidade

						<input name="cidade"
							type="text"
							maxlength="100"
							placeholder="São Paulo" />
					</label>

					<label>
						Estado

						<input name="estado"
							type="text"
							maxlength="2"
							placeholder="SP" />
					</label>

					<label>
						Aceita troca

						<select name="aceitaTroca">

							<option value="NAO">
								Não
							</option>

							<option value="SIM">
								Sim
							</option>

						</select>
					</label>

					<label class="full-field">
						Foto do veículo

						<input name="foto"
							type="text"
							maxlength="255"
							placeholder="https://imagem-do-veiculo.jpg"
							data-image-preview-input />
					</label>

					<div class="image-preview-box full-field"
						data-image-preview>

						<div class="preview-placeholder">

							<i data-lucide="image-plus"></i>

							<span>
								Pré-visualização da imagem
							</span>

						</div>

					</div>

					<label class="full-field">
						Descrição

						<textarea name="descricao"
							rows="5"
							placeholder="Descreva o estado do veículo, opcionais, histórico e diferenciais."></textarea>
					</label>

				</div>

				<button class="primary-btn"
					type="submit">

					<i data-lucide="plus"></i>

					Adicionar veículo

				</button>

			</form>

			<!-- LIST -->
			<div class="seller-card vehicle-stock-card">

				<div class="seller-card-title">

					<span class="seller-card-icon">
						<i data-lucide="clipboard-list"></i>
					</span>

					<div>

						<h2>
							Veículos cadastrados
						</h2>

						<p>
							<%= veiculos.size() %> veículo(s) encontrados
						</p>

					</div>

				</div>

				<!-- SEARCH -->
				<div class="seller-search-box">

					<i data-lucide="search"></i>

					<input type="search"
						placeholder="Buscar veículo..."
						data-seller-vehicle-search />

				</div>

				<!-- EMPTY -->
				<% if (veiculos.isEmpty()) { %>

				<div class="empty-state">

					<i data-lucide="car-front"></i>

					<h3>
						Nenhum veículo cadastrado
					</h3>

					<p>
						Seus anúncios aparecerão aqui.
					</p>

				</div>

				<% } else { %>

				<div class="vehicle-list-organized">

					<% for (JavaBeans v : veiculos) { %>

					<div class="vehicle-card"
						data-vehicle-card>

						<!-- TOP -->
						<div class="vehicle-card-header">

							<div class="vehicle-image-wrapper">

								<% if (v.getImagem() != null && !v.getImagem().trim().isEmpty()) { %>

								<img class="vehicle-thumb"
									src="<%= v.getImagem() %>"
									alt="Foto do veículo" />

								<% } else { %>

								<div class="vehicle-thumb-empty">

									<i data-lucide="image-off"></i>

								</div>

								<% } %>

							</div>

							<div class="vehicle-main">

								<div class="vehicle-main-top">

									<strong>
										<%= v.getTituloAnuncio() %>
									</strong>

									<span class="vehicle-price">
										R$ <%= v.getPreco() %>
									</span>

								</div>

								<span>
									<%= v.getMarca() %>
									<%= v.getModelo() %>
									-
									<%= v.getAno() %>
								</span>

								<p>
									<%= v.getDescricao() != null ? v.getDescricao() : "" %>
								</p>

							</div>

						</div>

						<!-- BADGES -->
						<div class="vehicle-badges">

							<span class="vehicle-badge badge-status">
								<%= v.getStatus() %>
							</span>

							<span class="vehicle-badge">
								KM: <%= v.getQuilometragem() %>
							</span>

							<span class="vehicle-badge">
								<%= v.getCambio() %>
							</span>

							<span class="vehicle-badge">
								<%= v.getCombustivel() %>
							</span>

							<span class="vehicle-badge">
								<%= v.getCidade() %> / <%= v.getEstado() %>
							</span>

							<span class="vehicle-badge">
								Placa: <%= v.getPlaca() %>
							</span>

							<span class="vehicle-badge">
								Troca:
								<%= v.isAceitaTroca() ? "Sim" : "Não" %>
							</span>

						</div>

						<!-- EDIT -->
						<form class="vehicle-edit"
							action="${pageContext.request.contextPath}/editarDadosVeiculo"
							method="post">

							<input type="hidden"
								name="idVenda"
								value="<%= v.getIdVenda() %>" />

							<label>
								Status

								<select name="statusVenda">

									<option value="DISPONIVEL"
										<%= "DISPONIVEL".equals(v.getStatus()) ? "selected" : "" %>>
										Disponível
									</option>

									<option value="RESERVADO"
										<%= "RESERVADO".equals(v.getStatus()) ? "selected" : "" %>>
										Reservado
									</option>

									<option value="VENDIDO"
										<%= "VENDIDO".equals(v.getStatus()) ? "selected" : "" %>>
										Vendido
									</option>

								</select>

							</label>

							<label>
								KM rodados

								<input name="kmRodados"
									type="number"
									min="0"
									value="<%= v.getQuilometragem() %>" />

							</label>

							<label>
								Cidade

								<input name="cidade"
									type="text"
									maxlength="100"
									value="<%= v.getCidade() != null ? v.getCidade() : "" %>" />

							</label>

							<button class="primary-btn"
								type="submit">

								<i data-lucide="save"></i>

								Salvar

							</button>

						</form>

					</div>

					<% } %>

				</div>

				<% } %>

			</div>

		</section>

	</main>

	<!-- TOAST -->
	<div class="toast-container"
		data-toast-container></div>

	<script
		src="${pageContext.request.contextPath}/js/scripts.js"></script>

	<script>

		document.addEventListener("DOMContentLoaded", function () {

			if (window.lucide) {
				lucide.createIcons();
			}

			/* PREVIEW IMAGEM */

			const imageInput = document.querySelector("[data-image-preview-input]");
			const previewBox = document.querySelector("[data-image-preview]");

			if (imageInput && previewBox) {

				imageInput.addEventListener("input", function () {

					const url = this.value.trim();

					if (!url) {

						previewBox.innerHTML = `
							<div class="preview-placeholder">
								<i data-lucide="image-plus"></i>
								<span>Pré-visualização da imagem</span>
							</div>
						`;

						if (window.lucide) {
							lucide.createIcons();
						}

						return;
					}

					previewBox.innerHTML = `
						<img src="${url}" class="preview-image" alt="Preview">
					`;
				});
			}

			/* SEARCH */

			const searchInput = document.querySelector("[data-seller-vehicle-search]");
			const vehicleCards = document.querySelectorAll("[data-vehicle-card]");

			if (searchInput) {

				searchInput.addEventListener("input", function () {

					const value = this.value.toLowerCase();

					vehicleCards.forEach(card => {

						const text = card.textContent.toLowerCase();

						card.style.display =
							text.includes(value)
								? "grid"
								: "none";
					});
				});
			}
		});

	</script>

</body>
</html>
