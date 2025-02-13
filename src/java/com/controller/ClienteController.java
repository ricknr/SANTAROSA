/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ClienteController {
   
 @RequestMapping("/cliente")  
 public ModelAndView cliente(HttpSession session, HttpServletRequest request, Authentication authentication){
 ModelAndView model = new ModelAndView();
     System.out.println("hasta aqui llego");
        model.setViewName("clientes");        
        return model;
    }
 
  @RequestMapping("/agregarCliente")  
 public ModelAndView agregarCliente(HttpSession session, HttpServletRequest request, Authentication authentication){
 ModelAndView model = new ModelAndView();
     System.out.println("hasta aqui llego");
        model.setViewName("AgregarCliente");        
        return model;
    }
 
  @RequestMapping("/contrato")  
 public ModelAndView contrato(HttpSession session, HttpServletRequest request, Authentication authentication){
 ModelAndView model = new ModelAndView();
     System.out.println("hasta aqui llego");
        model.setViewName("contrato");        
        return model;
    }
 
   @RequestMapping("/agregarContrato")  
 public ModelAndView agregarContrato(HttpSession session, HttpServletRequest request, Authentication authentication){
 ModelAndView model = new ModelAndView();
     System.out.println("hasta aqui llego");
        model.setViewName("agregarContrato");        
        return model;
    }
}