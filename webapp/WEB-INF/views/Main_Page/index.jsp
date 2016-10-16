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
<link href="/gs25/assets/css/menubar.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/gs25/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript" src="/gs25/assets/js/menubar.js"></script>
<style>
 #STATICMENU {
	margin: 0 150px;
	padding: 0pt;
	position: absolute;
	right: 0px;
	top: 0px;
}
</style>
</head>
<body>

<div id="container">
<jsp:include page="/WEB-INF/views/include/header.jsp" />

<div id="content">

<div class="container_index">
		<img src="/gs25/assets/images/index/gs25_03.gif"/>
		
		<div id="menubar">
			<div id="STATICMENU">
			<div class="myarea_wrap">
			<div class="mymenu">
				<div class="couwrap_off">
					<p>
					서비스 이용을<br>
					위해 로그인<br>
					해주세요 <br>
					</p>
					<input type="button" class="btn_log" value="로그인" onclick="location.href='/gs25/user/loginform';">
				</div>
				<ul class="my_lst">
					<li>
						<a href="/gs25/map/list" class="my_m0">매장검색</a>
					</li>
					<li>
						<a href="/gs25/custom/list" class="my_m1">고객센터</a>
					</li>
				</ul>
				<div class="menuwrap">
				<c:choose>
					<c:when test='${authUser.no ==1 }'>
					<p class="menu_tit">관리자 메뉴</p>
					<ul class="my_lst">
						<li>
							<a href="/gs25/manage/userManage" class="my_m4">회원관리</a>
						</li>
					</ul>
					</c:when>
					<c:otherwise>
					<p class="menu_tit">반가워요</p>
					</c:otherwise>
				</c:choose>
				</div>
			</div>
			<a href="#" class="top">TOP</a>
			</div>
			</div>
			</div>
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