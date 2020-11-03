<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
'
<div class="title-box cf">
	<div class="close-btn" onclick="closePopMyArea();">
		<svg viewBox="0 0 40 40" class="close-icon">
						<line x1="4.9" y1="4.9" x2="35.1" y2="35.1" />
					<line x1="35.1" y1="4.9" x2="4.9" y2="35.1" />'
						</svg>
	</div>
	<div class="tit">관심지역</div>
</div>
<ul>
	<li>
		<div class="area">${sigunguName } ${dongName }</div>
		<div class="delete-btn">x</div>
	</li>
	
</ul>