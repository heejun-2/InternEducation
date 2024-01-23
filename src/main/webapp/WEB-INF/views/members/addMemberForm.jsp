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
            <h3>회원 가입</h3>
        </div>
        <h5 class="mb-3">회원 정보 입력</h5>
        <form:form action="" modelAttribute="member" method="post">
            <div class="mb-3">
                <label for="name">이름</label>
                <input type="text" id="name" name="name" value="${member.name}" class="form-control" >
                <form:errors path="name" class="field-error" />
            </div>
            <div class="mb-3">
                <label for="loginId">로그인 ID</label>
                <input type="text" id="loginId" name="id" value="${member.id}" class="form-control">
                <form:errors path="id" class="field-error"/>
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
            <div class="mb-3">
                <input type="hidden" name="role" value="USER" class="form-control">
            </div>

            <hr class="my-4">
            <div class="row">
                <div class="col">
                    <button class="w-100 btn btn-primary btn-lg addBtn" type="button">회원 가입</button>
                </div>
                <div class="col">
                    <a href="${path}/" class="w-100 btn btn-secondary btn-lg">취소</a>
                </div>
            </div>
        </form:form>
    </div>
</body>
<script type="text/javascript">
    $(document).ready(function(){
        var isPass = false

        $("[name=passwordCheck]").keyup(function (){
            var password = $("[name=originalPassword]").val();
            var passwordCheck = $(this).val();

            if(password !== passwordCheck){
                $(".commentChk").addClass("field-error").text("비밀번호가 다릅니다.");
                isPass = false;
            }
            else{
                $(".commentChk").removeClass("field-error").text("");
                isPass = true;
            }
        });

        $(".addBtn").click(function (){
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
