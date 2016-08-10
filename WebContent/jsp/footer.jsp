<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="body1">
        <div class="main">
            <footer>
                &copy; <fmt:message key="label.author" />, 2016
            </footer>
        </div>
</div>
<div class="b-popup" id="popup1" style="z-index:10">
				<div class="b-popup-content"> 
                	<span id="message"></span>
					<br><br>
					<a href="javascript:PopUpHide()"><fmt:message key="menu.close_window" /></a>
					</div>
			</div>