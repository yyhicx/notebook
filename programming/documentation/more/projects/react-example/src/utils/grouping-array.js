function groupingArray(list, field) {
  let obj = [];
  for (let i = 0; i < list.length; i++) {
    if (obj[list[i][field]] === undefined) {
      obj[list[i][field]] = [list[i]];
    } else {
      obj[list[i][field]].push(list[i]);
    }
  }

  for (let key1 in obj) {
    for (let key2 in obj[key1]) {
      delete obj[key1][key2].category;
    }
  }

  return obj;
}

export default groupingArray;

// Test function
// function test() {
//   const data = [
//     {category: "Sporting Goods", price: "$49.99", stocked: true, name: "Football"},
//     {category: "Sporting Goods", price: "$9.99", stocked: true, name: "Baseball"},
//     {category: "Sporting Goods", price: "$29.99", stocked: false, name: "Basketball"},
//     {category: "Electronics", price: "$99.99", stocked: true, name: "iPod Touch"},
//     {category: "Electronics", price: "$399.99", stocked: false, name: "iPhone 5"},
//     {category: "Electronics", price: "$199.99", stocked: true, name: "Nexus 7"}
//   ];

//   let obj = groupingArray(data, 'category');
//   console.log(obj);
// }
