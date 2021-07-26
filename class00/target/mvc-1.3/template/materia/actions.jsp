<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="../controls.jsp">
  <jsp:param name="create" value="/materia" />
  <jsp:param name="delete" value="/materia/delete?id=${id}" />
  <jsp:param name="findAll" value="/materia/findAll" />
  <jsp:param name="form" value="materia"/>
</jsp:include>
