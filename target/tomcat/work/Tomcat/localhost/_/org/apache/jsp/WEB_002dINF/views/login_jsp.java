/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2020-11-03 15:11:45 UTC
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
    _jspx_dependants.put("/WEB-INF/views/../jspf/menuSuperior.jspf", Long.valueOf(1604416294240L));
    _jspx_dependants.put("/WEB-INF/views/../jspf/header.jspf", Long.valueOf(1604415589168L));
    _jspx_dependants.put("/WEB-INF/views/../jspf/footer.jspf", Long.valueOf(1604415589168L));
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

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta charset=\"UTF-8\">\n");
      out.write("<link href=\"webjars/bootstrap/4.5.2/css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("<title>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pagina.getTitulo()}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</title>\n");
      out.write("\n");
      out.write("<style>\n");
      out.write("\t.footer {\n");
      out.write("\t\tposition: absolute;\n");
      out.write("\t\tbottom: 0;\n");
      out.write("\t\twidth: 100%;\n");
      out.write("\t\theight: 60px;\n");
      out.write("\t\tbackground-color: #f5f5f5;\n");
      out.write("\t}\n");
      out.write("\t\n");
      out.write("\tbody{\n");
      out.write("\tpadding: 5px;\n");
      out.write("\tpadding-left: 10px;\n");
      out.write("\tpadding-right: 10px;\n");
      out.write("}\n");
      out.write("</style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\n");
      out.write('\n');
      out.write("<nav class=\"navbar navbar-default\">\n");
      out.write("\n");
      out.write("\t\t<a href=\"http://ieslluissimarro.org/\" class=\"navbar-brand\">Simarro</a>\n");
      out.write("\n");
      out.write("\t\t<nav class=\"nav nav-pills flex-column flex-sm-row\">\n");
      out.write("\t\t\t<a class=\"nav-link ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pagina.getStrBootstrapActiva('/login')}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" href=\"/login\">Home</a>\n");
      out.write("\t\t\t<a class=\"nav-link ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pagina.getStrBootstrapActiva('/list-alumno')}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" href=\"/list-alumno\">Alumnos</a>\n");
      out.write("\t\t\t<a class=\"nav-link ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pagina.getStrBootstrapActiva(\"/list-errores.do\")}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" href=\"/list-errores.do\">Errores</a>\n");
      out.write("\t\t\t<a class=\"nav-link\" href=\"http://www.ieslluissimarro.org/moodle/course/view.php?id=508\">DWES</a>\n");
      out.write("\t\t</nav>\n");
      out.write(" \n");
      out.write(" \n");
      out.write("\t\t<ul class=\"nav navbar-nav navbar-right\">\n");
      out.write("\t\t\t<li><a class=\"nav-link\" href=\"/logout.do\">Logout</a></li>\n");
      out.write("\t\t</ul>\n");
      out.write("\n");
      out.write("\t</nav>");
      out.write("\n");
      out.write("\n");
      out.write("<div class=\"container\">\n");
      out.write("\n");
      out.write("\t<p>\n");
      out.write("\t\t<font color=\"red\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${errores}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</font>\n");
      out.write("\t</p>\n");
      out.write("\t<form action=\"login\" method=\"post\">\n");
      out.write("\t\t<label for=\"nombre\">Introduzca su nombre: </label> <input type=\"text\"\n");
      out.write("\t\t\tname=\"nombre\" /> <br>\n");
      out.write("\t\t<br> <label for=\"password\">Introduzca su contraseña: </label> <input\n");
      out.write("\t\t\ttype=\"password\" name=\"password\" /> <br>\n");
      out.write("\t\t<br> <input type=\"submit\" value=\"Login\" />\n");
      out.write("\t</form>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("<footer class=\"footer\">\n");
      out.write("\t<p>DWES: Desarrollo Web en Entorno Servidor</p>\n");
      out.write("</footer>\n");
      out.write("\n");
      out.write("\n");
      out.write("\t<script src=\"webjars/jquery/3.5.1/jquery.min.js\"></script>\n");
      out.write("\t<script src=\"webjars/popper.js/1.16.0/umd/popper.min.js\"></script>\n");
      out.write("\t<script src=\"webjars/bootstrap/4.5.2/js/bootstrap.min.js\"></script>\n");
      out.write("</body>\n");
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
