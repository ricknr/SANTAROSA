package com.util;



import com.model.mssql.NewHibernateUtil;
import com.model.mssql.AdmUsuarios;
import com.dao.AdmUsuariosMssqlDAO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.*;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    
    @Autowired
    AdmUsuariosMssqlDAO admUsuariosMssqlDAO = new AdmUsuariosMssqlDAO();
//    AdminConsoleUserRolesDAO adminConsoleUserRolesDao = new AdminConsoleUserRolesDAO();    
    private Logger log  = Logger.getLogger(CustomAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {        
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        AdmUsuarios user = new AdmUsuarios();
        System.out.println("authenticate method. Username: "+username+" | password: "+password);
            
        try{
            System.out.println("Before method accountDAO.getAccountByLogin(username)");            
            user = admUsuariosMssqlDAO.getUsuarioByLogin(username);

            if (user == null) {
                System.out.println("Username not found");
                throw new BadCredentialsException("Username not found.");
            }
                        
            System.out.println("Password created: "+com.util.Tools.getMD5(password.toLowerCase())+" | DB Password:"+user.getPassword().toString().toLowerCase());

            if(!com.util.Tools.getMD5(password.toLowerCase()).equals(user.getPassword().toString().toLowerCase())){
                throw new BadCredentialsException("Wrong password.");
            }

            List<GrantedAuthority> grantedAuths = new ArrayList<>();
//            List<String> permisos = new ArrayList<String>(Arrays.asList(adminConsoleUserRolesDao.getAuthorities(username).split(",")));
//            for(int i=0; i<permisos.size(); i++){            
//                grantedAuths.add(new GrantedAuthorityImpl(permisos.get(i)));
                grantedAuths.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
//            }

            return new UsernamePasswordAuthenticationToken(authentication.getName(), password, grantedAuths);
        }catch(Exception e){
            log.error("Error al autenticar el usuario: "+e.getMessage());
            System.out.println("Error al autenticar el usuario: "+e.getMessage());
            e.printStackTrace();
            throw new BadCredentialsException("Connection to database failed..");
        }
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }
    
}
