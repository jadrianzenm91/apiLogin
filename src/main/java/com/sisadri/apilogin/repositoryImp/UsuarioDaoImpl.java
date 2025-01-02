/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sisadri.apilogin.repositoryImp;

import com.sisadri.apilogin.helpers.Constantes;
import com.sisadri.apilogin.helpers.Format;
import com.sisadri.apilogin.model.Usuario;
import com.sisadri.apilogin.repository.UsuarioDao;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pc
 */
@Repository("UsuarioDaoImpl")
public class UsuarioDaoImpl implements UsuarioDao {
    
    private SimpleJdbcCall getUsuario;
    
     @Autowired
    public void setDataSource(DataSource dataSource) {
        this.getUsuario = new SimpleJdbcCall(dataSource)
                .withProcedureName("usp_sel_usuario_get");
      
    }
    
    @Override
    public Usuario getUsuario(Usuario usuario) {
        
        SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("v_usuario", usuario.getUsuario());
            Map map = getUsuario.execute(in);
            List<Map<String, Object>> result = (List<Map<String, Object>>) map.get("#result-set-"+1);
            for (Map obj : result) {
                
                usuario.setIdusuario(Format.toInt(obj.get("idusuario")));
                usuario.setUsuario(Format.toString(obj.get("usuario")));
                usuario.setNombresCompletos(Format.toString(obj.get("nombres")));
                usuario.setCorreo(Format.toString(obj.get("correo")));
                usuario.setUuid(Format.toString(obj.get("uuid")));
                usuario.setRol(Format.toString(obj.get("rol")));
            }
         
        return usuario;
    }
    
}
