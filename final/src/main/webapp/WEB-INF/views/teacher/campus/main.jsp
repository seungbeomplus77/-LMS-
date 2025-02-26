<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>spring</title>
  <jsp:include page="/WEB-INF/views/layout/headerResources.jsp"/>

  <style type="text/css">
    .body-frame { display: flex; }
    .body-left { width: 260px; }
    .body-right { padding-left: 30px; width: 100%; }
    .list-group {
      list-style: none;
      margin-top: 10px;
      padding: 0;
      margin-bottom: 20px;
      width: 210px;
      min-height: 500px;
      border-radius: 4px;
      box-shadow: 0 1px 2px rgba(0,0,0,.075);
    }
    .list-group .list-header {
      color: #ffffff;
      background-color: #337ab7;
      border-color: #337ab7;
      font-family: "Malgun Gothic", "Nanum Gothic", "Dotum";
      font-size: 14px;
      font-weight: bold;
    }
    .list-group .list-header i {
      background: #ffffff;
      display: inline-block;
      margin: 0 10px 0 3px;
      position: relative;
      top: 2px;
      width: 3px;
      height: 15px;
    }
    .list-group-item {
      padding: 10px 15px;
      background-color: #ffffff;
      border: 1px solid #dddddd;
    }
    .list-group li { color: #555555; }
    .list-group-item.active, 
    .list-group-item.active:focus, 
    .list-group-item.active:hover {
      color: #424951;
      font-weight: bold;
      background: none;
      background-color: #F6F6F6;
      border-color: #D5D5D5;
      text-shadow: 0 -1px 0 #286090;
    }
    li.list-subject:not(.active) { cursor: pointer; }
    li.list-subject:hover { background-color: #F6F6F6; }
    .list-group-item:first-child {
      border-top-left-radius: 4px;
      border-top-right-radius: 4px;
    }
    .list-group-item:last-child {
      margin-bottom: 0;
      border-bottom-right-radius: 4px;
      border-bottom-left-radius: 4px;
    }
    li.list-group-item, button.list-group-item { color: #555555; }
    .delete-file { cursor: pointer; }
    .delete-file:hover { color: #0d58ba; }
  </style>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/boot-board.css" type="text/css">
</head>
<body>

<header>
  <jsp:include page="/WEB-INF/views/layout/teacherHeader.jsp"/>
</header>
  
<main>
  <div class="container body-container body-frame">
    <div class="body-left">
      <ul class="list-group">
       <li class="list-group-item list-header"><i></i>${lectureCategory.majorName}</li>
        <c:forEach var="vo" items="${list}" varStatus="status">
         
			<li class="list-group-item list-subject ${status.index == 0 ? 'active' : ''}" 
			    data-major="${vo.majorId}" data-subject="${vo.subjectId}">${vo.subjectName}</li>
        </c:forEach>
      </ul>
    </div>
    <div class="body-right">
      <div class="body-title">
        <h3><i class="fa-solid fa-graduation-cap"></i> <label class="subject-title">${subjectName}</label></h3>
      </div>
      <div class="body-main content-frame"></div>
    </div>
  </div>
</main>

<!-- 검색 폼 -->
<form name="searchForm" method="post">
  <input type="hidden" name="schType" value="all">
  <input type="hidden" name="kwd" value="">
</form>

<script type="text/javascript">
$(function(){ listPage(1); });

//글 리스트 출력
function listPage(page) {
 let subjectCode = $('.list-group li.active').attr('data-subject');

 let url = '${pageContext.request.contextPath}/teacher/campus/' + subjectCode + '/list';
 let formData = 'pageNo=' + page;
 
 if ($('form[name=searchForm] input[name=kwd]').val()) {
     let search = $('form[name=searchForm]').serialize();
     formData += '&' + search;
 }
 
 const fn = function(data){ $('.content-frame').html(data); };
 ajaxRequest(url, 'get', formData, 'text', fn);
}

//좌측 서브메뉴 클릭 시
$('.list-group').on('click', 'li.list-subject:not(.active)', function(){
 let subject = $(this).text();
 $('.subject-title').text(subject);
 $('li.list-subject').removeClass('active');
 $(this).addClass('active');
 
 $('form[name=searchForm] input[name=schType]').val('all');
 $('form[name=searchForm] input[name=kwd]').val('');
 
 listPage(1);
});

//새로고침
function reloadLecture() {
 $('form[name=searchForm] input[name=schType]').val('all');
 $('form[name=searchForm] input[name=kwd]').val('');
 listPage(1);
}

//검색
function searchList() {
 const f = $('form[name=searchForm]');
 f.find('input[name=schType]').val($('#searchType').val());
 f.find('input[name=kwd]').val($.trim($('#searchValue').val()));
 listPage(1);
}

//게시글 보기
function articleLecture(lectureId, page) {
 let subjectCode = $('.list-group li.active').attr('data-subject');
 let url = '${pageContext.request.contextPath}/teacher/campus/' + subjectCode + '/article';
 
 let formData = 'lectureId=' + lectureId + '&pageNo=' + page;
 if ($('form[name=searchForm] input[name=kwd]').val()) {
     let search = $('form[name=searchForm]').serialize();
     formData += '&' + search;
 }
 
 const fn = function(data){ $('.content-frame').html(data); };
 ajaxRequest(url, 'get', formData, 'text', fn);
}
</script>

<c:if test="${teacher}">
  <script type="text/javascript">
    // 글쓰기 폼
    function writeForm() {
      let subjectCode = $('.list-group li.active').attr('data-subject');
      let url = '${pageContext.request.contextPath}/teacher/campus/' + subjectCode + '/write';
      let formData = 'tmp=' + new Date().getTime();
      const fn = function(data){ $('.content-frame').html(data); };
      ajaxRequest(url, 'get', formData, 'text', fn);
    }
    // 글 수정 폼
    function updateForm(lectureId, page) {
      let subjectCode = $('.list-group li.active').attr('data-subject');
      let url = '${pageContext.request.contextPath}/teacher/campus/' + subjectCode + '/update';
      let formData = 'lectureId=' + lectureId + '&pageNo=' + page;
      const fn = function(data){ $('.content-frame').html(data); };
      ajaxRequest(url, 'get', formData, 'text', fn);
    }
    // 등록/수정 완료 처리
    function sendOk(mode, page) {
      const f = document.lectureForm;
      let str;
      
      str = f.lectureTitle.value.trim();
	    if(!str) {
	        alert('제목을 입력하세요. ');
	        f.lectureTitle.focus();
	        return;
	    }
		
	    str = f.lectureContent.value.trim();
	    if(!str) {
	        alert('내용을 입력하세요. ');
	        f.lectureContent.focus();
	        return;
	    }
     
	  let subjectCode = $('.list-group li.active').attr('data-subject');
      let url = '${pageContext.request.contextPath}/teacher/campus/' + subjectCode + '/' + mode;
     
      let formData = new FormData(f);
		
      const fn = function(data){
			let state = data.state;
	        if(state === 'false') {
	            alert('게시물을 추가(수정)하지 못했습니다. !!!');
	            return false;
	        }
	        
	    	if(mode === 'write') {
	    		page = '1';
	    	}
	    	
	    	listPage(page);
		};
		
		ajaxRequest(url, 'post', formData, 'json', fn, true);
	}
	
	// 글쓰기 취소, 수정 취소
	function sendCancel(page) {
		if( ! page ) {
			page = '1';
		}
		
		listPage(page);
	}
    // 글 삭제
    function deleteOk(lectureId, page) {
      let subjectCode = $('.list-group li.active').attr('data-subject');
      let url = '${pageContext.request.contextPath}/teacher/campus/' + subjectCode + '/delete';
      let formData = "lectureId=" + lectureId;
      if(!confirm('강좌를 삭제 하시겠습니까?')){ return; }
      const fn = function(data){ listPage(page); };
      ajaxRequest(url, 'post', formData, 'json', fn);
    }
    
	// 수정에서 파일 삭제
	$('.content-frame').on('click', '.delete-file', function(){
		if(! confirm('선택한 파일을 삭제 하시겠습니까 ? ')) {
			return false;
		}
		
		let subjectCode = $('.list-group li.active').attr('data-subject');
		
		let $tr = $(this).closest('tr');
		let fileId = $(this).attr('data-fileId');
		let url = '${pageContext.request.contextPath}/teacher/campus/' + subjectCode + '/deleteFile';

		$.ajaxSetup({ beforeSend: function(e) { e.setRequestHeader('AJAX', true); } });
		$.post(url, {fileId:fileId}, function(data){
			$($tr).remove();
		}, 'json').fail(function(jqXHR){
			console.log(jqXHR.responseText);
		});			
	});    
  </script>
</c:if>

<!-- 유튜브 보기 Modal -->
<div class="modal fade" id="myDialogModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="myDialogModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="myDialogModalLabel">유튜브 동영상</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <iframe id="youtubePlayer" width="465" height="310" style="border:none;"></iframe>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary btnPlayStop">중지</button>
        <button type="button" class="btn btn-secondary btnPlayClose" data-bs-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
  // 유튜브 플레이 함수
  function youtubePlay(src) {
    if (!src) return;
    // URL에서 동영상 ID 추출 (예: v= 또는 마지막 / 이후)
    src = src.substring(src.lastIndexOf('/') + 1);
    if(src.indexOf('=') > 0){
      src = src.substring(src.indexOf('=') + 1);
    }
    let movSrc = 'https://www.youtube.com/embed/' + src + '?enablejsapi=1&version=3&playerapiid=ytplayer';
    let player = document.getElementById('youtubePlayer');
    player.setAttribute('allow', 'accelerometer; encrypted-media; gyroscope; picture-in-picture');
    player.setAttribute('allowfullscreen', 'true');
    player.setAttribute('src', movSrc);
    $('#myDialogModal').modal('show');
  }

  // 유튜브 정지 함수
  function youtubeStop() {
    const frame = document.getElementById('youtubePlayer');
    if(frame && frame.contentWindow){
      frame.contentWindow.postMessage('{"event":"command", "func":"stopVideo", "args":""}', '*');
    }
  }

  $(function(){
    $('.btnPlayStop').click(function(){
      youtubeStop();
    });
    $('.btnPlayClose').click(function(){
      $("#myDialogModal").modal("hide");
    });
  });

  // 모달 닫힐 때 유튜브 정지 처리
  const myModalEl = document.getElementById('myDialogModal');
  myModalEl.addEventListener('hidden.bs.modal', event => {
    youtubeStop();
  });

  $(function(){
    // 모달 닫힐 때 포커스 충돌 방지
    $('#myDialogModal').on('hide.bs.modal', function () {
      $('button, input, select, textarea').each(function () {
        $(this).blur();
      });
    });
  });
</script>

<footer>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
</footer>
<jsp:include page="/WEB-INF/views/layout/footerResources.jsp"/>
</body>
</html>
