$(function(){
	console.log("...");
	$( "a" ).click(function( event ) {
	  event.stopPropagation();
	});
	
	$( "#popup_window" ).on("click", ".popup_box", function( event ) {
	  event.stopPropagation();
	});
	
	
	$(window).on("load", function(){
		getPostlist();
	});
	$(window).on("scroll", function(){
		var _scroll = $(window).height() + $(window).scrollTop();
		var _height = $(document).height();
		
		if(_scroll+200 > _height){
			getPostlist();
		}
	});
	
	
	$("#popup_window").on("click", function(e){
		$("#popup_window").hide();
		$("body").css({"overflow":"auto"});
	});
});

function getPostlist(){
	
	$.ajax({
		url: 'postlist',
		type: 'get', 
		dataType: 'html', 
		data: { 
		}
	})
	.done(function (response) {
		$("#postlist").append(response);
	})
	.fail(function (response) {
	});
}

function getPost(pid){
	
	$("#popup_window .popup_box").empty();
	$("#popup_window").show();
	$("body").css({"overflow":"hidden"});
	
	
	$.ajax({
		url: 'postsingle',
		type: 'get', 
		dataType: 'html', 
		data: {
			_pid : pid
		}
	})
	.done(function (response) {
		$("#popup_window .popup_box").html(response);
	})
	.fail(function (response) {
	});
}