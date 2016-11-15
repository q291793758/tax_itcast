<%@ page import="java.util.Calendar" %>
<jsp:include page="/common/header.jsp"></jsp:include>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="/common/header.jsp" %>
    <title>年度投诉统计图</title>
    <script type="text/javascript" src="${basePath}js/fusioncharts/fusioncharts.js"></script>
    <script type="text/javascript" src="${basePath}js/fusioncharts/themes/fusioncharts.theme.fint.js"></script>

    <script type="text/javascript">

        //页面加载完毕,默认加载当前年份
        $(document).ready(doAnnualStatistic());

        //获取年份,传送服务器,获取投诉统计
        function doAnnualStatistic() {
            var year = $("#year option:selected").val();
            if (year == "" || year == undefined) {
                year = new Date().getFullYear();
            }
            $.ajax({
                        url: "${bathPath}nsfw/complain_getAnnualStatisticData.action",
                        data: {"year": year},
                        type: "post",
                        dataType: "json",
                        success: function (data) {
                            if (data != null && data != "" && data != undefined) {

                                var revenueChart = new FusionCharts({
                                    "type": "line",     //曲线图
                                    "renderAt": "chartContainer",  //渲染到哪里
                                    "width": "600",
                                    "height": "400",
                                    "dataFormat": "json",
                                    "dataSource": {
                                        "chart": {
                                            "caption": year + "年年度投诉统计图",
//                                "subCaption": "Harry's SuperMart",
                                            "xAxisName": "月份",
                                            "yAxisName": "投诉数",
                                            "theme": "fint"
                                        },
                                        "data": data.chartData,
                                    }

                                });
                                revenueChart.render();

                            } else {
                                alert("统计投诉信息无法获取,请稍后尝试!");

                            }
                        },
                        error: function () {
                            alert("统计投诉信息无法获取,请稍后尝试!");
                        }
                    }
            );
        }

    </script>
</head>

<body>
<br>
<div style="text-align: center;width: 100%;">
    <s:select id="year" list="#years" onchange="doAnnualStatistic()"></s:select>
</div>
<br>
<div id="chartContainer" style="text-align: center;width: 100%;"></div>
</body>
</html>
