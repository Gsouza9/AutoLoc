package model;

public class JavaBeans {

	// =========================
	// USUÁRIO
	// =========================

	private int idUsuario;
	private String nome;
	private String email;
	private String senha;
	private String numeroTelefone;
	private String cpf;
	private String tipoUsuario;
	private String statusConta;

	// =========================
	// EMPRESA
	// =========================

	private int idEmpresa;
	private String nomeEmpresa;
	private String razaoSocial;
	private String cnpj;
	private String descricao;
	private String categoria;
	private String endereco;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;
	private String latitude;
	private String longitude;
	private String horario;
	private String fotoEmpresa;
	private String bannerEmpresa;

	// =========================
	// VEÍCULOS
	// =========================

	private int idCarro;
	private int idVenda;

	private String marca;
	private String modelo;
	private int ano;
	private String cor;
	private String placa;
	private String combustivel;
	private int quilometragem;
	private String imagem;
	private String imagem2;
	private String imagem3;
	private String imagem4;

	private String chassi;
	private String carroceria;
	private boolean aceitaTroca;

	private String tituloAnuncio;
	private String cambio;
	private int portas;
	private String finalPlaca;

	private double preco;
	private String status;

	// =========================
	// SERVIÇOS
	// =========================

	private int idServico;

	private String servico;
	private String prazo;

	// =========================
	// PRODUTOS
	// =========================

	private int idProduto;

	private String nomeProduto;
	private int estoque;

	// =========================
	// DASHBOARD / ESTATÍSTICAS
	// =========================

	private int totalVeiculos;
	private int totalServicos;
	private int totalProdutos;
	private int totalVisualizacoes;
	private int totalFavoritos;
	private int totalVendas;

	// =========================
	// FAVORITOS
	// =========================

	private boolean favorito;

	// =========================
	// CONSTRUTOR
	// =========================

	public JavaBeans() {
		super();
	}

	// =========================
	// GETTERS E SETTERS
	// =========================

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getStatusConta() {
		return statusConta;
	}

	public void setStatusConta(String statusConta) {
		this.statusConta = statusConta;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getFotoEmpresa() {
		return fotoEmpresa;
	}

	public void setFotoEmpresa(String fotoEmpresa) {
		this.fotoEmpresa = fotoEmpresa;
	}

	public String getBannerEmpresa() {
		return bannerEmpresa;
	}

	public void setBannerEmpresa(String bannerEmpresa) {
		this.bannerEmpresa = bannerEmpresa;
	}

	public int getIdCarro() {
		return idCarro;
	}

	public void setIdCarro(int idCarro) {
		this.idCarro = idCarro;
	}

	public int getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}

	public int getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(int quilometragem) {
		this.quilometragem = quilometragem;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getImagem2() {
		return imagem2;
	}

	public void setImagem2(String imagem2) {
		this.imagem2 = imagem2;
	}

	public String getImagem3() {
		return imagem3;
	}

	public void setImagem3(String imagem3) {
		this.imagem3 = imagem3;
	}

	public String getImagem4() {
		return imagem4;
	}

	public void setImagem4(String imagem4) {
		this.imagem4 = imagem4;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public String getCarroceria() {
		return carroceria;
	}

	public void setCarroceria(String carroceria) {
		this.carroceria = carroceria;
	}

	public boolean isAceitaTroca() {
		return aceitaTroca;
	}

	public void setAceitaTroca(boolean aceitaTroca) {
		this.aceitaTroca = aceitaTroca;
	}

	public String getTituloAnuncio() {
		return tituloAnuncio;
	}

	public void setTituloAnuncio(String tituloAnuncio) {
		this.tituloAnuncio = tituloAnuncio;
	}

	public String getCambio() {
		return cambio;
	}

	public void setCambio(String cambio) {
		this.cambio = cambio;
	}

	public int getPortas() {
		return portas;
	}

	public void setPortas(int portas) {
		this.portas = portas;
	}

	public String getFinalPlaca() {
		return finalPlaca;
	}

	public void setFinalPlaca(String finalPlaca) {
		this.finalPlaca = finalPlaca;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getIdServico() {
		return idServico;
	}

	public void setIdServico(int idServico) {
		this.idServico = idServico;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public String getPrazo() {
		return prazo;
	}

	public void setPrazo(String prazo) {
		this.prazo = prazo;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public int getTotalVeiculos() {
		return totalVeiculos;
	}

	public void setTotalVeiculos(int totalVeiculos) {
		this.totalVeiculos = totalVeiculos;
	}

	public int getTotalServicos() {
		return totalServicos;
	}

	public void setTotalServicos(int totalServicos) {
		this.totalServicos = totalServicos;
	}

	public int getTotalProdutos() {
		return totalProdutos;
	}

	public void setTotalProdutos(int totalProdutos) {
		this.totalProdutos = totalProdutos;
	}

	public int getTotalVisualizacoes() {
		return totalVisualizacoes;
	}

	public void setTotalVisualizacoes(int totalVisualizacoes) {
		this.totalVisualizacoes = totalVisualizacoes;
	}

	public int getTotalFavoritos() {
		return totalFavoritos;
	}

	public void setTotalFavoritos(int totalFavoritos) {
		this.totalFavoritos = totalFavoritos;
	}

	public int getTotalVendas() {
		return totalVendas;
	}

	public void setTotalVendas(int totalVendas) {
		this.totalVendas = totalVendas;
	}

	public boolean isFavorito() {
		return favorito;
	}

	public void setFavorito(boolean favorito) {
		this.favorito = favorito;
	}
}
