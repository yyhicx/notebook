<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Todo List</title>
</head>
<body>
<h1>Todo List</h1>
<a href="todos/create">Create New Todo</a>
<ul>
  <c:forEach items="${todos}" var="todo">
    <span>${todo.title}</span> - <span>${todo.description}</span>
    <a href="/todos/update/{${todo.id}}">Edit</a>
    <a href="/todos/delete/{${todo.id}}">Delete</a>
  </c:forEach>
</ul>
</body>
</html>
