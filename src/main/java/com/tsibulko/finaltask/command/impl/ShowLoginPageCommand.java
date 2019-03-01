package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.impl.CustomerServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ShowLoginPageCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ResponseContent responseContent = new ResponseContent();
        if (CustomerServiceImpl.isAuthenticated(session)) {
            System.out.println("aut");
            responseContent.setRouter(new Router("/jsp/barman.jsp", Router.Type.FORWARD));
            request.setAttribute("viewName", "empty"); //ПЕРЕДЕЛАТЬ КАК ТОЛЬКО ТАК СРАЗУ!!
        } else {
            responseContent.setRouter(new Router("/jsp/login.jsp", Router.Type.FORWARD));
        }
        return responseContent;
    }
}
