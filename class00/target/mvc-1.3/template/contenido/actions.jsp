<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="../controls.jsp">
  <jsp:param name="create" value="/contenido" />
  <jsp:param name="delete" value="/contenido/delete?id=${id}" />
  <jsp:param name="findAll" value="/contenido/findAll" />
  <jsp:param name="form" value="contenido"/>
</jsp:include>
