package model;

public class JavaBeans {
	
	private String idcon;
	private String nome;
	private String nomeEmpresa;
	private String cpf;
	private String cnpj;
	private String email;
	private String senha;
	private String anuncio;
	private String descricao;
	private String marca;
	private double preco;
	private int ano;
	private int kilometragem;
	private String modelo;
	private String carroceria;
	private String tipoUsuario;
	private String endereco;
	private String cep;
	private String numeroTelefone;
	private String imagem;
	private String combustivel;
	private String porta;
	private String cambio;
	private String placa;
	private String cor;
	
	 public JavaBeans() {
	    }
	 
	 public JavaBeans(String idcon, String nome, String nomeEmpresa, String cpf, String cnpj, String email, String senha, String anuncio, String descricao, String marca, double preco, 
			 int ano, int kilometragem, String modelo, String carroceria, String tipoUsuario, String endereco, 
			 String cep, String numeroTelefone, String imagem, String combustivel, 
			 String porta, String cambio, String placa, String cor) {
		 
		 this.idcon = idcon;
		 this.nome = nome;
		 this.nomeEmpresa = nomeEmpresa;
		 this.cpf = cpf;
		 this.cnpj = cnpj;
		 this.email = email;
		 this.senha = senha;
		 this.anuncio = anuncio;
		 this.descricao = descricao;
		 this.marca = marca;
		 this.preco = preco;
		 this.ano = ano;
		 this.kilometragem = kilometragem;
		 this.modelo = modelo;
		 this.carroceria = carroceria;
		 this.tipoUsuario = tipoUsuario;
		 this.numeroTelefone = numeroTelefone;
		 this.imagem = imagem;
		 this.combustivel = combustivel;
		 this.porta = porta;
		 this.cambio = cambio;
		 this.placa = placa;
		 this.cor = cor;
	 }
	 
	 //-----------------------------------------------
	 
	 public String getIdcon() {
		 return idcon;
	 }
	 
	 public void setIdcon(String idcon) {
		 this.idcon = idcon;
	 }
	 //-----------------------------------------------
	 
	 public String getNome() {
		 return nome;
	 }
	 
	 public void setNome(String nome) {
		 this.nome = nome;
	 }
	 //-----------------------------------------------
	
	 public String getCpf() {
		 return cpf;
	 }
	 
	 public void setCpf(String cpf) {
		 this.cpf = cpf;
	 }
	 //-----------------------------------------------
	 
	 public String getCnpj() {
		 return cnpj;
	 }
	 
	 public void setCnpj(String cnpj) {
		 this.cnpj = cnpj;
	 }
	 //-----------------------------------------------
	 
	 public String getEmail() {
		 return email;
	 }
	 public void setEmail(String email) {
		 this.email = email;
	 }
	 //-----------------------------------------------
	 
	 public String getSenha() {
		 return senha;
	 }
	 public void setSenha(String senha) {
		 this.senha = senha;
	 }
	 //-----------------------------------------------
	 
	 public String getAnuncio() {
		 return anuncio;
	 }
	 public void setAnuncio(String anuncio) {
		 this.anuncio = anuncio;
	 }
	 //-----------------------------------------------
	 
	 public String getMarca() {
		 return marca;
	 }
	 public void setMarca(String marca) {
		 this.marca = marca;
	 }
	 //-----------------------------------------------
	 
	 public double getPreco() {
		 return preco;
	 }
	 public void setPreco(double preco) {
		 this.preco = preco;
	 }
	 //-----------------------------------------------
	 
	 public int getAno() {
		 return ano;
	 }
	 public void setAno(int ano) {
		 this.ano = ano;
	 }
	 //-----------------------------------------------
	 public int getKilometragem() {
		 return kilometragem;
	 }
	 public void setKilometragem(int kilometragem) {
		 this.kilometragem = kilometragem;
	 }
	 //-----------------------------------------------
	 
	 public String getModelo() {
		 return modelo;
	 }
	 public void setModelo(String modelo) {
		 this.modelo = modelo;
	 }
	 //-----------------------------------------------
	 
	 public String getDescricao() {
		 return descricao;
	 }
	 public void setDescricao(String descricao) {
		 this.descricao = descricao;
	 }
	 //-----------------------------------------------
	 
	 public String getCarroceria() {
		 return carroceria;
	 }
	 
	 public void setCarroceria(String carroceria) {
		 this.carroceria = carroceria;
	 }
	 //--------------------------------------------------
	 
	 public String getTipoUsuario() {
		 return tipoUsuario;
	 }
	 public void setTipoUsuario(String tipoUsuario) {
		 this.tipoUsuario = tipoUsuario;
	 }
	 //--------------------------------------------------
	 
	 public String getEndereco() {
		 return endereco;
	 }
	 
	 public void setEndereco(String endereco) {
		 this.endereco = endereco;
	 }
	 //---------------------------------------------------
	 
	 public String getCep() {
		 return cep;
	 }
	 
	 public void setCep(String cep) {
		 this.cep = cep;
	 }
	 //----------------------------------------------------
	 
	 public String getNumeroTelefone() {
		 return numeroTelefone;
	 }
	 
	 public void setNumeroTelefone(String numeroTelefone) {
		 this.numeroTelefone = numeroTelefone;
	 }
	 //-----------------------------------------------------
	 
	 public String getNomeEmpresa() {
		 return nomeEmpresa;
	 }
	 
	 public void setNomeEmpresa(String nomeEmpresa) {
		 this.nomeEmpresa = nomeEmpresa;
	 }
	 //----------------------------------------------------
	 
	 public String getImagem() {
		 return imagem;
	 }
	 
	 public void setImagem(String imagem) {
		 this.imagem = imagem;
	 }
	 //----------------------------------------------------
	 
	 public String getCombustivel() {
		 return combustivel;
	 }
	 public void setCombustivel(String combustivel) {
		 this.combustivel = combustivel;
	 }
	 //----------------------------------------------------
	 
	 public String getPorta() {
		 return porta;
	 }
	 public void setPorta(String porta) {
		 this.porta = porta;
	 }
	 //----------------------------------------------------
	 
	 public String getCambio() {
		 return cambio;
	 }
	 public void setCambio(String cambio) {
		 this.cambio = cambio;
	 }
	 //----------------------------------------------------
	 
	 public String getPlaca() {
		 return placa;
	 }
	 public void setPlaca(String placa) {
		 this.placa = placa;
	 }
	 //-----------------------------------------------------
	 
	 public String getCor() {
		 return cor;
	 }
	 
	 public void setCor(String cor) {
		 this.cor = cor;
	 }
}
