package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.DAO;
import model.JavaBeans;

@WebServlet({ "/cadastro", "/deletar", "/edit", "/navegacao", "/cadastrou", "/cadastrol", "/loginu", "/logine",
		"/update", "/cadveiculo", "/cadservico", "/buscarEditar", "/updateAnuncio" })

public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;

	DAO dao;

	@Override
	public void init() {

		dao = new DAO();

	}

	// =========================================================
	// DO GET
	// =========================================================

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();

		System.out.println("ACTION: " + action);

		// LISTAR ANUNCIOS
		if (action.equals("/navegacao")) {

			listarAnuncios(request, response);

		}

		// EDITAR ANUNCIO
		else if (action.equals("/edit")) {

			editarAnuncio(request, response);

		}

		// DELETAR ANUNCIO
		else if (action.equals("/deletar")) {

			deletarAnuncio(request, response);

		}

		// REDIRECIONA
		else {

			response.sendRedirect("index.jsp");

		}
	}

	// =========================================================
	// DO POST
	// =========================================================

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();

		System.out.println("POST ACTION: " + action);

		// =========================================
		// CADASTRO USUARIO
		// =========================================

		if (action.equals("/cadastrou")) {

			cadastrarUsuario(request, response);

		}

		// =========================================
		// CADASTRO EMPRESA
		// =========================================

		else if (action.equals("/cadastrol")) {

			cadastrarEmpresa(request, response);

		}

		// =========================================
		// LOGIN USUARIO
		// =========================================

		else if (action.equals("/loginu")) {

			loginUsuario(request, response);

		}

		// =========================================
		// LOGIN EMPRESA
		// =========================================

		else if (action.equals("/logine")) {

			loginEmpresa(request, response);

		}

		// =========================================
		// CADASTRAR VEICULO
		// =========================================

		else if (action.equals("/cadveiculo")) {

			cadastrarVeiculo(request, response);

		}

		// =========================================
		// CADASTRAR SERVICO
		// =========================================

		else if (action.equals("/cadservico")) {

			cadastrarServico(request, response);

		}

		// =========================================
		// UPDATE ANUNCIO
		// =========================================

		else if (action.equals("/updateAnuncio")) {

			atualizarAnuncio(request, response);

		}
	}

	// =========================================================
	// CADASTRO USUARIO
	// =========================================================

	protected void cadastrarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("CHEGOU CADASTRO USUARIO");

		JavaBeans usuario = new JavaBeans();

		usuario.setNome(request.getParameter("nome"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setSenha(request.getParameter("senha"));
		usuario.setNumeroTelefone(request.getParameter("telefone"));

		String cpf = request.getParameter("cpf");

		if (cpf != null) {

			cpf = cpf.replace(".", "").replace("-", "");

		}

		usuario.setCpf(cpf);
		usuario.setTipoUsuario("CLIENTE");

		System.out.println("NOME RECEBIDO: " + usuario.getNome());
		System.out.println("EMAIL RECEBIDO: " + usuario.getEmail());
		System.out.println("TELEFONE RECEBIDO: " + usuario.getNumeroTelefone());
		System.out.println("CPF RECEBIDO: " + usuario.getCpf());

		if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {

			System.out.println("ERRO: NOME DO USUARIO VEIO VAZIO");

			response.sendRedirect("cadastro.jsp?erro=nome");
			return;

		}

		int idUsuarioGerado = dao.cadastrarUsuario(usuario);

		System.out.println("ID USUARIO GERADO: " + idUsuarioGerado);

		if (idUsuarioGerado <= 0) {

			System.out.println("ERRO: USUARIO NAO FOI CADASTRADO");

			response.sendRedirect("cadastro.jsp?erro=banco");
			return;

		}

		System.out.println("USUARIO CADASTRADO");

		response.sendRedirect("loginUsuario.jsp");

	}

	// =========================================================
	// CADASTRO EMPRESA
	// =========================================================

	protected void cadastrarEmpresa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("CHEGOU NO CADASTRO EMPRESA");

		JavaBeans usuario = new JavaBeans();

		usuario.setNome(request.getParameter("nome"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setSenha(request.getParameter("senha"));
		usuario.setNumeroTelefone(request.getParameter("telefone"));

		String cpf = request.getParameter("cpf");

		if (cpf != null) {

			cpf = cpf.replace(".", "").replace("-", "");

		}

		usuario.setCpf(cpf);
		usuario.setTipoUsuario("EMPRESA");

		System.out.println("================================");
		System.out.println("DADOS DO USUARIO DA EMPRESA");
		System.out.println("NOME: " + usuario.getNome());
		System.out.println("EMAIL: " + usuario.getEmail());
		System.out.println("SENHA: " + usuario.getSenha());
		System.out.println("TELEFONE: " + usuario.getNumeroTelefone());
		System.out.println("CPF: " + usuario.getCpf());
		System.out.println("TIPO: " + usuario.getTipoUsuario());
		System.out.println("================================");

		if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {

			System.out.println("ERRO: NOME DO RESPONSAVEL VEIO VAZIO");

			response.sendRedirect("cadastro.jsp?erro=nome");
			return;

		}

		if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {

			System.out.println("ERRO: EMAIL DO RESPONSAVEL VEIO VAZIO");

			response.sendRedirect("cadastro.jsp?erro=email");
			return;

		}

		if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()) {

			System.out.println("ERRO: SENHA DO RESPONSAVEL VEIO VAZIA");

			response.sendRedirect("cadastro.jsp?erro=senha");
			return;

		}

		System.out.println("CADASTRANDO USUARIO DA EMPRESA...");

		int idUsuarioGerado = dao.cadastrarUsuario(usuario);

		System.out.println("ID USUARIO GERADO: " + idUsuarioGerado);

		if (idUsuarioGerado <= 0) {

			System.out.println("ERRO: USUARIO DA EMPRESA NAO FOI CADASTRADO");

			response.sendRedirect("cadastro.jsp?erro=usuario");
			return;

		}

		JavaBeans empresa = new JavaBeans();

		empresa.setIdUsuario(idUsuarioGerado);
		empresa.setNomeEmpresa(request.getParameter("nomeFantasia"));
		empresa.setRazaoSocial(request.getParameter("razaoSocial"));

		String cnpj = request.getParameter("cnpj");

		if (cnpj != null) {

			cnpj = cnpj.replace(".", "").replace("/", "").replace("-", "");

		}

		empresa.setCnpj(cnpj);
		empresa.setDescricao(request.getParameter("descricao"));
		empresa.setNumeroTelefone(request.getParameter("telefone"));
		empresa.setEmail(request.getParameter("email"));
		empresa.setCategoria(request.getParameter("categoria"));

		System.out.println("================================");
		System.out.println("DADOS DA EMPRESA");
		System.out.println("NOME EMPRESA: " + empresa.getNomeEmpresa());
		System.out.println("RAZAO SOCIAL: " + empresa.getRazaoSocial());
		System.out.println("CNPJ: " + empresa.getCnpj());
		System.out.println("DESCRICAO: " + empresa.getDescricao());
		System.out.println("TELEFONE: " + empresa.getNumeroTelefone());
		System.out.println("EMAIL: " + empresa.getEmail());
		System.out.println("CATEGORIA: " + empresa.getCategoria());
		System.out.println("ID USUARIO FK: " + empresa.getIdUsuario());
		System.out.println("================================");

		if (empresa.getNomeEmpresa() == null || empresa.getNomeEmpresa().trim().isEmpty()) {

			System.out.println("ERRO: NOME DA EMPRESA VEIO VAZIO");

			response.sendRedirect("cadastro.jsp?erro=empresa");
			return;

		}

		if (empresa.getCnpj() == null || empresa.getCnpj().trim().isEmpty()) {

			System.out.println("ERRO: CNPJ VEIO VAZIO");

			response.sendRedirect("cadastro.jsp?erro=cnpj");
			return;

		}

		if (empresa.getCategoria() == null || empresa.getCategoria().trim().isEmpty()) {

			System.out.println("ERRO: CATEGORIA VEIO VAZIA");

			response.sendRedirect("cadastro.jsp?erro=categoria");
			return;

		}

		boolean empresaCadastrada = dao.cadastrarEmpresa(empresa);

		if (!empresaCadastrada) {

			System.out.println("ERRO: EMPRESA NAO FOI CADASTRADA");

			response.sendRedirect("cadastro.jsp?erro=empresa");
			return;

		}

		System.out.println("EMPRESA CADASTRADA");

		response.sendRedirect("loginEmpresa.jsp");
	}

	// =========================================================
	// LOGIN USUARIO
	// =========================================================

	protected void loginUsuario(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		JavaBeans login = new JavaBeans();

		login.setEmail(request.getParameter("email"));
		login.setSenha(request.getParameter("senha"));

		System.out.println("LOGIN GERAL");
		System.out.println("EMAIL: " + login.getEmail());

		JavaBeans usuarioLogado = dao.loginGeral(login);

		if (usuarioLogado != null) {

			request.getSession().setAttribute(
					"usuarioLogado",
					usuarioLogado.getEmail());

			request.getSession().setAttribute(
					"nomeUsuario",
					usuarioLogado.getNome());

			request.getSession().setAttribute(
					"idUsuario",
					usuarioLogado.getIdUsuario());

			request.getSession().setAttribute(
					"tipoUsuario",
					usuarioLogado.getTipoUsuario());

			if ("EMPRESA".equals(usuarioLogado.getTipoUsuario())) {

				request.getSession().setAttribute(
						"idEmpresa",
						usuarioLogado.getIdEmpresa());

				System.out.println("REDIRECIONANDO PARA TELA DO LOJISTA");

				response.sendRedirect("vendedorpainel.jsp");

			} else {

				System.out.println("REDIRECIONANDO PARA TELA DO USUARIO");

				response.sendRedirect("consumidor.jsp");

			}
		
		} else {

			System.out.println("LOGIN INVALIDO - REDIRECIONANDO PARA CADASTRO");

			response.sendRedirect("cadastro.jsp");

		}
	}

	// =========================================================
	// LOGIN EMPRESA
	// =========================================================

	protected void loginEmpresa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JavaBeans login = new JavaBeans();

		login.setEmail(request.getParameter("email"));

		login.setCnpj(request.getParameter("cnpj"));

		boolean autenticado = dao.loginEmpresa(login);

		if (autenticado) {

			request.getSession().setAttribute("empresaLogada", login.getEmail());

			response.sendRedirect("dashboardEmpresa.jsp");

		}

		else {

			response.sendRedirect("loginEmpresa.jsp?erro=1");

		}
	}

	// =========================================================
	// CADASTRAR VEICULO
	// =========================================================

	protected void cadastrarVeiculo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JavaBeans carro = new JavaBeans();

		carro.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));

		carro.setMarca(request.getParameter("marca"));

		carro.setModelo(request.getParameter("modelo"));

		carro.setAno(Integer.parseInt(request.getParameter("ano")));

		carro.setCor(request.getParameter("cor"));

		carro.setPlaca(request.getParameter("placa"));

		carro.setCombustivel(request.getParameter("combustivel"));

		carro.setQuilometragem(Integer.parseInt(request.getParameter("quilometragem")));

		carro.setImagem(request.getParameter("foto"));

		dao.cadastrarCarro(carro);

		response.sendRedirect("meusVeiculos.jsp");

	}

	// =========================================================
	// CADASTRAR SERVICO
	// =========================================================

	protected void cadastrarServico(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JavaBeans servico = new JavaBeans();

		servico.setIdEmpresa(Integer.parseInt(request.getParameter("idEmpresa")));

		servico.setServico(request.getParameter("nomeServico"));

		servico.setDescricao(request.getParameter("descricao"));

		servico.setPreco(Double.parseDouble(request.getParameter("preco")));

		servico.setPrazo(request.getParameter("tempoEstimado"));

		servico.setStatus(request.getParameter("statusServico"));

		dao.cadastrarServico(servico);

		response.sendRedirect("dashboardEmpresa.jsp");

	}

	// =========================================================
	// LISTAR ANUNCIOS
	// =========================================================

	protected void listarAnuncios(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<JavaBeans> lista = dao.listarAnuncios();

		request.setAttribute("anuncios", lista);

		RequestDispatcher rd = request.getRequestDispatcher("listar.jsp");

		rd.forward(request, response);

	}

	// =========================================================
	// DELETAR ANUNCIO
	// =========================================================

	protected void deletarAnuncio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JavaBeans anuncio = new JavaBeans();

		anuncio.setIdVenda(Integer.parseInt(request.getParameter("idVenda")));

		dao.deletarAnuncio(anuncio);

		response.sendRedirect("navegacao");

	}

	// =========================================================
	// EDITAR ANUNCIO
	// =========================================================

	protected void editarAnuncio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int idVenda = Integer.parseInt(request.getParameter("idVenda"));

		JavaBeans anuncio = dao.buscarAnuncio(idVenda);

		request.setAttribute("anuncio", anuncio);

		RequestDispatcher rd = request.getRequestDispatcher("editarAnuncio.jsp");

		rd.forward(request, response);

	}

	// =========================================================
	// UPDATE ANUNCIO
	// =========================================================

	protected void atualizarAnuncio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JavaBeans anuncio = new JavaBeans();

		anuncio.setIdVenda(Integer.parseInt(request.getParameter("idVenda")));

		anuncio.setTituloAnuncio(request.getParameter("tituloAnuncio"));

		anuncio.setDescricao(request.getParameter("descricao"));

		anuncio.setPreco(Double.parseDouble(request.getParameter("preco")));

		anuncio.setCidade(request.getParameter("cidade"));

		anuncio.setEstado(request.getParameter("estado"));

		dao.editarAnuncio(anuncio);

		response.sendRedirect("navegacao");

	}
}
