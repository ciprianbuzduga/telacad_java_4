/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.clinicamed.web.util;

import org.primefaces.context.RequestContext;

public class RequestContextUtil {
    
    public static void hideDialog(String widgetvar) {
        execute("PF('" + widgetvar + "').hide()");
    }
    
    private static void execute(String js) {
        RequestContext.getCurrentInstance().execute(js);
    }
}
