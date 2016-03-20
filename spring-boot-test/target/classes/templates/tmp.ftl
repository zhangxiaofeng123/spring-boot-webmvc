<html>
	<#-- 这是一个测试-->
	<head>
		<title>freemark测试</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" style="text/css" href="/css/mycss.css" />
		<script type="text/javascript" src="/js/mytest.js"></script>
	</head>
	<#-- 这是一个assign值得测试-->
	<#assign name="zhang" desc="this is a 测试" "t"="time"/>
	
	<#assign m>哈喽</#assign>
	
	
	<#assign seq=[1,2,3,4,5,6,7,8,9,10]/>
	<#function add a b>
		<#return a+b>
	</#function>
	
	<#function staticTotal nums...>
		<#if nums?size==0>
			<#return 0>
		</#if>
		<#local sum=0>
		<#list nums as num>
			<#local sum=sum+num>
		</#list>
		<#return sum>
	</#function>
	
	<#setting locale="en_US">
	<#-- defaul is number, currency, computer, percent -->
	<#setting number_format="computer">
	<#-- 布尔值的默认值，yes，no -->
	<#setting boolean_format="true,false">
	<#assign money=2400000000000/>
	
	<body>
		<p>${name} says:"${m},${desc}, ${t}"</p>
		${add(10,20)}<br>
		${staticTotal()}<br>
		${money}
		${msg}
		<div class="ceshi" onclick="test();">
			这是一个测试
		</div>
	</body>
</html>