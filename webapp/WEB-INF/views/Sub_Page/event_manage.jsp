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
	<jsp:include page="/WEB-INF/views/include/subheader.jsp" />
<div id="content">

		<div id="manage_wrap">
		
		
			<jsp:include page="/WEB-INF/views/include/menubar.jsp" />
		
			<div id="tap_content">
			    <ul class="tabs">
			     <li class="active" id="tap0" rel="tab0">이벤트 창</li>

			    </ul>
			    
			    <div class="tab_container">
			    
			      <div id="tab0" class="tab_content">
			        	<form id="search_form" action="/gs25/manage/eventList" method="get">
			        	<input type="hidden" name="store_no" value="${store_no }">
							<input type="text" id="kwd" name="kwd" value="${map.keyword }">
							<input type="submit" value="찾기">
						</form>
						
			        	<h4>
							전체 회원수 : <span>${map.totalCount }</span>
						</h4>
						
						<table class="tbl-ex" id="tblList">
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>시작일</th>
								<th>종료일</th>
								<th>작성자</th>
								<th>&nbsp;</th>


							</tr>
						
						<c:set var="countList"
								value="${map.totalCount - (map.currentPage - 1)*map.sizeList }" />
						<c:forEach var='vo' items='${map.list}' varStatus='status'>
							<tr>
							<td>${countList - status.index }</td>
							<td>${vo.title }</td>
							<td>${vo.startdate}</td>
							<td>${vo.enddate }</td>
							<td>${vo.name }</td>
							<td>
								<a href="/gs25/manage/eventDelete?no=${vo.no}&store_no=${store_no}" class="del">삭제</a>
							</td>
							</tr>
						</c:forEach>
						</table>
						
			            <ul>
			                <li><a href="/gs25/sub/main?store_no=${store_no }">메인으로 돌아가기</a>
			                </li>
			            </ul>
			            
			            
			            <!-- begin:paging -->
			         
							<div class="pager">
								<ul>

									<c:if test="${map.prevtoPage >= 0 }">
										<li><a href="/gs25/manage/eventList?p=${map.prevtoPage }">◀◀</a></li>
									</c:if>

									<c:if test="${map.prevPage >= 0 }">
										<li><a href="/gs25/manage/eventList?p=${map.prevPage }">◀</a></li>
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
												<li><a href="/gs25/manage/eventList?p=${i }">${i }</a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>

									<c:if test='${map.nextPage > 0 }'>
										<li><a href="/gs25/manage/eventList?p=${map.nextPage }">▶</a></li>
									</c:if>
									<c:if test='${map.nexttoPage > 0 }'>
										<li><a href="/gs25/manage/eventList?p=${map.nexttoPage }">▶▶</a></li>
									</c:if>

								</ul>
							</div>
						 
			     </div>
			<a href="/gs25/Sub_Page/event_write?userno=${authUser.no}&store_no=${store_no}" id="new-book">글쓰기</a>
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
   
});
</script>
</html>