<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <title>Title</title>
    <style>
        .container {
            max-width: 900px;
        }
        textarea{
            height: 150px;
        }
    </style>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
    <header>
        <c:if test="${not empty msg}">
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                <strong>${msg}</strong>
                <div>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </div>
        </c:if>
    </header>

    <div class="container">
        <div class="py-5 text-center">
            <h4>게시판</h4>
        </div>
        <form class="container" action="" method="post">
            <div class="mb-4">
                <label for="exampleFormControlInput2" class="form-label">작성자</label>
                <input type="text" class="form-control bg-body-secondary" id="exampleFormControlInput2" value="${board.name}" readonly>
            </div>
            <div class="mb-4">
                <label for="exampleFormControlInput1" class="form-label">제목</label>
                <input type="text" class="form-control bg-body-secondary" id="exampleFormControlInput1" value="${board.title}" readonly>
            </div>
            <div class="mb-4">
                <label for="exampleFormControlTextarea1" class="form-label">내용</label>
                <textarea class="form-control bg-body-secondary" id="exampleFormControlTextarea1" rows="3" style="resize: none;" readonly>${board.content}</textarea>
            </div>
        </form>
        <hr>
        <h5>
            <span class="badge text-bg-primary">첨부파일</span>
        </h5>
        <div class="container">
        <c:if test="${not empty fileList}">
            <c:forEach var="file" items="${fileList}">
                <c:if test="${file.uploadFileName ne ''}">
                    <div class="mb-2">
                        <a>${file.uploadFileName}</a>&emsp;
                        <span>
                            <a href="${path}/download/${file.fileId}" class="btn btn-outline-primary">첨부파일 다운로드</a>
                        </span>
                        <c:if test="${member.memberId == board.memberId or member.role eq 'ADMIN'}">
                        <span>
                            <a href="${path}/boards/${file.fileId}/fileDelete?boardId=${board.boardId}" class="btn btn-outline-danger">첨부파일 삭제</a>
                        </span>
                        </c:if>
                    </div>
                </c:if>
            </c:forEach>
        </c:if>
        </div>

        <br><br>
        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
            <c:if test="${member.memberId == board.memberId or member.role eq 'ADMIN'}">
            <div class="col-auto">
                <a href="${path}/boards/${board.boardId}/edit" class="btn btn-outline-warning">수정</a>
            </div>
            <div class="col-auto">
                <a href="${path}/boards/${board.boardId}/delete" class="btn btn-outline-danger">삭제</a>
            </div>
            </c:if>
            <div class="col-auto">
                <a href="${path}/boards" class="btn btn-outline-dark">목록으로</a>
            </div>
        </div>
        <br><br>
    </div>
</body>
</html>
