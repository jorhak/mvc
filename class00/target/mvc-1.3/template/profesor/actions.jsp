<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="../controls.jsp">
  <jsp:param name="create" value="/profesor" />
  <jsp:param name="delete" value="/profesor/delete?id=${id}" />
  <jsp:param name="findAll" value="/profesor/findAll" />
  <jsp:param name="form" value="profesor"/>
</jsp:include>
