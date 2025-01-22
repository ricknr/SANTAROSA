/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.AdmUsuariosMssqlDAO;
import com.model.mssql.AdmUsuarios;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 *
 * @author Ricardo
 */
@Controller
public class HomeController{
    
    private Logger log  = Logger.getLogger(HomeController.class);
    
    AdmUsuariosMssqlDAO usuariosMssqlDAO = new AdmUsuariosMssqlDAO();
    
    @RequestMapping(value = "entrar", method = RequestMethod.GET)  
    public ModelAndView login(HttpSession session, HttpServletRequest request, Authentication authentication){ //Metodo que se ejecuta antes de cargar el Login          
        ModelAndView model = new ModelAndView(); 
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AdmUsuarios user = new AdmUsuarios();
        String login    = null;        
        String usuarioNombre    = "";
//        List<AdmUsuarios> listUsuarios = new ArrayList<AdmUsuarios>();
//        listUsuarios = usuariosMssqlDAO.getAll();
//        System.out.println("usuarios: "+listUsuarios.size());      
                
        try{
            System.out.println("== SICAM - Cargando el Home ==");
            session = request.getSession(true); //Create the session
            login   = auth.getName();
            
            System.out.println("Login: "+auth.getName() + " | Roles: "+auth.getAuthorities().toString()); 
            System.out.println("UsuarioNombre: "+user.getNombre());
            
            user = usuariosMssqlDAO.getUsuarioByLogin(login);
            usuarioNombre   = user.getNombre();
            
            System.out.println("== Setteando datos en sesión [usuario: "+login+"] ==");
            session.setAttribute("login", login); //Set the login in session
            session.setAttribute("sNombreUsuario", usuarioNombre); //Set the name of user on session
            session.setAttribute("sRoles", authentication.getAuthorities()); //Set the roles from user
            
        }catch(Exception e){
            System.out.println("Error HomeController_login: "+e.getMessage());
            log.info("Error HomeController_login: "+e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("== Home Cargado ==");
        model.setViewName("home");        
        return model;
    }
    
    
}
