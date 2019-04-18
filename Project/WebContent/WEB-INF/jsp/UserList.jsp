<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ユーザー一覧</title>
    <!-- BootstrapのCSS読み込み -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery読み込み -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- BootstrapのJS読み込み -->
    <script src="js/bootstrap.min.js"></script>
  </head>
  
  <body>
    <!-- header -->
    <div class="card">
    <div class="card-header">ユーザー一覧
    <div style="text-align:right;">
    ${userInfo.name }さん
    <a href="LogoutServlet">ログアウト</a>
    </div>
    </div>
    <!-- header -->
    
   	<div class="text-right">
    <a href="UserRegisterServlet" >新規登録</a>
    </div>
      
    <!-- searching -->
    
    <form action="UserListServlet" method="post">
    <div style="text-align:center;">
    <div class="form-group">
    <label>ログインID：
      <input type="text" class="form-control" name="login_id" />
    </label>
    </div>
    <form action="" method="post">
    <div class="form-group">
    <label>ユーザー名： 
      <input type="text" class="form-control" name="name" />
    </label>
    </div>
    <form action="" method="post">
    <div class="form-group">
    <label>生年月日： 
      <input type="date" class="form-control" name="birth_date" />〜<input type="date" class="form-control" name="birth_date1" />
    </label>
    </div>
    <div style="text-align:center;">
    <input type="submit" value="検索">
    </div>
    <!-- searching -->
    
    <!-- userinfo -->
    <div class="container">
    <table class="table table-bordered">
      <thead>
        <tr>
          <th>ログインID</th>
          <th>ユーザー名</th>
          <th>生年月日</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
      <c:forEach var="user" items="${userList}" >
        <tr>
          <th scope="row">${user.loginId}</th>
          <td>${user.name}</td>
          <td>${user.birthDate}</td>
          <td>
          <!-- 管理者でログインした場合のみ削除ボタンを表示 -->
          	<c:if test="${userInfo.loginId == 'admin'}">	
            <a href="UserDetailServlet?id=${user.id}" class="btn btn-primary" role="button">詳細</a>
            <a href="UserUpdateServlet?id=${user.id}" class="btn btn-success" role="button">更新</a>
            <a href="UserDeleteServlet?id=${user.id}" class="btn btn-danger" role="button">削除</a>
            </c:if>
            
          <!-- 管理者以外でログインした場合削除ボタンは表示させない -->
            <c:if test="${userInfo.loginId != 'admin'}">	
            <a href="UserDetailServlet?id=${user.id}" class="btn btn-primary" role="button">詳細</a>
            
            <c:if test="${userInfo.loginId == user.loginId}">	
            <a href="UserUpdateServlet?id=${user.id}" class="btn btn-success" role="button">更新</a>
            </c:if>
            </c:if>
          </td>
        </tr>
        </c:forEach>
      </tbody>
    </table>
    </div>
  </body>
</html>