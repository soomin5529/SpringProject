// 로그인 체크
function loginCheck() {
      if (document.loginFrm.loginId.value == "") {
         alert("아이디를 입력해 주세요.");
         document.loginFrm.loginId.focus();
         return;
      } else if (document.loginFrm.loginPwd.value == "") {
         alert("비밀번호를 입력해 주세요.");
         document.loginFrm.loginPwd.focus();
         return;
      } else {
         var id = document.getElementById("loginId").value;
         var pwd = document.getElementById("loginPwd").value;
         var idcheck = document.getElementById("idPresenceCheck");
      }

      $.ajax({
         type : "post",
         url : "/SpringTeamProject/request/loginCheck",
         contentType : "application/x-www-form-urlencoded; charset=UTF-8",
         data : {
            'userid' : id,
            'pwd' : pwd
         },
         success : function(textStatus) {
            if (textStatus.includes("ok")) {
               document.loginFrm.submit();
               sessionStorage.setItem("userid", id);
            } else {
               idcheck.innerHTML = textStatus;
            }
         }
      });
}

// 회원가입 아이디 중복 및 8자리 체크
function idCheck(userid) {
   var idcheck = document.getElementById("idOverlapCheck");
   if (userid.length < 8) {
      idcheck.innerHTML = "8자리로 만들어라";
   } else {
      idcheck.innerHTML = "";
      // 중복체크
      $.ajax({
         type : "post",
         url : "/SpringTeamProject/request/checkId",
         contentType : "application/x-www-form-urlencoded; charset=UTF-8",
         data : {
            'userid' : userid
         },
         success : function(textStatus) {
            idcheck.innerHTML = textStatus;
         }
      });
   }
}

// 회원가입 입력 체크
function inputCheck() {

   var chkboolean = false;

   var idcheck = document.getElementById("idOverlapCheck");
   if (!idcheck.innerHTML.includes("사용가능")) {
      alert("사용불가한 아이디입니다.");
      document.regForm.id.focus();
      return false;
   }
   // 비밀번호 및 비밀번호 확인 체크
   if (document.regForm.pwd.value == "") {
      alert("비밀번호를 입력하세요")
      document.regForm.pwd.focus();
      return;
   }
   if (document.regForm.repwd.value == "") {
      alert("비밀번호를 확인하세요");
      document.regForm.repwd.focus();
      return;
   }
   if (document.regForm.pwd.value != document.regForm.repwd.value) {
      alert("비밀번호가 일치하지 않습니다.");
      document.regForm.repwd.value = "";
      document.regForm.repwd.focus();
      return;
   }

   // 이름 체크
   if (document.regForm.name.value == "") {
      alert("이름을 입력해주세요.");
      document.regForm.name.focus();
      return;
   }

   // 이메일 체크
   if (document.regForm.email.value == "") {
      alert("이메일을 입력해주세요.");
      document.regForm.email.focus();
      return;
   }
   var str = document.regForm.email.value;
   var atPos = str.indexOf('@');
   var atLastPos = str.lastIndexOf('@');
   var dotPos = str.indexOf('.');
   var spacePos = str.indexOf(' ');
   var commaPos = str.indexOf(',');
   var eMailSize = str.length;
   if (atPos > 1 && atPos == atLastPos && dotPos > 3 && spacePos == -1
         && commaPos == -1 && atPos + 1 < dotPos && dotPos + 1 < eMailSize)
      ;
   else {
      alert('E-mail형식을 지켜주세요');
      document.regForm.email.focus();
      return;
   }

   // 생일 체크
   if (document.regForm.birthdate.value == "") {
      alert("생일을 입력해주세요.");
      return;
   }

   var signInInfo = {
      "userid" : $('#userid').val(),
      "pwd" : $('#pwd').val(),
      "name" : $('#name').val(),
      "email" : $('#email').val(),
      "birthdate" : $('#birthdate').val(),
      "gender" : $("input:radio[name='gender']:checked").val()
   }

   $.ajax({
      type : "post",
      url : "/SpringTeamProject/member/signIn",
      contentType : "application/x-www-form-urlencoded; charset=UTF-8",
      data : signInInfo,
      success : function(textStatus) {
         if (textStatus.includes('fail')) {
            alert("회원가입에 실패하였습니다.");
         } else {
            alert("회원가입 되셨습니다.");
         }
         window.location.href = "/SpringTeamProject/view/main";
      }
   });
   


}

function pwdCheck(){
   var chkboolean = false;
   // 비밀번호 및 비밀번호 확인 체크
   if (document.pwdForm.oldpwd.value == "") {
      alert("비밀번호를 입력하세요")
      document.regForm.pwd.focus();
      return;
   }
   if (document.pwdForm.pwd.value == "") {
      alert("새로운 비밀번호를 입력하세요")
      document.regForm.pwd.focus();
      return;
   }
   if (document.pwdForm.repwd.value == "") {
      alert("비밀번호를 확인하세요");
      document.regForm.repwd.focus();
      return;
   }
   if (document.pwdForm.pwd.value != document.pwdForm.repwd.value) {
      alert("비밀번호가 일치하지 않습니다.");
      document.pwdForm.repwd.value = "";
      document.pwdForm.repwd.focus();
      return;
   }
   
   var pwdInfo = {
         "pwd" : $('#pwd').val(),
      }
      $.ajax({
         type : "post",
         url : "/SpringTeamProject/member/updatepassword",
         contentType : "application/x-www-form-urlencoded; charset=UTF-8",
         data : pwdInfo,
         success : function(textStatus) {
            if (textStatus.includes('fail')) {
               alert("비밀번호 수정 실패");
            } else {
               alert("비밀번호 수정 완료");
            }
            window.location.href = "/SpringTeamProject/view/main";
         }
      });
}