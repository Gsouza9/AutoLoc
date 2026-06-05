package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.MultipartConfig;
import java.io.File;
import jakarta.servlet.http.Part;

import model.DAO;
import model.JavaBeans;

@MultipartConfig
@WebServlet({ "/cadastro", "/deletar", "/edit", "/navegacao", "/cadastrou", "/cadastrol", "/loginu", "/logine",
	"/update", "/cadveiculo", "/cadservico", "/cadproduto", "/vendedorservicos", "/vendedorveiculos",
	"/buscarEditar", "/updateAnuncio", "/vendedorpainel", "/atualizarLoja", "/editarDadosVeiculo","/consumidor","/vendedorinicio" })

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
			deletarAnuncios(request, response);
		} else if (action.equals("/vendedorservicos")) {
			carregarServicosProdutos(request, response);
		} else if (action.equals("/vendedorveiculos")) {
			carregarVeiculos(request, response);
		} else if (action.equals("/vendedorpainel")) {
			carregarDadosLoja(request, response);  
		} else if (action.equals("/consumidor")) {
		    carregarHome(request, response);
		} else if(action.equals("/vendedorinicio")) {
			 carregarInicioVendedor(request, response);
		}else {
			response.sendRedirect("login.jsp");
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
		} else if (action.equals("/atualizarLoja")) {
			atualizarLoja(request, response);
		}  else if (action.equals("/editarDadosVeiculo")) {
			atualizarDadosVeiculo(request, response);
		}	else {
			response.sendRedirect("login.jsp");
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
				response.sendRedirect(request.getContextPath() + "/vendedorinicio"); 
			} else {
				response.sendRedirect(request.getContextPath() + "/consumidor");
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
			response.sendRedirect(request.getContextPath() + "/vendedorpainel");
		} else {
			response.sendRedirect("cadastro.jsp?erro=1");
		}
	}
	
	protected void carregarHome(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    ArrayList<JavaBeans> destaques = dao.listarAnuncio();
	    request.setAttribute("destaques", destaques);
	    request.getRequestDispatcher("consumidor.jsp").forward(request, response);
	}

	protected void cadastrarVeiculo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JavaBeans veiculo = new JavaBeans();
		
		Part foto = request.getPart("foto");
		if (foto != null && foto.getSize() > 0) {
		    String nomeArquivo = System.currentTimeMillis() + "_" + foto.getSubmittedFileName();

		    // Salva na pasta fixa fora do projeto
		    String caminhoUpload = "C:/autoloc/uploads";

		    File pasta = new File(caminhoUpload);
		    if (!pasta.exists()) {
		        pasta.mkdirs();
		    }

		    foto.write(caminhoUpload + File.separator + nomeArquivo);
		    veiculo.setImagem(nomeArquivo); // salva só o nome no banco
		}

		Object idUsuarioSessao = request.getSession().getAttribute("idUsuario");

		if (idUsuarioSessao == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}

		veiculo.setIdUsuario(Integer.parseInt(idUsuarioSessao.toString()));
		veiculo.setMarca(request.getParameter("marca"));
		veiculo.setModelo(request.getParameter("modelo"));
		veiculo.setCor(request.getParameter("cor"));
		veiculo.setPlaca(request.getParameter("placa"));
		veiculo.setChassi(request.getParameter("chassi"));
		veiculo.setCombustivel(request.getParameter("combustivel"));
		//veiculo.setImagem(request.getParameter("foto"));
		
		veiculo.setTituloAnuncio(request.getParameter("tituloAnuncio"));
		veiculo.setDescricao(request.getParameter("descricao"));
		veiculo.setStatus(request.getParameter("statusVenda"));
		veiculo.setCambio(request.getParameter("cambio"));
		veiculo.setCidade(request.getParameter("cidade"));
		veiculo.setEstado(request.getParameter("estado"));

		String anoTexto = request.getParameter("ano");
		int ano = 0;
		if (anoTexto != null && !anoTexto.trim().isEmpty()) {
			ano = Integer.parseInt(anoTexto);
		}
		veiculo.setAno(ano);

		String precoTexto = request.getParameter("preco");
		double preco = 0;
		if (precoTexto != null && !precoTexto.trim().isEmpty()) {
			precoTexto = precoTexto.trim().replace(".", "").replace(",", ".");
			preco = Double.parseDouble(precoTexto);
		}
		veiculo.setPreco(preco);

		String kmTexto = request.getParameter("kmRodados");
		int km = 0;
		if (kmTexto != null && !kmTexto.trim().isEmpty()) {
			km = Integer.parseInt(kmTexto);
		}
		veiculo.setQuilometragem(km);

		String portasTexto = request.getParameter("portas");
		int portas = 0;
		if (portasTexto != null && !portasTexto.trim().isEmpty()) {
			portas = Integer.parseInt(portasTexto);
		}
		veiculo.setPortas(portas);

		String finalPlaca = request.getParameter("finalPlaca");
		if ((finalPlaca == null || finalPlaca.trim().isEmpty()) && veiculo.getPlaca() != null && !veiculo.getPlaca().isEmpty()) {
			finalPlaca = veiculo.getPlaca().substring(veiculo.getPlaca().length() - 1);
		}
		veiculo.setFinalPlaca(finalPlaca);

		String aceitaTroca = request.getParameter("aceitaTroca");
		veiculo.setAceitaTroca("SIM".equalsIgnoreCase(aceitaTroca));

		boolean cadastrou = dao.cadastrarVeiculoVenda(veiculo);

		System.out.println("CADASTROU VEICULO VENDA? " + cadastrou);

		response.sendRedirect(request.getContextPath() + "/vendedorveiculos");
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
			response.sendRedirect("vendedorservicos.jsp");
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

	    int pagina = 1;
	    String paginaParam = request.getParameter("pagina");
	    if (paginaParam != null && !paginaParam.isEmpty()) {
	        pagina = Integer.parseInt(paginaParam);
	    }

	    int total = dao.contarAnuncios();
	    int totalPaginas = (int) Math.ceil((double) total / 6);

	    ArrayList<JavaBeans> lista = dao.listarAnuncio(pagina);

	    System.out.println("TOTAL DE ANUNCIOS: " + lista.size());
	    for (JavaBeans a : lista) {
	        System.out.println("TITULO: " + a.getTituloAnuncio() + " | FOTO: " + a.getImagem());
	    }

	    request.setAttribute("anuncios", lista);
	    request.setAttribute("paginaAtual", pagina);
	    request.setAttribute("totalPaginas", totalPaginas);

	    request.getRequestDispatcher("veiculos.jsp").forward(request, response);
	}
	

	protected void deletarAnuncios(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JavaBeans anuncio = new JavaBeans();

		anuncio.setIdVenda(Integer.parseInt(request.getParameter("idVenda")));

		dao.deletarAnuncio(anuncio);

		response.sendRedirect(request.getContextPath() + "/vendedorveiculos");
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

	protected void carregarVeiculos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Object idUsuarioSessao = request.getSession().getAttribute("idUsuario");

		if (idUsuarioSessao == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}

		int idUsuario = Integer.parseInt(idUsuarioSessao.toString());

		ArrayList<JavaBeans> veiculos = dao.listarVeiculosVendaPorUsuario(idUsuario);

		request.setAttribute("veiculos", veiculos);

		RequestDispatcher rd = request.getRequestDispatcher("vendedorveiculos.jsp");
		rd.forward(request, response);
	}
	
	protected void carregarDadosLoja(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Object idEmpresaSessao = request.getSession().getAttribute("idEmpresa");

		if (idEmpresaSessao == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		int idEmpresa = Integer.parseInt(idEmpresaSessao.toString());

		JavaBeans loja = dao.buscarEmpresaPorId(idEmpresa);

		request.setAttribute("loja", loja);

		RequestDispatcher rd = request.getRequestDispatcher("vendedorpainel.jsp");
		rd.forward(request, response);
	}

	protected void atualizarLoja(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		Object idEmpresaSessao = request.getSession().getAttribute("idEmpresa");

		if (idEmpresaSessao == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		JavaBeans loja = new JavaBeans();

		loja.setIdEmpresa(Integer.parseInt(idEmpresaSessao.toString()));
		loja.setNumeroTelefone(request.getParameter("telefone"));
		loja.setEndereco(request.getParameter("endereco"));
		loja.setHorario(request.getParameter("horario"));

		dao.atualizarDadosLoja(loja);

		response.sendRedirect("vendedorpainel");
	}
	
	protected void atualizarDadosVeiculo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Object idUsuarioSessao = request.getSession().getAttribute("idUsuario");

		if (idUsuarioSessao == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}

		JavaBeans veiculo = new JavaBeans();

		veiculo.setIdVenda(Integer.parseInt(request.getParameter("idVenda")));
		veiculo.setStatus(request.getParameter("statusVenda"));
		veiculo.setCidade(request.getParameter("cidade"));

		String kmTexto = request.getParameter("kmRodados");
		int km = 0;

		if (kmTexto != null && !kmTexto.trim().isEmpty()) {
			km = Integer.parseInt(kmTexto);
		}

		veiculo.setQuilometragem(km);

		dao.atualizarDadosVeiculoVenda(veiculo);

		response.sendRedirect(request.getContextPath() + "/vendedorveiculos");
	}
	//------------------------------------------------------------------------------
	protected void carregarInicioVendedor(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    Object idUsuarioSessao = request.getSession().getAttribute("idUsuario");
	    Object idEmpresaSessao = request.getSession().getAttribute("idEmpresa");

	    System.out.println("=== DEBUG INICIO VENDEDOR ===");
	    System.out.println("ID USUARIO NA SESSAO: " + idUsuarioSessao);
	    System.out.println("ID EMPRESA NA SESSAO: " + idEmpresaSessao);

	    if (idUsuarioSessao == null || idEmpresaSessao == null) {
	        response.sendRedirect("login.jsp");
	        return;
	    }

	    int idUsuario = Integer.parseInt(idUsuarioSessao.toString());
	    int idEmpresa = Integer.parseInt(idEmpresaSessao.toString());

	    System.out.println("TOTAL VEICULOS ENCONTRADOS: " + dao.contarVeiculosPorUsuario(idUsuario));
	    System.out.println("=============================");

	    JavaBeans loja = dao.buscarEmpresaPorId(idEmpresa);
	    String nomeLoja = (loja != null && loja.getNomeEmpresa() != null) ? loja.getNomeEmpresa() : "";

	    request.setAttribute("nomeLoja",      nomeLoja);
	    request.setAttribute("totalVeiculos", dao.contarVeiculosPorUsuario(idUsuario));
	    request.setAttribute("totalServicos", dao.contarServicosPorEmpresa(idEmpresa));
	    request.setAttribute("totalProdutos", dao.contarProdutosPorEmpresa(idEmpresa));

	    request.getRequestDispatcher("vendedorinicio.jsp").forward(request, response);
	}
	
}
