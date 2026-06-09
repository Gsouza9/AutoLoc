<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.JavaBeans" %>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />

  <title>AutoLoc | Serviços do Vendedor</title>

  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet" />

  <link rel="stylesheet" href="styles.css?v=20260519-theme" />

  <script src="https://unpkg.com/lucide@latest/dist/umd/lucide.min.js" defer></script>
  <link rel="stylesheet" href="css/styles.css">
</head>

<body data-page="seller-services">

  <header class="site-header seller-header">

    <a class="brand" href="vendedorinicio">
      <span class="brand-mark">AL</span>
      <span class="brand-name">Auto<span>Loc</span></span>
    </a>

    <nav class="desktop-nav" aria-label="Navegação do vendedor">
    	<a href="${pageContext.request.contextPath}/vendedorinicio">Início</a>
		<a href="${pageContext.request.contextPath}/vendedorpainel">Loja</a>
     	<a href="${pageContext.request.contextPath}/vendedorveiculos">Veículos</a>
      	<a class="active" href="${pageContext.request.contextPath}/vendedorservicos">Serviços</a>
      	<a href="${pageContext.request.contextPath}/consumidor">Ver consumidor</a>
    </nav>

    <div class="header-actions">
      <a class="icon-btn" href="index.jsp" aria-label="Sair" data-clear-role>
        <i data-lucide="log-out"></i>
      </a>
    </div>

  </header>

  <main class="seller-shell">

    <section class="seller-page-heading">
      <div>
        <span class="section-kicker">Serviços e produtos</span>
        <h1>Gerencie o que sua loja oferece.</h1>
        <p>Publique serviços automotivos e produtos para organizar sua operação e melhorar a experiência do consumidor.</p>
      </div>

      <a class="secondary-btn" href="servicos.jsp">
        Ver mapa do consumidor
      </a>
    </section>

    <section class="seller-grid two-columns">

      <form class="seller-card seller-form" action="cadservico" method="post">

        <div class="seller-card-title">
          <span><i data-lucide="wrench"></i></span>
          <div>
            <h2>Cadastrar serviço</h2>
            <p>Manutenção, vistoria, borracharia, estética e outros.</p>
          </div>
        </div>

        <label>
          Serviço
          <input name="nomeServico" type="text" placeholder="Ex: Revisão completa" required />
        </label>

        <label>
          Categoria
          <select name="categoria">
            <option value="Mecanica">Mecânica</option>
            <option value="Eletrica">Elétrica</option>
            <option value="Borracharia">Borracharia</option>
            <option value="Funilaria">Funilaria</option>
            <option value="Estetica">Estética</option>
            <option value="Locadora">Locadora</option>
            <option value="Vistoria">Vistoria</option>
          </select>
        </label>

        <label>
          Preço a partir de
          <input name="preco" type="text" inputmode="decimal" placeholder="250,00" />
        </label>

        <label>
          Prazo médio
          <input name="tempoEstimado" type="text" placeholder="2 horas" />
        </label>

        <label>
          Status
          <select name="statusServico">
            <option value="ATIVO">Ativo</option>
            <option value="INATIVO">Inativo</option>
          </select>
        </label>

        <label class="full-field">
          Descrição
          <textarea name="descricao" rows="3" placeholder="Detalhe o que está incluso"></textarea>
        </label>

        <button class="primary-btn" type="submit">
          Adicionar serviço
        </button>

      </form>

      <form class="seller-card seller-form" action="cadproduto" method="post">

        <div class="seller-card-title">
          <span><i data-lucide="package"></i></span>
          <div>
            <h2>Cadastrar produto</h2>
            <p>Autopeças, acessórios e itens automotivos.</p>
          </div>
        </div>

        <label>
          Produto
          <input name="nomeProduto" type="text" placeholder="Ex: Kit pastilhas de freio" required />
        </label>

        <label>
          Categoria
          <select name="categoriaProduto">
            <option value="Autopecas">Autopeças</option>
            <option value="Borracharia">Borracharia</option>
            <option value="Oleo e fluidos">Óleo e fluidos</option>
            <option value="Acessorios">Acessórios</option>
          </select>
        </label>

        <label>
          Preço
          <input name="precoProduto" type="text" inputmode="decimal" placeholder="390,00" />
        </label>

        <label>
          Estoque
          <input name="estoqueProduto" type="number" min="0" step="1" placeholder="12" />
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
          Descrição
          <textarea name="descricaoProduto" rows="3" placeholder="Marca, compatibilidade e condições"></textarea>
        </label>

        <button class="primary-btn" type="submit">
          Adicionar produto
        </button>

      </form>

    </section>

    <section class="seller-grid two-columns">

      <div class="seller-card">
        <div class="seller-card-title">
          <span><i data-lucide="clipboard-list"></i></span>
          <div>
            <h2>Serviços cadastrados</h2>
            <%
              ArrayList<JavaBeans> servicos = (ArrayList<JavaBeans>) request.getAttribute("servicos");
              int totalServicos = servicos == null ? 0 : servicos.size();
            %>
            <p><%= totalServicos %> itens</p>
          </div>
        </div>

        <div class="seller-list">
          <%
            if (servicos == null || servicos.isEmpty()) {
          %>
              <p>Nenhum serviço cadastrado.</p>
          <%
            } else {
              for (JavaBeans s : servicos) {
          %>
                <div class="seller-list-item">
                  <strong><%= s.getServico() %></strong>
                  <span>R$ <%= s.getPreco() %></span>
                  <span><%= s.getPrazo() %></span>
                  <span><%= s.getStatus() %></span>
                  <p><%= s.getDescricao() %></p>
                </div>
          <%
              }
            }
          %>
        </div>
      </div>

      <div class="seller-card">
        <div class="seller-card-title">
          <span><i data-lucide="boxes"></i></span>
          <div>
            <h2>Produtos cadastrados</h2>
            <%
              ArrayList<JavaBeans> produtos = (ArrayList<JavaBeans>) request.getAttribute("produtos");
              int totalProdutos = produtos == null ? 0 : produtos.size();
            %>
            <p><%= totalProdutos %> itens</p>
          </div>
        </div>

        <div class="seller-list">
          <%
            if (produtos == null || produtos.isEmpty()) {
          %>
              <p>Nenhum produto cadastrado.</p>
          <%
            } else {
              for (JavaBeans p : produtos) {
          %>
                <div class="seller-list-item">
                  <strong><%= p.getNomeProduto() %></strong>
                  <span><%= p.getCategoria() %></span>
                  <span>R$ <%= p.getPreco() %></span>
                  <span>Estoque: <%= p.getEstoque() %></span>
                  <span><%= p.getStatus() %></span>
                  <p><%= p.getDescricao() %></p>
                </div>
          <%
              }
            }
          %>
        </div>
      </div>
    </section>
  </main>
  <nav class="bottom-nav" aria-label="Navegacao inferior">
			<a class="nav-item active" href="${pageContext.request.contextPath}/vendedorservicos.jsp"><i data-lucide="wrench"></i>Serviços</a> 
			<a class="nav-item" href="${pageContext.request.contextPath}/vendedorveiculos.jsp"><i data-lucide="car"></i>Veículos</a>
			<a class="nav-item" href="${pageContext.request.contextPath}/vendedorpainel.jsp"><i data-lucide="home"></i>Loja</a>
			<a class="nav-item" href="${pageContext.request.contextPath}/vendedorinicio.jsp"><i data-lucide="heart"></i>Início</a>
			
		<a class="nav-item" href="perfil.jsp"><i data-lucide="user"></i>Perfil</a>
</nav>

  <script src="js/scripts.js"></script>
</body>
</html>
