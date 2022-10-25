package com.eminimal.backend.services.impl.users;

import com.eminimal.backend.models.users.Users;
import com.eminimal.backend.repository.UsersRepository;
import com.eminimal.backend.services.interfaces.UserService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.auth.*;
import com.google.firebase.cloud.FirestoreClient;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class UserServiceImpl implements UserService {
    private static final String COLLECTION_NAME = "users";
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final Firestore dbFirestore = FirestoreClient.getFirestore();

    @Autowired
    private ModelMapper modelMapper;


//  Find account
    @Override
    public List<Users> findAll() throws FirebaseAuthException, ExecutionException, InterruptedException {

        List<Users> usersList = new ArrayList<>();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            Users users = document.toObject(Users.class);
            usersList.add(users);
        }
        return usersList;
    }

    @Override
    public Users findById(String uuid) throws Exception {
        ApiFuture<DocumentSnapshot> future = dbFirestore.collection(COLLECTION_NAME).document(uuid).get();
        DocumentSnapshot document = future.get();
        if(document.exists()){
            return document.toObject(Users.class);
        }else{
            throw new ExecutionException("Can't find user with id: " + uuid, null);
        }
    }


    //  Create account
    @Override
    public String save(Users entity) throws ExecutionException, InterruptedException, FirebaseAuthException {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setUid(entity.getUserId())
                .setDisplayName(entity.getUserName())
                .setEmail(entity.getUserEmail())
                .setPassword(hashPass(entity.getUserPassword()))
                .setEmailVerified(false)
                .setDisabled(false);

        UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
        dbFirestore.collection(COLLECTION_NAME).document(entity.getUserId()).set(entity);
        return "Successfully created new user: " + userRecord.getDisplayName();

    }

    public String hashPass(String pass){
        int strength = 10; // work factor of bcrypt
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
        return bCryptPasswordEncoder.encode(pass);
    }


//  Delete account
    @Override
    public String deleteById(String uuid) throws Exception {
        FirebaseAuth.getInstance().deleteUser(uuid);
        dbFirestore.collection(COLLECTION_NAME).document(uuid).delete();
        return "Remove user success with id: " + uuid;
    }

//   Update account
    @Override
    public String updateUserById(Users newUsers) throws Exception {
        Users user = findById(newUsers.getUserId());

        modelMapper.map(newUsers, user);
        dbFirestore.collection(COLLECTION_NAME).document(newUsers.getUserId()).set(user);

        UserRecord.UpdateRequest request = new UserRecord.UpdateRequest(user.getUserId())
            .setEmail(user.getUserEmail())
            .setPhoneNumber(user.getUserPhone())
            .setPassword(hashPass(user.getUserPassword()))
            .setDisplayName(user.getUserName())
            .setPhotoUrl(user.getUserImage());

        FirebaseAuth.getInstance().updateUser(request);

        return "Update success";
    }
}
