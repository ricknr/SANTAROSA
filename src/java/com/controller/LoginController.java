/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.formBeans.LoginFormBean;
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

/**
 *
 * @author Ricardo
 */
@Controller
public class LoginController extends HttpServlet{
    
    private Logger log  = Logger.getLogger(LoginController.class);
    
    @RequestMapping(value = "/")
    public ModelAndView login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout){ //Metodo que se ejecuta antes de cargar el Login          
        ModelAndView model = new ModelAndView(); 
//        log.info("== Login Cargado ==");        
        System.out.println("Login Cargado");
        
        if (error != null) {
            model.addObject("error", "Usuario o contraseña inválidos!");            
            log.info("== Error:  Invalid username and password ==");
            System.out.println("Error:  Invalid username and password");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
            log.info("==  Msg:  You've been logged out successfully. ==");        
            System.out.println("==  Msg:  You've been logged out successfully. ==");
        }
        
        model.setViewName("login");        
        return model;
    }
    
    @RequestMapping(value = "validar", method = RequestMethod.POST)
    @ResponseBody
    public String validar(LoginFormBean loginFormBean, HttpServletRequest request, HttpServletResponse response, HttpSession session){ //Validar usuario 
        String result   = null;
        String login    = null;
        String pass     = null;
        
        try{
            session = request.getSession(true); //Create the session
            login       = loginFormBean.getUsername();
            pass        = loginFormBean.getPassword();
            
            System.out.println("login: "+login);
            System.out.println("pass: "+pass);
            
            
            result = "Success";
            
        }catch(Exception e){
            System.out.println("Error LoginController_validar: "+e.getMessage());
            e.printStackTrace();
            result = "error";
        }
        
        return result;
    }
    
    
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session, HttpServletRequest request, HttpServletResponse response){ //Metodo que se ejecuta antes de cargar el Login        
        ModelAndView model = new ModelAndView();            
        
        try{            
            session.invalidate();
            
            model.setViewName("login");
            System.out.println("Session destroyed.");
//            log.info("Session destroyed.");
        }catch(Exception e){
            System.out.println("Error LoginController_logout: "+e.getMessage());
//            log.info("Error LoginController_logout: "+e.getMessage());
            e.printStackTrace();
        }
        
        return model;
    }
    
    
    
}
