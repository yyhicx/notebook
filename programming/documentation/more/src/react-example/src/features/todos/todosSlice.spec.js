import todoReducer, { addTodo, toggleTodo, deleteTodo } from './todosSlice';

describe('todo reducer', () => {
  it('should handle initial state', () => {
    expect(todoReducer(undefined, { type: 'unknown' })).toEqual([]);
  });

  it('should handle addTodo', () => {
    const initialState = [];
    const actual = todoReducer(initialState, addTodo('Buy groceries'));
    expect(actual.value).toEqual([
      {
        id: expect.any(Number),
        text: 'Buy groceries',
        completed: false,
      },
    ]);
  });

  it('should handle toggleTodo', () => {
    const initialState = [
      {
        id: 1,
        text: 'Buy groceries',
        completed: false,
      },
    ];
    const actual = counterReducer(initialState, toggleTodo(1));
    expect(actual.value).toEqual([
      {
        id: 1,
        text: 'Buy groceries',
        completed: true,
      },
    ]);
  });

  it('should handle deleteTodo', () => {
    const initialState = [
      {
        id: 1,
        text: 'Buy groceries',
        completed: false,
      },
    ];
    const actual = counterReducer(initialState, deleteTodo(1));
    expect(actual.value).toEqual([]);
  });
});
