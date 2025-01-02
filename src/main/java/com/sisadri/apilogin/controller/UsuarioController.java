/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sisadri.apilogin.controller;

import com.sisadri.apilogin.model.Message;
import com.sisadri.apilogin.model.Usuario;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pc
 */
@RestController
@RequestMapping(path = "api/v1/usuario")
public class UsuarioController {
    
    @PostMapping() 
    public Message prueba(@RequestParam("usuario") String usuario,
            @RequestParam("password") String password) {
        
        Message msj = new Message();
        msj.setSuccess(Boolean.TRUE);
      
        return msj;
    }
}
