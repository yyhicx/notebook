import { createTheme } from '@mui/material/styles';

export const lightTheme = createTheme({
  palette: {
    type: 'light',
    primary: {
      main: '#eeeeee',
    },
    secondary: {
      main: '#ffffff',
    },
  },
});

export const darkTheme = createTheme({
  palette: {
    type: 'dark',
    primary: {
      main: '#222222',
    },
    secondary: {
      main: '#000000',
    },
  },
});
