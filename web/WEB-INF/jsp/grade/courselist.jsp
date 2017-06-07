<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../common/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<t:base type="calendar,tools,layer"></t:base>
<script>
function loadTime(){
	$.getJSON("${ pageContext.request.contextPath }/web/manager/course/list/time", function(res){
		  	if(res.success){
		  		$('#external-events .fc-event').remove();
		  		$.each(res.obj,function(i,item){
		  			 
		  			$("#drop-remove").parent().before("<div class='fc-event' id='"+item.id+"' >"+item.title+"</div>");
		  		})
		  		$('#external-events .fc-event').each(function() {
					$(this).data('event', {
						title: $.trim($(this).text()), // use the element's text as the event title
						stick: true // maintain when user navigates (see docs on the renderEvent method)
					});
		 
					$(this).draggable({
						zIndex: 999,
						revert: true,      // will cause the event to go back to its
						stop: function() {
						if ($('#drop-remove').is(':checked')) {//删除时间段
                            $(this).remove();
                            $.ajax({
                                type: "DELETE",
								dataType:"json",
                                url: "${ pageContext.request.contextPath }/web/manager/course/del/time/"+$(this).attr("id"),
                                success: function(res){
                                    if(!res.success){
                                        parent.layer.msg(res.msg);
                                        loadTime();
                                    }
                                }
                            });

						}
						},
						revertDuration: 0  //  original position after the drag
		 
					});
				});
		  	}
		});
}
	$(document).ready(function() {
 			
		loadTime();


		/* initialize the calendar
		-----------------------------------------------------------------*/

		$('#calendar').fullCalendar({
            events: function(start, end, timezone, callback) {
                $.ajax({
                    url: '${ pageContext.request.contextPath }/web/manager/course/list/gradetime/${gradId}',
                    dataType: 'json',
                    data: {
                        start: start.unix()*1000,
                        end: end.unix()*1000
                    },
                    success: function(res) {
                        if(res.success){
                            $("#calendar").fullCalendar("removeEvents");///这个很关键：要清除旧的方法，再执行下面方法
                            //       $('#calendar').fullCalendar('refetchEvents'); //重新获取所有事件数据
                            var events = [];
                            $.each(res.obj,function(i,item) {

                                events.push({
                                    id:item.id,
                                    title: $.myTime.UnixToDate(item.start, "hh:mm")+"-"+$.myTime.UnixToDate(item.end, "hh:mm"),
									start:item.start, // will be parsed
                                    allDay:true
                                });
                            });

                            callback(events);
                        }

                    }
                });
            },
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek'
			},
			titleFormat:"${graName} YY年 MMM D",	
			selectable: true,
			editable: true,
			droppable: true, // this allows things to be dropped onto the calendar
			eventLimit: true, // allow "more" link when too many events
			eventClick: function(calEvent, jsEvent, view) {
                openWinMax('${ pageContext.request.contextPath }/web/manager/gradereg/to/gtId/'+calEvent.id);
			},
			drop: function(date, jsEvent, ui, resourceId) {
                var ymd =new Date().format("yyyy-MM-dd",date);
				var s_e =$(this).html();
			 	if(s_e!=null&&s_e!=""){
			 	    var s_e_array =s_e.split("-");
			 	    var start =strTotimestamp(ymd+" "+s_e_array[0]);//开始时间;
					var end =strTotimestamp(ymd+" "+s_e_array[1]);//结束时间
                    add(start,end);

                }

			},
            eventDragStart:function(calEvent, jsEvent, ui, view){
                layer.confirm("此操作将会删除【"+calEvent.title+"】，确定要执行?", {
                    btn: ['是','否'], //按钮
                    shade: false //不显示遮罩
                }, function(){
                    del(calEvent.id);
                    layer.msg("删除成功", {shift: 6});
                }, function(){
                    layer.msg('决定好再操作', {shift: 6});
                    $('#calendar').fullCalendar( 'refetchEvents' ); //重新获取所有事件数据
                });

			},
			eventDragStop:function(calEvent, jsEvent, view) {//移动到指定位置触发事件:此处回调函数参数是原来参数

	  
			}/* ,
			eventDrop:function( event, delta, revertFunc) { //移动到指定位置触发事件:此处回调函数参数是最新参数
			 
				 var ymd =new Date().format("yyyy-MM-dd",event.start);
				 var s_e =event.title;
				 if(s_e!=null&&s_e!=""){
				 	    var s_e_array =s_e.split("-");
				 	    var start =strTotimestamp(ymd+" "+s_e_array[0]);//开始时间;
						var end =strTotimestamp(ymd+" "+s_e_array[1]);//结束时间
	                    add(start,end);

	                }
			} */
		});


	});

	function del(id){
		 
		if(typeof(id) == "undefined")
			return;
		$.ajax({
			  type: "DELETE",
			  url: "${pageContext.request.contextPath }/web/manager/course/del/gradetime/"+id,
			  dataType: "json",
			  success:function(res){
                  $('#calendar').fullCalendar( 'removeEvents',id);
			      // $('#calendar').fullCalendar( 'refetchEvents' ); //重新获取所有事件数据
			  }
			});
	}
	 function add(start,end){
		 $.post("${ pageContext.request.contextPath }/web/manager/course/add/gradetime/${gradId}",{start:start,end:end}, function(res) {
             if (!res.success) {
                 parent.layer.msg(res.msg);
             }else{
             	$('#calendar').fullCalendar( 'refetchEvents' ); //重新获取所有事件数据 
             }
         },"json");
	 }
</script>
<style>

	body {
		margin-top: 40px;
		text-align: center;
		font-size: 14px;
		font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
	}
		
	#wrap {
		width: 1100px;
		margin: 0 auto;
	}
		
	#external-events {
		float: left;
		width: 150px;
		padding: 0 10px;
		border: 1px solid #ccc;
		background: #eee;
		text-align: left;
	}
		
	#external-events h4 {
		font-size: 16px;
		margin-top: 0;
		padding-top: 1em;
	}
		
	#external-events .fc-event {
		margin: 10px 0;
		cursor: pointer;
	}
		
	#external-events p {
		margin: 1.5em 0;
		font-size: 11px;
		color: #666;
	}
		
	#external-events p input {
		margin: 0;
		vertical-align: middle;
	}

	#calendar {
		float: right;
		width: 900px;
	}

</style>
</head>
<body>
	<div id='wrap'>

		<div id='external-events'>
			<h4>上课时间段(可拖拽)</h4>
			<!-- 此处插入拖拽的时间 -->
			<p>
				 <input type='checkbox' id='drop-remove' />
				<label for='drop-remove'>拖拽后删除</label>
			</p>
			<p>
				 <input type='button' value="新建时间段"  onclick="openWin('${ pageContext.request.contextPath }/web/manager/course/to/add/time','新建时间段',500,450)"/>
			</p>
		</div>

		<div id='calendar'></div>

		<div style='clear:both'></div>

	</div>
</body>
</html>
