<%@page contentType="text/html" pageEncoding="UTF-8"%>

<form action="/mvc/profesor" method="POST" id="profesor">
  <input type="text" name="id" value="${id}" hidden>  
  <div>
    <label for="descripcion">C.I.</label>
    <input type="text" name="ci" value="${ci}" id="ci" required>
  </div>
  <div>
    <label for="descripcion">NOMBRES</label>
    <input type="text" name="nombres" value="${nombres}" id="nombres" required>
  </div> 
  <div>
    <label for="descripcion">APELLIDOS</label>
    <input type="text" name="apellidos" value="${apellidos}" id="apellidos" required>
  </div> 
  <div>
    <label for="id_materia">Materia</label>
    <select name="id_materia" id="id_materia">
      ${carga_materias}
    </select>
  </div>    
</form>
