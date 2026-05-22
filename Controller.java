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

@WebServlet({
"/cadastro",
"/deletar",
"/edit",
"/navegacao",
"/cadastrou",
"/cadastrol",
"/loginu",
"/logine",
"/update",
"/cadveiculo",
"/cadservico",
"/buscarEditar",
"/updateAnuncio"
})

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
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
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
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
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

	    // TESTE
	    System.out.println("===== DADOS RECEBIDOS =====");
	    System.out.println(usuario.getNome());
	    System.out.println(usuario.getEmail());
	    System.out.println(usuario.getSenha());
	    System.out.println(usuario.getNumeroTelefone());
	    System.out.println(usuario.getCpf());

	    dao.cadastrarUsuario(usuario);

	    System.out.println("USUARIO CADASTRADO");

	    response.sendRedirect("loginUsuario.jsp");
	}

	// =========================================================
	// CADASTRO EMPRESA
	// =========================================================

	protected void cadastrarEmpresa(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		JavaBeans empresa = new JavaBeans();

		// DADOS USUARIO

		empresa.setNome(request.getParameter("nome"));
		empresa.setEmail(request.getParameter("email"));
		empresa.setSenha(request.getParameter("senha"));
		empresa.setNumeroTelefone(request.getParameter("telefone"));

		String cpf = request.getParameter("cpf");

		if (cpf != null) {

			cpf = cpf.replace(".", "")
					 .replace("-", "");

		}

		empresa.setCpf(cpf);

		empresa.setTipoUsuario("EMPRESA");

		// CADASTRA USUARIO

		int idUsuarioGerado = dao.cadastrarUsuario(empresa);

		// DADOS EMPRESA

		empresa.setIdUsuario(idUsuarioGerado);

		empresa.setNomeEmpresa(request.getParameter("nomeFantasia"));

		empresa.setRazaoSocial(request.getParameter("razaoSocial"));

		String cnpj = request.getParameter("cnpj");

		if (cnpj != null) {

			cnpj = cnpj.replace(".", "")
					   .replace("/", "")
					   .replace("-", "");

		}

		empresa.setCnpj(cnpj);

		empresa.setCategoria(request.getParameter("categoria"));

		empresa.setDescricao(request.getParameter("descricao"));

		dao.cadastrarEmpresa(empresa);

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

		boolean autenticado = dao.loginUsuario(login);

		if (autenticado) {

			request.getSession().setAttribute(
					"usuarioLogado",
					login.getEmail());

			response.sendRedirect("index.jsp");

		}

		else {

			response.sendRedirect("loginUsuario.jsp?erro=1");

		}
	}

	// =========================================================
	// LOGIN EMPRESA
	// =========================================================

	protected void loginEmpresa(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		JavaBeans login = new JavaBeans();

		login.setEmail(request.getParameter("email"));

		login.setCnpj(request.getParameter("cnpj"));

		boolean autenticado = dao.loginEmpresa(login);

		if (autenticado) {

			request.getSession().setAttribute(
					"empresaLogada",
					login.getEmail());

			response.sendRedirect("dashboardEmpresa.jsp");

		}

		else {

			response.sendRedirect("loginEmpresa.jsp?erro=1");

		}
	}

	// =========================================================
	// CADASTRAR VEICULO
	// =========================================================

	protected void cadastrarVeiculo(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		JavaBeans carro = new JavaBeans();

		carro.setIdUsuario(
				Integer.parseInt(
						request.getParameter("idUsuario")));

		carro.setMarca(request.getParameter("marca"));

		carro.setModelo(request.getParameter("modelo"));

		carro.setAno(
				Integer.parseInt(
						request.getParameter("ano")));

		carro.setCor(request.getParameter("cor"));

		carro.setPlaca(request.getParameter("placa"));

		carro.setCombustivel(
				request.getParameter("combustivel"));

		carro.setQuilometragem(
				Integer.parseInt(
						request.getParameter("quilometragem")));

		carro.setImagem(request.getParameter("foto"));

		dao.cadastrarCarro(carro);

		response.sendRedirect("meusVeiculos.jsp");

	}

	// =========================================================
	// CADASTRAR SERVICO
	// =========================================================

	protected void cadastrarServico(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		JavaBeans servico = new JavaBeans();

		servico.setIdEmpresa(
				Integer.parseInt(
						request.getParameter("idEmpresa")));

		servico.setServico(
				request.getParameter("nomeServico"));

		servico.setDescricao(
				request.getParameter("descricao"));

		servico.setPreco(
				Double.parseDouble(
						request.getParameter("preco")));

		servico.setPrazo(
				request.getParameter("tempoEstimado"));

		servico.setStatus(
				request.getParameter("statusServico"));

		dao.cadastrarServico(servico);

		response.sendRedirect("dashboardEmpresa.jsp");

	}

	// =========================================================
	// LISTAR ANUNCIOS
	// =========================================================

	protected void listarAnuncios(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<JavaBeans> lista =
				dao.listarAnuncios();

		request.setAttribute("anuncios", lista);

		RequestDispatcher rd =
				request.getRequestDispatcher("listar.jsp");

		rd.forward(request, response);

	}

	// =========================================================
	// DELETAR ANUNCIO
	// =========================================================

	protected void deletarAnuncio(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		JavaBeans anuncio = new JavaBeans();

		anuncio.setIdVenda(
				Integer.parseInt(
						request.getParameter("idVenda")));

		dao.deletarAnuncio(anuncio);

		response.sendRedirect("navegacao");

	}

	// =========================================================
	// EDITAR ANUNCIO
	// =========================================================

	protected void editarAnuncio(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		int idVenda =
				Integer.parseInt(
						request.getParameter("idVenda"));

		JavaBeans anuncio =
				dao.buscarAnuncio(idVenda);

		request.setAttribute("anuncio", anuncio);

		RequestDispatcher rd =
				request.getRequestDispatcher(
						"editarAnuncio.jsp");

		rd.forward(request, response);

	}

	// =========================================================
	// UPDATE ANUNCIO
	// =========================================================

	protected void atualizarAnuncio(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		JavaBeans anuncio = new JavaBeans();

		anuncio.setIdVenda(
				Integer.parseInt(
						request.getParameter("idVenda")));

		anuncio.setTituloAnuncio(
				request.getParameter("tituloAnuncio"));

		anuncio.setDescricao(
				request.getParameter("descricao"));

		anuncio.setPreco(
				Double.parseDouble(
						request.getParameter("preco")));

		anuncio.setCidade(
				request.getParameter("cidade"));

		anuncio.setEstado(
				request.getParameter("estado"));

		dao.editarAnuncio(anuncio);

		response.sendRedirect("navegacao");

	}
}
