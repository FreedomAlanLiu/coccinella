<%@ include file="../common/include.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="zh" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="zh" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>Coccinella | Admin</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>

<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="//fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/resources/app/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/resources/app/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/resources/app/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/app/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/app/plugins/select2/select2-metronic.css"/>
<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN THEME STYLES -->
<link href="${pageContext.request.contextPath}/resources/app/css/style-metronic.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/resources/app/css/style.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/resources/app/css/style-responsive.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/resources/app/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/resources/app/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="${pageContext.request.contextPath}/resources/app/css/pages/login.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/resources/app/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/app/img/favicon.ico"/>
</head>
<!-- BEGIN BODY -->
<body class="login">
<!-- BEGIN LOGO -->
<div class="logo">
	<a href="index.html">
		<img src="${pageContext.request.contextPath}/resources/app/img/logo-big.png" alt=""/>
	</a>
</div>
<!-- END LOGO -->
<!-- BEGIN LOGIN -->
<div class="content">
    <spring:url var="authUrl" value="/static/j_spring_security_check" />
	<!-- BEGIN LOGIN FORM -->
	<form class="login-form" action="${authUrl}" method="post">
		<h3 class="form-title">企业信息查询管理平台登录</h3>
		<div class="alert alert-danger display-hide">
			<button class="close" data-close="alert"></button>
			<span>
				 请输入用户名和密码。
			</span>
		</div>
		<div class="form-group">
			<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
			<label class="control-label visible-ie8 visible-ie9">用户名</label>
			<div class="input-icon">
				<i class="fa fa-user"></i>
				<input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="用户名" name="j_username"/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label visible-ie8 visible-ie9">密码</label>
			<div class="input-icon">
				<i class="fa fa-lock"></i>
				<input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="密码" name="j_password"/>
			</div>
		</div>
		<div class="form-actions">
            <!--
			<label class="checkbox">
			<input type="checkbox" name="_spring_security_remember_me" value="1"/> 记住我 </label>
			-->
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<button type="submit" class="btn green pull-right">
			登录 <i class="m-icon-swapright m-icon-white"></i>
			</button>
		</div>
	</form>
	<!-- END LOGIN FORM -->
</div>
<!-- END LOGIN -->
<!-- BEGIN COPYRIGHT -->
<div class="copyright">
	 2014 &copy; Coccinella.
</div>
<!-- END COPYRIGHT -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
	<script src="${pageContext.request.contextPath}/resources/app/plugins/respond.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/plugins/excanvas.min.js"></script>
	<![endif]-->
<script src="${pageContext.request.contextPath}/resources/app/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${pageContext.request.contextPath}/resources/app/plugins/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/plugins/select2/select2.min.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${pageContext.request.contextPath}/resources/app/scripts/core/app.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/scripts/custom/login.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
		jQuery(document).ready(function() {     
		  App.init();
		  Login.init();
		});
	</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>