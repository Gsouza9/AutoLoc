package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/autoloc?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "";

	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void testeConexao() {
		try {
			Connection con = conectar();
			System.out.println(con);
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
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
				+ "(id_usuario, nome_fantasia, razao_social, cnpj, descricao, telefone, email, categoria, cep) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
			pst.setString(9, empresa.getCep());

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

	public boolean cadastrarVeiculoVenda(JavaBeans veiculo) {

		String sql = "INSERT INTO veiculos_venda "
				+ "(id_usuario, marca, modelo, ano, cor, placa, chassi, combustivel, foto, "
				+ "titulo_anuncio, descricao, preco, status_venda, km_rodados, cambio, portas, "
				+ "final_placa, cidade, estado, aceita_troca) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			Connection con = conectar();

			if (con == null) {
				System.out.println("CONEXAO NULL AO CADASTRAR VEICULO VENDA");
				return false;
			}

			PreparedStatement pst = con.prepareStatement(sql);

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

			pst.close();
			con.close();

			return resultado > 0;

		} catch (Exception e) {
			System.out.println("ERRO AO CADASTRAR VEICULO VENDA");
			System.out.println("MENSAGEM DO ERRO: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<JavaBeans> listarVeiculosVendaPorUsuario(int idUsuario) {

		ArrayList<JavaBeans> lista = new ArrayList<>();

		String sql = "SELECT * FROM veiculos_venda WHERE id_usuario = ? ORDER BY id_venda DESC";

		try {
			Connection con = conectar();

			if (con == null) {
				System.out.println("CONEXAO NULL AO LISTAR VEICULOS");
				return lista;
			}

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, idUsuario);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				JavaBeans v = new JavaBeans();

				v.setIdVenda(rs.getInt("id_venda"));
				v.setIdUsuario(rs.getInt("id_usuario"));
				v.setMarca(rs.getString("marca"));
				v.setModelo(rs.getString("modelo"));
				v.setAno(rs.getInt("ano"));
				v.setCor(rs.getString("cor"));
				v.setPlaca(rs.getString("placa"));
				v.setChassi(rs.getString("chassi"));
				v.setCombustivel(rs.getString("combustivel"));
				v.setImagem(rs.getString("foto"));
				v.setTituloAnuncio(rs.getString("titulo_anuncio"));
				v.setDescricao(rs.getString("descricao"));
				v.setPreco(rs.getDouble("preco"));
				v.setStatus(rs.getString("status_venda"));
				v.setQuilometragem(rs.getInt("km_rodados"));
				v.setCambio(rs.getString("cambio"));
				v.setPortas(rs.getInt("portas"));
				v.setFinalPlaca(rs.getString("final_placa"));
				v.setCidade(rs.getString("cidade"));
				v.setEstado(rs.getString("estado"));
				v.setAceitaTroca(rs.getBoolean("aceita_troca"));

				lista.add(v);
			}

			rs.close();
			pst.close();
			con.close();

		} catch (Exception e) {
			System.out.println("ERRO AO LISTAR VEICULOS VENDA POR USUARIO");
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

	public ArrayList<JavaBeans> listarAnuncio() {
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
		            a.setMarca(rs.getString("marca"));
		            a.setModelo(rs.getString("modelo"));
		            a.setAno(rs.getInt("ano"));
		            a.setCombustivel(rs.getString("combustivel"));
		            a.setImagem(rs.getString("foto")); 
		            a.setTituloAnuncio(rs.getString("titulo_anuncio"));
		            a.setDescricao(rs.getString("descricao"));
		            a.setPreco(rs.getDouble("preco"));
		            a.setQuilometragem(rs.getInt("km_rodados"));
		            a.setCambio(rs.getString("cambio"));
		            a.setPortas(rs.getInt("portas"));
		            a.setFinalPlaca(rs.getString("final_placa"));
		            a.setCidade(rs.getString("cidade"));
		            a.setEstado(rs.getString("estado"));
		            a.setStatus(rs.getString("status_venda"));

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
	
	// COM paginação - para a página de veículos
	public ArrayList<JavaBeans> listarAnuncio(int pagina) {
	    ArrayList<JavaBeans> lista = new ArrayList<>();
	    int limite = 6;
	    int offset = (pagina - 1) * limite;
	    String sql = "SELECT * FROM veiculos_venda ORDER BY id_venda DESC LIMIT ? OFFSET ?";
	    try {
	        Connection con = conectar();
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setInt(1, limite);
	        pst.setInt(2, offset);
	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	            JavaBeans a = new JavaBeans();
	            a.setIdVenda(rs.getInt("id_venda"));
	            a.setIdUsuario(rs.getInt("id_usuario"));
	            a.setMarca(rs.getString("marca"));
	            a.setModelo(rs.getString("modelo"));
	            a.setAno(rs.getInt("ano"));
	            a.setCombustivel(rs.getString("combustivel"));
	            a.setImagem(rs.getString("foto"));
	            a.setTituloAnuncio(rs.getString("titulo_anuncio"));
	            a.setDescricao(rs.getString("descricao"));
	            a.setPreco(rs.getDouble("preco"));
	            a.setQuilometragem(rs.getInt("km_rodados"));
	            a.setCambio(rs.getString("cambio"));
	            a.setPortas(rs.getInt("portas"));
	            a.setCidade(rs.getString("cidade"));
	            a.setEstado(rs.getString("estado"));
	            a.setStatus(rs.getString("status_venda"));
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
	
	public int contarAnuncios() {
	    int total = 0;
	    String sql = "SELECT COUNT(*) FROM veiculos_venda";
	    try {
	        Connection con = conectar();
	        PreparedStatement pst = con.prepareStatement(sql);
	        ResultSet rs = pst.executeQuery();
	        if (rs.next()) total = rs.getInt(1);
	        rs.close();
	        pst.close();
	        con.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return total;
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
	
	public JavaBeans buscarEmpresaPorId(int idEmpresa) {

		JavaBeans loja = null;

		String sql = "SELECT * FROM empresas WHERE id_empresa = ?";

		try {
			Connection con = conectar();

			if (con == null) {
				System.out.println("CONEXAO NULL AO BUSCAR EMPRESA");
				return null;
			}

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, idEmpresa);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				loja = new JavaBeans();

				loja.setIdEmpresa(rs.getInt("id_empresa"));
				loja.setIdUsuario(rs.getInt("id_usuario"));
				loja.setNomeEmpresa(rs.getString("nome_fantasia"));
				loja.setRazaoSocial(rs.getString("razao_social"));
				loja.setCnpj(rs.getString("cnpj"));
				loja.setDescricao(rs.getString("descricao"));
				loja.setNumeroTelefone(rs.getString("telefone"));
				loja.setEmail(rs.getString("email"));
				loja.setCategoria(rs.getString("categoria"));
				loja.setEndereco(rs.getString("endereco"));
				loja.setHorario(rs.getString("horario"));
				loja.setCep(rs.getString("cep"));
			}

			rs.close();
			pst.close();
			con.close();

		} catch (Exception e) {
			System.out.println("ERRO AO BUSCAR EMPRESA");
			System.out.println("MENSAGEM DO ERRO: " + e.getMessage());
			e.printStackTrace();
		}

		return loja;
	}

	public boolean atualizarDadosLoja(JavaBeans loja) {

		String sql = "UPDATE empresas SET telefone = ?, endereco = ?, horario = ? WHERE id_empresa = ?, cep = ?";

		try {
			Connection con = conectar();

			if (con == null) {
				System.out.println("CONEXAO NULL AO ATUALIZAR LOJA");
				return false;
			}

			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, loja.getNumeroTelefone());
			pst.setString(2, loja.getEndereco());
			pst.setString(3, loja.getHorario());
			pst.setInt(4, loja.getIdEmpresa());
			pst.setString(5, loja.getCep());

			int resultado = pst.executeUpdate();

			pst.close();
			con.close();

			return resultado > 0;

		} catch (Exception e) {
			System.out.println("ERRO AO ATUALIZAR DADOS DA LOJA");
			System.out.println("MENSAGEM DO ERRO: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean atualizarDadosVeiculoVenda(JavaBeans veiculo) {

		String sql = "UPDATE veiculos_venda SET status_venda = ?, km_rodados = ?, cidade = ? WHERE id_venda = ?";

		try {
			Connection con = conectar();

			if (con == null) {
				System.out.println("CONEXAO NULL AO ATUALIZAR VEICULO VENDA");
				return false;
			}

			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, veiculo.getStatus());
			pst.setInt(2, veiculo.getQuilometragem());
			pst.setString(3, veiculo.getCidade());
			pst.setInt(4, veiculo.getIdVenda());

			int resultado = pst.executeUpdate();

			pst.close();
			con.close();

			return resultado > 0;

		} catch (Exception e) {
			System.out.println("ERRO AO ATUALIZAR DADOS DO VEICULO VENDA");
			System.out.println("MENSAGEM DO ERRO: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	//--------------------------------------------------------------------------------------------------
	public int contarVeiculosPorUsuario(int idUsuario) {
	    int total = 0;
	    String sql = "SELECT COUNT(*) FROM veiculos_venda WHERE id_usuario = ?";
	    try {
	        Connection con = conectar();
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setInt(1, idUsuario);
	        ResultSet rs = pst.executeQuery();
	        if (rs.next()) total = rs.getInt(1);
	        rs.close(); pst.close(); con.close();
	    } catch (Exception e) { e.printStackTrace(); }
	    return total;
	}

	public int contarServicosPorEmpresa(int idEmpresa) {
	    int total = 0;
	    String sql = "SELECT COUNT(*) FROM servicos WHERE id_empresa = ?";
	    try {
	        Connection con = conectar();
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setInt(1, idEmpresa);
	        ResultSet rs = pst.executeQuery();
	        if (rs.next()) total = rs.getInt(1);
	        rs.close(); pst.close(); con.close();
	    } catch (Exception e) { e.printStackTrace(); }
	    return total;
	}

	public int contarProdutosPorEmpresa(int idEmpresa) {
	    int total = 0;
	    String sql = "SELECT COUNT(*) FROM produtos WHERE id_empresa = ?";
	    try {
	        Connection con = conectar();
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setInt(1, idEmpresa);
	        ResultSet rs = pst.executeQuery();
	        if (rs.next()) total = rs.getInt(1);
	        rs.close(); pst.close(); con.close();
	    } catch (Exception e) { e.printStackTrace(); }
	    return total;
	}
}
