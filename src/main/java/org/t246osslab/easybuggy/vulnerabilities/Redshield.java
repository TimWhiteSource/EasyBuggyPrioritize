package org.t246osslab.easybuggy.vulnerabilities;

import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class Redshield extends UnrestrictedSizeUploadServlet {
// test
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        try {
            String string = req.getParameter("string");
            Locale locale = req.getLocale();

            StringBuilder bodyHtml = new StringBuilder();

            bodyHtml.append("<form action=\"xss\" method=\"post\">");
            bodyHtml.append(getMsg("description.reverse.string", locale));
            bodyHtml.append("<br><br>");
            bodyHtml.append(getMsg("label.string", locale) + ": ");
            bodyHtml.append("<input type=\"text\" name=\"string\" size=\"100\" maxlength=\"100\">");
            bodyHtml.append("<br><br>");
            bodyHtml.append("<input type=\"submit\" value=\"" + getMsg("label.submit", locale) + "\">");
            bodyHtml.append("<br><br>");

            if (!StringUtils.isBlank(string)) {
                // Reverse the given string
                String reversedName = StringUtils.reverse(string);
                bodyHtml.append(getMsg("label.reversed.string", locale) + " : "
                        + reversedName);
            } else {
                bodyHtml.append(getMsg("msg.enter.string", locale));
            }
            bodyHtml.append("<br><br>");
            bodyHtml.append(getInfoMsg("msg.note.xss", locale));
            bodyHtml.append("</form>");

            responseToClient(req, res, getMsg("title.xss.page", locale), bodyHtml.toString());

        } catch (Exception e) {
            log.error("Exception occurs: ", e);
        }
    }

}
