var _s = "null";
var _u = "";
var _cur = ">0";
var loading = 0;

preprocess();
console.log("pre");

$(function(){
	console.log("post");
	
	postprocess();

	$( "a" ).click(function( event ) {
	  event.stopPropagation();
	});
	
	$( "#popup_window" ).on("click", ".popup_box", function( event ) {
	  event.stopPropagation();
	});
	
	$("#popup_window").on("click", function(e){
		closepopup();
	});
	
	$(document).on("submit", ".writepost form", function(e){
		if($(this).find("textarea").val() != ''){
			addpost($(this).find("textarea").val());
		}
		return false;
	});
	
	$(document).on("submit", ".writereply form", function(e){
		if($(this).find("textarea").val() != ''){
			addreply($(this).closest(".popup_box").find(".postsingle").attr("pid"), $(this).find("textarea").val());
		}
		return false;
	});
	
});


$(window).on("load hashchange", function(){
	var show = setInterval(function(){
		if($("#postlist").find("li").length < 5){
			getPostlist();
		}
		
		setTimeout(function(){ clearInterval(show) }, 1000);
	}, 100);
	
});
$(window).on("scroll", function(){
	var _scroll = $(window).height() + $(window).scrollTop();
	var _height = $(document).height();
	
	if(_scroll+1000 > _height){
		getPostlist();
	}
});

function getPostlist(){
	
	if(loading == 1){
		return false;
	}
	
	loading = 1;
	
	var pid = _cur;
	
	$.ajax({
		url: 'postnext',
		type: 'post', 
		dataType: 'html', 
		data: { 
			'_pid' : pid,
			'_s' : _s,
			'_u' : _u
		}
	})
	.done(function (response) {
		$("#postlist").append(response);
		_cur = $("#postlist > li:last").attr("id")
		sessionStorage.setItem('maxpost', _cur);
	})
	.always(function (response) {
		loading = 0;
	});
}

function getPostlistNew(){
	
	if(window.sessionStorage.getItem('loading')=="1"){
		return false;
	}
	
	loading = 1;
	
	var pid = $("#postlist > li:first").attr("id");
	
	$.ajax({
		url: 'postnew',
		type: 'get', 
		dataType: 'html', 
		data: { 
			'_pid' : pid,
			'_s' : _s,
			'_u' : _u
		}
	})
	.done(function (response) {
		loading = 0;
		$("#postlist").prepend(response);
		if(pid!=$("#postlist > li:first").attr("id")){
			getPostlistNew();
		}
	})
	.fail(function (response) {
		loading = 0;
	})
	.always(function (response) {
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
			'_pid' : pid
		}
	})
	.done(function (response) {
		$("#popup_window .popup_box").html(response);
	})
	.fail(function (response) {
	});
}

function getReply(){
	
	var pid = $(".popup_box").find(".postsingle").attr("pid");
	
	$.ajax({
		url: 'replylist',
		type: 'get', 
		dataType: 'html', 
		data: {
			'_pid' : pid
		}
	})
	.done(function (response) {
		$("#popup_window .popup_box .readreply").html(response);
	})
	.fail(function (response) {
	});
}

function openwrite(){
	
	$("#popup_window .popup_box").empty();
	$("#popup_window").show();
	$("body").css({"overflow":"hidden"});
	
	$.ajax({
		url: 'openwrite'
	})
	.done(function (response) {
		$("#popup_window .popup_box").html(response);
	})
	.fail(function (response) {
	});
}

function addpost(_postcontent){
	
	$.ajax({
		url: 'addpost',
		type: 'post', 
		dataType: 'html', 
		data: {
			'postcontent' : _postcontent
		}
	})
	.done(function (response) {
		getPostlistNew();
		closepopup();
	})
	.fail(function (response) {
		alert("送信に失敗しました。");
	});
}

function addreply(_pid, _postcontent){
	
	$.ajax({
		url: 'addreply',
		type: 'post', 
		dataType: 'html', 
		data: {
			'pid' : _pid,
			'postcontent' : _postcontent
		}
	})
	.done(function (response) {
		getReply();
		$(".writereply form textarea").val("");
	})
	.fail(function (response) {
		alert("送信に失敗しました。");
	});
}

function closepopup(){

	$("#popup_window").hide();
	$("body").css({"overflow":"auto"});
	$("#popup_window .popup_box").empty();
	
}

function getFollowCheck(selector, targetusr){
	
	if(targetusr==""){
		$(selector).empty();
		return false;
	}
	
	var target;
	if(targetusr){
		target = targetusr;
	}else{
		target = _u;
	}
	
	$.ajax({
		url: 'followcheck',
		dataType: 'html', 
		data: {
			'_tid' : target
		}
	})
	.done(function (response) {
		var result;
		
		if(response.match(/true/)){
			result = "<a href=\"javascript: follow(0, '"+target+"', '"+selector+"');\" class=\"rounded\">フォロー中</a>";
		}else if(response.match(/false/)){
			result = "<a href=\"javascript: follow(1, '"+target+"', '"+selector+"');\" class=\"rounded\">フォローする</a>";
		}
		
		$(selector).html(result);
	})
	.fail(function (response) {
	});
}

function follow(flag, targetusr, selector){
	
	var targeturl;
	if(flag == 0){
		targeturl = 'unfollow';
	}else{
		targeturl = 'follow';
	}
	
	var target;
	if(targetusr){
		target = targetusr;
	}else{
		target = _u;
	}
	
	$.ajax({
		url: targeturl,
		data: {
			follow : target,
			unfollow : target
		}
	})
	.done(function (response) {
		getFollowCheck(selector, target);
	})
	.fail(function (response) {
	});
}


function preprocess(){
	
		 var arg  = new Object;
	 var params = decodeURIComponent(location.search.substring(1)).split('&');

	for(i=0; params[i]; i++) {
		var k = params[i].split('=');
		if(k[0]=="s"){
			_s = k[1];
		}else if(k[0]=="u"){
			_u = k[1]
		}
	}
	
}
function postprocess(){
	
	$.ajax({
		url: 'leftside',
		data: {
			'u' : _u
		}
	})
	.done(function (response) {
		$("#mbody .leftSide").html(response);
	})
	.fail(function (response) {
	});
	
	getFollowCheck("#subheader .followbtn", _u);
	
}