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
<title>AutoLoc | Veiculos do Vendedor</title>

<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
	rel="stylesheet" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
<script src="https://unpkg.com/lucide@latest/dist/umd/lucide.min.js" defer></script>

<style>
	.vehicle-list-organized {
		display: grid;
		gap: 16px;
	}

	.vehicle-card {
		border: 1px solid #e5e7eb;
		border-radius: 14px;
		padding: 16px;
		background: #fff;
		display: grid;
		gap: 14px;
	}

	.vehicle-card-header {
		display: flex;
		gap: 14px;
		align-items: flex-start;
	}

	.vehicle-thumb {
		width: 92px;
		height: 72px;
		border-radius: 10px;
		object-fit: cover;
		background: #eef2f7;
		flex: 0 0 auto;
	}

	.vehicle-thumb-empty {
		width: 92px;
		height: 72px;
		border-radius: 10px;
		background: #eef2f7;
		display: grid;
		place-items: center;
		color: #64748b;
		flex: 0 0 auto;
	}

	.vehicle-main {
		min-width: 0;
		flex: 1;
	}

	.vehicle-main strong {
		display: block;
		font-size: 16px;
		line-height: 1.25;
		color: #0f172a;
		margin-bottom: 4px;
	}

	.vehicle-main span {
		display: block;
		font-size: 13px;
		color: #475569;
		line-height: 1.35;
	}

	.vehicle-badges {
		display: flex;
		flex-wrap: wrap;
		gap: 8px;
	}

	.vehicle-badge {
		border: 1px solid #e5e7eb;
		background: #f8fafc;
		color: #334155;
		border-radius: 999px;
		padding: 6px 10px;
		font-size: 12px;
		font-weight: 600;
	}

	.vehicle-edit {
		display: grid;
		grid-template-columns: 1fr 1fr 1fr auto;
		gap: 10px;
		align-items: end;
		padding-top: 12px;
		border-top: 1px solid #e5e7eb;
	}

	.vehicle-edit label {
		font-size: 13px;
		font-weight: 700;
		color: #334155;
	}

	.vehicle-edit input,
	.vehicle-edit select {
		width: 100%;
		margin-top: 6px;
	}

	.vehicle-edit .primary-btn {
		min-height: 44px;
		white-space: nowrap;
	}

	@media (max-width: 900px) {
		.vehicle-edit {
			grid-template-columns: 1fr;
		}

		.vehicle-card-header {
			flex-direction: column;
		}

		.vehicle-thumb,
		.vehicle-thumb-empty {
			width: 100%;
			height: 160px;
		}
	}
</style>
</head>

<body data-page="seller-vehicles">

<header class="site-header seller-header">
	<a class="brand" href="${pageContext.request.contextPath}/vendedorinicio.jsp">
		<span class="brand-mark">AL</span>
		<span class="brand-name">Auto<span>Loc</span></span>
	</a>

	<nav class="desktop-nav" aria-label="Navegacao do vendedor">
		<a href="${pageContext.request.contextPath}/vendedorinicio.jsp">Inicio</a>
		<a href="${pageContext.request.contextPath}/vendedorpainel">Loja</a>
		<a class="active" href="${pageContext.request.contextPath}/vendedorveiculos">Veiculos</a>
		<a href="${pageContext.request.contextPath}/vendedorservicos">Servicos</a>
		<a href="${pageContext.request.contextPath}/consumidor.jsp">Ver consumidor</a>
	</nav>

	<div class="header-actions">
		<a class="icon-btn" href="${pageContext.request.contextPath}/index.jsp" aria-label="Sair" data-clear-role>
			<i data-lucide="log-out"></i>
		</a>
	</div>
</header>

