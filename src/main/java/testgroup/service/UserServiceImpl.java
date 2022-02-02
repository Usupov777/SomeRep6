package testgroup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testgroup.dao.UserDAO;
import testgroup.model.User;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{

    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    @Transactional
    @Override
    public List<User> allUsers() {
        return userDAO.allUsers();
    }
    @Transactional
    @Override
    public void add(User user) {
        userDAO.add(user);
    }
    @Transactional
    @Override
    public void delete(User user) {
        userDAO.delete(user);
    }
    @Transactional
    @Override
    public void edit(User user) {
        userDAO.edit(user);
    }
    @Transactional
    @Override
    public User getById(int id) {
        return userDAO.getById(id);
    }
}
