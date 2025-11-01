import React from 'react';
import { makeStyles } from '@mui/styles';

import Layout from '../components/layout';

const useStyles = makeStyles({
  root: {
    width: '400px',
    height: '100px',
    margin: 'auto',
    paddingTop: '40px',
    textAlign: 'center',
    fontSize: '2rem',
  },
});

const NotFound = () => {
  const classes = useStyles();
  return (
    <Layout name="Not Found">
      <div className={classes.root}>404: Page Not Found</div>
    </Layout>
  );
};

export default NotFound;
