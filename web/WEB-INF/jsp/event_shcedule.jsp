<%-- 
    Document   : event_shcedule
    Created on : 23 ต.ค. 2559, 20:30:48
    Author     : QuartierLatin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prise | Schedule</title>
        <meta charset='utf-8' />
        <link href='https://fullcalendar.io/js/fullcalendar-3.0.1/fullcalendar.css' rel='stylesheet' />
        <link href='https://fullcalendar.io/js/fullcalendar-3.0.1/fullcalendar.print.css' rel='stylesheet' media='print' />
        <script src='https://fullcalendar.io/js/fullcalendar-3.0.1/lib/moment.min.js'></script>
        <script src='https://fullcalendar.io/js/fullcalendar-3.0.1/lib/jquery.min.js'></script>
        <script src='https://fullcalendar.io/js/fullcalendar-3.0.1/fullcalendar.min.js'></script>
        <script src='https://fullcalendar.io/js/fullcalendar-3.0.1/gcal.js'></script>
        <script>
	$(document).ready(function() {
	
		$('#calendar').fullCalendar({

			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,listYear'
			},

			displayEventTime: false, // don't show the time column in list view

			// THIS KEY WON'T WORK IN PRODUCTION!!!
			// To make your own Google API key, follow the directions here:
			// http://fullcalendar.io/docs/google_calendar/
			googleCalendarApiKey: 'AIzaSyDcnW6WejpTOCffshGDDb4neIrXVUA1EAE',
		
			// US Holidays
			//events: 'en.usa#holiday@group.v.calendar.google.com',
			
			eventClick: function(event) {
				// opens events in a popup window
				window.open(event.url, 'gcalevent', 'width=700,height=600');
				return false;
			},
			
			loading: function(bool) {
				$('#loading').toggle(bool);
			}
			
		});
		
	});

</script>
<style>

	body {
		margin: 40px 10px;
		padding: 0;
		font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
		font-size: 14px;
	}
		
	#loading {
		display: none;
		position: absolute;
		top: 10px;
		right: 10px;
	}

	#calendar {
		max-width: 900px;
		margin: 0 auto;
	}

</style>
</head>
<body>

	<div id='loading'>loading...</div>

	<div id='calendar'></div>

</body>
</html>
