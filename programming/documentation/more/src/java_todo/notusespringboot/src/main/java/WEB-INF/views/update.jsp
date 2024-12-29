<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Update Todo</title>
</head>
<body>
<h1>Update Todo</h1>
<form action="todos/update" method="post">
  <input type="hidden" name="id" />
  <label>Title: <input type="text" name="title" /></label><br/>
  <label>Description: <input type="text" name="description" /></label><br/>
  <label>Completed: <input type="checkbox" name="completed" /></label><br/>
  <button type="submit">Save</button>
</form>
</body>
</html>
