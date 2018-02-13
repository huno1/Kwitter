<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>

<html>
<body>
<h1>ツイート投稿</h1>
<form method="post" action="addpost">
<span class="count" >0</span><br>
	投稿内容：　<textarea type="text" name="postcontent" rows="10" cols="30" maxlength="250"></textarea><br><br>
	<input type="submit" value="投稿する">
</form>
</body>
</html>

<script type="text/javascript">
$(function(){
    var countMax = 250;
    $('textarea').bind('keydown keyup keypress change',function(){
        var thisValueLength = $(this).val().length;
        var countDown = (countMax)-(thisValueLength);
        $('.count').html(countDown);
 
        if(countDown < 0){
            $('.count').css({color:'#ff0000',fontWeight:'bold'});
        } else {
            $('.count').css({color:'#000000',fontWeight:'normal'});
        }
    });
    $(window).load(function(){
        $('.count').html(countMax);
    });
});
</script>