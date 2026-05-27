package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {

	private final String url = "jdbc:mysql://localhost:3306/autoloc?useTimezone=true&serverTimezone=UTC";
	private final String user = "root";
	private final String password = "123@senac";

	public Connection conectar() {

		Connection con = null;

		try {
			System.out.println("INICIANDO CONEXAO MYSQL");

			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/autoloc?useSSL=false&serverTimezone=UTC",
					"root", "123@senac");

			System.out.println("MYSQL CONECTADO");
		} catch (Exception e) {
			System.out.println("ERRO MYSQL");
			e.printStackTrace();
		}

		return con;
	}

	public int cadastrarUsuario(JavaBeans usuario) {

		int idGerado = 0;

		String sql = "INSERT INTO usuarios(nome,email,senha,telefone,cpf,tipo_usuario) VALUES(?,?,?,?,?,?)";

		try {
			Connection con = conectar();

			if (con == null) {
				System.out.println("ERRO: CONEXAO NULL AO CADASTRAR USUARIO");
				return 0;
			}

			PreparedStatement pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getEmail());
			pst.setString(3, usuario.getSenha());
			pst.setString(4, usuario.getNumeroTelefone());
			pst.setString(5, usuario.getCpf());
			pst.setString(6, usuario.getTipoUsuario());

			int linhas = pst.executeUpdate();

			System.out.println("LINHAS INSERIDAS EM USUARIOS: " + linhas);

			ResultSet rs = pst.getGeneratedKeys();

			if (rs.next()) {
				idGerado = rs.getInt(1);
			}

			rs.close();
			pst.close();
			con.close();

		} catch (Exception e) {
			System.out.println("ERRO AO CADASTRAR USUARIO NO MYSQL");
			System.out.println("MENSAGEM DO ERRO: " + e.getMessage());
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

			rs.close();
			pst.close();
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

			rs.close();
			pst.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	public boolean cadastrarEmpresa(JavaBeans empresa) {

		String sql = "INSERT INTO empresas "
				+ "(id_usuario, nome_fantasia, razao_social, cnpj, descricao, telefone, email, categoria) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			Connection con = conectar();

			if (con == null) {
				System.out.println("CONEXAO NULL AO CADASTRAR EMPRESA");
				return false;
			}

			if (empresa.getIdUsuario() <= 0) {
				System.out.println("ID USUARIO INVALIDO PARA CADASTRAR EMPRESA");
				return false;
			}

			PreparedStatement pst = con.prepareStatement(sql);

			pst.setInt(1, empresa.getIdUsuario());
			pst.setString(2, empresa.getNomeEmpresa());
			pst.setString(3, empresa.getRazaoSocial());
			pst.setString(4, empresa.getCnpj());
			pst.setString(5, empresa.getDescricao());
			pst.setString(6, empresa.getNumeroTelefone());
			pst.setString(7, empresa.getEmail());
			pst.setString(8, empresa.getCategoria());

			int resultado = pst.executeUpdate();

			pst.close();
			con.close();

			return resultado > 0;

		} catch (Exception e) {
			System.out.println("ERRO AO CADASTRAR EMPRESA");
			System.out.println("MENSAGEM DO ERRO: " + e.getMessage());
			e.printStackTrace();
			return false;
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

			rs.close();
			pst.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ok;
	}

	public void cadastrarCarro(JavaBeans carro) {

	    String sql = "INSERT INTO carros(id_usuario,marca,modelo,ano,cor,placa,combustivel,quilometragem,foto) VALUES(?,?,?,?,?,?,?,?,?)";

	    try {
	        Connection con = conectar();

	        if (con == null) {
	            System.out.println("CONEXAO NULL AO CADASTRAR CARRO");
	            return;
	        }

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

	        int linhas = pst.executeUpdate();

	        System.out.println("LINHAS INSERIDAS EM CARROS: " + linhas);

	        pst.close();
	        con.close();

	    } catch (Exception e) {
	        System.out.println("ERRO AO CADASTRAR CARRO");
	        System.out.println("MENSAGEM DO ERRO: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	
	public ArrayList<JavaBeans> listarCarrosPorUsuario(int idUsuario) {

	    ArrayList<JavaBeans> lista = new ArrayList<>();

	    String sql = "SELECT * FROM carros WHERE id_usuario = ? ORDER BY id_carro DESC";

	    try {
	        Connection con = conectar();

	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setInt(1, idUsuario);

	        ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	            JavaBeans c = new JavaBeans();

	            c.setIdCarro(rs.getInt("id_carro"));
	            c.setIdUsuario(rs.getInt("id_usuario"));
	            c.setMarca(rs.getString("marca"));
	            c.setModelo(rs.getString("modelo"));
	            c.setAno(rs.getInt("ano"));
	            c.setCor(rs.getString("cor"));
	            c.setPlaca(rs.getString("placa"));
	            c.setCombustivel(rs.getString("combustivel"));
	            c.setQuilometragem(rs.getInt("quilometragem"));
	            c.setImagem(rs.getString("foto"));

	            lista.add(c);
	        }

	        rs.close();
	        pst.close();
	        con.close();

	    } catch (Exception e) {
	        System.out.println("ERRO AO LISTAR CARROS DO USUARIO");
	        System.out.println("MENSAGEM DO ERRO: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return lista;
	}

	public void cadastrarServico(JavaBeans servico) {

		String sql = "INSERT INTO servicos(id_empresa,nome_servico,descricao,preco,tempo_estimado,status_servico) VALUES(?,?,?,?,?,?)";

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

			pst.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<JavaBeans> listarServicosPorEmpresa(int idEmpresa) {

		ArrayList<JavaBeans> lista = new ArrayList<>();

		String sql = "SELECT * FROM servicos WHERE id_empresa = ? ORDER BY id_servico DESC";

		try {
			Connection con = conectar();

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, idEmpresa);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				JavaBeans s = new JavaBeans();

				s.setIdEmpresa(rs.getInt("id_empresa"));
				s.setServico(rs.getString("nome_servico"));
				s.setDescricao(rs.getString("descricao"));
				s.setPreco(rs.getDouble("preco"));
				s.setPrazo(rs.getString("tempo_estimado"));
				s.setStatus(rs.getString("status_servico"));

				lista.add(s);
			}

			rs.close();
			pst.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	public void cadastrarProduto(JavaBeans produto) {

		String sql = "INSERT INTO produtos(id_empresa,nome_produto,categoria,descricao,preco,estoque,status_produto) VALUES(?,?,?,?,?,?,?)";

		try {
			Connection con = conectar();

			PreparedStatement pst = con.prepareStatement(sql);

			pst.setInt(1, produto.getIdEmpresa());
			pst.setString(2, produto.getNomeProduto());
			pst.setString(3, produto.getCategoria());
			pst.setString(4, produto.getDescricao());
			pst.setDouble(5, produto.getPreco());
			pst.setInt(6, produto.getEstoque());
			pst.setString(7, produto.getStatus());

			pst.executeUpdate();

			pst.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<JavaBeans> listarProdutosPorEmpresa(int idEmpresa) {

		ArrayList<JavaBeans> lista = new ArrayList<>();

		String sql = "SELECT * FROM produtos WHERE id_empresa = ? ORDER BY id_produto DESC";

		try {
			Connection con = conectar();

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, idEmpresa);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				JavaBeans p = new JavaBeans();

				p.setIdEmpresa(rs.getInt("id_empresa"));
				p.setNomeProduto(rs.getString("nome_produto"));
				p.setCategoria(rs.getString("categoria"));
				p.setDescricao(rs.getString("descricao"));
				p.setPreco(rs.getDouble("preco"));
				p.setEstoque(rs.getInt("estoque"));
				p.setStatus(rs.getString("status_produto"));

				lista.add(p);
			}

			rs.close();
			pst.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

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

			rs.close();
			pst.close();
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

			pst.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void editarAnuncio(JavaBeans anuncio) {

		String sql = "UPDATE veiculos_venda SET titulo_anuncio=?, descricao=?, preco=?, cidade=?, estado=? WHERE id_venda=?";

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

			pst.close();
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

			rs.close();
			pst.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return a;
	}

	public JavaBeans loginGeral(JavaBeans login) {

		JavaBeans usuario = null;

		String sql = "SELECT u.id_usuario, u.nome, u.email, u.telefone, u.cpf, u.tipo_usuario, e.id_empresa "
				+ "FROM usuarios u "
				+ "LEFT JOIN empresas e ON u.id_usuario = e.id_usuario "
				+ "WHERE u.email = ? "
				+ "AND u.senha = ? "
				+ "AND u.status_conta = 'ATIVO'";

		try {
			Connection con = conectar();

			if (con == null) {
				System.out.println("CONEXAO NULL AO FAZER LOGIN");
				return null;
			}

			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, login.getEmail());
			pst.setString(2, login.getSenha());

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				usuario = new JavaBeans();

				usuario.setIdUsuario(rs.getInt("id_usuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setTipoUsuario(rs.getString("tipo_usuario"));
				usuario.setNumeroTelefone(rs.getString("telefone"));
				usuario.setCpf(rs.getString("cpf"));

				int idEmpresa = rs.getInt("id_empresa");

				if (!rs.wasNull()) {
					usuario.setIdEmpresa(idEmpresa);
				}
			}

			rs.close();
			pst.close();
			con.close();

		} catch (Exception e) {
			System.out.println("ERRO AO FAZER LOGIN");
			System.out.println("MENSAGEM DO ERRO: " + e.getMessage());
			e.printStackTrace();
		}

		return usuario;
	}
}
