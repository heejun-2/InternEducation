<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  prefix="form"  uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <style>
        .container {
            max-width: 560px;
        }

        .field-error{
            border-color: #dc3545;
            color: #dc3545;
        }
    </style>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

    <div class="container">
        <div class="py-5 text-center">
            <h2>로그인</h2>
        </div>
        <form:form action="" modelAttribute="loginForm" method="post">
            <c:if test="${not empty msg}">
                <p class="field-error">${msg}</p>
            </c:if>
            <div class="mb-3">
                <label for="loginId">로그인 ID</label>
                <input type="text" id="loginId" name="id" value="${loginForm.id}" class="form-control">
                <form:errors path="id" class="field-error" />
            </div>
            <div class="mb-3">
                <label for="password">비밀번호</label>
                <input type="password" id="password" name="password" value="${loginForm.password}" class="form-control">
                <form:errors path="password" class="field-error" />
            </div>
            <hr class="my-4">
            <div class="row">
                <div class="col">
                    <button class="w-100 btn btn-primary btn-lg" type="submit">로그인</button>
                </div>
                <div class="col">
                    <button class="w-100 btn btn-secondary btn-lg" onclick="location.href='${path}/'" type="button">취소</button>
                </div>
            </div>
        </form:form>
    </div> <!-- /container -->
</body>
</html>
