/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sisadri.apilogin.helpers;

import com.sisadri.apilogin.model.Message;
import com.sisadri.apilogin.model.Token;
import com.sisadri.apilogin.service.LogueoService;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author pc
 */
public class Authentication {
    public static Message validarToken(HttpServletRequest request) {
        
        String token = request.getHeader("token");
        String idusuario = request.getHeader("idusuario");
        
        ServletContext servletContext = request.getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        LogueoService logueoService = webApplicationContext.getBean(LogueoService.class);
        
        Token item = new Token();
        item.setIdusuario(Format.toInt(idusuario));
        item.setToken(token);
        
        Message msg = logueoService.validarToken(item);
        
        return msg;
    }
}
