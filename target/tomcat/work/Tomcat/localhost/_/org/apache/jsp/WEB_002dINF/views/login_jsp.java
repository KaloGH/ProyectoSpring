/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2020-11-10 14:43:39 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(3);
    _jspx_dependants.put("/WEB-INF/views/../jspf/menuSuperior.jspf", Long.valueOf(1604487667093L));
    _jspx_dependants.put("/WEB-INF/views/../jspf/header.jspf", Long.valueOf(1604487667093L));
    _jspx_dependants.put("/WEB-INF/views/../jspf/footer.jspf", Long.valueOf(1604487667093L));
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<link href=\"webjars/bootstrap/4.5.2/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("<title>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pagina.getTitulo()}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</title>\r\n");
      out.write("\r\n");
      out.write("<style>\r\n");
      out.write("\t.footer {\r\n");
      out.write("\t\tposition: absolute;\r\n");
      out.write("\t\tbottom: 0;\r\n");
      out.write("\t\twidth: 100%;\r\n");
      out.write("\t\theight: 60px;\r\n");
      out.write("\t\tbackground-color: #f5f5f5;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tbody{\r\n");
      out.write("\tpadding: 5px;\r\n");
      out.write("\tpadding-left: 10px;\r\n");
      out.write("\tpadding-right: 10px;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write('\r');
      out.write('\n');
      out.write("<nav class=\"navbar navbar-default\">\r\n");
      out.write("\r\n");
      out.write("\t\t<a href=\"http://ieslluissimarro.org/\" class=\"navbar-brand\">Simarro</a>\r\n");
      out.write("\r\n");
      out.write("\t\t<nav class=\"nav nav-pills flex-column flex-sm-row\">\r\n");
      out.write("\t\t\t<a class=\"nav-link ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pagina.getStrBootstrapActiva('login')}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" href=\"login\">Home</a>\r\n");
      out.write("\t\t\t<a class=\"nav-link ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pagina.getStrBootstrapActiva('list-alumno')}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" href=\"list-alumno\">Alumnos</a>\r\n");
      out.write("\t\t\t<a class=\"nav-link ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pagina.getStrBootstrapActiva(\"/list-errores.do\")}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" href=\"/list-errores.do\">Errores</a>\r\n");
      out.write("\t\t\t<a class=\"nav-link\" href=\"http://www.ieslluissimarro.org/moodle/course/view.php?id=508\">DWES</a>\r\n");
      out.write("\t\t</nav>\r\n");
      out.write(" \r\n");
      out.write(" \r\n");
      out.write("\t\t<ul class=\"nav navbar-nav navbar-right\">\r\n");
      out.write("\t\t\t<li><a class=\"nav-link\" href=\"/logout.do\">Logout</a></li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\r\n");
      out.write("\t</nav>");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div class=\"container\">\r\n");
      out.write("\r\n");
      out.write("\t<p>\r\n");
      out.write("\t\t<font color=\"red\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${errores}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</font>\r\n");
      out.write("\t</p>\r\n");
      out.write("\t<form action=\"login\" method=\"post\">\r\n");
      out.write("\t\t<label for=\"nombre\">Introduzca su nombre: </label> <input type=\"text\"\r\n");
      out.write("\t\t\tname=\"nombre\" /> <br>\r\n");
      out.write("\t\t<br> <label for=\"password\">Introduzca su contraseña: </label> <input\r\n");
      out.write("\t\t\ttype=\"password\" name=\"password\" /> <br>\r\n");
      out.write("\t\t<br> <input type=\"submit\" value=\"Login\" />\r\n");
      out.write("\t</form>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<footer class=\"footer\">\r\n");
      out.write("\t<p>DWES: Desarrollo Web en Entorno Servidor</p>\r\n");
      out.write("</footer>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t<script src=\"webjars/jquery/3.5.1/jquery.min.js\"></script>\r\n");
      out.write("\t<script src=\"webjars/popper.js/1.16.0/umd/popper.min.js\"></script>\r\n");
      out.write("\t<script src=\"webjars/bootstrap/4.5.2/js/bootstrap.min.js\"></script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
