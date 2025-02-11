<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>나의 학습현황</title>
<jsp:include page="/WEB-INF/views/layout/headerResources.jsp"/>
</head>
<body>
<header>
    <jsp:include page="/WEB-INF/views/layout/studentHeader.jsp"/>
</header>
<main>
<div class="breadcrumb">
  <div class="sub-breadcrumb">
  <div class="sub-sub-breadcrumb">
    <span>></span>
	<a href="#" class="font-size small fw-bold">학습현황</a> 	
  </div>
  </div>
</div>

<div class="container grade-container">
  <div class="body-container">
<div class="body-title">
  <h3 class="me-5"><i class="bi bi-book"></i> 나의 학습현황</h3>
</div>

<div class="body-main">
		    	<div class="row g-1 mt-1 p-1">
					<div class="col-4 p-2">
						<div class="fs-6 fw-semibold mb-2"><i class="bi bi-chevron-right"></i> </div>
						<div class="border rounded p-5 text-center">
							<div class="fs-5 mb-2">
								<span class="product-totalAmount fw-semibold text-primary">${today.COUNT}</span>
							</div>
							<div class="fs-5"> 
								<span class="product-totalAmount fw-semibold text-danger"><fmt:formatNumber value="${today.TOTAL}"/></span>
							</div>
						</div>
					</div>
					
					<div class="col-4 p-2">
						<div class="fs-6 fw-semibold mb-2"><i class="bi bi-chevron-right"></i> </div>
						<div class="border rounded p-5 text-center">
							<div class="fs-5 mb-2">
								<span class="product-totalAmount fw-semibold text-primary">${thisMonth.COUNT}</span>
							</div>
							<div class="fs-5">
								<span class="product-totalAmount fw-semibold text-danger"><fmt:formatNumber value="${thisMonth.TOTAL}"/></span>
							</div>
						</div>
					</div>
		    	
					<div class="col-4 p-2">
						<div class="fs-6 fw-semibold mb-2"><i class="bi bi-chevron-right"></i> </div>
						<div class="border rounded p-5 text-center">
							<div class="fs-5 mb-2">
								<span class="product-totalAmount fw-semibold text-primary">${previousMonth.COUNT}</span>
							</div>
							<div class="fs-5">
								<span class="product-totalAmount fw-semibold text-danger"><fmt:formatNumber value="${previousMonth.TOTAL}"/></span>
							</div>
						</div>
					</div>
		    	</div>
		    
				<div class="row mt-3 p-1">
					<div class="col-4 p-2">
						<div class="fs-6 fw-semibold mb-2"><i class="bi bi-chevron-right"></i> </div>
						<div class="charts-day border rounded" style="height: 430px; width: 100%;"></div>
					</div>
					<div class="col-4 p-2">
						<div class="fs-6 fw-semibold mb-2 "><i class="bi bi-chevron-right"></i> <label class="charts-dayOfWeek-title"> </label></div>
						<div class="charts-dayOfWeek border rounded" style="height: 430px; width: 100%;"></div>
					</div>
					<div class="col-4 p-2">
						<div class="fs-6 fw-semibold mb-2"><i class="bi bi-chevron-right"></i> </div>
						<div class="charts-month border rounded" style="height: 430px; width: 100%;"></div>
					</div>
				</div>
</div>
  </div>
</div>
</main>

<script src="https://cdnjs.cloudflare.com/ajax/libs/echarts/5.6.0/echarts.min.js"></script>
<script type="text/javascript">
$(function(){
    // JSON 데이터를 반환하는 엔드포인트로 URL 변경
    let url = '${pageContext.request.contextPath}/student/mystudy/data';
    $.getJSON(url, function(data) {
        chartsDay(data);
        chartsDayOfWeek(data);
        chartsMonth(data);
    });
});

function chartsDay(data) {
    let chartData = [];
    
    for(let item of data.days) {
        let s = parseInt(item.ORDERDATE.substring(5,7)) + '월 ' + parseInt(item.ORDERDATE.substring(8)) + '일 ';
        let obj = { value: item.TOTALMONEY, name: s };
        chartData.push(obj);
    }
    
    var chartDom = document.querySelector('.charts-day');
    var myChart = echarts.init(chartDom);
    var option = {
        tooltip: {
            trigger: 'item'
        },
        legend: {
            top: '5%',
            left: 'center'
        },
        series: [
            {
                name: '1주간 취득학점',
                type: 'pie',
                radius: ['40%', '70%'],
                avoidLabelOverlap: false,
                itemStyle: {
                    borderRadius: 10,
                    borderColor: '#fff',
                    borderWidth: 2
                },
                label: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: 40,
                        fontWeight: 'bold'
                    }
                },
                labelLine: {
                    show: false
                },
                data: chartData
            }
        ]
    };
    myChart.setOption(option);
}

function chartsDayOfWeek(data) {
    let chartData = [];
    
    chartData.push(data.dayOfWeek.SUN);
    chartData.push(data.dayOfWeek.MON);
    chartData.push(data.dayOfWeek.TUE);
    chartData.push(data.dayOfWeek.WED);
    chartData.push(data.dayOfWeek.THU);
    chartData.push(data.dayOfWeek.FRI);
    chartData.push(data.dayOfWeek.SAT);
    
    let m = new Date().getMonth() + 1;
    let m2 = parseInt(data.dayOfWeek.month.substring(4));
    
    let title = (m !== m2) ? '' : '';
    document.querySelector('.charts-dayOfWeek-title').innerText = title;
    
    var chartDom = document.querySelector('.charts-dayOfWeek');
    var myChart = echarts.init(chartDom);
    var option = {
        xAxis: {
            type: 'category',
            data: ['일', '월', '화', '수', '목', '금', '토']
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                data: chartData,
                type: 'bar'
            }
        ]
    };
    myChart.setOption(option);
}

function chartsMonth(data) {
    let chartData = [];
    
    for(let item of data.months) {
        let s = parseInt(item.ORDERDATE.substring(4)) + ' 월';
        let obj = { value: item.TOTALMONEY, name: s };
        chartData.push(obj);
    }
    
    var chartDom = document.querySelector('.charts-month');
    var myChart = echarts.init(chartDom);
    var option = {
        tooltip: {
            trigger: 'item'
        },
        legend: {
            top: '5%',
            left: 'center'
        },
        series: [
            {
                name: '월별 판매현황',
                type: 'pie',
                radius: ['40%', '70%'],
                avoidLabelOverlap: false,
                itemStyle: {
                    borderRadius: 10,
                    borderColor: '#fff',
                    borderWidth: 2
                },
                label: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: 40,
                        fontWeight: 'bold'
                    }
                },
                labelLine: {
                    show: false
                },
                data: chartData
            }
        ]
    };
    myChart.setOption(option);
}
</script>
<footer class="w-100 bg-light.bg-gradient fw-lighter text-dark font-size small">
    <jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
</footer>

<jsp:include page="/WEB-INF/views/layout/footerResources.jsp"/>
</body>
</html>