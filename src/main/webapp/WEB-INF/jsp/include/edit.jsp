<%--
  Created by IntelliJ IDEA.
  User: Buddtha
  Date: 01.03.2019
  Time: 0:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="utf-8"/>

<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en'}"/>
<fmt:setBundle basename="language" var="bundle" scope="application"/>

<div id="content-header">
    <div id="breadcrumb"><a href="?command=main" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>
        Home</a><a href="#" class="current">User edit</a></div>
    <h1 class="pagination-centered">Edit your profile</h1>
</div>
<div class="container-fluid">
    <hr>
    <div class="row-fluid">
        <div class="span6">
            <div class="widget-box">
                <div class="widget-title"><span class="icon"> <i class="icon-align-justify"></i> </span>
                    <h5>Personal info</h5>
                </div>
                <div class="widget-content nopadding">
                    <form action="${pageContext.request.contextPath}/barman" method="post" class="form-horizontal">
                        <div class="control-group">
                            <label class="control-label">First Name :</label>
                            <div class="controls">
                                <input type="text" class="span11" placeholder="First name"
                                       value="<c:out value="${customer.first_name}"/>" name="first_name">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Second Name :</label>
                            <div class="controls">
                                <input type="text" class="span11" placeholder="Second name"
                                       value="<c:out value="${customer.second_name}"/>" name="second_name">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">E-mail</label>
                            <div class="controls">
                                <input type="email" class="span11" placeholder="E-mail"
                                       value="<c:out value="${customer.email}"/>" name="email">
                            </div>
                        </div>
                        <div class="form-actions">
                            <input type="hidden" name="command" value="edit_profile">
                            <button type="submit" class="btn btn-success">Save</button>
                        </div>
                    </form>
                </div>
            </div>

        </div>


        <div class="span6">
            <div class="widget-box">
                <div class="widget-title"><span class="icon"> <i class="icon-align-justify"></i> </span>
                    <h5>Change password</h5>
                </div>
                <div class="widget-content nopadding">
                    <form action="${pageContext.request.contextPath}/barman" method="post" class="form-horizontal">

                        <div class="control-group">
                            <label class="control-label">Old password :</label>
                            <div class="controls">
                                <input type="text" class="span11" placeholder="Old password" name="old_password">
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">Password :</label>
                            <div class="controls">
                                <input type="text" class="span11" placeholder="Password" name="password">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Confirm password :</label>
                            <div class="controls">
                                <input type="text" class="span11" placeholder="Confirm password"
                                       name="confirm_password">
                            </div>
                        </div>

                        <div class="form-actions">
                            <button type="submit" class="btn btn-success">Save</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
