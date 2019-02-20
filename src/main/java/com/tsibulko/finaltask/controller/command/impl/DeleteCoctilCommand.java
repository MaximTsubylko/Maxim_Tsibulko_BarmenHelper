package com.tsibulko.finaltask.controller.command.impl;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.controller.command.Command;
import com.tsibulko.finaltask.controller.command.Router;
import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.dao.exception.PersistException;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.CocktailServiceImpl;
import com.tsibulko.finaltask.validation.exception.ServiceDateValidationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteCoctilCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) throws ServletException, IOException, SQLException, PersistException, DaoException, InterruptedException, ServiceDateValidationException {
        CocktailServiceImpl service = (CocktailServiceImpl) ServiceFactory.getInstance().getSrvice(ServiceTypeEnum.COCKTAILE);
        Integer id = Integer.parseInt(request.getParameter("cocktilId"));
        Cocktail cocktaile = service.getByPK(id);
        service.delete(cocktaile);
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("main?command=cocktil_list", "redirect"));
        return responseContent;
    }
}