<main class="seller-shell">

	<section class="seller-page-heading">
		<div>
			<span class="section-kicker">Estoque</span>
			<h1>Veiculos que voce esta vendendo.</h1>
			<p>Cadastre os anuncios e atualize status, quilometragem e cidade quando precisar.</p>
		</div>
	</section>

	<section class="seller-grid">

		<form class="seller-card seller-form"
			action="${pageContext.request.contextPath}/cadveiculo"
			method="post">

			<div class="seller-card-title">
				<span><i data-lucide="car"></i></span>
				<div>
					<h2>Cadastrar veiculo</h2>
					<p>Adicione os dados do carro e do anuncio.</p>
				</div>
			</div>

			<label>Titulo do anuncio
				<input name="tituloAnuncio" type="text" maxlength="150" required />
			</label>

			<label>Marca
				<input name="marca" type="text" maxlength="100" required />
			</label>

			<label>Modelo
				<input name="modelo" type="text" maxlength="100" required />
			</label>

			<label>Ano
				<input name="ano" type="number" min="1980" max="2030" required />
			</label>

			<label>Cor
				<input name="cor" type="text" maxlength="50" />
			</label>

			<label>Placa
				<input name="placa" type="text" maxlength="10" required />
			</label>

			<label>Chassi
				<input name="chassi" type="text" maxlength="50" />
			</label>

			<label>Combustivel
				<select name="combustivel" required>
					<option value="">Selecione</option>
					<option value="GASOLINA">Gasolina</option>
					<option value="ETANOL">Etanol</option>
					<option value="FLEX">Flex</option>
					<option value="DIESEL">Diesel</option>
					<option value="ELETRICO">Eletrico</option>
					<option value="HIBRIDO">Hibrido</option>
				</select>
			</label>

			<label>Preco
				<input name="preco" type="number" min="0" step="0.01" required />
			</label>

			<label>Descricao
				<textarea name="descricao" rows="3"></textarea>
			</label>

			<label>Status
				<select name="statusVenda">
					<option value="DISPONIVEL">Disponivel</option>
					<option value="RESERVADO">Reservado</option>
					<option value="VENDIDO">Vendido</option>
				</select>
			</label>

			<label>KM rodados
				<input name="kmRodados" type="number" min="0" />
			</label>

			<label>Cambio
				<select name="cambio">
					<option value="">Selecione</option>
					<option value="MANUAL">Manual</option>
					<option value="AUTOMATICO">Automatico</option>
					<option value="CVT">CVT</option>
				</select>
			</label>

			<label>Portas
				<input name="portas" type="number" min="2" max="6" />
			</label>

			<label>Final da placa
				<input name="finalPlaca" type="text" maxlength="1" />
			</label>

			<label>Cidade
				<input name="cidade" type="text" maxlength="100" />
			</label>

			<label>Estado
				<input name="estado" type="text" maxlength="2" />
			</label>

			<label>Aceita troca
				<select name="aceitaTroca">
					<option value="NAO">Nao</option>
					<option value="SIM">Sim</option>
				</select>
			</label>

			<label>Foto
				<input name="foto" type="text" maxlength="255" placeholder="URL da imagem" />
			</label>

			<button class="primary-btn" type="submit">Adicionar veiculo</button>
		</form>

		<div class="seller-card">
			<div class="seller-card-title">
				<span><i data-lucide="clipboard-list"></i></span>
				<div>
					<h2>Veiculos cadastrados</h2>
					<p><%=veiculos.size()%> itens</p>
				</div>
			</div>

			<div class="vehicle-list-organized">
				<% if (veiculos.isEmpty()) { %>

					<p>Nenhum veiculo cadastrado ainda.</p>

				<% } else { %>

					<% for (JavaBeans v : veiculos) { %>
						<div class="vehicle-card">

							<div class="vehicle-card-header">
								<% if (v.getImagem() != null && !v.getImagem().trim().isEmpty()) { %>
									<img class="vehicle-thumb" src="<%=v.getImagem()%>" alt="Foto do veiculo" />
								<% } else { %>
									<div class="vehicle-thumb-empty">
										<i data-lucide="car"></i>
									</div>
								<% } %>

								<div class="vehicle-main">
									<strong><%=v.getTituloAnuncio()%></strong>
									<span><%=v.getMarca()%> <%=v.getModelo()%> - <%=v.getAno()%></span>
									<span><%=v.getDescricao() != null ? v.getDescricao() : ""%></span>
								</div>
							</div>

							<div class="vehicle-badges">
								<span class="vehicle-badge">Status: <%=v.getStatus()%></span>
								<span class="vehicle-badge">R$ <%=v.getPreco()%></span>
								<span class="vehicle-badge">KM: <%=v.getQuilometragem()%></span>
								<span class="vehicle-badge">Cambio: <%=v.getCambio()%></span>
								<span class="vehicle-badge">Portas: <%=v.getPortas()%></span>
								<span class="vehicle-badge">Placa: <%=v.getPlaca()%></span>
								<span class="vehicle-badge">Final: <%=v.getFinalPlaca()%></span>
								<span class="vehicle-badge">Local: <%=v.getCidade()%> / <%=v.getEstado()%></span>
								<span class="vehicle-badge">Troca: <%=v.isAceitaTroca() ? "Sim" : "Nao"%></span>
							</div>

							<form class="vehicle-edit"
								action="${pageContext.request.contextPath}/editarDadosVeiculo"
								method="post">

								<input type="hidden" name="idVenda" value="<%=v.getIdVenda()%>" />

								<label>Status
									<select name="statusVenda">
										<option value="DISPONIVEL" <%= "DISPONIVEL".equals(v.getStatus()) ? "selected" : "" %>>Disponivel</option>
										<option value="RESERVADO" <%= "RESERVADO".equals(v.getStatus()) ? "selected" : "" %>>Reservado</option>
										<option value="VENDIDO" <%= "VENDIDO".equals(v.getStatus()) ? "selected" : "" %>>Vendido</option>
									</select>
								</label>

								<label>KM rodados
									<input name="kmRodados" type="number" min="0"
										value="<%=v.getQuilometragem()%>" />
								</label>

								<label>Cidade
									<input name="cidade" type="text" maxlength="100"
										value="<%=v.getCidade() != null ? v.getCidade() : ""%>" />
								</label>

								<button class="primary-btn" type="submit">Salvar</button>
							</form>

						</div>
					<% } %>

				<% } %>
			</div>
		</div>

	</section>
</main>

<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
</body>
</html>
