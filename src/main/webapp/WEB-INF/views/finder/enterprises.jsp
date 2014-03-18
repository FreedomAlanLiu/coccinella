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
    <title>Metronic | Page Layouts - Dashboard & Mega Menu</title>
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
    <!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
    <link href="${pageContext.request.contextPath}/resources/app/plugins/gritter/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/resources/app/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/resources/app/plugins/fullcalendar/fullcalendar/fullcalendar.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/resources/app/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/resources/app/plugins/jquery-easy-pie-chart/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css"/>
    <!-- END PAGE LEVEL PLUGIN STYLES -->
    <!-- BEGIN THEME STYLES -->
    <link href="${pageContext.request.contextPath}/resources/app/css/style-metronic.css" rel="stylesheet" type="text/css"/>

    <link href="${pageContext.request.contextPath}/resources/app/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/resources/app/css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/resources/app/css/plugins.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/resources/app/css/pages/tasks.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/resources/app/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="${pageContext.request.contextPath}/resources/app/css/custom.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/resources/app/css/pages/search.css" rel="stylesheet" type="text/css"/>
    <!-- END THEME STYLES -->
    <link rel="shortcut icon" href="favicon.ico"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-full-width">
<!-- BEGIN HEADER -->
<div class="header navbar navbar-fixed-top mega-menu">
<!-- BEGIN TOP NAVIGATION BAR -->
<div class="header-inner">
<!-- BEGIN LOGO -->
<a class="navbar-brand" href="${pageContext.request.contextPath}">
    <img src="${pageContext.request.contextPath}/resources/app/img/logo.png" alt="logo" class="img-responsive"/>
</a>
<!-- END LOGO -->
</div>
<!-- END TOP NAVIGATION BAR -->
</div>
<!-- END HEADER -->
<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<div class="page-container">
<!-- BEGIN CONTENT -->
<div class="page-content-wrapper">
<div class="page-content">
    <c:forEach var="enterprise" items="${enterprises.content}">
        <div class="search-classic">
            <h4>
                <a data-toggle="modal" href="#modal_${enterprise.id}">
                    ${enterprise.name}
                </a>
            </h4>
            <p>
                地址：${enterprise.address}  状态:${enterprise.currentStatus}
            </p>
        </div>
        <div class="modal fade" id="modal_${enterprise.id}" tabindex="-1" role="basic" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                        <h4 class="modal-title">${enterprise.name}</h4>
                    </div>
                    <div class="modal-body">
                        <div class="scroller" style="height:300px" data-always-visible="1" data-rail-visible1="1">
                            <div class="row">
                                <div class="col-md-3">
                                    <p>
                                        <span>名称：</span>
                                    </p>
                                    <p>
                                        <span>注册编号：</span>
                                    </p>
                                    <p>
                                        <span>法人：</span>
                                    </p>
                                    <p>
                                        <span>地址：</span>
                                    </p>
                                    <p>
                                        <span>注册资本：</span>
                                    </p>
                                    <p>
                                        <span>企业状态：</span>
                                    </p>
                                    <p>
                                        <span>公司类型：</span>
                                    </p>
                                    <p>
                                        <span>成立日期：</span>
                                    </p>
                                    <p>
                                        <span>登记机关：</span>
                                    </p>
                                    <p>
                                        <span>经营范围：</span>
                                    </p>
                                </div>
                                <div class="col-md-9">
                                    <p>
                                        <span>${enterprise.name}</span>
                                    </p>
                                    <p>
                                        <span>${enterprise.registrationNumber}</span>
                                    </p>
                                    <p>
                                        <span>${enterprise.agent}</span>
                                    </p>
                                    <p>
                                        <span>${enterprise.address}</span>
                                    </p>
                                    <p>
                                        <span>${enterprise.registeredCapital}</span>
                                    </p>
                                    <p>
                                        <span>${enterprise.currentStatus}</span>
                                    </p>
                                    <p>
                                        <span>${enterprise.type}</span>
                                    </p>
                                    <p>
                                        <span>${enterprise.establishmentDate}</span>
                                    </p>
                                    <p>
                                        <span>${enterprise.registrationAuthority}</span>
                                    </p>
                                    <p>
                                        <span>${enterprise.scope}</span>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <form:form action="${pageContext.request.contextPath}/enterprises?p=0" method="post" modelAttribute="condition" id="research_crawl_form">
                            <form:hidden path="province" value="${enterprise.aic.province}"/>
                            <form:hidden path="enterpriseName" value="${enterprise.name}"/>
                            <form:hidden path="cache" value="false"/>
                        </form:form>
                        <button id="research_button" type="button" class="btn blue" data-dismiss="modal">重新抓取</button>
                        <button type="button" class="btn default" data-dismiss="modal">关闭</button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
    </c:forEach>
    <div class="margin-top-20">
        <ul class="pagination">
            <c:if test="${enterprises.number > 0}">
                <li>
                    <a href="${pageContext.request.contextPath}/enterprises?province=${condition.province}&enterpriseName=${condition.enterpriseName}&cache=true&p=${enterprises.number - 1}">
                        上一页
                    </a>
                </li>
            </c:if>
            <c:forEach var="p" begin="0" end="${enterprises.totalPages - 1}" step="1">
                <li <c:if test="${p == enterprises.number}">class="active"</c:if>>
                    <a href="${pageContext.request.contextPath}/enterprises?province=${condition.province}&enterpriseName=${condition.enterpriseName}&cache=true&p=${p}">
                        ${p + 1}
                    </a>
                </li>
            </c:forEach>
            <c:if test="${enterprises.number < enterprises.totalPages - 1}">
                <li>
                    <a href="${pageContext.request.contextPath}/enterprises?province=${condition.province}&enterpriseName=${condition.enterpriseName}&cache=true&p=${enterprises.number + 1}">
                        下一页
                    </a>
                </li>
            </c:if>
        </ul>
    </div>
