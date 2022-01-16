<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ include file="../header.jsp" %>   
<%@ include file="sub_img.html"%> 
<%@ include file="sub_menu.html" %>   
  <article>
    <h2>Join Us</h2>
    <form id="join" action="join" method="post" name="formm">
      <fieldset>
        <legend>Basic Info</legend>
        <label>User ID</label>
        <input type="text"      name="id"  id="id" value="${id}"  size="12"  >
        <input type="hidden"    name="reid" id="reid" value="${reid}">
        <input type="button"    value="중복 체크"  class="dup" onclick="idcheck()"><br>
        <label>Password</label> 
        <input type="password"  name="pwd" id="pwd"><br> 
        <label>Retype Password</label> 
        <input type="password"  name="pwdCheck" id="pwdCheck"><br> 
        <label>Name</label>
        <input type="text" name="name" id="name"><br> 
        <label>E-Mail</label>
        <input type="text" name="email"><br>

        
      </fieldset>
      <fieldset>
        <legend>Optional</legend>
        <label>Zip Code</label> 
        <input type="text"       name="zip_num"   size="10" >      
        <input type="button"     value="주소 찾기" class="dup" onclick="post_zip()"><br>
        <label>Address</label> 
        <input type="text"        name="addr1"   size="50"> <br>
        <input type="text"        name="addr2"   size="25" style="margin-left: 140px"> <br>
        <label>Phone Number</label> 
        <input  type="text"       name="phone"><br>
      </fieldset>
      <div class="clear"></div>
      <div id="buttons">
        <input type="button"    value="회원가입"   class="submit" onclick="go_save()"> 
        <input type="reset"      value="취소"     class="cancel">
      </div>
      <br>
    </form>
  </article>
<%@ include file="../footer.jsp" %>
  