const ITEMS_PER_PAGE = 2;
const cards = document.querySelectorAll('[data-vehicle-item]');
const pagination = document.getElementById('seller-pagination');
let currentPage = 1;
const totalPages = Math.ceil(cards.length / ITEMS_PER_PAGE);

function showPage(page) {
    currentPage = page;
    cards.forEach((card, index) => {
        const start = (page - 1) * ITEMS_PER_PAGE;
        const end = start + ITEMS_PER_PAGE;
        card.style.display = (index >= start && index < end) ? 'grid' : 'none';
    });
    renderPagination();
}

function renderPagination() {
    if (totalPages <= 1) return;
    let html = '';
    if (currentPage > 1) html += `<a class="page-btn" style="padding:8px 16px;border-radius:8px;border:1px solid #daa520;color:#daa520;cursor:pointer;font-weight:600;text-decoration:none;margin:4px" onclick="showPage(${currentPage - 1})">← Anterior</a>`;
    for (let i = 1; i <= totalPages; i++) {
        const active = i === currentPage ? 'background:#daa520;color:white;' : '';
        html += `<a class="page-btn" style="padding:8px 16px;border-radius:8px;border:1px solid #daa520;color:#daa520;cursor:pointer;font-weight:600;text-decoration:none;margin:4px;${active}" onclick="showPage(${i})">${i}</a>`;
    }
    if (currentPage < totalPages) html += `<a class="page-btn" style="padding:8px 16px;border-radius:8px;border:1px solid #daa520;color:#daa520;cursor:pointer;font-weight:600;text-decoration:none;margin:4px" onclick="showPage(${currentPage + 1})">Próxima →</a>`;
    pagination.innerHTML = html;
}

showPage(1);

const fotoInput = document.getElementById('foto');

if (fotoInput) {
    fotoInput.addEventListener('change', () => {
        if (fotoInput.files.length > 0) {
            const preview = document.getElementById('previewFoto');
            const container = document.getElementById('previewContainer');
            const reader = new FileReader();
            reader.onload = (e) => {
                preview.src = e.target.result;
                container.style.display = 'block';
            };
            reader.readAsDataURL(fotoInput.files[0]);
        }
    });
}
