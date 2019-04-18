<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>ユーザー情報参照</title>
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
  <div class="card-header">
   	ユーザー情報詳細
  </div>
  <div style="text-align:right;">
  	${userInfo.name }さん	
    <a href="LogoutServlet">ログアウト</a>
   </div>
    <!-- header -->
    
    <!-- body -->
    <div class="card-body">
  <div style="text-align:center;">
    <p>ログインID
     ${user.loginId}
    </p>
    <p>ユーザー名
    ${user.name}
    </p>
    <p>生年月日
      ${user.birthDate}
    </p>
    <p>登録日時</p>
    ${user.createDate}
    <p>更新日時</p>
  </div>
  </form>
</body>