<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.JavaBeans"%>

<%
ArrayList<JavaBeans> veiculos =
	(ArrayList<JavaBeans>) request.getAttribute("veiculos");

if (veiculos == null) {
	veiculos = new ArrayList<JavaBeans>();
}
%>

<!DOCTYPE html>
<html lang="pt-BR">

<head>

<meta charset="UTF-8" />

<meta name="viewport"
	content="width=device-width, initial-scale=1.0" />

<title>AutoLoc | Veiculos do Vendedor</title>

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

.vehicle-list-organized{
	display:grid;
	gap:18px;
}

.vehicle-card{
	background:#ffffff;
	border:1px solid #e5e7eb;
	border-radius:20px;
	padding:20px;
	display:grid;
	gap:18px;
	box-shadow:0 10px 30px rgba(0,0,0,.04);
	transition:.2s ease;
}

.vehicle-card:hover{
	transform:translateY(-2px);
}

.vehicle-card-header{
	display:flex;
	gap:18px;
	align-items:flex-start;
}

.vehicle-thumb{
	width:150px;
	height:110px;
	border-radius:16px;
	object-fit:cover;
	background:#f1f5f9;
	border:1px solid #e2e8f0;
}

.vehicle-thumb-empty{
	width:150px;
	height:110px;
	border-radius:16px;
	background:#f8fafc;
	border:1px solid #e2e8f0;
	display:flex;
	align-items:center;
	justify-content:center;
	color:#64748b;
}

.vehicle-main{
	flex:1;
	min-width:0;
}

.vehicle-main strong{
	display:block;
	font-size:20px;
	font-weight:800;
	color:#0f172a;
	margin-bottom:6px;
}

.vehicle-main span{
	display:block;
	color:#475569;
	font-size:14px;
	line-height:1.5;
}

.vehicle-description{
	margin-top:10px;
	color:#64748b;
	font-size:14px;
	line-height:1.6;
}

.vehicle-badges{
	display:flex;
	flex-wrap:wrap;
	gap:10px;
}

.vehicle-badge{
	background:#f8fafc;
	border:1px solid #e2e8f0;
	padding:8px 14px;
	border-radius:999px;
	font-size:13px;
	font-weight:600;
	color:#334155;
}

.vehicle-edit{
	display:grid;
	grid-template-columns:1fr 1fr 1fr auto;
	gap:14px;
	align-items:end;
	padding-top:18px;
	border-top:1px solid #e5e7eb;
}

.vehicle-edit label{
	font-size:13px;
	font-weight:700;
	color:#334155;
}

.vehicle-edit input,
.vehicle-edit select{
	width:100%;
	margin-top:6px;
}

.vehicle-edit .primary-btn{
	min-height:46px;
	white-space:nowrap;
}

.upload-preview{
	width:100%;
	height:220px;
	border-radius:18px;
	border:2px dashed #d4af37;
	display:flex;
	align-items:center;
	justify-content:center;
	background:#0f172a;
	color:#fff;
	font-size:15px;
	overflow:hidden;
	position:relative;
}

.upload-preview img{
	width:100%;
	height:100%;
	object-fit:cover;
}

@media(max-width:900px){

	.vehicle-card-header{
		flex-direction:column;
	}

	.vehicle-thumb,
	.vehicle-thumb-empty{
		width:100%;
		height:220px;
	}

	.vehicle-edit{
		grid-template-columns:1fr;
	}

}

</style>

</head>

