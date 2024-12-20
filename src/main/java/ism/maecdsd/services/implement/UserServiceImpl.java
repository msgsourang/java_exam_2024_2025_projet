package ism.maecdsd.services.implement;

import java.util.List;

import ism.maecdsd.entity.Role;
import ism.maecdsd.entity.User;
import ism.maecdsd.repository.interfaces.UserRepository;
import ism.maecdsd.services.interfaces.UserService;

public class UserServiceImpl implements UserService {

private final UserRepository userRepository;

public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
}

@Override
public void add(User user){
        userRepository.insert(user);
    }

    @Override
    public List<User> getAll(){
       return  userRepository.lister();
    }


    @Override

    public List<User> getAllUserByRole(Role role){
        return  userRepository.listUserByRole(role);
     }

    @Override

     public List<User> getAllUserByStaus(Boolean status){
        return  userRepository.listUserByStatus(status);
     }
     
     @Override
     public User getUserByLoginPasword(String login,String password){
        return userRepository.getUserByLoginPasword(login, password);
     }
     @Override
     public void changeStatus(boolean status,User user){
      user.setStatus(status);
     }

     






}
