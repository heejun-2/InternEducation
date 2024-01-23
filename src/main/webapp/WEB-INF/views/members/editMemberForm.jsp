<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  prefix="form"  uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
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
            <h2>정보 수정하기</h2>
        </div>
        <form:form action="/members/update" modelAttribute="member" method="post">
            <input type="hidden" name="memberId" value="${member.memberId}">
            <div class="mb-3">
                <label for="userName">이름</label>
                <input type="text" id="userName" name="name" value="${member.name}" class="form-control bg-body-secondary" readonly>
                <form:errors path="name" class="field-error" />
            </div>
            <div class="mb-3">
                <label for="loginId">아이디</label>
                <input type="text" id="loginId" name="id" value="${member.id}" class="form-control">
                <form:errors path="id" class="field-error" />
            </div>
            <div class="mb-3">
                <label for="password">비밀번호</label>
                <input type="password" id="password" name="originalPassword" value="${member.originalPassword}" class="form-control">
                <form:errors path="originalPassword" class="field-error" />
            </div>
            <div class="mb-3">
                <label for="passwordCheck">비밀번호 확인</label>
                <input type="password" id="passwordCheck" name="passwordCheck" class="form-control">
                <p class="commentChk"></p>
            </div>
            <div class="mb-3">
                <label for="email">이메일</label>
                <input type="text" id="email" name="email" value="${member.email}" class="form-control">
                <form:errors path="email" class="field-error" />
            </div>
            <div class="mb-3">
                <label for="phoneNumber">전화번호</label>
                <input type="text" id="phoneNumber" name="phoneNumber" value="${member.phoneNumber}" class="form-control">
                <form:errors path="phoneNumber" class="field-error" />
            </div>
            <hr class="my-4">
            <div class="row">
                <div class="col">
                    <button class="w-100 btn btn-outline-warning uptBtn" type="button">수정</button>
                </div>
                <div class="col">
                    <button class="w-100 btn btn-outline-dark" onclick="location.href='${path}/members/${member.memberId}/myPage'" type="button">취소</button>
                </div>
            </div>
        </form:form>
    </div>

</body>
<script type="text/javascript">
    $(document).ready(function (){
        let isPass = false;

        $("[name=passwordCheck]").keyup(function (){
            let password = $("[name=originalPassword]").val();
            let passwordCheck = $(this).val();

            if(password !== passwordCheck){
                $(".commentChk").addClass("field-error").text("비밀번호가 일치하지 않습니다.");
                isPass = false;
            }
            else{
                $(".commentChk").removeClass("field-error").text("");
                isPass = true;
            }
        });

        $(".uptBtn").click(function (){
            if(!isPass){
                alert("비밀번호를 다시 확인해주세요.");
            }
            else{
                $("form").submit();
            }
        });

    });
</script>
</html>
