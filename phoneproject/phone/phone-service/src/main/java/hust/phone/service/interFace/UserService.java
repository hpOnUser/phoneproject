package hust.phone.service.interFace;

import hust.phone.mapper.pojo.Task;
import hust.phone.mapper.pojo.User;

public interface UserService {
    User getUserById(String userId);

    User login(String username, String password,String identity);

    int register(String username, String password, String role);

}
