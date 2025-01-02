/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sisadri.apilogin.helpers;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.util.StringUtils;

/**
 *
 * @author pc
 */
public final class Util {

    public static Date toDate(String date, String format) {
        try {
            SimpleDateFormat oFormatter = new SimpleDateFormat(format);
            return oFormatter.parse(date);
        } catch (Exception exception) {
            System.err.printf("EXCEPTION@toDate method: %s", exception.getMessage());
            return null;
        }
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.equals("");
    }


    public static List<Object> listDistinct(List<Object> lista) {
        SortedSet<Object> listaN = new TreeSet<>();
        listaN.addAll(lista);
        lista.clear();
        for (Object obj : listaN) {
            if (!lista.contains(obj)) {
                lista.add(obj);
            }
        }
        return lista;
    }
    public static String getYearNow() {
        SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy");
        Date date = new Date();
        return DATE_TIME_FORMAT.format(date);
    }
    public static String getDateNow() {
        SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return DATE_TIME_FORMAT.format(date);
    }
    public static String encrypt(String value) throws Exception {
        return new AES().encrypt(value);
    }

    public static String decrypt(String value) throws Exception{
        return new AES().decrypt(value);
    }

    public static String getClientIp(HttpServletRequest request) {
        String LOCALHOST_IPV4 = "127.0.0.1";
        String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";
        String ipAddress = request.getHeader("X-Forwarded-For");
 
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
 
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
 
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (LOCALHOST_IPV4.equals(ipAddress) || LOCALHOST_IPV6.equals(ipAddress)) {
                try {
                    InetAddress inetAddress = InetAddress.getLocalHost();
                    ipAddress = inetAddress.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }
 
        if (!StringUtils.isEmpty(ipAddress)
                && ipAddress.length() > 15
                && ipAddress.indexOf(",") > 0) {
            ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
        }
 
        return ipAddress;
    }
   
}

