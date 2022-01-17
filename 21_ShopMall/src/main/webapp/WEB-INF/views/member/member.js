/**
 * 회원 관련 JS 함수
 */

/*
 * 1. 약관 동의 여부 확인
 */

function go_next() {

	if ($(".agree")[0].checked == true) { // 클래스 agree가 배열 0번쨰 항목이 동의함 체크되어
											// 있을떄
		$("#join").attr('action', 'join_form').submit(); // 회원가입 페이지로 이동
	} else if ($(".agree")[1].checked == true) { // 배열 1번쨰 항목이 체크 되어 있을때
		alert("약관에 동의해 주세요");
	}

}

/*
 * id 중복확인 화면 출력
 */

function idcheck() {

	if ($("#id").val() == "") { // id 입력하지 않았을떄
		alert("아이디를 입력하세요");
		$("#id").focus();
		return false;
	}

	// id가 입력이 되었으면 id 중복확인 윈도우 창 오픈(윈도우창 크기 및 사이즈 변경 여부)
	var url = "id_check_form?id=" + $("#id").val();
	window.open(url, "_blank_", "toolbar=no, menubar=no, scrollbars=no, resizable=yes, width=350, height=200");
}

/*
 *  중복확인 ID 사용
 */

function idok(){
	$("#theform").attr("action", "id_check_confirmed").submit();
}

/*
 * 회원가입시, 필수입력 항목 확인
 */

function go_save() {

	if ($("#id").val() == "") {
		alert("아이디를 입력 하세요");
		$("#id").focus();
		return false;
	} else if ($("#id").val() != $("#reid").val()) {
		alert("아이디 중복 체크를 하세요");
		$("#id").focus();
		return false;
	} else if ($("#pwd").val() == "") {
		alert("비밀번호를 입력 하세요");
		$("#pwd").focus();
		return false;
	} else if ($("#pwd").val() != $("#pwdCheck").val()) {
		alert("비밀번호가 일치하지 않습니다");
		$("#pwd").focus();
		return false;
	} else if ($("#name").val() == "") {
		alert("이름을 입력해 주세요");
		$("#name").focus();
		return false;
	} else {
		$("#join").attr("action", "join").submit(); // 회원가입 요청
	}

}

/*
 *  우편번호 / 주소찾기 윈도우 오픈
 */

function post_zip(){
	var url = "find_zip_num";
	window.open(url, "_blank_", "toolbar=no, menubar=no, scrollbars=no, resizable=yes, width=500, height=350");
	
}

