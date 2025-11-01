import React from 'react';

import { Button } from '@mui/material';

import { lightTheme, darkTheme } from '../theme/theme';
import { ThemeProvider } from '@mui/material/styles';

const ContextExample = () => {
  const [isDark, setIsDark] = React.useState(true);

  const handleThemeChange = () => {
    setIsDark(!isDark);
  };

  return (
    <ThemeProvider theme={isDark ? darkTheme : lightTheme}>
      <Toolbar func={handleThemeChange} />
    </ThemeProvider>
  );
};

function Toolbar(props) {
  return (
    <div>
      <ThemeButton func={props.func} />
    </div>
  );
}

const ThemeButton = (props) => {
  return (
    <Button onClick={props.func} variant="outlined">
      Outlined
    </Button>
  );
};

export default ContextExample;
