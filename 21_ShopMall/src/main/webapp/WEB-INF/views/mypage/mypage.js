/**
 *  상품을 장바구니에 담기 위한 요청 전달 
 */

function go_cart(){
	/*
	 *  quantity 입력필드에 값이 있는지 확인
	 *  값이 없으면 alert 출력
	 *  값이 있으면: url -> "cart_insert" submit
	 */
	
	if($("#quantity").val()==""){
		alert("수량을 입력하세요");
		$("#quantity").focus();
	}else{
		$("#theform").attr("action", "cart_insert").submit();
	}
}