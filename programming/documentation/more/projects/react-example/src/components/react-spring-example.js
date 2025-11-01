import React, { useState, useEffect } from 'react';
import {
  Card,
  CardContent,
  CssBaseline,
  Container,
  Typography,
  Grid,
} from '@mui/material';
import { makeStyles } from '@mui/styles';

import { useSpring, animated, config, easings } from 'react-spring';

import useMeasure from 'react-use-measure'; // 测量您引用的视图的边界（例如宽度、高度、顶部、左侧）

const AnimatedTypography = animated(Typography);

const Text = () => {
  const props = useSpring({
    to: { opacity: 1 },
    from: { opacity: 0 },
    reset: true,
    loop: { reverse: true },
    delay: 200,
    config: config.molasses,
  });

  return (
    <AnimatedTypography
      variant="h1"
      component="div"
      style={{ ...props, textAlign: 'center', fontWeight: 'bold' }}
    >
      hello
    </AnimatedTypography>
  );
};

const SVG = () => {
  const { x } = useSpring({
    reset: true,
    loop: { reverse: true },
    from: { x: 0 },
    x: 1,
    delay: 200,
    config: config.molasses,
  });

  return (
    <animated.svg
      style={{ margin: 20, width: 80, height: 80 }}
      viewBox="0 0 45 44"
      strokeWidth="2"
      fill="white"
      stroke="rgb(45, 55, 71)"
      strokeLinecap="round"
      strokeLinejoin="round"
      strokeDasharray={156}
      strokeDashoffset={x.to((x) => (1 - x) * 156)}
    >
      <polygon points="22.5 35.25 8.68704657 42.5118994 11.3250859 27.1309497 0.150171867 16.2381006 15.5935233 13.9940503 22.5 0 29.4064767 13.9940503 44.8498281 16.2381006 33.6749141 27.1309497 36.3129534 42.5118994" />
    </animated.svg>
  );
};

const Number = () => {
  const { number } = useSpring({
    reset: true,
    loop: { reverse: true },
    from: { number: 0 },
    number: 1,
    delay: 200,
    config: config.molasses,
  });

  return (
    <AnimatedTypography
      variant="h1"
      component="div"
      style={{ textAlign: 'center' }}
    >
      {number.to((n) => n.toFixed(2))}
    </AnimatedTypography>
  );
};

const Scrolling = () => {
  const words = ['We', 'came.', 'We', 'saw.', 'We', 'kicked', 'its', 'ass.'];

  const { scroll } = useSpring({
    scroll: (words.length - 1) * 50,
    from: { scroll: 0 },
    reset: true,
    loop: { reverse: true },
    delay: 200,
    config: config.molasses,
  });

  return (
    <animated.div
      style={{
        position: 'relative',
        width: '30%',
        height: 60,
        marginLeft: '100px',
        marginTop: '50px',
        overflow: 'auto',
        fontSize: '0.5em',
      }}
      scrollTop={scroll}
    >
      {words.map((word, i) => (
        <div
          key={`${word}_${i}`}
          style={{ width: '100%', height: 50, textAlign: 'center' }}
        >
          {word}
        </div>
      ))}
    </animated.div>
  );
};

const LoopTrue = () => {
  const styles = useSpring({
    loop: true,
    from: { rotateZ: 0 },
    to: { rotateZ: 180 },
  });

  return (
    <animated.div
      style={{
        width: 80,
        height: 80,
        backgroundColor: '#46e891',
        borderRadius: 16,
        ...styles,
      }}
    />
  );
};

const LoopObject = () => {
  const styles = useSpring({
    // loop: true,
    loop: { reverse: true },
    from: { x: 0 },
    to: { x: 100 },
  });

  return (
    <animated.div
      style={{
        width: 80,
        height: 80,
        backgroundColor: '#46e891',
        borderRadius: 16,
        ...styles,
      }}
    />
  );
};

const EasingComponent = () => {
  const { background, rotateZ } = useSpring({
    from: {
      background: '#46e891',
      rotateZ: 0,
    },
    to: {
      background: '#277ef4',
      rotateZ: 225,
    },
    config: {
      duration: 2000,
      easing: easings.easeInOutQuart,
    },
    loop: { reverse: true },
  });

  return (
    <animated.div
      style={{ background, width: 120, height: 120, borderRadius: 16, rotateZ }}
    />
  );
};

const BackwardsCompatability = () => {
  const { x, opacity } = useSpring({ x: -50, opacity: 1 });

  useEffect(() => {
    x.start({ from: 50, to: -50, loop: { reverse: true } });
  }, [x]);

  return (
    <animated.div
      style={{
        width: 80,
        height: 80,
        backgroundColor: '#46e891',
        borderRadius: 16,
        x,
        opacity,
      }}
    />
  );
};

const aaStyles = makeStyles({
  // Animating Auto Styles
  main: {
    position: 'relative',
    width: '200px',
    height: '50px',
    cursor: 'pointer',
    borderRadius: '5px',
    border: '2px solid #272727',
    overflow: 'hidden',
  },
  fill: {
    position: 'absolute',
    top: '0',
    left: '0',
    width: '100%',
    height: '100%',
    background: 'hotpink',
  },
  content: {
    position: 'absolute',
    top: '0',
    left: '0',
    width: '100%',
    height: '100%',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    color: '#272727',
  },
});

