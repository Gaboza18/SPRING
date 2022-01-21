/**
 * 
 */

// 상품 등록 화면 출력
function go_wrt() {
	$("#prod_form").attr("action", "admin_product_write_form").submit();
}

// 상품 등록시 입력값 확인
function go_save() {

	var $price1 = $("#price1");
	var $price2 = $("#price2");
	var $price3 = $("#price3");

	if ($("#kind").val == "") {
		alert("상품종류를 입력하세요!");
		$("#kind").focus();
	} else if ($("#name").val() == "") {
		alert("상품명을 입력하세요!");
		$("#name").focus();
	} else if ($price1.val() == "") {
		alert("원가를 입력하세요!");
		$price1.focus();
	} else if ($price2.val() == "") {
		alert("판매가를 입력하세요!");
		$price2.focus();
	} else if ($("#content").val() == "") {
		alert("상품상세를 입력하세요!");
		$("#content").focus();
	} else if ($("#product_image").val() == "") {
		alert("상품이미지를 입력하세요!");
		$("#product_image").focus();
	} else {
		// 주의: 이미지 파일을 전송하므로 enctype이 설정되어야 함
		$("#write_form").attr("encoding", "multipart/form-data");
		$("#write_form").attr("action", "admin_product_write").submit();
	}
}

// price3 필드 입력: 판매가(price2) - 원가(price1)을 계산하여 설정
function go_ab() {

	var a = $("#price2").val(); // 판매가
	var b = $("#price1").val(); // 원가

	$("#price3").val(a - b);
}

// 상품 상세보기 요청 함수
function go_detail(pseq) {
	$("#prod_form").attr("action", "admin_product_detail?pseq=" + pseq)
			.submit(); // #prod_form : 요청방식 Id 값
}

// 상품정보 수정 요청 함수
function go_mod(pseq) {
	$("#detail_form").attr("action", "admin_product_update_form?pseq=" + pseq)
			.submit();
}

// 상품정보 수정 구현
function go_mod_save(pseq) {

	var $price1 = $("#price1");
	var $price2 = $("#price2");
	var $price3 = $("#price3");

	if ($("#kind").val == "") {
		alert("상품종류를 입력하세요!");
		$("#kind").focus();
	} else if ($("#name").val() == "") {
		alert("상품명을 입력하세요!");
		$("#name").focus();
	} else if ($price1.val() == "") {
		alert("원가를 입력하세요!");
		$price1.focus();
	} else if ($price2.val() == "") {
		alert("판매가를 입력하세요!");
		$price2.focus();
	} else if ($("#content").val() == "") {
		alert("상품상세를 입력하세요!");
		$("#content").focus();
	} else {
		// 베스트 상품 체크 수정
		if ($("#bestyn").is(":checked")) {
			$("#bestyn").val("y");
		} else {
			$("#bestyn").val("n");
		}
		// 신상품 체크 수정
		if ($("#useyn").is(":checked")) {
			$("#useyn").val("y");
		} else {
			$("#useyn").val("n");
		}

		// 주의: 이미지 파일을 전송하므로 enctype이 설정되어야 함
		$("#update_form").attr("encoding", "multipart/form-data");
		$("#update_form").attr("action", "admin_product_update").submit();
	}
}