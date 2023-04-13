package com.workshop.ivar.service.dispatcher;

import com.workshop.ivar.dto.AuthenticationRequest;

/**
 * @author cuongnd9
 * @date 03/04/2023
 * @project ivar
 * @package com.workshop.ivar.service.dispatcher
 */
public interface IAuthenticationService {

    String authenticate(AuthenticationRequest request);

}
