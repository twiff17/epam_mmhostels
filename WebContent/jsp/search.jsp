<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<article class="col1">
	<h2>
		<fmt:message key="label.search" />
	</h2>
	<ul class="tabs">
		<li><a id="o_date" href="javascript:;"
			onclick="click_o_date();" class="active"> По дате
		</a></li>
		<li class='end'><a id='o_price' href='javascript:;'
			onclick='click_o_price();'><fmt:message key="label.hostels" /></a></li>
	</ul>
	<div class='tabs_cont'>
		<form method="post" action="Controller" id='form_by_date'>
			<input type="hidden" id="command" name="command" value="search_by_date">

			<div class='wrapper' id='country'>
				<fmt:message key="table.hostel.country" />
				<input type="text" name="country" required style="width: 100%">
			</div>

			<div class='wrapper'>
				<fmt:message key="label.in_date" />
				<input type="date" id="inDateSearch" name='inDate' required
					style='width: 100%;'>
			</div>

			<div class='wrapper'>
				<fmt:message key="label.out_date" />
				<input type="date" id="outDateSearch" name='outDate' required
					style='width: 100%;'>
			</div>
			<input type='submit' class='button' style='cursor: pointer;'
				value="<fmt:message key="label.search" />"/>
		</form>
		<form class="invisible" method="post" action="Controller" id='form_by_price'>
			<input type="hidden" id="command" name="command" value="search_by_price">

			<div class='wrapper' id='country'>
				<fmt:message key="table.hostel.country" />
				<input type="text" name="country" required style="width: 100%">
			</div>

			<div class='wrapper'>
				<fmt:message key="label.in_date" />
				<input type="number" id="inDateSearch" name='inDate' required
					style='width: 100%;'>
			</div>

			<input type='submit' class='button' style='cursor: pointer;'
				value="<fmt:message key="label.search" />"/>
		</form>
	</div>
</article>