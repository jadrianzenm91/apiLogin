/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sisadri.apilogin.controller;

import com.sisadri.apilogin.helpers.Authentication;
import com.sisadri.apilogin.helpers.Format;
import com.sisadri.apilogin.helpers.Util;
import com.sisadri.apilogin.model.Message;
import com.sisadri.apilogin.model.Token;
import com.sisadri.apilogin.model.Usuario;
import com.sisadri.apilogin.service.LogueoService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/auth")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class loginController {
    
    @Autowired
    LogueoService service;
   
    @PostMapping("/login") 
    public Message login(HttpServletRequest request,
            @RequestParam("usuario") String usuario,
            @RequestParam("password") String password) {
        
        Message msj;
        
        Usuario user = new Usuario();
        user.setUsuario(usuario);
        user.setClave(password);
        msj = service.login(user, request);
      
        return msj;
    }
    
    @PostMapping("/getModulos") 
    public Message getModulos(HttpServletRequest request) {
        
        Message msj = Authentication.validarToken(request);
        
        if(msj.getSuccess()){
            String token = request.getHeader("token");
            msj = service.getModulos(token);
        }
        
        return msj;
    }
    
    @PostMapping("/getSubModulos") 
    public Message getSubModulos(HttpServletRequest request, @RequestParam("idSubMenu") Integer idSubMenu) {
        
        Message msj = Authentication.validarToken(request);
        
        if(msj.getSuccess()){
            String token = request.getHeader("token");
            msj = service.getSubModulos(token, idSubMenu);
        }
        
        return msj;
    }
    
    @PostMapping("/obtenerAccesosPorToken")
    public Message obtenerAccesosPorToken(HttpServletRequest request, @RequestParam("siglaMenu")String siglaMenu) {
        
        Message msj = Authentication.validarToken(request);
        
        if(msj.getSuccess()){
            String token = request.getHeader("token");
            msj = service.obtenerAccesosPorToken(token, siglaMenu);
        }
        
        return msj;
    }
    @PostMapping("/validarToken")
    public Message validarToken(HttpServletRequest request) {
        Message msj;
        
        Token item = new Token();
        item.setIdusuario(Format.toInt(request.getHeader("idusuario")));
        item.setToken(request.getHeader("token"));
        item.setIpTerminal(Util.getClientIp(request));
        msj = service.validarToken(item);
                
        return msj;
    }
}
