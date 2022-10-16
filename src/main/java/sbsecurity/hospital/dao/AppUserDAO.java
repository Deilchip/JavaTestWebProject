package sbsecurity.hospital.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.security.crypto.password.PasswordEncoder;
import sbsecurity.hospital.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class AppUserDAO {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private sbsecurity.hospital.Reposiroty.AppUserRepository AppUserRepository;
    private static final Map<Long, AppUser> USERS_MAP = new HashMap<>();


    public AppUser findByUserName(String userName) {

        Iterable<AppUser> appUsers = AppUserRepository.findAll();
        for (AppUser u : appUsers) {
            System.out.println(u.getUserName());
            if (u.getUserName().equals(userName)) {
                return u;
            }
        }
        return null;
    }

    public List<AppUser> getAppUsers() {
        List<AppUser> list = new ArrayList<>();

        list.addAll(USERS_MAP.values());
        return list;
    }

    @Autowired
    private EntityManager entityManager;

    public AppUser findUserAccount(String userName) {
        try {
            String sql = "Select e from " + AppUser.class.getName() + " e " //
                    + " Where e.userName = :userName ";

            Query query = entityManager.createQuery(sql, AppUser.class);
            query.setParameter("userName", userName);

            return (AppUser) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}