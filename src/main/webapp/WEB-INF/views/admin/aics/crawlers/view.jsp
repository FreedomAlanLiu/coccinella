<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="g" tagdir="/WEB-INF/tags" %>

<g:genericpage>
    <jsp:attribute name="page_styles">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/app/plugins/select2/select2.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/app/plugins/select2/select2-metronic.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/app/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/app/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
    </jsp:attribute>
    <jsp:attribute name="page_content">
        <!-- BEGIN PAGE HEADER-->
		<div class="row">
                <div class="col-md-12">
                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                    <h3 class="page-title">
                        信息抓取配置管理 <small>工商局企业信息抓取配置管理</small>
                    </h3>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>
		<!-- END PAGE HEADER-->
		<!-- BEGIN PAGE CONTENT-->
		<div class="row">
                <div class="col-md-12">
                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-globe"></i>工商局信息
                            </div>
                            <div class="tools">
                                <a href="javascript:;" class="collapse">
                                </a>
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <form class="form-horizontal">
                                <div class="form-body">
                                    <div class="form-group">
                                        <label class="control-label col-md-3">省份或者直辖市</label>
                                        <label class="control-label">${aic.province}</label>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">名称</label>
                                        <label class="control-label">${aic.name}</label>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">官网</label>
                                        <label class="control-label">${aic.website}</label>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">最大频率（每天最大抓取次数）</label>
                                        <label class="control-label">${aic.maxFrequency}</label>
                                    </div>
                                </div>
                            </form>
                            <!-- END FORM-->
                        </div>
                    </div>

                    <!-- BEGIN VALIDATION STATES-->
                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-globe"></i>抓取配置
                            </div>
                            <div class="tools">
                                <a href="javascript:;" class="collapse">
                                </a>
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <form class="form-horizontal">
                                <div class="form-body">
                                    <div class="form-group">
                                        <label class="control-label col-md-3">名称</label>
                                        <label class="control-label">${crawler.name}</label>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">URL</label>
                                        <label class="control-label">${crawler.url}</label>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">HTTP方法</label>
                                        <label class="control-label">${crawler.method}</label>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">参数</label>
                                        <label class="control-label">${crawler.params}</label>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">编码</label>
                                        <label class="control-label">${crawler.encode}</label>
                                    </div>
                                    <br/>
                                    <div class="portlet gren">
                                        <div class="portlet-title">
                                            <div class="caption">
                                                <i class="fa fa-edit"></i>解析列表
                                            </div>
                                            <div class="tools">
                                                <a href="javascript:;" class="collapse">
                                                </a>
                                            </div>
                                        </div>
                                        <div class="portlet-body">
                                            <table class="table table-striped table-hover table-bordered">
                                                <thead>
                                                <tr>
                                                    <th>
                                                        关键词
                                                    </th>
                                                    <th>
                                                        XPATH
                                                    </th>
                                                    <th>
                                                        正则表达式
                                                    </th>
                                                </tr>
                                                </thead>
                                                <c:forEach items="${crawler.parsers}" var="parser" varStatus="status">
                                                    <tr>
                                                        <td>${parser.nameKey}</td>
                                                        <td>${parser.xpath}</td>
                                                        <td>${parser.regex}</td>
                                                    </tr>
                                                </c:forEach>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <!-- END FORM-->
                        </div>
                    </div>
                    <!-- END VALIDATION STATES-->
                </div>
            </div>
		<!-- END PAGE CONTENT-->
    </jsp:attribute>
    <jsp:attribute name="page_scripts">
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/plugins/jquery-validation/dist/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/plugins/jquery-validation/dist/additional-methods.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/plugins/select2/select2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/plugins/bootstrap-wysihtml5/wysihtml5-0.3.0.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/plugins/ckeditor/ckeditor.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/plugins/bootstrap-markdown/lib/markdown.js"></script>
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
        <script src="${pageContext.request.contextPath}/resources/app/scripts/core/app.js"></script>
        <script src="${pageContext.request.contextPath}/resources/app/scripts/custom/crawler-form.js"></script>
        <script>
            jQuery(document).ready(function() {
            App.init();
            CrawlerForm.init();
        });
        </script>
        <!-- END PAGE LEVEL SCRIPTS -->
    </jsp:attribute>
</g:genericpage>