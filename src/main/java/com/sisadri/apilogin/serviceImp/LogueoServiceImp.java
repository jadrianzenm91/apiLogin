/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sisadri.apilogin.serviceImp;

import com.sisadri.apilogin.helpers.Constantes;
import com.sisadri.apilogin.helpers.Util;
import com.sisadri.apilogin.model.Menu;
import com.sisadri.apilogin.model.Message;
import com.sisadri.apilogin.model.Token;
import com.sisadri.apilogin.model.Usuario;
import com.sisadri.apilogin.repository.LogueoDao;
import com.sisadri.apilogin.repository.UsuarioDao;
import com.sisadri.apilogin.repositoryImp.LogueoDaoImpl;
import com.sisadri.apilogin.service.LogueoService;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pc
 */
@Service("logueoService")
public class LogueoServiceImp implements LogueoService {

    @Autowired
    LogueoDao data;
    @Autowired
    UsuarioDao usuarioDao;

    @Override
    public Message login(Usuario usuario, HttpServletRequest request) {
        Message msg = new Message();
        
        try{
            String resultado = data.login(usuario);
           
            msg.convert(resultado);
            
            if(msg.getSuccess()){//LOGIN OK
                usuario = usuarioDao.getUsuario(usuario);
                usuario.setIpTerminal(Util.getClientIp(request));
                //GENERAMOS TOKEN
                Token _token = new Token();
                _token.setIdusuario(usuario.getIdusuario());
                _token.setUsuario(usuario.getUsuario());
                _token.setIpTerminal(usuario.getIpTerminal());
                _token.setHostname(usuario.getHostname());
                _token = data.generarToken(_token);
                usuario.setToken(_token.getToken());
                usuario.setFechaexp(_token.getFechaexp());
                
                msg.setData(usuario);
                
            }else{
                msg.setType(Constantes.TypeAlerta.warning);
                msg.setSuccess(Boolean.FALSE);
                msg.setMessage(msg.getMessage());
            }
           
            
        }catch(Exception ex){
            Logger.getLogger(LogueoServiceImp.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            msg.setSuccess(Boolean.FALSE);
            msg.setType(Constantes.TypeAlerta.error);
            msg.setMessage(Constantes.Mensajes.ErrorSistema);
        }
        
        msg.setData(usuario);
        
        return msg;
    }

    @Override
    public Message validarToken(Token token) {
        Message msg = new Message();
        
        try{
            
                String resultado = data.validarToken(token);
                msg.convert(resultado);
                msg.setData(token);

                if(!msg.getSuccess()){//TOKEN INVALIDO

                    msg.setSuccess(Boolean.FALSE);
                }
            
           
            
        }catch(Exception ex){
            Logger.getLogger(LogueoServiceImp.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            msg.setSuccess(Boolean.FALSE);
            msg.setType(Constantes.TypeAlerta.error);
            msg.setMessage(Constantes.Mensajes.ErrorSistema);
        }
        
        return msg;
    }

    @Override
    public Message obtenerAccesosPorToken(String token, String siglaMenu) {
         Message msg = new Message();
        
        try{
            List<Menu> lstMenu = data.obtenerAccesosPorToken(token, siglaMenu);
            msg.setData(lstMenu);
            
            msg.setSuccess(Boolean.TRUE);
            msg.setType(Constantes.TypeAlerta.success);
            msg.setMessage(Constantes.Mensajes.MensajeConsultaExitosa);
           
            
        }catch(Exception ex){
            Logger.getLogger(LogueoServiceImp.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            msg.setSuccess(Boolean.FALSE);
            msg.setType(Constantes.TypeAlerta.error);
            msg.setMessage(Constantes.Mensajes.ErrorSistema);
        }
        
        return msg;
    }

    @Override
    public Message loginLDAP(Usuario usuario) {
       Message msg = new Message();
        Boolean authenticated = false;
        try{
            try {
                String principalName = "cooperacionix\\caoc_adviser";
               Hashtable<String, String> env = new Hashtable<String, String>();
                env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
                env.put(Context.PROVIDER_URL, "LDAP://cooperacionix.mil.pe/DC=cooperacionix,DC=mil,DC=pe");

                env.put(Context.SECURITY_AUTHENTICATION, "simple");
                env.put(Context.SECURITY_PRINCIPAL, principalName);
                env.put(Context.SECURITY_CREDENTIALS, usuario.getClave());
                InitialLdapContext ctx = new InitialLdapContext(env,null);
                authenticated = (ctx != null) ? true : false;
               
                
              

            }catch(NamingException ex){
                authenticated = false;
                msg.setSuccess(Boolean.FALSE);
                msg.setMessage(Constantes.Mensajes.MensajeAutenticacionLDAP_ERROR);
                msg.setException(ex.getMessage());
            }
            
            if(authenticated){//LOGIN OK
                
                usuario = usuarioDao.getUsuario(usuario);
                //GENERAMOS TOKEN
                Token _token = new Token();
                _token.setIdusuario(usuario.getIdusuario());
                _token.setUsucreacio(usuario.getUsuario());
                _token.setIpTerminal(usuario.getIpTerminal());
                _token.setHostname(usuario.getHostname());
                _token = data.generarToken(_token);
                usuario.setToken(_token.getToken());
                
                msg.setSuccess(Boolean.TRUE);
                msg.setType(Constantes.TypeAlerta.success);
                msg.setMessage(Constantes.Mensajes.MensajeConsultaExitosa);
                msg.setData(usuario);
                
            }
           
            
        }catch(Exception ex){
            msg.setException(ex.getMessage());
            msg.setSuccess(Boolean.FALSE);
            msg.setMessage(Constantes.Mensajes.ErrorSistema);
        }
        
        msg.setData(usuario);
        
        return msg;
    }


    @Override
    public Message getModulos(String token) {
         Message msg = new Message();
        
        try{
            List<Menu> lstMenu = data.getModulos(token);
            msg.setData(lstMenu);
            
            msg.setSuccess(Boolean.TRUE);
            msg.setType(Constantes.TypeAlerta.success);
            msg.setMessage(Constantes.Mensajes.MensajeConsultaExitosa);
           
            
        }catch(Exception ex){
            Logger.getLogger(LogueoServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            msg.setSuccess(Boolean.FALSE);
            msg.setType(Constantes.TypeAlerta.error);
            msg.setMessage(Constantes.Mensajes.ErrorSistema);
        }
        
        return msg;
    }

    @Override
    public Message getSubModulos(String token, Integer idsubmenu) {
         Message msg = new Message();
        
        try{
            List<Menu> lstMenu = data.getSubModulos(token, idsubmenu);
            msg.setData(lstMenu);
            
            msg.setSuccess(Boolean.TRUE);
            msg.setType(Constantes.TypeAlerta.success);
            msg.setMessage(Constantes.Mensajes.MensajeConsultaExitosa);
           
            
        }catch(Exception ex){
            Logger.getLogger(LogueoServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            msg.setSuccess(Boolean.FALSE);
            msg.setType(Constantes.TypeAlerta.error);
            msg.setMessage(Constantes.Mensajes.ErrorSistema);
        }
        
        return msg;
    }

   

}

