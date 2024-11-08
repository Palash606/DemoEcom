package com.hexaware.service;

import com.hexaware.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class AuthService {

    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    Map<String, String> map;

    { //anonymous block
        map = new HashMap<>();
        map.put("admin1", "admin1");
        map.put("admin2", "admin2");
    }

    public AuthService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.entityTransaction = entityManager.getTransaction();
    }

    public boolean checkIfAdmin(String username,String password) {
        if(map.containsKey(username)) {
            String mapPassword = map.get(username);
            return mapPassword.equals(password);
        }
        return false;
    }

    public boolean checkIfUsernameUnique(String username) {
        entityTransaction.begin();
        String jpql="select u from User u where u.username=?1";
        TypedQuery <User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter(1, username);
        List<User> list = query.getResultList();
        entityTransaction.commit();
        if(list != null && !list.isEmpty())
            return false;
        return true;
    }
}
