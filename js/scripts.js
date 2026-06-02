const favoriteKey = 'autoloc:favorites';
const leadKey = 'autoloc:leads';
const profileKey = 'autoloc:profile';

const sellerStoreKey = 'autoloc:seller:store';
const sellerVehiclesKey = 'autoloc:seller:vehicles';
const sellerServicesKey = 'autoloc:seller:services';
const sellerProductsKey = 'autoloc:seller:products';

const roleKey = 'autoloc:role';

const BRL = new Intl.NumberFormat('pt-BR', {
	style: 'currency',
	currency: 'BRL'
});

const KM = new Intl.NumberFormat('pt-BR');

function qs(selector, scope = document) {
	return scope.querySelector(selector);
}

function qsa(selector, scope = document) {
	return Array.from(scope.querySelectorAll(selector));
}

function formatPrice(value) {
	return BRL.format(Number(value) || 0);
}

function formatKm(value) {
	return `${KM.format(Number(value) || 0)} km`;
}

function readStorage(key, fallback) {

	try {

		const data = JSON.parse(localStorage.getItem(key));

		return data !== null && data !== undefined
			? data
			: fallback;

	} catch (e) {

		return fallback;

	}

}

function writeStorage(key, value) {
	localStorage.setItem(key, JSON.stringify(value));
}

function createId() {

	if (window.crypto && window.crypto.randomUUID) {
		return window.crypto.randomUUID();
	}

	return `${Date.now()}-${Math.random().toString(16).slice(2)}`;
}

function getFavorites() {

	try {

		const favorites =
			JSON.parse(localStorage.getItem(favoriteKey)) || [];

		return Array.isArray(favorites)
			? favorites
			: [];

	} catch (e) {

		return [];

	}

}

function setFavorites(ids) {

	const unique = [...new Set(ids)];

	localStorage.setItem(
		favoriteKey,
		JSON.stringify(unique)
	);

	updateFavoriteBadges();
}

function isFavorite(id) {
	return getFavorites().includes(id);
}

function toggleFavorite(id) {

	const favorites = getFavorites();

	const next = favorites.includes(id)
		? favorites.filter((item) => item !== id)
		: [...favorites, id];

	setFavorites(next);

	renderCurrentPage();
}

function updateFavoriteBadges() {

	const count = getFavorites().length;

	qsa('[data-favorite-count]').forEach((item) => {
		item.textContent = count;
	});

}

function renderIcons() {

	if (window.lucide) {
		lucide.createIcons();
	}

}
