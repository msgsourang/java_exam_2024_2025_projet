package ism.maecdsd.repository.interfaces;

import java.util.List;

import ism.maecdsd.entity.Role;
import ism.maecdsd.entity.User;
import ism.maecdsd.repository.Repository;

public interface UserRepository extends Repository<User> {
    public List<User> listUserByRole(Role role);
    public List<User> listUserByStatus(Boolean status);
    public User getUserByLoginPasword(String login,String password);


}
