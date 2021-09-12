package br.edu.ifpb.dac.atividade.jsf.repository;

import br.edu.ifpb.dac.atividade.jsf.entidades.CPF;
import java.sql.Connection;
import java.sql.PreparedStatement;

import br.edu.ifpb.dac.atividade.jsf.entidades.Integrante;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class IntegrantePersistencia {

    private Connection con;

    public IntegrantePersistencia() {
        // TODO Auto-generated constructor stub
    }

    public void AddIntegrante(Integrante i) {
        String SQL = "INSERT INTO integrante (nome, dataDeNascimento, CPF) VALUES (?,?,?)";
        con = Conexao.abrirConexao();
        try {
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setString(1, i.getNome());
            stm.setDate(2, Date.valueOf(i.getDataDeNascimento()));
            stm.setString(3, i.getCpf().formatado());

            stm.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public Integrante buscarPorId(int id) {
        String sql = "SELECT * FROM integrante WHERE id = "+ id ;
        return todas(sql).get(0);
    }

    public List<Integrante> todas() {
       
        return todas("SELECT * FROM integrante");
    }
    
    public void removeIntegrante(int id) {
        String SQL = "DELETE FROM integrante where id=?";
        con = Conexao.abrirConexao();
        try {
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setInt(1, id);

            stm.executeUpdate();
        } catch (SQLException e) {
          
        }
        Conexao.CloseConecxao(con);
    }
      public List<Integrante> localizarintegranteCPF(String cpf) {
        List<Integrante> integrante = new ArrayList<>();
        try {
            this.con = Conexao.abrirConexao();
            String consulta = "SELECT * FROM integrante WHERE cpf ilike '" + cpf + "%' ";
            PreparedStatement statement = con.prepareStatement(consulta);
            // statement.setString(1, cpf);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                integrante.add(criarIntegrante(result));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } finally {
            Conexao.CloseConecxao(con);
        }
        if (integrante.size() > 0) {
            return Collections.unmodifiableList(integrante);
        } else {
            return Collections.EMPTY_LIST;
        }
    }
    
    private List<Integrante> todas(String sql) {
        // TODO Auto-generated method stub
        con = Conexao.abrirConexao();
        List<Integrante> b = new ArrayList<>();
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resut = statement.executeQuery();
            while (resut.next()) {
                b.add(criarIntegrante(resut));
            }
        } catch (SQLException ex) {
            System.out.print(ex);
        } finally {
            Conexao.CloseConecxao(con);
        }
        if (b.size() > 0) {
            return Collections.unmodifiableList(b);
        } else {
            return Collections.EMPTY_LIST;
        }

    }

    private Integrante criarIntegrante(ResultSet result) {
        Integrante integrante = new Integrante();

        try {
            System.err.println("resut "+result.getString("nome"));

            integrante.setId(result.getInt("id"));
            integrante.setNome(result.getString("nome"));
            integrante.setDataDeNascimento(result.getDate("dataDeNascimento").toLocalDate());
            CPF cpf = new CPF(result.getString("cpf"));
            integrante.setCpf(cpf);

        } catch (SQLException ex) {
            System.out.print(ex);
        }

        return integrante;
    }

    public void atualizar(Integrante i) {

        String SQL = "UPDATE  integrante SET nome=?, dataDeNascimento=?, cpf=? WHERE id = ?";
        con = Conexao.abrirConexao();
        try {
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setString(1, i.getNome());
            stm.setDate(2, Date.valueOf(i.getDataDeNascimento()));
            stm.setString(3, i.getCpf().formatado());
            stm.setInt(4, i.getId());

            stm.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    

}
