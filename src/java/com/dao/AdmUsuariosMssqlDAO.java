/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.mssql.AdmUsuarios;
import com.model.mssql.NewHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.apache.log4j.Logger;

/**
 *
 * @author Ricardo
 */
public class AdmUsuariosMssqlDAO {
    
    private Logger log  = Logger.getLogger(AdmUsuariosMssqlDAO.class);
    SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory("hibernate_server11_MSSQL.cfg.xml");
    
    public List<AdmUsuarios> getAll() {
        Session session = sessionFactory.openSession();
//        session.beginTransaction();
        List<AdmUsuarios> userTypeList = new ArrayList<AdmUsuarios>();
        
        try{                        
            Query query = session.createQuery("from AdmUsuarios order by nombre");
            userTypeList = query.list();
        }catch(Exception e){
//            session.getTransaction().rollback();
            System.out.println("Error AdmUsuariosDAO_getAll: "+e.getMessage());
            e.printStackTrace();            
        }finally{
            if(session != null){
                session.flush();
                session.close();
            }
        }
        
        
        return userTypeList;
    }
    
    
    public AdmUsuarios getUsuarioByLogin(String idUsuario){
        Session session = sessionFactory.openSession();
        AdmUsuarios user = new AdmUsuarios();
        System.out.println("== Entro al metodo getAccountByLogin");        
        
        try{
            String sql = "select a from AdmUsuarios a where a.idUsuario = :idUsuario";
            Query query = session.createQuery(sql);
            query.setParameter("idUsuario", idUsuario);
            user = (AdmUsuarios) query.uniqueResult();
            System.out.println("== Resultado: "+user.getNombre());
        }catch(Exception e){
            System.out.println("Error AdmUsuariosMssqlDAO_getAccountByLogin: "+e.getMessage());
            log.error("Error AdmUsuariosMssqlDAO_getAccountByLogin: "+e.getMessage());
            e.printStackTrace();
        }finally{
            if(session != null){
                session.flush();
                session.close();
            }
        }
        
        return user;
    }
    
    
    
}