</div>
</div>
<!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<div class="footer">
    <div class="footer-inner">
        2014 &copy; Coccinella.
    </div>
    <div class="footer-tools">
		<span class="go-top">
			<i class="fa fa-angle-up"></i>
		</span>
    </div>
</div>
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="${pageContext.request.contextPath}/resources/app/plugins/respond.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/excanvas.min.js"></script>
<![endif]-->
<script src="${pageContext.request.contextPath}/resources/app/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${pageContext.request.contextPath}/resources/app/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${pageContext.request.contextPath}/resources/app/plugins/jqvmap/jqvmap/jquery.vmap.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/jqvmap/jqvmap/maps/jquery.vmap.russia.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/jqvmap/jqvmap/maps/jquery.vmap.world.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/jqvmap/jqvmap/maps/jquery.vmap.europe.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/jqvmap/jqvmap/maps/jquery.vmap.germany.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/jqvmap/jqvmap/maps/jquery.vmap.usa.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/jqvmap/jqvmap/data/jquery.vmap.sampledata.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/flot/jquery.flot.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/flot/jquery.flot.resize.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/flot/jquery.flot.categories.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/jquery.pulsate.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/bootstrap-daterangepicker/moment.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/bootstrap-daterangepicker/daterangepicker.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/gritter/js/jquery.gritter.js" type="text/javascript"></script>
<!-- IMPORTANT! fullcalendar depends on jquery-ui-1.10.3.custom.min.js for drag & drop support -->
<script src="${pageContext.request.contextPath}/resources/app/plugins/fullcalendar/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/jquery-easy-pie-chart/jquery.easy-pie-chart.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/jquery.sparkline.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${pageContext.request.contextPath}/resources/app/scripts/core/app.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    jQuery(document).ready(function() {
        App.init(); // initlayout and core plugins

        var researchButton = $('#research_button');
        researchButton.click(function() {
            $.blockUI({ message: '<h4>抓取中...</h4>' });
            $('#research_crawl_form').submit();
        });
    });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>