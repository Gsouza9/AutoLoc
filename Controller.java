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
		"/update", "/cadveiculo", "/cadservico", "/cadproduto", "/vendedorservicos", "/buscarEditar", "/updateAnuncio" })

public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;

	DAO dao;

	@Override
	public void init() {
		dao = new DAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();

		System.out.println("ACTION: " + action);

		if (action.equals("/navegacao")) {
			listarAnuncios(request, response);
		} else if (action.equals("/edit")) {
			editarAnuncio(request, response);
		} else if (action.equals("/deletar")) {
			deletarAnuncio(request, response);
		} else if (action.equals("/vendedorservicos")) {
			carregarServicosProdutos(request, response);
		} else {
			response.sendRedirect("index.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String action = request.getServletPath();

		System.out.println("POST ACTION: " + action);

		if (action.equals("/cadastrou")) {
			cadastrarUsuario(request, response);
		} else if (action.equals("/cadastrol")) {
			cadastrarEmpresa(request, response);
		} else if (action.equals("/loginu")) {
			loginUsuario(request, response);
		} else if (action.equals("/logine")) {
			loginEmpresa(request, response);
		} else if (action.equals("/cadveiculo")) {
			cadastrarVeiculo(request, response);
		} else if (action.equals("/cadservico")) {
			cadastrarServico(request, response);
		} else if (action.equals("/cadproduto")) {
			cadastrarProduto(request, response);
		} else if (action.equals("/updateAnuncio")) {
			atualizarAnuncio(request, response);
		} else {
			response.sendRedirect("index.jsp");
		}
	}

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

		if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
			response.sendRedirect("cadastro.jsp?erro=nome");
			return;
		}

		int idUsuarioGerado = dao.cadastrarUsuario(usuario);

		if (idUsuarioGerado <= 0) {
			response.sendRedirect("cadastro.jsp?erro=banco");
			return;
		}

		response.sendRedirect("login.jsp");
	}

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

		if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
			response.sendRedirect("cadastro.jsp?erro=nome");
			return;
		}

		if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
			response.sendRedirect("cadastro.jsp?erro=email");
			return;
		}

		if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()) {
			response.sendRedirect("cadastro.jsp?erro=senha");
			return;
		}

		int idUsuarioGerado = dao.cadastrarUsuario(usuario);

		if (idUsuarioGerado <= 0) {
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

		if (empresa.getNomeEmpresa() == null || empresa.getNomeEmpresa().trim().isEmpty()) {
			response.sendRedirect("cadastro.jsp?erro=empresa");
			return;
		}

		if (empresa.getCnpj() == null || empresa.getCnpj().trim().isEmpty()) {
			response.sendRedirect("cadastro.jsp?erro=cnpj");
			return;
		}

		if (empresa.getCategoria() == null || empresa.getCategoria().trim().isEmpty()) {
			response.sendRedirect("cadastro.jsp?erro=categoria");
			return;
		}

		boolean empresaCadastrada = dao.cadastrarEmpresa(empresa);

		if (!empresaCadastrada) {
			response.sendRedirect("cadastro.jsp?erro=empresa");
			return;
		}

		response.sendRedirect("login.jsp");
	}

	protected void loginUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JavaBeans login = new JavaBeans();

		login.setEmail(request.getParameter("email"));
		login.setSenha(request.getParameter("senha"));

		System.out.println("LOGIN GERAL");
		System.out.println("EMAIL: " + login.getEmail());

		JavaBeans usuarioLogado = dao.loginGeral(login);

		if (usuarioLogado != null) {
			request.getSession().setAttribute("usuarioLogado", usuarioLogado.getEmail());
			request.getSession().setAttribute("nomeUsuario", usuarioLogado.getNome());
			request.getSession().setAttribute("emailUsuario", usuarioLogado.getEmail());
			request.getSession().setAttribute("telefoneUsuario", usuarioLogado.getNumeroTelefone());
			request.getSession().setAttribute("cpfUsuario", usuarioLogado.getCpf());
			request.getSession().setAttribute("idUsuario", usuarioLogado.getIdUsuario());
			request.getSession().setAttribute("tipoUsuario", usuarioLogado.getTipoUsuario());

			if ("EMPRESA".equals(usuarioLogado.getTipoUsuario())) {
				request.getSession().setAttribute("idEmpresa", usuarioLogado.getIdEmpresa());
				response.sendRedirect("vendedorpainel.jsp");
			} else {
				response.sendRedirect("consumidor.jsp");
			}
		} else {
			response.sendRedirect("cadastro.jsp");
		}
	}

	protected void loginEmpresa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JavaBeans login = new JavaBeans();

		login.setEmail(request.getParameter("email"));
		login.setCnpj(request.getParameter("cnpj"));

		boolean autenticado = dao.loginEmpresa(login);

		if (autenticado) {
			request.getSession().setAttribute("empresaLogada", login.getEmail());
			response.sendRedirect("vendedorinicio.jsp");
		} else {
			response.sendRedirect("login.jsp?erro=1");
		}
	}

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

		response.sendRedirect("vendedorveiculos.jsp");
	}

	protected void cadastrarServico(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JavaBeans servico = new JavaBeans();

		Object idEmpresaSessao = request.getSession().getAttribute("idEmpresa");

		if (idEmpresaSessao == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		servico.setIdEmpresa(Integer.parseInt(idEmpresaSessao.toString()));
		servico.setServico(request.getParameter("nomeServico"));
		servico.setDescricao(request.getParameter("descricao"));

		String precoTexto = request.getParameter("preco");
		double preco = 0;

		if (precoTexto != null && !precoTexto.trim().isEmpty()) {
			precoTexto = precoTexto.trim().replace(".", "").replace(",", ".");
			preco = Double.parseDouble(precoTexto);
		}

		servico.setPreco(preco);
		servico.setPrazo(request.getParameter("tempoEstimado"));
		servico.setStatus(request.getParameter("statusServico"));

		dao.cadastrarServico(servico);

		response.sendRedirect("vendedorservicos");
	}

	protected void cadastrarProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JavaBeans produto = new JavaBeans();

		Object idEmpresaSessao = request.getSession().getAttribute("idEmpresa");

		if (idEmpresaSessao == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		produto.setIdEmpresa(Integer.parseInt(idEmpresaSessao.toString()));
		produto.setNomeProduto(request.getParameter("nomeProduto"));
		produto.setCategoria(request.getParameter("categoriaProduto"));
		produto.setDescricao(request.getParameter("descricaoProduto"));

		String precoTexto = request.getParameter("precoProduto");
		double preco = 0;

		if (precoTexto != null && !precoTexto.trim().isEmpty()) {
			precoTexto = precoTexto.trim().replace(".", "").replace(",", ".");
			preco = Double.parseDouble(precoTexto);
		}

		produto.setPreco(preco);

		String estoqueTexto = request.getParameter("estoqueProduto");
		int estoque = 0;

		if (estoqueTexto != null && !estoqueTexto.trim().isEmpty()) {
			estoque = Integer.parseInt(estoqueTexto);
		}

		produto.setEstoque(estoque);
		produto.setStatus(request.getParameter("statusProduto"));

		dao.cadastrarProduto(produto);

		response.sendRedirect("vendedorservicos");
	}

	protected void carregarServicosProdutos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Object idEmpresaSessao = request.getSession().getAttribute("idEmpresa");

		if (idEmpresaSessao == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		int idEmpresa = Integer.parseInt(idEmpresaSessao.toString());

		ArrayList<JavaBeans> servicos = dao.listarServicosPorEmpresa(idEmpresa);
		ArrayList<JavaBeans> produtos = dao.listarProdutosPorEmpresa(idEmpresa);

		request.setAttribute("servicos", servicos);
		request.setAttribute("produtos", produtos);

		RequestDispatcher rd = request.getRequestDispatcher("vendedorservicos.jsp");
		rd.forward(request, response);
	}

	protected void listarAnuncios(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<JavaBeans> lista = dao.listarAnuncios();

		request.setAttribute("anuncios", lista);

		RequestDispatcher rd = request.getRequestDispatcher("listar.jsp");

		rd.forward(request, response);
	}

	protected void deletarAnuncio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JavaBeans anuncio = new JavaBeans();

		anuncio.setIdVenda(Integer.parseInt(request.getParameter("idVenda")));

		dao.deletarAnuncio(anuncio);

		response.sendRedirect("navegacao");
	}

	protected void editarAnuncio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int idVenda = Integer.parseInt(request.getParameter("idVenda"));

		JavaBeans anuncio = dao.buscarAnuncio(idVenda);

		request.setAttribute("anuncio", anuncio);

		RequestDispatcher rd = request.getRequestDispatcher("editarAnuncio.jsp");

		rd.forward(request, response);
	}

	protected void atualizarAnuncio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JavaBeans anuncio = new JavaBeans();

		anuncio.setIdVenda(Integer.parseInt(request.getParameter("idVenda")));
		anuncio.setTituloAnuncio(request.getParameter("tituloAnuncio"));
		anuncio.setDescricao(request.getParameter("descricao"));

		String precoTexto = request.getParameter("preco");
		double preco = 0;

		if (precoTexto != null && !precoTexto.trim().isEmpty()) {
			precoTexto = precoTexto.trim().replace(".", "").replace(",", ".");
			preco = Double.parseDouble(precoTexto);
		}

		anuncio.setPreco(preco);
		anuncio.setCidade(request.getParameter("cidade"));
		anuncio.setEstado(request.getParameter("estado"));

		dao.editarAnuncio(anuncio);

		response.sendRedirect("navegacao");
	}
}
