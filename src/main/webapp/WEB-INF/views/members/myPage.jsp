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
    </style>
</head>
<body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
    <div class="container">
        <div class="py-5 text-center">
            <h2>마이페이지</h2>
        </div>

        <form action=""  method="post">
            <div class="mb-3">
                <label for="userName">이름</label>
                <input type="text" id="userName" name="name" value="${member.name}" class="form-control bg-body-secondary" readonly>
            </div>
            <div class="mb-3">
                <label for="loginId">아이디</label>
                <input type="text" id="loginId" name="id" value="${member.id}" class="form-control bg-body-secondary" readonly>
            </div>
            <div class="mb-3">
                <label for="password">비밀번호</label>
                <input type="password" id="password" name="originalPassword" value="${member.originalPassword}" class="form-control bg-body-secondary" readonly>
            </div>
            <div class="mb-3">
                <label for="email">이메일</label>
                <input type="text" id="email" name="email" value="${member.email}" class="form-control bg-body-secondary" readonly>
            </div>
            <div class="mb-3">
                <label for="phoneNumber">전화번호</label>
                <input type="text" id="phoneNumber" name="phoneNumber" value="${member.phoneNumber}" class="form-control bg-body-secondary" readonly>
            </div>
            <hr class="my-4">
            <div class="row">
                <div class="col">
                    <button class="w-100 btn btn-outline-warning" onclick="location.href='${path}/members/${member.memberId}/edit'" type="button">수정</button>
                </div>
                <c:if test="${member.role ne 'ADMIN'}">
                <div class="col">
                    <button class="w-100 btn btn-outline-danger" onclick="location.href='${path}/members/${member.memberId}/delete'" type="button">탈퇴</button>
                </div>
                </c:if>
                <div class="col">
                    <button class="w-100 btn btn-outline-dark" onclick="location.href='${path}/boards'" type="button">취소</button>
                </div>
            </div>
        </form>

    </div>
</body>
</html>
