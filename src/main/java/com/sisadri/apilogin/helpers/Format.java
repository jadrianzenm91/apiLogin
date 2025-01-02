/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sisadri.apilogin.helpers;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author pc
 */
public class Format {

    public static Date toDate(String date, String format) {
        try {
            if (date != null) {
                SimpleDateFormat oFormatter = new SimpleDateFormat(format);
                return oFormatter.parse(date);
            }
            return null;
        } catch (Exception exception) {
            System.err.printf("EXCEPTION @toDate method: %s\n", exception.getMessage());
            return null;
        }
    }

    public static String toString(Date date, String format) {
        try {
            if (date != null) {
                SimpleDateFormat oFormatter = new SimpleDateFormat(format);
                return oFormatter.format(date);
            }
            return null;
        } catch (Exception exception) {
            System.err.printf("EXCEPTION @toString method: %s\n", exception.getMessage());
            return null;
        }
    }

    public static String toString(Double value) {
        try {
            if (value != null) {
                DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(Locale.US);
                DecimalFormat formatter = new DecimalFormat("###,##0");
                formatter.setDecimalFormatSymbols(symbols);
                return formatter.format(value);
            }
            return null;
        } catch (Exception exception) {
            return null;
        }
    }

    public static String toString(Double value, Integer decimals) {
        try {
            if (value != null) {
                String format = "###,##0";

                if (decimals > 0) {
                    format += ".";
                    for (int i = 0; i < decimals; i++) {
                        format += "0";
                    }
                }

                DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(Locale.US);
                DecimalFormat formatter = new DecimalFormat(format);
                formatter.setDecimalFormatSymbols(symbols);
                return formatter.format(value);
            }
            return null;
        } catch (Exception exception) {
            return null;
        }
    }

    public static String toString(Object obj) {
        try {
            return obj == null ? "" : obj.toString();
        } catch (NullPointerException | NumberFormatException exception) {
            System.out.printf("EXCEPTION @toShort method: %s\n", exception.getMessage());
            return "";
        }
    }

    public static String toStringUpper(Object obj) {
        try {
            return obj == null ? "" : obj.toString().toUpperCase();
        } catch (NullPointerException | NumberFormatException exception) {
            System.out.printf("EXCEPTION @toShort method: %s\n", exception.getMessage());
            return "";
        }
    }

    public static Integer toInteger(Object obj) {
        try {
            return obj == null ? 0 : Integer.parseInt(obj.toString());
        } catch (NullPointerException | NumberFormatException exception) {
            System.out.printf("EXCEPTION @toShort method: %s\n", exception.getMessage());
            return 0;
        }
    }

    public static short toShort(Object value) {
        try {
            return Short.parseShort(value.toString());
        } catch (NullPointerException | NumberFormatException exception) {
            System.out.printf("EXCEPTION @toShort method: %s\n", exception.getMessage());
            return -1;
        }
    }

    public static int toInt(Object value) {
        try {
            return Integer.parseInt(value.toString());
        } catch (NullPointerException | NumberFormatException exception) {
            System.out.printf("EXCEPTION @toInt method: %s\n", exception.getMessage());
            return -1;
        }
    }

    public static long toLong(Object value) {
        try {
            return Long.parseLong(value.toString());
        } catch (NullPointerException | NumberFormatException exception) {
            System.out.printf("EXCEPTION @toInt method: %s\n", exception.getMessage());
            return -1;
        }
    }

    public static double toDouble(Object value) {
        try {
            return value == null ? 0 : Double.parseDouble(value.toString());
        } catch (NullPointerException | NumberFormatException exception) {
            System.out.printf("EXCEPTION @toDouble method: %s\n", exception.getMessage());
            return 0;
        }
    }

    public static boolean toBoolean(Object value) {
        try {
            if (value.toString().equalsIgnoreCase("true") || value.toString().equals("1")) {
                return true;
            } else if (value.toString().equalsIgnoreCase("false") || value.toString().equals("0")) {
                return false;
            }
            return false;
        } catch (NullPointerException exception) {
            System.out.printf("EXCEPTION @toBoolean method: %s\n", exception.getMessage());
            return false;
        }
    }

}
