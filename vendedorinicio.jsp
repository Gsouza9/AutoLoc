<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.JavaBeans" %>
<%
    Integer totalVeiculos = (Integer) request.getAttribute("totalVeiculos");
    Integer totalServicos = (Integer) request.getAttribute("totalServicos");
    Integer totalProdutos = (Integer) request.getAttribute("totalProdutos");
    String  nomeLoja      = (String)  request.getAttribute("nomeLoja");

    if (totalVeiculos == null) totalVeiculos = 0;
    if (totalServicos == null) totalServicos = 0;
    if (totalProdutos == null) totalProdutos = 0;
    if (nomeLoja == null || nomeLoja.trim().isEmpty()) nomeLoja = "Loja sem nome";

    boolean perfilCompleto = !nomeLoja.equals("Loja sem nome");
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>AutoLoc | Início do Vendedor</title>

  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet" />

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
  <script src="https://unpkg.com/lucide@latest/dist/umd/lucide.min.js" defer></script>
</head>

<body data-page="seller-home">

<header class="site-header seller-header">

  <a class="brand" href="${pageContext.request.contextPath}/vendedorinicio" aria-label="Inicio do vendedor AutoLoc">
    <span class="brand-mark">AL</span>
    <span class="brand-name">Auto<span>Loc</span></span>
  </a>

  <nav class="desktop-nav" aria-label="Navegacao do vendedor">
    <a class="active" href="${pageContext.request.contextPath}/vendedorinicio">Inicio</a>
    <a href="${pageContext.request.contextPath}/vendedorpainel">Loja</a>
    <a href="${pageContext.request.contextPath}/vendedorveiculos">Veiculos</a>
    <a href="${pageContext.request.contextPath}/vendedorservicos">Servicos</a>
    <a href="${pageContext.request.contextPath}/consumidor">Ver consumidor</a>
  </nav>

  <div class="header-actions">
    <a class="icon-btn" href="${pageContext.request.contextPath}/login.jsp" aria-label="Sair" data-clear-role>
      <i data-lucide="log-out"></i>
    </a>
  </div>

</header>

<main class="seller-shell" data-seller-dashboard>

  <section class="seller-hero seller-hero-dashboard">
    <div>
      <span class="section-kicker">Painel do vendedor</span>
      <h1>Veja seu negócio como o consumidor vê.</h1>
      <p>
        Controle sua loja, estoque, serviços e produtos com acesso rápido às telas que impactam sua presença no AutoLoc.
      </p>
    </div>

    <div class="seller-status" data-seller-status>
      <% if (perfilCompleto) { %>
        Perfil ativo
      <% } else { %>
        Perfil em configuração
      <% } %>
    </div>
  </section>

  <section class="seller-stats" aria-label="Resumo do vendedor">
    <article>
      <strong><%= nomeLoja %></strong>
      <span>Loja cadastrada</span>
    </article>

    <article>
      <strong><%= totalVeiculos %></strong>
      <span>Veículos ativos</span>
    </article>

    <article>
      <strong><%= totalServicos %></strong>
      <span>Serviços ativos</span>
    </article>

    <article>
      <strong><%= totalProdutos %></strong>
      <span>Produtos ativos</span>
    </article>
  </section>

  <section class="seller-action-grid">

    <a class="seller-action-card" href="${pageContext.request.contextPath}/vendedorpainel">
      <i data-lucide="store"></i>
      <h2>Cadastro da loja</h2>
      <p>Atualize nome, telefone, endereço, horário e apresentação pública.</p>
    </a>

    <a class="seller-action-card" href="${pageContext.request.contextPath}/vendedorveiculos">
      <i data-lucide="car"></i>
      <h2>Veículos à venda</h2>
      <p>Cadastre o estoque que o consumidor poderá encontrar e comparar.</p>
    </a>

    <a class="seller-action-card" href="${pageContext.request.contextPath}/vendedorservicos">
      <i data-lucide="wrench"></i>
      <h2>Serviços e produtos</h2>
      <p>Gerencie manutenções, peças, acessórios e disponibilidade.</p>
    </a>

    <a class="seller-action-card" href="${pageContext.request.contextPath}/consumidor">
      <i data-lucide="eye"></i>
      <h2>Visualizar consumidor</h2>
      <p>Abra a experiência pública e volte ao painel pela barra superior.</p>
    </a>

  </section>
</main>

<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
</body>
</html>
