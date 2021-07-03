<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="layer/layer.js"></script>
<base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
<script>

    $(function (){
        layer.msg("aaa");
    });

    function test(){
        $.ajax({
            "url":"send/array.html",
            "type":"post",
            "data": {
                "array": [1,2,3]
            },
            "dataType":"text",
            "success": function (response){

            },
            "error": function (response){

            }
        });

    }
</script>

<body>
<h2>Hello World!</h2>
<a href="test/ssm.html">测试链接</a>
<button id="btn1" onclick="test()">ajax1</button>
</body>
</html>
