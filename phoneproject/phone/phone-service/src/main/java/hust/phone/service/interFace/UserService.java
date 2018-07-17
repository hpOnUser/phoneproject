package hust.phone.service.interFace;

import hust.phone.mapper.pojo.User;

public interface UserService {
    User getUserById(String userId);

    User login(String username, String password);

    int register(String username, String password);

}
