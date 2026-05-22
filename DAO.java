package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {

    // =========================================================
    // CONEXÃO
    // =========================================================

    private final String url =
    "jdbc:mysql://localhost:3306/autoloc?useTimezone=true&serverTimezone=UTC";

    private final String user = "root";

    private final String password = "123@senac";

    public Connection conectar() {

        Connection con = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {

            e.printStackTrace();

        }

        return con;
    }

    // =========================================================
    // USUÁRIO
    // =========================================================

    public int cadastrarUsuario(JavaBeans usuario) {

        int idGerado = 0;

        String sql =
        "INSERT INTO usuarios(nome,email,senha,telefone,cpf,tipo_usuario) VALUES(?,?,?,?,?,?)";

        try {

            Connection con = conectar();

            PreparedStatement pst =
            con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getEmail());
            pst.setString(3, usuario.getSenha());
            pst.setString(4, usuario.getNumeroTelefone());
            pst.setString(5, usuario.getCpf());
            pst.setString(6, usuario.getTipoUsuario());

            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();

            if (rs.next()) {
                idGerado = rs.getInt(1);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return idGerado;
    }

    public boolean loginUsuario(JavaBeans usuario) {

        String sql = "SELECT * FROM usuarios WHERE email=? AND senha=?";
        boolean ok = false;

        try {

            Connection con = conectar();

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, usuario.getEmail());
            pst.setString(2, usuario.getSenha());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                ok = true;
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ok;
    }

    public ArrayList<JavaBeans> listarUsuarios() {

        ArrayList<JavaBeans> lista = new ArrayList<>();

        String sql = "SELECT * FROM usuarios";

        try {

            Connection con = conectar();

            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                JavaBeans u = new JavaBeans();

                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setNumeroTelefone(rs.getString("telefone"));
                u.setCpf(rs.getString("cpf"));
                u.setTipoUsuario(rs.getString("tipo_usuario"));

                lista.add(u);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // =========================================================
    // EMPRESA
    // =========================================================

    public void cadastrarEmpresa(JavaBeans empresa) {

        String sql =
        "INSERT INTO empresas(id_usuario,nome_fantasia,razao_social,cnpj,descricao,telefone,email,categoria) VALUES(?,?,?,?,?,?,?,?)";

        try {

            Connection con = conectar();

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, empresa.getIdUsuario());
            pst.setString(2, empresa.getNomeEmpresa());
            pst.setString(3, empresa.getRazaoSocial());
            pst.setString(4, empresa.getCnpj());
            pst.setString(5, empresa.getDescricao());
            pst.setString(6, empresa.getNumeroTelefone());
            pst.setString(7, empresa.getEmail());
            pst.setString(8, empresa.getCategoria());

            pst.executeUpdate();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean loginEmpresa(JavaBeans empresa) {

        String sql = "SELECT * FROM empresas WHERE email=? AND cnpj=?";
        boolean ok = false;

        try {

            Connection con = conectar();

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, empresa.getEmail());
            pst.setString(2, empresa.getCnpj());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                ok = true;
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ok;
    }

    // =========================================================
    // VEÍCULOS
    // =========================================================

    public void cadastrarCarro(JavaBeans carro) {

        String sql =
        "INSERT INTO carros(id_usuario,marca,modelo,ano,cor,placa,combustivel,quilometragem,foto) VALUES(?,?,?,?,?,?,?,?,?)";

        try {

            Connection con = conectar();

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, carro.getIdUsuario());
            pst.setString(2, carro.getMarca());
            pst.setString(3, carro.getModelo());
            pst.setInt(4, carro.getAno());
            pst.setString(5, carro.getCor());
            pst.setString(6, carro.getPlaca());
            pst.setString(7, carro.getCombustivel());
            pst.setInt(8, carro.getQuilometragem());
            pst.setString(9, carro.getImagem());

            pst.executeUpdate();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =========================================================
    // SERVIÇOS
    // =========================================================

    public void cadastrarServico(JavaBeans servico) {

        String sql =
        "INSERT INTO servicos(id_empresa,nome_servico,descricao,preco,tempo_estimado,status_servico) VALUES(?,?,?,?,?,?)";

        try {

            Connection con = conectar();

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, servico.getIdEmpresa());
            pst.setString(2, servico.getServico());
            pst.setString(3, servico.getDescricao());
            pst.setDouble(4, servico.getPreco());
            pst.setString(5, servico.getPrazo());
            pst.setString(6, servico.getStatus());

            pst.executeUpdate();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =========================================================
    // ANÚNCIOS (OBRIGATÓRIO PARA SEU CONTROLLER)
    // =========================================================

    public ArrayList<JavaBeans> listarAnuncios() {

        ArrayList<JavaBeans> lista = new ArrayList<>();

        String sql = "SELECT * FROM veiculos_venda";

        try {

            Connection con = conectar();

            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                JavaBeans a = new JavaBeans();

                a.setIdVenda(rs.getInt("id_venda"));
                a.setIdUsuario(rs.getInt("id_usuario"));
                a.setIdCarro(rs.getInt("id_carro"));
                a.setTituloAnuncio(rs.getString("titulo_anuncio"));
                a.setDescricao(rs.getString("descricao"));
                a.setPreco(rs.getDouble("preco"));
                a.setQuilometragem(rs.getInt("km_rodados"));
                a.setCambio(rs.getString("cambio"));
                a.setPortas(rs.getInt("portas"));
                a.setFinalPlaca(rs.getString("final_placa"));
                a.setCidade(rs.getString("cidade"));
                a.setEstado(rs.getString("estado"));

                lista.add(a);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void deletarAnuncio(JavaBeans anuncio) {

        String sql = "DELETE FROM veiculos_venda WHERE id_venda=?";

        try {

            Connection con = conectar();

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, anuncio.getIdVenda());

            pst.executeUpdate();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editarAnuncio(JavaBeans anuncio) {

        String sql =
        "UPDATE veiculos_venda SET titulo_anuncio=?, descricao=?, preco=?, cidade=?, estado=? WHERE id_venda=?";

        try {

            Connection con = conectar();

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, anuncio.getTituloAnuncio());
            pst.setString(2, anuncio.getDescricao());
            pst.setDouble(3, anuncio.getPreco());
            pst.setString(4, anuncio.getCidade());
            pst.setString(5, anuncio.getEstado());
            pst.setInt(6, anuncio.getIdVenda());

            pst.executeUpdate();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JavaBeans buscarAnuncio(int idVenda) {

        JavaBeans a = new JavaBeans();

        String sql = "SELECT * FROM veiculos_venda WHERE id_venda=?";

        try {

            Connection con = conectar();

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, idVenda);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                a.setIdVenda(rs.getInt("id_venda"));
                a.setTituloAnuncio(rs.getString("titulo_anuncio"));
                a.setDescricao(rs.getString("descricao"));
                a.setPreco(rs.getDouble("preco"));
                a.setCidade(rs.getString("cidade"));
                a.setEstado(rs.getString("estado"));
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return a;
    }
}
