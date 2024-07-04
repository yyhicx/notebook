import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';

import Home from './pages/home';
import NotFound from './pages/notfound';
// react
import FilterableProductTablePage from './pages/filterable-product-table-page';
import ContextExamplePage from './pages/context-example-page';
import PortalExamplePage from './pages/portal-example-page';
import ProfilerExamplePage from './pages/profiler-example-page';
import RenderPropsExamplePage from './pages/render-props-example-page';
// redux
import CounterPage from './pages/counter-page';
import TodosPage from './pages/todos-page';
// react-spring
import ReactSpringExamplePage from './pages/react-spring-example-page';
import AnimatedCardPage from './pages/animated-card-page';
import AnimatedTreePage from './pages/animated-tree-page';
import DraggableListPage from './pages/draggable-list-page';
import MasonryPage from './pages/masonry-page';

function App() {
  return (
    <Router>
      <Routes>
        <Route exact path="/" element={<Home />} />
        <Route
          path="/filterable-product-table"
          element={<FilterableProductTablePage />}
        />
        <Route path="/context-example" element={<ContextExamplePage />} />
        <Route path="/portal-example" element={<PortalExamplePage />} />
        <Route path="/profiler-example" element={<ProfilerExamplePage />} />
        <Route
          path="/render-props-example"
          element={<RenderPropsExamplePage />}
        />
        <Route path="/counter" element={<CounterPage />} />
        <Route path="/todos" element={<TodosPage />} />
        <Route
          path="/react-spring-example"
          element={<ReactSpringExamplePage />}
        />
        <Route path="/animated-card" element={<AnimatedCardPage />} />
        <Route path="/animated-tree" element={<AnimatedTreePage />} />
        <Route path="/draggable-list" element={<DraggableListPage />} />
        <Route path="/masonry" element={<MasonryPage />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </Router>
  );
}

export default App;
