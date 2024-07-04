import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import {
  addTodo,
  toggleTodo,
  deleteTodo,
  selectTodos,
} from '../features/todos/todosSlice';
import { makeStyles } from '@mui/styles';

const useStyles = makeStyles({
  appContainer: {
    maxWidth: '600px',
    margin: '0 auto',
    padding: '20px',
  },
  appTitle: {
    textAlign: 'center',
    fontSize: '24px',
    marginBottom: '20px',
  },
  addTodoContainer: {
    display: 'flex',
    marginBottom: '20px',
  },
  addTodoInput: {
    flex: '1',
    padding: '8px',
    fontSize: '16px',
    border: '1px solid #ccc',
    borderRadius: '4px',
    marginRight: '10px',
  },
  addTodoButton: {
    padding: '8px 16px',
    fontSize: '16px',
    backgroundColor: '#007bff',
    color: '#fff',
    border: 'none',
    borderRadius: '4px',
    cursor: 'pointer',
  },
  todosList: {
    listStyle: 'none',
    padding: '0',
  },
  todoItem: {
    display: 'flex',
    alignItems: 'center',
    marginBottom: '10px',
  },
  todoText: {
    flex: '1',
    cursor: 'pointer',
  },
  completed: {
    textDecoration: 'line-through',
  },
  deleteTodoButton: {
    padding: '4px 8px',
    fontSize: '14px',
    backgroundColor: '#dc3545',
    color: '#fff',
    border: 'none',
    borderRadius: '4px',
    cursor: 'pointer',
    marginLeft: '10px',
  },
});

const Todos = () => {
  const dispatch = useDispatch();
  const todos = useSelector(selectTodos);

  const [inputValue, setInputValue] = useState('');

  const classes = useStyles();

  const handleAddTodo = () => {
    dispatch(addTodo(inputValue));
    setInputValue('');
  };

  const handleToggleTodo = (id) => {
    dispatch(toggleTodo(id));
  };

  const handleDeleteTodo = (id) => {
    dispatch(deleteTodo(id));
  };

  return (
    <div className={classes.appContainer}>
      <h1 className={classes.appTitle}>Todo App</h1>
      <div className={classes.addTodoContainer}>
        <input
          className={classes.addTodoInput}
          value={inputValue}
          onChange={(e) => setInputValue(e.target.value)}
          placeholder="Add a new todo"
        />
        <button className={classes.addTodoButton} onClick={handleAddTodo}>
          Add Todo
        </button>
      </div>
      <ul className={classes.todosList}>
        {todos.map((todo) => (
          <li
            key={todo.id}
            className={
              classes.todoItem && `${todo.completed ? classes.completed : ''}`
            }
          >
            <span
              className={classes.todoText}
              onClick={() => handleToggleTodo(todo.id)}
            >
              {todo.text}
            </span>
            <button
              className={classes.deleteTodoButton}
              onClick={() => handleDeleteTodo(todo.id)}
            >
              Delete
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Todos;
