package com.seuusuario.app.dao;

import com.seuusuario.app.model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public List<Usuario> listar() throws SQLException {
        String sql = "SELECT * FROM public.usuarios ORDER BY codigo";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            List<Usuario> resultados = new ArrayList<>();
            while (rs.next()) {
                Usuario u = new Usuario(
                    rs.getInt("codigo"),
                    rs.getString("login"),
                    rs.getString("senha"),
                    rs.getString("sexo").charAt(0)
                );
                resultados.add(u);
            }
            return resultados;
        }
    }
    public int getNextCodigo() throws SQLException {
    	  String seq = "SELECT COALESCE(MAX(codigo), 0) + 1 FROM public.usuarios";
    	  try (Connection c = ConnectionFactory.getConnection();
    	       Statement s = c.createStatement();
    	       ResultSet rs = s.executeQuery(seq)) {
    	    rs.next();
    	    return rs.getInt(1);
    	  }
    	}


    public void inserir(Usuario u) throws SQLException {
    	  int novoCodigo = getNextCodigo();
    	  String sql = "INSERT INTO public.usuarios(codigo, login, senha, sexo) VALUES(?, ?, ?, ?)";
    	  try (Connection c = ConnectionFactory.getConnection();
    	       PreparedStatement ps = c.prepareStatement(sql)) {
    	    ps.setInt(1, novoCodigo);
    	    ps.setString(2, u.getLogin());
    	    ps.setString(3, u.getSenha());
    	    ps.setString(4, String.valueOf(u.getSexo()));
    	    ps.executeUpdate();
    	  }
    	}


    public void excluir(int codigo) throws SQLException {
        String sql = "DELETE FROM public.usuarios WHERE codigo = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, codigo);
            ps.executeUpdate();
        }
    }

    public void atualizar(Usuario u) throws SQLException {
        String sql = "UPDATE public.usuarios SET login = ?, senha = ?, sexo = ? WHERE codigo = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, u.getLogin());
            ps.setString(2, u.getSenha());
            ps.setString(3, String.valueOf(u.getSexo()));
            ps.setInt(4, u.getCodigo());
            ps.executeUpdate();
        }
    }
}
