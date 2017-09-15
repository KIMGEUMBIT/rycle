<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<header id="header">
    <ul class="header-inner">
        <li class="pull-left" id="sideNav_open"><a href="#"><i class="icon-menu"></i> <span class="hidden-xs">MENU</span></a></li>
        <li class="pull-left"><a href=""><i class="icon-search"></i></a></li>
        <li class="logo"><a href="sub.html">LYCLE</a></li>
        <li class="pull-right"><a href="${path}/member/write.do">JOIN</a> &nbsp;/&nbsp; <a href="">LOGIN</a></li>
    </ul>
</header>