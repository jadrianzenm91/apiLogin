/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sisadri.apilogin.model;

import java.util.UUID;
import lombok.Data;

/**
 *
 * @author pc
 */
@Data
public class Usuario extends Base {
    private Integer idusuario;
    private String usuario;
    private String clave;
    private String token;
    private String fechaexp;
    private String correo;
    private String nombresCompletos;
    private String rol;
    private String uuid;
}
