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

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/vendedorveiculos.css" />
<script src="https://unpkg.com/lucide@latest/dist/umd/lucide.min.js"
	defer></script>

</head>

<body data-page="seller-vehicles">

	<header class="site-header seller-header">
		<a class="brand" href="${pageContext.request.contextPath}/vendedorinicio">
		<span class="brand-mark">AL</span>
		<span class="brand-name">Auto<span>Loc</span></span>
	</a>

		<nav class="desktop-nav" aria-label="Navegacao do vendedor">
			<a href="${pageContext.request.contextPath}/vendedorinicio">Inicio</a>
			<a href="${pageContext.request.contextPath}/vendedorpainel">Loja</a>
			<a class="active" href="${pageContext.request.contextPath}/vendedorveiculos">Veiculos</a>
			<a href="${pageContext.request.contextPath}/vendedorservicos">Servicos</a>
			<a href="${pageContext.request.contextPath}/consumidor">Ver consumidor</a>
	
		</nav>

		<div class="header-actions">
			<a class="icon-btn"
				href="${pageContext.request.contextPath}/login.jsp"
				aria-label="Sair" data-clear-role> <i data-lucide="log-out"></i>
			</a>
		</div>
	</header>

	<main class="seller-shell">

		<section class="seller-page-heading">
			<div>
				<span class="section-kicker">Estoque</span>
				<h1>Veiculos que voce esta vendendo.</h1>
				<p>Cadastre os anuncios e atualize status, quilometragem e
					cidade quando precisar.</p>
			</div>
		</section>

		<section class="seller-grid">

			<form class="seller-card seller-form"
				action="${pageContext.request.contextPath}/cadveiculo" method="post"
				enctype="multipart/form-data">

				<div class="seller-card-title">
					<span><i data-lucide="car"></i></span>
					<div>
						<h2>Cadastrar veiculo</h2>
						<p>Adicione os dados do carro e do anuncio.</p>
					</div>
				</div>

				<label>Titulo do anuncio <input name="tituloAnuncio"
					type="text" maxlength="150" required />
				</label> <label>Marca <input name="marca" type="text"
					maxlength="100" required />
				</label> <label>Modelo <input name="modelo" type="text"
					maxlength="100" required />
				</label> <label>Ano <input name="ano" type="number" min="1980"
					max="2030" required />
				</label> <label>Cor <input name="cor" type="text" maxlength="50" />
				</label> <label>Placa <input name="placa" type="text" maxlength="10"
					required />
				</label> <label>Chassi <input name="chassi" type="text"
					maxlength="50" />
				</label> <label>Combustivel <select name="combustivel" required>
						<option value="">Selecione</option>
						<option value="GASOLINA">Gasolina</option>
						<option value="ETANOL">Etanol</option>
						<option value="FLEX">Flex</option>
						<option value="DIESEL">Diesel</option>
						<option value="ELETRICO">Eletrico</option>
						<option value="HIBRIDO">Hibrido</option>
				</select>
				</label> <label>Preco <input name="preco" type="number" min="0"
					step="0.01" required />
				</label> <label>Descricao <textarea name="descricao" rows="3"></textarea>
				</label> <label>Status <select name="statusVenda">
						<option value="DISPONIVEL">Disponivel</option>
						<option value="RESERVADO">Reservado</option>
						<option value="VENDIDO">Vendido</option>
				</select>
				</label> <label>KM rodados <input name="kmRodados" type="number"
					min="0" />
				</label> <label>Cambio <select name="cambio">
						<option value="">Selecione</option>
						<option value="MANUAL">Manual</option>
						<option value="AUTOMATICO">Automatico</option>
						<option value="CVT">CVT</option>
				</select>
				</label> <label>Portas <input name="portas" type="number" min="2"
					max="6" />
				</label> <label>Final da placa <input name="finalPlaca" type="text"
					maxlength="1" />
				</label> <label>Cidade <input name="cidade" type="text"
					maxlength="100" />
				</label> <label>Estado <input name="estado" type="text"
					maxlength="2" />
				</label> <label>Aceita troca <select name="aceitaTroca">
						<option value="NAO">Nao</option>
						<option value="SIM">Sim</option>
				</select>
				</label> <label for="foto" class="btn-foto">📷 Escolher Foto</label> <input
					type="file" id="foto" name="foto" accept="image/*" hidden>
				<div id="previewContainer" style="margin-top: 8px; display: none;">
					<img id="previewFoto" src="" alt="Preview"
						style="width: 100%; max-height: 200px; object-fit: cover; border-radius: 10px;" />
				</div>

				<button class="primary-btn" type="submit">Adicionar veiculo</button>
			</form>

			<div class="seller-card">
				<div class="seller-card-title">
					<span><i data-lucide="clipboard-list"></i></span>

					<div>
						<h2>Veiculos cadastrados</h2>
						<p><%=veiculos.size()%>
							itens
						</p>
					</div>
				</div>

				<div class="vehicle-list-organized">
					<%
					if (veiculos.isEmpty()) {
					%>

					<p>Nenhum veiculo cadastrado ainda.</p>

					<%
					} else {
					%>

					<%
					for (JavaBeans v : veiculos) {
					%>
					<div class="vehicle-card" data-vehicle-item>

						<div class="vehicle-card-header">
							<%
							if (v.getImagem() != null && !v.getImagem().trim().isEmpty()) {
							%>
							<img class="vehicle-thumb"
								src="${pageContext.request.contextPath}/uploads/<%=v.getImagem()%>"
								alt="Foto do veiculo" />
							<%
							} else {
							%>
							<div class="vehicle-thumb-empty">
								<i data-lucide="car"></i>
							</div>
							<%
							}
							%>

							<div class="vehicle-main">
								<strong><%=v.getTituloAnuncio()%></strong> <span><%=v.getMarca()%>
									<%=v.getModelo()%> - <%=v.getAno()%></span> <span><%=v.getDescricao() != null ? v.getDescricao() : ""%></span>
							</div>
						</div>

						<div class="vehicle-badges">
							<span class="vehicle-badge">Status: <%=v.getStatus()%></span> <span
								class="vehicle-badge">R$ <%=v.getPreco()%></span> <span
								class="vehicle-badge">KM: <%=v.getQuilometragem()%></span> <span
								class="vehicle-badge">Cambio: <%=v.getCambio()%></span> <span
								class="vehicle-badge">Portas: <%=v.getPortas()%></span> <span
								class="vehicle-badge">Placa: <%=v.getPlaca()%></span> <span
								class="vehicle-badge">Final: <%=v.getFinalPlaca()%></span> <span
								class="vehicle-badge">Local: <%=v.getCidade()%> / <%=v.getEstado()%></span>
							<span class="vehicle-badge">Troca: <%=v.isAceitaTroca() ? "Sim" : "Nao"%></span>
						</div>

						<form class="vehicle-edit"
							action="${pageContext.request.contextPath}/editarDadosVeiculo"
							method="post">

							<input type="hidden" name="idVenda" value="<%=v.getIdVenda()%>" />

							<label>Status <select name="statusVenda">
									<option value="DISPONIVEL"
										<%="DISPONIVEL".equals(v.getStatus()) ? "selected" : ""%>>Disponivel</option>
									<option value="RESERVADO"
										<%="RESERVADO".equals(v.getStatus()) ? "selected" : ""%>>Reservado</option>
									<option value="VENDIDO"
										<%="VENDIDO".equals(v.getStatus()) ? "selected" : ""%>>Vendido</option>
							</select>
							</label> <label>KM rodados <input name="kmRodados" type="number"
								min="0" value="<%=v.getQuilometragem()%>" />
							</label> <label>Cidade <input name="cidade" type="text"
								maxlength="100"
								value="<%=v.getCidade() != null ? v.getCidade() : ""%>" />
							</label>

							<button class="primary-btn" type="submit">Salvar</button>
						</form>
						<a
							href="${pageContext.request.contextPath}/deletar?idVenda=<%=v.getIdVenda()%>"
							onclick="return confirm('Tem certeza que deseja excluir este anúncio?')"
							style="display: block; margin-top: 8px; padding: 10px; background: #dc2626; color: white; text-align: center; border-radius: 8px; text-decoration: none; font-weight: 600;">
							Excluir anúncio </a>

					</div>
					<%
					}
					%>

					<%
					}
					%>
					<div class="pagination" id="seller-pagination"></div>
				</div>

			</div>

		</section>
	</main>

	<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
	<script src="${pageContext.request.contextPath}/js/vendedorveiculos.js"></script>
</body>
</html>
