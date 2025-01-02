/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sisadri.apilogin.repository;

import com.sisadri.apilogin.model.Menu;
import com.sisadri.apilogin.model.Token;
import com.sisadri.apilogin.model.Usuario;
import java.util.List;

/**
 *
 * @author pc
 */
public interface LogueoDao {
    String login(Usuario usuario);
    String validarToken(Token token);
    Token generarToken(Token token);
    List<Menu> obtenerAccesosPorToken(String token, String siglaMenu);
    List<Menu> getModulos(String token);
    List<Menu> getSubModulos(String token, Integer idsubmenu);
}
