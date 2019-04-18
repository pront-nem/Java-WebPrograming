<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>トップページ</title>
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
    ログイン画面
  </div>
    <!-- header -->
    
    <!-- body -->
    <div class="card-body">
    
    <c:if test="${errmsg != null}" >	
	    <div class="alert alert-danger" role="alert"> 
	    ${errmsg}
	    </div>
 	</c:if>
 	
    <div style="text-align:center;">
      <form action="LoginServlet" method="post">
        <div class="form-group">
          <label>ログインID：
            <input type="text" class="form-control" name="login_id" />
          </label>
          <br/>
        </div>
        <div class="form-group">
          <label>パスワード：
            <input type="password" class="form-control" name="password" />
          </label>
          <br/>
        </div>
        <button type="submit" class="btn btn-primary">ログイン</button>
      </div> 
      </div>
    </form>
  </body>
</html>