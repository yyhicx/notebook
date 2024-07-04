import React, { useRef } from 'react';
import { Box, Container } from '@mui/material';
import { makeStyles } from '@mui/styles';

import { useSprings, animated } from 'react-spring';

import { useDrag } from 'react-use-gesture';

import lodash from 'lodash';

import move from '../utils/lodash-move';

const useStyles = makeStyles({
  content: {
    position: 'relative',
    width: '200px',
    height: '100px',
    '& > div': {
      position: 'absolute',
      width: '200px',
      height: '40px',
      transformOrigin: '50% 50% 0px',
      borderRadius: '5px',
      color: 'white',
      lineHeight: '40px',
      paddingLeft: '32px',
      fontSize: '14.5px',
      background: 'lightblue',
      textTransform: 'uppercase',
      letterSpacing: '2px',
      touchAction: 'none',
    },
    '& > div:nth-child(1)': {
      background: 'linear-gradient(135deg, #f6d365 0%, #fda085 100%)',
    },
    '& > div:nth-child(2)': {
      background: 'linear-gradient(135deg, #f093fb 0%, #f5576c 100%)',
    },
    '& > div:nth-child(3)': {
      background: 'linear-gradient(135deg, #5ee7df 0%, #b490ca 100%)',
    },
    '& > div:nth-child(4)': {
      background: 'linear-gradient(135deg, #c3cfe2 0%, #c3cfe2 100%)',
    },
  },
});

const fn =
  (order, active = false, originalIndex = 0, curIndex = 0, y = 0) =>
  (index) => {
    return active && index === originalIndex
      ? {
          y: curIndex * 50 + y,
          scale: 1.1,
          zIndex: 1,
          shadow: 15,
          immediate: (key) => key === 'y' || key === 'zIndex',
        }
      : {
          y: order.indexOf(index) * 50,
          scale: 1,
          zIndex: 0,
          shadow: 1,
          immediate: false,
        };
  };

const DraggableListItems = ({ items }) => {
  const order = useRef(items.map((_, index) => index));
  const [springs, api] = useSprings(items.length, fn(order.current));
  const bind = useDrag(({ args: [originalIndex], active, movement: [, y] }) => {
    const curIndex = order.current.indexOf(originalIndex);
    const curRow = lodash.clamp(
      Math.round((curIndex * 100 + y) / 100),
      0,
      items.length - 1
    );
    const newOrder = move(order.current, curIndex, curRow);
    // Feed springs new style data, they'll animate the view without causing a single render
    api.start(fn(newOrder, active, originalIndex, curIndex, y));
    if (!active) order.current = newOrder;
  });
  const classes = useStyles();

  return (
    <Box className={classes.content} style={{ height: items.length * 50 }}>
      {springs.map(({ zIndex, shadow, y, scale }, i) => (
        <animated.div
          {...bind(i)}
          key={i}
          style={{
            zIndex,
            boxShadow: shadow.to(
              (s) => `rgba(0, 0, 0, 0.15) 0px ${s}px ${2 * s}px 0px`
            ),
            y,
            scale,
          }}
          children={items[i]}
        />
      ))}
    </Box>
  );
};

const DraggableList = () => {
  return (
    <Container
      sx={{ marginTop: '100px', display: 'flex', justifyContent: 'center' }}
    >
      <DraggableListItems items={'Lorem ipsum dolor sit'.split(' ')} />
    </Container>
  );
};

export default DraggableList;
