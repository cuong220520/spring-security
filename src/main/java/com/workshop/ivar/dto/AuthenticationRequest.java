package com.workshop.ivar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author cuongnd9
 * @date 03/04/2023
 * @project ivar
 * @package com.workshop.ivar.dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest implements Serializable {

    private static final long serialVersionUID = 1l;

    private String email;
    private String password;

}
