<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>AutoLoc | Início do Vendedor</title>

  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet" />

  <link rel="stylesheet" href="styles.css?v=20260519-theme" />
  <script src="https://unpkg.com/lucide@latest/dist/umd/lucide.min.js" defer></script>
   <link rel="stylesheet" href="css/styles.css">
</head>

<body data-page="seller-home">

<header class="site-header seller-header">

  <a class="brand" href="vendedorinicio.jsp" aria-label="Inicio do vendedor AutoLoc">
    <span class="brand-mark">AL</span>
    <span class="brand-name">Auto<span>Loc</span></span>
  </a>

  <nav class="desktop-nav" aria-label="Navegacao do vendedor">
    <a class="active" href="vendedorinicio.jsp">Inicio</a>
    <a href="vendedorpainel.jsp">Loja</a>
    <a href="vendedorveiculos.jsp">Veiculos</a>
    <a class="active" href="vendedorservicos">Servicos</a>
    <a href="consumidor.jsp">Ver consumidor</a>
  </nav>

  <div class="header-actions">
    <a class="icon-btn" href="index.jsp" aria-label="Sair" data-clear-role>
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
      Perfil em configuração
    </div>
  </section>

  <section class="seller-stats" aria-label="Resumo do vendedor">
    <article>
      <strong data-seller-store-name>Loja sem nome</strong>
      <span>Loja cadastrada</span>
    </article>

    <article>
      <strong data-seller-vehicle-count>0</strong>
      <span>Veículos ativos</span>
    </article>

    <article>
      <strong data-seller-service-count>0</strong>
      <span>Serviços ativos</span>
    </article>

    <article>
      <strong data-seller-product-count>0</strong>
      <span>Produtos ativos</span>
    </article>
  </section>

  <section class="seller-action-grid">

    <a class="seller-action-card" href="vendedorpainel.jsp">
      <i data-lucide="store"></i>
      <h2>Cadastro da loja</h2>
      <p>Atualize nome, telefone, endereço, horário e apresentação pública.</p>
    </a>

    <a class="seller-action-card" href="vendedorveiculos.jsp">
      <i data-lucide="car"></i>
      <h2>Veículos à venda</h2>
      <p>Cadastre o estoque que o consumidor poderá encontrar e comparar.</p>
    </a>

    <a class="seller-action-card" href="vendedorservicos.jsp">
      <i data-lucide="wrench"></i>
      <h2>Serviços e produtos</h2>
      <p>Gerencie manutenções, peças, acessórios e disponibilidade.</p>
    </a>

    <a class="seller-action-card" href="consumidor.jsp">
      <i data-lucide="eye"></i>
      <h2>Visualizar consumidor</h2>
      <p>Abra a experiência pública e volte ao painel pela barra superior.</p>
    </a>

  </section>
</main>
<script src="js/scripts.js"></script>
</body>
</html>
