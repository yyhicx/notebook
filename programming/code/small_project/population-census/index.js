let myChart = echarts.init(document.getElementById('main'));

let data;
$.get('http://localhost:8000/data.json', function (d) {
  data = d;
});

myChart.showLoading();
$.get('http://localhost:8000/china.json', function (chinaJson) {
  myChart.hideLoading();
  echarts.registerMap('China', chinaJson);

  let option = {
    title: {
      text: '中国人口普查 (2010)',
      subtext: '数据来自 www.stats.gov.cn',
      sublink: 'http://www.stats.gov.cn/tjsj/pcsj/rkpc/6rp/indexch.htm',
      left: 'right'
    },
    tooltip: {
      trigger: 'item',
      showDelay: 0,
      transitionDuration: 0.2,
      formatter: function (params) {
        const value = (params.value + '').split('.');
        const valueStr = value[0].replace(
          /(\d{1,3})(?=(?:\d{3})+(?!\d))/g,
          '$1,'
        );
        return params.seriesName + '<br/>' + params.name + ': ' + valueStr;
      }
    },
    visualMap: {
      left: 'right',
      min: 20000000,
      max: 110000000,
      inRange: {
        color: [
          '#313695',
          '#4575b4',
          '#74add1',
          '#abd9e9',
          '#e0f3f8',
          '#ffffbf',
          '#fee090',
          '#fdae61',
          '#f46d43',
          '#d73027',
          '#a50026'
        ]
      },
      text: ['High', 'Low'],
      calculable: true
    },
    toolbox: {
      show: true,
      // orient: 'vertical',
      left: 'left',
      top: 'top',
      feature: {
        dataView: { readOnly: false },
        restore: {},
        saveAsImage: {}
      }
    },
    series: [
      {
        name: '普查结果',
        type: 'map',
        roam: true,
        map: 'China',
        emphasis: {
          label: {
            show: true
          }
        },
        data: data['data']
      }
    ]
  };

  myChart.setOption(option);
});
