import React from 'react';

import { makeStyles } from '@mui/styles';

import groupingArray from '../utils/grouping-array';

const globalData = [
  {category: 'Sporting Goods', price: '$49.99', stocked: true, name: 'Football'},
  {category: 'Sporting Goods', price: '$9.99', stocked: true, name: 'Baseball'},
  {category: 'Sporting Goods', price: '$29.99', stocked: false, name: 'Basketball'},
  {category: 'Electronics', price: '$99.99', stocked: true, name: 'iPod Touch'},
  {category: 'Electronics', price: '$399.99', stocked: false, name: 'iPhone 5'},
  {category: 'Electronics', price: '$199.99', stocked: true, name: 'Nexus 7'}
];

/**
 * function component
 */

const useStyles = makeStyles({
  root: {
    width: '300px',
    margin: 'auto',
    paddingTop: '20px',
    paddingLeft: '50px',
  },
  searchBarInput: {
    height: '20px',
  },
  searchBarLabel: {
    fontSize: '0.9rem',
  },
  productTable: {
    marginTop: '10px',
  },
  productTableTitleSpan: {
    width: '100px',
    fontWeight: 'bold',
    display: 'inline-block',
  },
  productCategoryRow: {
    fontWeight: 'bold',
    paddingTop: '2px',
    paddingBottom: '2px',
  },
  productRow: {
    paddingTop: '2px',
    paddingBottom: '2px',
  },
  productRowSpan: {
    width: '100px',
    display: 'inline-block',
  },
});

const processingData = (searchObject, onlyShowStocked) => {
  let data = JSON.parse(JSON.stringify(globalData));
  if (searchObject !== '') {
    for (let i = 0; i < data.length; i++) {
      if (data[i].name.indexOf(searchObject) !== 0) {
        data.splice(i, 1);
        i--;
      }
    }
  }
  if (onlyShowStocked === true) {
    data.forEach(function (item, index, arr) {
      if (item.stocked === false) {
        arr.splice(index, 1);
      }
    });
  }

  data = groupingArray(data, 'category');

  return data;
}

const FilterableProductTable = () => {
  const [searchObject, setSearchObject] = React.useState('');
  const [onlyShowStocked, setOnlyShowStocked] = React.useState(false);

  let data = processingData(searchObject, onlyShowStocked);
  let keys = Object.keys(data);
  const classes = useStyles();

  const handleSearchObjectChange = (value) => {
    setSearchObject(value);
    handleDataChange();
  }

  const handleOnlyShowStockedChange = (checked) => {
    setOnlyShowStocked(checked);
    handleDataChange();
  }

  const handleDataChange = () => {
    data = processingData(searchObject, onlyShowStocked);
    keys = Object.keys(data);
  }

  return (
    <div className={classes.root}>
      <div className="searchbar">
        <input
          type="text" placeholder="Search..." value={searchObject} className={classes.searchBarInput}
          onChange={(event) => handleSearchObjectChange(event.target.value)}
        />
        <br />
        <label className={classes.searchBarLabel}>
          <input type="checkbox" checked={onlyShowStocked} onChange={(event) => handleOnlyShowStockedChange(event.target.checked)} />
          Only show products in stock
        </label>
      </div>
      <div className={classes.productTable}>
        <div className="product-table-title">
          <span className={classes.productTableTitleSpan}>Name</span>
          <span className={classes.productTableTitleSpan}>Price</span>
        </div>
        {keys.map(function (k, index) {
            return (
              <div key={index} className={classes.productCategoryRow}>
                {k}
                {
                  data[k].map(function (d, index) {
                    return (
                      <div key={index} className={classes.productRow}>
                        <span className={classes.productRowSpan} style={{ color: d.stocked ? 'black' : 'red' }} >{d.name}</span>
                        <span className={classes.productRowSpan}>{d.price}</span>
                      </div>
                    );
                  })
                }
              </div>
            );
          }
        )}
      </div>
    </div>
  );
};

/**
 * function component
 */

/**
 * class component
 */

// class ProductCategoryRow extends React.Component {
//   render() {
//     return (
//       <div className="product-category-row" style={{ fontWeight: 'bold', paddingTop: '2px', paddingBottom: '2px' }}>
//         {this.props.category}
//         {this.props.children}
//       </div>
//     );
//   }
// }

