var CrawlersTable = function () {

    return {

        //main function to initiate the module
        init: function () {
            
            if (!jQuery().dataTable) {
                return;
            }

            // begin first table
            $('#crawlers_table').dataTable({
                "aoColumns": [
                  { "bSortable": false },
                  null,
                  null,
                  null,
                  null,
                  null,
                  { "bSortable": false }
                ],
                "aLengthMenu": [
                    [5, 15, 20, -1],
                    [5, 15, 20, "全部"] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 5,
                "sPaginationType": "bootstrap",
                "oLanguage": {
                    "sSearch": "查找",
                    "sLengthMenu": "每页显示 _MENU_ 条",
                    "sInfo": "共 _TOTAL_ 条",
                    "sInfoEmpty": "没有找到可以显示的记录",
                    "sGroupActions": "_TOTAL_ 条被选择：  ",
                    "sAjaxRequestGeneralError": "无法完成请求。请检查您的网络连接",
                    "sEmptyTable":  "表中无可用数据",
                    "sZeroRecords": "没有找到匹配的记录",
                    "oPaginate": {
                        "sPrevious": "上一页",
                        "sNext": "下一页",
                        "sPage": "页",
                        "sPageOf": "于"
                    }
                },
                "aoColumnDefs": [
                    { 'bSortable': false, 'aTargets': [0, 6] },
                    { "bSearchable": false, "aTargets": [0, 6] }
                ]
            });

            jQuery('#crawlers_table .group-checkable').change(function () {
                var set = jQuery(this).attr("data-set");
                var checked = jQuery(this).is(":checked");
                jQuery(set).each(function () {
                    if (checked) {
                        $(this).attr("checked", true);
                        $(this).parents('tr').addClass("active");
                    } else {
                        $(this).attr("checked", false);
                        $(this).parents('tr').removeClass("active");
                    }                    
                });
                jQuery.uniform.update(set);
            });

            jQuery('#crawlers_table').on('change', 'tbody tr .checkboxes', function(){
                 $(this).parents('tr').toggleClass("active");
            });

            jQuery('#crawlers_table_wrapper .dataTables_filter input').addClass("form-control input-medium input-inline"); // modify table search input
            jQuery('#crawlers_table_wrapper .dataTables_length select').addClass("form-control input-xsmall"); // modify table per page dropdown
            //jQuery('#crawlers_table_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
        }

    };

}();