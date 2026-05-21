package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {

    // =========================================
    // CONEXÃO
    // =========================================

    private final String url = "jdbc:mysql://localhost:3306/autoloc?useTimezone=true&serverTimezone=UTC";

    private final String user = "root";

    private final String password = "";

    public Connection conectar() {

        Connection conn = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {

            System.out.println(e);

        }

        return conn;

    }

    // =========================================
    // CADASTRAR USUÁRIO
    // =========================================

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

        System.out.println(e);

    }

    return idGerado;
}

    // =========================================
    // LOGIN USUÁRIO
    // =========================================

    public boolean loginUsuario(JavaBeans usuario) {

        String sql =
        "SELECT * FROM usuarios WHERE email=? AND senha=?";

        boolean acesso = false;

        try {

            Connection con = conectar();

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, usuario.getEmail());
            pst.setString(2, usuario.getSenha());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                acesso = true;

            }

            con.close();

        } catch (Exception e) {

            System.out.println(e);

        }

        return acesso;

    }

    // =========================================
    // LISTAR USUÁRIOS
    // =========================================

    public ArrayList<JavaBeans> listarUsuarios() {

        ArrayList<JavaBeans> lista = new ArrayList<>();

        String sql = "SELECT * FROM usuarios";

        try {

            Connection con = conectar();

            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                JavaBeans usuario = new JavaBeans();

                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setNumeroTelefone(rs.getString("telefone"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setTipoUsuario(rs.getString("tipo_usuario"));

                lista.add(usuario);

            }

            con.close();

        } catch (Exception e) {

            System.out.println(e);

        }

        return lista;

    }

    // =========================================
    // CADASTRAR EMPRESA
    // =========================================

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

            System.out.println(e);

        }

    }

    // =========================================
    // LOGIN EMPRESA
    // =========================================

    public boolean loginEmpresa(JavaBeans empresa) {

        String sql =
        "SELECT * FROM empresas WHERE email=? AND cnpj=?";

        boolean acesso = false;

        try {

            Connection con = conectar();

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, empresa.getEmail());
            pst.setString(2, empresa.getCnpj());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                acesso = true;

            }

            con.close();

        } catch (Exception e) {

            System.out.println(e);

        }

        return acesso;

    }

    // =========================================
    // CADASTRAR CARRO
    // =========================================

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

            System.out.println(e);

        }

    }

    // =========================================
    // CADASTRAR SERVIÇO
    // =========================================

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

            System.out.println(e);

        }

    }

    // =========================================
    // CRIAR ANÚNCIO
    // =========================================

    public void criarAnuncio(JavaBeans anuncio) {

        String sql =
        "INSERT INTO veiculos_venda(id_usuario,id_carro,titulo_anuncio,descricao,preco,km_rodados,cambio,portas,final_placa,cidade,estado,aceita_troca) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

        try {

            Connection con = conectar();

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, anuncio.getIdUsuario());
            pst.setInt(2, anuncio.getIdCarro());
            pst.setString(3, anuncio.getTituloAnuncio());
            pst.setString(4, anuncio.getDescricao());
            pst.setDouble(5, anuncio.getPreco());
            pst.setInt(6, anuncio.getQuilometragem());
            pst.setString(7, anuncio.getCambio());
            pst.setString(8, anuncio.getPorta());
            pst.setString(9, anuncio.getFinalPlaca());
            pst.setString(10, anuncio.getCidade());
            pst.setString(11, anuncio.getEstado());
            pst.setBoolean(12, anuncio.isAceitaTroca());

            pst.executeUpdate();

            con.close();

        } catch (Exception e) {

            System.out.println(e);

        }

    }

    // =========================================
    // LISTAR ANÚNCIOS
    // =========================================

    public ArrayList<JavaBeans> listarAnuncios() {

        ArrayList<JavaBeans> lista = new ArrayList<>();

        String sql = "SELECT * FROM veiculos_venda";

        try {

            Connection con = conectar();

            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                JavaBeans anuncio = new JavaBeans();

                anuncio.setIdVenda(rs.getInt("id_venda"));
                anuncio.setIdUsuario(rs.getInt("id_usuario"));
                anuncio.setIdCarro(rs.getInt("id_carro"));
                anuncio.setTituloAnuncio(rs.getString("titulo_anuncio"));
                anuncio.setDescricao(rs.getString("descricao"));
                anuncio.setPreco(rs.getDouble("preco"));
                anuncio.setQuilometragem(rs.getInt("km_rodados"));
                anuncio.setCambio(rs.getString("cambio"));
                anuncio.setPorta(rs.getString("portas"));
                anuncio.setFinalPlaca(rs.getString("final_placa"));
                anuncio.setCidade(rs.getString("cidade"));
                anuncio.setEstado(rs.getString("estado"));

                lista.add(anuncio);

            }

            con.close();

        } catch (Exception e) {

            System.out.println(e);

        }

        return lista;

    }

    // =========================================
    // DELETAR ANÚNCIO
    // =========================================

    public void deletarAnuncio(JavaBeans anuncio) {

        String sql =
        "DELETE FROM veiculos_venda WHERE id_venda=?";

        try {

            Connection con = conectar();

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, anuncio.getIdVenda());

            pst.executeUpdate();

            con.close();

        } catch (Exception e) {

            System.out.println(e);

        }

    }


    //=========================================
    //EDITAR ANÚNCIO
    //=========================================

    public void editarAnuncio(JavaBeans anuncio) {

    	String sql ="UPDATE veiculos_venda SET titulo_anuncio=?, descricao=?, preco=?, cidade=?, estado=? WHERE id_venda=?";

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
	// =========================================
	// ATUALIZAR ANÚNCIO
	// =========================================

	public void atualizarAnuncio(JavaBeans anuncio) {

  	  String sql = "UPDATE veiculos_venda SET titulo_anuncio=?, descricao=?, preco=?, cidade=?, estado=? WHERE id_venda=?";

   		 try {

      		  Connection con = conectar();

      		  PreparedStatement pst = con.prepareStatement(sql);

       			pst.setString(1, anuncio.getTituloAnuncio());
       			pst.setString(2, anuncio.getDescricao());
       			pst.setDouble(3, anuncio.getPreco());
       			pst.setString(4, anuncio.getCidade();
			 	pst.setString(5, anuncio.getEstado());
       			pst.setInt(6, anuncio.getIdVenda());

        		pst.executeUpdate();

       	 con.close();

    	} catch (Exception e) {

       		 System.out.println(e);

    }
}
}
