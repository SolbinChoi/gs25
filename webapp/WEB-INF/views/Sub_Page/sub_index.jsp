<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/gs25/assets/css/sub.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="/gs25/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

</head>
<body>
	<jsp:include page="/WEB-INF/views/include/subheader.jsp" />
	<div class="container">
		<div id="contents">
			<div class="img">
				<img src="/gs25/assets/images/subindex/gs25Main.png" width="600px">
			</div>
			<div class="login">
				<c:choose>
					<c:when test='${empty sessionScope.authUser }'>
						<div id="login1">
							<p id="title">
								<em>GS편의점</em> <em style="color: blue">로그인</em>
							</p>
							<p id="intro">
								기존 GS편의점 사이트에 가입하신<br> 아이디와 비밀번호로 로그인 하실 수 있습니다.
							</p>
						</div>
						<div id="login2">
							<form class="login-form" name="loginform" method="post"
								action="/gs25/user/login">
								<ul>
									<li><input id="email" name="email" class="form-control"
										type="text" value="" placeholder="이메일"></li>
									<li><input id="password" name="password" type="password"
										class="form-control" value="" placeholder="비밀번호"></li>
								</ul>
								<input id="login-btn" type="button" value="로그인">
							</form>
						</div>
						<div id="login3">
							<a href='/gs25/user/joinform' id="aleft">회원가입</a> <a
								href="/gs25/user/findInfo" id="aright">아이디/비밀번호찾기</a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="login2">
							<c:choose>
								<c:when test='${"FEMALE" == authUser.gender }'>
									<div id="login4_1">
										<p id="username">${authUser.name }님</p>
										<div id="login5">
											<input type="image" id="userbutton"
												src="/gs25/assets/images/login/coins.png">
											<p id="point">포인트&nbsp;${authUser.point }점</p>
											<input type="image" id="userbutton"
												src="/gs25/assets/images/login/cart.png">
											<p id="point">
												<a href="/gs25/cart/list?no=${authUser.no }">찜목록</a>
												${authUser.point }개
											</p>
										</div>
										<p id="endP">아무개 매장에 찾아 주셔서 감사합니다.</p>
									</div>
								</c:when>
								<c:otherwise>
									<div id="login4">
										<p id="username">${authUser.name }님</p>
										<div id="login5">
											<input type="image" id="userbutton"
												src="/gs25/assets/images/login/coins.png">
											<p id="point">포인트&nbsp;${authUser.point } 점</p>
											<input type="image" id="userbutton"
												src="/gs25/assets/images/login/cart.png"> <a
												href="/gs25/" id="userbutton"
												src="/gs25/assets/images/login/cart.png"></a>
											<p id="point">
												<a href="/gs25/cart/list">찜목록</a> ${authUser.point } 개
											</p>
										</div>
										<p id="endP">아무개 매장에 찾아 주셔서 감사합니다.</p>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		
<jsp:include page="/WEB-INF/views/include/menubar.jsp" />

	</div>

	<div class="prod_wrap">
		<div class="container">
			<div class="prod_tab">
				<div class="afterClass" id="tab1">
					유통기한임박 상품
					<div>할인율 최고적용 상품</div>
				</div>
				<div class="beforeClass" id="tab2">
					인기상품
					<div>누적판매순 인기상품</div>
				</div>
				<div class="beforeClass" id="tab3">
					신상품
					<div>방금 들어온 따끈따끈한 신상품</div>
				</div>
				<div class="beforeClass" id="tab4">
					추천상품
					<div>선택장애 극복을 위한 추천상품</div>
				</div>
			</div>

			<div class="prod_list" id="list1">
				<ul>
					<c:set var='countList' value='${fn:length(list)}' />
					<c:forEach var='vo' items='${vo }' varStatus='status'>
						<li>
							<div class='sbbox pro'>
								<span class='tip typ1'><span>${countList }</span></span> <a
									href="/gs25/product/view?no=${vo.no }&name=${vo.name}"><img
									src='${vo.imageurl }'></a> <span class='title'> <em
									class='mt'>${vo.maker})${vo.name }</em><em>${vo.price }원</em>
								</span>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="prod_list" id="list2">
				<ul>
					<c:forEach begin="1" end="4" varStatus='status'>
						<li>
							<div class='sbbox pro'>
								<span class='tip typ1'><span>"+i+"</span></span> <a
									href='/gs25/product/view'><img
									src='http://gs25appimg.gsretail.com/imgsvr/item/GD_8801056076719_002.jpg'
									alt='Y)오렌지스파클 상품'></a> <span class='title'> <em
									class='mt'>유어스)롯데오렌지스파클</em><em>1,700원</em>
								</span>
							</div>
						</li>
					</c:forEach>

				</ul>
			</div>
			<div class="prod_list" id="list3">
				<ul>
					<c:set var='countList' value='${fn:length(list)}' />
					<c:forEach var='vo3' items='${vo3 }' varStatus='status'>
						<li>
							<div class='sbbox pro'>
								<span class='tip typ1'><span>${countList }</span></span> <a
									href="/gs25/product/view?no=${vo3.no }&name=${vo3.name}"><img
									src='${vo3.imageurl }'></a> <span class='title'> <em
									class='mt'>${vo3.maker})${vo3.name }</em><em>${vo3.price }원</em>
								</span>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="prod_list" id="list4">
				<ul>
					<c:set var='countList' value='${fn:length(list)}' />
					<c:forEach var='vo' items='${vo4 }' varStatus='status'>
						<li>
							<div class='sbbox pro'>
								<span class='tip typ1'><span>${countList }</span></span> <a
									href="/gs25/product/view?no=${vo.no }&name=${vo.name}"><img
									src='${vo.imageurl }'></a> <span class='title'> <em
									class='mt'>${vo.maker})${vo.name }</em><em>${vo.price }원</em>
								</span>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
<script>
	$(function() {
	
		$("#login-btn").on("click", function() {
			if ($("#email").val() == "") {
				alert("아이디를 입력해주십시오.");
				$("#email").focus();
				return false;
			}

			if ($("#password").val() == "") {
				alert("비밀번호를 입력해주십시오.");
				$("#password").focus();
				return false;
			}

			var email = $("#email").val();
			var password = $("#password").val();

			$.ajax({
				url : "/gs25/user/checkLogin",
				type : "POST",
				data : {
					"email" : email,
					"password" : password
				},
				dataType : "text",
				success : function(result) { //비동기식으로 진행되어 결과와 상관 없이 submit되므로 계속 refres됨(따로 동기식으로 변경해야함)
					console.log(result);
					if (result == "false") {
						console.log(result);
						alert("유효하지 않는 로그인입니다. 다시 시도해주세요.")
						return false;
					}

					if (result == "true") {
						location.href = '/gs25/main';
					}
				},

				error : function(jsXHR, status, e) {
					console.error("error:" + status + ":" + e);
				}
			});
		});
	});

	jQuery('.prod_tab').click(function(e) {
		console.log('${list }');
		var getId = e.target.getAttribute('id');
		var id = getId.substring(getId.length - 1, getId.length);
		var index = 0;
		// alert(getId);
		for (index = 1; index <= 5; index++) {
			$('#tab' + index).attr('class', 'afterClass');
			if (index != id) {
				$('#tab' + index).attr('class', 'beforeClass');
				$('#list' + id).show();
			}
			$('#list' + index).hide();
		}
	});
</script>
</html>