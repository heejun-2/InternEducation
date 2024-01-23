<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
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

    <div class="container" style="max-width: 600px">
        <div class="py-5 text-center">
            <h2>홈 화면</h2>
        </div>
        <div class="row">
            <div class="col">
                <a href="${path}/members/add" class="w-100 btn btn-secondary btn-lg">회원가입</a>
            </div>
            <div class="col">
                <a href="${path}/members/login" class="w-100 btn btn-dark btn-lg">로그인</a>
            </div>
        </div>
        <hr class="my-4">
    </div>
</body>
</html>
