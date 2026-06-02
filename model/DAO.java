```java
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DAO {

    private String driver = "com.mysql.cj.jdbc.Driver";

    private String url =
            "jdbc:mysql://127.0.0.1:3306/autoloc"
            + "?useTimezone=true"
            + "&serverTimezone=UTC"
            + "&useSSL=false"
            + "&allowPublicKeyRetrieval=true";

    private String user = "root";
    private String password = "";

    // =========================================
    // CONEXÃO
    // =========================================

    private Connection conectar() {

        Connection con = null;

        try {

            Class.forName(driver);

            con = DriverManager.getConnection(
                    url,
                    user,
                    password
            );

            return con;

        } catch (Exception e) {

            System.out.println("ERRO AO CONECTAR NO MYSQL");
            e.printStackTrace();

            return null;
        }
    }

    // =========================================
    // TESTE CONEXÃO
    // =========================================

    public void testeConexao() {

        try {

            Connection con = conectar();

            System.out.println(con);

            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =========================================
    // CADASTRAR USUÁRIO
    // =========================================

    public int cadastrarUsuario(JavaBeans usuario) {

        int idGerado = 0;

        String sql =
                "INSERT INTO usuarios"
                        + "(nome,email,senha,telefone,cpf,tipo_usuario)"
                        + " VALUES(?,?,?,?,?,?)";

        try (

                Connection con = conectar();

                PreparedStatement pst =
                        con.prepareStatement(
                                sql,
                                Statement.RETURN_GENERATED_KEYS
                        );

        ) {

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

            rs.close();

        } catch (Exception e) {

            System.out.println("ERRO AO CADASTRAR USUARIO");
            e.printStackTrace();
        }

        return idGerado;
    }

    // =========================================
    // LOGIN GERAL
    // =========================================

    public JavaBeans loginGeral(JavaBeans login) {

        JavaBeans usuario = null;

        String sql =
                "SELECT "
                        + "u.id_usuario, "
                        + "u.nome, "
                        + "u.email, "
                        + "u.telefone, "
                        + "u.cpf, "
                        + "u.tipo_usuario, "
                        + "e.id_empresa "
                        + "FROM usuarios u "
                        + "LEFT JOIN empresas e "
                        + "ON u.id_usuario = e.id_usuario "
                        + "WHERE u.email=? "
                        + "AND u.senha=?";

        try (

                Connection con = conectar();

                PreparedStatement pst =
                        con.prepareStatement(sql);

        ) {

            pst.setString(1, login.getEmail());
            pst.setString(2, login.getSenha());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                usuario = new JavaBeans();

                usuario.setIdUsuario(
                        rs.getInt("id_usuario")
                );

                usuario.setNome(
                        rs.getString("nome")
                );

                usuario.setEmail(
                        rs.getString("email")
                );

                usuario.setNumeroTelefone(
                        rs.getString("telefone")
                );

                usuario.setCpf(
                        rs.getString("cpf")
                );

                usuario.setTipoUsuario(
                        rs.getString("tipo_usuario")
                );

                int idEmpresa =
                        rs.getInt("id_empresa");

                if (!rs.wasNull()) {

                    usuario.setIdEmpresa(idEmpresa);
                }
            }

            rs.close();

        } catch (Exception e) {

            System.out.println("ERRO LOGIN");
            e.printStackTrace();
        }

        return usuario;
    }

    // =========================================
    // CADASTRAR EMPRESA
    // =========================================

    public boolean cadastrarEmpresa(JavaBeans empresa) {

        String sql =
                "INSERT INTO empresas "
                        + "(id_usuario,nome_fantasia,razao_social,"
                        + "cnpj,descricao,telefone,email,categoria,"
                        + "cep,endereco,numero)"
                        + " VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        try (

                Connection con = conectar();

                PreparedStatement pst =
                        con.prepareStatement(sql);

        ) {

            pst.setInt(1, empresa.getIdUsuario());
            pst.setString(2, empresa.getNomeEmpresa());
            pst.setString(3, empresa.getRazaoSocial());
            pst.setString(4, empresa.getCnpj());
            pst.setString(5, empresa.getDescricao());
            pst.setString(6, empresa.getNumeroTelefone());
            pst.setString(7, empresa.getEmail());
            pst.setString(8, empresa.getCategoria());

            pst.setString(9, empresa.getCep());
            pst.setString(10, empresa.getEndereco());
            pst.setString(11, empresa.getNumero());

            int resultado = pst.executeUpdate();

            return resultado > 0;

        } catch (Exception e) {

            System.out.println("ERRO AO CADASTRAR EMPRESA");
            e.printStackTrace();

            return false;
        }
    }

    // =========================================
    // CADASTRAR VEÍCULO
    // =========================================

    public boolean cadastrarVeiculoVenda(JavaBeans veiculo) {

        String sql =
                "INSERT INTO veiculos_venda "
                        + "("
                        + "id_usuario,"
                        + "marca,"
                        + "modelo,"
                        + "ano,"
                        + "cor,"
                        + "placa,"
                        + "chassi,"
                        + "combustivel,"
                        + "foto,"
                        + "titulo_anuncio,"
                        + "descricao,"
                        + "preco,"
                        + "status_venda,"
                        + "km_rodados,"
                        + "cambio,"
                        + "portas,"
                        + "final_placa,"
                        + "cidade,"
                        + "estado,"
                        + "aceita_troca"
                        + ") "
                        + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (

                Connection con = conectar();

                PreparedStatement pst =
                        con.prepareStatement(sql);

        ) {

            pst.setInt(1, veiculo.getIdUsuario());
            pst.setString(2, veiculo.getMarca());
            pst.setString(3, veiculo.getModelo());
            pst.setInt(4, veiculo.getAno());
            pst.setString(5, veiculo.getCor());
            pst.setString(6, veiculo.getPlaca());
            pst.setString(7, veiculo.getChassi());
            pst.setString(8, veiculo.getCombustivel());
            pst.setString(9, veiculo.getImagem());

            pst.setString(10, veiculo.getTituloAnuncio());

            pst.setString(11, veiculo.getDescricao());

            pst.setDouble(12, veiculo.getPreco());

            pst.setString(13, veiculo.getStatus());

            pst.setInt(14, veiculo.getQuilometragem());

            pst.setString(15, veiculo.getCambio());

            pst.setInt(16, veiculo.getPortas());

            pst.setString(17, veiculo.getFinalPlaca());

            pst.setString(18, veiculo.getCidade());

            pst.setString(19, veiculo.getEstado());

            pst.setBoolean(20, veiculo.isAceitaTroca());

            int resultado = pst.executeUpdate();

            return resultado > 0;

        } catch (Exception e) {

            System.out.println("ERRO AO CADASTRAR VEICULO");
            e.printStackTrace();

            return false;
        }
    }

    // =========================================
    // LISTAR ANÚNCIOS
    // =========================================

    public ArrayList<JavaBeans> listarAnuncio(int pagina) {

        ArrayList<JavaBeans> lista =
                new ArrayList<>();

        int limite = 6;

        int offset =
                (pagina - 1) * limite;

        String sql =
                "SELECT * FROM veiculos_venda "
                        + "ORDER BY id_venda DESC "
                        + "LIMIT ? OFFSET ?";

        try (

                Connection con = conectar();

                PreparedStatement pst =
                        con.prepareStatement(sql);

        ) {

            pst.setInt(1, limite);
            pst.setInt(2, offset);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                JavaBeans v = new JavaBeans();

                v.setIdVenda(
                        rs.getInt("id_venda")
                );

                v.setMarca(
                        rs.getString("marca")
                );

                v.setModelo(
                        rs.getString("modelo")
                );

                v.setAno(
                        rs.getInt("ano")
                );

                v.setImagem(
                        rs.getString("foto")
                );

                v.setPreco(
                        rs.getDouble("preco")
                );

                v.setDescricao(
                        rs.getString("descricao")
                );

                v.setTituloAnuncio(
                        rs.getString("titulo_anuncio")
                );

                v.setCidade(
                        rs.getString("cidade")
                );

                v.setEstado(
                        rs.getString("estado")
                );

                v.setStatus(
                        rs.getString("status_venda")
                );

                lista.add(v);
            }

            rs.close();

        } catch (Exception e) {

            System.out.println("ERRO AO LISTAR ANUNCIOS");
            e.printStackTrace();
        }

        return lista;
    }

    // =========================================
    // TOTAL ANÚNCIOS
    // =========================================

    public int contarAnuncios() {

        int total = 0;

        String sql =
                "SELECT COUNT(*) FROM veiculos_venda";

        try (

                Connection con = conectar();

                PreparedStatement pst =
                        con.prepareStatement(sql);

        ) {

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                total = rs.getInt(1);
            }

            rs.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return total;
    }

    // =========================================
    // FAVORITOS
    // =========================================

    public boolean adicionarFavorito(
            int idUsuario,
            int idVenda
    ) {

        String sql =
                "INSERT INTO favoritos(id_usuario,id_venda)"
                        + " VALUES(?,?)";

        try (

                Connection con = conectar();

                PreparedStatement pst =
                        con.prepareStatement(sql);

        ) {

            pst.setInt(1, idUsuario);
            pst.setInt(2, idVenda);

            int resultado = pst.executeUpdate();

            return resultado > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================
    // DASHBOARD
    // =========================================

    public int totalVeiculosEmpresa(int idUsuario) {

        int total = 0;

        String sql =
                "SELECT COUNT(*) "
                        + "FROM veiculos_venda "
                        + "WHERE id_usuario=?";

        try (

                Connection con = conectar();

                PreparedStatement pst =
                        con.prepareStatement(sql);

        ) {

            pst.setInt(1, idUsuario);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                total = rs.getInt(1);
            }

            rs.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return total;
    }

    // =========================================
    // BUSCA
    // =========================================

    public ArrayList<JavaBeans> buscarVeiculos(
            String termo
    ) {

        ArrayList<JavaBeans> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM veiculos_venda "
                        + "WHERE marca LIKE ? "
                        + "OR modelo LIKE ?";

        try (

                Connection con = conectar();

                PreparedStatement pst =
                        con.prepareStatement(sql);

        ) {

            pst.setString(1, "%" + termo + "%");
            pst.setString(2, "%" + termo + "%");

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                JavaBeans v = new JavaBeans();

                v.setMarca(
                        rs.getString("marca")
                );

                v.setModelo(
                        rs.getString("modelo")
                );

                v.setPreco(
                        rs.getDouble("preco")
                );

                v.setImagem(
                        rs.getString("foto")
                );

                lista.add(v);
            }

            rs.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }
}
```
