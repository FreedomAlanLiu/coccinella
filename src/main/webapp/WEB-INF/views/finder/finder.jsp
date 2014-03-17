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
    <title>Coccinella</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>

    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css"/>
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
    <link href="${pageContext.request.contextPath}/resources/app/css/pages/lock.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/resources/app/css/custom.css" rel="stylesheet" type="text/css"/>
    <!-- END THEME STYLES -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/app/img/favicon.ico"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body>
<div class="page-lock">
    <div class="page-logo">
        <a class="brand" href="#">
            <img src="${pageContext.request.contextPath}/resources/app/img/logo-big.png" alt="logo"/>
        </a>
    </div>
    <div class="page-body">
        <img class="page-lock-img" src="${pageContext.request.contextPath}/resources/app/img/enterprise.jpg" alt="">
        <span> </span>
        <div class="page-lock-info">
            <h1>企业信息查询</h1>
            <form:form id="finder_form" action="${pageContext.request.contextPath}/enterprises" modelAttribute="condition" cssClass="form-horizontal col-md-3" method="post">
                <div class="form-group">
                    <form:select path="province" cssClass="form-control input-medium select2me" data-placeholder="选择一个省份或者直辖市...">
                        <option value=""></option>
                        <c:forEach var="aic" items="${aics}">
                            <option>${aic.province}</option>
                        </c:forEach>
                    </form:select>
                </div>
                <div class="form-group">
                    <form:input path="enterpriseName" cssClass="form-control input-medium" placeholder="企业名称" />
                </div>
                <div class="form-group">
				    <button type="button" class="btn blue icn-only" id="finder_submit_button">查询 <i class="m-icon-swapright m-icon-white"></i></button>
                </div>
            </form:form>
        </div>
    </div>
    <div class="page-footer">
        2014 &copy; Coccinella.
    </div>
</div>
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
<script src="${pageContext.request.contextPath}/resources/app/plugins/backstretch/jquery.backstretch.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/plugins/select2/select2.min.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<script src="${pageContext.request.contextPath}/resources/app/scripts/core/app.js"></script>
<script>
    var Lock = function () {
        return {
            //main function to initiate the module
            init: function () {
                $.backstretch([
                    "${pageContext.request.contextPath}/resources/app/img/bg/1.jpg",
                    "${pageContext.request.contextPath}/resources/app/img/bg/2.jpg",
                    "${pageContext.request.contextPath}/resources/app/img/bg/3.jpg",
                    "${pageContext.request.contextPath}/resources/app/img/bg/4.jpg"
                ], {
                    fade: 1000,
                    duration: 8000
                });
            }
        };
    }();

    jQuery(document).ready(function() {
        App.init();
        Lock.init();

        $('#finder_submit_button').click(function() {
            $('#finder_form').submit();
            $.blockUI({ message: "<h4><img style='' src='${pageContext.request.contextPath}/resources/app/img/loading-spinner-grey.gif'/> 查询中...</h4>" });
        });
    });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>