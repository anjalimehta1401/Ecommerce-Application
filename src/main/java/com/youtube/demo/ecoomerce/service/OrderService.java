package com.youtube.demo.ecoomerce.service;

import com.youtube.demo.ecoomerce.model.LocalUser;
import com.youtube.demo.ecoomerce.model.WebOrder;
import com.youtube.demo.ecoomerce.model.dao.WebOrderDAO;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Service
public class OrderService {

    /** The Web Order DAO. */
    private WebOrderDAO webOrderDAO;

    /**
     * Constructor for spring injection.
     * @param webOrderDAO
     */
    public OrderService(WebOrderDAO webOrderDAO) {
        this.webOrderDAO = webOrderDAO;
    }

    /**
     * Gets the list of orders for a given user.
     * @param user The user to search for.
     * @return The list of orders.
     */
    public List<WebOrder> getOrders(LocalUser user) {
        return webOrderDAO.findByUser(user);
    }

}