<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="g" tagdir="/WEB-INF/tags" %>

<g:genericpage>
    <jsp:attribute name="page_styles">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/app/plugins/select2/select2.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/app/plugins/select2/select2-metronic.css"/>
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
                    <!-- BEGIN EXAMPLE TABLE PORTLET-->
                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-globe"></i>工商局信息抓取配置列表
                            </div>
                            <div class="tools">
                                <a href="javascript:;" class="collapse">
                                </a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="table-toolbar">
                                <div class="btn-group">
                                    <button id="sample_editable_1_new" class="btn green" onclick="javascript:location.href='${pageContext.request.contextPath}/admin/aics/new'">
                                        添加 <i class="fa fa-plus"></i>
                                    </button>
                                </div>
                                <div class="btn-group pull-right">
                                </div>
                            </div>
                            <table class="table table-striped table-bordered table-hover" id="aics_table">
                                <thead>
                                <tr>
                                    <th class="table-checkbox">
                                        <input type="checkbox" class="group-checkable" data-set="#aics_table .checkboxes"/>
                                    </th>
                                    <th>
                                        省份
                                    </th>
                                    <th>
                                        名称
                                    </th>
                                    <th>
                                        官网
                                    </th>
                                    <th>
                                        每天最多抓取次数
                                    </th>
                                    <th>
                                        操作
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="aic" items="${aics}">
                                    <tr class="odd gradeX">
                                        <td>
                                            <input type="checkbox" class="checkboxes" value="1"/>
                                        </td>
                                        <td>
                                            ${aic.province}
                                        </td>
                                        <td>
                                            ${aic.name}
                                        </td>
                                        <td>
                                            ${aic.website}
                                        </td>
                                        <td>
                                            ${aic.maxFrequency}
                                        </td>
                                        <td>
                                            <form:form action="${pageContext.request.contextPath}/admin/aics/${aic.id}" method="DELETE">
                                                <div class="btn-group">
                                                    <button type="button" class="btn default btn-xs blue" onclick="javascript:location.href='${pageContext.request.contextPath}/admin/aics/${aic.id}'">
                                                        <i class="fa fa-share"></i> 查看
                                                    </button>
                                                    <button type="button" class="btn default btn-xs purple" onclick="javascript:location.href='${pageContext.request.contextPath}/admin/aics/${aic.id}/edit'">
                                                        <i class="fa fa-edit"></i> 编辑
                                                    </button>
                                                    <button type="submit" class="btn default btn-xs black" onclick="return confirm('确定要删除${aic.name}吗？');">
                                                        <i class="fa fa-trash-o"></i> 删除
                                                    </button>
                                                </div>
                                            </form:form>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- END EXAMPLE TABLE PORTLET-->
                </div>
            </div>
		<!-- END PAGE CONTENT-->
    </jsp:attribute>
    <jsp:attribute name="page_scripts">
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/plugins/select2/select2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/plugins/data-tables/jquery.dataTables.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/plugins/data-tables/DT_bootstrap.js"></script>
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
        <script src="${pageContext.request.contextPath}/resources/app/scripts/core/app.js"></script>
        <script src="${pageContext.request.contextPath}/resources/app/scripts/custom/aics-table.js"></script>
        <script>
            jQuery(document).ready(function() {
                App.init();
                AicsTable.init();
        });
        </script>
        <!-- END PAGE LEVEL SCRIPTS -->
    </jsp:attribute>
</g:genericpage>