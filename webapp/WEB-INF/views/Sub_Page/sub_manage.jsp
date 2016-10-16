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
<link rel="stylesheet" type="text/css" href="/gs25/assets/css/sweetalert.css">
<script type="text/javascript" src="/gs25/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="/gs25/assets/js/sweetalert.min.js"></script>
</head>
<body>
<div id="container">
	<jsp:include page="/WEB-INF/views/include/subheader.jsp" />
<div id="content">

		<div id="manage_wrap">
		
			<div id="tap_content">
			    <ul class="tabs">
			     <li class="active" id="tap0" rel="tab0">관리 메뉴</li>
			     <li id="tap1" rel="tab1">이벤트관리</li>
			     <li id="tap2" rel="tab2"><a href="/gs25/manage/guestList?store_no=${store_no }">방명록관리</a></li>
			    </ul>
			    
			    <div class="tab_container">
			      <div id="tab0" class="tab_content">
			     	 즐!!!!!!!!!!!
			      </div>
			      
			      <div id="tab1" class="tab_content">
			        	<form id="search_form" action="/gs25/manage/eventlist?store_no=${store_no }" method="get">
							<input type="text" id="kwd" name="kwd" value="${map.keyword }">
							<input type="submit" value="찾기">
						</form>
						
			        	<h4>
							전체 회원수 : <span>${map.totalCount }</span>
						</h4>
						
						<table class="tbl-ex">
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
			                <li><a href="/gs25/main">메인으로 돌아가기</a>
			                </li>
			            </ul>
			            
			            
			            <!-- begin:paging -->
			            <!-- 
							<div class="pager">
								<ul>

									<c:if test="${map.prevtoPage >= 0 }">
										<li><a href="/gs25/manage/eventlist?p=${map.prevtoPage }">◀◀</a></li>
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
						 -->
			     </div>
			
			        <div id="tab2" class="tab_content">
			        	<table class="tbl-ex">
			        	<tr>
								<th>번호</th>
								<th>제목</th>
								<th>등록일</th>
								<th>작성자</th>
								<th>&nbsp;</th>

							</tr>
			        	</table>
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
    
    $("#tap1").on("click", function(){
    	console.log('click');
    	var values = []; // 리스트를 받을 변수
    	var store_no=${store_no};
    	$.ajax({
			url: "/gs25/manage/eventList",
			type: "POST",
			data: {"store_no":store_no, "page":1},
			dataType: "text",
			success : function(result){
				console.log('잘갔다왔다!');
				
				
				 if(map.code == "OK"){
				
					 
					values = map.list;
					sweetAlert("리스트 완료");
					$("#tap1").addClass("active").css("color", "darkred");
					$("#tap0").removeClass("active").css("color", "#333");
					
				} 
				
			},
			"error": function(jsXHR, status, e){
				console.error("error:"+status+":"+e);
			}
		});
    });
    
	// 탭 클릭시
   /* $("ul.tabs li").click(function () {
        $("ul.tabs li").removeClass("active").css("color", "#333"); // active 클래스를 변경시켜주기
        $(this).addClass("active").css("color", "darkred");
        $(".tab_content").hide()
        var activeTab = $(this).attr("rel");
        $("#" + activeTab).fadeIn()
    }); */
});
</script>
</html>