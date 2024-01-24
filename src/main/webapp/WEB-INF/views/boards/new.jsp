<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="form"  uri="http://www.springframework.org/tags/form"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <style>
        .container {
            max-width: 900px;
        }
        .field-error{
            border-color: #dc3545;
            color: #dc3545;
        }
    </style>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
<br>

    <form:form class="container" method="post" modelAttribute="board" enctype="multipart/form-data">
        <div class="py-5 text-center">
            <h4>게시판 생성</h4>
        </div>
        <div class="mb-4">
            <label for="exampleFormControlInput2" class="form-label">작성자</label>
            <input type="text" class="form-control bg-body-secondary" id="exampleFormControlInput2" name="name" value="${member.name}" readonly>
            <form:errors path="name" class="field-error"/>
        </div>
        <div class="mb-4">
            <label for="exampleFormControlInput1" class="form-label">제목</label>
            <input type="text" class="form-control" id="exampleFormControlInput1" name="title" value="${board.title}">
            <form:errors path="title" class="field-error"/>
        </div>
        <div class="mb-4">
            <label for="exampleFormControlTextarea1" class="form-label">내용</label>
            <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="content" style="resize: none;">${board.content}</textarea>
            <form:errors path="content" class="field-error"/>
        </div>
            <input type="hidden" name="memberId" value="${member.memberId}">
        <hr>
        <div class="mb-3">
            <h5><span class="badge text-bg-primary">첨부파일</span></h5>
            <input type="file" class="form-control" id="exampleFile" name="attachFile">
            <input type="file" class="form-control" multiple>
        </div>
        <br>
        <div class="d-grid gap-1 d-md-flex justify-content-md-end">
            <div class="col-auto">
                <button type="submit" class="btn btn-outline-success">작성</button>
            </div>
            <div class="col-auto">
                <a href="${path}/boards" class="btn btn-outline-dark">취소</a>
            </div>
        </div>
    </form:form>

</body>
</html>
