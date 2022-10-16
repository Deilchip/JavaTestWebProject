package sbsecurity.hospital.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sbsecurity.hospital.Reposiroty.UserParamsRepository;
import sbsecurity.hospital.entity.UserParams;

import java.util.ArrayList;

@Repository
@Transactional

public class UserParamsDAO {
    @Autowired
    private UserParamsRepository paramRepository;

    public ArrayList FindAllParamsById (Long id){
        Iterable<UserParams> userParams = paramRepository.findAll();
        ArrayList list = new ArrayList();
        for (UserParams u : userParams) {
            if(u.getUserId()==id){
                list.add(u);
            }
        }
        return list;
    }

}
