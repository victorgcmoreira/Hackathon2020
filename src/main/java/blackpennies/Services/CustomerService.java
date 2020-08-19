package blackpennies.Services;

import blackpennies.Exceptions.CustomerDoesNotExistException;
import blackpennies.Model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {

    private Map<Integer, User> customersMap = new HashMap<>();


    public void addCustomer(User user){
        customersMap.put(user.getId(),user);
    }

    public void removeCustomer(int id){
        customersMap.remove(id);
    }

    public User getUser(String name) throws CustomerDoesNotExistException {
        for(User u: customersMap.values()) {
            if(u.getNickname().equals(name)){
                return u;
            }
        }
        return null;
    }

    public User getUserByEmail(String email) throws CustomerDoesNotExistException {
        for(User u: customersMap.values()) {
            if(u.getEmail().equals(email)){
                return u;
            }
        }
        return null;
    }

    public User getUser(Integer id) throws CustomerDoesNotExistException {
        if(customersMap.get(id) == null){
            throw new CustomerDoesNotExistException("Customer does not exist!");
        }
        return customersMap.get(id);
    }

    public boolean authenticate(User user) {
        for (User u: customersMap.values()) {
            if(u.getNickname().equals(user.getNickname()) && u.getPassword().equals(user.getPassword())){
                return true;
            }
        }
        return false;
    }

    public List<User> getAll(){
        return new LinkedList<>(customersMap.values());
    }

}
