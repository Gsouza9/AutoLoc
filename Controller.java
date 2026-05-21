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

@WebServlet(urlPatterns = {
        "/cadastroUsuario",
        "/cadastroEmpresa",
        "/loginUsuario",
        "/loginEmpresa",
        "/cadastroVeiculo",
        "/cadastroServico",
        "/listarAnuncios",
        "/deletarAnuncio",
        "/editarAnuncio",
        "/updateAnuncio"
})

public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;

    DAO dao = new DAO();

    // =========================================================
    // GET
    // =========================================================

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        switch (action) {

            case "/listarAnuncios":
                listarAnuncios(request, response);
                break;

            case "/deletarAnuncio":
                deletarAnuncio(request, response);
                break;

            default:
                response.sendRedirect("index.jsp");
                break;
        }
    }

    // =========================================================
    // POST
    // =========================================================

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        switch (action) {

            case "/cadastroUsuario":
                cadastrarUsuario(request, response);
                break;

            case "/cadastroEmpresa":
                cadastrarEmpresa(request, response);
                break;

            case "/loginUsuario":
                loginUsuario(request, response);
                break;

            case "/loginEmpresa":
                loginEmpresa(request, response);
                break;

            case "/cadastroVeiculo":
                cadastrarVeiculo(request, response);
                break;

            case "/cadastroServico":
                cadastrarServico(request, response);
                break;

            case "/editarAnuncio":
                editarAnuncio(request, response);
                break;

            case "/updateAnuncio":
                atualizarAnuncio(request, response);
                break;

            default:
                response.sendRedirect("index.jsp");
                break;
        }
    }

    // =========================================================
    // CADASTRO USUÁRIO
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

            cpf = cpf.replace(".", "")
                     .replace("-", "");
        }

        usuario.setCpf(cpf);

        usuario.setTipoUsuario("CLIENTE");

        dao.cadastrarUsuario(usuario);

        response.sendRedirect("loginUsuario.jsp");
    }

    // =========================================================
    // CADASTRO EMPRESA
    // =========================================================

    protected void cadastrarEmpresa(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        JavaBeans empresa = new JavaBeans();

        // DADOS USUÁRIO
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

        // CADASTRA PRIMEIRO NA TABELA USUARIOS
        int idUsuarioGerado = dao.cadastrarUsuario(empresa);

        // DADOS DA EMPRESA
        empresa.setIdUsuario(idUsuarioGerado);

        empresa.setNomeEmpresa(
                request.getParameter("nomeFantasia")
        );

        empresa.setRazaoSocial(
                request.getParameter("razaoSocial")
        );

        String cnpj = request.getParameter("cnpj");

        if (cnpj != null) {

            cnpj = cnpj.replace(".", "")
                       .replace("/", "")
                       .replace("-", "");
        }

        empresa.setCnpj(cnpj);

        empresa.setCategoria(
                request.getParameter("categoria")
        );

        empresa.setDescricao(
                request.getParameter("descricao")
        );

        dao.cadastrarEmpresa(empresa);

        response.sendRedirect("loginEmpresa.jsp");
    }

    // =========================================================
    // LOGIN USUÁRIO
    // =========================================================

    protected void loginUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        JavaBeans login = new JavaBeans();

        login.setEmail(request.getParameter("email"));
        login.setSenha(request.getParameter("senha"));

        boolean autenticado = dao.loginUsuario(login);

        if (autenticado) {

            response.sendRedirect("index.jsp");

        } else {

            response.sendRedirect("loginUsuario.jsp?erro=1");
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

            response.sendRedirect("dashboardEmpresa.jsp");

        } else {

            response.sendRedirect("loginEmpresa.jsp?erro=1");
        }
    }

    // =========================================================
    // CADASTRAR VEÍCULO
    // =========================================================

    protected void cadastrarVeiculo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        JavaBeans carro = new JavaBeans();

        carro.setIdUsuario(
                Integer.parseInt(request.getParameter("idUsuario"))
        );

        carro.setMarca(request.getParameter("marca"));

        carro.setModelo(request.getParameter("modelo"));

        carro.setAno(
                Integer.parseInt(request.getParameter("ano"))
        );

        carro.setCor(request.getParameter("cor"));

        carro.setPlaca(request.getParameter("placa"));

        carro.setCombustivel(
                request.getParameter("combustivel")
        );

        carro.setQuilometragem(
                Integer.parseInt(
                        request.getParameter("quilometragem")
                )
        );

        carro.setImagem(request.getParameter("foto"));

        dao.cadastrarCarro(carro);

        response.sendRedirect("meusVeiculos.jsp");
    }

    // =========================================================
    // CADASTRAR SERVIÇO
    // =========================================================

    protected void cadastrarServico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        JavaBeans servico = new JavaBeans();

        servico.setIdEmpresa(
                Integer.parseInt(request.getParameter("idEmpresa"))
        );

        servico.setServico(
                request.getParameter("nomeServico")
        );

        servico.setDescricao(
                request.getParameter("descricao")
        );

        servico.setPreco(
                Double.parseDouble(request.getParameter("preco"))
        );

        servico.setPrazo(
                request.getParameter("tempoEstimado")
        );

        servico.setStatus(
                request.getParameter("statusServico")
        );

        dao.cadastrarServico(servico);

        response.sendRedirect("dashboardEmpresa.jsp");
    }

    // =========================================================
    // LISTAR ANÚNCIOS
    // =========================================================

    protected void listarAnuncios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<JavaBeans> lista = dao.listarAnuncios();

        request.setAttribute("anuncios", lista);

        RequestDispatcher rd =
                request.getRequestDispatcher("listar.jsp");

        rd.forward(request, response);
    }

    // =========================================================
    // DELETAR ANÚNCIO
    // =========================================================

    protected void deletarAnuncio(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        JavaBeans anuncio = new JavaBeans();

        anuncio.setIdVenda(
                Integer.parseInt(request.getParameter("idVenda"))
        );

        dao.deletarAnuncio(anuncio);

        response.sendRedirect("listarAnuncios");
    }

    // =========================================================
    // EDITAR ANÚNCIO
    // =========================================================

    protected void editarAnuncio(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("listarAnuncios");
    }

    // =========================================================
    // UPDATE ANÚNCIO
    // =========================================================

   protected void atualizarAnuncio(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    JavaBeans anuncio = new JavaBeans();

    anuncio.setIdVenda(
            Integer.parseInt(request.getParameter("idVenda"))
    );

    anuncio.setTituloAnuncio(
            request.getParameter("tituloAnuncio")
    );

    anuncio.setDescricao(
            request.getParameter("descricao")
    );

    anuncio.setPreco(
            Double.parseDouble(request.getParameter("preco"))
    );

    anuncio.setCidade(
            request.getParameter("cidade")
    );

    anuncio.setEstado(
            request.getParameter("estado")
    );

    dao.editarAnuncio(anuncio);

    response.sendRedirect("listarAnuncios");
}
