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
.body-container {
	max-width: 870px;
}
</style>
<link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/boot-board.css" type="text/css">

<script type="text/javascript">
function sendOk() {
    const f = document.qnaForm;
	let str;
	
    str = f.title.value.trim();
    if(!str) {
        alert('제목을 입력하세요. ');
        f.title.focus();
        return;
    }

    str = f.question.value.trim();
    if(!str) {
        alert('내용을 입력하세요. ');
        f.question.focus();
        return;
    }

    f.action = '${pageContext.request.contextPath}/qa/${mode}';
    f.submit();
}
</script>
</head>
<body>

<header>
	<jsp:include page="/WEB-INF/views/layout/header.jsp"/>
</header>
	
<main>
	<div class="container">
		<div class="body-container">	
			<div class="body-title">
				<h3><i class="bi bi-exclamation-square"></i> 강좌 질문과 답변 </h3>
			</div>
			
			<div class="body-main">

				<form name="qnaForm" method="post">
					<table class="table mt-5 write-form">
					
						<tr>
							<td class="bg-light col-sm-2" scope="row">제 목</td>
							<td>
								<input type="text" name="title" maxlength="100" class="form-control" value="${dto.title}">
							</td>
						</tr>
						
						<tr>
							<td class="bg-light col-sm-2" scope="row">작성자</td>
	 						<td>
								<p class="form-control-plaintext">${loginUser}</p>
							</td>
						</tr>
			
						<tr>
							<td class="bg-light col-sm-2" scope="row">공개여부</td>
							<td class="py-3"> 
								<input type="radio" name="secret" id="secret1" class="form-check-input" 
									value="0" ${empty dto || dto.showQa==0?"checked":"" }>
								<label class="form-check-label" for="secret1">공개</label>
								<input type="radio" name="secret" id="secret2" class="form-check-input"
									value="1" ${dto.showQa==1?"checked":"" }>
								<label class="form-check-label" for="secret2">비공개</label>
							</td>
						</tr>
	
						<tr>
							<td class="bg-light col-sm-2" scope="row">내 용</td>
							<td>
								<textarea name="question" class="form-control">${dto.question}</textarea>
							</td>
						</tr>
						
					</table>
					
					<table class="table table-borderless">
	 					<tr>
							<td class="text-center">
								<button type="button" class="btn btn-dark" onclick="sendOk();">${mode=='update'?'수정완료':'등록완료'}&nbsp;<i class="bi bi-check2"></i></button>
								<button type="reset" class="btn btn-light">다시입력</button>
								<button type="button" class="btn btn-light" onclick="location.href='${pageContext.request.contextPath}/qa/list';">${mode=='update'?'수정취소':'등록취소'}&nbsp;<i class="bi bi-x"></i></button>
								<c:if test="${mode=='update'}">
									<input type="hidden" name="qaNum" value="${dto.qaNum}">
									<input type="hidden" name="page" value="${page}">
									<input type="hidden" name="userId" value="${dto.userId}">
								</c:if>
							</td>
						</tr>
					</table>
				</form>

			</div>
		</div>
	</div>
</main>

<footer>
	<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
</footer>

<jsp:include page="/WEB-INF/views/layout/footerResources.jsp"/>
</body>
</html>