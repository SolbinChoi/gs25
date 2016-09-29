<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/gs25/assets/css/eventboard.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/gs25/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>
function getThumbnailPrivew(html, $target) {
    if (html.files && html.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $target.css('display', '');
            //$target.css('background-image', 'url(\"' + e.target.result + '\")'); // 배경으로 지정시
            $target.html('<img src="' + e.target.result + '" border="0" alt="" />');
        }
        reader.readAsDataURL(html.files[0]);
    }
}
</script>
</head>
<body>
<div id="container">
<jsp:include page="/WEB-INF/views/include/header.jsp" />
<div id="content">
	<div id="event_main">
		<div id="event_sub">
			<div id="brand_main">
				<h1>이벤트</h1>
			</div>
			<div id="board">
			<form class="board-form" method="post" autocomplete="off"
						action="/gs25/Sub_Page/event_write?userno=${authUser.no }"
						enctype="multipart/form-data">
			<table class="tbl-ex">
			<tr>
				<th colspan="2">이벤트 글쓰기</th>
			</tr>
			
			<tr>
				<td class="label">제목</td>
				<td><input type="text" name="title" value=""></td>
			</tr>
			
			<tr>
				<td class="label">시작일</td>
				<td><input type="text" name="startdate" value=""></td>
			</tr>
			<tr>
				<td class="label">종료일</td>
				<td><input type="text" name="enddate" value=""></td>
			</tr>
			
			<tr>
				<td class="label">첨부파일</td>
				<td><input type="file" name="file" id="file" accept="image/*" capture="camera" onchange="getThumbnailPrivew(this,$('#cma_image'))"></td>
			</tr>
			</table>
			
			<div id="cma_image" style="width:95%; max-width:100%; border:1px solid #c0c0c0;display:none;">
			</div>
			
			<div class="bottom">
				<a href="/gs25/Sub_Page/eventlist">취소</a> 
				<input type="submit" value="등록">
			</div>	
			</form>
			<form id="mainForm" method="post">
    		<select name="dateYear" onChange="setDay()"></select>년&nbsp;
   			 <select name="dateMonth" onChange="setDay()"></select>월&nbsp;
   			 <select name="dateDay"></select>일&nbsp;
			</form>
			</div>
		</div>
	</div>

</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</div>
</body>

<script language="javascript">
window.onload = function() {
    var frm = document.getElementById('mainForm');
    
    var nowDate        = new Date();
    var nowYear        = nowDate.getFullYear();
    var nowMonth    = eval(nowDate.getMonth()) +1;
    var nowDay        = eval(nowDate.getDate());
    
    /***************
     * 년 세팅
     ***************/
    var startYear    = nowYear;
    for( var i=0; i<20; i++ ) {
        frm['dateYear'].options[i] = new Option(startYear+i, startYear+i);
    }

    /***************
     * 월 세팅
     **************/
    for (var i=0; i<12; i++) {
    	
        frm['dateMonth'].options[i] = new Option(i+1, i+1);
    }
    
    
    /***************************************
     * 먼저 현재 년과 월을 셋팅
     * 윤년계산과 월의 마지막 일자를 구하기 위해
     ***************************************/
    frm['dateYear'].value        = nowYear;
    frm['dateMonth'].value    = nowMonth;
    setDay();
    /********************************************
     * 일(day)의 select를 생성하고 현재 일자로 초기화
     ********************************************/
    frm['dateDay'].value        = nowDay;
}

/******************
 * 일(day) 셋팅
 ******************/
function setDay() {
    var frm = document.getElementById('mainForm');
    
    var year            = frm['dateYear'].value;
    var month            = frm['dateMonth'].value;
    var day                = frm['dateDay'].value;    
    var dateDay        = frm['dateDay'];
    
    var arrayMonth    = [31,28,31,30,31,30,31,31,30,31,30,31];

    /*******************************************
     * 윤달 체크 부분
     * 윤달이면 2월 마지막 일자를 29일로 변경
     *******************************************/
    if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
        arrayMonth[1] = 29;
    }

    /**************************************
     * 기존 일(day) select를 모두 삭제한다.
     **************************************/
    for( var i=dateDay.length; i>0; i--) {
        dateDay.remove(dateDay.selectedIndex);
    }
        
    /*********************************
     * 일(day) select 옵션 생성
     *********************************/
    for (var i=1; i<=arrayMonth[month-1]; i++) {
        dateDay.options[i-1] = new Option(i, i);
    }

    /*********************************************
     * 기존에 선택된 일값 유지
     * 기존 일값보다 현재 최대일값이 작을 경우
     * 현재 선택 최대일값으로 세팅
     ********************************************/
    if( day != null || day != '' ) {
        if( day > arrayMonth[month-1] ) {
            dateDay.options.selectedIndex = arrayMonth[month-1]-1;
        } else {
            dateDay.options.selectedIndex = day-1;
        }
    }
}
</script>
</html>