import React from 'react';

import Layout from '../components/layout';

import Todos from '../components/todos';

const TodosPage = () => {
  return (
    <Layout name="Todos">
      <Todos />
    </Layout>
  );
};

export default TodosPage;
