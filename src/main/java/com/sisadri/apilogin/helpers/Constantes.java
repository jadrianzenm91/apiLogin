/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sisadri.apilogin.helpers;

/**
 *
 * @author pc
 */
public class Constantes {

    public static class App {
        public final static String sistema = "SERVICIO WEB REST API PARA LOGIN DE USUARIOS"; 
        public final static String sigla = "APILOGIN";
        public final static String version = "v0.1";
    } 
    
    public static class BDContext {

        public final static String Schema = "SICS";
        public final static String PKG_LOGIN = "UPKG_APILOGIN";
        public final static String PKG_USUARIO = "UPKG_USUARIO";
        public final static String PKG_ADMINISTRACION = "UPKG_ADMINISTRACION";

    }
    public static class Parametros {
         
        
    }
    public static class Todos {

        public final static int value = 0;
        public final static String text = "Todos";

    }
    
    public static class TypeAlerta {

        public final static String success = "success";
        public final static String error = "danger";
        public final static String warning = "warning";
        

    }

    public static class Mensajes {

        public final static String typeValidacion = "warning";
        public final static String typeExcepcion = "danger";
        public final static String typeExitoso = "success";
        public final static String ErrorSistema = "Error del Sistema. Comunicarse con el Administrador.";
        public final static String MensajeErrorExcepcion = "Ocurrió un error en el Sistema.";
        public final static String MensajeErrorParametro = "Un parámetro no contiene el valor esperado.";
        public final static String MensajeConsultaExitosa = "Se realizó la consulta satisfactoria.";
        public final static String MensajeAutenticacionLDAP_SUCCESS = "Credenciales correctas.";
        public final static String MensajeAutenticacionLDAP_ERROR = "Ocurrió un error en la autenticación por LDAP.";

    }

    public static class Servidor {

        public final static String URL = "\\\\172.18.98.47\\FileServer\\";
    }
}

