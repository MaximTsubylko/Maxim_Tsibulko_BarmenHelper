package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.Page;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.MailSender;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.CustomerServiceImpl;
import com.tsibulko.finaltask.service.message.CustomMessage;
import com.tsibulko.finaltask.service.message.CustomMessageFactory;
import com.tsibulko.finaltask.service.message.CustomMessageType;
import com.tsibulko.finaltask.validation.LoginAndRegistrationException;
import com.tsibulko.finaltask.validation.ServiceDateValidationException;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) throws ServiceException {
        CustomerServiceImpl service = (CustomerServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.CUSTOMER);
        service.signUp(request);
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(Page.SUCCESS_REGISTRATION.getRout(), Router.Type.REDIRECT));
        return responseContent;
    }
}
