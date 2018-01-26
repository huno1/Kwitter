$(function(){
	console.log("main.js loaded");

	$( "a" ).click(function( event ) {
	  event.stopPropagation();
	});
	
	$( "#popup_window" ).on("click", ".popup_box", function( event ) {
	  event.stopPropagation();
	});
	
	
	$(window).on("load", function(){
		sessionStorage.setItem('curpost','>0');
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
		$("#popup_window .popup_box").empty();
	});
});

function getPostlist(){
	
	if(window.sessionStorage.getItem('loading')=="1"){
		return false;
	}
	
	sessionStorage.setItem('loading',"1");
	/*if(window.sessionStorage.getItem('curpost')==null ){
		sessionStorage.setItem('curpost','>0');
	}*/
	var pid = window.sessionStorage.getItem('curpost');
	
	$.ajax({
		url: 'postnext',
		type: 'get', 
		dataType: 'html', 
		data: { 
			_pid : pid
		}
	})
	.done(function (response) {
		$("#postlist").append(response);
		sessionStorage.setItem('curpost', $("#postlist > li:last").attr("id"));
	})
	.always(function (response) {
		sessionStorage.setItem('loading', "0");
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

function openwrite(uid){
	
	$("#popup_window .popup_box").empty();
	$("#popup_window").show();
	$("body").css({"overflow":"hidden"});
	
	$.ajax({
		url: 'openwrite',
		type: 'get', 
		dataType: 'html', 
		data: {
			_uid : uid
		}
	})
	.done(function (response) {
		$("#popup_window .popup_box").html(response);
	})
	.fail(function (response) {
	});
}
