package ism.maecdsd.services.interfaces;

import java.util.List;

import ism.maecdsd.entity.Role;
import ism.maecdsd.entity.User;
import ism.maecdsd.services.ServicesI;

public interface UserService extends ServicesI<User> {

        public List<User> getAllUserByRole(Role role);
     public List<User> getAllUserByStaus(Boolean status);
     public User getUserByLoginPasword(String login,String password);
     public void changeStatus(boolean status,User user);

}
