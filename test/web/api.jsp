<%--
  Created by IntelliJ IDEA.
  User: 35517_000
  Date: 2015/3/3
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%
    out.clear();

    String action = request.getParameter("action");

    if (action!=null) {
        if (action.equals("login")) {
            out.print("{\"status\":1,\"token\":\"asdasd\"}");
        }
    }
    else {
        out.print("请指定action");
    }
%>
