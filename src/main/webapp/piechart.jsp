<%@page contentType='text/html' pageEncoding='UTF-8' %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix='vdab' uri='http://vdab.be/tags' %>
<fmt:setBundle basename='resourceBundles.teksten'/>
<fmt:message key='pieChart' var="titel"/>
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
                ['Dirt', 20<%-- ${numberDirt}--%>],
                ['Carnivores', 3<%-- ${numberCarn}--%>],
                ['Herbivores', 8<%-- ${numberHerb}--%>],
                ['Plants', 5<%-- ${numberPlant}--%>]
            ]);

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

<div id="piechart"></div>
<form method="get" action="/board.htm">
    <input type="submit" value="Go to the next day" />
</form>
<label><span class="text">hallo input</span><input><span class="error">neen</span></label>
<label><span class="text">hallo inputgfhgfhfgh dfgh</span><input></label>

<vdab:footer/>
</body>
</html>