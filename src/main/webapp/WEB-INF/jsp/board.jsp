<%@page contentType='text/html' pageEncoding='UTF-8' %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix='vdab' uri='http://vdab.be/tags' %>
<fmt:setBundle basename='resourceBundles.teksten'/>
<fmt:message key='Terrarium' var="titel"/>
<!doctype html>
<html lang="nl">
<head>
    <vdab:head title="${titel}"/>
    
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load("current", {packages:["corechart"]});
        google.charts.setOnLoadCallback(drawChart);
        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['Organism', 'Number'],
                ['<fmt:message key="carnivore"/>', ${numberCarn}],
                ['<fmt:message key="herbivore"/>', ${numberHerb}],
                ['<fmt:message key="plant"/>', ${numberPlant}] ]);

            var options = {
                is3D: true,
                backgroundColor: 'transparent',
                chartArea:{left:0,top:0,width:'600',height:'200'},
                enableInteractivity:false,
                pieSliceText:'value',
            };

            var chart = new google.visualization.PieChart(document.getElementById('piechart'));
            chart.draw(data, options);
        }
    </script>
    
</head>
<body>
<vdab:menu/>

<h1>${titel}</h1>
<div id="Days">${numberDays}</div>
<div class="container">
	 	<div class="raster">
			<c:forEach var='rows' items='${organisms}'>
			<div class="row">
			<c:forEach var='organism' items='${rows}'>
					<div class="cell">  
						
						<c:if test='${not empty organism.url}'>
							<img src = '<c:url value="${organism.url}"/>' />
						</c:if>
						
						<c:if test='${empty organism.url}'>
							<img src = '<c:url value="images/dirt.png"/>' />
						</c:if>
						
						<c:if test="${not empty organism}" >
							<div class="lifeBol">${organism.life}</div>
						</c:if>
						
					</div>
			</c:forEach>
			</div></c:forEach>		
	
	    </div>
    </div>
</div>

<div id="msg">${exception}</div>  <!-- toon fouten hier -->
<form method='post'>
<input id="NextDay" type="submit" value="Next day.">
</form>

<div id="piechart"></div>

<vdab:footer/>

</body>
</html>