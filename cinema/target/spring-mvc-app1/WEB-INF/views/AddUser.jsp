<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add Users using ajax</title>
    <script src="<%=request.getContextPath() %>/js/jquery.js"></script>
    <script type="text/javascript">
        var contexPath = "<%=request.getContextPath() %>";
    </script>
    <script src="<%=request.getContextPath() %>/js/user.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/style/app.css">
</head>
<body>
<h1>Add Users using Ajax ........</h1>
<table>
    <tr><td colspan="2"><div id="error" class="error"></div></td></tr>
    <tr><td>Enter your name : </td><td> <input type="text" id="name"><br/></td></tr>
    <tr><td>Education : </td><td> <input type="text" id="education"><br/></td></tr>
    <tr><td colspan="2"><input type="button" value="Add Users" onclick="doAjaxPost()"><br/></td></tr>
    <tr><td colspan="2"><div id="info" class="success"></div></td></tr>
</table>
</body>
</html>