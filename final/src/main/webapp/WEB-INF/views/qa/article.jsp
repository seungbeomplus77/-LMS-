<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>spring</title>
    <jsp:include page="/WEB-INF/views/layout/headerResources.jsp"/>
    <style type="text/css">
        .body-container { max-width: 870px; }
    </style>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/boot-board.css" type="text/css">
</head>
<body>
   
    <c:choose>
        <c:when test="${student}">
            <header>
                <jsp:include page="/WEB-INF/views/layout/studentHeader.jsp"/>
            </header>
        </c:when>
        <c:otherwise>
            <header>
                <jsp:include page="/WEB-INF/views/layout/teacherHeader.jsp"/>
            </header>
        </c:otherwise>
    </c:choose>
    
<main>
    <div class="container">
        <div class="body-container">    
            <div class="body-title">
                <h3><i class="bi bi-exclamation-square"></i> 강좌 질문과 답변 </h3>
            </div>
            
            <div class="body-main">
              
                <table class="table board-article">
                    <tbody>
                        <tr>
                            <td colspan="2" class="px-0 pb-0">
                                <div class="row gx-0">
                                    <div class="col-sm-1 bg-primary me-1">
                                        <p class="form-control-plaintext text-white text-center">Q.</p>
                                    </div>
                                    <div class="col bg-primary">
                                        <p class="form-control-plaintext text-white ps-2">${dto.title}</p>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td width="50%">
                                작성자 : ${dto.userId}                    
                            </td>
                            <td align="right">
                                문의일자 : ${dto.reg_date}
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" valign="top" height="150">
                                ${dto.question}
                            </td>
                        </tr>
                    </tbody>
                </table>
                
                <c:if test="${not empty dto.answer}">
                    <table class="table mb-0">
                        <tbody>
                            <tr>
                                <td colspan="2" class="p-0">
                                    <div class="row gx-0">
                                        <div class="col-sm-1 bg-success me-1">
                                            <p class="form-control-plaintext text-white text-center">A.</p>
                                        </div>
                                        <div class="col bg-success">
                                            <p class="form-control-plaintext text-white ps-2">${dto.title}</p>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td width="50%">
                                    담당자 : ${dto.teacherUserId}
                                </td>
                                <td align="right">
                                    답변일자 : ${dto.answer_date}
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" valign="top" height="150">
                                    ${dto.answer}
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </c:if>
              
                <table class="table table-borderless">
                    <tr>
                        <td width="50%">
                            
                            <c:if test="${loginUser == dto.userId and empty dto.answer}">
                                <button type="button" class="btn btn-light" onclick="location.href='${pageContext.request.contextPath}/qa/update?qaNum=${dto.qaNum}&page=${page}';">질문수정</button>
                            </c:if>
                            <c:if test="${loginUser == dto.userId or employee}">
                                <button type="button" class="btn btn-light" onclick="deleteOk('question');">질문삭제</button>
                            </c:if>
                           
                            <c:if test="${teacher or employee}">
                                <c:choose>
                                    <c:when test="${not empty dto.answer}">
                                        <button type="button" class="btn btn-light btnUpdateAnswer" data-mode="update">답변수정</button>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="button" class="btn btn-light btnAnswer" data-mode="write">답변작성</button>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                        </td>
                        <td class="text-end">
                            <button type="button" class="btn btn-light" onclick="location.href='${pageContext.request.contextPath}/qa/list?${query}';">리스트</button>
                        </td>
                    </tr>
                </table>
                
                <!-- 답변 입력 폼 (교사/교직원만 사용, 기본은 숨김) -->
                <div class="reply" style="display: none;">
                    <form name="answerForm" method="post">
                        <div class="form-header">
                            <span class="bold">질문에 대한 답변</span>
                        </div>
                        <table class="table table-borderless reply-form">
                            <tr>
                                <td>
                                    <textarea class="form-control" name="answer">${dto.answer}</textarea>
                                </td>
                            </tr>
                            <tr>
                                <td align="right">
                                    <input type="hidden" name="qaNum" value="${dto.qaNum}">    
                                    <input type="hidden" name="page" value="${page}">                       
                                    <button type="button" class="btn btn-light btnSendAnswer">답변 등록</button>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
                
            </div>
        </div>
    </div>
</main>

<c:if test="${loginUser == dto.userId or employee}">
    <script type="text/javascript">
        function deleteOk(mode) {
            let s = (mode === 'question' ? '질문' : '답변') + '을 삭제 하시겠습니까?';
            if(confirm(s)) {
                let query = 'qaNum=${dto.qaNum}&mode=' + mode + '&${query}';
                let url = '${pageContext.request.contextPath}/qa/delete?' + query;
                location.href = url;
            }
        }
    </script>
</c:if>

<c:if test="${teacher or employee}">
    <script type="text/javascript">
        $(function(){
            // 답변작성 버튼 클릭 시 답변 입력 폼 표시
            $('.btnAnswer').click(function(){
                $('.reply').show();
            });
            
            // 답변수정 버튼 클릭 시 토글 처리
            $('.btnUpdateAnswer').click(function(){
                let mode = $(this).attr('data-mode');
                if(mode === 'update'){
                    $('.reply').show();
                    $(this).text('답변 수정 취소');
                    $(this).attr('data-mode', 'cancel');
                } else {
                    $('.reply').hide();
                    $(this).attr('data-mode', 'update');
                    $(this).text('답변수정');
                }
            });
            
            // 답변 등록 버튼 클릭 시
            $('.btnSendAnswer').click(function(){
                const f = document.answerForm;
                if(!f.answer.value.trim()){
                    f.answer.focus();
                    return false;
                }
                f.action = '${pageContext.request.contextPath}/qa/answer';
                f.submit();
            });
        });
    </script>
</c:if>

<footer>
    <jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
</footer>

<jsp:include page="/WEB-INF/views/layout/footerResources.jsp"/>
</body>
</html>
