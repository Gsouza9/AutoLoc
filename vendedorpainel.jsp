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

<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
	rel="stylesheet" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css?v=2" />

<script
	src="https://unpkg.com/lucide@latest/dist/umd/lucide.min.js"
	defer></script>

</head>

<body data-page="seller-panel">

	<header class="site-header seller-header">

		<a class="brand"
			href="${pageContext.request.contextPath}/vendedorinicio.jsp">

			<span class="brand-mark">AL</span>

			<span class="brand-name">
				Auto<span>Loc</span>
			</span>

		</a>

		<nav class="desktop-nav" aria-label="Navegacao do vendedor">

			<a href="${pageContext.request.contextPath}/vendedorinicio.jsp">
				Inicio
			</a>

			<a class="active"
				href="${pageContext.request.contextPath}/vendedorpainel">
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
				aria-label="Sair">

				<i data-lucide="log-out"></i>

			</a>

		</div>

	</header>

	<main class="seller-shell">

		<section class="seller-page-heading">

			<div>

				<span class="section-kicker">
					Painel da loja
				</span>

				<h1>
					Cadastro e visualizacao da empresa
				</h1>

				<p>
					Mantenha os dados da sua loja atualizados para aparecer corretamente
					no mapa e para os consumidores.
				</p>

			</div>

			<a class="secondary-btn"
				href="${pageContext.request.contextPath}/consumidor.jsp">

				Ver como consumidor

			</a>

		</section>

		<section class="seller-grid seller-panel-grid">

			<!-- FORMULARIO -->
			<form class="seller-card seller-form"
				action="${pageContext.request.contextPath}/atualizarLoja"
				method="post"
				enctype="multipart/form-data">

				<div class="seller-card-title">

					<span>
						<i data-lucide="store"></i>
					</span>

					<div>

						<h2>Dados da loja</h2>

						<p>
							Atualize telefone, endereco, CEP, fotos e horario.
						</p>

					</div>

				</div>

				<label>
					Nome da loja

					<input type="text"
						value="<%= loja.getNomeEmpresa() != null ? loja.getNomeEmpresa() : "" %>"
						readonly />

				</label>

				<label>
					CNPJ

					<input type="text"
						value="<%= loja.getCnpj() != null ? loja.getCnpj() : "" %>"
						readonly />

				</label>

				<label>
					Categoria

					<input type="text"
						value="<%= loja.getCategoria() != null ? loja.getCategoria() : "" %>"
						readonly />

				</label>

				<label>
					Email

					<input type="email"
						value="<%= loja.getEmail() != null ? loja.getEmail() : "" %>"
						readonly />

				</label>

				<label>
					Telefone

					<input name="telefone"
						type="tel"
						placeholder="(11) 99999-0000"
						value="<%= loja.getNumeroTelefone() != null ? loja.getNumeroTelefone() : "" %>" />

				</label>

				<label>
					CEP

					<input name="cep"
						id="cep"
						type="text"
						maxlength="9"
						placeholder="00000-000" />

				</label>

				<label class="full-field">
					Endereco

					<input name="endereco"
						id="endereco"
						type="text"
						placeholder="Rua, avenida..."
						value="<%= loja.getEndereco() != null ? loja.getEndereco() : "" %>" />

				</label>

				<label>
					Numero

					<input name="numero"
						type="text"
						placeholder="123" />

				</label>

				<label>
					Cidade

					<input name="cidade"
						id="cidade"
						type="text"
						placeholder="Sao Paulo" />

				</label>

				<label>
					Estado

					<input name="estado"
						id="estado"
						type="text"
						maxlength="2"
						placeholder="SP" />

				</label>

				<label>
					Horario

					<input name="horario"
						type="text"
						placeholder="Seg a Sex - 08h as 18h"
						value="<%= loja.getHorario() != null ? loja.getHorario() : "" %>" />

				</label>

				<label class="full-field">

					Descricao

					<textarea name="descricao"
						rows="4"
						placeholder="Descreva sua loja"><%= loja.getDescricao() != null ? loja.getDescricao() : "" %></textarea>

				</label>

				<label class="full-field">

					Foto da empresa

					<input name="fotoEmpresa"
						type="file"
						accept="image/*" />

				</label>

				<button class="primary-btn" type="submit">
					Salvar alteracoes
				</button>

			</form>

			<!-- PREVIEW -->
			<aside class="seller-card seller-preview">

				<div class="seller-card-title">

					<span>
						<i data-lucide="badge-check"></i>
					</span>

					<div>

						<h2>Previa publica</h2>

						<p>
							Como sua empresa aparece para os consumidores.
						</p>

					</div>

				</div>

				<div class="preview-company">

					<div class="preview-banner">

						<div class="preview-logo">
							AL
						</div>

					</div>

					<div class="preview-content">

						<h3>
							<%= loja.getNomeEmpresa() != null
								? loja.getNomeEmpresa()
								: "Nome da loja" %>
						</h3>

						<span>
							<%= loja.getCategoria() != null
								? loja.getCategoria()
								: "Categoria" %>
						</span>

						<div class="preview-info">

							<p>
								<strong>Telefone:</strong>

								<%= loja.getNumeroTelefone() != null
									? loja.getNumeroTelefone()
									: "-" %>
							</p>

							<p>
								<strong>Endereco:</strong>

								<%= loja.getEndereco() != null
									? loja.getEndereco()
									: "-" %>
							</p>

							<p>
								<strong>Horario:</strong>

								<%= loja.getHorario() != null
									? loja.getHorario()
									: "-" %>
							</p>

						</div>

						<div class="preview-description">

							<%= loja.getDescricao() != null
								? loja.getDescricao()
								: "Sua descricao aparecera aqui." %>

						</div>

					</div>

				</div>

				<!-- MAPA -->
				<div class="map-section">

					<h3>Localizacao da empresa</h3>

					<div id="map"></div>

				</div>

			</aside>

		</section>

	</main>

	<script>

		document.addEventListener("DOMContentLoaded", function() {

			if (window.lucide) {
				lucide.createIcons();
			}

			const cepInput = document.getElementById("cep");

			cepInput.addEventListener("blur", async () => {

				const cep = cepInput.value.replace(/\D/g, "");

				if (cep.length !== 8) return;

				try {

					const response = await fetch(
						"https://viacep.com.br/ws/" + cep + "/json/"
					);

					const data = await response.json();

					if (!data.erro) {

						document.getElementById("endereco").value =
							data.logradouro || "";

						document.getElementById("cidade").value =
							data.localidade || "";

						document.getElementById("estado").value =
							data.uf || "";

					}

				} catch (error) {
					console.log("Erro ao buscar CEP");
				}

			});

		});

	</script>

	<script
		src="https://maps.googleapis.com/maps/api/js?key=SUA_API_KEY&callback=initMap"
		async
		defer></script>

	<script>

		function initMap() {

			const localizacao = {
				lat: -23.55052,
				lng: -46.633308
			};

			const map = new google.maps.Map(
				document.getElementById("map"),
				{
					zoom: 14,
					center: localizacao
				}
			);

			new google.maps.Marker({
				position: localizacao,
				map: map
			});

		}

	</script>

	<script
		src="${pageContext.request.contextPath}/js/scripts.js"></script>

</body>
</html>
