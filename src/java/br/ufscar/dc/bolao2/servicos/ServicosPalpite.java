package br.ufscar.dc.bolao2.servicos;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.ufscar.dc.bolao2.beans.Palpite;
import br.ufscar.dc.bolao2.dao.PalpiteDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *
 * @author Lucas
 */
@Stateless
@Path("palpite")
public class ServicosPalpite {


    @Inject
    PalpiteDAO palpiteDao;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPalpites() {
        simularDemora();
        try {
            return Response.ok(palpiteDao.listarTodosPalpites()).build();
        } catch (SQLException ex) {
            Logger.getLogger(ServicosPalpite.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/selecoes")
    public Response getSelecoes() {
        simularDemora();
        try {
            return Response.ok(palpiteDao.listarTodasSelecoes()).build();
        } catch (SQLException ex) {
            Logger.getLogger(ServicosPalpite.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/usuario")
    public Response getPalpitePorEmail(@QueryParam("email") String email) {
        simularDemora();
        try {
            return Response.ok(palpiteDao.listarPalpitesPorEmail(email)).build();
        } catch (SQLException ex) {
            Logger.getLogger(ServicosPalpite.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/quantidade")
    public Response getQuantidadePalpitePorEmail(@QueryParam("email") String email) {
        simularDemora();
        try{
            return Response.ok(palpiteDao.returnQuantidadePalpitesPorEmail(email)).build();
        } catch (SQLException ex) {
            Logger.getLogger(ServicosPalpite.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response gravarPalpite(Palpite p) {
        simularDemora();
        try {
            Palpite palpiteGravado = palpiteDao.gravarPalpite(p);

            return Response.ok(palpiteGravado).build();
        } catch (SQLException ex) {
            Logger.getLogger(ServicosPalpite.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
    }


    private void simularDemora() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ServicosPalpite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
