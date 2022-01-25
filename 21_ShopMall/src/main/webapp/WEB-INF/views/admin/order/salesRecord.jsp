<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="../sub_menu.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
<%-- AJAX 통신 설정--%>
	$.ajax({
			type : 'GET',
			headers : {
				Accept : "application/json; charset=utf-8",
				"Content-type" : "application/json; charset=utf-8"
			},
			url : 'sales_record_chart',
			success : function(result) { // result : controller 에서 전달된 데이터 전달

				// 최신 버전의 구글 코어차트 패키지를 메모리로 로드
				google.charts.load('current', {
					'packages' : [ 'corechart' ]
				});

				// 차트 API가 로드가 완료되었을 때 아래의 콜백함수 실행(이벤트로 처리)
				// 차트 그리는 함수 호출
				google.charts.setOnLoadCallback(function() {
					drawChart(result);
				})
			},
			error : function() {
				alert("Chart drawing error!");
			}
		})
	});

	function drawChart(result) {

		// 차트를 그리는데 사용할 데이터 객체 생성
		var data = new google.visualization.DataTable();

		data.addColumn("string", "pname");
		data.addColumn("number", "quantity");

		// 콘트롤러에서 전달된 json 타입의 데이터를 JS 배열로 변경
		var dataArray = [];
		$.each(result, function(i, obj) { // json 배열 타입의 데이터를 항목별로 반복
			dataArray.push([ obj.pname, obj.quantity ]);
		})

		// data 객체에 dataArray의 내용을 저장
		data.addRows(dataArray);

		// 차트 그리기 옵션 지정
		var piechart_options = {
			title : '제품별 판매 실적',
			width : 300,
			height : 300
		};

		// 차트 종류와 옵션을 설정
		var piechart = new google.visualization.PieChart(document
				.getElementById('piechart_div'));

		piechart.draw(data, piechart_options);

		// 바 차트 그리기 옵션 지정
		var barchart_options = {
			title : '제품별 판매 실적',
			width : 300,
			height : 300
		}
		
		// 바 종류와 옵션을 설정
		var barchart = new google.visualization.BarChart(document
				.getElementById('barchart_div'));

		barchart.draw(data, barchart_options);
	}
</script>
<div align="center">
	<h1>제품별 판매 실적</h1>
	<table>
		<tr>
			<td><div id="piechart_div" style="boarder: 1px solid #cc"></div></td>
			<td><div id="barchart_div" style="boarder: 1px solid #cc"></div></td>
		</tr>

	</table>
</div>
</body>

<%@ include file="../footer.jsp"%>
</html>