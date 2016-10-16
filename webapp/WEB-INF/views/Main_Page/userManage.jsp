<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/gs25/assets/css/manage.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/gs25/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
<div id="container">
<jsp:include page="/WEB-INF/views/include/header.jsp" />
<div id="content">

		<div id="manage_wrap">
		
			<div id="tap_content">
			    <ul class="tabs">
			        <li class="active" rel="tab1">
			        	<a href="/gs25/manage/userManage">회원관리</a></li>
			        <li rel="tab2">테스트</li>
			    </ul>
			    
			    <div class="tab_container">
			        <div id="tab1" class="tab_content">
			        	<form id="search_form" action="/gs25/manage/userManage" method="get">
							<input type="text" id="kwd" name="kwd" value="${map.keyword }">
							<input type="submit" value="찾기">
						</form>
						
			        	<h4>
							전체 회원수 : <span>${map.totalCount }</span>
						</h4>
						
						<table class="tbl-ex">
							<tr>
								<th>번호</th>
								<th>아이디</th>
								<th>이름</th>
								<th>PHONE</th>
								<th>직책</th>
								<th>&nbsp;</th>


							</tr>
						
						<c:set var="countList"
								value="${map.totalCount - (map.currentPage - 1)*map.sizeList }" />
						<c:forEach var='vo' items='${map.list}' varStatus='status'>
							<tr>
							<td>${countList - status.index }</td>
							<td>${vo.email }</td>
							<td>${vo.name }</td>
							<td>${vo.phone }</td>
							<td>${vo.position }</td>
							<td>
							<!-- <c:choose>
								<c:when test='${authUser.no==1 }'>
								<a href="/smartcan/userdelete?no=${vo.no}" class="del">삭제</a>
								</c:when>
								
								<c:otherwise>
									&nbsp;
								</c:otherwise>
							</c:choose> -->
							&nbsp;
							</td>
							</tr>
						</c:forEach>
						</table>
						
			            <ul>
			                <li><a href="/gs25/main">메인으로 돌아가기</a>
			                </li>
			            </ul>
			            
			            <!-- begin:paging -->
							<div class="pager">
								<ul>

									<c:if test="${map.prevtoPage >= 0 }">
										<li><a href="/gs25/manage/userManage?p=${map.prevtoPage }">◀◀</a></li>
									</c:if>

									<c:if test="${map.prevPage >= 0 }">
										<li><a href="/gs25/manage/userManage?p=${map.prevPage }">◀</a></li>
									</c:if>

									<c:forEach begin='${map.firstPage }' end='${map.lastPage }'
										step='1' var='i'>
										<c:choose>
											<c:when test='${map.currentPage == i }'>
												<li class="selected">${i }</li>
											</c:when>
											<c:when test='${i > map.pageCount }'>
												<li>${i }</li>
											</c:when>
											<c:otherwise>
												<li><a href="/gs25/manage/userManage?p=${i }">${i }</a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>

									<c:if test='${map.nextPage > 0 }'>
										<li><a href="/gs25/manage/userManage?p=${map.nextPage }">▶</a></li>
									</c:if>
									<c:if test='${map.nexttoPage > 0 }'>
										<li><a href="/gs25/manage/userManage?p=${map.nexttoPage }">▶▶</a></li>
									</c:if>

								</ul>
							</div>
			            
			            
			            
			        </div>
			
			        <div id="tab2" class="tab_content">
			        	관리할 것 ?
			        </div>
			    </div>
			</div>
			
			
		</div>
		
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</div>
</body>
<script>
$(function () {

    $(".tab_content").hide();
    $(".tab_content:first").show();

    $("ul.tabs li").click(function () {
        $("ul.tabs li").removeClass("active").css("color", "#333");
        $(this).addClass("active").css("color", "darkred");
        $(".tab_content").hide()
        var activeTab = $(this).attr("rel");
        $("#" + activeTab).fadeIn()
    });
});
</script>
</html>