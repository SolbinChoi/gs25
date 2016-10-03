<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>gs25_subheader</title>
</head>
<body>

	<div class="header">
		<!-- topwrap -->
		<div class="topwrap">
			<!-- brandwrap -->
			<div class="brdwrap">
				<!-- utility -->
				<div class="uty">
					<ul>
					<c:choose>
					<c:when test='${empty sessionScope.authUser }'>
					<li><a href="/gs25/main">GS25 홈페이지</a></li>
					<li><a href="/gs25/user/loginform">로그인</a></li>
					<li><a href="/gs25/user/joinform">회원가입</a></li>
					</c:when>
					<c:otherwise>
						<li>${authUser.name}님안녕하세요^^;</li>
						<li><a href="/gs25/user/modifyform">회원정보수정</a></li>
						<li><a href="/gs25/user/logout">로그아웃</a></li>
					</c:otherwise>
				</c:choose>
						</ul>
					</div>
					<!-- //utility -->
			</div>
			<!-- //brandwrap -->
			<!-- gnbwrap -->
			<div class="gnbwrap">
				<div class="gnbw">
					<h1 class="logo">
						<a href="/gs25/Sub_Page/sub_index">GS25</a>
					</h1>
					<div class="gnb" id="gnb_menu">
						<ul>
							<li><h2>
									<a href="/gs25/Sub_Page/list" class="on">방명록</a>
								</h2></li>
							<li><h2>
									<a href="/gs25/Sub_Page/eventlist">이벤트</a>
								</h2></li>
							<li><h2>
									<a href="/gs25/map/list">매장검색</a>
								</h2></li>
							<li><h2>
									<a href="/gs25/">상품</a>
								</h2>
						</ul>
					</div>
				</div>

				<div class="gnb_bg" style="display: none;"></div>
			</div>
			<!-- //gnbwrap -->
		</div>
		<!-- //topwrap -->
	</div>

</body>
</html>