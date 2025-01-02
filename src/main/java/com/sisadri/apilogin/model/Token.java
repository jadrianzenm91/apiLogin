/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sisadri.apilogin.model;

import lombok.Data;

/**
 *
 * @author pc
 */
@Data
public class Token extends Base {
    private int idtoken;
    private int idusuario;
    private String token;
    private String usuario;
    private String fechaexp;
}
