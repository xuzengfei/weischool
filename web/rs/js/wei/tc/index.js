$(function () {

})
/**
 * 列出用户
 */
function list() {
    $.getJSON(basePath + "wei/tc/data", function (res) {
        if (res.success) {
            var data = res.obj;
            var week = data.week;
            var date = data.date;
            var tcName =data.tcName;
            var emNo = data.emNo;
            var gradeDatas =data.gradeDatas;
            $(".classTop h3").html(tcName);
            $(".classTop h5").html("任教工号："+emNo);
            $.each(gradeDatas,function(i,item){
                var html="<div class="list-group">";
                html+="<div class="addressChoose">";
                html+="     <img src="${pageContext.request.contextPath}/rs/css/wei/images/teach_point.png" class="point">";
                html+="   <p>五桂山校区</p>";
                html+="  </div>";
                    <div class="list-group-item">
                    <button class="statue">课程结束</button>
                    <p class="className">跆拳道初级班</p>
                    <p class="time">周六 08:30-10:00</p>
                <a href="teach_callName.html"><button class="callNameBtn">点名</button></a>
                    </div>
                    <div class="list-group-item">
                    <button class="statue">正在上课</button>
                    <p class="className">跆拳道初级班</p>
                    <p class="time">周六 08:30-10:00</p>
                <a href="teach_callName.html"><button class="callNameBtn" >点名</button></a>
                    </div>
                    </div>
            })
        }
    });
}

