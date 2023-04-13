package com.workshop.ivar.dao;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author cuongnd9
 * @date 03/04/2023
 * @project ivar
 * @package com.workshop.ivar.dao
 */
public interface IUserDao {

    UserDetails findUserByEmail(String email);

}
