<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>AutoLoc | Serviços</title>

    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />

    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
        rel="stylesheet" />

    <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />

    <link rel="stylesheet"
        href="https://unpkg.com/leaflet/dist/leaflet.css" />

    <link rel="stylesheet"
        href="<%= request.getContextPath() %>/styles.css?v=20260519-theme" />

    <link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css">
</head>

<body data-page="service-map">

    <aside class="sidebar">

        <div class="logo-area">
            <a href="<%= request.getContextPath() %>/consumidor.jsp"
                aria-label="AutoLoc, página inicial">

                <div class="logo">
                    <div class="logo-circle">AL</div>
                    Auto<span>Loc</span>
                </div>

            </a>
        </div>

        <div class="search-box">
            <i class="fa-solid fa-magnifying-glass"></i>

            <input
                data-service-search
                type="text"
                placeholder="Buscar serviços, autopeças ou oficina" />
        </div>

        <div class="location">
            <i class="fa-solid fa-location-dot"></i>
            Localização atual detectada
        </div>

        <div class="section-title">Serviços</div>

        <div class="services-grid">

            <div class="service-card" data-service-type="Mecânica">
                <div class="icon">
                    <i class="fa-solid fa-wrench"></i>
                </div>
                <span>Mecânicas</span>
            </div>

            <div class="service-card" data-service-type="Autopeças">
                <div class="icon">
                    <i class="fa-solid fa-box"></i>
                </div>
                <span>Autopeças</span>
            </div>

            <div class="service-card" data-service-type="Locadora">
                <div class="icon">
                    <i class="fa-solid fa-car-side"></i>
                </div>
                <span>Locadora</span>
            </div>

            <div class="service-card" data-service-type="Borracharia">
                <div class="icon">
                    <i class="fa-solid fa-circle-dot"></i>
                </div>
                <span>Borracharia</span>
            </div>

            <div class="service-card" data-service-type="Elétrica">
                <div class="icon">
                    <i class="fa-solid fa-bolt"></i>
                </div>
                <span>Elétrica</span>
            </div>

            <div class="service-card" data-service-type="Funilaria">
                <div class="icon">
                    <i class="fa-solid fa-hammer"></i>
                </div>
                <span>Funilaria</span>
            </div>

            <div class="service-card" data-service-type="Estética">
                <div class="icon">
                    <i class="fa-solid fa-spray-can-sparkles"></i>
                </div>
                <span>Estética</span>
            </div>

        </div>

        <div class="section-title">Filtros</div>

        <div class="filters">

            <button class="filter active" type="button"
                data-service-sort="near">

                <i class="fa-solid fa-location-dot"></i>
                Mais próximo

            </button>

            <button class="filter"
                type="button"
                data-service-sort="cheap">

                <i class="fa-solid fa-dollar-sign"></i>
                Mais barato

            </button>

            <button class="filter"
                type="button"
                data-service-sort="rating">

                <i class="fa-solid fa-star"></i>
                Melhor avaliação

            </button>

            <button class="filter clear-filter"
                type="button"
                data-service-clear>

                <i class="fa-solid fa-rotate-left"></i>
                Limpar filtros

            </button>

        </div>

        <div class="section-title">Locais encontrados</div>

        <div class="service-list" data-service-results>
            <p class="empty-state">
                Buscando locais próximos...
            </p>
        </div>

    </aside>

    <main class="map-area">

        <div id="map"></div>

        <div class="stats">

            <div class="stat-card">
                <div class="stat-icon blue">
                    <i class="fa-solid fa-location-dot"></i>
                </div>

                <h2 data-map-total>6</h2>
                <p>Estabelecimentos</p>
            </div>

            <div class="stat-card">
                <div class="stat-icon orange">
                    <i class="fa-solid fa-star"></i>
                </div>

                <h2 data-map-rating>4.7</h2>
                <p>Avaliação média</p>
            </div>

            <div class="stat-card">
                <div class="stat-icon green">
                    <i class="fa-solid fa-clock"></i>
                </div>

                <h2 data-map-open>5</h2>
                <p>Abertos agora</p>
            </div>

        </div>

        <div class="powered">
            Powered by <span>AutoLoc</span>
        </div>

    </main>

    <div class="modal-overlay" id="modalOverlay">

        <div class="modal-card">

            <button class="close-modal"
                id="closeModal"
                type="button"
                aria-label="Fechar">

                <i class="fa-solid fa-xmark"></i>

            </button>

            <div class="modal-banner">

                <img
                    data-place-image
                    src="https://images.unsplash.com/photo-1486006920555-c77dcf18193c?q=80&w=1600&auto=format&fit=crop"
                    alt="Oficina" />

                <div class="banner-overlay"></div>

                <div class="status-badge" data-place-status>
                    Aberto agora
                </div>

                <div class="modal-info">

                    <span class="distance">
                        <i class="fa-solid fa-location-dot"></i>
                        <span data-place-distance>1.2 km</span>
                    </span>

                    <h2 data-place-name>
                        AutoMaster Premium
                    </h2>

                </div>

            </div>

        </div>

    </div>

    <nav class="bottom-nav">

        <a href="<%= request.getContextPath() %>/consumidor.jsp">
            <div class="nav-item">
                <i class="fa-solid fa-house"></i>
                <span>Início</span>
            </div>
        </a>

        <a href="<%= request.getContextPath() %>/veiculos.jsp">
            <div class="nav-item">
                <i class="fa-solid fa-car"></i>
                <span>Veículos</span>
            </div>
        </a>

        <a href="<%= request.getContextPath() %>/servicos.jsp">
            <div class="nav-item active">
                <i class="fa-solid fa-wrench"></i>
                <span>Serviços</span>
            </div>
        </a>

        <a href="<%= request.getContextPath() %>/favoritos.jsp">
            <div class="nav-item">
                <i class="fa-solid fa-heart"></i>
                <span>Favoritos</span>
            </div>
        </a>

        <a href="<%= request.getContextPath() %>/perfil.jsp">
            <div class="nav-item">
                <i class="fa-solid fa-user"></i>
                <span>Perfil</span>
            </div>
        </a>

    </nav>

    <script src="https://unpkg.com/leaflet/dist/leaflet.js"></script>

    <script src="<%= request.getContextPath() %>/js/scripts.js"></script>

</body>
</html>
