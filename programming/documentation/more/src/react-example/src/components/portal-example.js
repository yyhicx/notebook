import React from 'react';
import ReactDOM from 'react-dom';

import { makeStyles } from '@mui/styles';

const useStyles = makeStyles({
  appRoot: {
    height: '13rem',
    width: '10rem',
    background: 'lightblue',
    overflow: 'hidden',
  },
  modalRoot: {
    position: 'relative',
    zIndex: '999',
  },
  modal: {
    backgroundColor: 'rgba(0, 0, 0, 0.5)',
    position: 'fixed',
    height: '100%',
    width: '100%',
    top: 0,
    left: 0,
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
  }
});

const PortalExample = () => {
  const [clicks, setClicks] = React.useState(0);

  const handleClick = () => {
    setClicks(clicks + 1);
  }

  const classes = useStyles();

  const appRoot = React.useRef(null);
  const modalRoot = React.useRef(null);

  const el = document.createElement('div');

  React.useEffect(() => {
    modalRoot.current.appendChild(el);
    // return () => {
    //   modalRoot.current.removeChild(el);
    // }
  });

  return (
    <>
      <div id="app-root" ref={appRoot} className={classes.appRoot}>
        <div onClick={handleClick}>
          <p>Number of clicks: {clicks}</p>
          <p>
            Open up the browser DevTools
            to observe that the button
            is not a child of the div
            with the onClick handler.
          </p>
          {ReactDOM.createPortal(
            <div className={classes.modal}>
              <button>Click</button>
            </div>, el
          )}
        </div>
      </div>
      <div id="modal-root" ref={modalRoot} className={classes.modalRoot}></div>
    </>
  );
}

export default PortalExample;
