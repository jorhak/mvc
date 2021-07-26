<%@page contentType="text/html" pageEncoding="UTF-8"%>

<form action="/mvc/materia" method="POST" id="materia">
  <input type="text" name="id" value="${id}" hidden>  
  <div>
    <label for="codigo">Codigo</label>
    <input type="text" name="codigo" value="${codigo}" id="codigo" required>
  </div> 
  <div>
    <label for="descripcion">Descripcion</label>
    <input type="text" name="descripcion" value="${descripcion}" id="descripcion" required>
  </div>    
</form>
