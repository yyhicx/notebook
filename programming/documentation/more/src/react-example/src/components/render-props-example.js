import React, { useState } from 'react';

import useMeasure from 'react-use-measure';

import CatImage from '../images/cat.png';

const imageWidth = 30;
const imageHeight = 30;

const Cat = (props) => {
  const [x, y] = props.mouse;

  return (
    <img src={CatImage} alt="cat" style={{ position: "absolute", left: x, top: y, width: imageWidth, height: imageHeight }} />
  );
}

const Mouse = (props) => {
  const [[x, y], setState] = useState([0, 0]);
  const [ref, { left, right, top, bottom }] = useMeasure();

  const handleMouseMove = (event) => {
    // setState([event.clientX, event.clientY]);
    let nextX = event.pageX;
    let nextY = event.pageY;

    if (nextX < left || nextX > (right - imageWidth)) nextX = x;
    if (nextY < top || nextY > (bottom - imageHeight)) nextY = y;

    setState([nextX, nextY]);
  }

  return (
    <div ref={ref} style={{ margin: "auto", width: "50vw" ,height: "50vh", border: "2px solid #ccc" }} onMouseMove={handleMouseMove}>
      {props.render([x, y])}
    </div>
  );
}

const MouseTracker = () => {
  return (
    <div style={{ textAlign: "center" }}>
      <h1>移动鼠标!</h1>
      <Mouse render={mouse => (
        <Cat mouse={mouse} />
      )}/>
    </div>
  );
}

const RenderPropsExample = () => {
  return <MouseTracker />;
}

export default RenderPropsExample;