<body data-page="seller-vehicles">

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

		<a class="active"
			href="${pageContext.request.contextPath}/vendedorveiculos">

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
				Estoque da loja
			</span>

			<h1>
				Gerencie seus veiculos cadastrados
			</h1>

			<p>
				Adicione fotos, atualize status e mantenha
				seu estoque sempre organizado.
			</p>

		</div>

	</section>

	<section class="seller-grid">

		<!-- FORM -->
		<form class="seller-card seller-form"
			action="${pageContext.request.contextPath}/cadveiculo"
			method="post"
			enctype="multipart/form-data">

			<div class="seller-card-title">

				<span>
					<i data-lucide="car"></i>
				</span>

				<div>

					<h2>Cadastrar veiculo</h2>

					<p>
						Preencha os dados do anuncio.
					</p>

				</div>

			</div>

			<label>
				Titulo do anuncio

				<input name="tituloAnuncio"
					type="text"
					maxlength="150"
					required />

			</label>

			<label>
				Marca

				<input name="marca"
					type="text"
					maxlength="100"
					required />

			</label>

			<label>
				Modelo

				<input name="modelo"
					type="text"
					maxlength="100"
					required />

			</label>

			<label>
				Ano

				<input name="ano"
					type="number"
					min="1980"
					max="2035"
					required />

			</label>

			<label>
				Cor

				<input name="cor"
					type="text"
					maxlength="50" />

			</label>

			<label>
				Placa

				<input name="placa"
					type="text"
					maxlength="10"
					required />

			</label>

			<label>
				Combustivel

				<select name="combustivel">

					<option value="">Selecione</option>

					<option value="GASOLINA">Gasolina</option>

					<option value="ETANOL">Etanol</option>

					<option value="FLEX">Flex</option>

					<option value="DIESEL">Diesel</option>

					<option value="ELETRICO">Eletrico</option>

					<option value="HIBRIDO">Hibrido</option>

				</select>

			</label>

			<label>
				Preco

				<input name="preco"
					type="number"
					min="0"
					step="0.01"
					required />

			</label>

			<label>
				KM rodados

				<input name="kmRodados"
					type="number"
					min="0" />

			</label>

			<label>
				Cambio

				<select name="cambio">

					<option value="">Selecione</option>

					<option value="MANUAL">Manual</option>

					<option value="AUTOMATICO">Automatico</option>

					<option value="CVT">CVT</option>

				</select>

			</label>

			<label>
				Portas

				<input name="portas"
					type="number"
					min="2"
					max="6" />

			</label>

			<label>
				Cidade

				<input name="cidade"
					type="text"
					maxlength="100" />

			</label>

			<label>
				Estado

				<input name="estado"
					type="text"
					maxlength="2" />

			</label>

			<label>
				Status

				<select name="statusVenda">

					<option value="DISPONIVEL">
						Disponivel
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
				Aceita troca

				<select name="aceitaTroca">

					<option value="NAO">Nao</option>

					<option value="SIM">Sim</option>

				</select>

			</label>

			<label class="full-field">

				Descricao

				<textarea name="descricao"
					rows="4"></textarea>

			</label>

			<label class="full-field">

				Foto do veiculo

				<input id="fotoInput"
					name="foto"
					type="file"
					accept="image/*" />

			</label>

			<div class="upload-preview full-field"
				id="previewContainer">

				Preview da imagem

			</div>

			<button class="primary-btn"
				type="submit">

				Adicionar veiculo

			</button>

		</form>

		<!-- LISTA -->
		<div class="seller-card">

			<div class="seller-card-title">

				<span>
					<i data-lucide="clipboard-list"></i>
				</span>

				<div>

					<h2>Veiculos cadastrados</h2>

					<p>
						<%= veiculos.size() %> itens
					</p>

				</div>

			</div>

			<div class="vehicle-list-organized">

				<% if (veiculos.isEmpty()) { %>

					<p>
						Nenhum veiculo cadastrado ainda.
					</p>

				<% } else { %>

					<% for (JavaBeans v : veiculos) { %>

						<div class="vehicle-card">

							<div class="vehicle-card-header">

								<% if (
									v.getImagem() != null &&
									!v.getImagem().trim().isEmpty()
								) { %>

									<img class="vehicle-thumb"
										src="<%= v.getImagem() %>"
										alt="Foto do veiculo" />

								<% } else { %>

									<div class="vehicle-thumb-empty">

										<i data-lucide="car"></i>

									</div>

								<% } %>

								<div class="vehicle-main">

									<strong>
										<%= v.getTituloAnuncio() %>
									</strong>

									<span>
										<%= v.getMarca() %>
										<%= v.getModelo() %>
										-
										<%= v.getAno() %>
									</span>

									<div class="vehicle-description">

										<%= v.getDescricao() != null
											? v.getDescricao()
											: "" %>

									</div>

								</div>

							</div>

							<div class="vehicle-badges">

								<span class="vehicle-badge">
									Status:
									<%= v.getStatus() %>
								</span>

								<span class="vehicle-badge">
									R$
									<%= v.getPreco() %>
								</span>

								<span class="vehicle-badge">
									KM:
									<%= v.getQuilometragem() %>
								</span>

								<span class="vehicle-badge">
									<%= v.getCambio() %>
								</span>

								<span class="vehicle-badge">
									<%= v.getCidade() %>
									/
									<%= v.getEstado() %>
								</span>

								<span class="vehicle-badge">
									Troca:
									<%= v.isAceitaTroca()
										? "Sim"
										: "Nao" %>
								</span>

							</div>

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
											<%= "DISPONIVEL".equals(v.getStatus())
												? "selected"
												: "" %>>

											Disponivel

										</option>

										<option value="RESERVADO"
											<%= "RESERVADO".equals(v.getStatus())
												? "selected"
												: "" %>>

											Reservado

										</option>

										<option value="VENDIDO"
											<%= "VENDIDO".equals(v.getStatus())
												? "selected"
												: "" %>>

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
										value="<%= v.getCidade() != null
											? v.getCidade()
											: "" %>" />

								</label>

								<button class="primary-btn"
									type="submit">

									Salvar

								</button>

							</form>

						</div>

					<% } %>

				<% } %>

			</div>

		</div>

	</section>

</main>

<script>

	document.addEventListener("DOMContentLoaded", function() {

		if (window.lucide) {
			lucide.createIcons();
		}

		const fotoInput =
			document.getElementById("fotoInput");

		const preview =
			document.getElementById("previewContainer");

		if (fotoInput) {

			fotoInput.addEventListener("change", function(e) {

				const file = e.target.files[0];

				if (!file) return;

				const reader = new FileReader();

				reader.onload = function(event) {

					preview.innerHTML =
						'<img src="' +
						event.target.result +
						'" alt="Preview">';

				};

				reader.readAsDataURL(file);

			});

		}

	});

</script>

<script
	src="${pageContext.request.contextPath}/js/scripts.js"></script>

</body>
</html>
