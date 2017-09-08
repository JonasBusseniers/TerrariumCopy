<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form>
<input type= submit Value="Autoplay"></form>
<script>
var isPlaying;
function autoplay()
{
if (isPlaying){
isPlaying = false;
clearTimeout(auto);
} else {
isPlaying = true;

var auto = setTimeout(function(){ autoRefresh(); }, 50);
}
        function submitform(){
          alert('test');
document.getElementsById(nextDayForm).submit();
        }

        function autoRefresh(){
           clearTimeout(auto);
           auto = setTimeout(function(){ submitform(); autoRefresh(); }, 10000);
        }
    }

}
</script>
</body>
</html>