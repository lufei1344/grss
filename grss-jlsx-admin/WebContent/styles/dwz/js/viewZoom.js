function magnify(event,index){
		sign = index;
		$(event.target).parent().css("width","300px");
		is_Scale = true;
	}
function shrink(event,index){
	sign = index;
	$(event.target).parent().css("width","30px");
	is_Scale = false;
}
function magnify_td(tag){
	$(tag).find("tr").each(function(i){
		$(tag).find("tr").eq(i).find("td").eq(sign).css("width","300px");
	});
}
function shrink_td(tag){
	$(tag).find("tr").each(function(i){
		$(tag).find("tr").eq(i).find("td").eq(sign).css("width","30px");
	});
}