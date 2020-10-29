function drawChart() {
	var ctx1 = document.getElementById('chart01').getContext('2d');
	var ctx2 = document.getElementById('chart02').getContext('2d');

	/* doughnutChart */
	var doughnutData = {
			label : '# of Votes',
			data : [ 12, 19, 3, 5 ],
			backgroundColor : [ 'rgba(88, 77, 228, 1.0)',
					'rgba(88, 77, 228, 0.6)', 'rgba(88, 77, 228, 0.2)',
					'rgba(0, 0, 0, 0.1)' ],
			borderWidth : 2
	}
	
	var doughnutOption = {
		animation : { /* 등장 애니메이션 */
			animateRotate : true,
			animateScale : true
		},
		legend : { /* 범례 옵션 */
			display : true,
			position : 'right',
			align : 'end',
			maintainAspectRatio : false, /* false = 차트 비율 부모 div 따름 */
			labels : {
				boxWidth : 10
			}
		}
	}
	
	var doughnutChart = new Chart(ctx1, {
		type : 'doughnut',
		data : {
			labels : [ '식품', '생활', '건강', '기타' ],
			datasets : [ doughnutData ]
		},
		options : doughnutOption
	});

	
	/* barChart */
	var barData = {
		data : [ 5427, 5243, 5514, 3933, 1326 ],
		backgroundColor : [ 
			'rgba(88, 77, 228, 1.0)', 
			'rgba(0, 0, 0, 0.1)',
			'rgba(0, 0, 0, 0.1)',
			'rgba(0, 0, 0, 0.1)',
			'rgba(0, 0, 0, 0.1)' ]
	};

	var barOption = {
		legend: {
	        display: false
	    },
		scales : {
			yAxes : [{
				barPercentage : 0.5,
				gridLines : {
					display:false
				}
			}],
			xAxes : [{
				 gridLines: {
			        display:false
				},
				ticks : { /* x축 범위 */
					min : 0,
					max : 6500,
					stepSize : 1300
				}
			}]
		},
		elements : {
			rectangle : {
				borderSkipped : 'left',
			}
		}
	};
	
	var barChart = new Chart(ctx2, {
		type : 'horizontalBar',
		data : {
			labels : [ "음식점", "미용", "의료기관", "식품제조", "체육" ],
			datasets : [ barData ],
		},
		options : barOption
	});
	
}
