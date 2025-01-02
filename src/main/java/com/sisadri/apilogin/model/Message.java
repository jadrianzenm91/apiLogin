/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sisadri.apilogin.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author pc
 */
@Data
public class Message {
    public static final String Success = "success";
    public static final String Information = "info";
    public static final String Warning = "warning";
    public static final String Danger = "danger";
    
    private String type;
    private String message;
    private List<Object> parameters;
    private Object data;
    private String exception;
    private Boolean success;
    
    /**
     * Returns same message received, but now with all parameters tha can be
     * found into message,
     *
     * @param type
     * @return
     */
    public Message withParameters(String type) {
        if (type.equals(this.type)) {
            this.parameters = new ArrayList<Object>();

            int iSeparator = this.message.indexOf("|");
            String sParameters = this.message.substring(iSeparator + 1, this.message.length());

            this.message = this.message.substring(0, iSeparator);

            int iAmp = sParameters.indexOf("&");
            while (iAmp != -1) {
                parameters.add(sParameters.substring(0, iAmp));
                sParameters = sParameters.substring(iAmp + 1, sParameters.length());
                iAmp = sParameters.indexOf("&");
            }
            parameters.add(sParameters);
        }
        return this;
    }

    /**
     * Returns a specific message formatted to client Format 1 type|message
     * Format 2 type|message|parameter&parameter&parameter...
     *
     * @param message Formatted text
     * @return
     */
    public Message convert(String message) {
        int iSeparator = message.indexOf("|");
        this.type = message.substring(0, iSeparator);
        this.success = message.substring(0, iSeparator).toUpperCase().equals("SUCCESS");
        this.message = message.substring(iSeparator + 1, message.length());
        return this;
    }

    public Message convert(String type, String message) {
        this.type = type;
        this.success = type.toUpperCase().equals("SUCCESS");
        this.message = message;
        return this;
    }

    public Message convert(String type, String message, Object[] parameters) {
        this.type = type;
        this.success = type.toUpperCase().equals("SUCCESS");
        this.message = message;
        this.parameters = new ArrayList<Object>();
        for (Object parameter : parameters) {
            this.parameters.add(parameter);
        }
        return this;
    }
}
