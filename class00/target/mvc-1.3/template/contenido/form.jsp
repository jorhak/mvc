<%@page contentType="text/html" pageEncoding="UTF-8"%>

<form action="/mvc/contenido" method="POST" id="contenido">
  <input type="text" name="id" value="${id}" hidden>  
  <div>
    <label for="descripcion">Descripcion</label>
    <input type="text" name="descripcion" value="${descripcion}" id="descripcion" required>
  </div> 
  <div>
    <label for="id_materia">Materia</label>
    <select name="id_materia" id="id_materia">
      ${carga_materias}
    </select>
  </div>    
</form>
