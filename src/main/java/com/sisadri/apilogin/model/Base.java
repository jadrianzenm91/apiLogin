/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sisadri.apilogin.model;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author pc
 */
public class Base {

    private String estado;

    private String feccreacio;

    private String fecmodific;

    private String usucreacio;

    private String usumodific;

    private String ipTerminal;

    private String hostname;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFeccreacio() {
        return feccreacio;
    }

    public void setFeccreacio(String feccreacio) {
        this.feccreacio = feccreacio;
    }

    public String getFecmodific() {
        return fecmodific;
    }

    public void setFecmodific(String fecmodific) {
        this.fecmodific = fecmodific;
    }

    /**
     * @return the ipTerminal
     */
    public String getIpTerminal() {
        try {
            if (this.ipTerminal == null) {
                InetAddress ip = InetAddress.getLocalHost();
                ipTerminal = ip.toString();
            }
            return ipTerminal;
        } catch (Exception ex) {
            return "";
        }
    }

    /**
     * @param ipTerminal the ipTerminal to set
     */
    public void setIpTerminal(String ipTerminal) {
        this.ipTerminal = ipTerminal;
    }

    /**
     * @return the hostname
     */
    public String getHostname() throws UnknownHostException {
        if (this.hostname == null) {
            InetAddress ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
        }

        return hostname;
    }

    /**
     * @param hostname the hostname to set
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * @return the usucreacio
     */
    public String getUsucreacio() {
        return usucreacio;
    }

    /**
     * @param usucreacio the usucreacio to set
     */
    public void setUsucreacio(String usucreacio) {
        this.usucreacio = usucreacio;
    }

    /**
     * @return the usumodific
     */
    public String getUsumodific() {
        return usumodific;
    }

    /**
     * @param usumodific the usumodific to set
     */
    public void setUsumodific(String usumodific) {
        this.usumodific = usumodific;
    }
}
