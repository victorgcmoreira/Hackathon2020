package blackpennies.API;

import blackpennies.Exceptions.CustomerDoesNotExistException;
import blackpennies.Model.User;
import blackpennies.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/user")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }


    @RequestMapping(method = RequestMethod.GET, path = "{id}/details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserDetails(@PathVariable Integer id) throws CustomerDoesNotExistException {
        User user = customerService.getUser(id);
        
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/add")
    public ResponseEntity<?> addCustomer(@Valid @RequestBody User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            if(customerService.getUser(user.getNickname()) != null){
                return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
            }
            if(customerService.getUserByEmail(user.getEmail()) != null){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }catch (CustomerDoesNotExistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        customerService.addCustomer(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<User> deleteCustomer(@PathVariable Integer id){
            customerService.removeCustomer(id);
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);

    }

    @RequestMapping(method = RequestMethod.POST, path = "/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody User user){
        User savedUser;
        if(customerService.authenticate(user)){
            try {
                savedUser = customerService.getUser(user.getNickname());
            }
            catch (CustomerDoesNotExistException e){
              return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(savedUser,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/test")
    public ResponseEntity<User> getAllUsers(){
        User user = new User();
        user.setEmail("teste");
        user.setPassword("passtest");
        user.setNickname("Nickname");
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}/deposit")
    public ResponseEntity<?> addBalance(@PathVariable Integer id, @RequestBody User user) {
       try {
        customerService.getUser(id).addBalance(user.getBalance());
       }
       catch (CustomerDoesNotExistException e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}/withdraw")
    public ResponseEntity<?> removeBalance(@PathVariable Integer id, @RequestBody User user){
        try {
            if(!customerService.getUser(id).removeBalance(user.getBalance())){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (CustomerDoesNotExistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/list")
    public ResponseEntity<List<User>> listUsers(){
        return new ResponseEntity<>(customerService.getAll(), HttpStatus.OK);
    }

}
