/**
 * 회원 관련 JS 함수
 */

/*
 * 1. 약관 동의 여부 확인
 */

function go_next() {

	if ($(".agree")[0].checked == true) { // 클래스 agree가 배열 0번쨰 항목이 동의함 체크되어 있을떄
		$("#join").attr('action', 'join_form').submit(); // 회원가입 페이지로 이동
	} else if ($(".agree")[1].checked == true) { // 배열 1번쨰 항목이 체크 되어 있을때
		alert("약관에 동의해 주세요");
	}

}