<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="dto" items="${listAnswer}">
	<div class="border-bottom m-1">
		<div class="row p-1">
			<div class="col-auto">
				<div class="row reply-writer">
					<div class="col-1">
						<i class="bi bi-person-circle text-muted icon"></i>
					</div>
					<div class="col ms-2 align-self-center">
						<div class="name">${dto.studentId}</div>
						<div class="date">${dto.reg_date}</div>
					</div>
				</div>
			</div>
			
			<div class="col align-self-center text-end">
				<span class="reply-dropdown">
					<i class="bi bi-three-dots-vertical"></i>
				</span>
				<div class="reply-menu">
					<c:choose>
						
						<c:when test="${isAuthor == dto.studentId}">
							<div class="deleteReplyAnswer reply-menu-item" 
							     data-replyNum="${dto.replyNum}" 
							     data-parentNum="${dto.parentNum}">
								삭제
							</div>
							
							<div class="hideReplyAnswer reply-menu-item" 
							     data-replyNum="${dto.replyNum}" 
							     data-showReply="${dto.showReply}">
								<c:choose>
									<c:when test="${dto.showReply == 1}">숨김</c:when>
									<c:otherwise>표시</c:otherwise>
								</c:choose>
							</div>
						</c:when>
						
						<c:when test="${employee}">
							<div class="deleteReplyAnswer reply-menu-item" 
							     data-replyNum="${dto.replyNum}" 
							     data-parentNum="${dto.parentNum}">
								삭제
							</div>
							<div class="blockReplyAnswer reply-menu-item">신고</div>
						</c:when>
						
						<c:otherwise>
							<div class="notifyReplyAnswer reply-menu-item">신고</div>
							<div class="blockReplyAnswer reply-menu-item">차단</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>

		<div class="p-2 ${dto.showReply == 0 ? 'text-primary text-opacity-50' : ''}">
			${dto.content}
		</div>
	</div>
</c:forEach>
