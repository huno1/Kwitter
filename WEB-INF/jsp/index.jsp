<%@ page contentType="text/html;charset=utf-8" %>

<%@ include file="header.jsp" %>
	
	<div id="main">
		<div id="subheader">
			<div class="sh_top">
				<div class="sh_bg">
				
				</div>
			</div>
			<div class="sh_bot">
				<div class="inner9 clearfix">
					<div class="leftSide">
					</div>
					<div class="centerSide">
						<ul class="clearfix">
							<li class="current"><a href="">ツイート<span>123123221</span></a></li>
							<li><a href="">フォロー<span>23221</span></a></li>
							<li><a href="">フォロワー<span>123221</span></a></li>
							<li><a href="">いいね<span>123121</span></a></li>
						</ul>
					</div>
					<div class="rightSide">
						<a href="" class="rounded">フォローする</a>
					</div>
					
				</div>
			</div>
		</div>
		<div id="mbody" class="wrapper">
			<div class="inner9 clearfix">
				<div class="leftSide">
					<div class="profilearea">
						<div class="avatar"><img src="https://pbs.twimg.com/profile_images/875517108884455426/q3HMm7hU_400x400.jpg"></div>
						<div class="accountname">
							<a href="">ローソン</a>
						</div>
						<p class="accountinfo">【ローソン公式Twitter】ローソンクルー♪あきこです(^^)最新情報をマイペースでお知らせします。instagramは https://www.instagram.com/akiko_lawson/  ☆ youtubeはhttps://m.youtube.com/user/lawsonnews 
lawson.co.jp
2010年2月に登録 
						</p>
					</div>
				</div>
				<div class="centerSide">
					
					<ul id="postlist" class="postlist">
						<li onclick="javascript:getPost(1);">
							<div class="accountgroup">
								<div class="avatar"><img src="https://pbs.twimg.com/profile_images/875517108884455426/q3HMm7hU_400x400.jpg"></div>
								<div class="accountname">ローソン</div>
								<div class="postdate">1時間前</div>
							</div>
							<div class="contentgroup">
								<p class="postcontent">＼ <a href="">#でか焼鳥無料プレゼント</a> ／
								1周年記念！フォロー＆リツイートで抽選で1日1万名様に さらにうまくなった <a href="">#でか焼鳥</a> がその場で当たります(^^) 3日目は1/19 10:59まで！ <a href="">#ローソン</a> <a href="http://lawson.eng.mg/9d182">http://lawson.eng.mg/9d182</a> </p>
								<div class="postimage"><video src="https://video.twimg.com/tweet_video/DT3mBJEVoAAT1gg.mp4"></div>
							</div>
							<div class="menugroup">
								<a class="reply"><i class="far fa-comment"></i></i><span>29</span></a>
								<a class="reply"><i class="far fa-heart"></i><span>29</span></a>
							</div>
						</li>
						
						<li onclick="javascript:getPost(2);">
							<div class="accountgroup">
								<div class="avatar"><img src="https://pbs.twimg.com/profile_images/875517108884455426/q3HMm7hU_400x400.jpg"></div>
								<div class="accountname">ローソン</div>
								<div class="postdate">1時間前</div>
							</div>
							<div class="contentgroup">
								<p class="postcontent"><a href="">「マッシュルームとズッキーニの五穀入りチャウダー」</a>が新発売♪ランチにもぴったりです(^^)
								<a href="">#ローソン</a> <a href="">#コンビニスープ</a> <a href="">#ナチュラルローソン</a> <a href="">http://lawson.eng.mg/8d00b</a></p>
								<div class="postimage"><img src="${pageContext.request.contextPath}/web-resources/images/DT3mByYUQAEhUUF.jpg"></div>
							</div>
							<div class="menugroup">
								<a class="reply"><i class="far fa-comment"></i><span>29</span></a>
								<a class="reply"><i class="far fa-heart"></i><span>29</span></a>
							</div>
						</li>
						
					</ul>
					<div id="popup_window">
						<div class="popup_box">
						
						</div>
					</div>
					
				</div>
				<div class="rightSide">
					<ul>
						<li>作成中…</li>
						<li>作成中…</li>
						<li>作成中…</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
  
<%@ include file="footer.jsp" %>