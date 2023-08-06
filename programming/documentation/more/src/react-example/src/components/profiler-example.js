import React, { useState } from 'react';
import {
  Box,
  Card,
  CardMedia,
  CardContent,
  CardActions,
  Container,
  CssBaseline,
  Typography,
  Stack,
  Pagination,
} from '@mui/material';
import { createTheme, ThemeProvider } from '@mui/material/styles';

import { animated, useTransition } from "react-spring";

const colors = {
  bg100: '#e6e6e6',
  text100: '#e6e6e6',
  text500: '39393a',
  brand: '#2d2dff',
};

const sliderHeight = 350;

const slides = [
  {
    url: 'https://images.pexels.com/photos/417173/pexels-photo-417173.jpeg',
    title: 'Lorem ipsum dolor sit amet',
    text:
      'Consectetur adipiscing elit. Cras euismod scelerisque nisl id viverra.',
  },
  {
    url: 'https://images.pexels.com/photos/2835562/pexels-photo-2835562.jpeg',
    title: 'Mauris eget tincidunt metus',
    text:
      'Nec tempor mi. Aenean euismod nunc ligula, ut tincidunt elit pretium at. Integer a molestie purus. Aliquam erat volutpat. ',
  },
  {
    url: 'https://images.pexels.com/photos/300857/pexels-photo-300857.jpeg',
    title: 'Nullam viverra est',
    text:
      'Vitae pulvinar tincidunt. Donec vel magna nunc. Cras pharetra cursus lectus, a pharetra orci iaculis non.',
  },
  {
    url: 'https://images.pexels.com/photos/346529/pexels-photo-346529.jpeg',
    title: 'Sed accumsan',
    text:
      'Condimentum leo, vel fermentum massa pulvinar vitae. Nam a felis libero. Fusce pellentesque dignissim finibus.',
  },
  {
    url: 'https://images.pexels.com/photos/2356087/pexels-photo-2356087.jpeg',
    title: 'Praesent varius',
    text:
      'Massa vitae ornare vestibulum, ligula elit ultrices mi, nec scelerisque odio nulla ut eros.',
  }
];

const theme = createTheme({
  palette: {
    background: {
      default: colors.bg100,
    },
  }
});

const Carousel = () => {
  const [[index, dir], setIndex] = useState([0, 0]);

  const handleChange = (event, value) => {
    const v = value - 1;
    setIndex([v, v > index ? 1 : -1]);
  };

  const transitions = useTransition(slides[index], {
    from: {
      opacity: 0,
      transform: `translate3d(${dir === 1 ? 100 : -100}%,0,0) scale(0.5)`,
    },
    enter: {
      opacity: 1,
      transform: 'translate3d(0%,0,0) scale(1)',
    },
    leave: {
      opacity: 0,
      transform: `translate3d(${dir === 1 ? -100 : 100}%,0,0) scale(0.5)`,
    },
    exitBeforeEnter: true,
  });

  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <Container>
        <Typography variant="h4" gutterBottom component="div" sx={{ textAlign: "center" }}>
          React Spring Example - useTransition
        </Typography>
        <Box sx={{ position: "relative" }}>
          {transitions(( styles, item ) =>
            item && <animated.div style={{ opacity: styles.opacity, transform: styles.transform }} key={item.title}>
              <Card sx={{ minWidth: "600px" }}>
                <CardMedia
                  component="img"
                  height={sliderHeight}
                  sx={{
                    backgroundImage: `url(${item.url}?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940)`,
                    backgroundSize: "100% 100%",
                  }}
                />
                <CardContent sx={{ paddingBottom: 0 }}>
                  <Typography gutterBottom variant="h5" component="div">
                    {item.title}
                  </Typography>
                  <Typography variant="body2" color="text.secondary">
                    {item.text}
                  </Typography>
                </CardContent>
                <CardActions sx={{ margin: "auto", width: "300px" }}>
                  <Stack spacing={2}>
                    <Pagination count={5} page={index + 1} onChange={handleChange} />
                  </Stack>
                </CardActions>
              </Card>
            </animated.div>
          )}
        </Box>
      </Container>
    </ThemeProvider>
  );
}

function onRenderCallback(
  id,  // 发生提交的 Profiler 树的 “id”
  phase,  // "mount" （如果组件树刚加载） 或者 "update" （如果它重渲染了）之一
  actualDuration,  // 本次更新 committed 花费的渲染时间
  baseDuration,  // 估计不使用 memoization 的情况下渲染整颗子树需要的时间
  startTime,  // 本次更新中 React 开始渲染的时间
  commitTime,  // 本次更新中 React committed 的时间
  interactions  // 属于本次更新的 interactions 的集合
) {
  // 合计或记录渲染时间。。。
  console.log(id);
  console.log(phase);
  console.log(actualDuration);
  console.log(baseDuration);
  console.log(startTime);
  console.log(commitTime);
  console.log(interactions);
}

const ProfilerExample = () => {
  return (
    <React.Profiler id="Carousel" onRender={onRenderCallback}>
      <Carousel />
    </React.Profiler>
  );
}

export default ProfilerExample;
