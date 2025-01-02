/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sisadri.apilogin.service;

import com.sisadri.apilogin.model.Message;
import com.sisadri.apilogin.model.Token;
import com.sisadri.apilogin.model.Usuario;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author pc
 */
public interface LogueoService {
   Message login(Usuario usuario, HttpServletRequest request);
   Message loginLDAP(Usuario usuario);
   Message validarToken(Token token);
   Message obtenerAccesosPorToken(String token, String siglaMenu);
   Message getModulos(String token);
   Message getSubModulos(String token, Integer idsubmenu);
}
