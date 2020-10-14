window.onload = function() {
	
}

/*popup open/close */
function closePopDashboard(){
	document.getElementById("dashboard").style.display = "none";
}
function openPopCommunity(){
	document.getElementById("community").style.display = "block";
}

function closePopCommunity(){
	document.getElementById("community").style.display = "none";
}

function openPopCommunityReg(){
	document.getElementById("communityReg").style.display = "block";
}

function closePopCommunityReg(){
	document.getElementById("communityReg").style.display = "none";
}

function openPopMyPage(){
	document.getElementById("login").style.display = "block";
}

function closePopMyPage(){
	document.getElementById("login").style.display = "none";
}

function closePopLogin(){
	document.getElementById("login").style.display = "none";
}


/* bookmark icon */
function bookmark(){
	var bookmark = document.getElementById("bookmark");
	
	if(bookmark.classList.contains('on')){
		bookmark.className = bookmark.className.replace("on", "off");
	}
	else if(bookmark.classList.contains('off')){
		bookmark.className = bookmark.className.replace("off", "on");
	}
}

/* like icon */
function postLike(){
	var postLike = document.getElementById("likeBtn");

	console.log("ddd00");
	if(postLike.classList.contains('on')){
		postLike.className = postLike.className.replace("on", "off");
		postLike.innerHTML = '<svg viewBox="0 0 40 40" class="like-icon">'
		+ '<path d="M20 4.5h.8c.9.1 1.6.5 2.1 1.2.5.6.8 1.4.6 2.2v8.6H34c.7 0 1.4.3 1.8.9.5.6.8 1.5.7 2.4v7.8c0 2.3-.5 4.9-2.5 6.7-1.5 1.3-4 2.2-7.6 2.2H3.5v-15h7.6l5.2-8 1.3-8.6.1-.3zm-9.5 17v15"></path>'
		+ '</svg>'
		+ '<span class="like-txt">좋아요</span>';
	}
	else if(postLike.classList.contains('off')){
		postLike.className = postLike.className.replace("off", "on");
		postLike.innerHTML = '<svg viewBox="0 0 40 40" class="like-icon">'
		+ '<path d="M25 15V8c.4-2.4-1.5-4.7-4-5h-2a2 2 0 00-2 1.5v.2l-1.3 8.2-3 7.1H2v18h24.4C36 38 38 32.4 38 27.6V20c.2-2.6-1.6-5-4-4.9-.5-.4-.8-.4-1 0h-8zM12 38H9V20h3v18z"></path>'
		+ '</svg>'
		+ '<span class="like-txt">좋아요</span>';
	}
}