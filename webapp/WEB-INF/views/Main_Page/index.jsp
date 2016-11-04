<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>main_index</title>
<link href="/gs25/assets/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/gs25/assets/js/jquery/jquery-1.9.0.js"></script>
<!-- 지도 -->
 <script>
$(function(){
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = { 
	    center: new daum.maps.LatLng(37.5547992, 126.9684953),
	    level: 4 // 지도의 확대 레벨
	};
	var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	
	$("#maptab1").click(function (){
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = { 
		    center: new daum.maps.LatLng(37.5547992, 126.9684953),
		    level: 4 // 지도의 확대 레벨
		};
		var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		
		var positions = [
          	<c:forEach var = 'vo' items='${mapvo }' varStatus='s'>
          	{
          	   content: '${vo.name}', 
          	   latlng: new daum.maps.LatLng('${vo.localx}', '${vo.localy}')
          	},
          	</c:forEach>
          ];
          for (var i = 0; i < positions.length; i ++) {
              // 마커를 생성합니다
          	  var marker = new daum.maps.Marker({
          	        map: map, // 마커를 표시할 지도
          	        position: positions[i].latlng // 마커의 위치
          	        
          	    });
          	    // 마커에 표시할 인포윈도우를 생성합니다 
          	    var infowindow = new daum.maps.InfoWindow({
          	        content: positions[i].content // 인포윈도우에 표시할 내용
          	    });
          	    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
          	    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
          	    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
          	    daum.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
          	    daum.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
          	}
	});
	
	$("#maptab2").click(function (){
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = { 
		    center: new daum.maps.LatLng(37.3800181, 126.9264755),
		    level: 4 // 지도의 확대 레벨
		};
		var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		
		var positions = [
           	<c:forEach var = 'vo' items='${mapvo }' varStatus='s'>
           	{
           	   content: '${vo.name}', 
           	   latlng: new daum.maps.LatLng('${vo.localx}', '${vo.localy}')
           	},
           	</c:forEach>
           ];
           for (var i = 0; i < positions.length; i ++) {
               // 마커를 생성합니다
           	  var marker = new daum.maps.Marker({
           	        map: map, // 마커를 표시할 지도
           	        position: positions[i].latlng // 마커의 위치
           	        
           	    });
           	    // 마커에 표시할 인포윈도우를 생성합니다 
           	    var infowindow = new daum.maps.InfoWindow({
           	        content: positions[i].content // 인포윈도우에 표시할 내용
           	    });
           	    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
           	    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
           	    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
           	    daum.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
           	    daum.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
           	}
	});
	
	$("#maptab3").click(function (){
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = { 
		    center: new daum.maps.LatLng(37.503463, 126.72378),
		    level: 4// 지도의 확대 레벨
		};
		var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		
		var positions = [
         	<c:forEach var = 'vo' items='${mapvo }' varStatus='s'>
         	{
         	   content: '${vo.name}', 
         	   latlng: new daum.maps.LatLng('${vo.localx}', '${vo.localy}')
         	},
         	</c:forEach>
         ];
         for (var i = 0; i < positions.length; i ++) {
             // 마커를 생성합니다
         	  var marker = new daum.maps.Marker({
         	        map: map, // 마커를 표시할 지도
         	        position: positions[i].latlng // 마커의 위치
         	        
         	    });
         	    // 마커에 표시할 인포윈도우를 생성합니다 
         	    var infowindow = new daum.maps.InfoWindow({
         	        content: positions[i].content // 인포윈도우에 표시할 내용
         	    });
         	    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
         	    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
         	    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
         	    daum.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
         	    daum.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
         	}
	});
		
//마커를 표시할 위치와 title 객체 배열입니다 
var positions = [
	<c:forEach var = 'vo' items='${mapvo }' varStatus='s'>
	{
	   content: '${vo.name}', 
	   latlng: new daum.maps.LatLng('${vo.localx}', '${vo.localy}')
	},
	</c:forEach>
];
for (var i = 0; i < positions.length; i ++) {
    // 마커를 생성합니다
	  var marker = new daum.maps.Marker({
	        map: map, // 마커를 표시할 지도
	        position: positions[i].latlng // 마커의 위치
	        
	    });
	    // 마커에 표시할 인포윈도우를 생성합니다 
	    var infowindow = new daum.maps.InfoWindow({
	        content: positions[i].content // 인포윈도우에 표시할 내용
	    });
	    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
	    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
	    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
	    daum.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
	    daum.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
	}
// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
function makeOverListener(map, marker, infowindow) {
    return function() {
        infowindow.open(map, marker);
    };
}
// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
function makeOutListener(infowindow) {
    return function() {
        infowindow.close();
    };
}
});
</script>
</head>
<body>

<div id="container">
<jsp:include page="/WEB-INF/views/include/header.jsp" />

<div id="content">

<div class="container_index">
		<img src="/gs25/assets/images/index/gs25_03.gif"/>
		
		<jsp:include page="/WEB-INF/views/include/main_menubar.jsp" />
		
		<div class="market_list">
		<div class="markey_list_sub">
			<ul class="market_list_area">
				<li><a href="#" class="on">서울</a></li>
				<li><a href="#">경기</a></li>
				<li><a href="#">인천</a></li>
				<li><a href="#">강원</a></li>
				<li><a href="#">대전/충청</a></li>
				<li><a href="#">광주/전라</a></li>
				<li><a href="#">대구/울산/부산</a></li>
				<li><a href="#">경상</a></li>
				<li><a href="#">제주</a></li>
			</ul>

			<ul class="market_list_point">
				<li><a href="/gs25/sub/main?store_no=4" class="on">지점1</a></li>
				<script language="JavaScript">
					for (i = 0; i < 27; i++) {
						document.write("<li><a href='#'>지점"+i+"</a></li>")
					}
				</script>
			</ul>


			</div>
		</div>
	</div>	


</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />

</div>
</body>
<script type= "text/javascript">

$(function() {
	 
	 $( window ).scroll( function() {
        if ( $( this ).scrollTop() > 200 ) {
          $( '.top' ).fadeIn();
        } else {
          $( '.top' ).fadeOut();
        }
      } );
      $( '.top' ).click( function() {
        $( 'html, body' ).animate( { scrollTop : 0 }, 400 );
        return false;
      } );
      
      
}); 

</script>
</html>