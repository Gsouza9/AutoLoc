<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>AutoLoc | Favoritos</title>

  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet" />
  
  <link rel="stylesheet" href="styles.css?v=20260519-theme" />
  <script src="https://unpkg.com/lucide@latest/dist/umd/lucide.min.js" defer></script>
  
  <link rel="stylesheet" href="css/styles.css">
  
</head>

<body data-page="favorites">

  <header class="site-header">
    <a class="brand" href="${pageContext.request.contextPath}/consumidor">
      <span class="brand-mark">AL</span>
      <span class="brand-name">Auto<span>Loc</span></span>
    </a>

    <nav class="desktop-nav" aria-label="Navegação principal">
      <a href="${pageContext.request.contextPath}/consumidor">Início</a>
      <a href="${pageContext.request.contextPath}/navegacao">Veículos</a>
     <a class="active" href="servicos.html">Servicos</a>
      <a class="active" href="favoritos.jsp">
        Favoritos <span data-favorite-count>0</span>
      </a>
    </nav>

    <div class="header-actions">
      <a class="icon-btn" href="${pageContext.request.contextPath}/navegacao">
        <i data-lucide="car"></i>
      </a>
      <a class="profile-link" href="perfil.jsp">
        <i data-lucide="user"></i>
      </a>
    </div>
  </header>

  <main class="container">

    <section class="page-hero favorites-banner">
      <span class="eyebrow">Sua seleção</span>
      <h1>Compare os veículos que você salvou para decidir com calma.</h1>
      <p>Os favoritos ficam guardados neste navegador e podem ser removidos a qualquer momento.</p>
    </section>

    <section class="stats">
      <article class="stat-card">
        <strong data-favorites-total>0</strong>
        <span>Favoritos salvos</span>
      </article>

      <article class="stat-card">
        <strong data-favorites-value>R$ 0</strong>
        <span>Valor total da seleção</span>
      </article>

      <article class="stat-card">
        <strong>24h</strong>
        <span>Retorno médio do consultor</span>
      </article>
    </section>

    <section class="content-section">

      <div class="section-title">
        <div>
          <span class="section-kicker">Lista ativa</span>
          <h2>Meus favoritos</h2>
        </div>
        <a href="veiculos.jsp">Adicionar mais</a>
      </div>

      <div class="empty-state" data-favorites-empty>
        <i data-lucide="heart-off"></i>
        <h3>Nenhum favorito ainda</h3>
        <p>Explore o catálogo e toque no coração para salvar os modelos que chamarem sua atenção.</p>
        <a class="primary-btn" href="${pageContext.request.contextPath}/navegacao">Ver veículos</a>
      </div>

      <div class="cars" data-favorites-grid></div>

    </section>

  </main>

  <!-- NAV MOBILE -->
  <nav class="bottom-nav" aria-label="Navegacao inferior">
    <a class="nav-item" href="${pageContext.request.contextPath}/consumidor"><i data-lucide="home"></i>Início</a>
    <a class="nav-item" href="${pageContext.request.contextPath}/navegacao"><i data-lucide="car"></i>Veículos</a>
    <a class="nav-item" href="servicos.html"><i data-lucide="wrench"></i>Serviços</a>
    <a class="nav-item active" href="favoritos.jsp"><i data-lucide="heart"></i>Favoritos</a>
    <a class="nav-item" href="perfil.jsp"><i data-lucide="user"></i>Perfil</a>
  </nav>

  <!-- MODAL -->
  <div class="modal" id="vehicle-modal" aria-hidden="true">
    <div class="modal-backdrop" data-close-modal></div>

    <section class="modal-card" aria-label="Detalhes do veiculo">
      <button class="modal-close" type="button" data-close-modal aria-label="Fechar">
        <i data-lucide="x"></i>
      </button>

      <img data-modal-image src="" alt="" />

      <div class="modal-body">
        <div class="modal-heading">
          <div>
            <span class="section-kicker">Detalhes do veiculo</span>
            <h2 data-modal-title></h2>
          </div>

          <button class="favorite-btn modal-fav" type="button" data-modal-favorite>
            <i data-lucide="heart"></i>
          </button>
        </div>

        <p data-modal-description></p>

        <div class="car-info modal-meta" data-modal-meta></div>

        <ul class="spec-list" data-modal-specs></ul>

        <div class="modal-footer">
          <strong data-modal-price></strong>

          <button class="primary-btn" type="button" data-modal-interest>
            Tenho interesse
          </button>
        </div>
      </div>

    </section>
  </div>

  <script src="script.js?v=20260518-fix"></script>
  <script src="js/scripts.js"></script>

</body>
</html>
