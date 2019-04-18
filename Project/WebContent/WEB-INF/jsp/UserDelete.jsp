<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ユーザーの情報を削除</title>
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
      <div class="card-header">ユーザー削除画面 
        <div style="text-align:right;">${userInfo.name }さん 
          <a href="LogoutServlet">ログアウト</a>
        </div>
      </div>
    </div>
    <!-- header -->
    
    <!-- body -->
    <div class="card-body">
      <div style="text-align:center;">
        <p>ログインid ${user.loginId} を削除してもよろしいでしょうか？</p>
        <form action="UserDeleteServlet" method="post">
          <input type="hidden" name="id" value="${user.id }">
          <input type="submit" value="OK">
          <input type="reset" value="キャンセル">
        </form>
      </div>
    </div>
  </body>
</html>