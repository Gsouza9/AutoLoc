/* =========================================================
   AUTOLOC SCRIPTS
========================================================= */

const favoriteKey = 'autoloc:favorites';
const themeKey = 'autoloc:theme';

/* =========================================================
   HELPERS
========================================================= */

function qs(selector, scope = document) {
	return scope.querySelector(selector);
}

function qsa(selector, scope = document) {
	return [...scope.querySelectorAll(selector)];
}

/* =========================================================
   ICONS
========================================================= */

function renderIcons() {

	if (window.lucide) {
		lucide.createIcons();
	}
}

/* =========================================================
   FAVORITOS
========================================================= */

function getFavorites() {

	try {

		const data =
			JSON.parse(
				localStorage.getItem(favoriteKey)
			);

		return Array.isArray(data)
			? data
			: [];

	} catch {

		return [];
	}
}

function setFavorites(data) {

	localStorage.setItem(
		favoriteKey,
		JSON.stringify(data)
	);

	updateFavoriteBadges();
	updateFavoriteButtons();
}

function updateFavoriteBadges() {

	const total = getFavorites().length;

	qsa('[data-favorite-count]')
		.forEach(item => {
			item.textContent = total;
		});
}

function updateFavoriteButtons() {

	const favorites = getFavorites();

	qsa('[data-favorite-id]')
		.forEach(button => {

			const id =
				button.dataset.favoriteId;

			button.classList.toggle(
				'active',
				favorites.includes(id)
			);
		});
}

function toggleFavorite(id) {

	const favorites = getFavorites();

	const exists =
		favorites.includes(id);

	const next = exists
		? favorites.filter(item => item !== id)
		: [...favorites, id];

	setFavorites(next);

	showToast(
		exists
			? 'Removido dos favoritos'
			: 'Adicionado aos favoritos'
	);
}

/* =========================================================
   TOAST
========================================================= */

function showToast(message, type = 'success') {

	let container =
		qs('.toast-container');

	if (!container) {

		container =
			document.createElement('div');

		container.className =
			'toast-container';

		document.body.appendChild(container);
	}

	const toast =
		document.createElement('div');

	toast.className =
		`toast ${type}`;

	toast.innerHTML = `
		<span>${message}</span>
	`;

	container.appendChild(toast);

	setTimeout(() => {

		toast.style.opacity = '0';
		toast.style.transform =
			'translateY(-10px)';

		setTimeout(() => {
			toast.remove();
		}, 300);

	}, 3000);
}

/* =========================================================
   THEME
========================================================= */

function applyTheme(theme) {

	document.body.classList.toggle(
		'light-mode',
		theme === 'light'
	);
}

function initTheme() {

	const saved =
		localStorage.getItem(themeKey)
		|| 'dark';

	applyTheme(saved);

	const toggle =
		qs('[data-theme-toggle]');

	if (!toggle) return;

	toggle.addEventListener('click', () => {

		const next =
			document.body.classList.contains(
				'light-mode'
			)
				? 'dark'
				: 'light';

		localStorage.setItem(
			themeKey,
			next
		);

		applyTheme(next);

		showToast(
			next === 'light'
				? 'Modo claro ativado'
				: 'Modo escuro ativado'
		);
	});
}

/* =========================================================
   MODAL
========================================================= */

function openModal(modal) {

	if (!modal) return;

	modal.classList.add('active');

	document.body.style.overflow =
		'hidden';

	requestAnimationFrame(() => {
		modal.style.opacity = '1';
	});
}

function closeModal(modal) {

	if (!modal) return;

	modal.style.opacity = '0';

	setTimeout(() => {

		modal.classList.remove('active');

		document.body.style.overflow = '';

	}, 220);
}

function initModal() {

	const modal =
		qs('#vehicle-modal');

	if (!modal) return;

	qsa('[data-close-modal]')
		.forEach(button => {

			button.addEventListener('click', () => {
				closeModal(modal);
			});
		});
}

/* =========================================================
   IMAGE PREVIEW
========================================================= */

function setupImagePreview() {

	const input =
		qs('[data-image-input]');

	const preview =
		qs('[data-image-preview]');

	if (!input || !preview) return;

	input.addEventListener('input', () => {

		const value =
			input.value.trim();

		if (!value) {

			preview.innerHTML =
				'<span>Preview da imagem</span>';

			return;
		}

		preview.innerHTML = `
			<img src="${value}" alt="Preview">
		`;
	});
}

/* =========================================================
   SEARCH
========================================================= */

function initSearch() {

	const input =
		qs('[data-search]');

	if (!input) return;

	input.addEventListener('input', () => {

		const value =
			input.value
				.toLowerCase()
				.trim();

		qsa('.vehicle-card').forEach(card => {

			const text =
				card.textContent.toLowerCase();

			card.style.display =
				text.includes(value)
					? ''
					: 'none';
		});
	});
}

/* =========================================================
   FILTERS
========================================================= */

function initFilters() {

	const buttons =
		qsa('[data-filter-chip]');

	if (!buttons.length) return;

	buttons.forEach(button => {

		button.addEventListener('click', () => {

			buttons.forEach(btn => {
				btn.classList.remove('active');
			});

			button.classList.add('active');

			const value =
				button.dataset.filterChip
					.toLowerCase();

			qsa('.vehicle-card').forEach(card => {

				if (value === 'todos') {

					card.style.display = '';
					return;
				}

				const text =
					card.textContent.toLowerCase();

				card.style.display =
					text.includes(value)
						? ''
						: 'none';
			});
		});
	});
}

/* =========================================================
   SKELETON
========================================================= */

function createSkeletonCards(
	container,
	total = 6
) {

	if (!container) return;

	container.innerHTML = '';

	for (let i = 0; i < total; i++) {

		const item =
			document.createElement('div');

		item.className =
			'card skeleton';

		item.style.height = '280px';

		container.appendChild(item);
	}
}

/* =========================================================
   REVEAL ANIMATION
========================================================= */

function initReveal() {

	const items = qsa(
		'.card, .vehicle-card, .seller-card'
	);

	if (!items.length) return;

	const observer =
		new IntersectionObserver(entries => {

			entries.forEach(entry => {

				if (entry.isIntersecting) {

					entry.target.classList.add(
						'revealed'
					);

					observer.unobserve(
						entry.target
					);
				}
			});

		}, {
			threshold: .12
		});

	items.forEach(item => {

		item.classList.add('reveal');

		observer.observe(item);
	});
}

/* =========================================================
   SELLER STATUS
========================================================= */

function initSellerStatus() {

	const status =
		qs('[data-seller-status]');

	if (!status) return;

	status.textContent =
		'Perfil ativo';

	status.classList.add('active');
}

/* =========================================================
   NAV ACTIVE
========================================================= */

function initActiveNav() {

	const page =
		document.body.dataset.page;

	if (!page) return;

	qsa('.nav-item').forEach(item => {

		if (
			item.href &&
			item.href.includes(page)
		) {
			item.classList.add('active');
		}
	});
}

/* =========================================================
   LOADING
========================================================= */

window.addEventListener('load', () => {

	document.body.classList.add('loaded');
});

/* =========================================================
   INIT
========================================================= */

document.addEventListener(
	'DOMContentLoaded',
	() => {

		renderIcons();

		updateFavoriteBadges();

		updateFavoriteButtons();

		initTheme();

		initModal();

		setupImagePreview();

		initSearch();

		initFilters();

		initReveal();

		initSellerStatus();
		
		initActiveNav();

	}
);
