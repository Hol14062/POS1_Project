package spg.pos.note.service;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spg.pos.note.model.User;
import spg.pos.note.repository.UserRepository;

@Service
public class UserService
{
  @Autowired
  private UserRepository userRepository;
  
  public void createNewUser(String name, String email)
  {
    User user = new User(name, email, "", "", new Date(), randomString(6));
    userRepository.save(user);
  }
  
  /**
   * @source http://stackoverflow.com/questions/5683327/how-to-generate-a-random-string-of-20-characters
   */
  private String randomString(int length)
  {
    char[] chars = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    StringBuilder sb = new StringBuilder();
    Random random = new Random();
    
    for (int i = 0; i < length; i++) 
    {
        char c = chars[random.nextInt(chars.length)];
        sb.append(c);
    }
    
    return sb.toString();
  }
}
