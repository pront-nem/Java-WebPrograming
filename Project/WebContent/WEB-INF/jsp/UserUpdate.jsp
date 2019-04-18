<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ja">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ユーザー情報更新</title>
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
    <div class="card-header">ユーザー情報更新
    <div style="text-align:right;">${userInfo.name }さん 
    <a href="LogoutServlet">ログアウト</a>
    </div>
    </div>
	<!-- header -->

	<!-- body -->
	<div class="card-body">
	
	<!-- 未入力の項目がある場合エラーを表示 -->
  	<c:if test="${errmsg != null}" >	
	   <div class="alert alert-danger" role="alert"> 
	   ${errmsg}
	   </div>
 	</c:if>
  
  	<!-- パスワードが一致しない場合エラーを表示 -->
  	<c:if test="${errpassword != null}">
  		<div class="alert alert-danger" role="alert">
  		${errpassword}
  		</div>
  	</c:if>
	
  <div style="text-align:center;">
    <form action="UserUpdateServlet" method="post">
      <div class="form-group">
     ログインID
      <input type="hidden" name="id" value="${user.id }">
      <div class="form-group">
        <label>パスワード：<input type="password" class="form-control" name="password" /></label><br/>
      </div>
      <div class="form-group">
        <label>パスワード(確認)：<input type="password" class="form-control" name="password1" /></label><br/>
      </div>
      <div class="form-group">
        <label>ユーザー名：<input type="text" class="form-control" name="name" value="${user.name }"/></label><br/>
      </div>
      <div class="form-group">
        <label>生年月日：<input type="date" class="form-control" name="birth_date" value="${user.birthDate }"/></label><br/>
      </div>
        <input type="submit" value="更新">
      </div>
   </div>
  	</form>
  </body>