<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<table class="table board-article">
	<thead>
		<tr>
			<td colspan="2" align="center">
				${dto.lectureTitle}
			</td>
		</tr>
	</thead>
	
	<tbody>
		<tr>
			<td width="50%">
				이름 : ${dto.userName}
			</td>
			<td align="right">
				${dto.reg_date} | 조회 ${dto.hitCount}
			</td>
		</tr>
		
		<tr>
			<td colspan="2" valign="top" height="200" style="${not empty dto.youtube || listFile.size() != 0 ? 'border-bottom: none;' : ''}">
				${dto.lectureContent}
			</td>
		</tr>
		<c:if test="${not empty dto.youtube}">
			<tr>
				<td colspan="2" class="text-center p-3" style="${listFile.size() != 0 ? 'border-bottom: none;' : ''}">
					<label style="cursor: pointer;" class="fs-3" onclick="youtubePlay('${dto.youtube}');" title="유투브"><i class="bi bi-youtube"></i></label>
				</td>
			</tr>		
		</c:if>
		
		<c:if test="${not empty listFile}">
		    <tr>
		        <td colspan="2">
		            <p class="border text-secondary mb-1 p-2">
		                <i class="bi bi-folder2-open"></i>
		                <c:forEach var="vo" items="${listFile}" varStatus="status">
		                    <a href="${pageContext.request.contextPath}/teacher/campus/attachment/download?fileId=${vo.fileId}" class="text-reset">
		                        ${vo.originalFilename}
		                    </a>
		                    <c:if test="${not status.last}">|&nbsp;</c:if>
		                </c:forEach>
		            </p>
		            <p class="border text-secondary mb-1 p-2">
		                <i class="bi bi-folder2-open"></i>
		                <a href="${pageContext.request.contextPath}/teacher/campus/attachment/zipdownload?fileId=${listFile[0].fileId}" class="text-reset" title="압축 다운로드">
		                    파일 전체 압축 다운로드(zip)
		                </a>
		            </p>
		        </td>
		    </tr>
		</c:if>

		
		<tr>
			<td colspan="2">
				이전글 :
				<c:if test="${not empty prevDto}">
					<a href="javascript:articleLecture('${prevDto.lectureId}', '${pageNo}');">${prevDto.lectureTitle}</a>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				다음글 :
				<c:if test="${not empty nextDto}">
					<a href="javascript:articleLecture('${nextDto.lectureId}', '${pageNo}');">${nextDto.lectureTitle}</a>
				</c:if>
			</td>
		</tr>
	</tbody>
</table>

<table class="table table-borderless">
	<tr>
		<td width="50%">

					<button type="button" class="btn btn-light" onclick="updateForm('${dto.lectureId}', '${pageNo}');">수정</button>

					<button type="button" class="btn btn-light" disabled>수정</button>

	    			<button type="button" class="btn btn-light" onclick="deleteOk('${dto.lectureId}', '${pageNo}');">삭제</button>

	    			<button type="button" class="btn btn-light" disabled>삭제</button>
		</td>
		<td class="text-end">
			<button type="button" class="btn btn-light" onclick="listPage('${pageNo}');">리스트</button>
		</td>
	</tr>
</table>
