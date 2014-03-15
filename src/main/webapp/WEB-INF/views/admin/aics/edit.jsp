<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="g" tagdir="/WEB-INF/tags" %>

<g:genericpage>
    <jsp:attribute name="page_styles">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/app/plugins/select2/select2.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/app/plugins/select2/select2-metronic.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/app/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/app/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/plugins/data-tables/DT_bootstrap.css"/>
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
                            <form:form action="${pageContext.request.contextPath}/admin/aics/${aic.id}" method="PATCH" modelAttribute="aic" id="form_sample_2" class="form-horizontal">
                                <div class="form-body">
                                    <div class="alert alert-danger display-hide">
                                        <button class="close" data-close="alert"></button>
                                        您的表单出现错误，请检查！
                                    </div>
                                    <div class="alert alert-success display-hide">
                                        <button class="close" data-close="alert"></button>
                                        您的表单验证成功！
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">省份或者直辖市
										<span class="required">
											 *
										</span>
                                        </label>
                                        <div class="col-md-4">
                                            <div class="input-icon right">
                                                <i class="fa"></i>
                                                <form:input path="province" cssClass="form-control" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">名称
										<span class="required">
											 *
										</span>
                                        </label>
                                        <div class="col-md-4">
                                            <div class="input-icon right">
                                                <i class="fa"></i>
                                                <form:input path="name" cssClass="form-control" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">官网
										<span class="required">
											 *
										</span>
                                        </label>
                                        <div class="col-md-4">
                                            <div class="input-icon right">
                                                <i class="fa"></i>
                                                <form:input path="website" cssClass="form-control" />
                                            </div>
											<span class="help-block">
												 例如：http://www.demo.com or http://demo.com
											</span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">最大频率（每天最大抓取次数）
										<span class="required">
											 *
										</span>
                                        </label>
                                        <div class="col-md-4">
                                            <div class="input-icon right">
                                                <i class="fa"></i>
                                                <form:input path="maxFrequency" cssClass="form-control" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-actions fluid">
                                    <div class="col-md-offset-3 col-md-9">
                                        <button type="submit" class="btn green">提交</button>
                                        <button type="button" class="btn default">取消</button>
                                    </div>
                                </div>
                            </form:form>
                            <!-- END FORM-->
                        </div>
                    </div>

                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-globe"></i>抓取配置列表
                            </div>
                            <div class="tools">
                                <a href="javascript:;" class="collapse">
                                </a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="table-toolbar">
                                <div class="btn-group">
                                    <button id="sample_editable_1_new" class="btn green" onclick="javascript:location.href='${pageContext.request.contextPath}/admin/crawlers/new'">
                                        添加 <i class="fa fa-plus"></i>
                                    </button>
                                </div>
                                <div class="btn-group pull-right">
                                </div>
                            </div>
                            <table class="table table-striped table-bordered table-hover" id="crawlers_table">
                                <thead>
                                <tr>
                                    <th class="table-checkbox">
                                        <input type="checkbox" class="group-checkable" data-set="#crawlers_table .checkboxes"/>
                                    </th>
                                    <th>
                                        名称
                                    </th>
                                    <th>
                                        抓取URL
                                    </th>
                                    <th>
                                        HTTP方法
                                    </th>
                                    <th>
                                        参数
                                    </th>
                                    <th>
                                        编码
                                    </th>
                                    <th>
                                        操作
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="crawler" items="${aic.crawlers}">
                                    <tr class="odd gradeX">
                                        <td>
                                            <input type="checkbox" class="checkboxes" value="1"/>
                                        </td>
                                        <td>
                                            ${crawler.name}
                                        </td>
                                        <td>
                                            ${crawler.url}
                                        </td>
                                        <td>
                                            ${crawler.params}
                                        </td>
                                        <td>
                                            ${crawler.encode}
                                        </td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/admin/crawlers/${crawler.id}" class="btn default btn-xs blue">
                                                <i class="fa fa-share"></i> 查看
                                            </a>
                                            <a href="${pageContext.request.contextPath}/admin/crawlers/${crawler.id}/edit" class="btn default btn-xs purple">
                                                <i class="fa fa-edit"></i> 编辑
                                            </a>
                                            <a href="${pageContext.request.contextPath}/admin/crawlers/${crawler.id}/delete" class="btn default btn-xs black">
                                                <i class="fa fa-trash-o"></i> 删除
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
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
        <script src="${pageContext.request.contextPath}/resources/app/scripts/custom/aic-form.js"></script>
        <script src="${pageContext.request.contextPath}/resources/app/scripts/custom/crawlers-table.js"></script>
        <script>
            jQuery(document).ready(function() {
            App.init();
            AicForm.init();
            CrawlersTable.init();
        });
        <!-- END PAGE LEVEL SCRIPTS -->
    </jsp:attribute>
</g:genericpage>