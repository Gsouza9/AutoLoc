```java
package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.mindrot.jbcrypt.BCrypt;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import model.DAO;
import model.JavaBeans;

@MultipartConfig(
	fileSizeThreshold = 1024 * 1024 * 2,
	maxFileSize = 1024 * 1024 * 10,
	maxRequestSize = 1024 * 1024 * 50
)

@WebServlet({
	"/cadastro",
	"/deletar",
	"/edit",
	"/navegacao",
	"/cadastrou",
	"/cadastrol",
	"/loginu",
	"/logine",
	"/logout",
	"/update",
	"/cadveiculo",
	"/cadservico",
	"/cadproduto",
	"/vendedorservicos",
	"/vendedorveiculos",
	"/buscarEditar",
	"/updateAnuncio",
	"/vendedorpainel",
	"/atualizarLoja",
	"/editarDadosVeiculo",
	"/consumidor"
})

public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private DAO dao;

	@Override
	public void init() {
		dao = new DAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();

		try {

			switch (action) {

			case "/navegacao":
				listarAnuncios(request, response);
				break;

			case "/edit":
				editarAnuncio(request, response);
				break;

			case "/deletar":
				deletarAnuncios(request, response);
				break;

			case "/vendedorservicos":
				carregarServicosProdutos(request, response);
				break;

			case "/vendedorveiculos":
				carregarVeiculos(request, response);
				break;

			case "/vendedorpainel":
				carregarDadosLoja(request, response);
				break;

			case "/consumidor":
				carregarHome(request, response);
				break;

			case "/logout":
				logout(request, response);
				break;

			default:
				response.sendRedirect("login.jsp");
				break;
			}

		} catch (Exception e) {

			e.printStackTrace();
			response.sendRedirect("erro.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String action = request.getServletPath();

		try {

			switch (action) {

			case "/cadastrou":
				cadastrarUsuario(request, response);
				break;

			case "/cadastrol":
				cadastrarEmpresa(request, response);
				break;

			case "/loginu":
				loginUsuario(request, response);
				break;

			case "/logine":
				loginEmpresa(request, response);
				break;

			case "/cadveiculo":
				cadastrarVeiculo(request, response);
				break;

			case "/cadservico":
				cadastrarServico(request, response);
				break;

			case "/cadproduto":
				cadastrarProduto(request, response);
				break;

			case "/updateAnuncio":
				atualizarAnuncio(request, response);
				break;

			case "/atualizarLoja":
				atualizarLoja(request, response);
				break;

			case "/editarDadosVeiculo":
				atualizarDadosVeiculo(request, response);
				break;

			default:
				response.sendRedirect("login.jsp");
				break;
			}

		} catch (Exception e) {

			e.printStackTrace();
			response.sendRedirect("erro.jsp");
		}
	}

	private boolean usuarioLogado(HttpServletRequest request) {
		return request.getSession().getAttribute("idUsuario") != null;
	}

	private boolean empresaLogada(HttpServletRequest request) {
		return request.getSession().getAttribute("idEmpresa") != null;
	}

	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session != null) {
			session.invalidate();
		}

		response.sendRedirect("login.jsp");
	}

	protected void cadastrarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JavaBeans usuario = new JavaBeans();

		usuario.setNome(request.getParameter("nome"));
		usuario.setEmail(request.getParameter("email"));

		String senha = request.getParameter("senha");
		String senhaHash = BCrypt.hashpw(senha, BCrypt.gensalt());

		usuario.setSenha(senhaHash);

		usuario.setNumeroTelefone(request.getParameter("telefone"));

		String cpf = request.getParameter("cpf");

		if (cpf != null) {
			cpf = cpf.replace(".", "").replace("-", "");
		}

		usuario.setCpf(cpf);
		usuario.setTipoUsuario("CLIENTE");

		int idUsuarioGerado = dao.cadastrarUsuario(usuario);

		if (idUsuarioGerado <= 0) {
			response.sendRedirect("cadastro.jsp?erro=usuario");
			return;
		}

		response.sendRedirect("login.jsp?sucesso=1");
	}

	protected void cadastrarEmpresa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JavaBeans usuario = new JavaBeans();

		usuario.setNome(request.getParameter("nome"));
		usuario.setEmail(request.getParameter("email"));

		String senha = request.getParameter("senha");
		String senhaHash = BCrypt.hashpw(senha, BCrypt.gensalt());

		usuario.setSenha(senhaHash);

		usuario.setNumeroTelefone(request.getParameter("telefone"));
		usuario.setTipoUsuario("EMPRESA");

		int idUsuarioGerado = dao.cadastrarUsuario(usuario);

		if (idUsuarioGerado <= 0) {
			response.sendRedirect("cadastro.jsp?erro=usuario");
			return;
		}

		JavaBeans empresa = new JavaBeans();

		empresa.setIdUsuario(idUsuarioGerado);
		empresa.setNomeEmpresa(request.getParameter("nomeFantasia"));
		empresa.setRazaoSocial(request.getParameter("razaoSocial"));
		empresa.setDescricao(request.getParameter("descricao"));
		empresa.setCategoria(request.getParameter("categoria"));
		empresa.setNumeroTelefone(request.getParameter("telefone"));
		empresa.setEmail(request.getParameter("email"));

		String cnpj = request.getParameter("cnpj");

		if (cnpj != null) {
			cnpj = cnpj.replace(".", "")
					.replace("/", "")
					.replace("-", "");
		}

		empresa.setCnpj(cnpj);

		empresa.setCep(request.getParameter("cep"));
		empresa.setEndereco(request.getParameter("endereco"));
		empresa.setNumero(request.getParameter("numero"));

		boolean empresaCadastrada = dao.cadastrarEmpresa(empresa);

		if (!empresaCadastrada) {
			response.sendRedirect("cadastro.jsp?erro=empresa");
			return;
		}

		response.sendRedirect("login.jsp?empresa=1");
	}

	protected void loginUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		JavaBeans usuario = dao.buscarUsuarioPorEmail(email);

		if (usuario == null) {
			response.sendRedirect("login.jsp?erro=login");
			return;
		}

		boolean senhaCorreta = BCrypt.checkpw(senha, usuario.getSenha());

		if (!senhaCorreta) {
			response.sendRedirect("login.jsp?erro=login");
			return;
		}

		HttpSession session = request.getSession();

		session.setAttribute("usuarioLogado", usuario.getEmail());
		session.setAttribute("nomeUsuario", usuario.getNome());
		session.setAttribute("emailUsuario", usuario.getEmail());
		session.setAttribute("telefoneUsuario", usuario.getNumeroTelefone());
		session.setAttribute("cpfUsuario", usuario.getCpf());
		session.setAttribute("idUsuario", usuario.getIdUsuario());
		session.setAttribute("tipoUsuario", usuario.getTipoUsuario());

		if ("EMPRESA".equals(usuario.getTipoUsuario())) {

			session.setAttribute("idEmpresa", usuario.getIdEmpresa());

			response.sendRedirect(request.getContextPath() + "/vendedorpainel");

		} else {

			response.sendRedirect(request.getContextPath() + "/consumidor");
		}
	}

	protected void loginEmpresa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		loginUsuario(request, response);
	}

	protected void carregarHome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<JavaBeans> destaques = dao.listarAnuncio();

		request.setAttribute("destaques", destaques);

		request.getRequestDispatcher("consumidor.jsp")
				.forward(request, response);
	}

	protected void cadastrarVeiculo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!usuarioLogado(request)) {
			response.sendRedirect("login.jsp");
			return;
		}

		JavaBeans veiculo = new JavaBeans();

		Part foto = request.getPart("foto");

		if (foto != null && foto.getSize() > 0) {

			String contentType = foto.getContentType();

			if (!contentType.startsWith("image/")) {
				response.sendRedirect("vendedorveiculos?erro=formato");
				return;
			}

			String nomeArquivo = System.currentTimeMillis()
					+ "_"
					+ foto.getSubmittedFileName();

			String caminhoUpload =
					System.getProperty("user.home")
					+ "/AutoLocUploads";

			File pasta = new File(caminhoUpload);

			if (!pasta.exists()) {
				pasta.mkdirs();
			}

			foto.write(caminhoUpload
					+ File.separator
					+ nomeArquivo);

			veiculo.setImagem(nomeArquivo);
		}

		int idUsuario = Integer.parseInt(
				request.getSession()
						.getAttribute("idUsuario")
						.toString());

		veiculo.setIdUsuario(idUsuario);

		veiculo.setTituloAnuncio(request.getParameter("tituloAnuncio"));
		veiculo.setMarca(request.getParameter("marca"));
		veiculo.setModelo(request.getParameter("modelo"));
		veiculo.setCor(request.getParameter("cor"));
		veiculo.setPlaca(request.getParameter("placa"));
		veiculo.setChassi(request.getParameter("chassi"));
		veiculo.setCombustivel(request.getParameter("combustivel"));
		veiculo.setDescricao(request.getParameter("descricao"));
		veiculo.setStatus(request.getParameter("statusVenda"));
		veiculo.setCambio(request.getParameter("cambio"));
		veiculo.setCidade(request.getParameter("cidade"));
		veiculo.setEstado(request.getParameter("estado"));

		String anoTexto = request.getParameter("ano");

		if (anoTexto != null && !anoTexto.isEmpty()) {
			veiculo.setAno(Integer.parseInt(anoTexto));
		}

		String precoTexto = request.getParameter("preco");

		if (precoTexto != null && !precoTexto.isEmpty()) {

			precoTexto = precoTexto
					.replace(".", "")
					.replace(",", ".");

			veiculo.setPreco(Double.parseDouble(precoTexto));
		}

		String kmTexto = request.getParameter("kmRodados");

		if (kmTexto != null && !kmTexto.isEmpty()) {
			veiculo.setQuilometragem(Integer.parseInt(kmTexto));
		}

		String portasTexto = request.getParameter("portas");

		if (portasTexto != null && !portasTexto.isEmpty()) {
			veiculo.setPortas(Integer.parseInt(portasTexto));
		}

		String aceitaTroca = request.getParameter("aceitaTroca");

		veiculo.setAceitaTroca(
				"SIM".equalsIgnoreCase(aceitaTroca)
		);

		dao.cadastrarVeiculoVenda(veiculo);

		response.sendRedirect(
				request.getContextPath()
				+ "/vendedorveiculos?sucesso=1"
		);
	}

	protected void cadastrarServico(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!empresaLogada(request)) {
			response.sendRedirect("login.jsp");
			return;
		}

		JavaBeans servico = new JavaBeans();

		int idEmpresa = Integer.parseInt(
				request.getSession()
						.getAttribute("idEmpresa")
						.toString());

		servico.setIdEmpresa(idEmpresa);
		servico.setServico(request.getParameter("nomeServico"));
		servico.setDescricao(request.getParameter("descricao"));
		servico.setPrazo(request.getParameter("tempoEstimado"));
		servico.setStatus(request.getParameter("statusServico"));

		String precoTexto = request.getParameter("preco");

		if (precoTexto != null && !precoTexto.isEmpty()) {

			precoTexto = precoTexto
					.replace(".", "")
					.replace(",", ".");

			servico.setPreco(Double.parseDouble(precoTexto));
		}

		dao.cadastrarServico(servico);

		response.sendRedirect("vendedorservicos?sucesso=servico");
	}

	protected void cadastrarProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!empresaLogada(request)) {
			response.sendRedirect("login.jsp");
			return;
		}

		JavaBeans produto = new JavaBeans();

		int idEmpresa = Integer.parseInt(
				request.getSession()
						.getAttribute("idEmpresa")
						.toString());

		produto.setIdEmpresa(idEmpresa);

		produto.setNomeProduto(request.getParameter("nomeProduto"));
		produto.setCategoria(request.getParameter("categoriaProduto"));
		produto.setDescricao(request.getParameter("descricaoProduto"));
		produto.setStatus(request.getParameter("statusProduto"));

		String precoTexto = request.getParameter("precoProduto");

		if (precoTexto != null && !precoTexto.isEmpty()) {

			precoTexto = precoTexto
					.replace(".", "")
					.replace(",", ".");

			produto.setPreco(Double.parseDouble(precoTexto));
		}

		String estoqueTexto = request.getParameter("estoqueProduto");

		if (estoqueTexto != null && !estoqueTexto.isEmpty()) {
			produto.setEstoque(Integer.parseInt(estoqueTexto));
		}

		dao.cadastrarProduto(produto);

		response.sendRedirect("vendedorservicos?sucesso=produto");
	}

	protected void carregarServicosProdutos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!empresaLogada(request)) {
			response.sendRedirect("login.jsp");
			return;
		}

		int idEmpresa = Integer.parseInt(
				request.getSession()
						.getAttribute("idEmpresa")
						.toString());

		ArrayList<JavaBeans> servicos =
				dao.listarServicosPorEmpresa(idEmpresa);

		ArrayList<JavaBeans> produtos =
				dao.listarProdutosPorEmpresa(idEmpresa);

		request.setAttribute("servicos", servicos);
		request.setAttribute("produtos", produtos);

		RequestDispatcher rd =
				request.getRequestDispatcher("vendedorservicos.jsp");

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

		int totalPaginas =
				(int) Math.ceil((double) total / 6);

		ArrayList<JavaBeans> lista =
				dao.listarAnuncio(pagina);

		request.setAttribute("anuncios", lista);
		request.setAttribute("paginaAtual", pagina);
		request.setAttribute("totalPaginas", totalPaginas);

		request.getRequestDispatcher("veiculos.jsp")
				.forward(request, response);
	}

	protected void deletarAnuncios(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JavaBeans anuncio = new JavaBeans();

		anuncio.setIdVenda(
				Integer.parseInt(
						request.getParameter("idVenda")
				)
		);

		dao.deletarAnuncio(anuncio);

		response.sendRedirect(
				request.getContextPath()
				+ "/vendedorveiculos"
		);
	}

	protected void editarAnuncio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int idVenda = Integer.parseInt(
				request.getParameter("idVenda")
		);

		JavaBeans anuncio =
				dao.buscarAnuncio(idVenda);

		request.setAttribute("anuncio", anuncio);

		RequestDispatcher rd =
				request.getRequestDispatcher("editarAnuncio.jsp");

		rd.forward(request, response);
	}

	protected void atualizarAnuncio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JavaBeans anuncio = new JavaBeans();

		anuncio.setIdVenda(
				Integer.parseInt(
						request.getParameter("idVenda")
				)
		);

		anuncio.setTituloAnuncio(
				request.getParameter("tituloAnuncio")
		);

		anuncio.setDescricao(
				request.getParameter("descricao")
		);

		String precoTexto =
				request.getParameter("preco");

		if (precoTexto != null && !precoTexto.isEmpty()) {

			precoTexto = precoTexto
					.replace(".", "")
					.replace(",", ".");

			anuncio.setPreco(
					Double.parseDouble(precoTexto)
			);
		}

		anuncio.setCidade(request.getParameter("cidade"));
		anuncio.setEstado(request.getParameter("estado"));

		dao.editarAnuncio(anuncio);

		response.sendRedirect("navegacao");
	}

	protected void carregarVeiculos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!usuarioLogado(request)) {
			response.sendRedirect("login.jsp");
			return;
		}

		int idUsuario = Integer.parseInt(
				request.getSession()
						.getAttribute("idUsuario")
						.toString());

		ArrayList<JavaBeans> veiculos =
				dao.listarVeiculosVendaPorUsuario(idUsuario);

		request.setAttribute("veiculos", veiculos);

		RequestDispatcher rd =
				request.getRequestDispatcher("vendedorveiculos.jsp");

		rd.forward(request, response);
	}

	protected void carregarDadosLoja(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!empresaLogada(request)) {
			response.sendRedirect("login.jsp");
			return;
		}

		int idEmpresa = Integer.parseInt(
				request.getSession()
						.getAttribute("idEmpresa")
						.toString());

		JavaBeans loja =
				dao.buscarEmpresaPorId(idEmpresa);

		request.setAttribute("loja", loja);

		RequestDispatcher rd =
				request.getRequestDispatcher("vendedorpainel.jsp");

		rd.forward(request, response);
	}

	protected void atualizarLoja(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!empresaLogada(request)) {
			response.sendRedirect("login.jsp");
			return;
		}

		JavaBeans loja = new JavaBeans();

		int idEmpresa = Integer.parseInt(
				request.getSession()
						.getAttribute("idEmpresa")
						.toString());

		loja.setIdEmpresa(idEmpresa);

		loja.setNumeroTelefone(
				request.getParameter("telefone")
		);

		loja.setEndereco(
				request.getParameter("endereco")
		);

		loja.setHorario(
				request.getParameter("horario")
		);

		loja.setCep(
				request.getParameter("cep")
		);

		dao.atualizarDadosLoja(loja);

		response.sendRedirect("vendedorpainel?sucesso=1");
	}

	protected void atualizarDadosVeiculo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!usuarioLogado(request)) {
			response.sendRedirect("login.jsp");
			return;
		}

		JavaBeans veiculo = new JavaBeans();

		veiculo.setIdVenda(
				Integer.parseInt(
						request.getParameter("idVenda")
				)
		);

		veiculo.setStatus(
				request.getParameter("statusVenda")
		);

		veiculo.setCidade(
				request.getParameter("cidade")
		);

		String kmTexto =
				request.getParameter("kmRodados");

		if (kmTexto != null && !kmTexto.isEmpty()) {

			veiculo.setQuilometragem(
					Integer.parseInt(kmTexto)
			);
		}

		dao.atualizarDadosVeiculoVenda(veiculo);

		response.sendRedirect(
				request.getContextPath()
				+ "/vendedorveiculos?editado=1"
		);
	}
}
```
