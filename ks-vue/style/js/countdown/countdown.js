function initTime( obj, nowTime ,startTime){
	var cha = startTime.getTime() - nowTime.getTime();
    if (cha <= 0) {
        cha = 0;
    }
    var countDown = cha/1000;
   	beginTime( obj,countDown);
}
/*时间*/
function beginTime(obj,countDown){
	ZooFun(obj ,countDown);
	var interval = setInterval(function(){
		countDown--;
		console.log(countDown);
		if (countDown <= 0) {
           clearInterval(interval);
           countDown = 0;
        }
        ZooFun(obj ,countDown);
	},1000);
}
/*时间补零函数*/
function ZooFun(obj ,countDown){
	var oDay =  parseInt(countDown/(24*60*60));
	var oHours = parseInt(countDown/(60*60)%24,10);
	oHours = parseInt(oDay * 24 + oHours);
	var oMinutes = parseInt(countDown/60%60,10);
	var oSeconds = parseInt(countDown%60,10);

	var str = '';
	var oHouL = oHours.toString().substring(0,1);
	var oHouR = oHours.toString().substring(1,2);
	var oMinutesL = oMinutes.toString().substring(0,1);
	var oMinutesR = oMinutes.toString().substring(1,2);
	var oSecondsL = oSeconds.toString().substring(0,1);
	var oSecondsR = oSeconds.toString().substring(1,2);
	console.log(oMinutes)
	/*有小时*/
	if(oDay>0){
		/*============天=============*/
		/*if(oDay<10){
			str+='0';
			str+=oDay;
		}else{
			str+=oDay;
		}
		str+='天';*/
		/*============小时=============*/
		if(oHours<10){
			str+='0';
			str+=oHours;
		}else{
			str+=oHouL;
			str+=oHouR;
		}
		str+=':'
		/*============分=============*/
		if(oMinutes<10){
			str+='0';
			str+=oMinutes;
		}else{
			str+=oMinutesL;
			str+=oMinutesR;
		}
		str+=':';
		
		if(oSeconds<10){
			str+='0';
			str+=oSeconds;
		}else{
			str+=oSecondsL;
			str+=oSecondsR;
		}
	/*没有 天*/
	}else{
		/*============天=============*/
		/*str+='00天';*/
		/*============小时=============*/
		if(oHours<10){
			str+='0';
			str+=oHours;
		}else{
			str+=oHouL;
			str+=oHouR;
		}
		str+=':'
		/*============分=============*/
		if(oMinutes<10){
			str+='0';
			str+=oMinutes;
		}else{
			str+=oMinutesL;
			str+=oMinutesR;
		}
		str+=':';
		
		if(oSeconds<10){
			str+='0';
			str+=oSeconds;
		}else{
			str+=oSecondsL;
			str+=oSecondsR;
		}
	}
	console.log(str)
	$(obj).html(str);
}