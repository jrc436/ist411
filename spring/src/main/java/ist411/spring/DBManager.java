/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist411.spring;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author drdea
 */
public class DBManager {
    @Autowired
    private SessionFactory sessionFactory;
    
    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
