import React from 'react';
import {
  Box,
  Drawer,
  CssBaseline,
  Toolbar,
  List,
  Typography,
  Divider,
  IconButton,
  ListItem,
  ListItemText,
} from '@mui/material';
import MuiAppBar from '@mui/material/AppBar';
import MenuIcon from '@mui/icons-material/Menu';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import ChevronRightIcon from '@mui/icons-material/ChevronRight';
import { styled, useTheme } from '@mui/material/styles';
import { Link } from 'react-router-dom';

const drawerWidth = 240;

const pages = {
  React: [
    'FilterableProductTable',
    'ContextExample',
    'PortalExample',
    'ProfilerExample',
    'RenderPropsExample',
  ],
  Redux: ['Counter', 'Todos'],
  Axios: ['Test2'],
  'React-Spring': [
    'ReactSpringExample',
    'AnimatedCard',
    'AnimatedTree',
    'DraggableList',
    'Masonry',
  ],
};

const pageLinks = {
  FilterableProductTable: '/filterable-product-table',
  ContextExample: '/context-example',
  PortalExample: '/portal-example',
  ProfilerExample: '/profiler-example',
  RenderPropsExample: '/render-props-example',
  Counter: '/counter',
  Todos: '/todos',
  Test2: 'test2',
  ReactSpringExample: '/react-spring-example',
  AnimatedCard: '/animated-card',
  AnimatedTree: '/animated-tree',
  DraggableList: '/draggable-list',
  Masonry: '/masonry',
};

const Main = styled('main', { shouldForwardProp: (prop) => prop !== 'open' })(
  ({ theme, open }) => ({
    flexGrow: 1,
    padding: theme.spacing(3),
    transition: theme.transitions.create('margin', {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen,
    }),
    marginLeft: `-${drawerWidth}px`,
    ...(open && {
      transition: theme.transitions.create('margin', {
        easing: theme.transitions.easing.easeOut,
        duration: theme.transitions.duration.enteringScreen,
      }),
      marginLeft: 0,
    }),
  })
);

const AppBar = styled(MuiAppBar, {
  shouldForwardProp: (prop) => prop !== 'open',
})(({ theme, open }) => ({
  transition: theme.transitions.create(['margin', 'width'], {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.leavingScreen,
  }),
  ...(open && {
    width: `calc(100% - ${drawerWidth}px`,
    marginLeft: `${drawerWidth}px`,
    transition: theme.transitions.create(['margin', 'width'], {
      easing: theme.transitions.easing.easeOut,
      duration: theme.transitions.duration.enteringScreen,
    }),
  }),
}));

const DrawerHeader = styled('div')(({ theme }) => ({
  display: 'flex',
  alignItems: 'center',
  padding: theme.spacing(0, 1),
  // necessary for content to be below app bar
  ...theme.mixins.toolbar,
  justifyContent: 'flex-end',
}));

const Layout = (props) => {
  const theme = useTheme();
  const [open, setOpen] = React.useState(false);

  const handleDrawerOpen = () => {
    setOpen(true);
  };

  const handleDrawerClose = () => {
    setOpen(false);
  };

  return (
    <Box sx={{ display: 'flex' }}>
      <CssBaseline />
      <AppBar position="fixed" open={open}>
        <Toolbar>
          <IconButton
            size="large"
            aria-label="open drawer"
            onClick={handleDrawerOpen}
            color="inherit"
            edge="start"
            sx={{ mr: 2, ...(open && { display: 'none' }) }}
          >
            <MenuIcon />
          </IconButton>
          <Typography
            color="inherit"
            variant="h6"
            noWrap
            component={Link}
            to="/"
            sx={{ textDecoration: 'none', boxShadow: 'none' }}
          >
            {props.name}
          </Typography>
        </Toolbar>
      </AppBar>
      <Drawer
        sx={{
          width: drawerWidth,
          flexShrink: 0,
          '& .MuiDrawer-paper': {
            width: drawerWidth,
            boxSizing: 'border-box',
          },
        }}
        variant="persistent"
        anchor="left"
        open={open}
      >
        <DrawerHeader>
          <IconButton onClick={handleDrawerClose}>
            {theme.direction === 'ltr' ? (
              <ChevronLeftIcon />
            ) : (
              <ChevronRightIcon />
            )}
          </IconButton>
        </DrawerHeader>
        {/* <Divider />
        <Box sx={{ ml: 2, mt: 1 }}>
          <Typography
            color="inherit"
            variant="h6"
            noWrap
            component="div"
            sx={{ textDecoration: "none", boxShadow: "none" }}
          >
            Home
          </Typography>
        </Box>
        <List>
          {['Inbox', 'Starred'].map((text, index) => (
            <ListItem button key={text}>
              <ListItemIcon>
                {index % 2 === 0 ? <InboxIcon /> : <MailIcon />}
              </ListItemIcon>
              <ListItemText primary={text} />
            </ListItem>
          ))}
        </List> */}
        {Object.keys(pages).map(function (k) {
          return (
            <React.Fragment key={k}>
              <Divider />
              <Box key={k} sx={{ ml: 2, mt: 1 }}>
                <Typography
                  color="inherit"
                  variant="h6"
                  noWrap
                  component="div"
                  sx={{ textDecoration: 'none', boxShadow: 'none' }}
                >
                  {k}
                </Typography>
              </Box>
              <List>
                {pages[k].map(function (p, index) {
                  return (
                    <ListItem button key={p} component={Link} to={pageLinks[p]}>
                      <ListItemText primary={p} />
                    </ListItem>
                  );
                })}
              </List>
            </React.Fragment>
          );
        })}
      </Drawer>
      <Main open={open}>
        <DrawerHeader />
        {props.children}
      </Main>
    </Box>
  );
};

export default Layout;
