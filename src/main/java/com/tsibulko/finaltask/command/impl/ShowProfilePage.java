package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.Include;
import com.tsibulko.finaltask.command.Page;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.CocktailServiceImpl;
import com.tsibulko.finaltask.service.impl.CustomerServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowProfilePage implements Command {
    private static final String SESSION_ATTRIBUTE = "user";
    private static final String COCKTAIL_LIST_ATTRIBUTE_NAME = "cocktailList";
    private static final String ID_PARAMETR_NAME = "id";
    private static final String CUSTOMER_ATTRIBUTE_NAME = "customer";

    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        CustomerServiceImpl service = (CustomerServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.CUSTOMER);
        Customer customer = service.getCustomerWithCocktails(Integer.valueOf(request.getParameter(ID_PARAMETR_NAME))
                ,new Customer());
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(Page.MAIN_PAGE.getRout(), Router.Type.FORWARD));
        request.setAttribute(CUSTOMER_ATTRIBUTE_NAME, customer);
        request.setAttribute(Include.VIEW_NAME.getName(), Include.PROFILE_INCLUDE.getName());
        return responseContent;
    }
}
