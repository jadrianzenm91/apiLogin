/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sisadri.apilogin.repositoryImp;

import com.sisadri.apilogin.helpers.Constantes;
import com.sisadri.apilogin.helpers.Format;
import com.sisadri.apilogin.helpers.Util;
import com.sisadri.apilogin.model.Menu;
import com.sisadri.apilogin.model.Token;
import com.sisadri.apilogin.model.Usuario;
import com.sisadri.apilogin.repository.LogueoDao;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pc
 */
@Repository("LogueoDaoImpl")
public class LogueoDaoImpl implements LogueoDao{
    
    private SimpleJdbcCall login;
    private SimpleJdbcCall generarToken;
    private SimpleJdbcCall validarToken;
    private SimpleJdbcCall getFormulariosPortokenUsuario;
    private SimpleJdbcCall getModulos;
    private SimpleJdbcCall getSubModulos;
    
    
     @Autowired
    public void setDataSource(DataSource dataSource) {
        this.login = new SimpleJdbcCall(dataSource)
                .withCatalogName("dbcobranza")
                .withProcedureName("usp_login")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                    new SqlParameter("v_usuario", Types.VARCHAR),
                    new SqlParameter("v_password", Types.VARCHAR),
                    new SqlOutParameter("MENSAJE", Types.VARCHAR)
                );
        this.generarToken = new SimpleJdbcCall(dataSource)
                .withCatalogName("dbcobranza")
                .withProcedureName("usp_ins_token");
        this.validarToken = new SimpleJdbcCall(dataSource)
                .withCatalogName("dbcobranza")
                .withProcedureName("usp_validartoken");
        this.getFormulariosPortokenUsuario = new SimpleJdbcCall(dataSource)
                .withCatalogName("dbcobranza")
                .withProcedureName("USP_GET_FORMULARIO");
        this.getModulos = new SimpleJdbcCall(dataSource)
                .withCatalogName("dbcobranza")
                .withProcedureName("USP_SEL_MODULO");
        this.getSubModulos = new SimpleJdbcCall(dataSource)
                .withCatalogName("dbcobranza")
                .withProcedureName("USP_SEL_SUBMODULO");
        
    }
    
    
    @Override
    public String login(Usuario usuario) {
        String mensaje = "";
       
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("v_usuario", usuario.getUsuario())
                .addValue("v_password", usuario.getClave());// Util.encrypt(usuario.getClave()));
        Map<String, Object>  result = login.execute(in);
        mensaje = Format.toString(result.get("mensaje"));
         
        return mensaje;
    }

    @Override
    public String validarToken(Token token) {
        String resultado = "";
        
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("V_TOKEN", token.getToken())
                .addValue("I_IDUSUARIO", token.getIdusuario());
         Map<String, Object> out = validarToken.execute(in);
         resultado = Format.toString(out.get("o_mensaje"));
        
        return resultado;
    }

    @Override
    public Token generarToken(Token token) {
        
        SqlParameterSource in;
       
        in = new MapSqlParameterSource()
                .addValue("i_idusuario", token.getIdusuario())
                .addValue("v_usuario", token.getUsuario());
        Map<String, Object> out = generarToken.execute(in);
        token.setToken(Format.toString(out.get("o_token")));
        token.setFechaexp(Format.toString(out.get("o_fechaexp")));
            
        return token;
    }

    @Override
    public List<Menu> obtenerAccesosPorToken(String token, String siglaMenu) {
        List<Menu> lstMenu = new ArrayList();
        
        SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("V_TOKEN", token)
                    .addValue("V_CLAVEMENU", siglaMenu);
        
            Map map = getFormulariosPortokenUsuario.execute(in);
            List<Map<String, Object>> result = (List<Map<String, Object>>) map.get("#result-set-"+1);
            for (Map obj : result) {
                Menu menu = new Menu();
                menu.setIdMenu(Format.toInteger(obj.get("IDMENU")));
                menu.setMenu(Format.toStringUpper(obj.get("NOMMENU")));
                menu.setIdform(Format.toInteger(obj.get("IDFORM")));
                menu.setFormulario(Format.toStringUpper(obj.get("NOMFORM")));
                menu.setUrl(Format.toString(obj.get("RUTAFORM")));
                menu.setDesimagen(Format.toString(obj.get("DESIMAGEN")));
                menu.setSecumenu(Format.toInteger(obj.get("SECUMENU")));
                menu.setSecuform(Format.toInteger(obj.get("SECUFORM")));
                lstMenu.add(menu);
            }
            
            return lstMenu;
    }
    @Override
    public List<Menu> getModulos(String token) {
        List<Menu> lstMenu = new ArrayList();
        
        SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("V_TOKEN", token);
        
            Map map = getModulos.execute(in);
            List<Map> result = (List<Map>) map.get("CV");
            for (Map obj : result) {
                Menu menu = new Menu();
                menu.setIdMenu(Format.toInteger(obj.get("IDMENU")));
                menu.setMenu(Format.toStringUpper(obj.get("NOMMENU")));
                menu.setIdform(Format.toInteger(obj.get("IDFORM")));
                menu.setFormulario(Format.toStringUpper(obj.get("NOMFORM")));
                menu.setUrl(Format.toString(obj.get("RUTAFORM")));
                menu.setDesimagen(Format.toString(obj.get("DESIMAGEN")));
                menu.setClaveMenu(Format.toString(obj.get("CLAVEMENU")));
                menu.setSecumenu(Format.toInteger(obj.get("SECUMENU")));
                lstMenu.add(menu);
            }
            
            return lstMenu;
    }
    @Override
    public List<Menu> getSubModulos(String token, Integer idsubmenu) {
        List<Menu> lstMenu = new ArrayList();
        
        SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("V_TOKEN", token)
                    .addValue("I_IDSUBMENU", idsubmenu);
        
            Map map = getSubModulos.execute(in);
            List<Map> result = (List<Map>) map.get("CV");
            for (Map obj : result) {
                Menu menu = new Menu();
                menu.setIdMenu(Format.toInteger(obj.get("IDMENU")));
                menu.setMenu(Format.toStringUpper(obj.get("NOMMENU")));
                menu.setIdform(Format.toInteger(obj.get("IDFORM")));
                menu.setFormulario(Format.toStringUpper(obj.get("NOMFORM")));
                menu.setUrl(Format.toString(obj.get("RUTAFORM")));
                menu.setDesimagen(Format.toString(obj.get("DESIMAGEN")));
                menu.setClaveMenu(Format.toString(obj.get("CLAVEMENU")));
                menu.setSecumenu(Format.toInteger(obj.get("SECUMENU")));
                lstMenu.add(menu);
            }
            
            return lstMenu;
    }
    

    
}
