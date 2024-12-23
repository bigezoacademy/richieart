package com.richieartco.event;

import com.richieartco.user.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * @author Alfred Ochola
 */

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {
    private  User user;
    private String confirmationUrl;
    public RegistrationCompleteEvent(User user, String confirmationUrl) {
        super(user);
        this.user = user;
        this.confirmationUrl = confirmationUrl;
    }
}
