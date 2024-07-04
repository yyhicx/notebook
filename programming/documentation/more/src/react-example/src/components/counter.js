import React, { useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import {
  decrement,
  increment,
  incrementByAmount,
  incrementAsync,
  incrementIfOdd,
  selectCount,
} from '../features/counter/counterSlice';
import { makeStyles } from '@mui/styles';

const useStyles = makeStyles({
  row: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    '& > button': {
      marginLeft: '4px',
      marginRight: '8px',
    },
    '&:not(:last-child)': {
      marginBottom: '16px',
    },
  },
  value: {
    fontSize: '78px',
    paddingLeft: '16px',
    paddingRight: '16px',
    marginTop: '2px',
    fontFamily: 'Courier new, Courier, monospace',
  },
  button: {
    appearance: 'none',
    background: 'none',
    fontSize: '32px',
    paddingLeft: '12px',
    paddingRight: '12px',
    paddingBottom: '4px',
    outline: 'none',
    border: '2px solid transparent',
    color: 'rgb(112, 76, 182)',
    cursor: 'pointer',
    backgroundColor: 'rgba(112, 76, 182, 0.1)',
    borderRadius: '2px',
    transition: 'all 0.15s',
    '&:hover, &:focus': {
      border: '2px solid rgba(112, 76, 182, 0.4)',
    },
    '&:active': {
      backgroundColor: 'rgba(112, 76, 182, 0.2)',
    },
  },
  textbox: {
    fontSize: '32px',
    padding: '2px',
    width: '64px',
    textAlign: 'center',
    marginRight: '4px',
  },
  asyncButton: {
    composes: 'button',
    position: 'relative',
    '&:after': {
      content: '',
      backgroundColor: 'rgba(112, 76, 182, 0.15)',
      display: 'block',
      position: 'absolute',
      width: '100%',
      height: '100%',
      left: '0',
      top: '0',
      opacity: '0',
      transition: 'width 1s linear, opacity 0.5s ease 1s',
    },
    '&:active:after': {
      width: '0%',
      opacity: '1',
      transition: '0s',
    },
  },
});

const Counter = () => {
  const count = useSelector(selectCount);
  const dispatch = useDispatch();
  const [incrementAmount, setIncrementAmount] = useState('2');

  const incrementValue = Number(incrementAmount) || 0;

  const classes = useStyles();

  return (
    <div>
      <div className={classes.row}>
        <button
          className={classes.button}
          aria-label="Decrement value"
          onClick={() => dispatch(decrement())}
        >
          -
        </button>
        <span className={classes.value}>{count}</span>
        <button
          className={classes.button}
          aria-label="Increment value"
          onClick={() => dispatch(increment())}
        >
          +
        </button>
      </div>
      <div className={classes.row}>
        <input
          className={classes.textbox}
          aria-label="Set increment amount"
          value={incrementAmount}
          onChange={(e) => setIncrementAmount(e.target.value)}
        />
        <button
          className={classes.button}
          onClick={() => dispatch(incrementByAmount(incrementValue))}
        >
          Add Amount
        </button>
        <button
          className={classes.asyncButton}
          onClick={() => dispatch(incrementAsync(incrementValue))}
        >
          Add Async
        </button>
        <button
          className={classes.button}
          onClick={() => dispatch(incrementIfOdd(incrementValue))}
        >
          Add If Odd
        </button>
      </div>
    </div>
  );
};

export default Counter;
