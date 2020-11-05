<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="search-result-content" id="SearchResultContent">
	<div class="data">
		<h2 class="tit"><span class="highlight01">${dong.name}</span>에 <span class="highlight01">${industry.name}</span>을(를) 창업 할 경우 전망은?</h2>
		<div class="result-box">
			<div class="icon-box" id="resultIconBox">
			</div>
			<div class="result-txt" id="resultTxt"></div>
		</div>
	</div>
	
	<div class="data">
		<h2 class="tit">해당지역 상권 등급은 <span class="highlight01" id="point"></span>점으로 <span class="highlight01"><span id="grade"></span>등급</span>입니다.</h2>
		<div id="chartContainer03">
		</div>
	</div>
	
	<div class="data">
		<h2 class="tit-small">상권 등급 상세정보</h2>
		<table class="tbl">
			<tr>
				<th colspan="2">업소수</th>
				<th colspan="2">월매출 평균</th>
				<th colspan="2">지역별 개폐업률</th>
				<th colspan="2">평균운영연수</th>
				<th rowspan="2">인구밀도</th>
			</tr>
			<tr>
				<th>전체</th>
				<th>선택업종</th>
				<th>건수</th>
				<th>매출액</th>
				<th>개업률</th>
				<th>폐업률</th>
				<th>전체</th>
				<th>선택업종</th>
			</tr>
			<tr>
				<td>${IndustryCount[0].count}</td>
				<td>
					<span class="highlight02">
						<c:if test="${IndustryCount[1].count == null}">
							<c:out value="0" />
						</c:if>
						<c:out value="${IndustryCount[1].count}" />
					</span>
				</td>
				<td>${salesAvg.avgCount}<span>건</span></td>
				<td><span class="highlight02">${salesAvg.avgSum}<span>만원</span></span></td>
				<td>${newAndFailRate.newRate}<span>%</span></td>
				<td><span class="highlight02">${newAndFailRate.failRate}<span>%</span></span></td>
				<td>8.3</td>
				<td><span class="highlight02">6.5</span></td>
				<td>${Population.density} <span>명/㎢</span></td>
			</tr>
		</table>
	</div>
</div>