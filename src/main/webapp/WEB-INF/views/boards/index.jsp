<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <title>Title</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <style>
        .container {
            max-width: 1000px;
        }
        a {
            text-decoration-line: none;
            cursor: pointer;
        }
        .blind{
            height: 22px;
        }
    </style>

</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
    <header>
        <c:if test="${not empty msg}">
        <div id="msgCheck" class="alert alert-warning alert-dismissible fade show" role="alert">
            <strong>${msg}</strong>
            <div>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </div>
        </c:if>
        <div class="container">
            <br><br>
            <c:if test="${not empty member}">
                <div class="row g-2 grid gap-0 d-md-flex justify-content-md-end">
                    <div class="col-auto">
                        <strong>${member.name}</strong>님 반갑습니다.
                    </div>
                    <div class="col-auto">
                        <button type="button" class="btn btn-outline-primary btn-sm" onclick="location.href='${path}/members/${member.memberId}/myPage'">
                            <i class="bi bi-person-circle"></i>&nbsp;마이페이지
                        </button>
                    </div>
                    <div class="col-auto">
                        <form action="${path}/members/logout" method="post">
                            <button type="submit" class="btn btn-outline-dark btn-sm">로그아웃 <i class="bi bi-box-arrow-right"></i></button>
                        </form>
                    </div>
                </div>
            </c:if>

        </div>
    </header>

    <div class="container">
        <br><br><br>
        <div class="sch_line">
            <form method="post" action="/boards" class="row g-2 grid gap-0 d-md-flex justify-content-md-end">
                <div class="col-auto">
                    <a href="${path}/boards/new" class="btn btn-outline-success">게시글 작성</a>
                </div>
                <c:if test="${member.role eq 'ADMIN'}">
                    <div class="col-auto">
                        <a href="${path}/boards/deleteAll" class="btn btn-outline-danger">게시글 전체삭제</a>
                    </div>
                    <div class="col-auto">
                        <button class="btn btn-outline-danger choiceBtn" disabled>게시글 선택삭제</button>
                    </div>
                </c:if>

                <div class="col-auto">
                    <input type="text" name="title" class="form-control" placeholder="제목 검색" value="${board.title}">
                    <input type="hidden" name="page" value="${pageVo.page}">
                </div>
                <div class="col-auto">
                    <button type="submit" class="btn btn-outline-secondary mb-3">검 색</button>
                </div>
            </form>
        </div>
        <br>
        <table class="table table-hover table-bordered">
            <thead class="table-light">
            <tr class="text-center">
                <th><input type="checkbox" class="button_checkbox blind" id="checkAll"></th>
                <th>글번호</th>
                <th>작성자</th>
                <th>제목</th>
                <th>조회수</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="board" items="${boards}">
            <tr class="text-center">
                <td><input type="checkbox" class="button_checkbox blind" name="check" value="${board.boardId}"></td>
                <td>${board.boardId}</td>
                <td>${board.name}</td>
                <td><a href="${path}/boards/${board.boardId}">${board.title}</a></td>
                <td>${board.visitCount}</td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
        <br>
        <div id="pagination" class="mb-4 d-flex justify-content-center">
<%--            <!-- 맨 처음 페이지로 이동하는 버튼 -->--%>
<%--            <c:if test="${pageVo.startPage > 1}">--%>
<%--            <span>--%>
<%--                <a href="${path}/boards?page=1">처음&emsp;</a>--%>
<%--            </span>--%>
<%--            </c:if>--%>

            <!-- 이전 블록으로 이동하는 버튼 -->
            <c:if test="${pageVo.isPrev == 1}">
                <span>
                    <a onclick="goPage(${pageVo.startPage - 1})"> < </a>
                </span>
            </c:if>

            <!-- 시퀀스 보여주는 값을 변경. -->
            <c:forEach var="pageNum" begin="${pageVo.startPage}" end="${pageVo.endPage}">
                <span>
                    <a onclick="goPage(${pageNum})">&emsp;${pageNum}&emsp;</a>
                </span>
            </c:forEach>

            <!-- 다음 블록으로 이동하는 버튼 -->
            <c:if test="${pageVo.isNext == 1}">
                <span>
                    <a onclick="goPage(${pageVo.endPage + 1})"> > </a>
                </span>
            </c:if>

<%--            <!-- 맨 뒷 페이지로 이동하는 버튼 -->--%>
<%--            <c:if test="${pageVo.endPage < pageVo.totalPageCount}">--%>
<%--                <span>--%>
<%--                    <a href="${path}/boards?page+${pageVo.totalPageCount}"> &emsp;끝 </a>--%>
<%--                </span>--%>
<%--            </c:if>--%>
        </div>

    </div>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#checkAll").click(function(){
                if($("#checkAll").prop("checked")){
                    $("input[name=check]").prop("checked", true);
                }
                else{
                    $("input[name=check]").prop("checked", false);
                }
            });


            $("input[type=checkbox]").change(function(){
                let boxCount = $("input[name=check]").length;
                let checkedBoxCount = $("input[name=check]:checked").length;

                if(boxCount === checkedBoxCount){
                    $("#checkAll").prop("checked", true);
                }
                else{
                    $("#checkAll").prop("checked", false);
                }

                if(checkedBoxCount > 0){
                    $(".choiceBtn").attr("disabled", false);
                }
                else{
                    $(".choiceBtn").attr("disabled", true);
                }
            })

            $(".choiceBtn").click(function(){
                let boardIdArray = [];

                $("input[name=check]:checked").each(function(){
                    boardIdArray.push($(this).val());
                })

                let isPass = confirm("정말로 삭제하시겠습니까?");

                if(isPass){
                    $.ajax({
                        type : 'POST',
                        url : '/board/delete',
                        dataType : 'json',
                        data : JSON.stringify(boardIdArray),
                        contentType : 'application/json',
                        success : function(result){
                            alert("게시글이 삭제되었습니다.")
                            console.log(result);
                        },
                        error : function (request, status, error){
                        }
                    });
                }

            })
        });

        function goPage(pageNum){
            $("input[name=page]").val(pageNum);
            $("form").submit();
        }
    </script>
</body>

</html>
