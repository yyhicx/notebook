function _toConsumableArray(arr) {
  if (Array.isArray(arr)) {
    let arr2 = Array(arr.length);
    for (let i = 0; i < arr.length; i++) {
      arr2[i] = arr[i];
    }
    return arr2;
  } else {
    return Array.from(arr);
  }
}

function move(array, moveIndex, toIndex) {
  let item = array[moveIndex];
  let length = array.length;
  let diff = moveIndex - toIndex;

  if (diff > 0) {
    return [].concat(_toConsumableArray(array.slice(0, toIndex)), [item], _toConsumableArray(array.slice(toIndex, moveIndex)), _toConsumableArray(array.slice(moveIndex + 1, length)));
  } else if (diff < 0) {
    return [].concat(_toConsumableArray(array.slice(0, moveIndex)), _toConsumableArray(array.slice(moveIndex + 1, toIndex + 1)), [item], _toConsumableArray(array.slice(toIndex + 1, length)));
  }
  return array;
}

export default move;
