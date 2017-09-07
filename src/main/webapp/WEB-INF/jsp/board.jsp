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
                ['Dirt', ${numberDirt}],
                ['Carnivores', ${numberCarn}],
                ['Herbivores', ${numberHerb}],
                ['Plants', ${numberPlant}] ]);

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

<div class="container">
	<div id="Days">${numberDays}</div>
				
    <div class="raster">
		<c:forEach var='organism' items='${organisms}'>
				<div class='${organism.key}'>  
					<img src = '<c:url value="${organism.value.url}"/>' />
					${organism.value.life}
				</div>
		</c:forEach>		

    </div>
</div>

<div id="msg"></div>  <!-- toon fouten hier -->
<input id="NextDay" type="submit" value="Next day.">

<div id="piechart"></div>

<vdab:footer/>

</body>
</html>