// class ProductRow extends React.Component {
//   render() {
//     const color = this.props.stocked ? 'black' : 'red';
//     return (
//       <div className="product-row" style={{ paddingTop: '2px', paddingBottom: '2px' }}>
//         <span style={{ width: '100px', display: 'inline-block', color: color }} >{this.props.name}</span>
//         <span style={{ width: '100px', display: 'inline-block' }}>{this.props.price}</span>
//       </div>
//     );
//   }
// }

// class ProductTable extends React.Component {
//   render() {
//     const data = this.props.data;
//     let keys = [];
//     for (let key in data) {
//       keys.push(key);
//     }
//     return (
//       <div className="product-table" style={{ marginTop: '10px' }} >
//         <div className="product-table-title">
//           <span style={{ width: '100px', fontWeight: 'bold', display: 'inline-block' }}>Name</span>
//           <span style={{ width: '100px', fontWeight: 'bold', display: 'inline-block' }}>Price</span>
//         </div>
//         {/* <ProductCategoryRow category="123" />
//         <ProductRow name="123" price="234" />
//         <ProductRow name="123" price="234" /> */}
//         {keys.map(function (k, index) {
//             return (
//               <ProductCategoryRow key={index} category={k}>
//               {
//                 data[k].map(function (d, index) {
//                   return (
//                     <ProductRow key={index} name={d.name} price={d.price} stocked={d.stocked} />
//                   );
//                 })
//               }
//               </ProductCategoryRow>
//             );
//           }
//         )}
//       </div>
//     );
//   }
// }

// class SearchBar extends React.Component {
//   constructor(props) {
//     super(props);
//     this.handleChange1 = this.handleChange1.bind(this);
//     this.handleChange2 = this.handleChange2.bind(this);
//   }

//   handleChange1(event) {
//     this.props.hsoc(event.target.value);
//   }

//   handleChange2(event) {
//     this.props.hossc(event.target.checked);
//   }

//   render() {
//     return (
//       <div className="searchbar">
//         <input
//           type="text" placeholder="Search..."
//           value={this.props.so} onChange={this.handleChange1}
//           style={{ height: '20px' }}
//         />
//         <br />
//         <label
//           className="searchbar-label"
//           style={{ fontSize: '0.9rem' }}
//         >
//           <input type="checkbox" checked={this.props.oss} onChange={this.handleChange2} />
//           Only show products in stock
//         </label>
//       </div>
//     );
//   }
// }

// class FilterableProductTable extends React.Component {
//   constructor(props) {
//     super(props);
//     this.state = {
//       searchObject: '',
//       onlyShowStocked: false
//     };
//     this.handleSearchObjectChange = this.handleSearchObjectChange.bind(this);
//     this.handleOnlyShowStockedChange = this.handleOnlyShowStockedChange.bind(this);
//   }

//   handleSearchObjectChange(value) {
//     this.setState({
//       searchObject: value
//     });
//   }

//   handleOnlyShowStockedChange(checked) {
//     this.setState({
//       onlyShowStocked: checked
//     });
//   }

//   render() {
//     let data = JSON.parse(JSON.stringify(globalData));
//     if (this.state.searchObject !== '') {
//       for (let i = 0; i < data.length; i++) {
//         if (data[i].name.indexOf(this.state.searchObject) !== 0) {
//           data.splice(i, 1);
//           i--;
//         }
//       }
//     }
//     if (this.state.onlyShowStocked === true) {
//       data.forEach(function (item, index, arr) {
//         if (item.stocked === false) {
//           arr.splice(index, 1);
//         }
//       });
//     }

//     data = groupingArray(data, 'category');
//     return (
//       <div
//         className="filterable-product-table"
//         style={{
//           width: '300px',
//           margin: 'auto',
//           paddingTop: '20px',
//         }}
//       >
//         <SearchBar so={this.searchObject} oss={this.onlyShowStocked}
//                    hsoc={this.handleSearchObjectChange} hossc={this.handleOnlyShowStockedChange} />
//         <ProductTable data={data} />
//       </div>
//     );
//   }
// }

/**
 * class component
 */

export default FilterableProductTable;
