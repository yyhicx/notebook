import React, { useState, useEffect, useRef } from 'react';
import {
  Box,
  Typography,
  SvgIcon,
} from '@mui/material';
import { makeStyles } from '@mui/styles';

import { useSpring, animated } from 'react-spring';

import useMeasure from 'react-use-measure';  // 测量您引用的视图的边界（例如宽度、高度、顶部、左侧）

const MinusSquareO = (props) => (
  <SvgIcon {...props} viewBox="64 -65 897 897">
    <path
      d="M888 760v0v0v-753v0h-752v0v753v0h752zM888 832h-752q-30 0 -51 -21t-21 -51v-753q0 -29 21 -50.5t51 -21.5h753q29 0 50.5 21.5t21.5 50.5v753q0 30 -21.5 51t-51.5 21v0zM732 347h-442q-14 0 -25 10.5t-11 25.5v0q0 15 11 25.5t25 10.5h442q14 0 25 -10.5t11 -25.5v0
q0 -15 -11 -25.5t-25 -10.5z"
    />
  </SvgIcon>
);

const PlusSquareO = (props) => (
  <SvgIcon {...props} viewBox="64 -65 897 897">
    <path
      d="M888 760v0v0v-753v0h-752v0v753v0h752zM888 832h-752q-30 0 -51 -21t-21 -51v-753q0 -29 21 -50.5t51 -21.5h753q29 0 50.5 21.5t21.5 50.5v753q0 30 -21.5 51t-51.5 21v0zM732 420h-184v183q0 15 -10.5 25.5t-25.5 10.5v0q-14 0 -25 -10.5t-11 -25.5v-183h-184
q-15 0 -25.5 -11t-10.5 -25v0q0 -15 10.5 -25.5t25.5 -10.5h184v-183q0 -15 11 -25.5t25 -10.5v0q15 0 25.5 10.5t10.5 25.5v183h184q15 0 25.5 10.5t10.5 25.5v0q0 14 -10.5 25t-25.5 11z"
    />
  </SvgIcon>
);

const CloseSquareO = (props) => (
  <SvgIcon {...props} viewBox="64 -65 897 897">
    <path
      d="M717.5 589.5q-10.5 10.5 -25.5 10.5t-26 -10l-154 -155l-154 155q-11 10 -26 10t-25.5 -10.5t-10.5 -25.5t11 -25l154 -155l-154 -155q-11 -10 -11 -25t10.5 -25.5t25.5 -10.5t26 10l154 155l154 -155q11 -10 26 -10t25.5 10.5t10.5 25t-11 25.5l-154 155l154 155
q11 10 11 25t-10.5 25.5zM888 760v0v0v-753v0h-752v0v753v0h752zM888 832h-752q-30 0 -51 -21t-21 -51v-753q0 -29 21 -50.5t51 -21.5h753q29 0 50.5 21.5t21.5 50.5v753q0 30 -21.5 51t-51.5 21v0z"
    />
  </SvgIcon>
);

const Icons = {
  'MinusSquareO': MinusSquareO,
  'PlusSquareO': PlusSquareO,
  'CloseSquareO': CloseSquareO,
};

const usePrevious = (value) => {
  const ref = useRef(null);
  useEffect(() => {
    ref.current = value;
  }, [value]);
  return ref.current;
}

const tStyles = makeStyles({
  frame: {
    position: 'relative',
    padding: '4px 0px 0px 0px',
    textOverflow: 'ellipsis',
    whiteSpace: 'nowrap',
    overflowX: 'hidden',
    verticalAlign: 'middle',
    color: '#24292e',
    fill: '#24292e',
  },
  content: {
    willChange: 'transform, opacity, height',
    marginLeft: '6px',
    padding: '0px 0px 0px 14px',
    borderLeft: '1px dashed rgba(255, 255, 255, 0.4)',
    overflow: 'hidden',
  }
});

const Tree = React.memo(({ children, name, style, defaultOpen = false }) => {
  const [isOpen, setOpen] = useState(defaultOpen);
  const previous = usePrevious(isOpen);
  const [ref, { height: viewHeight }] = useMeasure();
  const classes = tStyles();
  const { height, opacity, y } = useSpring({
    from: { height: 0, opacity: 0, y: 0 },
    to: {
      height: isOpen ? viewHeight : 0,
      opacity: isOpen ? 1 : 0,
      y: isOpen ? 0 : 20,
    },
  });

  const Icon = Icons[`${children ? (isOpen ? 'Minus' : 'Plus') : 'Close'}SquareO`];
  return (
    <Box className={classes.frame}>
      <Icon
        style={{ width: "1em", height: "1em", marginRight: 10, cursor: "pointer", verticalAlign: "middle", opacity: children ? 1 : 0.3 }}
        onClick={() => setOpen(!isOpen)}
      />
      <Typography component="span" sx={{ verticalAlign: "middle" }} style={style}>
        {name}
      </Typography>
      <animated.div
        className={classes.content}
        style={{
          opacity,
          height: isOpen && previous === isOpen ? 'auto' : height,
        }}>
        <animated.div ref={ref} style={{ y }} children={children} />
      </animated.div>
    </Box>
  );
})

const atStyles = makeStyles({
  container: {
    width: '100%',
    height: '100%',
    margin: '0',
    padding: '0',
    /* background: '#191b21', */
    overflow: 'hidden',
    fontFamily: 'ui-monospace, monospace',
    fontSize: '14px',
    lineHeight: '21px',
    userSelect: 'none',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
  },
});

const AnimatedTree = () => {
  const classes = atStyles();

  return (
    <Box className={classes.container}>
      <Tree name="main" defaultOpen>
        <Tree name="hello" />
        <Tree name="subtree with children">
          <Tree name="hello" />
          <Tree name="sub-subtree with children">
            <Tree name="child 1" style={{ color: '#37ceff' }} />
            <Tree name="child 2" style={{ color: '#37ceff' }} />
            <Tree name="child 3" style={{ color: '#37ceff' }} />
            <Tree name="custom content">
              <div
                style={{
                  position: 'relative',
                  width: '100%',
                  height: 200,
                  padding: 10,
                }}>
                <div
                  style={{
                    width: '100%',
                    height: '100%',
                    background: 'black',
                    borderRadius: 5,
                  }}
                />
              </div>
            </Tree>
          </Tree>
          <Tree name="hello" />
        </Tree>
        <Tree name="world" />
        <Tree name={<span><span role="img" aria-label="terrified">🙀</span>  something something</span>} />
      </Tree>
    </Box>
  );
}

export default AnimatedTree;
