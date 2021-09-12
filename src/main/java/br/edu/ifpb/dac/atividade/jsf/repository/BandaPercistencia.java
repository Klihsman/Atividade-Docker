package br.edu.ifpb.dac.atividade.jsf.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.ifpb.dac.atividade.jsf.entidades.Banda;

public class BandaPercistencia {

    private Connection con;

    public BandaPercistencia() {

    }

    public void addBanda(Banda b) {
        b.setId((int) System.currentTimeMillis());
        String SQL = "INSERT INTO banda (localdeorigem,nomefantasia, id) VALUES (?,?,?)";
        con = Conexao.abrirConexao();
        try {
            PreparedStatement stm = con.prepareStatement(SQL);

            stm.setString(1, b.getLocalDeOrigem());
            stm.setString(2, b.getNomeFantasia());
            stm.setInt(3, b.getId());

            if (stm.executeUpdate() > 0) {
               
                bandaIntegrante(b.getId(),
                        b.getIntegrantes().get(0).getId(),
                        con);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
    }

    public List<Banda> todas() {

        con = Conexao.abrirConexao();
        List<Banda> b = new ArrayList<>();
        try {
            String sql = "SELECT * FROM banda";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resut = statement.executeQuery();
            while (resut.next()) {
                b.add(criarBanda(resut));
            }
        } catch (SQLException ex) {
            System.out.print(ex);
        } finally {
            Conexao.CloseConecxao(con);
        }
        if (b.size() > 0) {
            System.err.println("er " + b.get(0));
            return Collections.unmodifiableList(b);
        } else {
            return Collections.EMPTY_LIST;
        }

    }

    private Banda criarBanda(ResultSet result) {
        Banda b = new Banda();

        try {

            b.setId(result.getInt("id"));
            b.setNomeFantasia(result.getString("nomeFantasia"));
            b.setLocalDeOrigem(result.getString("localdeorigem"));

        } catch (SQLException ex) {
            System.out.print(ex);
        }

        return b;
    }

    public void removeBanda(int id) {
        String SQL = "DELETE FROM banda where id=?";
        con = Conexao.abrirConexao();
        try {
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setInt(1, id);

            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("erro" + e.getMessage());
        }
        Conexao.CloseConecxao(con);
    }

    public void atualizar(Banda b) {

        String SQL = "UPDATE banda  SET localdeorigem=?,nomefantasia=? WHERE id = ?";
        con = Conexao.abrirConexao();
        try {
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setString(1, b.getLocalDeOrigem());
            stm.setString(2, b.getNomeFantasia());
            stm.setInt(3, b.getId());

            stm.executeUpdate();
        } catch (SQLException e) {
            System.err.println("eroor at " + e.getMessage());

        }
    }

    public List<Banda> localizarLocalDeOrigem(String origem) {
        List<Banda> bandas = new ArrayList<>();
        try {
            this.con = Conexao.abrirConexao();
            String consulta = "SELECT * FROM banda WHERE localdeorigem ilike '" + origem + "%' ";
            PreparedStatement statement = con.prepareStatement(consulta);
            // statement.setString(1, cpf);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                bandas.add(criarBanda(result));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } finally {
            Conexao.CloseConecxao(con);
        }
        if (bandas.size() > 0) {
            return Collections.unmodifiableList(bandas);
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    private void bandaIntegrante(int id_banda, int id_integrante, Connection con) {
        String SQL = "INSERT INTO integrante_banda (id_banda,id_integrante) VALUES(?,?)";
        con = Conexao.abrirConexao();
        try {
            System.err.println("int salve " + id_banda + id_integrante);
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setInt(1, id_banda);
            stm.setInt(2, id_integrante);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }

    }
}