const AnimatingAuto = () => {
  const [open, toggle] = useState(false);
  const [ref, { width }] = useMeasure();
  const props = useSpring({ width: open ? width : 0 });
  const classes = aaStyles();

  return (
    <Container ref={ref} className={classes.main} onClick={() => toggle(!open)}>
      <animated.div className={classes.fill} style={props} />
      <animated.div className={classes.content}>
        {props.width.to((x) => x.toFixed(0))}
      </animated.div>
    </Container>
  );
};

const ReactSpringExample = () => {
  return (
    <React.Fragment>
      <CssBaseline />

      {/* Basic */}
      <Grid container spacing={2}>
        <Grid item xs={12}>
          <Typography
            variant="h2"
            component="div"
            sx={{ textAlign: 'center', fontWeight: 'bold' }}
          >
            Basic
          </Typography>
        </Grid>
        <Grid item xs={6} sx={{ minWidth: 350 }}>
          <Card
            sx={{
              mb: 2,
              width: 300,
              height: 200,
              mx: 'auto',
              bgcolor: '#eeffee',
            }}
          >
            <CardContent sx={{ paddingTop: '40px' }}>
              <Text />
            </CardContent>
          </Card>
        </Grid>
        <Grid item xs={6} sx={{ minWidth: 350 }}>
          <Card
            sx={{
              mb: 2,
              width: 300,
              height: 200,
              mx: 'auto',
              bgcolor: '#eeffee',
            }}
          >
            <CardContent sx={{ paddingLeft: '85px', paddingTop: '40px' }}>
              <SVG />
            </CardContent>
          </Card>
        </Grid>
        <Grid item xs={6} sx={{ minWidth: 350 }}>
          <Card
            sx={{
              mb: 2,
              width: 300,
              height: 200,
              mx: 'auto',
              bgcolor: '#eeffee',
            }}
          >
            <CardContent sx={{ paddingTop: '40px' }}>
              <Number />
            </CardContent>
          </Card>
        </Grid>
        <Grid item xs={6} sx={{ minWidth: 350 }}>
          <Card
            sx={{
              mb: 2,
              width: 300,
              height: 200,
              mx: 'auto',
              bgcolor: '#eeffee',
            }}
          >
            <CardContent>
              <Scrolling />
            </CardContent>
          </Card>
        </Grid>
      </Grid>

      {/* Props */}
      <Grid container spacing={2}>
        <Grid item xs={12}>
          <Typography
            variant="h2"
            component="div"
            sx={{ textAlign: 'center', fontWeight: 'bold' }}
          >
            Props
          </Typography>
        </Grid>
        <Grid item xs={6} sx={{ minWidth: 350 }}>
          <Card
            sx={{
              mb: 2,
              width: 300,
              height: 200,
              mx: 'auto',
              bgcolor: '#eeffee',
            }}
          >
            <CardContent sx={{ paddingLeft: '105px', paddingTop: '60px' }}>
              <LoopTrue />
            </CardContent>
          </Card>
        </Grid>
        <Grid item xs={6} sx={{ minWidth: 350 }}>
          <Card
            sx={{
              mb: 2,
              width: 300,
              height: 200,
              mx: 'auto',
              bgcolor: '#eeffee',
            }}
          >
            <CardContent sx={{ paddingLeft: '60px', paddingTop: '60px' }}>
              <LoopObject />
            </CardContent>
          </Card>
        </Grid>
        <Grid item xs={6} sx={{ minWidth: 350 }}>
          <Card
            sx={{
              mb: 2,
              width: 300,
              height: 200,
              mx: 'auto',
              bgcolor: '#eeffee',
            }}
          >
            <CardContent sx={{ paddingLeft: '90px', paddingTop: '40px' }}>
              <EasingComponent />
            </CardContent>
          </Card>
        </Grid>
      </Grid>

      {/* Imperatives & Refs */}
      <Grid container spacing={2}>
        <Grid item xs={12}>
          <Typography
            variant="h2"
            component="div"
            sx={{ textAlign: 'center', fontWeight: 'bold' }}
          >
            Imperatives & Refs
          </Typography>
        </Grid>
        <Grid item xs={6} sx={{ minWidth: 350 }}>
          <Card
            sx={{
              mb: 2,
              width: 300,
              height: 200,
              mx: 'auto',
              bgcolor: '#eeffee',
            }}
          >
            <CardContent sx={{ paddingLeft: '105px', paddingTop: '60px' }}>
              <BackwardsCompatability />
            </CardContent>
          </Card>
        </Grid>
      </Grid>

      {/* Hooks */}
      <Grid container spacing={2}>
        <Grid item xs={12}>
          <Typography
            variant="h2"
            component="div"
            sx={{ textAlign: 'center', fontWeight: 'bold' }}
          >
            Hooks
          </Typography>
        </Grid>
        <Grid item xs={6} sx={{ minWidth: 350 }}>
          <Card
            sx={{
              mb: 2,
              width: 300,
              height: 200,
              mx: 'auto',
              bgcolor: '#eeffee',
            }}
          >
            <CardContent sx={{ paddingLeft: '20px', paddingTop: '70px' }}>
              <AnimatingAuto />
            </CardContent>
          </Card>
        </Grid>
      </Grid>
    </React.Fragment>
  );
};

export default ReactSpringExample;
