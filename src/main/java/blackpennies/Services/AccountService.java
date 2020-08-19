package blackpennies.Services;

import blackpennies.Exceptions.CustomerDoesNotExistException;
import blackpennies.Exceptions.NotEnoughBalanceException;

import blackpennies.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }



    public void addBalance(Integer uid, double amount) throws CustomerDoesNotExistException {
        if(customerService.getUser(uid) == null){
            throw new CustomerDoesNotExistException("Customer does not exist");
        }
        User user = customerService.getUser(uid);
        user.addBalance(amount);

    }


    public void removeBalance(int uid, double amount) throws CustomerDoesNotExistException, NotEnoughBalanceException {
        if(customerService.getUser(uid) == null){
            throw  new CustomerDoesNotExistException("Customer does not exist");
        }

        User user = customerService.getUser(uid);


        if(!user.removeBalance(amount)){
            throw new NotEnoughBalanceException("Not enough balance!");
        }
        user.removeBalance(amount);

    }
}
