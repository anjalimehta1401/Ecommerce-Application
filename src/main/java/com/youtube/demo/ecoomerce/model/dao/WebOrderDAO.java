package com.youtube.demo.ecoomerce.model.dao;

import com.youtube.demo.ecoomerce.model.LocalUser;
import com.youtube.demo.ecoomerce.model.WebOrder;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.context.annotation.Bean;

import java.util.List;

public interface WebOrderDAO extends ListCrudRepository<WebOrder, Long> {

    List<WebOrder> findByUser(LocalUser user);

}