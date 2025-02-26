<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<form name="lectureForm" method="post" enctype="multipart/form-data">
	<table class="table mt-5 write-form">
		<tr>
			<td class="bg-light col-sm-2" scope="row">제 목</td>
			<td>
				<input type="text" name="lectureTitle" maxlength="100" class="form-control" value="${dto.lectureTitle}">
			</td>
		</tr>
		
		<tr>
			<td class="bg-light col-sm-2" scope="row">작성자명</td>
				<td>
				<p class="form-control-plaintext">${sessionScope.member.userName}</p>
			</td>
		</tr>

		<tr>
			<td class="bg-light col-sm-2" scope="row">내 용</td>
			<td>
				<textarea name="lectureContent" class="form-control">${dto.lectureContent}</textarea>
			</td>
		</tr>
		
		<tr>
			<td class="bg-light col-sm-2" scope="row">유투브 주소</td>
			<td>
				<input type="text" name="youtube" class="form-control" value="${dto.youtube}">
				<small class="form-control-plaintext help-block">동영상 URL 복사 - 예 : https://youtu.be/FDOKes38JlQ</small>
			</td>
		</tr>

		<tr>
			<td class="bg-light col-sm-2">첨 부</td>
			<td> 
				<input type="file" name="selectFile" multiple class="form-control">
			</td>
		</tr>
		
		<c:if test="${mode=='update'}">
			<c:forEach var="vo" items="${listFile}">
				<tr>
					<td class="bg-light col-sm-2" scope="row">첨부된파일</td>
					<td>
						<p class="form-control-plaintext">
							<span class="delete-file" data-fileId="${vo.fileId}"><i class="bi bi-trash"></i></span> 
							${vo.originalFilename}
						</p>
					</td>
				</tr>
			</c:forEach> 
		</c:if>
	</table>
	
	<table class="table table-borderless">
			<tr>
			<td class="text-center">
				<button type="button" class="btn btn-dark" onclick="sendOk('${mode}', '${pageNo}');">${mode=='update'?'수정완료':'등록완료'}&nbsp;<i class="bi bi-check2"></i></button>
				<button type="reset" class="btn btn-light">다시입력</button>
				<button type="button" class="btn btn-light" onclick="sendCancel('${pageNo}');">${mode=='update'?'수정취소':'등록취소'}&nbsp;<i class="bi bi-x"></i></button>
				<c:if test="${mode=='update'}">
					<input type="hidden" name="lectureId" value="${dto.lectureId}">
				</c:if>
			</td>
		</tr>
	</table>
</form>
