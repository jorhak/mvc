<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-md-4 offset-8 text-right">
  <a class="btn btn-primary btn-sm" href='/mvc<%=request.getParameter("create")%>' role="button">New</a>
  <button type="submit" class="btn btn-success btn-sm" form='<%=request.getParameter("form")%>'>Save</button>
  <a class="btn btn-danger btn-sm" href='/mvc<%=request.getParameter("delete")%>' role="button">Delete</a>
  <a class="btn btn-warning btn-sm" href='/mvc<%=request.getParameter("findAll")%>' role="button">List</a>
</div>