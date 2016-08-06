<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<article class="col1">
<h2>
	<fmt:message key="label.search" />
</h2>
<ul class="tabs">
	<li><a id="o_ticket" href="javascript:;"
		onclick="click_o_ticket();" class="active"> <fmt:message
				key="label.tickets" />
	</a></li>
	<li class='end'><a id='o_hotel' href='javascript:;'
		onclick='click_o_hotel();'><fmt:message key="label.hostels" /></a></li>
</ul>
<div class='tabs_cont'>
	<form method="post" id='form_order'>
		<div class='wrapper invisible' id='hostelCity'>
			<fmt:message key="label.city" />
			<input type="text" name="city" required style="width: 100%">
		</div>

		<div class='wrapper' id='start'>
			<fmt:message key="label.from" />
			<!--select style='width: 100%' name='from'>
                                    <option value='0'> Минск </option>
                                </select-->
			<input type="search" name="from" style="width: 100%">
		</div>

		<div class='wrapper' id='finish'>
			<fmt:message key="label.to" />
			<!--select style='width: 100%' name='to'>
                                    <option value='0'> Нет билета </option>
                                </select-->
			<input type="text" name="to" required style="width: 100%">
		</div>

		<div class='wrapper'>
			<fmt:message key="label.date" />
			<input class="datepicker" id="departDate" name='departDate' required
				style='width: 90%;'>
		</div>

		<div class='wrapper' id="type">
			<fmt:message key="label.tickettype" />
			<select style='width: 100%' name='ticket-type'>
				<option value='0'>Эконом</option>
				<option value='1'>Первый</option>
				<option value='2'>Бизнес</option>
			</select>
		</div>

		<div class='wrapper invisible' id="roomCount">
			<fmt:message key="label.roomcount" />
			<select style='width: 100%' name='room-number'>
				<option value='1'>1</option>
				<option value='2'>2</option>
				<option value='3'>3</option>
				<option value='4'>4</option>
				<option value='5'>5</option>
			</select>
		</div>
		<button type='submit' class='button' style='cursor: pointer;'>
			<fmt:message key="label.search" />
		</button>
	</form>
</div>
</article>