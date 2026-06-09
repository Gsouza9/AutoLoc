const btnVendedor = document.getElementById("btnVendedor");
const btnUsuario = document.getElementById("btnUsuario");
const formCadastro = document.getElementById("formCadastro");
const formVendedor = document.getElementById("formVendedor");
const formUsuario = document.getElementById("formUsuario");

function setCamposAtivos(container, ativo) {
    const campos = container.querySelectorAll("input, select, textarea");
    campos.forEach(function(campo) {
        campo.disabled = !ativo;
    });
}

function mostrarVendedor() {
    formCadastro.action = "cadastrol";
    formVendedor.style.display = "contents";
    formUsuario.style.display = "none";
    setCamposAtivos(formVendedor, true);
    setCamposAtivos(formUsuario, false);
    btnVendedor.classList.add("ativo");
    btnUsuario.classList.remove("ativo");
}

function mostrarUsuario() {
    formCadastro.action = "cadastrou";
    formVendedor.style.display = "none";
    formUsuario.style.display = "contents";
    setCamposAtivos(formVendedor, false);
    setCamposAtivos(formUsuario, true);
    btnUsuario.classList.add("ativo");
    btnVendedor.classList.remove("ativo");
}

btnUsuario.addEventListener("click", mostrarUsuario);
btnVendedor.addEventListener("click", mostrarVendedor);
mostrarVendedor();

function mostrarSenhas() {
    const campos = document.querySelectorAll(
        "#senhaVendedor, #confirmarSenhaVendedor, #senhaUsuario, #confirmarSenhaUsuario"
    );
    campos.forEach(function(campo) {
        if (campo) {
            campo.type = campo.type === "password" ? "text" : "password";
        }
    });
}

// ---------- BUSCA POR CEP ----------
document.getElementById('formCadastro').addEventListener('submit', function(e) {
    if (formCadastro.action.includes('cadastrol')) {
        var lat = document.getElementById('latitude');
        var lon = document.getElementById('longitude');

        if (!lat || !lat.value || lat.value === '0' || lat.value === '') {
            e.preventDefault();
            alert('Digite o CEP e aguarde o preenchimento automático do endereço.');
            return;
        }
    }
});

function buscarCEP() {
    var input = document.getElementById('cep');
    if (!input) return;

    var cep = input.value.replace(/\D/g, '');
    if (cep.length !== 8) return;

    fetch('https://viacep.com.br/ws/' + cep + '/json/')
        .then(function(res) {
            return res.json();
        })
        .then(function(dados) {
            if (dados.erro) {
                alert('CEP não encontrado.');
                return Promise.reject('CEP inválido');
            }

            var logradouro = document.getElementById('logradouro');
            var bairro = document.getElementById('bairro');
            var cidade = document.getElementById('cidade');
            var estado = document.getElementById('estado');

            if (logradouro) logradouro.value = dados.logradouro || '';
            if (bairro) bairro.value = dados.bairro || '';
            if (cidade) cidade.value = dados.localidade || '';
            if (estado) estado.value = dados.uf || '';

            var numeroInput = document.getElementById('numero');
            var numero = numeroInput ? numeroInput.value.trim() : '';

            var endereco = [
                dados.logradouro,
                numero,
                dados.bairro,
                dados.localidade,
                dados.uf,
                'Brasil'
            ].filter(Boolean).join(', ');

            console.log('ENDERECO BUSCADO:', endereco);

            return fetch(
                'https://nominatim.openstreetmap.org/search?' +
                'format=json' +
                '&limit=1' +
                '&countrycodes=br' +
                '&q=' + encodeURIComponent(endereco)
            );
        })
        .then(function(res) {
            return res.json();
        })
        .then(function(geoData) {
            if (!geoData || !geoData.length) {
                alert('Coordenadas não encontradas. Verifique o CEP e o número.');
                return;
            }

            var lat = parseFloat(geoData[0].lat);
            var lon = parseFloat(geoData[0].lon);

            var hidLat = document.getElementById('latitude');
            var hidLon = document.getElementById('longitude');

            if (hidLat) hidLat.value = lat;
            if (hidLon) hidLon.value = lon;

            console.log('LAT: ' + lat + ' | LON: ' + lon);
            alert('Endereço encontrado! Agora você pode criar a conta.');
        })
        .catch(function(err) {
            if (err !== 'CEP inválido') {
                console.error(err);
                alert('Erro ao buscar o CEP. Tente novamente.');
            }
        });
}

var cepInput = document.getElementById('cep');
if (cepInput) {
    cepInput.addEventListener('input', function() {
        var v = this.value.replace(/\D/g, '').substring(0, 8);
        this.value = v.length > 5 ? v.substring(0, 5) + '-' + v.substring(5) : v;
    });

    cepInput.addEventListener('blur', buscarCEP);
}

var numeroInput = document.getElementById('numero');

if (numeroInput) {
    numeroInput.addEventListener('blur', function () {
        if (typeof window.buscarCEP === 'function') {
            window.buscarCEP();
        }
    });
}
// -----------------------------------
